package Gestion;

import Excepciones.ProveedorNoEncontradoException;
import IngresoDeDatos.InputHelper;
import Productos.Producto;
import Transacciones.Compra;
import Transacciones.Venta;
import Users.LogInUser.LogIn;
import Users.Proveedor;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;

import java.io.FileNotFoundException;

public class GestionMenu {

    private GestionProducto gestionProducto;
    private GestionProveedor gestionProveedor;
    private LogIn logIn;
    private GestionAdministrador gestionAdministrador;
    private GestionUsuario gestionUsuario;
    private GestionVenta gestionVenta;
    private GestionCompra gestionCompra;

    public GestionMenu() {
        this.gestionProducto = new GestionProducto();
        this.gestionProveedor = new GestionProveedor(gestionProducto);
        this.gestionAdministrador = new GestionAdministrador();
        this.gestionUsuario = new GestionUsuario();
        this.gestionCompra = new GestionCompra();
        this.gestionVenta = new GestionVenta();
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
            System.out.println("3. Salir");

            int opcion = InputHelper.leerEnteroSwitch();

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

            int opcion = InputHelper.leerEnteroSwitch();

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

            System.out.println("\nElija una opción:");
            System.out.println("1. Mi cuenta");
            System.out.println("2. Gestión productos");
            System.out.println("3. Gestión proveedores");
            System.out.println("4. Gestión de usuarios");
            System.out.println("5. Gestión de stock");
            System.out.println("6. Gestión de compras");
            System.out.println("7. Gestión de ventas");
            System.out.println("8. Salir");

            int opcion = InputHelper.leerEnteroSwitch();

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
                    gestionStockAdministrador();
                    break;

                case 6:
                    gestionComprasAdministrador();
                    break;

                case 7:
                    gestionVentasAdministrador();
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

            System.out.println("\nElija una opción:");
            System.out.println("1. Cargar productos");
            System.out.println("2. Dar de baja productos");
            System.out.println("3. Dar de alta productos");
            System.out.println("4. Mostrar productos por coincidencia del nombre");
            System.out.println("5. Modificar datos de un producto disponible");
            System.out.println("6. Mostrar todos los productos disponibles");
            System.out.println("7. Buscar un producto por el código");
            System.out.println("8. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {
                case 1:
                    gestionProducto.cargarProductos();
                    break;

                case 2:
                    String codigoB = InputHelper.leerIDOCodigo("Ingrese el código del producto que quiere dar de baja");
                    Producto pB = gestionProducto.buscarProductoPorCodigo(codigoB);

                    if(pB != null){
                        gestionProducto.darBajaProducto(pB);
                    }

                    break;

                case 3:
                    String codigoA = InputHelper.leerIDOCodigo("Ingrese el código del producto que quiere dar de alta");
                    Producto pA = gestionProducto.buscarProductoPorCodigo(codigoA);

                    if(pA != null){
                        gestionProducto.darAltaProducto(pA);
                    }

                    break;

                case 4:
                    String nombre = InputHelper.leerNombreProducto("Ingrese el nombre de los productos que quiere mostrar");
                    gestionProducto.mostrarProductosPorCoincidencia(nombre);

                    break;

                case 5:
                    String codigo = InputHelper.leerIDOCodigo("Ingrese el código del producto que quiere modificar");
                    Producto pM = gestionProducto.buscarProductoPorCodigo(codigo);

                    if(pM != null){
                        gestionProducto.modificarProducto(pM);
                        pM.mostrarProducto();
                    }

                    break;

                case 6:
                    gestionProducto.mostrarTodosLosProductos();
                    break;

                case 7:
                    String codigoC = InputHelper.leerIDOCodigo("Ingrese el código del producto que quiere buscar");
                    Producto p = gestionProducto.buscarProductoPorCodigo(codigoC);

                    if(p != null){
                        p.mostrarProducto();
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

            System.out.println("\nElija una opción:");
            System.out.println("1. Cargar proveedor");
            System.out.println("2. Dar de baja proveedor");
            System.out.println("3. Dar de alta proveedor");
            System.out.println("4. Mostrar proveedores por coincidencia del nombre");
            System.out.println("5. Mostrar todos los proveedores");
            System.out.println("6. Modificar datos de un proveedor disponible");
            System.out.println("7. Buscar proveedor por su ID");
            System.out.println("8. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion){

                case 1:
                    gestionProveedor.cargarProveedor();
                    break;

                case 2:
                    String idB = InputHelper.leerIDOCodigo("Ingrese la ID del proveedor que quiere dar de baja");
                    Proveedor pB = null;

                    try {
                        pB = gestionProveedor.buscarProveedorPorId(idB);
                    } catch (ProveedorNoEncontradoException e) {
                        System.err.println("Error: " +e.getMessage());
                    }

                    if(pB != null){
                        gestionProveedor.darBajaProveedor(pB);
                    }

                    break;

                case 3:
                    String idA = InputHelper.leerIDOCodigo("Ingrese la ID del proveedor que quiere dar de alta");
                    Proveedor pA = null;

                    try {
                        pA = gestionProveedor.buscarProveedorPorId(idA);
                    } catch (ProveedorNoEncontradoException e) {
                        System.err.println("Error: " +e.getMessage());
                    }

                    if(pA != null){
                        gestionProveedor.darAltaProveedor(pA);
                    }

                    break;

                case 4:
                    String nombre = InputHelper.pedirString("Ingrese un nombre: ");

                    try {
                        gestionProveedor.mostrarProveedoresPorCoincidencia(nombre);
                    } catch (ProveedorNoEncontradoException e) {
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 5:
                    gestionProveedor.mostrarTodosLosProveedores();
                    break;

                case 6:
                    String idC = InputHelper.leerIDOCodigo("Ingrese la ID del proveedor que quiere modificar");
                    Proveedor pM = null;

                    try {
                        pM = gestionProveedor.buscarProveedorPorId(idC);
                    } catch (ProveedorNoEncontradoException e) {
                        System.err.println("Error: " +e.getMessage());
                    }

                    if(pM != null){
                        gestionProveedor.modificarProveedor(pM);
                    }

                    break;

                case 7:
                    String id = InputHelper.leerIDOCodigo("Ingrese la ID del proveedor que quiere buscar:");
                    Proveedor p = null;

                    try {
                        p = gestionProveedor.buscarProveedorPorId(id);
                    } catch (ProveedorNoEncontradoException e) {
                        System.err.println("Error: " +e.getMessage());
                    }

                    if(p != null){
                        gestionProveedor.mostrarDatosProveedor(p);
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

            System.out.println("\nElija una opción:");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Modificar mi cuenta");
            System.out.println("3. Eliminar cuenta");
            System.out.println("4. Dar de alta administrador");
            System.out.println("5. Mostrar todos los administradores");
            System.out.println("6. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

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
                    String dni = InputHelper.pedirDni("administrador", "Ingrese el DNI del administrador que quiere dar de alta.");
                    Administrador aux = gestionAdministrador.encontrarUsuario(dni);

                    if (aux != null) {
                        gestionAdministrador.darDeAltaUsuario(aux);
                        System.out.println("El administrador fue dado de alta correctamente.");
                    } else {
                        System.out.println("No se encontró ningún administrador con ese DNI.");
                    }

                    break;

                case 5:
                    gestionAdministrador.mostrarTodosLosUsuarios();
                    break;

                case 6:
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

            System.out.println("\nElija una opción:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Dar de baja usuario");
            System.out.println("3. Dar de alta usuario");
            System.out.println("4. Buscar un usuario por su DNI");
            System.out.println("5. Mostrar todos los usuarios");
            System.out.println("6. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {

                case 1:
                    gestionUsuario.ingresarUsuario();
                    break;

                case 2:
                    String dni1 = InputHelper.pedirDni("usuario", "Ingrese el DNI del usuario que quiere dar de baja.");
                    Usuario aux1 = gestionUsuario.encontrarUsuario(dni1);

                    if (aux1 != null) {
                        gestionUsuario.darDeBajaUsuario(aux1);
                    } else {
                        System.out.println("No se encontró ningún usuario con ese DNI.");
                    }

                    break;

                case 3:
                    String dni2 = InputHelper.pedirDni("usuario", "Ingrese el DNI del usuario que quiere dar de alta.");
                    Usuario aux2 = gestionUsuario.encontrarUsuario(dni2);

                    if (aux2 != null) {
                        gestionUsuario.darDeAltaUsuario(aux2);
                    } else {
                        System.out.println("No se encontró ningún usuario con ese DNI.");
                    }

                    break;

                case 4:
                    String dni3 = InputHelper.pedirDni("usuario", "Ingrese el DNI del usuario que está buscando");
                    Usuario aux3 = gestionUsuario.encontrarUsuario(dni3);

                    if (aux3 != null) {
                        System.out.println("El usuario fue encontrado exitosamente. Mostrando perfil...");
                        gestionUsuario.mostrarDatosUsuario(aux3);
                    } else {
                        System.out.println("No se encontró ningún usuario con ese DNI.");
                    }

                    break;

                case 5:
                    gestionUsuario.mostrarTodosLosUsuarios();
                    break;

                case 6:
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

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion){
                case 1:
                    cuentaUsuario(usuario);
                    break;

                case 2:
                    gestionVenta.cargarOrdenVenta();
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

            System.out.println("\nElija una opción:");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Modificar mi cuenta");
            System.out.println("3. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

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


    public void gestionComprasAdministrador() {

        while (true) {

            System.out.println("\nElija una opción:");
            System.out.println("1. Generar orden de compra");
            System.out.println("2. Cancelar orden de compra");
            System.out.println("3. Mostrar ordenes de compra");
            System.out.println("4. Modificar orden de compra");
            System.out.println("5. Buscar orden de compra");
            System.out.println("6. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {

                case 1:
                    gestionCompra.cargarCompra();
                    break;

                case 2:
                    String id = InputHelper.leerIDOCodigo("Ingrese el ID de la compra que quiere cancelar: ");

                    try {
                        gestionCompra.cancelarCompra(id);

                    }catch (IllegalArgumentException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 3:
                    gestionCompra.mostrarCompras();
                    break;

                case 4:
                    String idA = InputHelper.leerIDOCodigo("Ingrese el ID de la compra que quiere modificar: ");

                    try {
                        Compra modificarCompra = gestionCompra.buscarPorId(idA);
                        gestionCompra.modificarCompra(modificarCompra);

                    }catch (IllegalArgumentException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 5:
                    String idB = InputHelper.leerIDOCodigo("Ingrese el ID de la compra que quiere buscar: ");

                    try {
                        Compra compra = gestionCompra.buscarPorId(idB);
                        compra.mostrarCompra();

                    }catch (IllegalArgumentException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 6:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }


    public void gestionVentasAdministrador() {

        while (true) {

            System.out.println("\nElija una opción:");
            System.out.println("1. Cargar orden de venta");
            System.out.println("2. Cancelar orden de venta");
            System.out.println("3. Mostrar ordenes de venta");
            System.out.println("4. Modificar orden de venta");
            System.out.println("5. Buscar orden de venta");
            System.out.println("6. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {

                case 1:
                    gestionVenta.cargarOrdenVenta();
                    break;

                case 2:
                    String idCancelar = InputHelper.leerIDOCodigo("Ingrese el ID de la venta a cancelar:");

                    try {
                        Venta cancelarVenta = gestionVenta.buscarOrdenVentaPorId(idCancelar);
                        gestionVenta.cancelarOrdenDeVenta(cancelarVenta);

                    }catch (IllegalArgumentException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 3:
                    gestionVenta.mostrarOrdenesVenta();
                    break;

                case 4:
                    String idMod = InputHelper.leerIDOCodigo("Ingrese el ID de la venta a modificar:");

                    try {
                        Venta modificarVenta = gestionVenta.buscarOrdenVentaPorId(idMod);
                        gestionVenta.modificarOrdenVenta(modificarVenta);

                    }catch (IllegalArgumentException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 5:
                    String idBusc = InputHelper.leerIDOCodigo("Ingrese el ID de la venta a buscar:");

                    try {
                        Venta buscarVenta = gestionVenta.buscarOrdenVentaPorId(idBusc);
                        buscarVenta.mostrarVenta();

                    }catch (IllegalArgumentException e){
                        System.err.println("Error: " +e.getMessage());
                    }

                    break;

                case 6:
                    System.out.println("Volviendo al menú anterior...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }


    public void gestionStockAdministrador(){
        while (true) {

            System.out.println("\nElija una opción:");
            System.out.println("1. Mostrar stock de un producto");
            System.out.println("2. Atrás");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {

                case 1:
                    String codigo = InputHelper.leerIDOCodigo("Ingrese el código del producto para consultar su stock:");
                    Producto p = gestionProducto.buscarProductoPorCodigo(codigo);

                    if(p != null) {
                        System.out.println("Código: " + p.getCodigo() + " - Nombre: " + p.getNombre() + " - Stock: " + p.getStock());
                    }

                    break;

                case 2:
                    System.out.println("Volviendo al menú anterior...");
                    return;

                default:
                    System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    break;
            }
        }
    }
}