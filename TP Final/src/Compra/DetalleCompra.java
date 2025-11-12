package Compra;

import Productos.Producto;

public class DetalleCompra {

    //Atributos
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    //Constructores
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

    //Setters y getters
    public Producto getProducto() {return producto;}

    public void setProducto(Producto producto) {this.producto = producto;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public double getPrecioUnitario() {return precioUnitario;}

    public void setPrecioUnitario(double precioUnitario) {this.precioUnitario = precioUnitario;}

    //Metodos

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
