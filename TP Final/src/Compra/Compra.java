package Compra;

import Productos.Producto;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compra {

    private static int contador = 0;
    private String idPedido;
    private double total;
    private LocalDate fechaCompra;
    private Proveedor proveedor;
    private List<DetalleCompra> detallesCompra;


    public Compra() {
    }

    public Compra(Proveedor proveedor) {
        this.idPedido = String.valueOf(++contador);
        this.total = 0;
        this.fechaCompra = LocalDate.now();
        this.proveedor = proveedor;
        this.detallesCompra = new ArrayList<>();
    }

    public String getIdPedido() {return idPedido;}

    public LocalDate getFechaCompra() {return fechaCompra;}

    public Proveedor getProveedor() {return proveedor;}

    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}

    public List<DetalleCompra> getDetallesCompra() {return detallesCompra;}


    @Override
    public String toString() {
        return "Compra{" +
                "idPedido = '" + idPedido + '\'' +
                ", total = " + total +
                ", fechaCompra = " + fechaCompra +
                ", proveedor = " + proveedor +
                ", detallesCompra = " + detallesCompra +
                '}';
    }
}
