package Gestion;

import Archivos.GestionJSONUsers.GestionJSONProveedor;
import Enums.TipoProveedor;
import IngresoDeDatos.InputHelper;
import Productos.Producto;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

public class GestionProveedor {

    private HashSet<Proveedor> listaProveedores;
    private GestionProducto gestionProducto;
    private Scanner teclado;

    public GestionProveedor() {
        this.listaProveedores = new HashSet<>();
        this.gestionProducto = new GestionProducto();
        this.teclado = new Scanner(System.in);
    }

    public void agregarProveedor(Proveedor nuevoProveedor){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");
        listaProveedores.add(nuevoProveedor);
        GestionJSONProveedor.listaProveedorToArchivo(listaProveedores, "proveedor.json");
    }

    public void modificarProveedor(Proveedor proveedor){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");
        boolean salir = false;

        for (Proveedor p : listaProveedores) {

            if (p.getIdProveedor().equals(proveedor.getIdProveedor())) {

                while (!salir) {

                    System.out.println("¿Qué desea modificar?");
                    System.out.println("1. Nombre");
                    System.out.println("2. Apellido");
                    System.out.println("3. E-mail");
                    System.out.println("4. Teléfono");
                    System.out.println("4. CUIT");
                    System.out.println("6. Tipo de proveedor");
                    System.out.println("7. Salir");

                    int opcion;

                    try {
                        opcion = Integer.parseInt(teclado.nextLine());
                    } catch(NumberFormatException e) {
                        System.err.println("Debe ingresar un número.");
                        continue;
                    }

                    switch (opcion) {

                        case 1:
                            p.setNombre(InputHelper.pedirString("Nuevo nombre:"));
                            break;

                        case 2:
                            p.setApellido(InputHelper.pedirString("Nuevo apellido:"));
                            break;

                        case 3:
                            p.setEmail(InputHelper.pedirEmail("Nuevo email:"));
                            break;

                        case 4:
                            p.setCuit(InputHelper.pedirCuit("Nuevo CUIT:"));
                            break;

                        case 5:
                            p.setTelefono(InputHelper.pedirTelefono("Nuevo teléfono:"));
                            break;

                        case 6:
                            System.out.println("Elija el nuevo tipo de proveedor:");
                            p.setTipoProveedor(elegirTipoProveedor());
                            break;

                        case 7:
                            System.out.println("Saliendo del apartado de modificación...");
                            salir = true;
                            break;

                        default:
                            System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }

                GestionJSONProveedor.listaProveedorToArchivo(listaProveedores, "proveedor.json");
                System.out.println("¡Datos cambiados con éxito!");
                return;
            }
        }

        System.out.println("No se encontró ningún proveedor con ese ID.");
    }


    public void darBajaProveedor(Proveedor p){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        for(Proveedor proveedor : listaProveedores){
            if(proveedor.equals(p)){
                System.out.println("¿Estás seguro de que quieres dar de baja a este proveedor? (si / no)");
                String opcion = teclado.nextLine();

                while (true){

                    if(opcion.equalsIgnoreCase("si")){

                        p.setActivo(false);
                        System.out.println("¡Proveedor dado de baja con éxito!");
                        GestionJSONProveedor.listaProveedorToArchivo(listaProveedores,"proveedor.json");

                        return;

                    }else if (opcion.equalsIgnoreCase("no")) {
                        System.out.println("Operación cancelada");
                        return;

                    }else{
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    }
                }
            }
        }

        System.out.println("No se encontró al proveedor");
    }

    public void darAltaProveedor(Proveedor p){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        for(Proveedor proveedor : listaProveedores){
            if(proveedor.equals(p)){
                System.out.println("¿Estás seguro de que quieres dar de alta a este proveedor? (si / no)");
                String opcion = teclado.nextLine();

                while (true){

                    if(opcion.equalsIgnoreCase("si")){

                        p.setActivo(true);
                        System.out.println("¡Proveedor dado de alta con éxito!");
                        GestionJSONProveedor.listaProveedorToArchivo(listaProveedores,"proveedor.json");

                        return;

                    }else if (opcion.equalsIgnoreCase("no")) {
                        System.out.println("Operación cancelada");
                        return;

                    }else{
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    }
                }
            }
        }

        System.out.println("No se encontró al proveedor");
    }

    public void mostrarProveedoresPorCoincidencia(String nombre){

        boolean encontrado = false;

        for(Proveedor p : listaProveedores){
            if(p.getNombre().toLowerCase().contains(nombre)){
                System.out.println("ID: " + p.getIdProveedor() + ", Nombre: " + p.getNombreCompleto());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron proveedores que coincidan con ese nombre");
        }
    }

    public Proveedor buscarProveedoresPorId(String id) {

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        boolean encontrado = false;
        Proveedor proveedor = null;

        for(Proveedor p : listaProveedores){
            if(p.getIdProveedor().equals(id)){
                proveedor = p;
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron proveedores con esa ID");
        }

        return proveedor;
    }

    public void cargarProveedor(){

        String nombre = InputHelper.pedirString("Ingrese el nombre del proveedor:");
        String apellido = InputHelper.pedirString("Ingrese el apellido:");
        String email = InputHelper.pedirEmail("Ingrese un e-mail:");
        String telefono = InputHelper.pedirTelefono("Ingrese un teléfono:");
        String cuit = InputHelper.pedirCuit("Ingrese el CUIT:");

        System.out.println("Elija el tipo de proveedor: ");
        TipoProveedor tipoProveedor = elegirTipoProveedor();

        HashMap<String, Producto> nuevosProductos = cargarProductosParaProveedor();

        Proveedor proveedor = new Proveedor(nombre, apellido, email, telefono, cuit, tipoProveedor);
        proveedor.agregarProductos(nuevosProductos);

        agregarProveedor(proveedor);
    }

    private HashMap<String, Producto> cargarProductosParaProveedor() {

        HashMap<String, Producto> nuevosProductos = new HashMap<>();

        int opcion;
        while (true) {

            System.out.println("¿Desea cargar productos nuevos (1) o elegir algunos ya existentes (2)?");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Debe ingresar un número.");
                continue;
            }

            if (opcion == 1) {
                return gestionProducto.cargarProductos();
            }

            if (opcion == 2) {
                while (true) {

                    Producto producto = gestionProducto.elegirProductosDisponibles();
                    nuevosProductos.put(producto.getCodigo(), producto);
                    System.out.println("Producto agregado correctamente.");

                    int opSalir;

                    while (true) {
                        System.out.println("Para dejar de agregar productos presione 1. Para continuar, otro número.");

                        try {
                            opSalir = Integer.parseInt(teclado.nextLine());
                            break;

                        } catch (NumberFormatException e) {
                            System.err.println("Opción inválida. Ingrese un número.");
                        }
                    }

                    if (opSalir == 1) break;
                }

                return nuevosProductos;
            }

            System.out.println("Opción inválida. Ingrese 1 o 2.");
        }
    }


    public TipoProveedor elegirTipoProveedor(){

        TipoProveedor tipoProveedor = null;

        boolean salir = true;

        while (salir) {

            System.out.println("1. Mayorista");
            System.out.println("2. Minorista");
            System.out.println("3. Fabricante");
            System.out.println("4. Importador");
            System.out.println("5. Ensamblador");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    tipoProveedor = TipoProveedor.MAYORISTA;
                    salir = false;
                    break;

                case 2:
                    tipoProveedor = TipoProveedor.MINORISTA;
                    salir = false;
                    break;

                case 3:
                    tipoProveedor = TipoProveedor.FABRICANTE;
                    salir = false;
                    break;

                case 4:
                    tipoProveedor = TipoProveedor.IMPORTADOR;
                    salir = false;
                    break;

                case 5:
                    tipoProveedor = TipoProveedor.ENSAMBLADOR;
                    salir = false;

                default:
                    System.out.println("Opción invalida.Inténtelo nuevamente");
                    break;
            }
        }

        return tipoProveedor;
    }

    public void mostrarDatosProveedor(Proveedor proveedor) {

        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("PERFIL DE PROVEEDOR: " + proveedor.getNombreCompleto());
        System.out.println("--------------------------------------------");

        System.out.println("ID: " + proveedor.getIdProveedor());
        System.out.println("Nombre: " + proveedor.getNombre());
        System.out.println("Apellido: " + proveedor.getApellido());
        System.out.println("Email: " + proveedor.getEmail());
        System.out.println("Teléfono: " + proveedor.getTelefono());
        System.out.println("CUIT: " + proveedor.getCuit());
        System.out.println("Fecha de alta: " + proveedor.getFechaAlta());
        System.out.println("Tipo de proveedor: " + proveedor.getTipoProveedor());
        System.out.println("--------------------------------------------");
    }


    public Proveedor elegirProveedorDisponible(){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");
        ArrayList<Proveedor> proveedores = new ArrayList<>(listaProveedores);

        System.out.println("=== Lista de Proveedores ===");
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).isActivo()){
                System.out.println(i+1 + ". " + proveedores.get(i).getNombre() + " " + proveedores.get(i).getApellido());
            }
        }

        int opcion = ingresarOpcionValida(proveedores);

        return proveedores.get(opcion-1);
    }


    public int ingresarOpcionValida(List<Proveedor> lista){

        int opcion;

        while (true){

            try {
                opcion = InputHelper.leerInt("Ingrese una opción del 1 al " +lista.size());
                Validaciones.ingresarOpcionValida(lista, opcion);

                break;

            }catch (IndexOutOfBoundsException e){
                System.err.println("Error: " +e.getMessage());

            }catch (InputMismatchException e){
                System.err.println("Error: Debe ingresar un número");

                teclado.nextLine();
            }
        }

        return opcion;
    }

    public void mostrarTodosLosProveedores() {

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        System.out.println("--- Lista de Proveedores ---");
        for (Proveedor proveedor : listaProveedores) {
            System.out.println("ID: " + proveedor.getIdProveedor());
            System.out.println("Nombre: " + proveedor.getNombre() + " " + proveedor.getApellido());
            System.out.println("Email: " + proveedor.getEmail());
            System.out.println("Teléfono: " + proveedor.getTelefono());
            System.out.println("CUIT: " + proveedor.getCuit());
            System.out.println("Tipo: " + proveedor.getTipoProveedor());
            System.out.println("Activo: " + (proveedor.isActivo() ? "Sí" : "No"));
            System.out.println("Fecha de alta: " + proveedor.getFechaAlta());
            System.out.println("Cantidad de productos suministrados: " + proveedor.getProductosSuministrados().size());
            System.out.println("----------------------------------");
        }
    }

}
