package GestorVentas;

import Enums.MetodoDePago;
import java.time.LocalDate;

/**
 * Representa un pago realizado en una venta.
 * Guarda el metodo de pago utilizado, el monto total y la fecha en que se efectuó.
 */
public class Pago {
    // Metodo utilizado para realizar el pago (efectivo, tarjeta, etc.)
    private MetodoDePago metodo;

    // Monto total abonado en el pago
    private double monto;

    // Fecha en la que se realizó el pago
    private LocalDate fecha;

    /**
     * Constructor del pago.
     * Recibe el metodo de pago y el monto, y guarda automáticamente la fecha actual.
     *
     * @param metodo Tipo de metodo de pago utilizado (enum MetodoDePago)
     * @param monto  Importe total pagado
     */
    public Pago(MetodoDePago metodo, double monto) {
        this.metodo = metodo;
        this.monto = monto;
        this.fecha = LocalDate.now(); // se toma la fecha actual al crear el pago
    }

    // ================== Getters y Setters ==================

    public MetodoDePago getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoDePago metodo) {
        this.metodo = metodo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    // ================== Métodos auxiliares ==================

    /**
     * Devuelve una representación en texto del pago,
     * útil para mostrar información en pantalla o en reportes.
     */
    @Override
    public String toString() {
        return "Pago de $" + monto + " mediante " + metodo + " el " + fecha;
    }
}
