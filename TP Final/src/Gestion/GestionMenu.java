package Gestion;

import Excepciones.ObjetoNoEncontradoException;
import Productos.Producto;
import Users.LogInUser.LogIn;
import Users.Proveedor;
import Users.RegistroUser.RegistroUser;
import Users.UsuarioSistema.Administrador;

import java.io.FileNotFoundException;
import java.util.*;

public class GestionMenu {

    private Scanner teclado;
    private Map<String, Producto> productosDisponibles;
    private GestionProducto gestionProducto;
    private GestionProveedor gestionProveedor;
    private LogIn logIn;
    private GestionAdministrador gestionAdministrador;

    public GestionMenu(Map<String, Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
        this.teclado = new Scanner(System.in);
        this.gestionProducto = new GestionProducto();
        this.gestionProveedor = new GestionProveedor();
        this.gestionAdministrador = new GestionAdministrador();
        this.logIn = new LogIn();
    }

    public void menuPrincipal() {

        System.out.println("======================");
        System.out.println("BIENVENIDO A PC ZONE");
        System.out.println("======================");
        boolean valido = false;

        while (!valido) {
            System.out.println("Seleccione su tipo de usuario:");
            System.out.println("1. Administrador");
            System.out.println("2. Empleado");
            System.out.println("3. Salir.");

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    valido = true;
                    menuAdministrador();

                    break;

                case 2:
                    valido = true;
                    menuEmpleado();

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


    public void menuAdministrador() {

        boolean salir = false;

        while (!salir) {

            System.out.println("=====================");
            System.out.println("MENÚ ADMINISTRADOR");
            System.out.println("=====================");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Atrás");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    gestionAdministrador.ingresarUsuario();
                    salir = true;
                    break;

                case 2:

                    try {

                        Administrador administrador = logIn.inicioSesionAdmin("administrador.json");

                        if(administrador == null){

                            System.out.println("Error: Usuario no existente. Volviendo al menú principal");
                            salir = true;
                            menuPrincipal();

                            break;
                        }

                        System.out.println("Bienvenido/a " +administrador.getUserName());
                        menuInicioSesionAdministrador(administrador);

                    }catch (FileNotFoundException e){
                        System.out.println("Error: " +e.getMessage());
                    }

                    break;

                case 3:
                    System.out.println("Volviendo al menú principal...");
                    salir = true;
                    menuPrincipal();

                    break;

                default:
                    System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");

                    break;
            }
        }
    }


    public void menuInicioSesionAdministrador(Administrador administrador) {

        while (true) {

            System.out.println("Elija una opción");
            System.out.println("1. Mi cuenta");
            System.out.println("2. Gestion productos");
            System.out.println("3. Gestion proveedores");
            System.out.println("4. Gestion stock");
            System.out.println("5. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    cuentaAdministrador(administrador);
                    break;

                case 2:
                    gestorProductosAdministrador();
                    break;

                case 3:
                    gestionProveedoresAdministrador();
                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Cerrando sesión...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }


    public void gestorProductosAdministrador(){

        while (true) {

            System.out.println("Elija una opción");
            System.out.println("1. Cargar productos");
            System.out.println("2. Dar de baja productos");
            System.out.println("3. Dar de alta productos");
            System.out.println("4. Mostrar productos por coincidencia del nombre");
            System.out.println("5. Modificar datos de un producto disponible");
            System.out.println("6. Mostrar todos los productos disponibles");
            System.out.println("7. Buscar un producto por el código");
            System.out.println("8. Atrás");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    gestionProducto.cargarProductos(teclado);

                    break;

                case 2:
                    Producto darBaja = gestionProducto.elegirProductosDisponibles(teclado);
                    gestionProducto.darBajaProducto(darBaja);

                    break;

                case 3:
                    Producto darAlta = gestionProducto.elegirProductosDeBaja(teclado);
                    gestionProducto.darAltaProducto(darAlta);

                    break;

                case 4:
                    System.out.println("Ingrese el nombre de los productos que quiere mostrar");

                    try {
                        String nombreBusqueda = teclado.nextLine().toLowerCase();
                        gestionProducto.mostrarProductosPorCoincidencia(nombreBusqueda);

                    }catch (ObjetoNoEncontradoException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 5:
                    Producto modificar = gestionProducto.elegirProductosDisponibles(teclado);
                    gestionProducto.modificarProducto(modificar);

                    break;

                case 6:
                    gestionProducto.mostrarProductos();
                    break;

                case 7:
                    System.out.println("Ingrese el código del producto que quiere buscar");

                    try {
                        String codigo = teclado.nextLine();
                        Producto p = gestionProducto.buscarProductoPorCodigo(codigo);
                        System.out.println(p);

                    }catch (ObjetoNoEncontradoException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 8:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }


    public void gestionProveedoresAdministrador(){

        while (true){

            System.out.println("Elija una opción");
            System.out.println("1. Cargar proveedor");
            System.out.println("2. Dar de baja proveedor");
            System.out.println("3. Dar de alta proveedor");
            System.out.println("4. Mostrar proveedores por coincidencia del nombre");
            System.out.println("5. Mostrar todos los proveedores");
            System.out.println("6. Modificar datos de un proveedor disponible");
            System.out.println("7. Buscar proveedor por su ID");
            System.out.println("8. Atrás");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){

                case 1:
                    gestionProveedor.cargarProveedor(teclado);
                    break;

                case 2:
                    System.out.println("Elija alguno de los siguientes proveedores disponibles para darlos de baja");
                    Proveedor bajaProveedor = gestionProveedor.elegirProveedor(teclado);
                    gestionProveedor.darBajaProveedor(bajaProveedor);

                    break;

                case 3:
                    System.out.println("Elija alguno de los siguientes proveedores de baja para darlos de alta");
                    Proveedor altaProveedor = gestionProveedor.elegirProveedorDeBaja(teclado);
                    gestionProveedor.darAltaProveedor(altaProveedor);

                    break;

                case 4:
                    System.out.println("Ingrese el nombre de los proveedores que quiere mostrar");
                    String nombre = teclado.nextLine().toLowerCase();
                    gestionProveedor.mostrarProveedoresPorCoincidencia(nombre);

                    break;

                case 5:
                    gestionProveedor.mostrarProveedoresDisponibles();
                    break;

                case 6:
                    Proveedor pm = gestionProveedor.elegirProveedor(teclado);
                    gestionProveedor.modificarProveedor(pm, teclado);
                    break;

                case 7:
                    System.out.println("Ingrese la ID del proveedor que quiere buscar");

                    try {
                        String id = teclado.nextLine();
                        Proveedor p = gestionProveedor.buscarProveedoresPorId(id);
                        System.out.println(p);

                    }catch (ObjetoNoEncontradoException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 8:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }


    public void cuentaAdministrador(Administrador administrador){

        while (true) {

            System.out.println("Elija una opción");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Modificar mi cuenta");
            System.out.println("3. Eliminar cuenta");
            System.out.println("4. Dar de alta administrador");
            System.out.println("5. Atrás");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    gestionAdministrador.mostrarDatosUsuario(administrador);
                    break;

                case 2:
                    administrador = gestionAdministrador.modificarUsuario(administrador); // es necesario administrador = ...
                    break;

                case 3:
                    gestionAdministrador.darDeBajaUsuario(administrador);
                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }

        }
    }


    public void menuEmpleado(){

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
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }
}
