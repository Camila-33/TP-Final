package Users.UsuarioSistema;

import Gestion.GestionMenu;
import Interfaces.Autenticable;

import java.util.UUID;

public abstract class UsuarioSistema {

    protected String idUsuario;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String contrasena;
    protected boolean activo;

    public UsuarioSistema(String nombre, String apellido, String email, String contrasena, boolean activo) {
        this.idUsuario = "USER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.activo = activo;
    }

    public UsuarioSistema() {
    }

    public String getIdUsuario() {
        return idUsuario;
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
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombreCompleto() {

        return this.nombre + " " + this.apellido;
    }

    public void mostrarMenu(GestionMenu gestionMenu){
        gestionMenu.elegirMenu(this);
    }

    @Override
    public String toString() {
        return "idUsuario = '" + idUsuario + '\'' +
                ", nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", email = '" + email + '\'' +
                ", contrasena = '" + contrasena + '\'' +
                ", activo = " + activo +
                '}';
    }
}
