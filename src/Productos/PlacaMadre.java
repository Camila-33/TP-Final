package Productos;

import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class PlacaMadre extends Producto{

    private String tipoMemoria;
    private int cantidadSlotMemoria;
    private boolean backConnect;
    private boolean bobtonBios;

    public PlacaMadre(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, String tipoMemoria, int cantidadSlotMemoria, boolean backConnect, boolean bobtonBios) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.tipoMemoria = tipoMemoria;
        this.cantidadSlotMemoria = cantidadSlotMemoria;
        this.backConnect = backConnect;
        this.bobtonBios = bobtonBios;
    }

    public PlacaMadre(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, String tipoMemoria, int cantidadSlotMemoria, boolean backConnect, boolean bobtonBios) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.tipoMemoria = tipoMemoria;
        this.cantidadSlotMemoria = cantidadSlotMemoria;
        this.backConnect = backConnect;
        this.bobtonBios = bobtonBios;
    }

    public PlacaMadre() {

    }

    public String getTipoMemoria() {
        return tipoMemoria;
    }

    public void setTipoMemoria(String tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
    }

    public int getCantidadSlotMemoria() {
        return cantidadSlotMemoria;
    }

    public void setCantidadSlotMemoria(int cantidadSlotMemoria) {
        this.cantidadSlotMemoria = cantidadSlotMemoria;
    }

    public boolean isBackConnect() {
        return backConnect;
    }

    public void setBackConnect(boolean backConnect) {
        this.backConnect = backConnect;
    }

    public boolean isBobtonBios() {
        return bobtonBios;
    }

    public void setBobtonBios(boolean bobtonBios) {
        this.bobtonBios = bobtonBios;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaMadre that)) return false;
        if (!super.equals(o)) return false;
        return cantidadSlotMemoria == that.cantidadSlotMemoria && backConnect == that.backConnect && bobtonBios == that.bobtonBios && Objects.equals(tipoMemoria, that.tipoMemoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipoMemoria, cantidadSlotMemoria, backConnect, bobtonBios);
    }

    @Override
    public String toString() {
        return "PlacaMadre {" +
                "tipoMemoria = '" + tipoMemoria + '\'' +
                ", cantidadSlotMemoria = " + cantidadSlotMemoria +
                ", backConnect = " + backConnect +
                ", bobtonBios = " + bobtonBios +
                " " + super.toString();
    }
}
