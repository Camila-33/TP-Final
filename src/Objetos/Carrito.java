package Objetos;

import Productos.Producto;

import java.util.LinkedList;

public class Carrito {

    private LinkedList<Producto> carritoDeCompras;

    public Carrito() {
        this.carritoDeCompras = new LinkedList<>();
    }

    public void agregarProducto(Producto p){
        carritoDeCompras.add(p);
    }

    public void eliminarProducto(Producto p){
        carritoDeCompras.remove(p);
    }

    public void vaciarCarrito(){
        carritoDeCompras.clear();
    }

    public double calcularTotal(){

        double total = 0;

        for (Producto p : carritoDeCompras){

            total+= p.getPrecio();
        }

        return total;
    }

    public void mostrarCarrito(){

        for (Producto p : carritoDeCompras){
            System.out.println("Nombre: " +p.getNombre() + ", Precio: " +p.getPrecio());
        }
    }
}
