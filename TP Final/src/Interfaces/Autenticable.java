package Interfaces;

public interface Autenticable {

    public boolean login(String usuario, String contrasena);
    public void restablecerContrsena(String contrasenaActual, String contrasenaNueva);
    public void restablecerEmail(String nuevoEmail);
}
