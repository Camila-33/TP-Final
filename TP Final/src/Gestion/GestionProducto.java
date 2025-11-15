package Gestion;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;
import IngresoDeDatos.InputHelper;
import Productos.*;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

public class GestionProducto {

    private HashMap<String, Producto> listaProductos;

    public GestionProducto() {
        this.listaProductos = new HashMap<>();
    }

    public void agregarProducto(Producto p){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");
        listaProductos.put(p.getCodigo(), p);
        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");
    }

    public void darBajaProducto(Producto p){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        for(Map.Entry<String, Producto> entry : listaProductos.entrySet()){
            if(entry.getValue().equals(p)){
                char opcion = InputHelper.leerChar("¿Estás seguro de que quieres dar de baja este producto? (s / n)");

                while (true){

                    if(opcion == 's'){

                        p.setActivo(false);
                        System.out.println("¡Producto dado de baja con éxito!");
                        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");

                        return;

                    }else if (opcion == 'n') {
                        System.out.println("Operación cancelada");
                        return;
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
                char opcion = InputHelper.leerChar("¿Estás seguro de que quieres dar de alta este producto? (s / n)");

                while (true){

                    if(opcion == 's'){

                        p.setActivo(true);
                        System.out.println("¡Producto dado de alta con éxito!");
                        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");

                        return;

                    }else if (opcion == 'n') {
                        System.out.println("Operación cancelada");
                        return;
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


    public void mostrarTodosLosProductos() {

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        for (Producto producto : listaProductos.values()) {
            producto.mostrarProducto();
        }
    }


    public Producto elegirProductosDisponibles(){

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");
        List<Producto> productos = new ArrayList<>(listaProductos.values());

        int i = 0;

        System.out.println("=== Lista de Productos ===");
        for (Producto p : productos){
            if(p.isActivo()){
                System.out.println(i+1 + "- " + "Nombre: " + p.getNombre() + ", Precio: " +p.getPrecio());
            }
        }

        System.out.println("Elija un producto: ");
        int opcion = ingresarOpcionValida(productos);

        return productos.get(opcion-1);
    }

    public Producto elegirProductosDeUnProveedor(Proveedor proveedor) {

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");
        listaProductos = proveedor.getProductosSuministrados();
        List<Producto> productos = new ArrayList<>(listaProductos.values());

        int i = 0;

        System.out.println("Productos disponibles de " + proveedor.getNombre() + ":");
        for (Producto p : productos) {
            System.out.println(i+1 + ". " + p.getNombre() + " (Stock actual: " + p.getStock() + ")");
        }

        int opcion = ingresarOpcionValida(productos);

        return productos.get(opcion-1);
    }


    public int ingresarOpcionValida(List<Producto> lista){

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


    public HashMap<String, Producto> cargarProductos() {
        HashMap<String, Producto> nuevosProductos = new HashMap<>();
        char continuar;

        do {
            int opcion = elegirTipoProducto();

            String nombre = InputHelper.leerNombreProducto("Ingrese el nombre del producto: ");
            String descripcion = InputHelper.leerDescripcion("Ingrese la descripción del producto: ");
            double precio = InputHelper.leerDouble("Ingrese el precio del producto: ");
            double peso = InputHelper.leerDouble("Ingrese el peso del producto: ");
            String dimension = InputHelper.leerDimension("Ingrese la dimensión del producto: ");
            String marca = InputHelper.leerMarca("Ingrese la marca del producto: ");
            int stock = InputHelper.leerInt("Ingrese el stock disponible: ");
            int garantia = InputHelper.leerInt("Ingrese la garantía en meses: ");
            String codigo = InputHelper.leerIDOCodigo("Ingrese el código del proveedor: ");

            System.out.println("Seleccione la categoría del producto");
            TipoCategoria categoria = elegirCategoria();

            System.out.println("Seleccione la subcategoría del producto");
            TipoSubCategoria subCategoria = elegirSubCategoria(categoria);

            Producto nuevoProducto = null;

            switch (opcion) {
                case 1:
                    String capacidad = InputHelper.leerString("Ingrese la capacidad: ");
                    String velocidad = InputHelper.leerString("Ingrese la velocidad: ");

                    nuevoProducto = new Almacenamiento(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, capacidad, velocidad, codigo);
                    break;

                case 2:
                    String velocidadCooler = InputHelper.leerString("Ingrese la velocidad: ");
                    String ruidoMax = InputHelper.leerString("Ingrese el nivel de ruido máximo: ");

                    nuevoProducto = new Cooler(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, velocidadCooler, ruidoMax, codigo);
                    break;

                case 3:
                    String potencia = InputHelper.leerString("Ingrese la potencia: ");
                    TipoCertificacion tipoCertificacion = null;

                    if(subCategoria == TipoSubCategoria.CERTIFICADA) {
                        tipoCertificacion = InputHelper.leerCertificacion();
                    }

                    nuevoProducto = new FuenteDePoder(nombre, descripcion, precio, peso, dimension, marca, stock,
                            garantia, categoria, subCategoria, potencia, codigo, tipoCertificacion);
                    break;

                case 4:
                    boolean ventana = InputHelper.leerBoolean("El gabinete tiene ventana (s/n): ");
                    String color = InputHelper.leerString("Ingrese el color: ");
                    String ancho = InputHelper.leerString("Ingrese el ancho del gabinete: ");
                    String alto = InputHelper.leerString("Ingrese el alto del gabinete: ");
                    String profundidad = InputHelper.leerString("Ingrese la profundidad del gabinete: ");

                    nuevoProducto = new Gabinete(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, codigo, ventana, color, ancho, alto, profundidad);
                    break;

                case 5:
                    int capacidadMemoria = InputHelper.leerInt("Ingrese la capacidad de la memoria: ");
                    String tipoMemoria = InputHelper.leerString("Ingrese el tipo de memoria: ");
                    String frecuencia = InputHelper.leerString("Ingrese la frecuencia: ");

                    nuevoProducto = new MemoriaRAM(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, codigo, capacidadMemoria, tipoMemoria, frecuencia);
                    break;

                case 6:
                    String dispositivosCompatibles = InputHelper.leerString("Ingrese los dispositivos compatibles: ");

                    nuevoProducto = new PlacaDeRed(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, codigo, dispositivosCompatibles);
                    break;

                case 7:
                    String GPU = InputHelper.leerString("Ingrese la GPU: ");
                    String VRAM = InputHelper.leerString("Ingrese la VRAM: ");
                    String frecuenciaNucleo = InputHelper.leerString("Ingrese la frecuencia del núcleo: ");
                    String anchoBanda = InputHelper.leerString("Ingrese el ancho de banda: ");

                    nuevoProducto = new PlacaDeVideo(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, codigo, GPU, VRAM, frecuenciaNucleo, anchoBanda);
                    break;

                case 8:
                    String memoriaTipo = InputHelper.leerString("Ingrese el tipo de memoria: ");
                    int slotsMemoria = InputHelper.leerInt("Ingrese la cantidad de slots de memoria: ");
                    boolean backConnect = InputHelper.leerBoolean("¿Tiene Back Connect? (s/n): ");
                    boolean botonBios = InputHelper.leerBoolean("¿Tiene un botón de Bios? (s/n): ");

                    nuevoProducto = new PlacaMadre(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, codigo, memoriaTipo, slotsMemoria, backConnect, botonBios);
                    break;

                case 9:
                    String frecuenciaDeReloj = InputHelper.leerString("Ingrese la frecuencia de reloj: ");
                    int numeroNucleos = InputHelper.leerInt("Ingrese el número de núcleos: ");

                    nuevoProducto = new Procesador(nombre, descripcion, precio, peso, dimension, marca, stock, garantia,
                            categoria, subCategoria, codigo, frecuenciaDeReloj, numeroNucleos);
                    break;
            }

            if (nuevoProducto != null && nuevoProducto.getCodigo() != null) {
                nuevosProductos.put(nuevoProducto.getCodigo(), nuevoProducto);
                agregarProducto(nuevoProducto);
                System.out.println("Producto agregado correctamente.");

            } else {
                System.err.println("Error: el producto no fue agregado porque no fue creado correctamente.");
            }

            continuar = InputHelper.leerChar("¿Desea cargar otro producto? (s/n): ");

        } while (continuar == 's');

        agregarProductos(nuevosProductos);
        return nuevosProductos;
    }


    public int elegirTipoProducto(){

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

        int opcion;

        while (true) {
            opcion = InputHelper.leerInt("Ingrese una opción:");

            try {
                Validaciones.validarOpcionNumero(opcion);
                break;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
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

            int opcion = InputHelper.leerEnteroSwitch();

        tipoCategoria = switch (opcion) {
            case 1 -> TipoCategoria.ALMACENAMIENTO;
            case 2 -> TipoCategoria.CONECTIVIDAD;
            case 3 -> TipoCategoria.FUENTE_DE_PODER;
            case 4 -> TipoCategoria.GABINETE;
            case 5 -> TipoCategoria.MEMORIA_RAM;
            case 6 -> TipoCategoria.PLACA_DE_VIDEO;
            case 7 -> TipoCategoria.PLACA_MADRE;
            case 8 -> TipoCategoria.PROCESADOR;
            case 9 -> TipoCategoria.REFRIGERACION;
            default -> tipoCategoria;
        };

        return tipoCategoria;
    }

    public TipoSubCategoria elegirSubCategoria(TipoCategoria categoria) {

        switch (categoria) {
            case ALMACENAMIENTO -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Disco Externo");
                    System.out.println("2. Disco Rígido");
                    System.out.println("3. Disco Sólido SSD");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.DISCO_EXTERNO;
                        case 2: return TipoSubCategoria.DISCO_RIGIDO;
                        case 3: return TipoSubCategoria.DISCO_SOLIDO_SSD;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case CONECTIVIDAD -> {
                System.out.println("Se elegirá automáticamente la subcategoría para la categoría de conectividad");
                return TipoSubCategoria.PLACA_DE_RED;
            }

            case FUENTE_DE_PODER -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Certificada");
                    System.out.println("2. Genérica");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.CERTIFICADA;
                        case 2: return TipoSubCategoria.GENERICA;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case MEMORIA_RAM -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Memoria RAM");
                    System.out.println("2. Memoria Notebook");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.MEMORIA_RAM;
                        case 2: return TipoSubCategoria.MEMORIA_NOTEBOOK;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case PLACA_DE_VIDEO -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Placa de Video GeForce");
                    System.out.println("2. Placa de Video Radeon AMD");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.PLACA_DE_VIDEO_GEFORCE;
                        case 2: return TipoSubCategoria.PLACA_DE_VIDEO_RADEONAMD;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case PLACA_MADRE -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Placa Madre AMD");
                    System.out.println("2. Placa Madre Intel");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.PLACA_AMD;
                        case 2: return TipoSubCategoria.PLACA_INTEL;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case PROCESADOR -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Procesador AMD");
                    System.out.println("2. Procesador Intel");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.PROCESADOR_AMD;
                        case 2: return TipoSubCategoria.PROCESADOR_INTEL;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case REFRIGERACION -> {
                while (true) {
                    System.out.println("Elija una subcategoría:");
                    System.out.println("1. Cooler CPU");
                    System.out.println("2. Cooler Fan");

                    int opcion = InputHelper.leerEnteroSwitch();

                    switch (opcion) {
                        case 1: return TipoSubCategoria.COOLER_CPU;
                        case 2: return TipoSubCategoria.COOLER_FAN;
                        default: System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }

            case GABINETE -> {
                System.out.println("No hay subcategoría para Gabinete, se usará por defecto");
                return TipoSubCategoria.NINGUNA;
            }

            default -> {
                System.out.println("Categoría no válida");
                return null;
            }
        }
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


    public void modificarProducto(Producto producto) {

        listaProductos = GestionJSONProducto.archivoProductosToLista("producto.json");

        char opcionContinuar;

        do {
            System.out.println("\n--- Modificar Producto ---");
            System.out.println("Producto actual:");
            System.out.println(producto);

            System.out.println("\nSeleccione el atributo a modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Descripción");
            System.out.println("3. Precio");
            System.out.println("4. Peso");
            System.out.println("5. Dimensión");
            System.out.println("6. Marca");
            System.out.println("7. Stock");
            System.out.println("8. Garantía");
            System.out.println("9. Código de proveedor");

            int opcion = InputHelper.leerEnteroSwitch();

            switch (opcion) {
                case 1:
                    producto.setNombre(InputHelper.leerNombreProducto("Ingrese el nuevo nombre: "));
                    break;
                case 2:
                    producto.setDescripcion(InputHelper.leerDescripcion("Ingrese la nueva descripción: "));
                    break;
                case 3:
                    producto.setPrecio(InputHelper.leerDouble("Ingrese el nuevo precio: "));
                    break;
                case 4:
                    producto.setPeso(InputHelper.leerDouble("Ingrese el nuevo peso: "));
                    break;
                case 5:
                    producto.setDimension(InputHelper.leerDimension("Ingrese la nueva dimensión: "));
                    break;
                case 6:
                    producto.setMarca(InputHelper.leerMarca("Ingrese la nueva marca: "));
                    break;
                case 7:
                    producto.setStock(InputHelper.leerInt("Ingrese el nuevo stock: "));
                    break;
                case 8:
                    producto.setGarantiaMeses(InputHelper.leerInt("Ingrese la nueva garantía (meses): "));
                    break;
                case 9:
                    producto.setCodigo(InputHelper.leerIDOCodigo("Ingrese el nuevo código de proveedor: "));
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

            if (producto instanceof Almacenamiento a) {
                System.out.println("10. Capacidad");
                System.out.println("11. Velocidad");

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        a.setCapacidad(InputHelper.leerString("Ingrese nueva capacidad: "));
                        break;
                    case 11:
                        a.setVelocidad(InputHelper.leerString("Ingrese nueva velocidad: "));
                        break;
                }

            } else if (producto instanceof Cooler c) {
                System.out.println("10. Velocidad");
                System.out.println("11. Nivel de ruido");

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        c.setVelocidad(InputHelper.leerString("Ingrese nueva velocidad: "));
                        break;
                    case 11:
                        c.setNivelRuidoMaximo(InputHelper.leerString("Ingrese nuevo nivel de ruido: "));
                        break;
                }

            } else if (producto instanceof FuenteDePoder f) {
                System.out.println("10. Potencia");

                if (f.getSubCategoria() == TipoSubCategoria.CERTIFICADA) {
                    System.out.println("11. Tipo de certificación");
                }

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        f.setPotencia(InputHelper.leerString("Ingrese nueva potencia: "));
                        break;
                    case 11:
                        if (f.getSubCategoria() == TipoSubCategoria.CERTIFICADA) {
                            f.setTipoCertificacion(InputHelper.leerCertificacion());
                        }
                        break;
                }

            } else if (producto instanceof Gabinete g) {
                System.out.println("10. Ventana (s/n)");
                System.out.println("11. Color");
                System.out.println("12. Ancho");
                System.out.println("13. Alto");
                System.out.println("14. Profundidad");

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        g.setConVentana(InputHelper.leerBoolean("Tiene ventana? (s/n): "));
                        break;
                    case 11:
                        g.setColor(InputHelper.leerString("Ingrese nuevo color: "));
                        break;
                    case 12:
                        g.setAncho(InputHelper.leerString("Ingrese nuevo ancho: "));
                        break;
                    case 13:
                        g.setAlto(InputHelper.leerString("Ingrese nuevo alto: "));
                        break;
                    case 14:
                        g.setProfundidad(InputHelper.leerString("Ingrese nueva profundidad: "));
                        break;
                }

            } else if (producto instanceof MemoriaRAM m) {
                System.out.println("10. Capacidad");
                System.out.println("11. Tipo de memoria");
                System.out.println("12. Frecuencia");

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        m.setCapacidad(InputHelper.leerInt("Ingrese nueva capacidad: "));
                        break;
                    case 11:
                        m.setTipoDeMemoria(InputHelper.leerString("Ingrese nuevo tipo de memoria: "));
                        break;
                    case 12:
                        m.setFrecuencia(InputHelper.leerString("Ingrese nueva frecuencia: "));
                        break;
                }

            } else if (producto instanceof PlacaDeRed p) {
                System.out.println("10. Dispositivos compatibles");

                p.setDispoditivosCompatibles(InputHelper.leerString("Ingrese nuevos dispositivos compatibles: "));

            } else if (producto instanceof PlacaDeVideo p) {
                System.out.println("10. GPU");
                System.out.println("11. VRAM");
                System.out.println("12. Frecuencia del núcleo");
                System.out.println("13. Ancho de banda");

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        p.setGPU(InputHelper.leerString("Ingrese nueva GPU: "));
                        break;
                    case 11:
                        p.setVRAM(InputHelper.leerString("Ingrese nueva VRAM: "));
                        break;
                    case 12:
                        p.setFrecuenciaNucleo(InputHelper.leerString("Ingrese nueva frecuencia del núcleo: "));
                        break;
                    case 13:
                        p.setAnchoDeBanda(InputHelper.leerString("Ingrese nuevo ancho de banda: "));
                        break;
                }

            } else if (producto instanceof PlacaMadre p) {
                System.out.println("10. Tipo de memoria");
                System.out.println("11. Cantidad de slots");
                System.out.println("12. Back Connect (s/n)");
                System.out.println("13. Botón de Bios (s/n)");

                int op = InputHelper.leerEnteroSwitch();;

                switch (op) {
                    case 10:
                        p.setTipoMemoria(InputHelper.leerString("Ingrese nuevo tipo de memoria: "));
                        break;
                    case 11:
                        p.setCantidadSlotMemoria(InputHelper.leerInt("Ingrese nueva cantidad de slots: "));
                        break;
                    case 12:
                        p.setBackConnect(InputHelper.leerBoolean("Tiene Back Connect? (s/n): "));
                        break;
                    case 13:
                        p.setBotonBios(InputHelper.leerBoolean("Tiene botón de Bios? (s/n): "));
                        break;
                }

            } else if (producto instanceof Procesador pr) {
                System.out.println("10. Frecuencia de reloj");
                System.out.println("11. Número de núcleos");

                int op = InputHelper.leerEnteroSwitch();

                switch (op) {
                    case 10:
                        pr.setFrecuenciaDeReloj(InputHelper.leerString("Ingrese nueva frecuencia de reloj: "));
                        break;
                    case 11:
                        pr.setNumeroDeNucleos(InputHelper.leerInt("Ingrese nuevo número de núcleos: "));
                        break;
                }
            }

            opcionContinuar = InputHelper.leerChar("¿Desea modificar otro atributo? (s/n)");

        } while (opcionContinuar == 's');

        listaProductos.put(producto.getCodigo(), producto);
        GestionJSONProducto.listaProductosToArchivo(listaProductos, "producto.json");
        System.out.println("Producto modificado correctamente.");
    }

}
