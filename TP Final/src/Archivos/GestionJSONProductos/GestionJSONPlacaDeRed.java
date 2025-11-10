package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Productos.PlacaDeRed;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionJSONPlacaDeRed {

    public GestionJSONPlacaDeRed() {
    }

    public static void listaPlacaDeRedToArchivo(ArrayList<PlacaDeRed> listaPlacaDeRed, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaPlacaDeRed(listaPlacaDeRed));
    }

    public static JSONArray serializarListaPlacaDeRed(ArrayList<PlacaDeRed> listaPlacaDeRed){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (PlacaDeRed p : listaPlacaDeRed){
                jsonArray.put(serializarPlacaDeRed(p));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarPlacaDeRed(PlacaDeRed p) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", p.getNombre());
            jsonObject.put("descripcion", p.getDescripcion());
            jsonObject.put("codigo", p.getCodigo());
            jsonObject.put("precio", p.getPrecio());
            jsonObject.put("peso", p.getPeso());
            jsonObject.put("dispositivos compatibles", p.getDispoditivosCompatibles());
            jsonObject.put("dimension", p.getDimension());
            jsonObject.put("marca", p.getMarca());
            jsonObject.put("disponible", p.isActivo());
            jsonObject.put("stock", p.getStock());
            jsonObject.put("garantia en meses", p.getGarantiaMeses());
            jsonObject.put("numero de serie", p.getNumeroDeSerie());
            jsonObject.put("fecha de ingreso", p.getFechaIngreso());
            jsonObject.put("categoria", p.getCategoria());
            jsonObject.put("subcategoria", p.getSubCategoria());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<PlacaDeRed> archivoPlacaDeRedToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<PlacaDeRed> listaPlacaDeRed = null;

        try {
            listaPlacaDeRed = deserializarListaPlacaDeRed(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaPlacaDeRed;
    }


    public static ArrayList<PlacaDeRed> deserializarListaPlacaDeRed(JSONArray jsonArray){

        ArrayList<PlacaDeRed> listaPlacaDeRed = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                PlacaDeRed p = deserializarPlacaDeRed(jsonArray.getJSONObject(i));
                listaPlacaDeRed.add(p);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaPlacaDeRed;
    }

    public static PlacaDeRed deserializarPlacaDeRed(JSONObject jsonObject) {

        PlacaDeRed placaDeRedLeida = new PlacaDeRed();

        try {
            placaDeRedLeida.setNombre(jsonObject.getString("nombre"));
            placaDeRedLeida.setDescripcion(jsonObject.getString("descripcion"));
            placaDeRedLeida.setCodigo(jsonObject.getString("codigo"));
            placaDeRedLeida.setPrecio(jsonObject.getDouble("precio"));
            placaDeRedLeida.setPeso(jsonObject.getDouble("peso"));
            placaDeRedLeida.setDispoditivosCompatibles(jsonObject.getString("dispositivos compatibles"));
            placaDeRedLeida.setDimension(jsonObject.getString("dimension"));
            placaDeRedLeida.setMarca(jsonObject.getString("marca"));
            placaDeRedLeida.setActivo(jsonObject.getBoolean("disponible"));
            placaDeRedLeida.setStock(jsonObject.getInt("stock"));
            placaDeRedLeida.setGarantiaMeses(jsonObject.getInt("garantia en meses"));
            placaDeRedLeida.setNumeroDeSerie(jsonObject.getString("numero de serie"));

            String fechaStr = jsonObject.getString("fecha de ingreso");
            LocalDate fechaIngreso = LocalDate.parse(fechaStr);
            placaDeRedLeida.setFechaIngreso(fechaIngreso);

            String catStr = jsonObject.getString("categoria");
            TipoCategoria categoria = TipoCategoria.valueOf(catStr.toUpperCase());
            placaDeRedLeida.setCategoria(categoria);

            String subCatStr = jsonObject.getString("subcategoria");
            TipoSubCategoria subCategoria = TipoSubCategoria.valueOf(subCatStr.toUpperCase());
            placaDeRedLeida.setSubCategoria(subCategoria);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return placaDeRedLeida;
    }
}
