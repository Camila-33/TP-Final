package Users.UsuarioSistema;

import Gestion.GestionMenu;
import Objetos.Carrito;
import Productos.Producto;

public class Cliente extends UsuarioSistema{

    private Carrito carrito;

    public Cliente(String nombre, String apellido, String email, String contrasena, boolean activo) {
        super(nombre, apellido, email, contrasena, activo);
        this.carrito = new Carrito();
    }

    public void agregarAlCarrito(Producto p){
        carrito.agregarProducto(p);
    }

    public void quitarDelCarrito(Producto p){
        carrito.eliminarProducto(p);
    }

    public void vaciarCarrito(){
        carrito.vaciarCarrito();
    }

    public void mostrarCarrito(){
        carrito.mostrarCarrito();
    }

    public double calcularTotal(){
        return carrito.calcularTotal();
    }

}
