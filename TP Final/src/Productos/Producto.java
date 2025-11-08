package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Producto {

    protected String nombre;
    protected String descripcion;
    protected String codigo;
    protected double precio;
    protected double peso;
    protected String dimension;
    protected String marca;
    protected boolean activo;
    protected int stock;
    protected int garantiaMeses;
    protected long numeroDeSerie;
    protected LocalDate fechaIngreso;
    protected TipoCategoria categoria;
    protected TipoSubCategoria subCategoria;
    protected Proveedor proveedor;

    public Producto(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, TipoCategoria categoria, TipoSubCategoria subCategoria, Proveedor proveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = "PROD-" + (long)(Math.random() * 900000) + 100000;
        this.precio = precio;
        this.peso = peso;
        this.dimension = dimension;
        this.marca = marca;
        this.activo = activo;
        this.stock = stock;
        this.garantiaMeses = garantiaMeses;
        this.fechaIngreso = fechaIngreso;
        this.categoria = categoria;
        this.subCategoria = subCategoria;
        this.proveedor = proveedor;
        this.numeroDeSerie = (long)(Math.random() * 90000000) + 10000000;
    }

    public Producto(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, Proveedor proveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = "PROD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.precio = precio;
        this.peso = peso;
        this.dimension = dimension;
        this.marca = marca;
        this.activo = true;
        this.stock = stock;
        this.garantiaMeses = garantiaMeses;
        this.fechaIngreso = LocalDate.now();
        this.categoria = categoria;
        this.subCategoria = subCategoria;
        this.proveedor = proveedor;
        this.numeroDeSerie = (long)(Math.random() * 90000000) + 10000000;
    }

    public Producto() {
    }

    public TipoSubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(TipoSubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public long getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public TipoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(precio, producto.precio) == 0 && Double.compare(peso, producto.peso) == 0 && activo == producto.activo && stock == producto.stock && garantiaMeses == producto.garantiaMeses && numeroDeSerie == producto.numeroDeSerie && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(codigo, producto.codigo) && Objects.equals(dimension, producto.dimension) && Objects.equals(marca, producto.marca) && Objects.equals(fechaIngreso, producto.fechaIngreso) && categoria == producto.categoria && subCategoria == producto.subCategoria && Objects.equals(proveedor, producto.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, codigo, precio, peso, dimension, marca, activo, stock, garantiaMeses, numeroDeSerie, fechaIngreso, categoria, subCategoria, proveedor);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre = '" + nombre + '\'' +
                ", descripcion = '" + descripcion + '\'' +
                ", codigo = '" + codigo + '\'' +
                ", precio = " + precio +
                ", peso = " + peso +
                ", dimension = '" + dimension + '\'' +
                ", marca = '" + marca + '\'' +
                ", activo = " + activo +
                ", stock = " + stock +
                ", garantiaMeses = " + garantiaMeses +
                ", numeroDeSerie = " + numeroDeSerie +
                ", fechaIngreso = " + fechaIngreso +
                ", categoria = " + categoria +
                ", subCategoria = " + subCategoria +
                ", proveedo r= " + proveedor +
                '}';
    }
}
