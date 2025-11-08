package Users.UsuarioSistema;

public class Empleado extends UsuarioSistema{

    public Empleado(String nombre, String apellido, String email, String contrasena, boolean activo, String userName, String telefono, String dni, String direccion) {
        super(nombre, apellido, email, contrasena, activo, userName, telefono, dni, direccion);
    }

    public Empleado() {
    }
}
