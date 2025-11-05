package Gestion;

import Productos.Almacenamiento;
import Productos.Categoria.Almacenamiento.DiscoExterno;
import Productos.Categoria.Almacenamiento.DiscoRigido;
import Productos.Categoria.Almacenamiento.DiscoSolidoSSD;
import Productos.Categoria.Categoria;
import Productos.Categoria.Conectividad.Conectividad;
import Productos.Categoria.Fuente.Certificada;
import Productos.Categoria.Fuente.Generica;
import Productos.Categoria.Gabinete.Gabinete;
import Productos.Categoria.MemoriaRAM.Memoria;
import Productos.Categoria.MemoriaRAM.MemoriaNotebook;
import Productos.Categoria.PlacaDeVideo.PlacaDeVideoGeForce;
import Productos.Categoria.PlacaDeVideo.PlacaDeVideoRadeonAMD;
import Productos.Categoria.PlacaMadre.PlacaAMD;
import Productos.Categoria.PlacaMadre.PlacaIntel;
import Productos.Categoria.Refrigeracion.CoolerCPU;
import Productos.Categoria.Refrigeracion.CoolerFan;
import Productos.Producto;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

public class GestionProducto {

    private HashMap<String, Producto> listaProductosDisponibles;
    private HashMap<String, Producto> productosProveedor;
    private GestionProveedor gestionProveedor;

    public GestionProducto(HashMap<String, Producto> productosProveedor) {
        this.gestionProveedor = new GestionProveedor();
        this.productosProveedor = productosProveedor;
        this.listaProductosDisponibles = new HashMap<>();
    }

    public GestionProducto() {
        this.listaProductosDisponibles = new LinkedHashMap<>();
    }

    public void agregarProducto(Producto p){
        listaProductosDisponibles.put(p.getCodigo(), p);
    }

    public void darBajaProducto(Producto p){
        p.setActivo(false);
    }

    public void darAltaProducto(Producto p){
        p.setActivo(true);
    }

    public void buscarProductosPorNombre(String nombreBusqueda) { //¿Está bien que devuelva en general o tiene que devolver uno especifico?

        boolean encontrado = false;

        for (Map.Entry<String, Producto> entry : listaProductosDisponibles.entrySet()) {
            String nombreProducto = entry.getValue().getNombre().toLowerCase();

            if (nombreProducto.contains(nombreBusqueda)) {
                System.out.println("Codigo: " + entry.getKey() + ", Producto: " + entry.getValue());
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("No hay productos que coincidan con ese nombre");
        }

        //Cambiar el nombre  el metodo
    }


    public void modificarProducto(Producto p){

    }

    public void mostrarProductos(){

        for (Map.Entry<String, Producto> entry : listaProductosDisponibles.entrySet()){
            if(entry.getValue().isActivo()){
                System.out.println("Codigo: " + entry.getKey() + ", Valor: " +entry.getValue());
            }
        }
    }


    public Producto elegirProductosDisponibles(Scanner teclado){

        List<Producto> productos = new ArrayList<>(listaProductosDisponibles.values());
        System.out.println("Elije un producto");

        int i = 0;

        for (Producto p : productos){
            if(p.isActivo()){
                System.out.println(i+1 + "- " + "Nombre: " + p.getNombre() + ", Precio: " +p.getPrecio());
            }
        }

        int opcion = Validaciones.ingresarOpcionValida(teclado, productos);

        return productos.get(opcion-1);
    }


    public Producto elegirProductosDeBaja(Scanner teclado){

        List<Producto> productos = new ArrayList<>(productosProveedor.values());
        System.out.println("Elije un producto");

        int i = 0;

        for (Producto p : productos){
            if(!p.isActivo()){
                System.out.println(i+1 + "- " + "Nombre: " + p.getNombre() + ", Precio: " +p.getPrecio());
            }
        }

        int opcion = Validaciones.ingresarOpcionValida(teclado, productos);

        return productos.get(opcion-1);
    }


    public void cargarProductos(Scanner teclado){

        char boton = 'n';

        LinkedHashMap<String, Producto> aux = new LinkedHashMap<>();

        while (true){

            int opcion = elegirTipoProducto(teclado);
            teclado.nextLine();
            Producto nuevoProducto = null;

            System.out.println("Ingrese el nombre del producto");
            String nombre = teclado.nextLine();

            System.out.println("Ingrese la descripcion de producto");
            String descripcion = teclado.nextLine();

            System.out.println("Ingrese el precio del producto");
            double precio = teclado.nextDouble();

            System.out.println("Ingrese el peso del producto");
            double peso = teclado.nextDouble();

            System.out.println("Ingrese la dimension del producto");
            String dimension = teclado.nextLine();

            System.out.println("Ingrese la marca del producto");
            String marca = teclado.nextLine();

            System.out.println("Ingrese el stock disponible");
            int stock = teclado.nextInt();

            System.out.println("Ingrese la garantia en meses");
            int garantia = teclado.nextInt();

            System.out.println("Seleccione la categoria del producto");
            Categoria categoria = elegirCategoria(teclado);

            System.out.println("Indique si quiere seleccionar un proveedor existente (1) o cargar uno nuevo (2)");
            int num = teclado.nextInt();
            Proveedor proveedor = null;

            switch (num){
                case 1:
                    proveedor = gestionProveedor.elegirProveedor(teclado);
                    break;

                case 2:
                    proveedor = gestionProveedor.cargarProveedor(teclado);
                    break;

                default:
                    System.out.println("Opción invalida. Intentelo nuevamente");
                    break;
            }


            switch (opcion){
                case 1:
                    System.out.println("Ingrese la capacidad");
                    String capacidad = teclado.nextLine();

                    System.out.println("Ingrese la capacidad");
                    String velocidad = teclado.nextLine();

                    nuevoProducto = new Almacenamiento(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, proveedor, capacidad, velocidad);

                    break;
            }

            agregarProducto(nuevoProducto);
            System.out.println("Producto agregado correctamente");

            System.out.println("¿Desea cargar otro producto? presione s o n");
            boton = teclado.next().toLowerCase().charAt(0);


            while (boton != 's' && boton != 'n'){//Exception?
                System.out.println("Dato invalido. Intentelo nuevamente");
                boton = teclado.next().toLowerCase().charAt(0);
            }
        }
    }

    public int elegirTipoProducto(Scanner teclado){

        System.out.println("Ingrese el tipo de producto que a cargar");
        System.out.println("1. Almacenamiento");
        System.out.println("2. Cooler");
        System.out.println("3. Fuente de Poder");
        System.out.println("4. Gabinete");
        System.out.println("5. Memoria RAM");
        System.out.println("6. Placa de Red");
        System.out.println("7. Placa de Video");
        System.out.println("8. Placa Madre");
        System.out.println("9. Procesador");

         int opcion = teclado.nextInt();
         teclado.nextLine();

            while (opcion < 0 || opcion > 9){
                System.err.println("Opcion fuera de rango. Intentelo nuevamente");
                opcion = teclado.nextInt();
            }

        return opcion;
    }

    public Categoria elegirCategoria(Scanner teclado){

        boolean categoriaValida = false;
        boolean subCategoriaValida;
        Categoria categoriaSeleccionada = null;

        while (!categoriaValida){

            System.out.println("1. Almacenamiento");
            System.out.println("2. Conectividad");
            System.out.println("3. Fuente");
            System.out.println("4. Gabinete");
            System.out.println("5. Memoria RAM");
            System.out.println("6. Placa de Video");
            System.out.println("7. Placa Madre");
            System.out.println("8. Procesador");
            System.out.println("9. Refrigeración");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){

                case 1:
                subCategoriaValida = false;

                while (!subCategoriaValida){
                    System.out.println("Seleccione el tipo de Almacenamiento");
                    System.out.println("1. Disco Externo");
                    System.out.println("2. Disco Rigido");
                    System.out.println("Disco Solido SSD");

                    int subOpcion = teclado.nextInt();
                    teclado.nextLine();

                    switch (subOpcion){
                        case 1:
                            categoriaSeleccionada= new DiscoExterno();
                            subCategoriaValida = true;
                            break;

                        case 2:
                            categoriaSeleccionada= new DiscoRigido();
                            subCategoriaValida = true;
                            break;

                        case 3:
                            categoriaSeleccionada= new DiscoSolidoSSD();
                            subCategoriaValida = true;
                            break;

                        default:
                            System.out.println("Opción invalida. Intentelo nuevamente");
                            break;
                    }
                }

                categoriaValida = true;
                break;

                case 2:
                    categoriaSeleccionada = new Conectividad();
                    break;

                case 3:
                    subCategoriaValida = false;

                    while (!subCategoriaValida) {
                        System.out.println("Seleccione el tipo de Fuente");
                        System.out.println("1. Certificada");
                        System.out.println("2. Generica");

                        int subOpcion = teclado.nextInt();
                        teclado.nextLine();

                        switch (subOpcion) {
                            case 1:
                                categoriaSeleccionada = new Certificada();
                                subCategoriaValida = true;
                                break;

                            case 2:
                                categoriaSeleccionada = new Generica();
                                subCategoriaValida = true;
                                break;

                            default:
                                System.out.println("Opción invalida. Intentelo nuevamente");
                                break;
                        }
                    }

                    categoriaValida = true;
                    break;

                case 4:
                    categoriaSeleccionada = new Gabinete();
                    break;

                case 5:
                    subCategoriaValida = false;

                    while (!subCategoriaValida) {
                        System.out.println("Seleccione el tipo de Memoria RAM");
                        System.out.println("1. Memoria");
                        System.out.println("2. Memoria Notebook");

                        int subOpcion = teclado.nextInt();
                        teclado.nextLine();

                        switch (subOpcion) {
                            case 1:
                                categoriaSeleccionada = new Memoria();
                                subCategoriaValida = true;
                                break;

                            case 2:
                                categoriaSeleccionada = new MemoriaNotebook();
                                subCategoriaValida = true;
                                break;

                            default:
                                System.out.println("Opción invalida. Intentelo nuevamente");
                                break;
                        }
                    }

                    categoriaValida = true;
                    break;

                case 6:
                    subCategoriaValida = false;

                    while (!subCategoriaValida) {
                        System.out.println("Seleccione el tipo de Placa de Video");
                        System.out.println("1. Placa de Video GeForce");
                        System.out.println("2. Placa de Video Radeon AMD");

                        int subOpcion = teclado.nextInt();
                        teclado.nextLine();

                        switch (subOpcion) {
                            case 1:
                                categoriaSeleccionada = new PlacaDeVideoGeForce();
                                subCategoriaValida = true;
                                break;

                            case 2:
                                categoriaSeleccionada = new PlacaDeVideoRadeonAMD();
                                subCategoriaValida = true;
                                break;

                            default:
                                System.out.println("Opción invalida. Intentelo nuevamente");
                                break;
                        }
                    }

                    categoriaValida = true;
                    break;

                case 7:
                    subCategoriaValida = false;

                    while (!subCategoriaValida) {
                        System.out.println("Seleccione el tipo de Placa Madre");
                        System.out.println("1. Placa AMD");
                        System.out.println("2. Placa Intel");

                        int subOpcion = teclado.nextInt();
                        teclado.nextLine();

                        switch (subOpcion) {
                            case 1:
                                categoriaSeleccionada = new PlacaAMD();
                                subCategoriaValida = true;
                                break;

                            case 2:
                                categoriaSeleccionada = new PlacaIntel();
                                subCategoriaValida = true;
                                break;

                            default:
                                System.out.println("Opción invalida. Intentelo nuevamente");
                                break;
                        }
                    }

                    categoriaValida = true;
                    break;

                case 8:
                    subCategoriaValida = false;

                    while (!subCategoriaValida) {
                        System.out.println("Seleccione el tipo de Procesador");
                        System.out.println("1. Procesador AMD");
                        System.out.println("2. Procesador Intel");

                        int subOpcion = teclado.nextInt();
                        teclado.nextLine();

                        switch (subOpcion) {
                            case 1:
                                categoriaSeleccionada = new PlacaAMD();
                                subCategoriaValida = true;
                                break;

                            case 2:
                                categoriaSeleccionada = new PlacaIntel();
                                subCategoriaValida = true;
                                break;

                            default:
                                System.out.println("Opción invalida. Intentelo nuevamente");
                                break;
                        }
                    }

                    categoriaValida = true;
                    break;

                case 9:
                    subCategoriaValida = false;

                    while (!subCategoriaValida) {
                        System.out.println("Seleccione el tipo de Cooler");
                        System.out.println("1. CoolerCPU");
                        System.out.println("2. CoolerFan");

                        int subOpcion = teclado.nextInt();
                        teclado.nextLine();

                        switch (subOpcion) {
                            case 1:
                                categoriaSeleccionada = new CoolerCPU();
                                subCategoriaValida = true;
                                break;

                            case 2:
                                categoriaSeleccionada = new CoolerFan();
                                subCategoriaValida = true;
                                break;

                            default:
                                System.out.println("Opción invalida. Intentelo nuevamente");
                        }
                    }

                    categoriaValida = true;
                    break;

                default:
                    System.out.println("Opción invalida. Intentelo nuevamente");
                    break;
            }
        }

        return categoriaSeleccionada;
    }


}
