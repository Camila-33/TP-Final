package Archivos.GestionJSONUsers;

import Archivos.GestionJSONProductos.*;
import Archivos.OperacionesLectoEscritura;
import Enums.TipoProveedor;
import Productos.*;
import Users.Proveedor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GestionJSONProveedor {

    public static void listaProveedorToArchivo(HashSet<Proveedor> listaProveedores, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaProveedores(listaProveedores));
    }

    public static JSONArray serializarListaProveedores(HashSet<Proveedor> listaProveedores){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Proveedor p : listaProveedores){
                jsonArray.put(serializarProveedor(p));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarProveedor(Proveedor p) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("ID", p.getIdProveedor());
            jsonObject.put("Nombre", p.getNombre());
            jsonObject.put("Apellido", p.getApellido());
            jsonObject.put("Email", p.getEmail());
            jsonObject.put("Teléfono", p.getTelefono());
            jsonObject.put("CUIT", p.getCuit());
            jsonObject.put("Estado", p.isActivo());
            jsonObject.put("Fecha de alta", p.getFechaAlta());
            jsonObject.put("Tipo proveedor", p.getTipoProveedor());

            JSONArray listaJson = new JSONArray();

            for (Map.Entry<String, Producto> entry : p.getProductosSuministrados().entrySet()) {
                Producto producto = entry.getValue();

                JSONObject productoJson = GestionJSONProducto.serializarProducto(producto); //o productoToArchivo?

                listaJson.put(productoJson);
            }

            jsonObject.put("Productos", listaJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


    public static HashSet<Proveedor> archivoProveedorToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        HashSet<Proveedor> listaProveedor = null;

        try {
            listaProveedor = deserializarListaProveedor(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaProveedor;
    }


    public static HashSet<Proveedor> deserializarListaProveedor(JSONArray jsonArray){

        HashSet<Proveedor> lista = new HashSet<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Proveedor p = deserializarProveedor(jsonArray.getJSONObject(i));
                lista.add(p);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }


    public static Proveedor deserializarProveedor(JSONObject jsonObject) {

        Proveedor proveedor = new Proveedor();

        try {
            proveedor.setIdProveedor(jsonObject.getString("ID"));
            proveedor.setNombre(jsonObject.getString("Nombre"));
            proveedor.setApellido(jsonObject.getString("Apellido"));
            proveedor.setEmail(jsonObject.getString("Email"));
            proveedor.setTelefono(jsonObject.getString("Teléfono"));
            proveedor.setCuit(jsonObject.getString("CUIT"));
            proveedor.setActivo(jsonObject.getBoolean("Estado"));

            String fechaStr = jsonObject.getString("Fecha de alta");
            proveedor.setFechaAlta(LocalDate.parse(fechaStr));

            String tipoStr = jsonObject.getString("Tipo proveedor").toUpperCase();
            proveedor.setTipoProveedor(TipoProveedor.valueOf(tipoStr));

            JSONArray productosArray = jsonObject.getJSONArray("Productos");
            HashMap<String, Producto> productos = new HashMap<>();

            for (int i = 0; i < productosArray.length(); i++) {
                JSONObject productoJson = productosArray.getJSONObject(i);

                Producto producto = GestionJSONProducto.deserializarProducto(productoJson);

                if (producto != null) {
                    productos.put(producto.getCodigo(), producto);
                } else {
                    System.err.println("Producto inválido omitido al deserializar proveedor");
                }
            }

            proveedor.setProductosSuministrados(productos);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return proveedor;
    }

}

