package Gestion;

import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;
import Excepciones.ObjetoNoEncontradoException;
import Productos.*;
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

    public void mostrarProductosPorCoincidencia(String nombreBusqueda) throws ObjetoNoEncontradoException {

        boolean encontrado = false;

        for (Map.Entry<String, Producto> entry : listaProductosDisponibles.entrySet()) {
            String nombreProducto = entry.getValue().getNombre().toLowerCase();

            if (nombreProducto.contains(nombreBusqueda)) {
                System.out.println("Codigo: " + entry.getKey() + ", Producto: " + entry.getValue());
                encontrado = true;
            }
        }

        if(!encontrado){
            throw new ObjetoNoEncontradoException("No hay productos que coincidan con ese nombre");
        }
    }


    public Producto buscarProductoPorCodigo(String codigo) throws ObjetoNoEncontradoException {

        boolean encontrado = false;
        Producto producto = null;

        for (Map.Entry<String, Producto> entry : listaProductosDisponibles.entrySet()) {
            String codigoProducto = entry.getKey();

            if (codigoProducto.equals(codigo)) {
                producto = entry.getValue();
                encontrado = true;
            }
        }

        if(!encontrado){
            throw new ObjetoNoEncontradoException("No hay productos que coincidan con ese código");
        }

        return producto;
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

        char boton;

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
            TipoCategoria categoria = elegirCategoria(teclado);

            System.out.println("Seleccione la subcategoria del producto");
            TipoSubCategoria subCategoria = elegirSubCategoria(teclado, categoria);

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
                    System.out.println("Opción invalida. Inténtelo nuevamente");
                    break;
            }


            switch (opcion){
                case 1:
                    System.out.println("Ingrese la capacidad");
                    String capacidad = teclado.nextLine();

                    System.out.println("Ingrese la velocidad");
                    String velocidad = teclado.nextLine();

                    nuevoProducto = new Almacenamiento(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, capacidad, velocidad);
                    break;

                case 2:
                    System.out.println("Ingrese la velocidad");
                    String velocidad1 = teclado.nextLine();

                    System.out.println("Ingrese el nivel de ruido maximo");
                    String ruidoMax = teclado.nextLine();

                    nuevoProducto = new Cooler(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, velocidad1, ruidoMax);
                    break;

                case 3:
                    System.out.println("Ingrese la potencia");
                    String potencia = teclado.nextLine();

                    if(subCategoria == TipoSubCategoria.CERTIFICADA){
                        System.out.println("Elija el tipo de certificación");
                        System.out.println("1. Bronze");
                        System.out.println("2. Silver");
                        System.out.println("3. Gold");
                        System.out.println("4. Platinum");
                        System.out.println("5. Titanium");

                        TipoCertificacion tipoCertificacion = null;
                        int op = teclado.nextInt();
                        teclado.nextLine();

                        switch (op){
                            case 1:
                                tipoCertificacion = TipoCertificacion.BRONZE;
                                break;

                            case 2:
                                tipoCertificacion = TipoCertificacion.SILVER;
                                break;

                            case 3:
                                tipoCertificacion = TipoCertificacion.GOLD;
                                break;

                            case 4:
                                tipoCertificacion = TipoCertificacion.PLATINUM;
                                break;

                            case 5:
                                tipoCertificacion = TipoCertificacion.TITANIUM;
                                break;

                            default:
                                System.out.println("Opción invalida. Inténtelo nuevamente");
                                break;
                        }

                        nuevoProducto = new FuenteDePoder(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, potencia, tipoCertificacion);

                    }else {
                        nuevoProducto = new FuenteDePoder(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, potencia, null);
                    }

                    break;

                case 4:
                    System.out.println("El gabinete tiene ventana (s / n)");
                    boolean ventana;
                    char c;

                    while (true) {
                        try {
                            c = teclado.next().toLowerCase().charAt(0);
                            Validaciones.validarBoton(c);
                            ventana = (c == 's');
                            break;

                        } catch (IllegalArgumentException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }

                    System.out.println("Ingrese el color");
                    String color = teclado.next();

                    System.out.println("Ingrese el ancho del gabinete");
                    String ancho = teclado.nextLine();

                    System.out.println("Ingrese el alto del gabinete");
                    String alto = teclado.nextLine();

                    System.out.println("Ingrese la profundidad del gabinete");
                    String profundidad = teclado.nextLine();

                    nuevoProducto = new Gabinete(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, ventana, color, ancho, alto, profundidad);
                    break;

                case 5:
                    System.out.println("Ingrese la capacidad de la memoria");
                    int capacidadMemoria = teclado.nextInt();

                    System.out.println("Ingrese el tipo de memoria");
                    String tipoMemoria = teclado.nextLine();

                    System.out.println("Ingrese la frecuencia");
                    String frecuencia = teclado.nextLine();

                    nuevoProducto = new MemoriaRAM(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, capacidadMemoria, tipoMemoria, frecuencia);
                    break;

                case 6:
                    System.out.println("Ingrese los dispositivos compatibles");
                    String dispositivosCompatibles = teclado.nextLine();

                    nuevoProducto = new PlacaDeRed(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, dispositivosCompatibles);
                    break;

                case 7:
                    System.out.println("Ingrese la GPU");
                    String GPU = teclado.nextLine();

                    System.out.println("Ingrese la VRAM");
                    String VRAM = teclado.nextLine();

                    System.out.println("Ingrese la frecuencia del nucleo");
                    String frecuenciaNucleo = teclado.nextLine();

                    System.out.println("Ingrese el ancho de banda");
                    String anchoBanda = teclado.nextLine();

                    nuevoProducto = new PlacaDeVideo(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, GPU, VRAM, frecuenciaNucleo, anchoBanda);
                    break;

                case 8:
                    System.out.println("Ingrese el tipo de memoria");
                    String memoriaTipo = teclado.nextLine();

                    System.out.println("Ingrese la cantidad de slots de memoria");
                    int slotsMemoria = teclado.nextInt();

                    System.out.println("¿Tiene Back Connect? (s / n)");
                    boolean backConnect;
                    char b;

                    while (true) {
                        try {
                            b = teclado.next().toLowerCase().charAt(0);
                            Validaciones.validarBoton(b);
                            backConnect = (b == 's');
                            break;

                        } catch (IllegalArgumentException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }

                    System.out.println("¿Tiene un botón de Bios?");
                    boolean botonBios;
                    char a;

                    while (true) {
                        try {
                            a = teclado.next().toLowerCase().charAt(0);
                            Validaciones.validarBoton(a);
                            botonBios = (a == 's');
                            break;

                        } catch (IllegalArgumentException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }

                    nuevoProducto = new PlacaMadre(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, memoriaTipo, slotsMemoria, backConnect, botonBios);
                    break;

                case 9:
                    System.out.println("Ingrese la frecuencia de reloj");
                    String frecuenciaDeReloj = teclado.nextLine();

                    System.out.println("Ingrese el número de nucleos");
                    int numeroNucleos = teclado.nextInt();

                    nuevoProducto = new Procesador(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, proveedor, frecuenciaDeReloj, numeroNucleos);
                    break;
            }

            agregarProducto(nuevoProducto);
            System.out.println("Producto agregado correctamente");

            System.out.println("¿Desea cargar otro producto? presione s o n");

            while (true) {
                try {
                    boton = teclado.next().toLowerCase().charAt(0);
                    Validaciones.validarBoton(boton);
                    break;

                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }

    public int elegirTipoProducto(Scanner teclado){

        System.out.println("Ingrese el tipo de producto a cargar");
        System.out.println("1. Almacenamiento");
        System.out.println("2. Cooler");
        System.out.println("3. Fuente de Poder");
        System.out.println("4. Gabinete");
        System.out.println("5. Memoria RAM");
        System.out.println("6. Placa de Red");
        System.out.println("7. Placa de Video");
        System.out.println("8. Placa Madre");
        System.out.println("9. Procesador");

         int opcion = 0;

         while (true) {

             try {
                 opcion = teclado.nextInt();
                 teclado.nextLine();
                 Validaciones.validarOpcionNumero(opcion);
                 break;

             } catch (IllegalArgumentException e) {
                 System.err.println("Error: " +e.getMessage());

             }catch (InputMismatchException e) {
                 System.err.println("Error: Debe ingresar un número válido.");
                 teclado.nextLine();
             }
         }

        return opcion;
    }

    public TipoCategoria elegirCategoria(Scanner teclado){

        TipoCategoria tipoCategoria = null;

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
                    tipoCategoria = TipoCategoria.ALMACENAMIENTO;
                    break;

                case 2:
                    tipoCategoria = TipoCategoria.CONECTIVIDAD;
                    break;

                case 3:
                    tipoCategoria = TipoCategoria.FUENTE;
                    break;

                case 4:
                    tipoCategoria = TipoCategoria.GABINETE;
                    break;

                case 5:
                    tipoCategoria = TipoCategoria.MEMORIA_RAM;
                    break;

                case 6:
                    tipoCategoria = TipoCategoria.PLACA_DE_VIDEO;
                    break;

                case 7:
                    tipoCategoria = TipoCategoria.PLACA_MADRE;
                    break;

                case 8:
                    tipoCategoria = TipoCategoria.PROCESADOR;
                    break;

                case 9:
                    tipoCategoria = TipoCategoria.REFRIGERACION;
                    break;
            }

        return tipoCategoria;
    }

    public TipoSubCategoria elegirSubCategoria(Scanner teclado, TipoCategoria categoria){

        if(categoria == TipoCategoria.ALMACENAMIENTO) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Disco Externo");
            System.out.println("2. Disco rígido");
            System.out.println("3. Disco solido SSD");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.DISCO_EXTERNO;

                    case 2:
                        return TipoSubCategoria.DISCO_RIGIDO;

                    case 3:
                        return TipoSubCategoria.DISCO_SOLIDO_SSD;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }


        if(categoria == TipoCategoria.FUENTE) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Certificada");
            System.out.println("2. Generica");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.CERTIFICADA;

                    case 2:
                        return TipoSubCategoria.GENERICA;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }

        if(categoria == TipoCategoria.MEMORIA_RAM) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Memoria RAM");
            System.out.println("2. Memoria notebook");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.MEMORIA_RAM;

                    case 2:
                        return TipoSubCategoria.MEMORIA_NOTEBOOK;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }

        if(categoria == TipoCategoria.PLACA_DE_VIDEO) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Placa de video GeForce");
            System.out.println("2. Placa de video RadeonAMD");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.PLACA_DE_VIDEO_GEFORCE;

                    case 2:
                        return TipoSubCategoria.PLACA_DE_VIDEO_RADEONAMD;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }

        if(categoria == TipoCategoria.PLACA_MADRE) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Placa madre AMD");
            System.out.println("2. Placa madre Intel");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.PLACA_AMD;

                    case 2:
                        return TipoSubCategoria.PLACA_INTEL;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }

        if(categoria == TipoCategoria.PROCESADOR) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Procesador AMD");
            System.out.println("2. Procesador Intel");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.PLACA_AMD;

                    case 2:
                        return TipoSubCategoria.PROCESADOR_INTEL;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }

        if(categoria == TipoCategoria.REFRIGERACION) {
            System.out.println("Elija una subcategoría");
            System.out.println("1. Cooler CPU");
            System.out.println("2. Cooler Fan");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            while (true) {

                switch (opcion) {

                    case 1:
                        return TipoSubCategoria.COOLER_CPU;

                    case 2:
                        return TipoSubCategoria.COOLER_FAN;

                    default:
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                        break;
                }
            }
        }

        return null;
    }

    public void modificarProducto(Producto p){

    }
}
