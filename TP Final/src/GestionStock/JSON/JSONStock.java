package GestionStock.JSON;

import GestionStock.Entidades.Stock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashSet;

public class JSONStock {

    public static JSONObject serializar(Stock obj)
    {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            //serializar atributos base:
            jsonObject.put("idStock", obj.getId());

            jsonObject.put("fechaAlta", obj.getStringFechaAlta());
            jsonObject.put("fechaBaja", obj.getStringFechaBaja());
            jsonObject.put("fechaUltimaActualizacion", obj.getStringFechaUltimaActualizacion( ));
            jsonObject.put("estadoActivo", obj.isEstadoActivo() );

            //serializar atributos propia clase:
            jsonObject.put("idProducto", obj.getIdProducto() );
            jsonObject.put("cantidad", obj.getCantidad() );
            jsonObject.put("min", obj.getMin() );
            jsonObject.put("max", obj.getMax() );


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;
    }


    public static JSONArray serializar(LinkedHashSet<Stock> lista)
    {
        if (lista == null || lista.isEmpty()) return null;

        JSONArray jsonArray = null;

        try {
                jsonArray = new JSONArray();

                for (Stock stock : lista)
                    jsonArray.put( serializar(stock) );

        } catch (JSONException e){
                e.printStackTrace();
        }

        return jsonArray;
    }


    public static Stock deserializar(JSONObject  jsonObject)
    {
        Stock stock = null;
        try {

            stock = new Stock();

            //deserializar atributos base:
            stock.setId( jsonObject.getString("idStock") );

            stock.setFechaAlta( jsonObject.getString("fechaAlta") );
            stock.setFechaBaja( jsonObject.getString("fechaBaja") );

            stock.setFechaUltimaActualizacion( jsonObject.getString("fechaUltimaActualizacion") );
            stock.setEstadoActivo( jsonObject.getBoolean("estadoActivo") );


            //deserializar atributos propia clase:
            stock.setIdProducto( jsonObject.getString("idProducto") );
            stock.setCantidad( jsonObject.getInt("cantidad") );
            stock.setMax( jsonObject.getInt("max") );
            stock.setMin( jsonObject.getInt("min") );


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stock;
    }


    public static LinkedHashSet<Stock> deserializarLinkedHashSet(JSONArray jsonArray)
    {
        LinkedHashSet<Stock> lista = null;

        try {
            lista = new LinkedHashSet<>();

            for (int i = 0; i < jsonArray.length(); i++)
            {
                lista.add( deserializar( jsonArray.getJSONObject(i)) );
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
