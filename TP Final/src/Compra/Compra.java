package Compra;

import Productos.Producto;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compra {

    //Atributos
    private String idPedido;
    private double total;
    private LocalDate fechaCompra;
    private Proveedor proveedor;
    private List<DetalleCompra> detallesCompra;

    //Constructores

    public Compra() {
    }

    public Compra(Proveedor proveedor) {
        this.idPedido = String.valueOf((long)(Math.random() * 900000) + 100000);
        this.total = 0;
        this.fechaCompra = LocalDate.now();
        this.proveedor = proveedor;
        this.detallesCompra = new ArrayList<>();
    }

    //Setters y getters

    public String getIdPedido() {return idPedido;}

    public LocalDate getFechaCompra() {return fechaCompra;}

    public Proveedor getProveedor() {return proveedor;}

    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}

    public List<DetalleCompra> getDetallesCompra() {return detallesCompra;}

    //Métodos

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
