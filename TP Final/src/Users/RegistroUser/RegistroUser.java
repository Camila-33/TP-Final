package Users.RegistroUser;

import IngresoDeDatos.InputHelper;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;

import java.util.Scanner;

public class RegistroUser {

    public RegistroUser() {
    }

    public Administrador registroAdministrador() {
        System.out.println("Complete los datos:");

        String username = InputHelper.pedirUsername("Username:");
        String contrasenia = InputHelper.pedirContraseniaRegistro("Contraseña");
        String nombre = InputHelper.pedirString("Nombre:");
        String apellido = InputHelper.pedirString("Apellido:");
        String email = InputHelper.pedirEmail("administrador", "Email:");
        String telefono = InputHelper.pedirTelefono("Teléfono:");
        String dni = InputHelper.pedirDniRegistro("administrador", "DNI:");
        String direccion = InputHelper.pedirDireccion("Dirección:");

        return new Administrador(username, contrasenia, nombre, apellido, email, telefono, dni, direccion);
    }


    public Usuario registroUsuario() {
        System.out.println("Complete los datos:");

        String username = InputHelper.pedirUsername("Username:");
        String contrasenia = InputHelper.pedirContraseniaRegistro("Contraseña (debe contener al menos 8 carácteres y un carácter especial):");
        String nombre = InputHelper.pedirString("Nombre:");
        String apellido = InputHelper.pedirString("Apellido:");
        String email = InputHelper.pedirEmail("usuario", "Email:");
        String telefono = InputHelper.pedirTelefono("Teléfono:");
        String dni = InputHelper.pedirDniRegistro("usuario", "DNI:");
        String direccion = InputHelper.pedirDireccion("Dirección:");

        return new Usuario(username, contrasenia, nombre, apellido, email, telefono, dni, direccion);
    }
}
