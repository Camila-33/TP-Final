package Users.RegistroUser;

import IngresoDeDatos.InputHelper;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;

import java.util.Scanner;

public class RegistroUser {

    private Scanner teclado;

    public RegistroUser() {
        this.teclado = new Scanner(System.in);
    }

    public Administrador registroAdministrador() {
        System.out.println("Complete con sus datos:");

        String username = InputHelper.pedirUsername("Nuevo username:");
        String contrasenia = InputHelper.pedirContrasenia("Contraseña");
        String nombre = InputHelper.pedirString("Nombre:");
        String apellido = InputHelper.pedirString("Apellido:");
        String email = InputHelper.pedirEmail("Email:");
        String telefono = InputHelper.pedirTelefono("Teléfono:");
        String dni = InputHelper.pedirDni("administrador", "DNI:");
        String direccion = InputHelper.pedirDireccion("Dirección:");

        return new Administrador(username, contrasenia, nombre, apellido, email, telefono, dni, direccion);
    }


    public Usuario registroUsuario() {
        System.out.println("Complete con sus datos:");

        String username = InputHelper.pedirUsername("Nuevo username:");
        String contrasenia = InputHelper.pedirContrasenia("Contraseña");
        String nombre = InputHelper.pedirString("Nombre:");
        String apellido = InputHelper.pedirString("Apellido:");
        String email = InputHelper.pedirEmail("Email:");
        String telefono = InputHelper.pedirTelefono("Teléfono:");
        String dni = InputHelper.pedirDni("usuario", "DNI:");
        String direccion = InputHelper.pedirDireccion("Dirección:");

        return new Usuario(username, contrasenia, nombre, apellido, email, telefono, dni, direccion);
    }
}
