package Productos;

import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

import java.util.Objects;

public class PlacaDeRed extends Producto{

    private String dispositivosCompatibles;

    public PlacaDeRed(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, String dispositivosCompatibles) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
        this.dispositivosCompatibles = dispositivosCompatibles;
    }

    public PlacaDeRed() {

    }

    public String getDispoditivosCompatibles() {
        return dispositivosCompatibles;
    }

    public void setDispoditivosCompatibles(String dispoditivosCompatibles) {
        this.dispositivosCompatibles = dispoditivosCompatibles;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaDeRed that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(dispositivosCompatibles, that.dispositivosCompatibles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dispositivosCompatibles);
    }

    @Override
    public String toString() {
        return "PlacaDeRed {" +
                "dispositivosCompatibles = '" + dispositivosCompatibles + '\'' +
                " " + super.toString();
    }
}
