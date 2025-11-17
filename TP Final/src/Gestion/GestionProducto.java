package Gestion;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;
import Excepciones.DatoInvalidoException;
import Productos.*;
import Validaciones.Validaciones;

import java.util.*;

public class GestionProducto {

    private HashMap<String, Producto> listaProductos;
    private Scanner teclado;

    private Inventario inventario;

    public GestionProducto(Inventario inventario) {
        this.inventario = inventario;
        this.listaProductos = new HashMap<>();
        
        this.teclado = new Scanner(System.in);
    }

    public void agregarProducto(Producto p){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");
        listaProductos.put(p.getCodigo(), p);
        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");
        
        inventario.registrarStockProducto(p);
        
    }

    public void darBajaProducto(Producto p){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        for(Map.Entry<String, Producto> entry : listaProductos.entrySet()){
            if(entry.getValue().equals(p)){
                System.out.println("¿Estás seguro de que quieres dar de baja este producto? (si / no)");
                String opcion = teclado.nextLine();

                while (true){

                    if(opcion.equalsIgnoreCase("si")){

                        p.setActivo(false);
                        System.out.println("¡Producto dado de baja con éxito!");
                        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");
                        inventario.darBajaStockProducto(p.getCodigo);
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

        System.out.println("No se encontró el producto");
    }

    public void darAltaProducto(Producto p){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        for(Map.Entry<String, Producto> entry : listaProductos.entrySet()){
            if(entry.getValue().equals(p)){
                System.out.println("¿Estás seguro de que quieres dar de alta este producto? (si / no)");
                String opcion = teclado.nextLine();

                while (true){

                    if(opcion.equalsIgnoreCase("si")){

                        p.setActivo(true);
                        System.out.println("¡Producto dado de alta con éxito!");
                        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");
                        inventario.darBajaStockProducto(p.getCodigo());
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

        System.out.println("No se encontró el producto");
    }

    public void mostrarProductosPorCoincidencia(String nombreBusqueda){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        boolean encontrado = false;

        for (Map.Entry<String, Producto> entry : listaProductos.entrySet()) {
            String nombreProducto = entry.getValue().getNombre().toLowerCase();

            if (nombreProducto.contains(nombreBusqueda)) {
                System.out.println("Codigo: " + entry.getKey() + ", Producto: " + entry.getValue());
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("No hay productos que coincidan con ese nombre");
        }
    }


    public Producto buscarProductoPorCodigo(String codigo){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        boolean encontrado = false;
        Producto producto = null;

        for (Map.Entry<String, Producto> entry : listaProductos.entrySet()) {
            String codigoProducto = entry.getKey();

            if (codigoProducto.equals(codigo)) {
                producto = entry.getValue();
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("No hay productos que coincidan con ese código");
        }

        return producto;
    }


    public void mostrarProductos(){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        for (Map.Entry<String, Producto> entry : listaProductos.entrySet()){
            if(entry.getValue().isActivo()){
                System.out.println("Código: " + entry.getKey() + ", Valor: " +entry.getValue());
            }
        }
    }


    public Producto elegirProductosDisponibles(){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        List<Producto> productos = new ArrayList<>(listaProductos.values());
        System.out.println("Elija un producto");

        int i = 0;

        for (Producto p : productos){
            if(p.isActivo()){
                System.out.println(i+1 + "- " + "Nombre: " + p.getNombre() + ", Precio: " +p.getPrecio());
            }
        }

        int opcion = ingresarOpcionValida(productos);

        return productos.get(opcion-1);
    }


    public int ingresarOpcionValida(List<Producto> lista){ //puede que no funcione

        int opcion;

        while (true){

            try {
                System.out.println("Ingrese una opcion del 1 al " +lista.size());
                opcion = teclado.nextInt();
                teclado.nextLine();
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


    public HashMap<String, Producto> cargarProductos(){

        char boton;
        HashMap<String, Producto> nuevosProductos = new HashMap<>();
        Producto nuevoProducto = null;

        while (true){

            int opcion = elegirTipoProducto();
            teclado.nextLine();

            String nombre = "";

            while (true){
                System.out.println("Ingrese el nombre del producto");

                try {
                    nombre = teclado.nextLine();
                    Validaciones.validarNombreProducto(nombre);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            String descripcion = "";

            while (true){
                System.out.println("Ingrese la descripción de producto");

                try {
                    descripcion = teclado.nextLine();
                    Validaciones.validarDescripcionProducto(descripcion);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            double precio;

            while (true){
                System.out.println("Ingrese el precio del producto");

                try {
                    precio = teclado.nextDouble();
                    Validaciones.validarNumero(precio);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            double peso;

            while (true){
                System.out.println("Ingrese el peso del producto");

                try {
                    peso = teclado.nextDouble();
                    Validaciones.validarNumero(peso);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            String dimension;

            while (true){
                System.out.println("Ingrese la dimension del producto");

                try {
                    dimension = teclado.nextLine();
                    Validaciones.validarDimension(dimension);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            String marca;

            while (true){
                System.out.println("Ingrese la marca del producto");

                try {
                    marca = teclado.nextLine();
                    Validaciones.validarMarca(marca);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            int stock;

            while (true){
                System.out.println("Ingrese el stock disponible");

                try {
                    stock = teclado.nextInt();
                    Validaciones.validarNumero(stock);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            int garantia;

            while (true){
                System.out.println("Ingrese la garantía en meses");

                try {
                    garantia = teclado.nextInt();
                    Validaciones.validarNumero(stock);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }


            String codigo;

            while (true){
                System.out.println("Ingrese el código del proveedor del producto");

                try {
                    codigo = teclado.next();
                    Validaciones.validarIDYCodigo(codigo);

                    break;

                }catch (DatoInvalidoException e){
                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                }
            }

            System.out.println("Seleccione la categoria del producto");
            TipoCategoria categoria = elegirCategoria();

            TipoSubCategoria subCategoria = null;

            if(opcion != 4){
                System.out.println("Seleccione la subcategoría del producto");
                subCategoria = elegirSubCategoria(categoria);
            }

            switch (opcion){
                case 1:
                    System.out.println("Ingrese la capacidad");
                    String capacidad = teclado.nextLine();

                    System.out.println("Ingrese la velocidad");
                    String velocidad = teclado.nextLine();

                    nuevoProducto = new Almacenamiento(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, capacidad, velocidad, codigo);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
                    break;

                case 2:
                    System.out.println("Ingrese la velocidad");
                    String velocidad1 = teclado.nextLine();

                    System.out.println("Ingrese el nivel de ruido maximo");
                    String ruidoMax = teclado.nextLine();

                    nuevoProducto = new Cooler(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, velocidad1, ruidoMax, codigo);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
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

                        nuevoProducto = new FuenteDePoder(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, potencia, codigo, tipoCertificacion);
                        nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                        agregarProducto(nuevoProducto);

                    }else {
                        nuevoProducto = new FuenteDePoder(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, potencia, codigo,null);
                        nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                        agregarProducto(nuevoProducto);
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

                    nuevoProducto = new Gabinete(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, codigo, ventana, color, ancho, alto, profundidad);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
                    break;

                case 5:
                    System.out.println("Ingrese la capacidad de la memoria");
                    int capacidadMemoria = teclado.nextInt();

                    System.out.println("Ingrese el tipo de memoria");
                    String tipoMemoria = teclado.nextLine();

                    System.out.println("Ingrese la frecuencia");
                    String frecuencia = teclado.nextLine();

                    nuevoProducto = new MemoriaRAM(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, codigo,capacidadMemoria, tipoMemoria, frecuencia);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
                    break;

                case 6:
                    System.out.println("Ingrese los dispositivos compatibles");
                    String dispositivosCompatibles = teclado.nextLine();

                    nuevoProducto = new PlacaDeRed(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, codigo, dispositivosCompatibles);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
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

                    nuevoProducto = new PlacaDeVideo(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, codigo, GPU, VRAM, frecuenciaNucleo, anchoBanda);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
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

                    nuevoProducto = new PlacaMadre(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, codigo, memoriaTipo, slotsMemoria, backConnect, botonBios);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
                    break;

                case 9:
                    System.out.println("Ingrese la frecuencia de reloj");
                    String frecuenciaDeReloj = teclado.nextLine();

                    System.out.println("Ingrese el número de núcleos");
                    int numeroNucleos = teclado.nextInt();

                    nuevoProducto = new Procesador(nombre, descripcion, precio, peso, dimension, marca, stock, garantia, categoria, subCategoria, codigo, frecuenciaDeReloj, numeroNucleos);
                    nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                    agregarProducto(nuevoProducto);
                    break;
            }

            System.out.println("Producto agregado correctamente");
            System.out.println("¿Desea cargar otro producto? (s / n)");

            while (true) {
                try {
                    boton = teclado.next().toLowerCase().charAt(0);
                    Validaciones.validarBoton(boton);
                    break;

                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }

            if (boton == 'n') {
                break;
            }
        }

        agregarProductos(nuevosProductos);

        return nuevosProductos;
    }

    public int elegirTipoProducto(){

        System.out.println("Ingrese el tipo de producto a cargar");
        System.out.println("1. Almacenamiento");
        System.out.println("2. Cooler");
        System.out.println("3. Fuente de Poder");
        System.out.println("4. Gabinete"); //
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

    public TipoCategoria elegirCategoria(){

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
                    tipoCategoria = TipoCategoria.FUENTE_DE_PODER;
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

    public TipoSubCategoria elegirSubCategoria(TipoCategoria categoria){

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


        if(categoria == TipoCategoria.CONECTIVIDAD) {
            System.out.println("Se elegirá automáticamente la subCategoria para la categoría de conectividad");
            return TipoSubCategoria.PLACA_DE_RED;
        }


        if(categoria == TipoCategoria.FUENTE_DE_PODER) {
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

    public void agregarProductos(HashMap<String, Producto> productos){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            String codigo = entry.getKey();
            Producto producto = entry.getValue();
            listaProductos.put(codigo, producto);
        }

        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");
    }

    public void modificarProducto(Producto p){

        //falta JSON

    }
}
