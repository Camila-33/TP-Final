package GestionStock.Gestor;

import GestionStock.Archivos.ArchivoSistema;
import GestionStock.Archivos.ManagerJSONMovimiento;
import GestionStock.Entidades.Movimiento;
import GestionStock.Interfaces.iAgregar;
import GestionStock.Interfaces.iCargar;
import GestionStock.Interfaces.iGuardar;
import GestionStock.Interfaces.iListar;
import GestionStock.Utils.Utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class GestorMovimiento implements iGuardar, iCargar, iAgregar<Movimiento>, iListar {

    private ArrayList<Movimiento> movimientos;

    public GestorMovimiento() {
        cargar();
    }

    @Override
    public void cargar()
    {
        movimientos = ManagerJSONMovimiento.fromArchivo(ArchivoSistema.DATOS_MOVIMIENTO_JSON.getNombre());

        if (movimientos == null)
            movimientos = new ArrayList<>();
    }

    @Override
    public void guardar() {
        ManagerJSONMovimiento.toArchivo(movimientos, ArchivoSistema.DATOS_MOVIMIENTO_JSON.getNombre());
    }

    @Override
    public  boolean agregar(Movimiento movimiento)
    {
       boolean resultado = movimientos.add(movimiento);

       guardar();
       cargar();

       return resultado;
    }

    @Override
    public String listar()
    {
        StringBuilder listado = new StringBuilder(Utils.dibujarEncabezadoTablaMovimiento());

        String separador = "\n" + Utils.dibujarLinea(listado.length() / 3);


        String elementos = movimientos.stream()
                .map(Movimiento::getInformacion)
                .collect(Collectors.joining(separador));

        listado.append(elementos);

        return listado.toString();
    }

    public String listar(String idUsuario)
    {
        StringBuilder listado = new StringBuilder(Utils.dibujarEncabezadoTablaMovimiento());
        String separador = "\n" + Utils.dibujarLinea(listado.length() / 3);

        String movimientoUsuario = movimientos.stream()
                .filter(mov -> mov.getIdUsuario().equals(idUsuario))
                .map(Movimiento::getInformacion)
                .collect(Collectors.joining("\n"));

        listado.append(movimientoUsuario);

        return listado.toString();
    }
}
