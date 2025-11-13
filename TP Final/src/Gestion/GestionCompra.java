package Gestion;

import Compra.Compra;
import Productos.Producto;

import java.util.ArrayList;
import java.util.List;

public class GestionCompra {

    List<Compra> compras;

    public GestionCompra() {
        this.compras = new ArrayList<>();
    }

    public void agregarDetalle(DetalleCompra detalle) {
        detallesCompra.add(detalle);
    }

    public void borrarDetalle(DetalleCompra detalle) {
        detallesCompra.remove(detalle);
    }

    public DetalleCompra buscarCompraPorProducto(Producto producto) {
        for (DetalleCompra d : detallesCompra) {
            if (d.getProducto().equals(producto)) {
                return d;
            }
        }
        return null;
    }

    public void actualizarStockProducto() {
        for (DetalleCompra detalle : detallesCompra) {
            Producto p = detalle.getProducto();
            p.setStock(p.getStock() + detalle.getCantidad());
        }
        System.out.println("Compra registrada y stock actualizado.");
    }

    public String mostrarDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Detalles de la Compra ").append(idPedido).append(" ---\n");
        for (DetalleCompra d : detallesCompra) {
            sb.append(d.toString()).append("\n");
        }
        sb.append("TOTAL = $").append(getTotal()).append("\n");
        sb.append("FECHA = ").append(fechaCompra).append("\n");
        sb.append("PROVEEDOR = ").append(proveedor);
        return sb.toString();
    }

    public double getTotal(){
        total = 0;
        for (DetalleCompra p : detallesCompra)
            total += p.getSubtotal();
        return total;
    }


}
