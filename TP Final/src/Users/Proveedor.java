package Users;

import Enums.TipoProveedor;
import Productos.Producto;

import java.time.LocalDate;
import java.util.*;

public class Proveedor {

    private String idProveedor;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String cuit;
    private boolean activo;
    private LocalDate fechaAlta;
    private TipoProveedor tipoProveedor;
    private HashMap<String, Producto> productosSuministrados;

    public Proveedor(String nombre, String apellido, String email, String telefono, String cuit, TipoProveedor tipoProveedor) {
        this.idProveedor = String.valueOf((long)(Math.random() * 900000) + 100000);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.cuit = cuit;
        this.activo = true;
        this.fechaAlta = LocalDate.now();
        this.tipoProveedor = tipoProveedor;
        this.productosSuministrados = new HashMap<>();
    }

    public Proveedor(String nombre, String apellido, String email, String telefono, String cuit, boolean activo, LocalDate fechaAlta, TipoProveedor tipoProveedor) {
        this.idProveedor = String.valueOf((long)(Math.random() * 900000) + 100000);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.cuit = cuit;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.tipoProveedor = tipoProveedor;
        this.productosSuministrados = new HashMap<>();
    }

    public Proveedor() {
        this.idProveedor = String.valueOf((long)(Math.random() * 900000) + 100000);
        this.productosSuministrados = new LinkedHashMap<>();
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public TipoProveedor getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getNombreCompleto() {

        return this.nombre + " " + this.apellido;
    }

    public Map<String, Producto> getProductosSuministrados() {
        return productosSuministrados;
    }

    public void agregarProductos(HashMap<String, Producto> productosNuevos) {

        if (productosNuevos != null && !productosNuevos.isEmpty()) {
            productosSuministrados.putAll(productosNuevos);
            System.out.println("Productos agregados correctamente.");

        } else {
            System.out.println("No hay productos para agregar.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return activo == proveedor.activo && Objects.equals(idProveedor, proveedor.idProveedor) && Objects.equals(nombre, proveedor.nombre) && Objects.equals(apellido, proveedor.apellido) && Objects.equals(email, proveedor.email) && Objects.equals(telefono, proveedor.telefono) && Objects.equals(cuit, proveedor.cuit) && Objects.equals(fechaAlta, proveedor.fechaAlta) && tipoProveedor == proveedor.tipoProveedor && Objects.equals(productosSuministrados, proveedor.productosSuministrados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, nombre, apellido, email, telefono, cuit, activo, fechaAlta, tipoProveedor, productosSuministrados);
    }

    @Override
    public String toString() {
        return "Users.Proveedor{" +
                "idProveedor = '" + idProveedor + '\'' +
                ", nombre = '" + nombre + '\'' +
                ", Apellido = '" + apellido + '\'' +
                ", email = '" + email + '\'' +
                ", telefono = '" + telefono + '\'' +
                ", cuit = '" + cuit + '\'' +
                ", activo = " + activo +
                ", fechaAlta = " + fechaAlta +
                ", tipoProveedor = " + tipoProveedor +
                ", productosSuministrados = " + productosSuministrados +
                '}';
    }
}
