package Venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private static int contador = 0;
    private String idVenta;
    private List<DetalleVenta> detalleVenta;
    private double total;
    private LocalDate fecha;
    private boolean activo;

    // Constructor completo (si querés usarlo igual)
    public Venta(List<DetalleVenta> detalleVenta, double total, LocalDate fecha, boolean activo) {
        this.idVenta = String.valueOf(++contador);
        this.detalleVenta = detalleVenta;
        this.total = total;
        this.fecha = fecha;
        this.activo = activo;
    }

    // ✅ Constructor más práctico: calcula el total solo y la venta arranca activa
    public Venta(List<DetalleVenta> detalleVenta, LocalDate fecha) {
        this.idVenta = String.valueOf(++contador);
        this.detalleVenta = (detalleVenta != null) ? detalleVenta : new ArrayList<>();
        this.fecha = fecha;
        this.activo = true;
        recalcularTotal();
    }

    // Constructor vacío (por si lo necesita JSON / frameworks)
    public Venta() {
    }

    // ✅ Recalcular el total a partir de los detalles
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

    // Si se quiere forzar un total manualmente
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

    // ✅ Cuando agrego un detalle, actualizo total
    public void agregarDetalle(DetalleVenta detalle) {
        if (detalleVenta == null) {
            detalleVenta = new ArrayList<>();
        }
        detalleVenta.add(detalle);
        recalcularTotal();
    }

    // ✅ Cuando quito un detalle, actualizo total
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
