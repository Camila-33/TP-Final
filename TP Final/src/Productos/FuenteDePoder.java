package Productos;

import Enums.TipoCategoria;
import Enums.TipoCertificacion;
import Enums.TipoSubCategoria;

import java.util.Objects;

public class FuenteDePoder extends Producto{

    private String potencia;
    private TipoCertificacion tipoCertificacion;

    public FuenteDePoder(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, String potencia, TipoCertificacion tipoCertificacion) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
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
    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("Potencia: " + potencia);

        if (subCategoria == TipoSubCategoria.CERTIFICADA) {
            System.out.println("Tipo de certificación: " + tipoCertificacion);
        }

        System.out.println("-------------------------\n");
    }
}
