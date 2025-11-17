package GestionStock.Entidades;

import GestionStock.Utils.Utils;

import java.util.Objects;

public final class Lote extends EntidadSistema {

    private String idProducto;
    private String idProveedor;
    private int cantidadInicial;
    private int cantidadDisponible;
    private double precioUnitario;

    public Lote() {

    }

    public Lote(String id) {
        super(id);
    }
    public Lote(String id, String idProducto, String idProveedor, int cantidadInicial, double precioUnitario) {
        super(id);
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.cantidadInicial = cantidadInicial;
        cantidadDisponible = cantidadInicial;
        this.precioUnitario = precioUnitario;
    }

    public Lote(String idProducto, String idProveedor, int cantidadInicial, double precioUnitario) {
        generarID();
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.cantidadInicial = cantidadInicial;
        cantidadDisponible = cantidadInicial;
        this.precioUnitario = precioUnitario;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lote that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getIdProducto(), that.getIdProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdProducto());
    }




    @Override
    public String toString() {
        return "Lote{" +
                super.toString() +
                "idProducto='" + idProducto + '\'' +
                ", idProveedor='" + idProveedor + '\'' +
                ", cantidadInicial=" + cantidadInicial +
                ", cantidadDisponible=" + cantidadDisponible +
                ", precioUnitario=" + precioUnitario +
                '}';
    }

    @Override
    public void generarID() {
        id = Utils.generadorID.generarLoteID();
    }

    @Override
    public String getInformacion()
    {

        return String.format("\n%-15s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
                getStringFechaAlta(),
                id,
                idProducto,
                idProveedor,
                cantidadInicial,
                cantidadDisponible,
                precioUnitario,
                estadoActivo,
                getStringFechaBaja(),
                getStringFechaUltimaActualizacion());
    }

    public double calcularValoracion() {
        return cantidadDisponible*precioUnitario;
    }

    public boolean sacarProducto(int cantidad)
    {
        if (!estadoActivo || cantidad > cantidadDisponible || cantidad <= 0)  return  false;



        cantidadDisponible -= cantidad;
        System.out.println(String.format("[LOTE: %S] SE EXTRAJO: %d unidades Quedan: %d", id, cantidad, cantidadDisponible));

        if (cantidadDisponible == 0)
            darBaja();


        actualizarFechaUltimaActualizacion();

        return true;
    }

    public boolean ajustar(int cantidad)
    {
        if (!estadoActivo)
            return false;
        cantidadInicial  = cantidad;
        return true;
    }


}
