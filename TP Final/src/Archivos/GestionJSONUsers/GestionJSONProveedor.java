package Archivos.GestionJSONUsers;

import Archivos.OperacionesLectoEscritura;
import Productos.Producto;
import Users.Proveedor;
import Users.UsuarioSistema.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class GestionJSONProveedor {

    public GestionJSONProveedor() {
    }

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

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
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
            /*

            for (Map.Entry<String, Producto> entry : proveedor.getProductosSuministrados().entrySet()) {
                Producto producto = entry.getValue();
                JSONObject productoJson;

                if (producto instanceof Procesador) {
                    productoJson = GestorProcesador.serializar((Procesador) producto);
                } else if (producto instanceof TarjetaGrafica) {
                    productoJson = GestorTarjetaGrafica.serializar((TarjetaGrafica) producto);
                }
                // etc para cada tipo
                listaJson.put(productoJson);
            }

             */

            jsonObject.put("Productos", listaJson);

        } catch (JSONException ex) {
            ex.printStackTrace();
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

        Proveedor proveedorLeido = new Proveedor();

        try {


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return proveedorLeido;
    }
}
