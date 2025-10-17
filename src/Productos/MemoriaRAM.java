package Productos;

import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class MemoriaRAM extends Producto{

    private int capacidad;
    private String tipoDeMemoria;
    private String frecuencia;

    public MemoriaRAM(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, int capacidad, String tipoDeMemoria, String frecuencia) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.capacidad = capacidad;
        this.tipoDeMemoria = tipoDeMemoria;
        this.frecuencia = frecuencia;
    }

    public MemoriaRAM(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, int capacidad, String tipoDeMemoria, String frecuencia) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.capacidad = capacidad;
        this.tipoDeMemoria = tipoDeMemoria;
        this.frecuencia = frecuencia;
    }

    public MemoriaRAM() {

    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipoDeMemoria() {
        return tipoDeMemoria;
    }

    public void setTipoDeMemoria(String tipoDeMemoria) {
        this.tipoDeMemoria = tipoDeMemoria;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MemoriaRAM that)) return false;
        if (!super.equals(o)) return false;
        return capacidad == that.capacidad && Objects.equals(tipoDeMemoria, that.tipoDeMemoria) && Objects.equals(frecuencia, that.frecuencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacidad, tipoDeMemoria, frecuencia);
    }

    @Override
    public String toString() {
        return "MemoriaRAM {" +
                "capacidad = " + capacidad +
                ", tipoDeMemoria = '" + tipoDeMemoria + '\'' +
                ", frecuencia = '" + frecuencia + '\'' +
                " " + super.toString();
    }
}
