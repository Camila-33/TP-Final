package Transacciones.Detalles;

import Productos.Producto;

public class DetalleCompra extends DetalleTransaccion {

    public DetalleCompra(Producto producto, int cantidad) {
        super(producto, cantidad);
    }

    public DetalleCompra() {
    }
}
