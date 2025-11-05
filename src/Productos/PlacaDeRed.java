package Productos;

import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class PlacaDeRed extends Producto{

    private String dispoditivosCompatibles;

    public PlacaDeRed(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, String dispoditivosCompatibles) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.dispoditivosCompatibles = dispoditivosCompatibles;
    }

    public PlacaDeRed(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, String dispoditivosCompatibles) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.dispoditivosCompatibles = dispoditivosCompatibles;
    }

    public PlacaDeRed() {

    }

    public String getDispoditivosCompatibles() {
        return dispoditivosCompatibles;
    }

    public void setDispoditivosCompatibles(String dispoditivosCompatibles) {
        this.dispoditivosCompatibles = dispoditivosCompatibles;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaDeRed that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(dispoditivosCompatibles, that.dispoditivosCompatibles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dispoditivosCompatibles);
    }

    @Override
    public String toString() {
        return "PlacaDeRed {" +
                "dispoditivosCompatibles = '" + dispoditivosCompatibles + '\'' +
                " " + super.toString();
    }
}
