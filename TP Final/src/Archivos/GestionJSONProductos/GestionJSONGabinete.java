package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Productos.Gabinete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionJSONGabinete {

    public GestionJSONGabinete() {
    }

    public static void listaAlmacenamientoToArchivo(ArrayList<Gabinete> listaGabinete, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaGabinete(listaGabinete));
    }

    public static JSONArray serializarListaGabinete(ArrayList<Gabinete> listaGabinete){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Gabinete g : listaGabinete){
                jsonArray.put(serializarGabinete(g));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarGabinete(Gabinete g) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", g.getNombre());
            jsonObject.put("descripcion", g.getDescripcion());
            jsonObject.put("codigo", g.getCodigo());
            jsonObject.put("precio", g.getPrecio());
            jsonObject.put("peso", g.getPeso());
            jsonObject.put("con ventana", g.isConVentana());
            jsonObject.put("color", g.getColor());
            jsonObject.put("ancho", g.getAncho());
            jsonObject.put("alto", g.getAlto());
            jsonObject.put("color", g.getColor());
            jsonObject.put("profundidad", g.getProfundidad());
            jsonObject.put("marca", g.getMarca());
            jsonObject.put("disponible", g.isActivo());
            jsonObject.put("stock", g.getStock());
            jsonObject.put("garantia en meses", g.getGarantiaMeses());
            jsonObject.put("numero de serie", g.getNumeroDeSerie());
            jsonObject.put("fecha de ingreso", g.getFechaIngreso());
            jsonObject.put("categoria", g.getCategoria());
            jsonObject.put("subcategoria", g.getSubCategoria());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<Gabinete> archivoGabineteToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Gabinete> listaGabinete = null;

        try {
            listaGabinete = deserializarListaGabinete(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaGabinete;
    }


    public static ArrayList<Gabinete> deserializarListaGabinete(JSONArray jsonArray){

        ArrayList<Gabinete> listaGabinete = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Gabinete g = deserializarGabinete(jsonArray.getJSONObject(i));
                listaGabinete.add(g);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaGabinete;
    }

    public static Gabinete deserializarGabinete(JSONObject jsonObject) {

        Gabinete gabineteLeido = new Gabinete();

        try {
            gabineteLeido.setNombre(jsonObject.getString("nombre"));
            gabineteLeido.setDescripcion(jsonObject.getString("descripcion"));
            gabineteLeido.setCodigo(jsonObject.getString("codigo"));
            gabineteLeido.setPrecio(jsonObject.getDouble("precio"));
            gabineteLeido.setPeso(jsonObject.getDouble("peso"));
            gabineteLeido.setConVentana(jsonObject.getBoolean("con ventana"));
            gabineteLeido.setColor(jsonObject.getString("color"));
            gabineteLeido.setAncho(jsonObject.getString("ancho"));
            gabineteLeido.setAlto(jsonObject.getString("alto"));
            gabineteLeido.setProfundidad(jsonObject.getString("profundidad"));
            gabineteLeido.setDimension(jsonObject.getString("dimension"));
            gabineteLeido.setMarca(jsonObject.getString("marca"));
            gabineteLeido.setActivo(jsonObject.getBoolean("disponible"));
            gabineteLeido.setStock(jsonObject.getInt("stock"));
            gabineteLeido.setGarantiaMeses(jsonObject.getInt("garantia en meses"));
            gabineteLeido.setNumeroDeSerie(jsonObject.getString("numero de serie"));

            String fechaStr = jsonObject.getString("fecha de ingreso");
            LocalDate fechaIngreso = LocalDate.parse(fechaStr);
            gabineteLeido.setFechaIngreso(fechaIngreso);

            String catStr = jsonObject.getString("categoria");
            TipoCategoria categoria = TipoCategoria.valueOf(catStr.toUpperCase());
            gabineteLeido.setCategoria(categoria);

            String subCatStr = jsonObject.getString("subcategoria");
            TipoSubCategoria subCategoria = TipoSubCategoria.valueOf(subCatStr.toUpperCase());
            gabineteLeido.setSubCategoria(subCategoria);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gabineteLeido;
    }
}
