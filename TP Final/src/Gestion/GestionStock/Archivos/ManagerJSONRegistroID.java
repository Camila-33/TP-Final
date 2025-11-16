package GestionStock.Archivos;

import GestionStock.GeneradorID.RegistroID;
import GestionStock.JSON.JSONRegistroID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class ManagerJSONRegistroID {

    public static void toArchivo(RegistroID registroID, String archivo)
    {
        OperacionesLectoEscritura.grabar(archivo, JSONRegistroID.serializar(registroID) );
    }

    public static RegistroID fromArchivo(String archivo)
    {
        JSONTokener tokener = OperacionesLectoEscritura.leer(archivo);
        RegistroID registroID = null;

        try {
            JSONObject jsonObject = new JSONObject(tokener);
            registroID = JSONRegistroID.deserializar(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return registroID;
    }

}
