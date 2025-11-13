package Gestion;

import Archivos.GestionJSONUsers.GestionJSONProveedor;
import Enums.TipoProveedor;
import Excepciones.DatoInvalidoException;
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
                    System.out.println("5. Tipo de proveedor");
                    System.out.println("6. Salir");

                    int opcion = teclado.nextInt();
                    teclado.nextLine();

                    switch (opcion) {

                        case 1:

                            String nombre = "";

                            while (true){

                                System.out.println("Nombre: ");

                                try {
                                    nombre = teclado.nextLine();
                                    Validaciones.validarString(nombre);
                                    p.setNombre(nombre);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 2:

                            String apellido = "";

                            while (true){

                                System.out.println("Apellido: ");

                                try {
                                    apellido = teclado.nextLine();
                                    Validaciones.validarString(apellido);
                                    p.setApellido(apellido);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 3:

                            String email = "";

                            while (true){

                                System.out.println("Email: ");

                                try {
                                    email = teclado.nextLine();
                                    Validaciones.validarEmail(email);
                                    p.setEmail(email);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 4:

                            String telefono = "";

                            while (true){

                                System.out.println("Teléfono: ");

                                try {
                                    telefono = teclado.nextLine();
                                    Validaciones.validarTelefono(telefono);
                                    p.setTelefono(telefono);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 5:
                            System.out.println("Elija el nuevo tipo de proveedor");
                            TipoProveedor tipoProveedor = elegirTipoProveedor();
                            p.setTipoProveedor(tipoProveedor);

                            break;

                        case 6:
                            System.out.println("Saliendo del apartado de modificación...");
                            salir = true;
                            break;

                        default:
                            System.out.println("Opción invalida. Por favor, inténtelo nuevamente");
                            break;
                    }
                }

                listaProveedores.add(p);
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


    public void mostrarProveedoresDisponibles(){

        for (Proveedor p : listaProveedores){
            System.out.println(p);
        }
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

        System.out.println("Ingrese el nombre del proveedor: ");
        String nombre = "";

        while (true){

            try {
                nombre = teclado.nextLine();
                Validaciones.validarString(nombre);

                break;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        System.out.println("Ingrese el apellido: ");
        String apellido = "";

        while (true){

            try {
                apellido = teclado.nextLine();
                Validaciones.validarString(apellido);

                break;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        System.out.println("Ingrese un e-mail: ");
        String email = "";

        while (true){

            try {
                email = teclado.nextLine();
                Validaciones.validarEmail(email);

                break;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        System.out.println("Ingrese un teléfono: ");
        String telefono = "";

        while (true){

            try {
                telefono = teclado.nextLine();
                Validaciones.validarTelefono(telefono);

                break;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        System.out.println("Ingrese el CUIT: ");
        String cuit = "";

        while (true){

            try {
                cuit = teclado.nextLine();
                Validaciones.validarCuit(cuit);

                break;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        System.out.println("Elija el tipo de proveedor: ");
        TipoProveedor tipoProveedor = elegirTipoProveedor();

        System.out.println("¿Desea cargar productos nuevos (1) o elegir algunos ya existentes (2)?");
        int opcion = teclado.nextInt();
        HashMap<String, Producto> nuevosProductos = new HashMap<>();
        Producto producto = null;

        while (true){

            if (opcion == 1){
                nuevosProductos = gestionProducto.cargarProductos();
                break;

            }else if(opcion == 2){

                while (true){

                    producto = gestionProducto.elegirProductosDisponibles();
                    nuevosProductos.put(producto.getCodigo(), producto);
                    System.out.println("Producto agregado correctamente");

                    int op;

                    while (true) {
                        System.out.println("Si desea salir presione 1, de otra manera presione cualquier otro número");

                        try {
                            op = teclado.nextInt();
                            teclado.nextLine();
                            break;

                        } catch (InputMismatchException e) {
                            System.out.println("Opción inválida. Por favor ingrese un número.");
                            teclado.nextLine();
                        }
                    }

                    if (op == 1) {
                        break;
                    }
                }

                break;

            }else {
                System.out.println("Opción invalida. Por favor, ingrese una opción valida");
            }
        }

        Proveedor proveedor = new Proveedor(nombre, apellido, email, telefono, cuit, tipoProveedor);
        proveedor.agregarProductos(nuevosProductos);
        agregarProveedor(proveedor);
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
                    System.out.println("Opción invalida.Intentelo nuevamente");
                    break;
            }
        }

        return tipoProveedor;
    }

    public void mostrarDatosProveedor(Proveedor proveedor){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        boolean encontrado = false;

        for (Proveedor p : listaProveedores){
            if (p.getIdProveedor().equals(proveedor.getIdProveedor())){
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE PROVEEDOR: " + p.getNombreCompleto());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + p.getIdProveedor());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Apellido: " +p.getApellido());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Teléfono: " + p.getTelefono());
                System.out.println("CUIT: " + p.getCuit());
                System.out.println("Fecha de alta: " + p.getFechaAlta());
                System.out.println("Tipo de proveedor: " + p.getTipoProveedor());
                System.out.println("--------------------------------------------");

                encontrado = true;
                break;
            }
        }

        if(!encontrado){
            System.out.print("No se encontró al proveedor");
        }
    }
}
