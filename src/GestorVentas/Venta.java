package GestorVentas;

import Enums.MetodoDePago;
import Productos.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta realizada en el sistema.
 * Contiene los detalles de los productos vendidos, el pago y la factura asociada.
 */
public class Venta {

    // Identificador único de la venta
    private String idVenta;

    // Lista de los detalles (productos, cantidades y precios)
    private List<DetalleVenta> detalles;

    // Pago asociado a la venta
    private Pago pago;

    // Factura generada una vez finalizada la venta
    private Factura factura;

    /**
     * Constructor de la clase Venta.
     * Inicializa la venta con un ID y una lista vacía de detalles.
     *
     * @param idVenta Identificador único de la venta
     */
    public Venta(String idVenta) {
        this.idVenta = idVenta;
        this.detalles = new ArrayList<>();
    }

    // ================== Getters y Setters ==================

    public String getIdVenta() {
        return idVenta;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public Pago getPago() {
        return pago;
    }

    public Factura getFactura() {
        return factura;
    }

    // ================== Métodos funcionales ==================

    /**
     * Agrega un nuevo detalle a la venta con el producto, cantidad y precio unitario indicados.
     *
     * @param producto       Producto vendido
     * @param cantidad       Cantidad de unidades
     * @param precioUnitario Precio por unidad
     */
    public void agregarDetalle(Producto producto, int cantidad, double precioUnitario) {
        DetalleVenta detalle = new DetalleVenta(producto, cantidad, precioUnitario);
        detalles.add(detalle);
    }

    /**
     * Calcula el total de la venta sumando los subtotales de todos los detalles.
     *
     * @return total de la venta
     */
    public double calcularTotal() {
        double total = 0;
        for (DetalleVenta d : detalles) {
            total += d.calcularSubtotal();
        }
        return total;
    }

    /**
     * Finaliza la venta generando un pago y una factura.
     * Asigna el metodo de pago elegido y registra el total automáticamente.
     *
     * @param metodo Metodo de pago utilizado (enum MetodoDePago)
     */
    public void finalizarVenta(MetodoDePago metodo) {
        this.pago = new Pago(metodo, calcularTotal());
        this.factura = new Factura(idVenta, this);
    }

    /**
     * Devuelve una representación completa de la venta,
     * mostrando los detalles, el total y el metodo de pago si existe.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta N° ").append(idVenta).append("\n");
        for (DetalleVenta d : detalles) {
            sb.append(" - ").append(d).append("\n");
        }
        sb.append("TOTAL: $").append(calcularTotal()).append("\n");
        if (pago != null) {
            sb.append(pago).append("\n");
        }
        return sb.toString();
    }
}
