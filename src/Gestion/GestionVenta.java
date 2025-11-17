package Gestion;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Archivos.GestionJSONVenta.GestionJSONVenta;
import GestionStock.Entidades.Inventario;
import IngresoDeDatos.InputHelper;
import Productos.Producto;
import Transacciones.Detalles.DetalleVenta;
import Transacciones.Venta;
import Validaciones.Validaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionVenta {

    private HashMap<String, Venta> listaVentas;
    private GestionProducto gestionProducto;
    private HashMap<String, Producto> listaProductos;

    private Inventario inventario;


    public GestionVenta(Inventario inventario, GestionProducto gestionProducto) {
        this.listaVentas = new HashMap<>();
        this.gestionProducto = gestionProducto;
        this.listaProductos = new HashMap<>();
        this.inventario = inventario;
    }


    public void agregarYguardar(Venta venta)
    {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");
        listaVentas.put(venta.getIdVenta(), venta);
        GestionJSONVenta.listaVentaToArchivo(listaVentas, "venta.json");

        //itera sacando todo del inventario:
        for (DetalleVenta detalleVenta : venta.getDetalleVenta())
        {
            inventario.sacarProducto(
                    detalleVenta.getProducto().getCodigo(),
                    detalleVenta.getCantidad()
            );
        }

    }

    public void cargarOrdenVenta() {

        List<DetalleVenta> detalles = new ArrayList<>();
        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");
        char boton = ' ';

        do {
            System.out.println("Seleccione un producto para agregar a la venta:");
            Producto producto = gestionProducto.elegirProductosDisponibles();

            int cantidad = InputHelper.leerInt("Ingrese la cantidad a vender:");

            if (cantidad > producto.getStock()) {
                System.out.println("¡No hay suficiente stock! Stock disponible: " + producto.getStock());
                continue;
            }

            DetalleVenta detalle = new DetalleVenta(producto, cantidad);
            detalles.add(detalle);

            producto.setStock(producto.getStock() - cantidad);
            listaProductos.put(producto.getCodigo(), producto);

            boton = InputHelper.leerChar("¿Desea agregar otro producto? (s / n)");

        } while (boton == 's');

        Venta nuevaVenta = new Venta(detalles);

        listaVentas.put(nuevaVenta.getIdVenta(), nuevaVenta);

        agregarYguardar(nuevaVenta);
        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");

        System.out.println("Orden de venta cargada correctamente. ID: " + nuevaVenta.getIdVenta());
        System.out.println("Total: " + nuevaVenta.getTotal());
    }


    public void cancelarOrdenDeVenta(Venta venta) {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");
        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        if (!venta.isActivo()) {
            System.out.println("La orden de venta ya está cancelada.");
            return;
        }

        System.out.println("Venta encontrada: " + venta);
        char opcion = InputHelper.leerChar("¿Está seguro que desea cancelar esta orden de venta? (s / n)");

        while (true) {
            if (opcion == 's') {
                venta.setActivo(false);

                for (DetalleVenta detalle : venta.getDetalleVenta()) {
                    Producto producto = detalle.getProducto();
                    int cantidad = detalle.getCantidad();
                    producto.setStock(producto.getStock() + cantidad);

                    listaProductos.put(producto.getCodigo(), producto);
                }

                GestionJSONVenta.listaVentaToArchivo(listaVentas, "venta.json");
                GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");

                System.out.println("¡Orden de venta cancelada con éxito y stock actualizado!");
                return;

            } else if (opcion == 'n') {
                System.out.println("Operación cancelada");
                return;
            }
        }
    }


    public void mostrarOrdenesVenta() {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");

        if (listaVentas.isEmpty()) {
            System.out.println("No hay órdenes de venta registradas.");
            return;
        }

        for (Map.Entry<String, Venta> entry : listaVentas.entrySet()) {
            Venta v = entry.getValue();
            System.out.println("-------------------------------------");
            System.out.println("ID Venta: " + entry.getKey());
            System.out.println(v);
        }
        System.out.println("-------------------------------------");
    }


    public Venta buscarOrdenVentaPorId(String idVenta) {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");

        if (!listaVentas.containsKey(idVenta)) {
            throw new IllegalArgumentException("No se encontró ninguna venta con ID: " + idVenta);
        }

        return listaVentas.get(idVenta);
    }


    public void modificarOrdenVenta(Venta venta) {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");

        if (!venta.isActivo()) {
            System.out.println("La orden de venta está cancelada, no se puede modificar.");
            return;
        }

        int opcion;

        do {
            System.out.println("Modificar orden de venta ID: " + venta.getIdVenta());
            System.out.println("1. Agregar producto");
            System.out.println("2. Quitar producto");
            System.out.println("3. Mostrar detalles");
            System.out.println("4. Terminar modificación");

            opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {
                case 1:
                    agregarProductoAVenta(venta);
                    break;

                case 2:
                    quitarProductoDeVenta(venta);
                    break;

                case 3:
                    System.out.println(venta);
                    break;

                case 4:
                    System.out.println("Modificación finalizada.");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (opcion != 4);

        GestionJSONVenta.listaVentaToArchivo(listaVentas, "venta.json");
    }


    private double calcularTotal(List<DetalleVenta> detalles) {
        double total = 0.0;
        for (DetalleVenta d : detalles) {
            total += d.getCantidad() * d.getPrecioUnitario();
        }
        return total;
    }

    private void recalcularTotalVenta(Venta venta) {
        if (venta.getDetalleVenta() == null) {
            venta.setTotal(0);
            return;
        }
        double total = calcularTotal(venta.getDetalleVenta());
        venta.setTotal(total);
    }

    private void agregarProductoAVenta(Venta venta) {

        System.out.println("Seleccione un producto para agregar:");
        Producto producto = gestionProducto.elegirProductosDisponibles();

        int cantidad = InputHelper.leerInt("Ingrese la cantidad:");
        DetalleVenta detalle = new DetalleVenta(producto, cantidad);

        venta.agregarDetalle(detalle);
        recalcularTotalVenta(venta);

        System.out.println("Producto agregado a la venta. Nuevo total: " + venta.getTotal());
    }

    private void quitarProductoDeVenta(Venta venta) {

        List<DetalleVenta> detalles = venta.getDetalleVenta();

        if (detalles == null || detalles.isEmpty()) {
            System.out.println("La venta no tiene productos.");
            return;
        }

        System.out.println("Seleccione el número de ítem a quitar:");

        for (int i = 0; i < detalles.size(); i++) {
            System.out.println((i + 1) + ". " + detalles.get(i));
        }

        int opcion;

        while (true) {
            try {
                opcion = InputHelper.leerEnteroSwitch();
                Validaciones.ingresarOpcionValida(detalles, opcion);
                break;

            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        DetalleVenta aQuitar = detalles.get(opcion - 1);
        venta.quitarDetalle(aQuitar);
        recalcularTotalVenta(venta);

        System.out.println("Producto quitado de la venta. Nuevo total: " + venta.getTotal());
    }
}
