package Gestion;

import Users.Proveedor;

import java.util.HashSet;
import java.util.Set;

public class GestionProveedor {

    private Set<Proveedor> listaProveedores;

    public GestionProveedor() {
        this.listaProveedores = new HashSet<>();
    }

    public void agregarProveedor(Proveedor p){
        listaProveedores.add(p);
    }

    public void modificarProveedor(Proveedor p){

    }

    public void eliminarProveedor(Proveedor p){

        for (Proveedor proveedor : listaProveedores){
            if (proveedor.equals(p)){

                proveedor.setActivo(false);
                break;
            }
        }
    }

    public void mostrarProveedores(){

        for (Proveedor p : listaProveedores){
            System.out.println(p);
        }
    }
}
