package Productos;

<<<<<<< HEAD:src/Productos/PlacaMadre.java
import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
=======
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

>>>>>>> camila-dev:TP Final/src/Productos/PlacaMadre.java
import java.util.Objects;

public class PlacaMadre extends Producto{

    private String tipoMemoria;
    private int cantidadSlotMemoria;
    private boolean backConnect;
<<<<<<< HEAD:src/Productos/PlacaMadre.java
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
=======
    private boolean botonBios;

    public PlacaMadre(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, String tipoMemoria, int cantidadSlotMemoria, boolean backConnect, boolean botonBios) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
        this.tipoMemoria = tipoMemoria;
        this.cantidadSlotMemoria = cantidadSlotMemoria;
        this.backConnect = backConnect;
        this.botonBios = botonBios;
>>>>>>> camila-dev:TP Final/src/Productos/PlacaMadre.java
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

<<<<<<< HEAD:src/Productos/PlacaMadre.java
    public boolean isBobtonBios() {
        return bobtonBios;
    }

    public void setBobtonBios(boolean bobtonBios) {
        this.bobtonBios = bobtonBios;
=======
    public boolean isBotonBios() {
        return botonBios;
    }

    public void setBotonBios(boolean botonBios) {
        this.botonBios = botonBios;
>>>>>>> camila-dev:TP Final/src/Productos/PlacaMadre.java
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaMadre that)) return false;
        if (!super.equals(o)) return false;
<<<<<<< HEAD:src/Productos/PlacaMadre.java
        return cantidadSlotMemoria == that.cantidadSlotMemoria && backConnect == that.backConnect && bobtonBios == that.bobtonBios && Objects.equals(tipoMemoria, that.tipoMemoria);
=======
        return cantidadSlotMemoria == that.cantidadSlotMemoria && backConnect == that.backConnect && botonBios == that.botonBios && Objects.equals(tipoMemoria, that.tipoMemoria);
>>>>>>> camila-dev:TP Final/src/Productos/PlacaMadre.java
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD:src/Productos/PlacaMadre.java
        return Objects.hash(super.hashCode(), tipoMemoria, cantidadSlotMemoria, backConnect, bobtonBios);
=======
        return Objects.hash(super.hashCode(), tipoMemoria, cantidadSlotMemoria, backConnect, botonBios);
>>>>>>> camila-dev:TP Final/src/Productos/PlacaMadre.java
    }

    @Override
    public String toString() {
        return "PlacaMadre {" +
                "tipoMemoria = '" + tipoMemoria + '\'' +
                ", cantidadSlotMemoria = " + cantidadSlotMemoria +
                ", backConnect = " + backConnect +
<<<<<<< HEAD:src/Productos/PlacaMadre.java
                ", bobtonBios = " + bobtonBios +
=======
                ", bobtonBios = " + botonBios +
>>>>>>> camila-dev:TP Final/src/Productos/PlacaMadre.java
                " " + super.toString();
    }
}
