package GestionStock.JSON;

import GestionStock.Entidades.Lote;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JSONLote {



    public static JSONObject serializar(Lote obj)
    {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            jsonObject.put("idLote", obj.getId());
            jsonObject.put("fechaAlta", obj.getStringFechaAlta());
            jsonObject.put("fechaBaja", obj.getStringFechaBaja());
            jsonObject.put("fechaUltimaActualizacion", obj.getStringFechaUltimaActualizacion());
            jsonObject.put("estadoActivo", obj.isEstadoActivo() );


            jsonObject.put("idProducto", obj.getIdProducto());
            jsonObject.put("idProveedor", obj.getIdProveedor());
            jsonObject.put("cantidadInicial", obj.getCantidadInicial());
            jsonObject.put("cantidadDisponible", obj.getCantidadDisponible());
            jsonObject.put("precioUnitario", obj.getPrecioUnitario());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;
    }


    public static JSONArray serializar(LinkedList<Lote> lista)
    {
        JSONArray json = null;

        try {
                json = new JSONArray();

                for (Lote lote : lista)
                    json.put( serializar(lote) );

        } catch (JSONException e) {
                e.printStackTrace();
        }
        return  json;
    }

    public static JSONArray serializar(HashMap<String, LinkedList<Lote> > mapLote)
    {
        JSONArray json = null;

        try {
            json = new JSONArray();

            for (Map.Entry<String, LinkedList<Lote>> claveValorActual : mapLote.entrySet() )
            {
                JSONObject tempJOSNObject = new JSONObject();

                tempJOSNObject.put("idProducto", claveValorActual.getKey( ) );
                tempJOSNObject.put("lista",   serializar(claveValorActual.getValue( )) );

                json.put(tempJOSNObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  json;
    }


    public static Lote deserializar(JSONObject obj)
    {
        Lote lote = null;

        try {

            lote = new Lote();

            lote.setId( obj.getString("idLote") );

            lote.setFechaAlta( obj.getString("fechaAlta") );
            lote.setFechaBaja( obj.getString("fechaBaja") );

            lote.setFechaUltimaActualizacion( obj.getString("fechaUltimaActualizacion") );
            lote.setEstadoActivo( obj.getBoolean("estadoActivo") );


            lote.setIdProducto( obj.getString("idProducto") );
            lote.setIdProveedor( obj.getString("idProveedor") );

            lote.setCantidadInicial( obj.getInt("cantidadInicial") );
            lote.setCantidadDisponible( obj.getInt("cantidadDisponible") );

            lote.setPrecioUnitario( obj.getDouble("precioUnitario") );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  lote;
    }


    public static LinkedList<Lote> deserializarLinkedList(JSONArray array)
    {
        LinkedList<Lote>  lista = null;

        try {
            lista = new LinkedList<>();

            for (int i = 0; i < array.length(); i++)
                lista.add( deserializar(array.getJSONObject(i)) );


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  lista;
    }

    public static HashMap<String, LinkedList<Lote> > deserializar(JSONArray array)
    {
        HashMap<String, LinkedList<Lote> > lista = null;

        try {

            lista = new HashMap<>();

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject tempJSONObject = array.getJSONObject(i);

                lista.put(
                        tempJSONObject.getString("idProducto"),
                        deserializarLinkedList( tempJSONObject.getJSONArray("lista") )
                );

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
