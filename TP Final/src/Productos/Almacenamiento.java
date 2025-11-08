package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class Almacenamiento extends Producto{

    private String capacidad;
    private String velocidad;

    public Almacenamiento(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, TipoCategoria categoria, TipoSubCategoria subCategoria, Proveedor proveedor, String capacidad, String velocidad) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, subCategoria, proveedor);
        this.capacidad = capacidad;
        this.velocidad = velocidad;
    }

    public Almacenamiento(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, Proveedor proveedor, String capacidad, String velocidad) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, proveedor);
        this.capacidad = capacidad;
        this.velocidad = velocidad;
    }

    public Almacenamiento() {

    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Almacenamiento that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(capacidad, that.capacidad) && Objects.equals(velocidad, that.velocidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacidad, velocidad);
    }

    @Override
    public String toString() {
        return "Almacenamiento {" +
                "capacidad = '" + capacidad + '\'' +
                ", velocidad = '" + velocidad + '\'' +
                " " + super.toString();
    }
}
