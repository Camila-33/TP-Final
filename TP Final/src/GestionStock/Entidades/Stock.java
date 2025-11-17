package GestionStock.Entidades;

import GestionStock.Utils.Utils;

import java.util.Objects;

public final class Stock extends EntidadSistema {

    private String idProducto;
    private int cantidad;
    private int min;
    private int max;


    public Stock() {
    }

    public Stock(String idProducto) {
        this.idProducto = idProducto;
    }

    public Stock(String idProducto, int cantidad) {
        generarID();
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
    public Stock(String idProducto, int cantidad, int min, int max) {
        generarID();
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.min = min;
        this.max = max;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock stock)) return false;
        return Objects.equals(getIdProducto(), stock.getIdProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProducto());
    }

    @Override
    public String toString() {
        return "Stock{" +
                "  idStock='" + id +
                ", fechaAlta=" + fechaAlta +
                ", estadoActivo=" + estadoActivo +
                "idProducto='" + idProducto + '\'' +
                ", cantidad=" + cantidad +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public void generarID() {
        id = Utils.generadorID.generarStockID();
    }

    @Override
    public String getInformacion()
    {

        return String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
                getStringFechaAlta(),
                id,
                idProducto,
                cantidad,
                min,
                max,
                estadoActivo,
                getStringFechaBaja(),
                getStringFechaUltimaActualizacion()
        );
    }

    public boolean verificarStock()
    {
        return cantidad > 0;
    }

    public boolean verificarStockBajo() {
        return cantidad > 0 && cantidad < min;
    }

    public boolean verificarStockAlto() {
        return cantidad > max;
    }

    public boolean ingresar(int cantidadIngresada)
    {
        if (cantidadIngresada < 0 || !estadoActivo) return false;
        cantidad += cantidadIngresada;
        return true;
    }

    public boolean extraer(int cantidadExtraer)
    {
        if (cantidadExtraer > cantidad || cantidadExtraer <= 0 || cantidad == 0 || !estadoActivo)
            return false;

        cantidad -= cantidadExtraer;

        return true;
    }

    public boolean ajustar(int ajuste)
    {
        if (ajuste < 0) return false;
        cantidad = ajuste;
        return true;
    }

}
