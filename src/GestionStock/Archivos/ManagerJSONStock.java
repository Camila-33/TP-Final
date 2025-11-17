package GestionStock.Archivos;

import GestionStock.Entidades.Stock;
import GestionStock.JSON.JSONStock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.LinkedHashSet;

public final class ManagerJSONStock {

    public static void toArchivo(LinkedHashSet<Stock> lista, String archivo)
    {
        OperacionesLectoEscritura.grabar(archivo, JSONStock.serializar(lista) );
    }

    public static LinkedHashSet<Stock> fromArchivo(String archivo)
    {
        JSONTokener tokener = OperacionesLectoEscritura.leer(archivo);
        LinkedHashSet<Stock> linkedHashSet = null;

        if (tokener == null)
            return null;

        try {

            JSONArray jsonArray = new JSONArray(tokener);
            linkedHashSet = JSONStock.deserializarLinkedHashSet(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return linkedHashSet;
    }
}
