package Gestion;

import Archivos.GestionJSONCompra.GestionJSONCompra;
import Transacciones.Compra;
import Excepciones.DatoInvalidoException;
import Productos.Producto;
import Transacciones.Detalles.DetalleCompra;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

public class GestionCompra {

    private HashMap<String, Compra> compras;
    private Scanner teclado;
    private GestionProveedor gestionProveedor;
    private GestionProducto gestionProducto;

    public GestionCompra() {
        this.compras = new HashMap<>();
        this.gestionProveedor = new GestionProveedor();
        this.gestionProducto = new GestionProducto();
        this.teclado = new Scanner(System.in);
    }


    public void agregarYguardar(Compra compra){

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");
        compras.put(compra.getIdPedido(), compra);
        GestionJSONCompra.listaCompraToArchivo(compras, "compra.json");
    }

    /**
     * Carga una compra al registro general
     */
    public void cargarCompra() {

        Compra compra = new Compra();

        System.out.println("Elija el proveedor al cual desea comprarle productos:");

        Proveedor proveedor = gestionProveedor.elegirProveedorDisponible();
        compra.setProveedor(proveedor);

        boolean seguir = true;
        Scanner sc = new Scanner(System.in);


        while (seguir) {

            DetalleCompra detalleCompra = cargarDetalleCompra(proveedor);
            compra.agregarDetalleCompra(detalleCompra);

            Producto p = detalleCompra.getProducto();
            p.setStock(p.getStock() + detalleCompra.getCantidad());

            System.out.println("Stock actualizado para " + p.getNombre() + ": " + p.getStock());

            System.out.print("¿Desea agregar otro producto de este proveedor? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();

            if (!respuesta.equals("s")) {
                seguir = false;
            }
        }

        agregarYguardar(compra);
        System.out.println("¡Compra registrada con éxito!");
    }


    public DetalleCompra cargarDetalleCompra(Proveedor proveedor) {

        Producto productoSeleccionado = gestionProducto.elegirProductosDeUnProveedor(proveedor);

        int cantidad;

        while (true) {
            try {
                System.out.print("Ingrese la cantidad a comprar: ");
                cantidad = teclado.nextInt();
                teclado.nextLine();

                Validaciones.validarNumero(cantidad);
                break;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage() + ". Por favor, inténtelo nuevamente.");
            } catch (InputMismatchException e) {
                System.err.println("Debe ingresar un número válido.");
                teclado.nextLine();
            }
        }

        return new DetalleCompra(productoSeleccionado, cantidad);
    }


    public void modificarCompra(Compra compra) {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");

        boolean seguirModificando = true;

        while (seguirModificando) {
            System.out.println("\n¿Qué desea modificar?");
            System.out.println("1. Proveedor");
            System.out.println("2. Detalles de compra (productos y cantidades)");
            System.out.println("3. Cancelar modificación");

            System.out.print("Seleccione una opción: ");
            String opcion = teclado.nextLine().trim();

            switch (opcion) {
                case "1":

                    Proveedor nuevoProveedor = gestionProveedor.elegirProveedorDisponible();
                    compra.setProveedor(nuevoProveedor);
                    System.out.println("Proveedor actualizado correctamente.");
                    break;

                case "2":
                    modificarDetallesCompra(compra);
                    break;

                case "3":
                    seguirModificando = false;
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        GestionJSONCompra.listaCompraToArchivo(compras, "compra.json");
        System.out.println("Compra modificada y guardada correctamente.");
    }

    private void modificarDetallesCompra(Compra compra) {

        List<DetalleCompra> detalles = compra.getDetallesCompra();

        if (detalles.isEmpty()) {
            System.out.println("Esta compra no tiene detalles registrados.");
            return;
        }

        boolean seguir = true;
        mostrarDetallesCompra(compra);

        while (seguir) {

            System.out.print("Seleccione el número del detalle a modificar (o 0 para salir): ");
            int numDetalle = teclado.nextInt();
            teclado.nextLine();

            if (numDetalle == 0) {
                seguir = false;
                continue;
            }

            try {
                Validaciones.ingresarOpcionValida(detalles, numDetalle);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Número inválido. Intente nuevamente.");
                continue;
            }

            DetalleCompra detalleSeleccionado = detalles.get(numDetalle - 1);
            Producto producto = detalleSeleccionado.getProducto();

            System.out.println("\nModificando producto: " + producto.getNombre());
            System.out.println("Cantidad actual: " + detalleSeleccionado.getCantidad());

            int nuevaCantidad = -1;

            while (true) {
                try {
                    System.out.print("Ingrese nueva cantidad (o -1 para no cambiar): ");
                    nuevaCantidad = teclado.nextInt();
                    teclado.nextLine();

                    if (nuevaCantidad == -1) break;

                    Validaciones.validarNumero(nuevaCantidad);

                    int stockActual = producto.getStock();
                    producto.setStock(stockActual - detalleSeleccionado.getCantidad() + nuevaCantidad);

                    detalleSeleccionado.setCantidad(nuevaCantidad);
                    System.out.println("Cantidad actualizada correctamente.");
                    break;

                } catch (DatoInvalidoException e) {
                    System.err.println("Error: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.err.println("Debe ingresar un número válido.");
                    teclado.nextLine();
                }
            }

            System.out.print("¿Desea modificar otro detalle? (s/n): ");
            String resp = teclado.nextLine().trim().toLowerCase();
            if (!resp.equals("s")) {
                seguir = false;
            }
        }
    }

    public void mostrarDetallesCompra(Compra compra) {

        List<DetalleCompra> detalles = compra.getDetallesCompra();

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


    /**
     * Elimina una compra por su ID
     */
    public void cancelarCompra(String idPedido) {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");

        if (compras.containsKey(idPedido)) {
            Compra compra = compras.get(idPedido);
            compra.setActivo(false);
            System.out.println("La compra con ID " + idPedido + " fue cancelada correctamente.");

        } else {
            throw new IllegalArgumentException("No se encontró una compra con el ID: " + idPedido);
        }

        GestionJSONCompra.listaCompraToArchivo(compras, "compra.json");
    }


    /**
     * Busca una compra por su ID
     */
    public Compra buscarPorId(String idPedido) {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");

        Compra compra = compras.get(idPedido);

        if (compra == null) {
            throw new IllegalArgumentException("No existe una compra con el ID: " + idPedido);
        }

        return compra;
    }


    /**
     * Lista todas las compras registradas
     */
    public void mostrarCompras() {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");

        for (Compra c : compras.values()) {
            System.out.println(c.mostrarDetalles());
        }
    }


    /**
     * Devuelve la compra con el monto total más alto.
     *
     * @return La compra con el total más alto, o null si no hay compras registradas.
     */
    public Compra obtenerCompraMaxima() {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");

        if (compras.isEmpty()) return null;

        Compra max = null;
        for (Compra c : compras.values()) {
            if (max == null || c.getTotal() > max.getTotal()) {
                max = c;
            }
        }

        return max;
    }

    /**
     * Devuelve la compra con el monto total más bajo.
     *
     * @return La compra con el total más bajo, o null si no hay compras registradas.
     */
    public Compra obtenerCompraMinima() {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");

        if (compras.isEmpty()) return null;

        Compra min = null;
        for (Compra c : compras.values()) {
            if (min == null || c.getTotal() < min.getTotal()) {
                min = c;
            }
        }

        return min;
    }


    public void mostrarCompra(Compra compra) {

        System.out.println("\n=== Detalle de la Compra ===");
        System.out.println("ID Pedido: " + compra.getIdPedido());
        System.out.println("Fecha: " + compra.getFechaCompra().toString());
        System.out.println("Proveedor: " + compra.getProveedor().getNombre() + " " + compra.getProveedor().getApellido());
        System.out.println("Estado: " + (compra.isActivo() ? "Activa" : "Cancelada"));

        if (compra.getDetallesCompra().isEmpty()) {
            System.out.println("No hay productos en esta compra.");

        } else {
            mostrarDetallesCompra(compra);
        }

        System.out.println("Total de la compra: $" + compra.getTotal());
        System.out.println("============================\n");
    }



}
