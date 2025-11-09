package Archivos.GestionJSONUsers;

import Archivos.OperacionesLectoEscritura;
import Users.UsuarioSistema.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashSet;

public class GestionJSONUsuario {

    public GestionJSONUsuario() {
    }

    public static void listaUsuarioToArchivo(HashSet<Usuario> listaUsuarios, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaUsuarios(listaUsuarios));
    }

    public static JSONArray serializarListaUsuarios(HashSet<Usuario> listaUsuarios){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Usuario e : listaUsuarios){
                jsonArray.put(serializarUsuario(e));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarUsuario(Usuario e) {

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


    public static HashSet<Usuario> archivoUsuarioToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        HashSet<Usuario> listaUsuario = null;

        try {
            listaUsuario = deserializarListaUsuario(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaUsuario;
    }


    public static HashSet<Usuario> deserializarListaUsuario(JSONArray jsonArray){

        HashSet<Usuario> lista = new HashSet<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Usuario e = deserializarUsuario(jsonArray.getJSONObject(i));
                lista.add(e);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    public static Usuario deserializarUsuario(JSONObject jsonObject) {

        Usuario usuarioLeido = new Usuario();

        try {
            usuarioLeido.setIdUsuario(jsonObject.getString("id"));
            usuarioLeido.setUserName(jsonObject.getString("username"));
            usuarioLeido.setContrasena(jsonObject.getString("contrasenia"));
            usuarioLeido.setNombre(jsonObject.getString("nombre"));
            usuarioLeido.setApellido(jsonObject.getString("apellido"));
            usuarioLeido.setDni(jsonObject.getString("dni"));
            usuarioLeido.setTelefono(jsonObject.getString("telefono"));
            usuarioLeido.setDireccion(jsonObject.getString("direccion"));
            usuarioLeido.setEmail(jsonObject.getString("email"));
            usuarioLeido.setActivo(jsonObject.getBoolean("estado"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usuarioLeido;
    }
}
