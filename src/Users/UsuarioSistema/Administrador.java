package Users.UsuarioSistema;

import Gestion.GestionProducto;
import Gestion.GestionProveedor;
import Productos.Producto;
import Users.Proveedor;

public class Administrador extends UsuarioSistema{

    public Administrador(String nombre, String apellido, String email, String contrasena, boolean activo) {
        super(nombre, apellido, email, contrasena, activo);
    }

    public Administrador() {
    }

    public void agregarProveedor(GestionProveedor gestionP, Proveedor p){
        gestionP.agregarProveedor(p);
    }

    public void eliminarProveedor(GestionProveedor gestionP, Proveedor p){
        gestionP.eliminarProveedor(p);
    }

    public void modificarProveedor(GestionProveedor gestionP, Proveedor p){
        gestionP.modificarProveedor(p);
    }

    public void mostrarProveedores(GestionProveedor gestionP){
        gestionP.mostrarProveedores();
    }

    public void agregarProducto(GestionProducto gestion, Producto p){
        gestion.agregarProducto(p);
    }

    public void eliminarProducto(GestionProducto gestion, Producto p){
        gestion.eliminarProducto(p);
    }

    public void modificarProducto(GestionProducto gestion, Producto p){
        gestion.modificarProducto(p);
    }

    public void mostrarProductos(GestionProducto gestion){
        gestion.mostrarProductos();
    }

}
