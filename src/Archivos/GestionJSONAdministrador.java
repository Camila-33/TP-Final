package Archivos;

import Users.UsuarioSistema.Administrador;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class GestionJSONAdministrador {

    public GestionJSONAdministrador() {
    }

    public static void listaAdminsToArchivo(ArrayList<Administrador> listaAdmins, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaAdministradores(listaAdmins));
    }

    public static JSONArray serializarListaAdministradores(ArrayList<Administrador> listaAdmins){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Administrador a : listaAdmins){
                jsonArray.put(serializarAdmin(a));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarAdmin(Administrador e) {

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


    public static ArrayList<Administrador> archivoAdminToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Administrador> listaAdmins = null;

        try {
            listaAdmins = deserializarListaAdmins(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaAdmins;
    }


    public static ArrayList<Administrador> deserializarListaAdmins(JSONArray jsonArray){

        ArrayList<Administrador> lista = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Administrador a = deserializarAdmin(jsonArray.getJSONObject(i));
                lista.add(a);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    public static Administrador deserializarAdmin(JSONObject jsonObject) {

        Administrador adminLeido = new Administrador();

        try {
            adminLeido.setIdUsuario(jsonObject.getString("id"));
            adminLeido.setUserName(jsonObject.getString("username"));
            adminLeido.setContrasena(jsonObject.getString("contrasenia"));
            adminLeido.setNombre(jsonObject.getString("nombre"));
            adminLeido.setApellido(jsonObject.getString("apellido"));
            adminLeido.setDni(jsonObject.getString("dni"));
            adminLeido.setTelefono(jsonObject.getString("telefono"));
            adminLeido.setDireccion(jsonObject.getString("direccion"));
            adminLeido.setEmail(jsonObject.getString("email"));
            adminLeido.setActivo(jsonObject.getBoolean("estado"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return adminLeido;
    }

}

