package Gestion;

import Productos.Producto;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Cliente;
import Users.UsuarioSistema.UsuarioSistema;

import java.util.*;

public class GestionMenu {

    private Scanner teclado = new Scanner(System.in); //NO SE SI ESTO PUEDE IR ACÁ, PREGUNTAR
    private Map<String, Producto> productosDisponibles;
    GestionProducto gestionProducto = new GestionProducto();

    public GestionMenu(Map<String, Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public void elegirMenu(UsuarioSistema usuario){

        if (usuario instanceof Administrador){
            mostrarMenuAdministrador((Administrador) usuario);

        } else if (usuario instanceof Cliente) {
            mostrarMenuCliente((Cliente) usuario);

        }else {
            System.out.println("Usuario no reconocido");
        }
    }

    public void mostrarMenuCliente(Cliente cliente){

        boolean salir = true;

        while (salir){

            System.out.println("---MENÚ---");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Eliminar producto del carrito");
            System.out.println("3. Ver productos del carrito");
            System.out.println("4. Vaciar carrito");
            System.out.println("5. Calcular total a pagar");
            System.out.println("6. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    Producto pA = gestionProducto.elegirProducto(teclado);
                    cliente.agregarAlCarrito(pA);

                case 2:
                    Producto pE = gestionProducto.elegirProducto(teclado);
                    cliente.quitarDelCarrito(pE);

                case 3:
                    cliente.mostrarCarrito();

                case 4:
                    cliente.vaciarCarrito();
                    System.out.println("Carrito vaciado con exito");

                case 5:
                    cliente.calcularTotal();

                case 6:
                    System.out.println("Saliendo del menú...");
                    salir = false;

                default:
                    System.out.println("Opcion fuera de rango. Intentelo nuevamente");
            }
        }
    }

    public void mostrarMenuAdministrador(Administrador administrador){

        boolean salir = true;

        while (salir){

            System.out.println("---MENÚ---");
            System.out.println("1. Cargar productos");
            System.out.println("2. Eliminar productos");
            System.out.println("3. Modificar datos de un producto");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Cargar proveedor");
            System.out.println("6. Eliminar proveedor");
            System.out.println("7. Mostrar proveedores");
            System.out.println("8. Modificar datos de un proveedor");
            System.out.println("9. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:

                case 8:

                case 9:
                    System.out.println("Saliendo del menú...");
                    salir = false;

                default:
                    System.out.println("Opcion fuera de rango. Intentelo nuevamente");
            }
        }
    }
}
