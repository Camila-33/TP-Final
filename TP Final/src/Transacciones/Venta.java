package Transacciones;

import Transacciones.Detalles.DetalleVenta;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public Venta(List<DetalleVenta> detalleVenta) {
        this.idVenta = String.valueOf(++contador);
        this.detalleVenta = (detalleVenta != null) ? detalleVenta : new ArrayList<>();
        this.fecha = LocalDate.now();
        this.activo = true;
        recalcularTotal();
    }

    public Venta() {
    }

    public void recalcularTotal() {
        double acum = 0.0;
        if (detalleVenta != null) {
            for (DetalleVenta d : detalleVenta) {
                acum += d.getCantidad() * d.getPrecioUnitario();
            }
        }
        this.total = acum;
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
        recalcularTotal();
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
        if (detalleVenta == null) {
            detalleVenta = new ArrayList<>();
        }
        detalleVenta.add(detalle);
        recalcularTotal();
    }

    public void quitarDetalle(DetalleVenta detalle) {
        if (detalleVenta != null) {
            detalleVenta.remove(detalle);
            recalcularTotal();
        }
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
