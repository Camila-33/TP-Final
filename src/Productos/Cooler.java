package Productos;

import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class Cooler extends Producto{

    private String velocidad;
    private String nivelRuidoMaximo;

    public Cooler(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, String velocidad, String nivelRuidoMaximo) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.velocidad = velocidad;
        this.nivelRuidoMaximo = nivelRuidoMaximo;
    }

    public Cooler(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, String velocidad, String nivelRuidoMaximo) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.velocidad = velocidad;
        this.nivelRuidoMaximo = nivelRuidoMaximo;
    }

    public Cooler() {

    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getNivelRuidoMaximo() {
        return nivelRuidoMaximo;
    }

    public void setNivelRuidoMaximo(String nivelRuidoMaximo) {
        this.nivelRuidoMaximo = nivelRuidoMaximo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cooler cooler)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(velocidad, cooler.velocidad) && Objects.equals(nivelRuidoMaximo, cooler.nivelRuidoMaximo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), velocidad, nivelRuidoMaximo);
    }

    @Override
    public String toString() {
        return "Cooler {" +
                "velocidad = '" + velocidad + '\'' +
                ", nivelRuidoMaximo = '" + nivelRuidoMaximo + '\'' +
                " " + super.toString();
    }
}
