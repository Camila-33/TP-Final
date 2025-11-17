package Archivos.GestionJSONProductos;

import Archivos.OperacionesLectoEscritura;
import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;
import Productos.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestionJSONProducto {

    public static void listaProductosToArchivo(HashMap<String, Producto> mapaProductos, String nombreArchivo) {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String, Producto> entry : mapaProductos.entrySet()) {
            Producto p = entry.getValue();
            jsonArray.put(serializarProducto(p));
        }

        OperacionesLectoEscritura.grabar(nombreArchivo, jsonArray);
    }


    public static JSONObject serializarProducto(Producto p) {

        JSONObject json = new JSONObject();

        json.put("tipo", p.getClass().getSimpleName());

        json.put("nombre", p.getNombre());
        json.put("descripcion", p.getDescripcion());
        json.put("codigo", p.getCodigo());
        json.put("precio", p.getPrecio());
        json.put("peso", p.getPeso());
        json.put("dimension", p.getDimension());
        json.put("marca", p.getMarca());
        json.put("activo", p.isActivo());
        json.put("stock", p.getStock());
        json.put("garantiaMeses", p.getGarantiaMeses());

        if (p.getIdProveedores() != null) {
            json.put("idProveedores", new JSONArray(p.getIdProveedores()));
        } else {
            json.put("idProveedores", new JSONArray());
        }

        json.put("numeroDeSerie", p.getNumeroDeSerie());
        json.put("fechaIngreso", p.getFechaIngreso().toString());
        json.put("categoria", p.getCategoria().name());
        json.put("subCategoria", p.getSubCategoria().name());


        switch (p) {
            case Almacenamiento a -> {
                json.put("capacidad", a.getCapacidad());
                json.put("velocidad", a.getVelocidad());
            }
            case Cooler c -> {
                json.put("velocidad", c.getVelocidad());
                json.put("nivelRuidoMaximo", c.getNivelRuidoMaximo());
            }
            case FuenteDePoder fp -> {
                json.put("potencia", fp.getPotencia());
                json.put("tipoCertificacion", fp.getTipoCertificacion().name());
            }
            case Gabinete g -> {
                json.put("conVentana", g.isConVentana());
                json.put("color", g.getColor());
                json.put("ancho", g.getAncho());
                json.put("alto", g.getAlto());
                json.put("profundidad", g.getProfundidad());
            }
            case MemoriaRAM mr -> {
                json.put("capacidad", mr.getCapacidad());
                json.put("tipoDeMemoria", mr.getTipoDeMemoria());
                json.put("frecuencia", mr.getFrecuencia());
            }
            case PlacaDeRed pr -> json.put("dispositivosCompatibles", pr.getDispoditivosCompatibles());
            case PlacaDeVideo pv -> {
                json.put("GPU", pv.getGPU());
                json.put("VRAM", pv.getVRAM());
                json.put("frecuenciaNucleo", pv.getFrecuenciaNucleo());
                json.put("anchoDeBanda", pv.getAnchoDeBanda());
            }
            case PlacaMadre pm -> {
                json.put("tipoMemoria", pm.getTipoMemoria());
                json.put("cantidadSlotMemoria", pm.getCantidadSlotMemoria());
                json.put("backConnect", pm.isBackConnect());
                json.put("botonBios", pm.isBotonBios());
            }
            case Procesador pr -> {
                json.put("frecuenciaDeReloj", pr.getFrecuenciaDeReloj());
                json.put("numeroDeNucleos", pr.getNumeroDeNucleos());
            }
            default -> {
            }
        }

        return json;
    }

    public static JSONObject serializarProductoParcial(Producto p) {

        JSONObject json = new JSONObject();

        json.put("tipo", p.getClass().getSimpleName());

        json.put("codigo", p.getCodigo());
        json.put("nombre", p.getNombre());
        json.put("precio", p.getPrecio());

        return json;
    }

    public static JSONObject serializarProductoParcialProveedor(Producto p) {

        JSONObject json = new JSONObject();

        json.put("tipo", p.getClass().getSimpleName());

        json.put("codigo", p.getCodigo());
        json.put("nombre", p.getNombre());

        return json;
    }

    public static HashMap<String, Producto> archivoProductosToLista(String nombreArchivo) {

        JSONTokener tokener = OperacionesLectoEscritura.leer(nombreArchivo);
        HashMap<String, Producto> mapaProductos = new HashMap<>();

        try {
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Producto producto = deserializarProducto(json);

                if (producto != null) {
                    mapaProductos.put(producto.getCodigo(), producto);
                } else {
                    System.err.println("Producto inválido omitido al deserializar archivo: " + nombreArchivo);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mapaProductos;
    }


    public static Producto deserializarProducto(JSONObject json) {

        try {
            String tipo = json.optString("tipo");
            Producto p = getProducto(tipo);

            p.setNombre(json.getString("nombre"));
            p.setDescripcion(json.getString("descripcion"));
            p.setCodigo(json.getString("codigo"));
            p.setPrecio(json.getDouble("precio"));
            p.setPeso(json.getDouble("peso"));
            p.setDimension(json.getString("dimension"));
            p.setMarca(json.getString("marca"));
            p.setActivo(json.getBoolean("activo"));
            p.setStock(json.getInt("stock"));
            p.setGarantiaMeses(json.getInt("garantiaMeses"));

            JSONArray jsonArray = json.optJSONArray("idProveedores");

            if (jsonArray != null) {
                if (p.getIdProveedores() == null) {
                    p.setIdProveedores(new ArrayList<>());
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    String id = jsonArray.getString(i);
                    if (!p.getIdProveedores().contains(id)) {
                        p.getIdProveedores().add(id);
                    }
                }
            }

            p.setNumeroDeSerie(json.getString("numeroDeSerie"));
            p.setFechaIngreso(LocalDate.parse(json.getString("fechaIngreso")));
            p.setCategoria(TipoCategoria.valueOf(json.getString("categoria").toUpperCase()));
            p.setSubCategoria(TipoSubCategoria.valueOf(json.getString("subCategoria").toUpperCase()));


            if (p instanceof Almacenamiento a) {
                a.setCapacidad(json.getString("capacidad"));
                a.setVelocidad(json.getString("velocidad"));

            } else if (p instanceof Cooler c) {
                c.setVelocidad(json.getString("velocidad"));
                c.setNivelRuidoMaximo(json.getString("nivelRuidoMaximo"));

            } else if (p instanceof FuenteDePoder fp) {
                fp.setPotencia(json.getString("potencia"));
                String certStr = json.getString("tipoCertificacion");
                fp.setTipoCertificacion(TipoCertificacion.valueOf(certStr.toUpperCase()));

            } else if (p instanceof Gabinete g) {
                g.setConVentana(json.getBoolean("conVentana"));
                g.setColor(json.getString("color"));
                g.setAncho(json.getString("ancho"));
                g.setAlto(json.getString("alto"));
                g.setProfundidad(json.getString("profundidad"));

            } else if (p instanceof MemoriaRAM mr) {
                mr.setCapacidad(json.getInt("capacidad"));
                mr.setTipoDeMemoria(json.getString("tipoDeMemoria"));
                mr.setFrecuencia(json.getString("frecuencia"));

            } else if (p instanceof PlacaDeRed pr) {
                pr.setDispoditivosCompatibles(json.getString("dispositivosCompatibles"));

            } else if (p instanceof PlacaDeVideo pv) {
                pv.setGPU(json.getString("GPU"));
                pv.setVRAM(json.getString("VRAM"));
                pv.setFrecuenciaNucleo(json.getString("frecuenciaNucleo"));
                pv.setAnchoDeBanda(json.getString("anchoDeBanda"));

            } else if (p instanceof PlacaMadre pm) {
                pm.setTipoMemoria(json.getString("tipoMemoria"));
                pm.setCantidadSlotMemoria(json.getInt("cantidadSlotMemoria"));
                pm.setBackConnect(json.getBoolean("backConnect"));
                pm.setBotonBios(json.getBoolean("botonBios"));

            } else if (p instanceof Procesador pr) {
                pr.setFrecuenciaDeReloj(json.getString("frecuenciaDeReloj"));
                pr.setNumeroDeNucleos(json.getInt("numeroDeNucleos"));
            }

            return p;

        }catch (IllegalArgumentException e){
            System.err.println("Error al crear producto: " + e.getMessage());
        }

        return null;
    }

    public static Producto deserializarProductoParcial(JSONObject json) {
        try {
            String tipo = json.optString("tipo");
            Producto p = getProducto(tipo);

            p.setCodigo(json.getString("codigo"));
            p.setNombre(json.getString("nombre"));
            p.setPrecio(json.getDouble("precio"));

            return p;

        } catch (Exception e) {
            System.err.println("Error al deserializar parcialmente: " + e.getMessage());
        }

        return null;
    }

    public static Producto deserializarProductoParcialProveedor(JSONObject json) {
        try {
            String tipo = json.optString("tipo");
            Producto p = getProducto(tipo);

            p.setCodigo(json.getString("codigo"));
            p.setNombre(json.getString("nombre"));

            return p;

        } catch (Exception e) {
            System.err.println("Error al deserializar parcialmente: " + e.getMessage());
        }

        return null;
    }


    private static Producto getProducto(String tipo) {

        return switch (tipo) {
            case "Almacenamiento" -> new Almacenamiento();
            case "Cooler" -> new Cooler();
            case "FuenteDePoder" -> new FuenteDePoder();
            case "Gabinete" -> new Gabinete();
            case "MemoriaRAM" -> new MemoriaRAM();
            case "PlacaDeRed" -> new PlacaDeRed();
            case "PlacaDeVideo" -> new PlacaDeVideo();
            case "PlacaMadre" -> new PlacaMadre();
            case "Procesador" -> new Procesador();
            default -> throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
        };
    }
}

