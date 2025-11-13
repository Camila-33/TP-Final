package Productos;

<<<<<<< HEAD:src/Productos/PlacaDeRed.java
import Productos.Categoria.Categoria;
import Users.Proveedor;

import java.time.LocalDate;
=======
import Enums.TipoCategoria;
import Enums.TipoSubCategoria;

>>>>>>> camila-dev:TP Final/src/Productos/PlacaDeRed.java
import java.util.Objects;

public class PlacaDeRed extends Producto{

<<<<<<< HEAD:src/Productos/PlacaDeRed.java
    private String dispoditivosCompatibles;

    public PlacaDeRed(String nombre, String descripcion, double precio, double peso, String dimension, String marca, boolean activo, int stock, int garantiaMeses, LocalDate fechaIngreso, Categoria categoria, Proveedor proveedor, String dispoditivosCompatibles) {
        super(nombre, descripcion, precio, peso, dimension, marca, activo, stock, garantiaMeses, fechaIngreso, categoria, proveedor);
        this.dispoditivosCompatibles = dispoditivosCompatibles;
    }

    public PlacaDeRed(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, Categoria categoria, Proveedor proveedor, String dispoditivosCompatibles) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, proveedor);
        this.dispoditivosCompatibles = dispoditivosCompatibles;
=======
    private String dispositivosCompatibles;

    public PlacaDeRed(String nombre, String descripcion, double precio, double peso, String dimension, String marca, int stock, int garantiaMeses, TipoCategoria categoria, TipoSubCategoria subCategoria, String idProveedor, String dispositivosCompatibles) {
        super(nombre, descripcion, precio, peso, dimension, marca, stock, garantiaMeses, categoria, subCategoria, idProveedor);
        this.dispositivosCompatibles = dispositivosCompatibles;
>>>>>>> camila-dev:TP Final/src/Productos/PlacaDeRed.java
    }

    public PlacaDeRed() {

    }

    public String getDispoditivosCompatibles() {
<<<<<<< HEAD:src/Productos/PlacaDeRed.java
        return dispoditivosCompatibles;
    }

    public void setDispoditivosCompatibles(String dispoditivosCompatibles) {
        this.dispoditivosCompatibles = dispoditivosCompatibles;
=======
        return dispositivosCompatibles;
    }

    public void setDispoditivosCompatibles(String dispoditivosCompatibles) {
        this.dispositivosCompatibles = dispoditivosCompatibles;
>>>>>>> camila-dev:TP Final/src/Productos/PlacaDeRed.java
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlacaDeRed that)) return false;
        if (!super.equals(o)) return false;
<<<<<<< HEAD:src/Productos/PlacaDeRed.java
        return Objects.equals(dispoditivosCompatibles, that.dispoditivosCompatibles);
=======
        return Objects.equals(dispositivosCompatibles, that.dispositivosCompatibles);
>>>>>>> camila-dev:TP Final/src/Productos/PlacaDeRed.java
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD:src/Productos/PlacaDeRed.java
        return Objects.hash(super.hashCode(), dispoditivosCompatibles);
=======
        return Objects.hash(super.hashCode(), dispositivosCompatibles);
>>>>>>> camila-dev:TP Final/src/Productos/PlacaDeRed.java
    }

    @Override
    public String toString() {
        return "PlacaDeRed {" +
<<<<<<< HEAD:src/Productos/PlacaDeRed.java
                "dispoditivosCompatibles = '" + dispoditivosCompatibles + '\'' +
=======
                "dispositivosCompatibles = '" + dispositivosCompatibles + '\'' +
>>>>>>> camila-dev:TP Final/src/Productos/PlacaDeRed.java
                " " + super.toString();
    }
}
