package Compra;

import Productos.Producto;

public class DetalleCompra {

    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public DetalleCompra(){
    }

    public DetalleCompra(Producto producto, int cantidad) {

        if (cantidad <= 0 && precioUnitario <= 0) {
            throw new IllegalArgumentException("Cantidad y precio deben ser mayores que cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public Producto getProducto() {return producto;}

    public void setProducto(Producto producto) {this.producto = producto;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public double getPrecioUnitario() {return precioUnitario;}

    public void setPrecioUnitario(double precioUnitario) {this.precioUnitario = precioUnitario;}


    public double getSubtotal(){
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return "DetalleCompra[" +
                "PRODUCTO= " + producto +
                ", CANTIDAD= " + cantidad +
                ", PRECIO UNITARIO= " + precioUnitario +
                ", SUBTOTAL= $" + getSubtotal() +
                ']';
    }
}
