package GestionStock.JSON;

import GestionStock.GeneradorID.RegistroID;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONRegistroID {

    public static JSONObject serializar(RegistroID registroID)
    {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            jsonObject.put("contdLoteID", registroID.getLoteID() );
            jsonObject.put("contdStockID", registroID.getStockID() );
            jsonObject.put("contdMovimientoID", registroID.getMovimientoID() );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static RegistroID deserializar(JSONObject jsonObject)
    {
        RegistroID registroID = null;

        try {
            registroID = new RegistroID();

            registroID.setLoteID(  jsonObject.optInt("contdLoteID", 0) );
            registroID.setStockID( jsonObject.optInt("contdStockID", 0));
            registroID.setLoteMovimientoID( jsonObject.optInt("contdMovimientoID", 0));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return registroID;
    }
}