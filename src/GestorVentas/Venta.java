package GestorVentas;

import Users.UsuarioSistema.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta realizada en el sistema.
 * Contiene los detalles de los productos vendidos, el cliente, y el estado de la venta.
 */
public class Venta {

    // Identificador único de la venta
    private String idVenta;

    // Cliente que realizó la compra
    private Cliente cliente;

    // Lista de detalles de la venta
    private List<DetalleVenta> detalles = new ArrayList<>();

    // Monto total de la venta
    private double total;

    // Fecha en que se realizó la venta
    private LocalDate fecha;

    // Estado de la venta (activa o no)
    private boolean activo;

    // Constructor
    public Venta(String idVenta, Cliente cliente, LocalDate fecha) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fecha = fecha;
        this.activo = true;
    }

    // Getters y setters
    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
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

    // Métodos de manejo de detalles
    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
    }

    public void quitarDetalle(DetalleVenta detalle) {
        detalles.remove(detalle);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta='" + idVenta + '\'' +
                ", cliente=" + cliente +
                ", detalles=" + detalles +
                ", total=" + total +
                ", fecha=" + fecha +
                ", activo=" + activo +
                '}';
    }
}
