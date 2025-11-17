package Users.UsuarioSistema;

public class Administrador extends UsuarioSistema{

    public Administrador(String userName, String contrasena, String nombre, String apellido, String email, String telefono, String dni, String direccion) {
        super(userName, contrasena, nombre, apellido, email, telefono, dni, direccion);
    }

    public Administrador() {
    }
}
