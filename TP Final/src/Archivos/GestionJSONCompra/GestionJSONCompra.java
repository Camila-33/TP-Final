package Archivos.GestionJSONCompra;

import Archivos.GestionJSONUsers.GestionJSONProveedor;
import Archivos.OperacionesLectoEscritura;
import Transacciones.Compra;
import Transacciones.Detalles.DetalleCompra;
import Users.Proveedor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionJSONCompra {

    public GestionJSONCompra() {
    }

    public static void listaCompraToArchivo(HashMap<String, Compra> listaCompras, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaCompra(listaCompras));
    }


    public static JSONArray serializarListaCompra(HashMap<String, Compra> listaCompras) {

        JSONArray jsonArray = new JSONArray();

        try {

            for (Compra c : listaCompras.values()) {
                jsonArray.put(serializarCompra(c));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }


    public static JSONObject serializarCompra(Compra c) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idPedido", c.getIdPedido());
            jsonObject.put("total", c.getTotal());
            jsonObject.put("fechaCompra", c.getFechaCompra().toString());
            jsonObject.put("activo", c.isActivo());

            jsonObject.put("proveedor", GestionJSONProveedor.serializarProveedorParcial(c.getProveedor()));

            JSONArray detallesArray = new JSONArray();
            for (DetalleCompra d : c.getDetallesCompra()) {
                detallesArray.put(GestionJSONDetalleCompra.serializarDetalleCompra(d));
            }

            jsonObject.put("detallesCompra", detallesArray);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static HashMap<String, Compra> archivoCompraToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        HashMap<String, Compra> listaCompra = null;

        try {
            listaCompra = deserializarListaCompra(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaCompra;
    }


    public static HashMap<String, Compra> deserializarListaCompra(JSONArray jsonArray){

        HashMap<String, Compra> lista = new HashMap<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Compra c = deserializarCompra(jsonArray.getJSONObject(i));
                lista.put(c.getIdPedido(), c);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }


    public static Compra deserializarCompra(JSONObject jsonObject) {

        Compra compraLeida = new Compra();

        try {
            compraLeida.setIdPedido(jsonObject.getString("idPedido"));
            compraLeida.setTotal(jsonObject.getDouble("total"));

            String fechaString = jsonObject.getString("fechaCompra");
            compraLeida.setFechaCompra(LocalDate.parse(fechaString));

            compraLeida.setActivo(jsonObject.getBoolean("activo"));

            JSONObject jsonProveedor = jsonObject.getJSONObject("proveedor");
            Proveedor proveedor = GestionJSONProveedor.deserializarProveedorParcial(jsonProveedor);
            compraLeida.setProveedor(proveedor);

            JSONArray jsonDetalles = jsonObject.getJSONArray("detallesCompra");
            List<DetalleCompra> listaDetalles = new ArrayList<>();

            for (int i = 0; i < jsonDetalles.length(); i++) {
                JSONObject jsonDetalle = jsonDetalles.getJSONObject(i);
                DetalleCompra detalle = GestionJSONDetalleCompra.deserializarDetalleCompra(jsonDetalle);
                listaDetalles.add(detalle);
            }

            compraLeida.setDetallesCompra(listaDetalles);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return compraLeida;
    }

}

