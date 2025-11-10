package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Productos.Almacenamiento;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionJSONPlacaDeVideo {

    public GestionJSONPlacaDeVideo() {
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
            jsonObject.put("descripcion", a.getDescripcion());
            jsonObject.put("codigo", a.getCodigo());
            jsonObject.put("precio", a.getPrecio());
            jsonObject.put("peso", a.getPeso());
            jsonObject.put("capacidad", a.getCapacidad());
            jsonObject.put("velocidad", a.getVelocidad());
            jsonObject.put("dimension", a.getDimension());
            jsonObject.put("marca", a.getMarca());
            jsonObject.put("disponible", a.isActivo());
            jsonObject.put("stock", a.getStock());
            jsonObject.put("garantia en meses", a.getGarantiaMeses());
            jsonObject.put("numero de serie", a.getNumeroDeSerie());
            jsonObject.put("fecha de ingreso", a.getFechaIngreso());
            jsonObject.put("categoria", a.getCategoria());
            jsonObject.put("subcategoria", a.getSubCategoria());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<Almacenamiento> archivoAlmacenamientoToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Almacenamiento> listaAlmacenamiento = null;

        try {
            listaAlmacenamiento = deserializarListaAlmacenamiento(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaAlmacenamiento;
    }


    public static ArrayList<Almacenamiento> deserializarListaAlmacenamiento(JSONArray jsonArray){

        ArrayList<Almacenamiento> listaAlmacenamiento = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Almacenamiento a = deserializarAlmacenamiento(jsonArray.getJSONObject(i));
                listaAlmacenamiento.add(a);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaAlmacenamiento;
    }

    public static Almacenamiento deserializarAlmacenamiento(JSONObject jsonObject) {

        Almacenamiento almacenamientoLeido = new Almacenamiento();

        try {
            almacenamientoLeido.setNombre(jsonObject.getString("nombre"));
            almacenamientoLeido.setDescripcion(jsonObject.getString("descripcion"));
            almacenamientoLeido.setCodigo(jsonObject.getString("codigo"));
            almacenamientoLeido.setPrecio(jsonObject.getDouble("precio"));
            almacenamientoLeido.setPeso(jsonObject.getDouble("peso"));
            almacenamientoLeido.setCapacidad(jsonObject.getString("capacidad"));
            almacenamientoLeido.setVelocidad(jsonObject.getString("velocidad"));
            almacenamientoLeido.setDimension(jsonObject.getString("dimension"));
            almacenamientoLeido.setMarca(jsonObject.getString("marca"));
            almacenamientoLeido.setActivo(jsonObject.getBoolean("disponible"));
            almacenamientoLeido.setStock(jsonObject.getInt("stock"));
            almacenamientoLeido.setGarantiaMeses(jsonObject.getInt("garantia en meses"));
            almacenamientoLeido.setNumeroDeSerie(jsonObject.getString("numero de serie"));

            String fechaStr = jsonObject.getString("fecha de ingreso");
            LocalDate fechaIngreso = LocalDate.parse(fechaStr);
            almacenamientoLeido.setFechaIngreso(fechaIngreso);

            String catStr = jsonObject.getString("categoria");
            TipoCategoria categoria = TipoCategoria.valueOf(catStr.toUpperCase());
            almacenamientoLeido.setCategoria(categoria);

            String subCatStr = jsonObject.getString("subcategoria");
            TipoSubCategoria subCategoria = TipoSubCategoria.valueOf(subCatStr.toUpperCase());
            almacenamientoLeido.setSubCategoria(subCategoria);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return almacenamientoLeido;
    }
}
