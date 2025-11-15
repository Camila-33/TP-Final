package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

import java.util.Objects;

public class Gabinete extends Producto{

    private boolean conVentana;
    private String color;
    private String ancho;
    private String alto;
    private String profundidad;

    public Gabinete(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, boolean conVentana, String color, String ancho, String alto, String profundidad) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
        this.conVentana = conVentana;
        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
    }

    public Gabinete() {

    }

    public boolean isConVentana() {
        return conVentana;
    }

    public void setConVentana(boolean conVentana) {
        this.conVentana = conVentana;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Gabinete gabinete)) return false;
        if (!super.equals(o)) return false;
        return conVentana == gabinete.conVentana && Objects.equals(color, gabinete.color) && Objects.equals(ancho, gabinete.ancho) && Objects.equals(alto, gabinete.alto) && Objects.equals(profundidad, gabinete.profundidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), conVentana, color, ancho, alto, profundidad);
    }

    @Override
    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("Con ventana: " + (conVentana ? "Sí" : "No"));
        System.out.println("Color: " + color);
        System.out.println("Ancho: " + ancho);
        System.out.println("Alto: " + alto);
        System.out.println("Profundidad: " + profundidad);
        System.out.println("-------------------------\n");
    }
}
