package Productos;

import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class Procesador extends Producto{

    private double frecuenciaDeReloj;
    private int numeroDeNucleos;
    private String soporteDeMemoria;

    public Procesador(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, double frecuenciaDeReloj, int numeroDeNucleos, String soporteDeMemoria) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.frecuenciaDeReloj = frecuenciaDeReloj;
        this.numeroDeNucleos = numeroDeNucleos;
        this.soporteDeMemoria = soporteDeMemoria;
    }

    public Procesador(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, double frecuenciaDeReloj, int numeroDeNucleos, String soporteDeMemoria) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.frecuenciaDeReloj = frecuenciaDeReloj;
        this.numeroDeNucleos = numeroDeNucleos;
        this.soporteDeMemoria = soporteDeMemoria;
    }

    public Procesador() {

    }

    public double getFrecuenciaDeReloj() {
        return frecuenciaDeReloj;
    }

    public void setFrecuenciaDeReloj(double frecuenciaDeReloj) {
        this.frecuenciaDeReloj = frecuenciaDeReloj;
    }

    public int getNumeroDeNucleos() {
        return numeroDeNucleos;
    }

    public void setNumeroDeNucleos(int numeroDeNucleos) {
        this.numeroDeNucleos = numeroDeNucleos;
    }

    public String getSoporteDeMemoria() {
        return soporteDeMemoria;
    }

    public void setSoporteDeMemoria(String soporteDeMemoria) {
        this.soporteDeMemoria = soporteDeMemoria;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Procesador that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(frecuenciaDeReloj, that.frecuenciaDeReloj) == 0 && numeroDeNucleos == that.numeroDeNucleos && Objects.equals(soporteDeMemoria, that.soporteDeMemoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), frecuenciaDeReloj, numeroDeNucleos, soporteDeMemoria);
    }

    @Override
    public String toString() {
        return "Procesador {" +
                "frecuenciaDeReloj = " + frecuenciaDeReloj +
                ", numeroDeNucleos = " + numeroDeNucleos +
                ", soporteDeMemoria = '" + soporteDeMemoria + '\'' +
                " " + super.toString();
    }
}
