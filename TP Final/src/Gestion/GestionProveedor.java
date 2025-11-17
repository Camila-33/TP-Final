package Gestion;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Archivos.GestionJSONUsers.GestionJSONProveedor;
import Enums.TipoProveedor;
import IngresoDeDatos.InputHelper;
import Productos.Producto;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

import static IngresoDeDatos.InputHelper.teclado;

public class GestionProveedor {

    private HashSet<Proveedor> listaProveedores;
    private GestionProducto gestionProducto;

    public GestionProveedor(GestionProducto gestionProducto) {
        this.listaProveedores = new HashSet<>();
        this.gestionProducto = gestionProducto;
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
                    System.out.println("4. CUIT");
                    System.out.println("5. Teléfono");
                    System.out.println("6. Tipo de proveedor");
                    System.out.println("7. ");
                    System.out.println("7. Salir");

                    int opcion = InputHelper.leerInt("Elija una opción");

                    switch (opcion) {

                        case 1:
                            p.setNombre(InputHelper.pedirString("Nuevo nombre:"));
                            break;

                        case 2:
                            p.setApellido(InputHelper.pedirString("Nuevo apellido:"));
                            break;

                        case 3:
                            p.setEmail(InputHelper.pedirEmail("proveedor", "Nuevo email:"));
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

        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.equals(p)) {

                if (!proveedor.isActivo()) {
                    System.err.println("Error: el proveedor ya está dado de baja.");
                    return;
                }

                char opcion = InputHelper.leerChar("¿Estás seguro de que quieres dar de baja a este proveedor? (s / n)");

                if (opcion == 's') {
                    proveedor.setActivo(false);
                    GestionJSONProveedor.listaProveedorToArchivo(listaProveedores, "proveedor.json");
                    System.out.println("¡Proveedor dado de baja con éxito!");

                } else {
                    System.out.println("Operación cancelada");
                }
                return;
            }
        }

        System.out.println("No se encontró al proveedor");
    }

    public void darAltaProveedor(Proveedor p){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.equals(p)) {

                if (proveedor.isActivo()) {
                    System.err.println("Error: el proveedor ya está dado de alta.");
                    return;
                }

                char opcion = InputHelper.leerChar("¿Estás seguro de que quieres dar de alta a este proveedor? (s / n)");

                if (opcion == 's') {
                    proveedor.setActivo(true);
                    GestionJSONProveedor.listaProveedorToArchivo(listaProveedores, "proveedor.json");
                    System.out.println("¡Proveedor dado de alta con éxito!");

                } else {
                    System.out.println("Operación cancelada");
                }

                return;
            }
        }

        System.out.println("No se encontró al proveedor");
    }


    public void mostrarProveedoresPorCoincidencia(String nombre){

        boolean encontrado = false;

        for(Proveedor p : listaProveedores){
            if(p.getNombre().toLowerCase().contains(nombre)){
                mostrarDatosProveedor(p);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron proveedores que coincidan con ese nombre");
        }
    }

    public Proveedor buscarProveedorPorId(String id) {

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

    public void cargarProveedor() {

        String nombre = InputHelper.pedirString("Ingrese el nombre del proveedor:");
        String apellido = InputHelper.pedirString("Ingrese el apellido:");
        String email = InputHelper.pedirEmail("proveedor", "Ingrese un e-mail:");
        String telefono = InputHelper.pedirTelefono("Ingrese un teléfono:");
        String cuit = InputHelper.pedirCuit("Ingrese el CUIT:");

        System.out.println("Elija el tipo de proveedor: ");
        TipoProveedor tipoProveedor = elegirTipoProveedor();

        Proveedor proveedor = new Proveedor(nombre, apellido, email, telefono, cuit, tipoProveedor);

        HashMap<String, Producto> nuevosProductos = cargarProductosParaProveedor(proveedor);
        proveedor.agregarProductos(nuevosProductos);

        gestionProducto.actualizarProveedoresDeProductoJSON(proveedor, nuevosProductos);

        agregarProveedor(proveedor);

        System.out.println("Proveedor cargado correctamente con " + nuevosProductos.size() + " productos asociados.");
    }


    private HashMap<String, Producto> cargarProductosParaProveedor(Proveedor proveedor) {
        HashMap<String, Producto> nuevosProductos = new HashMap<>();

        while (true) {
            System.out.println();
            int opcion = InputHelper.leerInt("¿Desea cargar productos nuevos (1) o elegir algunos ya existentes (2)?");

            if (opcion == 1) {
                HashMap<String, Producto> productosCargados = gestionProducto.cargarProductos();
                nuevosProductos.putAll(productosCargados);
                return nuevosProductos;
            }

            if (opcion == 2) {
                while (true) {
                    Producto producto = gestionProducto.elegirProductosDisponibles();

                    HashMap<String, Producto> listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");
                    Producto productoJSON = listaProductos.get(producto.getCodigo());

                    if (productoJSON != null) {
                        nuevosProductos.put(productoJSON.getCodigo(), productoJSON);
                        System.out.println("Producto agregado correctamente.");

                    } else {
                        System.err.println("Error: producto no encontrad.");
                    }

                    System.out.println("Para dejar de agregar productos presione 1. Para continuar, otro número.");
                    int opSalir = InputHelper.leerEnteroSwitch();
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

            int opcion = InputHelper.leerEnteroSwitch();

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
            }
        }

        return opcion;
    }

    public void mostrarTodosLosProveedores() {

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        for (Proveedor proveedor : listaProveedores) {
            mostrarDatosProveedor(proveedor);
        }
    }

    public void mostrarProveedoresDisponibles(){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        System.out.println("=== Lista de Proveedores ===");
        for (Proveedor p : listaProveedores) {
            if (p.isActivo()){
                System.out.println(p.getIdProveedor() + " - " + p.getNombreCompleto());
            }
        }
    }


    public void mostrarDatosProveedor(Proveedor p) {

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.getIdProveedor().equals(p.getIdProveedor())) {

                p = proveedor;

                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE PROVEEDOR: " + p.getNombreCompleto());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + p.getIdProveedor());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Apellido: " + p.getApellido());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Teléfono: " + p.getTelefono());
                System.out.println("CUIT: " + p.getCuit());
                System.out.println("Activo: " + (p.isActivo() ? "Sí" : "No"));
                System.out.println("Fecha de alta: " + p.getFechaAlta().toString());
                System.out.println("Tipo de proveedor: " + p.getTipoProveedor());
                System.out.println("Cantidad de productos suministrados: " + p.getProductosSuministrados().size());
                System.out.println("--------------------------------------------");
            }
        }
    }


    public HashMap<String, Producto> actualizarProductosProveedor(Producto nuevoProducto){

        listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");
        HashMap<String, Producto> nuevosProductos = new HashMap<>();

        if (nuevoProducto != null && nuevoProducto.getCodigo() != null) {

            nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);

            for (String idProv : nuevoProducto.getIdProveedores()) {
                Proveedor proveedorEncontrado = null;

                for (Proveedor p : listaProveedores) {
                    if (p.getIdProveedor().equals(idProv)) {
                        proveedorEncontrado = p;
                        break;
                    }
                }

                if (proveedorEncontrado != null) {

                    HashMap<String, Producto> prodParaProveedor = proveedorEncontrado.getProductosSuministrados();
                    if (prodParaProveedor == null) {
                        prodParaProveedor = new HashMap<>();
                    }

                    prodParaProveedor.put(nuevoProducto.getCodigo(), nuevoProducto);

                    proveedorEncontrado.setProductosSuministrados(prodParaProveedor);

                } else {
                    System.err.println("Error: El proveedor con ID " + idProv + " no existe.");
                }
            }

            GestionJSONProveedor.listaProveedorToArchivo(listaProveedores, "proveedor.json");

            System.out.println("Producto agregado correctamente al/los proveedor(es).");
        }

        return nuevosProductos;
    }

}
