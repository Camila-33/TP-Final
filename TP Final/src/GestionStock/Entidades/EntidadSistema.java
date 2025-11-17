package GestionStock.Entidades;


import GestionStock.Interfaces.iGenerarID;
import GestionStock.Utils.Utils;

import java.time.LocalDate;
import java.util.Objects;

public abstract class EntidadSistema  implements iGenerarID {


    protected String id;
    protected LocalDate fechaAlta = LocalDate.now();
    protected LocalDate fechaBaja = null;
    protected LocalDate fechaUltimaActualizacion = fechaAlta;

    protected boolean estadoActivo = true;


    public EntidadSistema() {
    }

    public EntidadSistema(String id) {
        this.id = id;
    }

    public EntidadSistema(String id, LocalDate fechaAlta, LocalDate fechaBaja, LocalDate fechaUltimaActualizacion, boolean estadoActivo) {
        this.id = id;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.estadoActivo = estadoActivo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = Utils.stringToLocalDate(fechaAlta);
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = Utils.stringToLocalDate(fechaBaja);
    }

    public LocalDate getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }


    public void setFechaUltimaActualizacion(LocalDate fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = Utils.stringToLocalDate(fechaUltimaActualizacion);
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }



    public String getStringFechaAlta( )
    {
        return Utils.localDateToString(fechaAlta);
    }
    public String getStringFechaBaja( )
    {
        return Utils.localDateToString(fechaBaja);
    }
    public String getStringFechaUltimaActualizacion( )
    {
        return Utils.localDateToString(fechaUltimaActualizacion);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntidadSistema that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        return "EntidadSistema{" +
                "id='" + id + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                ", estadoActivo=" + estadoActivo +
                '}';
    }



    public abstract String getInformacion();

    public void actualizarFechaUltimaActualizacion( )
    {
        fechaUltimaActualizacion = LocalDate.now();
    }



    public void darAlta() {
        estadoActivo = true;
        actualizarFechaUltimaActualizacion();;
    }
    public void darBaja()
    {
        estadoActivo = false;
        fechaBaja = LocalDate.now();
        actualizarFechaUltimaActualizacion();
    }


}
