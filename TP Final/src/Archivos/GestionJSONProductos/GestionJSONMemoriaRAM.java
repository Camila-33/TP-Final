package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Productos.MemoriaRAM;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionJSONMemoriaRAM {

    public GestionJSONMemoriaRAM() {
    }

    public static void listaMemoriaRAMToArchivo(ArrayList<MemoriaRAM> listaMemoriaRAM, String nombreArchivo){
        OperacionesLectoEscritura.grabar(nombreArchivo, serializarListaMemoriaRAM(listaMemoriaRAM));
    }

    public static JSONArray serializarListaMemoriaRAM(ArrayList<MemoriaRAM> listaMemoriaRAM){

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray();

            for (MemoriaRAM m : listaMemoriaRAM){
                jsonArray.put(serializarMemoriaRAM(m));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject serializarMemoriaRAM(MemoriaRAM m) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", m.getNombre());
            jsonObject.put("descripcion", m.getDescripcion());
            jsonObject.put("codigo", m.getCodigo());
            jsonObject.put("precio", m.getPrecio());
            jsonObject.put("peso", m.getPeso());
            jsonObject.put("capacidad", m.getCapacidad());
            jsonObject.put("tipo de memoria", m.getTipoDeMemoria());
            jsonObject.put("frecuencia", m.getFrecuencia());
            jsonObject.put("dimension", m.getDimension());
            jsonObject.put("marca", m.getMarca());
            jsonObject.put("disponible", m.isActivo());
            jsonObject.put("stock", m.getStock());
            jsonObject.put("garantia en meses", m.getGarantiaMeses());
            jsonObject.put("numero de serie", m.getNumeroDeSerie());
            jsonObject.put("fecha de ingreso", m.getFechaIngreso());
            jsonObject.put("categoria", m.getCategoria());
            jsonObject.put("subcategoria", m.getSubCategoria());


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }


    public static ArrayList<MemoriaRAM> archivoMemoriaRAMToLista(String nombreArchivo){

        JSONTokener jsonTokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<MemoriaRAM> listaMemoriaRAM = null;

        try {
            listaMemoriaRAM = deserializarListaMemoriaRAM(new JSONArray(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaMemoriaRAM;
    }


    public static ArrayList<MemoriaRAM> deserializarListaMemoriaRAM(JSONArray jsonArray){

        ArrayList<MemoriaRAM> listaMemoriaRAM = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                MemoriaRAM m = deserializarMemoriaRAM(jsonArray.getJSONObject(i));
                listaMemoriaRAM.add(m);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaMemoriaRAM;
    }

    public static MemoriaRAM deserializarMemoriaRAM(JSONObject jsonObject) {

        MemoriaRAM memoriaRAMLeida = new MemoriaRAM();

        try {
            memoriaRAMLeida.setNombre(jsonObject.getString("nombre"));
            memoriaRAMLeida.setDescripcion(jsonObject.getString("descripcion"));
            memoriaRAMLeida.setCodigo(jsonObject.getString("codigo"));
            memoriaRAMLeida.setPrecio(jsonObject.getDouble("precio"));
            memoriaRAMLeida.setPeso(jsonObject.getDouble("peso"));
            memoriaRAMLeida.setCapacidad(jsonObject.getInt("capacidad"));
            memoriaRAMLeida.setTipoDeMemoria(jsonObject.getString("tipo de memoria"));
            memoriaRAMLeida.setFrecuencia(jsonObject.getString("frecuencia"));
            memoriaRAMLeida.setDimension(jsonObject.getString("dimension"));
            memoriaRAMLeida.setMarca(jsonObject.getString("marca"));
            memoriaRAMLeida.setActivo(jsonObject.getBoolean("disponible"));
            memoriaRAMLeida.setStock(jsonObject.getInt("stock"));
            memoriaRAMLeida.setGarantiaMeses(jsonObject.getInt("garantia en meses"));
            memoriaRAMLeida.setNumeroDeSerie(jsonObject.getString("numero de serie"));

            String fechaStr = jsonObject.getString("fecha de ingreso");
            LocalDate fechaIngreso = LocalDate.parse(fechaStr);
            memoriaRAMLeida.setFechaIngreso(fechaIngreso);

            String catStr = jsonObject.getString("categoria");
            TipoCategoria categoria = TipoCategoria.valueOf(catStr.toUpperCase());
            memoriaRAMLeida.setCategoria(categoria);

            String subCatStr = jsonObject.getString("subcategoria");
            TipoSubCategoria subCategoria = TipoSubCategoria.valueOf(subCatStr.toUpperCase());
            memoriaRAMLeida.setSubCategoria(subCategoria);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return memoriaRAMLeida;
    }
}
