package GestorVentas;

import Productos.Producto;

/**
 * Representa un detalle individual dentro de una venta.
 * Cada detalle corresponde a un producto, su cantidad y su subtotal.
 */
public class DetalleVenta {

    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(Producto producto, int cantidad, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
