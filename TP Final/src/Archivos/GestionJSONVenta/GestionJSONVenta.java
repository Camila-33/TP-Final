package Archivos.GestionJSONVenta;

import Archivos.OperacionesLectoEscritura;
import Transacciones.Detalles.DetalleVenta;
import Transacciones.Venta;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionJSONVenta {

    public GestionJSONVenta() {
    }

    public static void listaVentaToArchivo(HashMap<String, Venta> listaVentas, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaVenta(listaVentas));
    }

    public static void ventaToArchivo(Venta venta, String nombreArchivo){

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(serializarVenta(venta));

        OperacionesLectoEscritura.grabar(nombreArchivo, jsonArray);
    }

    public static JSONArray serializarListaVenta(HashMap<String, Venta> listaVentas) {

        JSONArray jsonArray = new JSONArray();

        try {

            for (Venta v : listaVentas.values()) {
                jsonArray.put(serializarVenta(v));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }


    public static JSONObject serializarVenta(Venta v) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idVenta", v.getIdVenta());
            jsonObject.put("total", v.getTotal());
            jsonObject.put("fechaVenta", v.getFecha().toString());
            jsonObject.put("activo", v.isActivo());

            JSONArray detallesArray = new JSONArray();
            for (DetalleVenta d : v.getDetalleVenta()) {
                detallesArray.put(GestionJSONDetalleVenta.serializarDetalleVenta(d));
            }

            jsonObject.put("detallesVenta", detallesArray);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static HashMap<String, Venta> archivoVentaToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        HashMap<String, Venta> listaVenta = null;

        try {
            listaVenta = deserializarListaVenta(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaVenta;
    }


    public static HashMap<String, Venta> deserializarListaVenta(JSONArray jsonArray){

        HashMap<String, Venta> lista = new HashMap<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Venta v = deserializarVenta(jsonArray.getJSONObject(i));
                lista.put(v.getIdVenta(), v);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }


    public static Venta deserializarVenta(JSONObject jsonObject) {

        Venta ventaLeida = new Venta();

        try {
            ventaLeida.setIdVenta(jsonObject.getString("idVenta"));
            ventaLeida.setTotal(jsonObject.getDouble("total"));

            String fechaString = jsonObject.getString("fechaVenta");
            ventaLeida.setFecha(LocalDate.parse(fechaString));

            ventaLeida.setActivo(jsonObject.getBoolean("activo"));

            JSONArray jsonDetalles = jsonObject.getJSONArray("detallesVenta");
            List<DetalleVenta> listaDetalles = new ArrayList<>();

            for (int i = 0; i < jsonDetalles.length(); i++) {
                JSONObject jsonDetalle = jsonDetalles.getJSONObject(i);
                DetalleVenta detalle = GestionJSONDetalleVenta.deserializarDetalleVenta(jsonDetalle);
                listaDetalles.add(detalle);
            }

            ventaLeida.setDetalleVenta(listaDetalles);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ventaLeida;
    }
}

