package Gestion;

import Productos.Producto;
import Users.LogInUser.LogIn;
import Users.Proveedor;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Empleado;

import java.io.FileNotFoundException;
import java.util.*;

public class GestionMenu {

    private Scanner teclado;
    private Map<String, Producto> productosDisponibles;
    private GestionProducto gestionProducto;
    private GestionProveedor gestionProveedor;
    private LogIn logIn;

    public GestionMenu(Map<String, Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
        this.teclado = new Scanner(System.in);
        this.gestionProducto = new GestionProducto();
        this.gestionProveedor = new GestionProveedor();
        this.logIn = new LogIn();
    }

    public void MenuPrincipal() {

        System.out.println("======================");
        System.out.println("BIENVENIDO A PC ZONE");
        System.out.println("======================");
        boolean valido = false;

        while (!valido) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar sesión como Administrador");
            System.out.println("2. Iniciar sesión como Empleado");
            System.out.println("3. Salir.");

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:

                    try {
                        Administrador administrador = logIn.inicioSesionAdmin("administrador.json");
                        mostrarMenuAdministrador(administrador);
                        valido = true;

                    }catch (FileNotFoundException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 2:

                    try {
                        Empleado empleado = logIn.inicioSesionEmpleado("empleado.json");
                        mostrarMenuEmpleado(empleado);
                        valido = true;

                    }catch (FileNotFoundException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 3:
                    valido = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }


    public void mostrarMenuEmpleado(Empleado empleado){

        boolean salir = true;

        while (salir){

            System.out.println("================");
            System.out.println("MENÚ EMPLEADO");
            System.out.println("=================");
            System.out.println("1. Generar orden de venta");
            System.out.println("2. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:

                    break;

                case 2:
                    System.out.println("Saliendo del menú...");
                    salir = false;
                    break;

                default:
                    System.out.println("Opcion fuera de rango. Intentelo nuevamente");
                    break;
            }
        }
    }


    public void mostrarMenuAdministrador(Administrador administrador){

        boolean salir = true;

        while (salir){

            System.out.println("=====================");
            System.out.println("MENÚ ADMINISTRADOR");
            System.out.println("=====================");
            System.out.println("1. Cargar productos");
            System.out.println("2. Dar de baja productos");
            System.out.println("3. Dar de alta productos");
            System.out.println("4. Buscar productos por nombre");
            System.out.println("5. Modificar datos de un producto disponible");
            System.out.println("6. Mostrar productos disponibles");
            System.out.println("7. Cargar proveedor");
            System.out.println("8. Dar de baja proveedor");
            System.out.println("9. Dar de alta proveedor");
            System.out.println("10. Buscar proveedor");
            System.out.println("11. Mostrar proveedores");
            System.out.println("12. Modificar datos de un proveedor disponible");
            System.out.println("13. Generar orden de compra");
            System.out.println("14. Generar orden de venta");
            System.out.println("15. Ver stock de productos");
            System.out.println("16. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    administrador.cargarProductos(gestionProducto, teclado);
                    break;

                case 2:
                    Producto darBaja = administrador.elegirProductosDisponibles(gestionProducto, teclado);
                    administrador.darDeBajaProducto(gestionProducto, darBaja);
                    break;

                case 3:
                    Producto darAlta = administrador.elegirProductosDeBaja(gestionProducto, teclado);
                    administrador.darDeAltaProducto(gestionProducto, darAlta);
                    break;

                case 4:
                    System.out.println("Ingrese el nombre de los productos que quiere buscar");
                    String nombreBusqueda = teclado.nextLine().toLowerCase();
                    administrador.buscarProductoPorNombre(gestionProducto, nombreBusqueda);
                    break;

                case 5:
                    Producto modificar = administrador.elegirProductosDisponibles(gestionProducto, teclado);
                    administrador.modificarProducto(gestionProducto, modificar);
                    break;

                case 6:
                    administrador.mostrarProductos(gestionProducto);
                    break;

                case 7:
                    administrador.cargarProveedor(gestionProveedor, teclado);
                    break;

                case 8:
                    System.out.println("Elija alguno de los siguientes proveedores de alta para darlos de baja");
                    Proveedor bajaProveedor = administrador.elegirProveedor(gestionProveedor, teclado);
                    administrador.darDeBajaProveedor(gestionProveedor, bajaProveedor);
                    break;

                case 9:
                    System.out.println("Elija alguno de los siguientes proveedores de baja para darlos de alta");
                    Proveedor altaProveedor = administrador.elegirProveedorDeBaja(gestionProveedor, teclado);
                    administrador.darDeAltaProveedor(gestionProveedor, altaProveedor);
                    break;

                case 10:
                    System.out.println("Ingrese el nombre de los proveedores que quiere buscar");
                    String nombre = teclado.nextLine().toLowerCase();
                    administrador.buscarProveedor(gestionProveedor,nombre);
                    break;

                case 11:
                    administrador.mostrarProveedoresDisponibles(gestionProveedor);
                    break;

                case 12:
                    Proveedor pm = administrador.elegirProveedor(gestionProveedor, teclado);
                    administrador.modificarProveedor(gestionProveedor, pm, teclado);
                    break;

                case 13:

                    break;

                case 14:

                    break;

                case 15:

                    break;

                case 16:
                    System.out.println("Saliendo del menú...");
                    salir = false;

                default:
                    System.out.println("Opcion fuera de rango. Intentelo nuevamente");
                    break;
            }
        }
    }
}
