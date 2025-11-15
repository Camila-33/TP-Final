package Productos;

import Archivos.GestionJSONProductos.GestionJSONProducto;
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Producto {

    protected static int contador = 0;
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
    private String idProveedor;
    protected String numeroDeSerie;
    protected LocalDate fechaIngreso;
    protected TipoCategoria categoria;
    protected TipoSubCategoria subCategoria;

    public Producto(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = String.valueOf(++contador);
        this.precio = precio;
        this.peso = peso;
        this.dimension = dimension;
        this.marca = marca;
        this.activo = true;
        this.stock = stock;
        this.garantiaMeses = garantiaMeses;
        this.idProveedor = idProveedor;
        this.fechaIngreso = LocalDate.now();
        this.categoria = categoria;
        this.subCategoria = subCategoria;
        this.numeroDeSerie = String.valueOf((long)(Math.random() * 90000000) + 10000000);
    }

    public Producto() {
    }

    public TipoSubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(TipoSubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
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

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Double.compare(precio, producto.precio) == 0 && Double.compare(peso, producto.peso) == 0 && activo == producto.activo && stock == producto.stock && garantiaMeses == producto.garantiaMeses && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(codigo, producto.codigo) && Objects.equals(dimension, producto.dimension) && Objects.equals(marca, producto.marca) && Objects.equals(numeroDeSerie, producto.numeroDeSerie) && Objects.equals(fechaIngreso, producto.fechaIngreso) && categoria == producto.categoria && subCategoria == producto.subCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, codigo, precio, peso, dimension, marca, activo, stock, garantiaMeses, numeroDeSerie, fechaIngreso, categoria, subCategoria);
    }

    public void mostrarProducto() {
        System.out.println("-------------------------");
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: $" + precio);
        System.out.println("Peso: " + peso);
        System.out.println("Dimensión: " + dimension);
        System.out.println("Marca: " + marca);
        System.out.println("Stock: " + stock);
        System.out.println("Garantía: " + garantiaMeses + " meses");
    }
}
