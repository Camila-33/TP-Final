package Productos;

import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;
import Users.Proveedor;

import java.time.LocalDate;
import java.util.Objects;

public class FuenteDePoder extends Producto{

    private String potencia;
    private TipoCertificacion tipoCertificacion;

    public FuenteDePoder(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, TipoCategoria categoria, TipoSubCategoria subCategoria, Proveedor proveedor, String potencia, TipoCertificacion tipoCertificacion) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, subCategoria, proveedor);
        this.potencia = potencia;
        this.tipoCertificacion = tipoCertificacion;
    }

    public FuenteDePoder(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, Proveedor proveedor, String potencia, TipoCertificacion tipoCertificacion) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, proveedor);
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
