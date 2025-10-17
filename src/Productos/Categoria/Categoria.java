package Productos.Categoria;

import java.util.Objects;
import java.util.UUID;

public class Categoria {

    protected String nombre;
    protected String idCategoria;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.idCategoria = "CATEG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Categoria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(nombre, categoria.nombre) && Objects.equals(idCategoria, categoria.idCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, idCategoria);
    }

    @Override
    public String toString() {
        return "Productos.Categoria{" +
                "nombre = '" + nombre + '\'' +
                ", idCategoria = '" + idCategoria + '\'' +
                '}';
    }
}
