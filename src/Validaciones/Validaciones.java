package Validaciones;

import Excepciones.DatoInvalidoException;
import Productos.Producto;
import Users.Proveedor;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Validaciones {

    public Validaciones() {
    }


    public static int esValido(Scanner teclado, List<Proveedor> proveedores){

        int opcion = 0;
        boolean valido = false;

        while (!valido){

            try {

                opcion = teclado.nextInt();
                teclado.nextLine();

                if (opcion < 1 || opcion > proveedores.size()){
                    throw new IndexOutOfBoundsException("Numero fuera de rango");
                }

                valido = true;

            }catch (IndexOutOfBoundsException a){
                System.err.println("Error: " +a.getMessage());

            }catch (InputMismatchException a){
                System.err.println("Error: Tipo de dato invalido");
            }
        }

        return opcion;
    }


    public static int ingresarOpcionValida(Scanner teclado, List<Producto> productos){

        boolean valido = false;
        int opcion = 0;

        while (!valido){

            try {
                System.out.println("Ingrese una opcion del 1 al " +productos.size());
                opcion = teclado.nextInt();

                if (opcion < 1 || opcion > productos.size()){
                    throw new IndexOutOfBoundsException("Numero fuera de rango");
                }

                valido = true;

            }catch (IndexOutOfBoundsException e){ //¿o excepción personalizada?¿El código es muy similar al de arriba?
                System.err.println("Error: " +e.getMessage());

            }catch (InputMismatchException e){
                System.err.println("Error: Debe ingresar un número");

                teclado.nextLine();
            }
        }

        return opcion;
    }


    public static void validarNombreUsuario (String username) throws DatoInvalidoException {

        if (username.length() < 5 || username.length() > 30){
            throw new DatoInvalidoException("El nombre de usuario debe tener al menos 5 caracteres.");
        }
        if (username.contains(" ")){
            throw new DatoInvalidoException("El nombre de usuario no puede contener espacios.");
        }
    }


    public static void validarContrasenia(String contrasenia) throws DatoInvalidoException {

        if (contrasenia.length() < 10) {
            throw new DatoInvalidoException("La contraseña debe contener al menos 10 caracteres.");
        }

        if (!contrasenia.matches(".*[0-9].*")) {
            throw new DatoInvalidoException("La contraseña debe contener al menos un numero.");
        }

        if (!contrasenia.matches(".*[!@#$%^&*_(),.?\":{}|<>].*")) {
            throw new DatoInvalidoException("La contraseña debe contener al menos un carácter especial.");
        }
    }

    public static void validarString(String cadena) throws DatoInvalidoException {

        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isDigit(cadena.charAt(i))) {
                throw new DatoInvalidoException("No se permiten números en este campo.");
            }
        }
    }

    public static void validarDNI(String dni) throws DatoInvalidoException {

        if (!dni.matches("^[0-9]+$")) {
            throw new DatoInvalidoException("El DNI solo debe contener números.");
        }
    }

    public static void validarTelefono (String telefono) throws DatoInvalidoException {

        if (!telefono.matches("^[0-9]+$")) {
            throw new DatoInvalidoException("El teléfono solo debe contener números.");
        }

        if (telefono.length() != 10){
            throw new DatoInvalidoException("El teléfono debe tener 10 dígitos.");
        }
    }

    public static void validarEmail(String email) throws DatoInvalidoException {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (email == null || !email.matches(regex)) {
            throw new DatoInvalidoException("El email ingresado no tiene un formato válido.");
        }
    }

    public static void validarDireccion(String direccion) throws DatoInvalidoException {

        String regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9°.,\\s-]{5,100}$";

        if (direccion == null || !direccion.matches(regex)) {
            throw new DatoInvalidoException("La dirección ingresada no es válida.");
        }
    }

}
