package Gestion;

import Archivos.GestionJSONVenta.GestionJSONVenta;
import Excepciones.DatoInvalidoException;
import Productos.Producto;
import Transacciones.Compra;
import Transacciones.Detalles.DetalleCompra;
import Validaciones.Validaciones;
import Transacciones.Detalles.DetalleVenta;
import Transacciones.Venta;

import java.util.*;

public class GestionVenta {

    private HashMap<String, Venta> listaVentas;
    private Scanner teclado;
    private GestionProducto gestionProducto;

    public GestionVenta() {
        this.listaVentas = new HashMap<>();
        this.teclado = new Scanner(System.in);
        this.gestionProducto = new GestionProducto();
    }

    // ================== 1) Cargar orden de venta ==================

    public void agregarYguardar(Venta venta){

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");
        listaVentas.put(venta.getIdVenta(), venta);
        GestionJSONVenta.listaVentaToArchivo(listaVentas, "venta.json");
    }

    public void cargarOrdenVenta() {

        List<DetalleVenta> detalles = new ArrayList<>();
        char boton;

        do {
            System.out.println("Seleccione un producto para agregar a la venta:");
            Producto producto = gestionProducto.elegirProductosDisponibles();

            int cantidad;

            while (true) {
                try {
                    System.out.println("Ingrese la cantidad:");
                    cantidad = teclado.nextInt();
                    teclado.nextLine();
                    Validaciones.validarNumero(cantidad);
                    break;

                } catch (DatoInvalidoException e) {
                    System.err.println("Error: " + e.getMessage() + ". Por favor, inténtelo nuevamente");
                } catch (InputMismatchException e) {
                    System.err.println("Error: Debe ingresar un número válido.");
                    teclado.nextLine();
                }
            }

            DetalleVenta detalle = new DetalleVenta(producto, cantidad);
            detalles.add(detalle);

            System.out.println("¿Desea agregar otro producto? (s / n)");

            while (true) {
                try {
                    boton = teclado.next().toLowerCase().charAt(0);
                    Validaciones.validarBoton(boton);
                    break;
                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }

        } while (boton == 's');

        Venta nuevaVenta = new Venta(detalles);

        listaVentas.put(nuevaVenta.getIdVenta(), nuevaVenta);

        agregarYguardar(nuevaVenta);
        System.out.println("Orden de venta cargada correctamente. ID: " + nuevaVenta.getIdVenta());
        System.out.println("Total: " + nuevaVenta.getTotal());
    }

    // ================== 2) Cancelar orden de venta ==================

    public void cancelarOrdenDeVenta(Venta venta) {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");

        if (!venta.isActivo()) {
            System.out.println("La orden de venta ya está cancelada.");
            return;
        }

        System.out.println("Venta encontrada: " + venta);
        System.out.println("¿Está seguro que desea cancelar esta orden de venta? (si / no)");
        String opcion = teclado.nextLine();

        while (true) {
            if (opcion.equalsIgnoreCase("si")) {
                venta.setActivo(false);

                GestionJSONVenta.listaVentaToArchivo(listaVentas, "venta.json");

                System.out.println("¡Orden de venta cancelada con éxito!");
                return;

            } else if (opcion.equalsIgnoreCase("no")) {
                System.out.println("Operación cancelada");
                return;

            } else {
                System.out.println("Opción inválida. Por favor, ingrese 'si' o 'no'");
                opcion = teclado.nextLine();
            }
        }
    }


    // ================== 3) Mostrar ordenes de venta ==================

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

    // ================== 4) Buscar orden de venta ==================

    public Venta buscarOrdenVentaPorId(String idVenta) {

        listaVentas = GestionJSONVenta.archivoVentaToLista("venta.json");

        if (!listaVentas.containsKey(idVenta)) {
            throw new IllegalArgumentException("No se encontró ninguna venta con ID: " + idVenta);
        }

        return listaVentas.get(idVenta);
    }

    // ================== 5) Modificar orden de venta ==================

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

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número válido.");
                teclado.nextLine();
                opcion = 0;
            }

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


    // ======== Métodos de ayuda ========

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

        int cantidad;

        while (true) {
            try {
                System.out.println("Ingrese la cantidad:");
                cantidad = teclado.nextInt();
                teclado.nextLine();
                Validaciones.validarNumero(cantidad);
                break;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage() + ". Por favor, inténtelo nuevamente");
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número válido.");
                teclado.nextLine();
            }
        }

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
                opcion = teclado.nextInt();
                teclado.nextLine();
                Validaciones.ingresarOpcionValida(detalles, opcion);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número válido.");
                teclado.nextLine();
            }
        }

        DetalleVenta aQuitar = detalles.get(opcion - 1);
        venta.quitarDetalle(aQuitar);
        recalcularTotalVenta(venta);

        System.out.println("Producto quitado de la venta. Nuevo total: " + venta.getTotal());
    }

    public void mostrarVenta(Venta venta) {

        System.out.println("\n=== Detalle de la venta ===");
        System.out.println("ID Pedido: " + venta.getIdVenta());
        System.out.println("Fecha: " + venta.getFecha().toString());
        System.out.println("Estado: " + (venta.isActivo() ? "Activa" : "Cancelada"));

        if (venta.getDetalleVenta().isEmpty()) {
            System.out.println("No hay productos en esta venta.");

        } else {
            mostrarDetallesVenta(venta);
        }

        System.out.println("Total de la venta: $" + venta.getTotal());
        System.out.println("============================\n");
    }

    public void mostrarDetallesVenta(Venta venta) {

        List<DetalleVenta> detalles = venta.getDetalleVenta();

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
