package GestionStock.JSON;

import GestionStock.Entidades.Movimiento;
import GestionStock.Enum.TipoMovimiento;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONMovimiento {

    public static JSONObject serializar(Movimiento movimiento)
    {
        JSONObject json = null;

        try {
            json = new JSONObject();

            json.put("fecha", movimiento.getStringFecha());
            json.put("idMovimiento", movimiento.getIdMovimiento());

            json.put("idUsuario", movimiento.getIdUsuario());
            json.put("tipoMovimiento", movimiento.getTipoMovimiento());

            json.put("idProducto", movimiento.getIdProducto());
            json.put("idLote", movimiento.getIdLote());

            json.put("cantidad", movimiento.getCantidad());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static JSONArray serializar(ArrayList<Movimiento> lista)
    {
        JSONArray json = null;

        try {
            json = new JSONArray();

            for (Movimiento mov : lista)
                json.put( serializar(mov) );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }


    public static Movimiento deserializar(JSONObject json)
    {
        Movimiento movimiento = null;

        try {

            String fecha        = json.getString("fecha");
            String idMovimiento = json.getString("idMovimiento");

            String idProducto = json.getString("idProducto");
            String idLote     = json.optString("idLote", "N/A");

            String idUsuario = json.getString("idUsuario");

            int cantidad = json.getInt("cantidad");

            TipoMovimiento tipoMovimiento = TipoMovimiento.valueOf( json.getString("tipoMovimiento") );

            movimiento = new Movimiento(
                    fecha,
                    idMovimiento,
                    idUsuario,
                    tipoMovimiento,
                    idProducto,
                    idLote,
                    cantidad
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movimiento;
    }

    public static ArrayList<Movimiento> deserializar(JSONArray json)
    {
        ArrayList<Movimiento> lista = null;

        try {
            lista = new ArrayList<>();

            for (int i = 0; i < json.length(); i++)
                lista.add( deserializar(json.getJSONObject(i)) );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
