package Transacciones;

import IngresoDeDatos.InputHelper;
import Transacciones.Detalles.DetalleCompra;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private String idPedido;
    private double total;
    private LocalDate fechaCompra;
    private boolean activo;
    private List<DetalleCompra> detallesCompra;


    public Compra() {
    }

    public Compra(Proveedor proveedor) {
        this.idPedido = InputHelper.generarCodigoUnico();
        this.detallesCompra = new ArrayList<>();
        this.total = getTotal();
        this.fechaCompra = LocalDate.now();
        this.activo = true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<DetalleCompra> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    public double getTotal(){

        total = 0;
        for (DetalleCompra p : detallesCompra)
            total += p.getSubtotal();

        return total;
    }

    public void agregarDetalleCompra(DetalleCompra detalleCompra){
        detallesCompra.add(detalleCompra);
    }

    public void mostrarCompra() {

        System.out.println("\n===Compra ===");
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Fecha: " + fechaCompra);
        System.out.println("Estado: " + (activo ? "Activa" : "Cancelada"));

        if (detallesCompra.isEmpty()) {
            System.out.println("No hay productos en esta compra.");

        } else {
            mostrarDetallesCompra();
        }

        System.out.println("Total de la compra: $" + getTotal());
        System.out.println("============================\n");
    }

    public void mostrarDetallesCompra() {

        List<DetalleCompra> detalles = detallesCompra;

        if (detalles.isEmpty()) {
            System.out.println("Esta compra no tiene detalles registrados.");
            return;
        }

        System.out.println("\nDetalles de la compra:");

        for (int i = 0; i < detalles.size(); i++) {
            DetalleCompra d = detalles.get(i);
            System.out.println((i + 1) + ". Producto: " + d.getProducto().getNombre()
                    + " | Cantidad: " + d.getCantidad()
                    + " | Precio unitario: " + d.getProducto().getPrecio()
                    + " | Subtotal: " + (d.getCantidad() * d.getProducto().getPrecio()));
        }
    }
}
