package GestionStock.Gestor;


import GestionStock.Archivos.ManagerJSONLote;
import GestionStock.Entidades.Lote;
import GestionStock.Archivos.ArchivoSistema;
import GestionStock.Interfaces.*;
import GestionStock.Utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

public final class GestionLote implements iAgregar<Lote>, iEliminar<Lote>, iListar, iGuardar, iCargar, iExtraer {

    private HashMap<String, LinkedList<Lote>> lista;


    public GestionLote( ) {
        cargar();
    }

    @Override
    public void cargar()
    {
        lista = ManagerJSONLote.fromFile( ArchivoSistema.DATOS_LOTE_JSON.getNombre( ) );

        if (lista == null)
            lista = new HashMap<>();
    }

    @Override
    public void guardar() {
            ManagerJSONLote.toArchivo(lista, ArchivoSistema.DATOS_LOTE_JSON.getNombre());
    }

    @Override
    public boolean agregar(Lote lote)
    {
        if (lote == null) return false;

        LinkedList<Lote> nuevo = lista.get(lote.getIdProducto());

        if (nuevo == null)
        {
            nuevo = new LinkedList<Lote>();
            nuevo.add(lote);
            lista.put( lote.getIdProducto(), nuevo);

        } else {
            nuevo.add(lote);
        }

        guardar();

        return true;
    }

    @Override
    public boolean eliminar(Lote lote)
    {
        if (lote == null) return false;

        LinkedList<Lote> listaLoteProductoBuscado = lista.get( lote.getIdProducto() );

        if (listaLoteProductoBuscado == null) return false;

        for (Lote index : listaLoteProductoBuscado)
        {
            if (index.getId().equals( lote.getId() ))
            {
                index.darBaja();
                index.setCantidadDisponible(0);
                guardar();
                cargar();
                return true;
            }
        }

        return false;
    }

    public boolean eliminar(String idLote)
    {
        for (Map.Entry<String, LinkedList<Lote>> lotes : lista.entrySet())
        {
            for (Lote i : lotes.getValue())
            {
                if ( i.getId().equals(idLote) )
                {
                    i.darBaja();
                    guardar();
                    cargar();
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String listar()
    {
        StringBuilder listado = new StringBuilder( Utils.dibujarEncabezadoTablaLote());
        String separador = "\n" +Utils.dibujarLinea(listado.toString().length() / 3);

        if (lista.isEmpty()) {
            listado.append("\n").append("No hay datos cargados!");
            return listado.toString();
        }

        String elementos =
                lista.values().stream()
                .flatMap(List::stream)
                .filter(Lote::isEstadoActivo)
                .map(Lote::getInformacion)
                .collect(Collectors.joining(separador));

        listado.append(elementos);


        return listado.toString();
    }


    public String listar(String idProducto)
    {
        StringBuilder listado = new StringBuilder( Utils.dibujarEncabezadoTablaLote());
        String separador = "\n" +Utils.dibujarLinea(listado.toString().length() / 3);

        if (lista.isEmpty() || idProducto == null || !lista.containsKey(idProducto) )
        {
            listado.append("\n").append("No hay datos cargados!");
            return listado.toString();
        }

        LinkedList<Lote> lotes = lista.get(idProducto);

        String  elementos = lotes.stream()
                .filter(lote -> lote.isEstadoActivo())
                .map(Lote::getInformacion)
                .collect(Collectors.joining(separador));

        return listado.append(elementos).toString();
    }


    @Override
    public boolean extraer(String idProducto, int cantidad)
    {
        int cantDisponible = calcularStockProducto(idProducto);

        if (cantDisponible == 0 || cantidad > cantDisponible || cantidad <= 0)
            return false;

        LinkedList<Lote> lotes = lista.get(idProducto);

        for (Lote lote : lotes)
        {
            int disponible = lote.getCantidadDisponible();

            if (disponible >= cantidad)
            {
                lote.sacarProducto(cantidad);
                guardar();
                cargar();
                return true;

            } else {
                cantidad -= disponible;
                lote.sacarProducto(disponible);
            }
        }

        return false;
    }

    public int calcularStockProducto(String idProducto)
    {
        if (idProducto == null || idProducto.isEmpty()) return 0;

        LinkedList<Lote> lotes = lista.get(idProducto);

        if (lotes == null) return 0;

        int total = 0;

        for (Lote i : lotes)
            total += i.getCantidadDisponible();

        return total;
     }


     public ArrayList<Lote> getArrayListLote()
     {
         ArrayList<Lote> lotesDisponibles = new ArrayList<>();

         for (Map.Entry<String, LinkedList<Lote>> lotes : lista.entrySet())
         {
             for (Lote i : lotes.getValue())
             {
                 if (i.isEstadoActivo())
                     lotesDisponibles.add(i);
             }
         }
         return lotesDisponibles;
     }

}
