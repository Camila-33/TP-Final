package Archivos.GestionJSONVenta;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Archivos.OperacionesLectoEscritura;
import Productos.Producto;
import Transacciones.Detalles.DetalleVenta;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestionJSONDetalleVenta {

    public GestionJSONDetalleVenta() {
    }


    public static void detalleVentaToArchivo(DetalleVenta detalleVenta, String nombreArchivo){

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(serializarDetalleVenta(detalleVenta));

        OperacionesLectoEscritura.grabar(nombreArchivo, jsonArray);
    }


    public static JSONObject serializarDetalleVenta(DetalleVenta d) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idDetalle", d.getIdDetalle());
            jsonObject.put("cantidad", d.getCantidad());
            jsonObject.put("precioUnitario", d.getPrecioUnitario());

            JSONObject productoJson = GestionJSONProducto.serializarProductoParcial(d.getProducto());

            jsonObject.put("producto", productoJson);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static DetalleVenta deserializarDetalleVenta(JSONObject jsonObject) {

        DetalleVenta detalleVentaLeido = new DetalleVenta();

        try {
            detalleVentaLeido.setIdDetalle(jsonObject.getString("idDetalle"));
            detalleVentaLeido.setCantidad(jsonObject.getInt("cantidad"));
            detalleVentaLeido.setPrecioUnitario(jsonObject.getDouble("precioUnitario"));

            JSONObject jsonProducto = jsonObject.getJSONObject("producto");
            Producto producto = GestionJSONProducto.deserializarProductoParcial(jsonProducto);

            detalleVentaLeido.setProducto(producto);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detalleVentaLeido;
    }

}

