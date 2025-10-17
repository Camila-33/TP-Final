package Gestion;

import Productos.Producto;

import java.util.*;

public class GestionProducto {

    private LinkedHashMap<String, Producto> listaProductos;
    private Map<String, Producto> productosDisponibles;//declarar como Map o LinkedHashMap?


    public GestionProducto() {
        this.listaProductos = new LinkedHashMap<>();
    }

    public void agregarProducto(Producto p){
        listaProductos.put(p.getCodigo(), p);
    }

    public void eliminarProducto(Producto p){

        Iterator<Map.Entry<String, Producto>> it = listaProductos.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, Producto> entry = it.next();

            if(entry.getValue().equals(p)){

                entry.getValue().setActivo(false);
                break;
            }
        }
    }

    public void modificarProducto(Producto p){

    }

    public void mostrarProductos(){

        for (Map.Entry<String, Producto> entry : listaProductos.entrySet()){
            System.out.println("Codigo: " + entry.getKey() + ", Valor: " +entry.getValue());
        }
    }

    public Producto elegirProducto(Scanner teclado){

        List<Producto> productos = new ArrayList<>(productosDisponibles.values());
        System.out.println("Elije el producto que quieres añadir al carrito");

        int i = 0;

        for (Producto p : productos){
            System.out.println(i+1 + "- " + "Nombre: " + p.getNombre() + ", Precio: " +p.getPrecio());
        }

        boolean valido = false;
        int opcion = 0;

        while (!valido){

            try {
                System.out.println("Ingrese una opcion del 1 al " +productosDisponibles.size());
                opcion = teclado.nextInt();

                valido = true;

            }catch (IndexOutOfBoundsException e){
                System.err.println("Error: Indice fuera de rango. Intentelo nuevamente");

            }catch (InputMismatchException e){
                System.err.println("Error: Debe ingresar un número");

                teclado.nextLine();
            }
        }

        return productos.get(opcion-1);
    }

    public Map<String, Producto> cargarProductos(Scanner teclado){

        char boton = 'n';

        LinkedHashMap<String, Producto> aux = new LinkedHashMap<>();

        while (true){

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

            System.out.println("Elija la categoria");

            System.out.println("Seleccione el proveedor del producto");

            System.out.println("¿Desea cargar otro producto? presione s o n");
            boton = teclado.next().toLowerCase().charAt(0);



            while (boton != 's' || boton != 'n'){//Exception?
                System.out.println("Dato invalido. Intentelo nuevamente");
                boton = teclado.next().toLowerCase().charAt(0);
            }

        }

        return
    }


}
