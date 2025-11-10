package Gestion;

import Excepciones.DatoInvalidoException;
import Productos.Producto;
import Users.LogInUser.LogIn;
import Users.Proveedor;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;
import Validaciones.Validaciones;

import java.io.FileNotFoundException;
import java.util.*;

public class GestionMenu {

    private Scanner teclado;
    private GestionProducto gestionProducto;
    private GestionProveedor gestionProveedor;
    private LogIn logIn;
    private GestionAdministrador gestionAdministrador;
    private GestionUsuario gestionUsuario;

    public GestionMenu() {
        this.teclado = new Scanner(System.in);
        this.gestionProducto = new GestionProducto();
        this.gestionProveedor = new GestionProveedor();
        this.gestionAdministrador = new GestionAdministrador();
        this.gestionUsuario = new GestionUsuario();
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
            System.out.println("2. Usuario");
            System.out.println("3. Salir.");

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    valido = true;
                    menuAdministrador();
                    break;

                case 2:
                    valido = true;
                    menuUsuario();
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

                        Administrador administrador = logIn.inicioSesionAdministrador("administrador.json");

                        if(administrador == null){

                            System.out.println("Error: Usuario no existente. Volviendo al menú principal");
                            salir = true;
                            menuPrincipal();

                            break;
                        }

                        System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + administrador.getUserName() + "!");
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

            System.out.println("=================================================");
            System.out.println("¡BIENVENIDO/A " +administrador.getNombre()+ " " +administrador.getApellido()+"!");
            System.out.println("=================================================");

            System.out.println("Elija una opción");
            System.out.println("1. Mi cuenta");
            System.out.println("2. Gestión productos");
            System.out.println("3. Gestión proveedores");
            System.out.println("4. Gestión de usuarios");
            System.out.println("5. Gestión stock");
            System.out.println("6. Gestión de ventas");
            System.out.println("7. Gestión de compras");
            System.out.println("8. Salir");

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
                    gestionUsuariosAdministrador();
                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:
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
                    gestionProducto.cargarProductos();
                    break;

                case 2:
                    Producto darBaja = gestionProducto.elegirProductosDisponibles();
                    gestionProducto.darBajaProducto(darBaja);

                    break;

                case 3:
                    Producto darAlta = gestionProducto.elegirProductosDeBaja();
                    gestionProducto.darAltaProducto(darAlta);

                    break;

                case 4:
                    System.out.println("Ingrese el nombre de los productos que quiere mostrar");
                    String nombre = "";

                    while (true){

                        try {
                            nombre = teclado.nextLine();
                            Validaciones.validarString(nombre);
                            gestionProducto.mostrarProductosPorCoincidencia(nombre);

                            break;

                        }catch (DatoInvalidoException e){
                            System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                        }
                    }

                    break;

                case 5:
                    Producto modificar = gestionProducto.elegirProductosDisponibles();
                    gestionProducto.modificarProducto(modificar);

                    break;

                case 6:
                    gestionProducto.mostrarProductos();
                    break;

                case 7:
                    System.out.println("Ingrese el código del producto que quiere buscar");
                    String codigo = "";

                    while (true){

                        try {
                            codigo = teclado.nextLine();
                            Validaciones.validarID(codigo);
                            Producto p = gestionProducto.buscarProductoPorCodigo(codigo);

                            if(p != null){
                                System.out.println(p);
                            }

                            break;

                        }catch (DatoInvalidoException e){
                            System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                        }
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
                    gestionProveedor.cargarProveedor();
                    break;

                case 2:
                    System.out.println("Elija alguno de los siguientes proveedores disponibles para darlos de baja");
                    Proveedor bajaProveedor = gestionProveedor.elegirProveedor();
                    gestionProveedor.darBajaProveedor(bajaProveedor);

                    break;

                case 3:
                    System.out.println("Elija alguno de los siguientes proveedores de baja para darlos de alta");
                    Proveedor altaProveedor = gestionProveedor.elegirProveedorDeBaja(); //elegirlos o buscarlos como en usuario por la id y de ahi darlos de baja o alta?
                    gestionProveedor.darAltaProveedor(altaProveedor);

                    break;

                case 4:
                    System.out.println("Ingrese un nombre: ");

                    String nombre = "";

                    while (true){

                        try {
                            nombre = teclado.nextLine();
                            Validaciones.validarString(nombre);
                            gestionProveedor.mostrarProveedoresPorCoincidencia(nombre);

                            break;

                        }catch (DatoInvalidoException e){
                            System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                        }
                    }

                    break;

                case 5:
                    gestionProveedor.mostrarProveedoresDisponibles();
                    break;

                case 6:
                    Proveedor pm = gestionProveedor.elegirProveedor();
                    gestionProveedor.modificarProveedor(pm);
                    break;

                case 7:
                    System.out.println("Ingrese la ID del proveedor que quiere buscar");
                    String id = "";

                    while (true){

                        try {
                            id = teclado.nextLine();
                            Validaciones.validarID(id);
                            Proveedor p = gestionProveedor.buscarProveedoresPorId(id);

                            if(p != null){
                                gestionProveedor.mostrarDatosProveedor(p);
                            }

                            break;

                        }catch (DatoInvalidoException e){
                            System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                        }
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
                    gestionAdministrador.modificarUsuario(administrador);
                    break;

                case 3:
                    gestionAdministrador.darDeBajaUsuario(administrador);
                    break;

                case 4:
                    System.out.println("Ingrese el DNI del administrador que quiere dar de alta.");
                    String dni = teclado.nextLine();

                    Administrador aux = gestionAdministrador.encontrarUsuario(dni);

                    if (aux != null) {
                        gestionAdministrador.darDeAltaUsuario(aux);
                        System.out.println("El administrador fue dado de alta correctamente.");
                    } else {
                        System.out.println("No se encontró ningún administrador con ese DNI.");
                    }

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


    public void gestionUsuariosAdministrador(){

        while (true) {

            System.out.println("Elija una opción");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Dar de baja usuario");
            System.out.println("3. Dar de alta usuario");
            System.out.println("4. Buscar un usuario por su DNI");
            System.out.println("5. Atrás");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    gestionUsuario.ingresarUsuario();
                    break;

                case 2:
                    System.out.println("Ingrese el DNI del usuario que quiere dar de baja.");
                    String dni1 = teclado.nextLine();

                    Usuario aux1 = gestionUsuario.encontrarUsuario(dni1);

                    if (aux1 != null) {
                        gestionUsuario.darDeBajaUsuario(aux1);
                        System.out.println("El usuario fue dado de baja correctamente.");
                    } else {
                        System.out.println("No se encontró ningún usuario con ese DNI.");
                    }

                    break;

                case 3:
                    System.out.println("Ingrese el DNI del usuario que quiere dar de alta.");
                    String dni2 = teclado.nextLine();

                    Usuario aux2 = gestionUsuario.encontrarUsuario(dni2);

                    if (aux2 != null) {
                        gestionUsuario.darDeAltaUsuario(aux2);
                        System.out.println("El usuario fue dado de alta correctamente.");
                    } else {
                        System.out.println("No se encontró ningún usuario con ese DNI.");
                    }

                    break;

                case 4:
                    System.out.println("Ingrese el DNI del usuario que está buscando");
                    String dni3 = teclado.nextLine();

                    Usuario aux3 = gestionUsuario.encontrarUsuario(dni3);

                    if (aux3 != null) {
                        System.out.println("El usuario fue encontrado exitosamente. Mostrando perfil...");
                        gestionUsuario.mostrarDatosUsuario(aux3);
                    } else {
                        System.out.println("No se encontró ningún usuario con ese DNI.");
                    }

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

    public void menuUsuario(){

        System.out.println("=====================");
        System.out.println("MENÚ USUARIO");
        System.out.println("=====================");

        try {
            Usuario usuario = logIn.inicioSesionUsuario("usuario.json");

            if(usuario != null){
                menuInicioDeSesionUsuario(usuario);

            }else{
                System.out.println("El usuario no existe o es invalido");
            }

        }catch (FileNotFoundException e){
            System.out.println("Error: " +e.getMessage());
        }
    }



    public void menuInicioDeSesionUsuario(Usuario usuario){

        boolean salir = true;

        while (salir){

            System.out.println("=================================================");
            System.out.println("¡BIENVENIDO/A " +usuario.getNombre()+ " " +usuario.getApellido()+"!");
            System.out.println("=================================================");
            System.out.println("1. Mi cuenta");
            System.out.println("2. Generar orden de venta");
            System.out.println("3. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    cuentaUsuario(usuario);
                    break;

                case 2:

                    break;

                case 3:
                    System.out.println("Saliendo del menú...");
                    salir = false;
                    break;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }


    public void cuentaUsuario(Usuario usuario) {

        while (true) {

            System.out.println("Elija una opción");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Modificar mi cuenta");
            System.out.println("3. Atrás");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    gestionUsuario.mostrarDatosUsuario(usuario);
                    break;

                case 2:
                    gestionUsuario.modificarUsuario(usuario);
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }
}
