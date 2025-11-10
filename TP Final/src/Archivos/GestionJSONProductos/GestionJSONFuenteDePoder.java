package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;
import Productos.FuenteDePoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionJSONFuenteDePoder {

    public GestionJSONFuenteDePoder() {
    }

    public static void listaFuenteDePoderToArchivo(ArrayList<FuenteDePoder> listaFuenteDePoder, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaFuenteDePoder(listaFuenteDePoder));
    }

    public static JSONArray serializarListaFuenteDePoder(ArrayList<FuenteDePoder> listaFuenteDePoder){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (FuenteDePoder f : listaFuenteDePoder){
                jsonArray.put(serializarFuenteDePoder(f));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarFuenteDePoder(FuenteDePoder f) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", f.getNombre());
            jsonObject.put("descripcion", f.getDescripcion());
            jsonObject.put("codigo", f.getCodigo());
            jsonObject.put("precio", f.getPrecio());
            jsonObject.put("peso", f.getPeso());
            jsonObject.put("tipo de certificacion", f.getTipoCertificacion());
            jsonObject.put("potencia", f.getPotencia());
            jsonObject.put("dimension", f.getDimension());
            jsonObject.put("marca", f.getMarca());
            jsonObject.put("disponible", f.isActivo());
            jsonObject.put("stock", f.getStock());
            jsonObject.put("garantia en meses", f.getGarantiaMeses());
            jsonObject.put("numero de serie", f.getNumeroDeSerie());
            jsonObject.put("fecha de ingreso", f.getFechaIngreso());
            jsonObject.put("categoria", f.getCategoria());
            jsonObject.put("subcategoria", f.getSubCategoria());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<FuenteDePoder> archivoFuenteDePoderToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<FuenteDePoder> listaFuenteDePoder = null;

        try {
            listaFuenteDePoder = deserializarListaFuenteDePoder(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaFuenteDePoder;
    }


    public static ArrayList<FuenteDePoder> deserializarListaFuenteDePoder(JSONArray jsonArray){

        ArrayList<FuenteDePoder> listaFuenteDePoder = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                FuenteDePoder f = deserializarFuenteDePoder(jsonArray.getJSONObject(i));
                listaFuenteDePoder.add(f);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaFuenteDePoder;
    }

    public static FuenteDePoder deserializarFuenteDePoder(JSONObject jsonObject) {

        FuenteDePoder fuenteDePoderLeida = new FuenteDePoder();

        try {
            fuenteDePoderLeida.setNombre(jsonObject.getString("nombre"));
            fuenteDePoderLeida.setDescripcion(jsonObject.getString("descripcion"));
            fuenteDePoderLeida.setCodigo(jsonObject.getString("codigo"));
            fuenteDePoderLeida.setPrecio(jsonObject.getDouble("precio"));
            fuenteDePoderLeida.setPeso(jsonObject.getDouble("peso"));
            fuenteDePoderLeida.setPotencia(jsonObject.getString("potencia"));
            fuenteDePoderLeida.setDimension(jsonObject.getString("dimension"));
            fuenteDePoderLeida.setMarca(jsonObject.getString("marca"));
            fuenteDePoderLeida.setActivo(jsonObject.getBoolean("disponible"));
            fuenteDePoderLeida.setStock(jsonObject.getInt("stock"));
            fuenteDePoderLeida.setGarantiaMeses(jsonObject.getInt("garantia en meses"));
            fuenteDePoderLeida.setNumeroDeSerie(jsonObject.getString("numero de serie"));

            String fechaStr = jsonObject.getString("fecha de ingreso");
            LocalDate fechaIngreso = LocalDate.parse(fechaStr);
            fuenteDePoderLeida.setFechaIngreso(fechaIngreso);

            String certificacionStr = jsonObject.getString("tipo de certificacion");
            TipoCertificacion certificacion = TipoCertificacion.valueOf(certificacionStr.toUpperCase());
            fuenteDePoderLeida.setTipoCertificacion(certificacion);

            String catStr = jsonObject.getString("categoria");
            TipoCategoria categoria = TipoCategoria.valueOf(catStr.toUpperCase());
            fuenteDePoderLeida.setCategoria(categoria);

            String subCatStr = jsonObject.getString("subcategoria");
            TipoSubCategoria subCategoria = TipoSubCategoria.valueOf(subCatStr.toUpperCase());
            fuenteDePoderLeida.setSubCategoria(subCategoria);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fuenteDePoderLeida;
    }
}
