package Transacciones;

import Transacciones.Detalles.DetalleCompra;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private static int contador = 0;
    private String idPedido;
    private double total;
    private LocalDate fechaCompra;
    private Proveedor proveedor;
    private boolean activo;
    private List<DetalleCompra> detallesCompra;


    public Compra() {
    }

    public Compra(Proveedor proveedor) {
        this.idPedido = String.valueOf(++contador);
        this.total = getTotal();
        this.fechaCompra = LocalDate.now();
        this.proveedor = proveedor;
        this.activo = true;
        this.detallesCompra = new ArrayList<>();
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleCompra> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    public double getTotal(){

        total = 0;
        for (DetalleCompra p : detallesCompra)
            total += p.getSubtotal();

        return total;
    }

    public void agregarDetalleCompra(DetalleCompra detalleCompra){
        detallesCompra.add(detalleCompra);
    }

    public void borrarDetalle(DetalleCompra detalle) {
        detallesCompra.remove(detalle);
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
}
