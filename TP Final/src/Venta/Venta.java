package Venta;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa una venta realizada en el sistema.
 * Contiene los detalles de los productos vendidos, el cliente, y el estado de la venta.
 */

public class Venta {

    private static int contador = 0;
    private String idVenta;
    private List<DetalleVenta> detalleVenta;
    private double total;
    private LocalDate fecha;
    private boolean activo;

    public Venta(List<DetalleVenta> detalleVenta, double total, LocalDate fecha, boolean activo) {
        this.idVenta = String.valueOf(++contador);
        this.detalleVenta = detalleVenta;
        this.total = total;
        this.fecha = fecha;
        this.activo = activo;
    }

    public Venta() {
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalleVenta.add(detalle);
    }

    public void quitarDetalle(DetalleVenta detalle) {
        detalleVenta.remove(detalle);
    }

    @Override
    public String toString() {
        return "Venta {" +
                "idVenta = '" + idVenta + '\'' +
                ", detalleVenta = " + detalleVenta +
                ", total = " + total +
                ", fecha = " + fecha +
                ", activo = " + activo +
                '}';
    }
}
