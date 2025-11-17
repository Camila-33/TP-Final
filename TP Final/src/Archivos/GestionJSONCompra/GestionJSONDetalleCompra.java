package Archivos.GestionJSONCompra;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Archivos.OperacionesLectoEscritura;
import Transacciones.Detalles.DetalleCompra;
import Productos.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestionJSONDetalleCompra {

    public GestionJSONDetalleCompra() {
    }


    public static void detalleCompraToArchivo(DetalleCompra detalleCompra, String nombreArchivo){

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(serializarDetalleCompra(detalleCompra));

        OperacionesLectoEscritura.grabar(nombreArchivo, jsonArray);
    }


    public static JSONObject serializarDetalleCompra(DetalleCompra d) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idDetalle", d.getIdDetalle());
            jsonObject.put("cantidad", d.getCantidad());
            jsonObject.put("precioUnitario", d.getPrecioUnitario());

            jsonObject.put("productos", GestionJSONProducto.serializarProducto(d.getProducto()));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static DetalleCompra deserializarDetalleCompra(JSONObject jsonObject) {

        DetalleCompra detalleCompraLeido = new DetalleCompra();

        try {
            detalleCompraLeido.setIdDetalle(jsonObject.getString("idDetalle"));
            detalleCompraLeido.setCantidad(jsonObject.getInt("cantidad"));
            detalleCompraLeido.setPrecioUnitario(jsonObject.getDouble("precioUnitario"));

            JSONObject jsonProducto = jsonObject.getJSONObject("productos");
            Producto producto = GestionJSONProducto.deserializarProducto(jsonProducto);

            detalleCompraLeido.setProducto(producto);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detalleCompraLeido;
    }
}

