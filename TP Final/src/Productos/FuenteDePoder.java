package Productos;

<<<<<<< HEAD:src/Productos/FuenteDePoder.java
import Enums.TipoCertificacion;
import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
=======
import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;

>>>>>>> camila-dev:TP Final/src/Productos/FuenteDePoder.java
import java.util.Objects;

public class FuenteDePoder extends Producto{

    private String potencia;
    private TipoCertificacion tipoCertificacion;

<<<<<<< HEAD:src/Productos/FuenteDePoder.java
    public FuenteDePoder(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, String potencia, TipoCertificacion tipoCertificacion) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.potencia = potencia;
        this.tipoCertificacion = tipoCertificacion;
    }

    public FuenteDePoder(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, String potencia, TipoCertificacion tipoCertificacion) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
=======
    public FuenteDePoder(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, String potencia, TipoCertificacion tipoCertificacion) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
>>>>>>> camila-dev:TP Final/src/Productos/FuenteDePoder.java
        this.potencia = potencia;
        this.tipoCertificacion = tipoCertificacion;
    }

    public FuenteDePoder() {

    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public TipoCertificacion getTipoCertificacion() {
        return tipoCertificacion;
    }

    public void setTipoCertificacion(TipoCertificacion tipoCertificacion) {
        this.tipoCertificacion = tipoCertificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FuenteDePoder that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(potencia, that.potencia) && tipoCertificacion == that.tipoCertificacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), potencia, tipoCertificacion);
    }

    @Override
    public String toString() {
        return "FuenteDePoder {" +
                "potencia = '" + potencia + '\'' +
                ", tipoCertificacion = " + tipoCertificacion +
                " " + super.toString();
    }
}
