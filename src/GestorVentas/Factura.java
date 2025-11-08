package GestorVentas;

import java.time.LocalDate;

/**
 * Representa una factura emitida a partir de una venta.
 * Contiene información sobre el número de factura, la fecha y la venta asociada.
 */
public class Factura {

    // Identificador único de la factura
    private String idFactura;

    // Venta asociada a la factura
    private Venta venta;

    // Fecha en que se emite la factura
    private LocalDate fechaEmision;

    // Monto total de la factura
    private double total;

    // Constructor
    public Factura(String idFactura, Venta venta, LocalDate fechaEmision, double total) {
        this.idFactura = idFactura;
        this.venta = venta;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    // Getters y setters
    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura='" + idFactura + '\'' +
                ", venta=" + venta +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                '}';
    }
}
