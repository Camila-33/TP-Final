package GestorVentas;

import Enums.MetodoDePago;
import java.time.LocalDate;

/**
 * Representa un pago realizado en una venta.
 * Guarda el metodo de pago utilizado, el monto total y la fecha en que se efectuó.
 */
public class Pago {

    // Identificador del pago
    private String idPago;

    // Venta asociada al pago
    private Venta venta;

    // Metodo utilizado para realizar el pago
    private MetodoDePago metodoPago;

    // Monto total abonado
    private double monto;

    // Fecha en que se efectuó el pago
    private LocalDate fechaPago;

    // Constructor
    public Pago(String idPago, Venta venta, MetodoDePago metodoPago, double monto, LocalDate fechaPago) {
        this.idPago = idPago;
        this.venta = venta;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    // Getters y setters
    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public MetodoDePago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoDePago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago='" + idPago + '\'' +
                ", venta=" + venta +
                ", metodoPago=" + metodoPago +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
