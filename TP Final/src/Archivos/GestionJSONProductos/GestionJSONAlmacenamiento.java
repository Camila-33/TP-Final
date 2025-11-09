package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Productos.Almacenamiento;
import Users.UsuarioSistema.Administrador;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashSet;

public class GestionJSONAlmacenamiento {

    public GestionJSONAlmacenamiento() {
    }

    public static void listaAlmacenamientoToArchivo(ArrayList<Almacenamiento> listaAlmacenamiento, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaAlmacenamiento(listaAlmacenamiento));
    }

    public static JSONArray serializarListaAlmacenamiento(ArrayList<Almacenamiento> listaAlmacenamiento){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Almacenamiento a : listaAlmacenamiento){
                jsonArray.put(serializarAlmacenamiento(a));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarAlmacenamiento(Almacenamiento a) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", a.getNombre());
            jsonObject.put("descripción", a.getDescripcion());
            jsonObject.put("código", a.getCodigo());
            jsonObject.put("precio", a.getPrecio());
            jsonObject.put("peso", a.getPeso());
            jsonObject.put("dimension", a.getDimension());
            jsonObject.put("marca", a.getMarca());
            jsonObject.put("nombre", a.getNombre());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static HashSet<Administrador> archivoAdminToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        HashSet<Administrador> listaAdmins = null;

        try {
            listaAdmins = deserializarListaAdmins(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaAdmins;
    }


    public static HashSet<Administrador> deserializarListaAdmins(JSONArray jsonArray){

        HashSet<Administrador> lista = new HashSet<>();

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
