package GestionStock.Archivos;

import GestionStock.Entidades.Movimiento;
import GestionStock.JSON.JSONMovimiento;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.ArrayList;

public final class ManagerJSONMovimiento {

    public static void toArchivo(ArrayList<Movimiento> lista, String archivo)
    {
        OperacionesLectoEscritura.grabar(archivo, JSONMovimiento.serializar(lista) );
    }

    public static ArrayList<Movimiento> fromArchivo(String archivo)
    {
        JSONTokener tokener = OperacionesLectoEscritura.leer(archivo);
        ArrayList<Movimiento> lista = null;

        if (tokener == null) return null;

        try {
            lista = JSONMovimiento.deserializar( new JSONArray(tokener) );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

}