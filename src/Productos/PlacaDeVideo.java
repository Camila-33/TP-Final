package Productos;

import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class PlacaDeVideo extends  Producto{

    private String GPU;
    private String VRAM;
    private String frecuenciaNucleo;
    private String anchoDeBanda;

    public PlacaDeVideo(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, String GPU, String VRAM, String frecuenciaNucleo, String anchoDeBanda) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.GPU = GPU;
        this.VRAM = VRAM;
        this.frecuenciaNucleo = frecuenciaNucleo;
        this.anchoDeBanda = anchoDeBanda;
    }

    public PlacaDeVideo(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, String GPU, String VRAM, String frecuenciaNucleo, String anchoDeBanda) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.GPU = GPU;
        this.VRAM = VRAM;
        this.frecuenciaNucleo = frecuenciaNucleo;
        this.anchoDeBanda = anchoDeBanda;
    }

    public PlacaDeVideo() {

    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getVRAM() {
        return VRAM;
    }

    public void setVRAM(String VRAM) {
        this.VRAM = VRAM;
    }

    public String getFrecuenciaNucleo() {
        return frecuenciaNucleo;
    }

    public void setFrecuenciaNucleo(String frecuenciaNucleo) {
        this.frecuenciaNucleo = frecuenciaNucleo;
    }

    public String getAnchoDeBanda() {
        return anchoDeBanda;
    }

    public void setAnchoDeBanda(String anchoDeBanda) {
        this.anchoDeBanda = anchoDeBanda;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaDeVideo that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(GPU, that.GPU) && Objects.equals(VRAM, that.VRAM) && Objects.equals(frecuenciaNucleo, that.frecuenciaNucleo) && Objects.equals(anchoDeBanda, that.anchoDeBanda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), GPU, VRAM, frecuenciaNucleo, anchoDeBanda);
    }

    @Override
    public String toString() {
        return "PlacaDeVideo {" +
                "GPU = '" + GPU + '\'' +
                ", VRAM = '" + VRAM + '\'' +
                ", frecuenciaNucleo = '" + frecuenciaNucleo + '\'' +
                ", anchoDeBanda = '" + anchoDeBanda + '\'' +
                " " + super.toString();
    }
}
