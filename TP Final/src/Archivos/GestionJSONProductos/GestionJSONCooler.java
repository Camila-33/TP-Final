package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Productos.Cooler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionJSONCooler {

    public GestionJSONCooler() {
    }

    public static void listaCoolerToArchivo(ArrayList<Cooler> listaCooler, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaCooler(listaCooler));
    }

    public static JSONArray serializarListaCooler(ArrayList<Cooler> listaCooler){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (Cooler c : listaCooler){
                jsonArray.put(serializarCooler(c));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarCooler(Cooler c) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", c.getNombre());
            jsonObject.put("descripcion", c.getDescripcion());
            jsonObject.put("codigo", c.getCodigo());
            jsonObject.put("precio", c.getPrecio());
            jsonObject.put("peso", c.getPeso());
            jsonObject.put("nivel de ruido", c.getNivelRuidoMaximo());
            jsonObject.put("velocidad", c.getVelocidad());
            jsonObject.put("dimension", c.getDimension());
            jsonObject.put("marca", c.getMarca());
            jsonObject.put("disponible", c.isActivo());
            jsonObject.put("stock", c.getStock());
            jsonObject.put("garantia en meses", c.getGarantiaMeses());
            jsonObject.put("numero de serie", c.getNumeroDeSerie());
            jsonObject.put("fecha de ingreso", c.getFechaIngreso());
            jsonObject.put("categoria", c.getCategoria());
            jsonObject.put("subcategoria", c.getSubCategoria());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<Cooler> archivoCoolerToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Cooler> listaCooler = null;

        try {
            listaCooler = deserializarListaCooler(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaCooler;
    }


    public static ArrayList<Cooler> deserializarListaCooler(JSONArray jsonArray){

        ArrayList<Cooler> listaCooler = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                Cooler c = deserializarCooler(jsonArray.getJSONObject(i));
                listaCooler.add(c);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaCooler;
    }

    public static Cooler deserializarCooler(JSONObject jsonObject) {

        Cooler coolerLeido = new Cooler();

        try {
            coolerLeido.setNombre(jsonObject.getString("nombre"));
            coolerLeido.setDescripcion(jsonObject.getString("descripcion"));
            coolerLeido.setCodigo(jsonObject.getString("codigo"));
            coolerLeido.setPrecio(jsonObject.getDouble("precio"));
            coolerLeido.setPeso(jsonObject.getDouble("peso"));
            coolerLeido.setNivelRuidoMaximo(jsonObject.getString("nivel de ruido"));
            coolerLeido.setVelocidad(jsonObject.getString("velocidad"));
            coolerLeido.setDimension(jsonObject.getString("dimension"));
            coolerLeido.setMarca(jsonObject.getString("marca"));
            coolerLeido.setActivo(jsonObject.getBoolean("disponible"));
            coolerLeido.setStock(jsonObject.getInt("stock"));
            coolerLeido.setGarantiaMeses(jsonObject.getInt("garantia en meses"));
            coolerLeido.setNumeroDeSerie(jsonObject.getString("numero de serie"));

            String fechaStr = jsonObject.getString("fecha de ingreso");
            LocalDate fechaIngreso = LocalDate.parse(fechaStr);
            coolerLeido.setFechaIngreso(fechaIngreso);

            String catStr = jsonObject.getString("categoria");
            TipoCategoria categoria = TipoCategoria.valueOf(catStr.toUpperCase());
            coolerLeido.setCategoria(categoria);

            String subCatStr = jsonObject.getString("subcategoria");
            TipoSubCategoria subCategoria = TipoSubCategoria.valueOf(subCatStr.toUpperCase());
            coolerLeido.setSubCategoria(subCategoria);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return coolerLeido;
    }
}
