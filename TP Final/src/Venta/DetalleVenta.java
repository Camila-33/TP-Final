package Venta;

import Productos.Producto;

public class DetalleVenta {

    private Producto producto;
    private int cantidad;
    private double precioUnitario; // Se congela el precio del momento de la venta

    public DetalleVenta(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
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

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "producto = " + producto +
                ", cantidad = " + cantidad +
                ", precioUnitario = " + precioUnitario +
                '}';
    }
}
