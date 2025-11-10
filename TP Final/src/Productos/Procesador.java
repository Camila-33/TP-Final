package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class Procesador extends Producto{

    private String frecuenciaDeReloj;
    private int numeroDeNucleos;

    public Procesador(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, TipoCategoria categoria, TipoSubCategoria subCategoria, String frecuenciaDeReloj, int numeroDeNucleos) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, subCategoria);
        this.frecuenciaDeReloj = frecuenciaDeReloj;
        this.numeroDeNucleos = numeroDeNucleos;
    }

    public Procesador(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String frecuenciaDeReloj, int numeroDeNucleos) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria);
        this.frecuenciaDeReloj = frecuenciaDeReloj;
        this.numeroDeNucleos = numeroDeNucleos;
    }

    public Procesador() {

    }

    public String getFrecuenciaDeReloj() {
        return frecuenciaDeReloj;
    }

    public void setFrecuenciaDeReloj(String frecuenciaDeReloj) {
        this.frecuenciaDeReloj = frecuenciaDeReloj;
    }

    public int getNumeroDeNucleos() {
        return numeroDeNucleos;
    }

    public void setNumeroDeNucleos(int numeroDeNucleos) {
        this.numeroDeNucleos = numeroDeNucleos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Procesador that)) return false;
        if (!super.equals(o)) return false;
        return numeroDeNucleos == that.numeroDeNucleos && Objects.equals(frecuenciaDeReloj, that.frecuenciaDeReloj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), frecuenciaDeReloj, numeroDeNucleos);
    }

    @Override
    public String toString() {
        return "Procesador {" +
                "frecuenciaDeReloj = '" + frecuenciaDeReloj + '\'' +
                ", numeroDeNucleos = " + numeroDeNucleos +
                " " + super.toString();
    }
}
