package Users.RegistroUser;

import Excepciones.DatoInvalidoException;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;
import Validaciones.Validaciones;

import java.util.Scanner;

public class RegistroUser {

    private Scanner teclado;

    public RegistroUser() {
        this.teclado = new Scanner(System.in);
    }

    public Administrador registroAdministrador(){

        System.out.println("Complete con sus datos:");

        String username = "";
        boolean usernameValido = false;

        while (!usernameValido){

            System.out.println("Username: ");

            try {
                username = teclado.nextLine();
                Validaciones.validarNombreUsuario(username);

                if(!Validaciones.existeUser(username)){
                    usernameValido = true;

                }else {
                    System.out.println("El Username ya existe en el sistema. Por favor, ingrese otro");
                }

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }


        String contrasenia = "";
        boolean contraseniaValida = false;

        while (!contraseniaValida){

            System.out.println("Contraseña: ");

            try {
                contrasenia = teclado.nextLine();
                Validaciones.validarContrasenia(contrasenia);

                contraseniaValida = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String nombre = "";
        boolean nombreValido = false;

        while(!nombreValido){

            System.out.println("Nombre: ");

            try {
                nombre = teclado.nextLine();
                Validaciones.validarString(nombre);

                nombreValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String apellido = "";
        boolean apellidoValido = false;

        while(!apellidoValido){

            System.out.println("Nombre: ");

            try {
                apellido = teclado.nextLine();
                Validaciones.validarString(apellido);

                apellidoValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String email = "";
        boolean emailValido = false;

        while (!emailValido){

            System.out.println("Email: ");

            try {
                email = teclado.nextLine();
                Validaciones.validarEmail(email);

                emailValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String telefono = "";
        boolean telefonoValido = false;

        while (!telefonoValido){

            System.out.println("Teléfono: ");

            try {
                telefono = teclado.nextLine();
                Validaciones.validarTelefono(telefono);

                telefonoValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String dni = "";
        boolean dniValido = false;

        while (!dniValido){

            System.out.println("DNI: ");

            try {
                dni = teclado.nextLine();
                Validaciones.validarDNI(dni);

                dniValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String direccion = "";
        boolean direccionValida = false;

        while (!direccionValida){

            System.out.println("Dirección: ");

            try {
                direccion = teclado.nextLine();
                Validaciones.validarDireccion(direccion);

                direccionValida = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        return new Administrador(username, contrasenia, nombre, apellido, email, telefono, dni, direccion);
    }


    public Usuario registroUsuario(){

        System.out.println("Complete con sus datos:");

        String username = "";
        boolean usernameValido = false;

        while (!usernameValido){

            System.out.println("Username: ");

            try {
                username = teclado.nextLine();
                Validaciones.validarNombreUsuario(username);

                if(!Validaciones.existeUser(username)){
                    usernameValido = true;

                }else {
                    System.out.println("El Username ya existe en el sistema. Por favor, ingrese otro");
                }

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }


        String contrasenia = "";
        boolean contraseniaValida = false;

        while (!contraseniaValida){

            System.out.println("Contraseña: ");

            try {
                contrasenia = teclado.nextLine();
                Validaciones.validarContrasenia(contrasenia);

                contraseniaValida = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String nombre = "";
        boolean nombreValido = false;

        while(!nombreValido){

            System.out.println("Nombre: ");

            try {
                nombre = teclado.nextLine();
                Validaciones.validarString(nombre);

                nombreValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String apellido = "";
        boolean apellidoValido = false;

        while(!apellidoValido){

            System.out.println("Nombre: ");

            try {
                apellido = teclado.nextLine();
                Validaciones.validarString(apellido);

                apellidoValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String email = "";
        boolean emailValido = false;

        while (!emailValido){

            System.out.println("Email: ");

            try {
                email = teclado.nextLine();
                Validaciones.validarEmail(email);

                emailValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String telefono = "";
        boolean telefonoValido = false;

        while (!telefonoValido){

            System.out.println("Teléfono: ");

            try {
                telefono = teclado.nextLine();
                Validaciones.validarTelefono(telefono);

                telefonoValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String dni = "";
        boolean dniValido = false;

        while (!dniValido){

            System.out.println("DNI: ");

            try {
                dni = teclado.nextLine();
                Validaciones.validarDNI(dni);

                dniValido = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        String direccion = "";
        boolean direccionValida = false;

        while (!direccionValida){

            System.out.println("Dirección: ");

            try {
                direccion = teclado.nextLine();
                Validaciones.validarDireccion(direccion);

                direccionValida = true;

            }catch (DatoInvalidoException e){
                System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
            }
        }

        return new Usuario(username, contrasenia, nombre, apellido, email, telefono, dni, direccion);
    }
}
