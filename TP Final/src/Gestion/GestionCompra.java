package Gestion;

import Archivos.GestionJSONCompra.GestionJSONCompra;
import Archivos.GestionJSONProductos.GestionJSONProducto;
import IngresoDeDatos.InputHelper;
import Transacciones.Compra;
import Productos.Producto;
import Transacciones.Detalles.DetalleCompra;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

public class GestionCompra {

    private HashMap<String, Compra> compras;
    private GestionProveedor gestionProveedor;
    private GestionProducto gestionProducto;
    private HashMap<String, Producto> listaProductos;

    public GestionCompra() {
        this.compras = new HashMap<>();
        this.gestionProducto = new GestionProducto();
        this.gestionProveedor = new GestionProveedor(gestionProducto);
        this.listaProductos = new HashMap<>();
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

        while (seguir) {

            DetalleCompra detalleCompra = cargarDetalleCompra(proveedor);
            compra.agregarDetalleCompra(detalleCompra);

            Producto p = detalleCompra.getProducto();
            p.setStock(p.getStock() + detalleCompra.getCantidad());

            System.out.println("Stock actualizado para " + p.getNombre() + ": " + p.getStock());

            char respuesta = InputHelper.leerChar("¿Desea agregar otro producto de este proveedor? (s/n): ");

            if (respuesta != 's') {
                seguir = false;
            }
        }

        agregarYguardar(compra);
        System.out.println("¡Compra registrada con éxito!");
    }


    public DetalleCompra cargarDetalleCompra(Proveedor proveedor) {

        Producto productoSeleccionado = gestionProducto.elegirProductosDeUnProveedor(proveedor);

        int cantidad;

        cantidad = InputHelper.leerInt("Ingrese la cantidad a comprar: ");

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

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {
                case 1:

                    Proveedor nuevoProveedor = gestionProveedor.elegirProveedorDisponible();
                    compra.setProveedor(nuevoProveedor);
                    System.out.println("Proveedor actualizado correctamente.");
                    break;

                case 2:
                    modificarDetallesCompra(compra);
                    break;

                case 3:
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
        compra.mostrarDetallesCompra();

        while (seguir) {

            System.out.print("Seleccione el número del detalle a modificar (o 0 para salir): ");
            int numDetalle = InputHelper.leerEnteroSwitch();

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

            int nuevaCantidad = InputHelper.leerInt("Ingrese nueva cantidad (o -1 para no cambiar): ");

            if (nuevaCantidad == -1) break;

            int stockActual = producto.getStock();
            producto.setStock(stockActual - detalleSeleccionado.getCantidad() + nuevaCantidad);

            detalleSeleccionado.setCantidad(nuevaCantidad);
            System.out.println("Cantidad actualizada correctamente.");

            char resp = InputHelper.leerChar("¿Desea modificar otro detalle? (s/n): ");
            if (resp != 's') {
                seguir = false;
            }
        }
    }


    /**
     * Elimina una compra por su ID
     */
    public void cancelarCompra(String idPedido) {

        compras = GestionJSONCompra.archivoCompraToLista("compra.json");
        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        if (compras.containsKey(idPedido)) {
            Compra compra = compras.get(idPedido);

            if (!compra.isActivo()) {
                System.out.println("La compra con ID " + idPedido + " ya estaba cancelada.");
                return;
            }

            compra.setActivo(false);

            for (DetalleCompra detalle : compra.getDetallesCompra()) {
                Producto producto = detalle.getProducto();
                int cantidad = detalle.getCantidad();
                producto.setStock(producto.getStock() - cantidad);

                listaProductos.put(producto.getCodigo(), producto);
            }

            GestionJSONCompra.listaCompraToArchivo(compras, "compra.json");
            GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");

            System.out.println("La compra con ID " + idPedido + " fue cancelada correctamente y el stock actualizado.");
        } else {
            throw new IllegalArgumentException("No se encontró una compra con el ID: " + idPedido);
        }
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
            c.mostrarCompra();
        }
    }
}
