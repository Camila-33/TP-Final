package GestorVentas;

import java.time.LocalDate;

/**
 * Representa una factura emitida a partir de una venta.
 * Contiene información sobre el número de factura, la fecha y la venta asociada.
 */
public class Factura {

    // Identificador único de la factura
    private String idFactura;

    // Fecha en que se emite la factura
    private LocalDate fechaEmision;

    // Venta asociada a esta factura
    private Venta venta;

    /**
     * Constructor de la clase Factura.
     * Crea una factura asociada a una venta específica y asigna la fecha actual.
     *
     * @param idFactura Código o número de factura
     * @param venta     Venta a la que corresponde la factura
     */
    public Factura(String idFactura, Venta venta) {
        this.idFactura = idFactura;
        this.venta = venta;
        this.fechaEmision = LocalDate.now(); // se genera con la fecha del día
    }

    // ================== Getters y Setters ==================

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    // ================== Métodos funcionales ==================

    /**
     * Calcula el total de la factura tomando el total de la venta asociada.
     *
     * @return monto total de la venta
     */
    public double getTotal() {
        return venta.calcularTotal();
    }

    /**
     * Devuelve una representación legible de la factura,
     * mostrando su número, fecha y total.
     */
    @Override
    public String toString() {
        return "Factura N° " + idFactura + " - Fecha: " + fechaEmision + " - Total: $" + getTotal();
    }
}
