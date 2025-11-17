package Transacciones;

import IngresoDeDatos.InputHelper;
import Transacciones.Detalles.DetalleVenta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta realizada en el sistema.
 * Contiene los detalles de los productos vendidos, el cliente, y el estado de la venta.
 */

public class Venta {

    private String idVenta;
    private List<DetalleVenta> detalleVenta;
    private double total;
    private LocalDate fecha;
    private boolean activo;


    public Venta(List<DetalleVenta> detalleVenta) {
        this.idVenta = InputHelper.generarCodigoUnico();
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

    public void mostrarVenta() {

        System.out.println("\n=== Detalle de la venta ===");
        System.out.println("ID Pedido: " + idVenta);
        System.out.println("Fecha: " + fecha.toString());
        System.out.println("Estado: " + (activo ? "Activa" : "Cancelada"));

        if (detalleVenta.isEmpty()) {
            System.out.println("No hay productos en esta venta.");

        } else {
            mostrarDetallesVenta();
        }

        System.out.println("Total de la venta: $" + getTotal());
        System.out.println("============================\n");
    }

    public void mostrarDetallesVenta() {

        List<DetalleVenta> detalles = detalleVenta;

        if (detalles.isEmpty()) {
            System.out.println("Esta venta no tiene detalles registrados.");
            return;
        }

        System.out.println("\nDetalles de la venta:");

        for (int i = 0; i < detalles.size(); i++) {
            DetalleVenta d = detalles.get(i);
            System.out.println((i + 1) + ". Producto: " + d.getProducto().getNombre()
                    + " | Cantidad: " + d.getCantidad()
                    + " | Precio unitario: " + d.getProducto().getPrecio()
                    + " | Subtotal: " + (d.getCantidad() * d.getProducto().getPrecio()));
        }
    }
}
