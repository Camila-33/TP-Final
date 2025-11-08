package Archivos;

import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Empleado;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class GestionJSONEmpleado {

    public GestionJSONEmpleado() {
    }

    public static void listaEmpleadoToArchivo(ArrayList<Empleado> listaEmpleados, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaEmpleados(listaEmpleados));
    }

    public static JSONArray serializarListaEmpleados(ArrayList<Empleado> listaEmpleados){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Empleado e : listaEmpleados){
                jsonArray.put(serializarEmpleado(e));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarEmpleado(Empleado e) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", e.getIdUsuario());
            jsonObject.put("username", e.getUserName());
            jsonObject.put("contrasenia", e.getContrasena());
            jsonObject.put("nombre", e.getNombre());
            jsonObject.put("apellido", e.getApellido());
            jsonObject.put("dni", e.getDni());
            jsonObject.put("telefono", e.getTelefono());
            jsonObject.put("direccion", e.getDireccion());
            jsonObject.put("email", e.getEmail());
            jsonObject.put("activo", e.isActivo());

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
