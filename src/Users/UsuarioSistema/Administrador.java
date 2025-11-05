package Users.UsuarioSistema;

import Gestion.GestionProducto;
import Gestion.GestionProveedor;
import Productos.Producto;
import Users.Proveedor;

import java.util.Scanner;

public class Administrador extends UsuarioSistema{

    public Administrador(String nombre, String apellido, String email, String contrasena, boolean activo, String userName, String telefono, String dni, String direccion) {
        super(nombre, apellido, email, contrasena, activo, userName, telefono, dni, direccion);
    }

    public Administrador() {
    }

    public Producto elegirProductosDisponibles(GestionProducto gestionP, Scanner teclado){
        return gestionP.elegirProductosDisponibles(teclado);
    }

    //Sacar todo esto y modificar en menu

    public void cargarProveedor(GestionProveedor gestionP, Scanner teclado){
        gestionP.cargarProveedor(teclado);
    }

    public void darDeAltaProveedor(GestionProveedor gestionP, Proveedor p){
        gestionP.darAltaProveedor(p);
    }

    public void darDeBajaProveedor(GestionProveedor gestionP, Proveedor p){
        gestionP.darBajaProveedor(p);
    }

    public void buscarProveedor(GestionProveedor gestionP, String nombre){
        gestionP.buscarProveedor(nombre);
    }

    public void modificarProveedor(GestionProveedor gestionP, Proveedor p, Scanner teclado){
        gestionP.modificarProveedor(p, teclado);
    }

    public Proveedor elegirProveedor(GestionProveedor gestionP, Scanner teclado){
        return gestionP.elegirProveedor(teclado);
    }

    public Proveedor elegirProveedorDeBaja(GestionProveedor gestionP, Scanner teclado){
        return gestionP.elegirProveedorDeBaja(teclado);
    }

    public void mostrarProveedoresDisponibles(GestionProveedor gestionP){
        gestionP.mostrarProveedoresDisponibles();
    }

    public void darDeBajaProducto(GestionProducto gestion, Producto p){
        gestion.darBajaProducto(p);
    }

    public void darDeAltaProducto(GestionProducto gestion, Producto p){
        gestion.darAltaProducto(p);
    }

    public void buscarProductoPorNombre(GestionProducto gestion, String nombreBusqueda){
        gestion.buscarProductosPorNombre(nombreBusqueda);
    }

    public void modificarProducto(GestionProducto gestion, Producto p){
        gestion.modificarProducto(p);
    }

    public void mostrarProductos(GestionProducto gestion){
        gestion.mostrarProductos();
    }

    public void cargarProductos(GestionProducto gestion, Scanner teclado){
        gestion.cargarProductos(teclado);
    }

    public Producto elegirProductosDeBaja(GestionProducto gestion, Scanner teclado){
        return gestion.elegirProductosDeBaja(teclado);
    }
}
