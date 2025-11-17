package Transacciones.Detalles;

import Productos.Producto;

/**
 * Representa un detalle individual dentro de una venta.
 * Cada detalle corresponde a un producto, su cantidad y su subtotal.
 */

public class DetalleVenta extends DetalleTransaccion {

    public DetalleVenta(Producto producto, int cantidad) {
        super(producto, cantidad);
    }

    public DetalleVenta() {
    }
}
