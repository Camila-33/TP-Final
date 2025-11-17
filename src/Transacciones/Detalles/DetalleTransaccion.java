package Transacciones.Detalles;

import IngresoDeDatos.InputHelper;
import Productos.Producto;

import java.util.Objects;

public abstract class DetalleTransaccion {

    protected String idDetalle;
    protected Producto producto;
    protected int cantidad;
    protected double precioUnitario;

    public DetalleTransaccion(Producto producto, int cantidad) {
        this.idDetalle = InputHelper.generarCodigoUnico();
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public DetalleTransaccion() {
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal(){
        return cantidad * precioUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DetalleTransaccion that)) return false;
        return cantidad == that.cantidad && Double.compare(precioUnitario, that.precioUnitario) == 0 && Objects.equals(idDetalle, that.idDetalle) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalle, producto, cantidad, precioUnitario);
    }

    @Override
    public String toString() {
        return "DetalleTransaccion {" +
                "idDetalle = '" + idDetalle + '\'' +
                ", producto = " + producto +
                ", cantidad = " + cantidad +
                ", precioUnitario = " + precioUnitario +
                '}';
    }
}
