package GestorVentas;

import Productos.Producto;

/**
 * Representa un detalle individual dentro de una venta.
 * Cada detalle corresponde a un producto, su cantidad y su precio unitario.
 */
public class DetalleVenta {

    // Producto incluido en la venta
    private Producto producto;

    // Cantidad de unidades vendidas del producto
    private int cantidad;

    // Precio unitario del producto al momento de la venta
    private double precioUnitario;

    /**
     * Constructor del detalle de venta.
     *
     * @param producto      Producto vendido
     * @param cantidad      Cantidad de unidades vendidas
     * @param precioUnitario Precio unitario del producto
     */
    public DetalleVenta(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // ================== Getters y Setters ==================

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

    // ================== Métodos funcionales ==================

    /**
     * Calcula el subtotal del detalle multiplicando la cantidad por el precio unitario.
     *
     * @return subtotal del detalle de venta
     */
    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    /**
     * Devuelve una representación legible del detalle.
     * Ejemplo: "Coca-Cola x2 - $600.0"
     */
    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " - $" + calcularSubtotal();
    }
}
