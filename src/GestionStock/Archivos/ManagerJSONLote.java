package GestionStock.Archivos;


import GestionStock.Entidades.Lote;
import GestionStock.JSON.JSONLote;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.LinkedList;

public class ManagerJSONLote {



    public static void toArchivo(HashMap<String, LinkedList<Lote>> lista, String archivo)
    {
        JSONArray jsonArray = JSONLote.serializar(lista);

        if (jsonArray == null) return;
        OperacionesLectoEscritura.grabar(archivo, jsonArray);
    }

    public static  HashMap<String, LinkedList<Lote>> fromFile(String archivo)
    {
        JSONTokener tokener = OperacionesLectoEscritura.leer(archivo);

        if (tokener == null) return null;

        HashMap<String, LinkedList<Lote>> lista = null;


        try {
            JSONArray jsonArray = new JSONArray(tokener);
            lista = JSONLote.deserializar(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
