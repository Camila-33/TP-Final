package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

import java.util.Objects;

public class PlacaMadre extends Producto{

    private String tipoMemoria;
    private int cantidadSlotMemoria;
    private boolean backConnect;
    private boolean botonBios;

    public PlacaMadre(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, String tipoMemoria, int cantidadSlotMemoria, boolean backConnect, boolean botonBios) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
        this.tipoMemoria = tipoMemoria;
        this.cantidadSlotMemoria = cantidadSlotMemoria;
        this.backConnect = backConnect;
        this.botonBios = botonBios;
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

    public boolean isBotonBios() {
        return botonBios;
    }

    public void setBotonBios(boolean botonBios) {
        this.botonBios = botonBios;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaMadre that)) return false;
        if (!super.equals(o)) return false;
        return cantidadSlotMemoria == that.cantidadSlotMemoria && backConnect == that.backConnect && botonBios == that.botonBios && Objects.equals(tipoMemoria, that.tipoMemoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipoMemoria, cantidadSlotMemoria, backConnect, botonBios);
    }

    @Override
    public String toString() {
        return "PlacaMadre {" +
                "tipoMemoria = '" + tipoMemoria + '\'' +
                ", cantidadSlotMemoria = " + cantidadSlotMemoria +
                ", backConnect = " + backConnect +
                ", bobtonBios = " + botonBios +
                " " + super.toString();
    }
}
