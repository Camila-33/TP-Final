package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

import java.util.Objects;

public class PlacaDeVideo extends  Producto{

    private String GPU;
    private String VRAM;
    private String frecuenciaNucleo;
    private String anchoDeBanda;

    public PlacaDeVideo(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String GPU, String VRAM, String frecuenciaNucleo, String anchoDeBanda) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria);
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
    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("GPU: " + GPU);
        System.out.println("VRAM: " + VRAM);
        System.out.println("Frecuencia del núcleo: " + frecuenciaNucleo);
        System.out.println("Ancho de banda: " + anchoDeBanda);
        System.out.println("-------------------------\n");
    }
}
