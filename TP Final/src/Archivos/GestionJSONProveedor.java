package Archivos;

import Productos.Producto;
import Users.Proveedor;
import Users.UsuarioSistema.Empleado;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Map;

public class GestionJSONProveedor {

    public GestionJSONProveedor() {
    }

    public static void listaProveedorToArchivo(ArrayList<Proveedor> listaProveedores, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaProveedores(listaProveedores));
    }

    public static JSONArray serializarListaProveedores(ArrayList<Proveedor> listaProveedores){

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

            for (Map.Entry<String, Producto> entry : p.getProductosSuministrados().entrySet()) {
                String clave = entry.getKey();
                Producto producto = entry.getValue();

                JSONObject productoJson = new JSONObject();
                productoJson.put("Código", clave);  // la key del map
                productoJson.put("Nombre", producto.getNombre());
                productoJson.put("Precio", producto.getPrecio());
                productoJson.put("Stock", producto.getStock());
                // agregar otros campos de Producto según necesites

                listaJson.put(productoJson);
            }

            jsonObject.put("Productos", listaJson);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<Empleado> archivoEmpleadoToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Empleado> listaEmpleado = null;

        try {
            listaEmpleado = deserializarListaEmpleado(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaEmpleado;
    }


    public static ArrayList<Empleado> deserializarListaEmpleado(JSONArray jsonArray){

        ArrayList<Empleado> lista = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Empleado e = deserializarEmpleado(jsonArray.getJSONObject(i));
                lista.add(e);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    public static Empleado deserializarEmpleado(JSONObject jsonObject) {

        Empleado empleadoLeido = new Empleado();

        try {
            empleadoLeido.setIdUsuario(jsonObject.getString("id"));
            empleadoLeido.setUserName(jsonObject.getString("username"));
            empleadoLeido.setContrasena(jsonObject.getString("contrasenia"));
            empleadoLeido.setNombre(jsonObject.getString("nombre"));
            empleadoLeido.setApellido(jsonObject.getString("apellido"));
            empleadoLeido.setDni(jsonObject.getString("dni"));
            empleadoLeido.setTelefono(jsonObject.getString("telefono"));
            empleadoLeido.setDireccion(jsonObject.getString("direccion"));
            empleadoLeido.setEmail(jsonObject.getString("email"));
            empleadoLeido.setActivo(jsonObject.getBoolean("estado"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return empleadoLeido;
    }
}
