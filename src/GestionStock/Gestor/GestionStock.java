package GestionStock.Gestor;

import GestionStock.Archivos.ArchivoSistema;
import GestionStock.Archivos.ManagerJSONStock;
import GestionStock.Entidades.Stock;
import GestionStock.Interfaces.*;
import GestionStock.Interfaces.iListar;
import GestionStock.Utils.Utils;

import java.util.LinkedHashSet;

public final class GestionStock implements iAgregar<Stock>, iEliminar<Stock>, iListar, iGuardar, iCargar, iExtraer {


    private LinkedHashSet<Stock> listaPrincipal = new LinkedHashSet<>();

    public GestionStock() {
        cargar();
    }


    
    //Busca por atributo idProducto: String - filtra por el hashCode,equals y estado activo: boolean:
    public Stock buscar(String idProducto)
    {
        if (idProducto.isEmpty()) return  null;

        for (Stock i : listaPrincipal)
        {
            if ( i.getIdProducto().equals(idProducto))
                return  i;
        }
        return null;
    }

    
    
    public boolean verificarExistenciaStock(Stock stock)
    {
        if (stock == null)
        {
            System.err.println("[ERROR] [PARAMETRO INVALIDO] [El parametro es nulo]");
            return false;
        }

        return  listaPrincipal.contains(stock);
    }


    
    public boolean verificarExistenciaStock(String idProducto)
    {
        Stock buscado = buscar(idProducto);
        if (buscado != null) return true;
        return false;
    }


    
    @Override
    public boolean agregar(Stock stock)
    {
        if (verificarExistenciaStock(stock))
            return false;

        if (listaPrincipal.add(stock))
            guardar();

        return false;
    }

    
    @Override
    public boolean eliminar(Stock stock)
    {
        if (!verificarExistenciaStock(stock)) return false;

        for (Stock actual : listaPrincipal)
        {
            if (actual.equals(stock))
            {
                actual.darBaja();
                guardar();
                return true;
            }
        }
        return false;
    }

    
    @Override
    public String listar()
    {
        StringBuilder sb = new StringBuilder(Utils.dibujarEncabezadoTablaStock());
        String separador = Utils.dibujarBarra(sb.length() / 3, "-"); // sb.length / 3 : Es para calcular la longitud total de los campos del enecabezado.

        if (listaPrincipal.isEmpty())
        {
            sb.append("\nNo hay datos cargados!");
            return sb.toString();
        }

        for (Stock i : listaPrincipal)
        {
            sb.append("\n").append(i.getInformacion());
            sb.append(separador);

        }

        return sb.toString();
    }


    
    public String listar(boolean activos)
    {
        StringBuilder sb = new StringBuilder(Utils.dibujarEncabezadoTablaStock());

        if (listaPrincipal.isEmpty())
        {
            sb.append("\nNo hay datos cargados!");
            return sb.toString();
        }

        String separador = Utils.dibujarBarra(sb.length() / 3, "-");

        for (Stock i : listaPrincipal)
        {
            if (activos && i.isEstadoActivo())
                sb.append("\n").append(i.getInformacion());

            else if (!activos && !i.isEstadoActivo())
                sb.append("\n").append(i.getInformacion());

              sb.append("\n").append(separador);
        }

        return  sb.toString();
    }

    @Override
    public void guardar() {
        ManagerJSONStock.toArchivo(listaPrincipal, ArchivoSistema.DATOS_STOCK_JSON.getNombre() );
    }

    //para cargar por primera vez o actualizar:
    @Override
    public void cargar() {
        listaPrincipal = ManagerJSONStock.fromArchivo(ArchivoSistema.DATOS_STOCK_JSON.getNombre());
    }


    public boolean altaStockProducto(String idproducto)
    {
        Stock buscado = buscar(idproducto);

        if (buscado == null) return false;

        buscado.darAlta();
        guardar();

        return true;
    }

    public boolean bajaStockProducto(String idproducto)
    {
        return eliminar( new Stock(idproducto) );
    }


    public String consultarStock(String idProducto)
    {
        if (idProducto == null || idProducto.isEmpty())
            return "\n[ERROR] [PARAMETRO INVALIDO] [idProducto es nulo]";

        Stock buscado = buscar(idProducto);

        if (buscado == null)
            return String.format("[STOCK PRODUCTO NO ENCONTRADO] [ID: %s]", idProducto);

        return String.format("\n%s\n%s", Utils.dibujarEncabezadoTablaStock(), buscado.getInformacion());
    }

    public boolean extraer(String idProducto, int cantidad)
    {
        Stock buscado = buscar(idProducto);

        if (buscado == null) return false;

        return buscado.extraer(cantidad);
    }

    public boolean actualizarCantStock(String idProducto, int cantidad)
    {
        Stock buscado = buscar(idProducto);

        if (buscado == null) return false;

        buscado.setCantidad(cantidad);
        guardar();

        return true;
    }


}
