package GestionStock.Entidades;

import GestionStock.Enum.TipoMovimiento;
import GestionStock.Utils.Utils;

import java.time.LocalDateTime;
import java.util.Objects;

public  class Movimiento {


    private final LocalDateTime fecha;

    private final String idMovimiento;
    private final String idUsuario;
    private final TipoMovimiento tipoMovimiento;

    private final String idProducto;
    private final String idLote;

    private final int cantidad;


    public Movimiento(String idUsuario, TipoMovimiento tipoMovimiento, String idProducto, String idLote, int cantidad)
    {
        idMovimiento = Utils.generadorID.generarMovimientoID();

        fecha = LocalDateTime.now();
        this.idUsuario = idUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.idProducto = idProducto;
        this.idLote = idLote;
        this.cantidad = cantidad;
    }
    public Movimiento(LocalDateTime fecha, String idMovimiento,String idUsuario, TipoMovimiento tipoMovimiento, String idProducto, String idLote, int cantidad) {
        this.fecha = fecha;
        this.idMovimiento = idMovimiento;
        this.idUsuario = idUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.idProducto = idProducto;
        this.idLote = idLote;
        this.cantidad = cantidad;
    }


    public Movimiento(String fecha, String idMovimiento,String idUsuario, TipoMovimiento tipoMovimiento, String idProducto, String idLote, int cantidad) {
        this.fecha = Utils.stringToLocalDateTime(fecha);
        this.idMovimiento = idMovimiento;
        this.idUsuario = idUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.idProducto = idProducto;
        this.idLote = idLote;
        this.cantidad = cantidad;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movimiento that)) return false;
        return Objects.equals(idMovimiento, that.idMovimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovimiento);
    }


    public String getInformacion( )
    {
        return String.format("\n%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
                getStringFecha(),
                idMovimiento,
                idUsuario,
                tipoMovimiento,
                idProducto,
                (idLote == null) ? "N/A" : idLote,
                cantidad
                );
    }



    @Override
    public String toString() {
        return "Movimiento{" +
                "fecha=" + fecha +
                ", idMovimiento='" + idMovimiento + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", tipoMovimiento=" + tipoMovimiento +
                ", idProducto='" + idProducto + '\'' +
                ", idLote='" + idLote + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getIdLote() {
        return idLote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getStringFecha( )
    {
        return Utils.localDateTimeToString(fecha);
    }


    public String getIdMovimiento() {
        return idMovimiento;
    }
}
