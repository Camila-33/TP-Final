package Validaciones;

import Archivos.GestionJSONUsers.GestionJSONAdministrador;
import Archivos.GestionJSONUsers.GestionJSONUsuario;
import Excepciones.DatoInvalidoException;
import Productos.Producto;
import Users.Proveedor;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;

import java.util.*;

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

            }catch (IndexOutOfBoundsException e){ //¿Este try-catch se maneja aca?
                System.err.println("Error: " +e.getMessage());

            }catch (InputMismatchException e){
                System.err.println("Error: Debe ingresar un número");

                teclado.nextLine();
            }
        }

        return opcion;
    }

    public static void validarBoton(char boton){

        if (boton != 's' && boton != 'n'){
            throw new IllegalArgumentException("Valor inválido. Solo se permite 's' o 'n'.");
        }
    }

    public static void validarOpcionNumero(int opcion){

        if(opcion < 0 || opcion > 9){
            throw new IllegalArgumentException("Opción fuera de rango");
        }
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

    public static boolean existeDni (String dni, String tipoUsuario){

        boolean existe = false;

        HashSet<Administrador> administradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        HashSet<Usuario> usuarios = GestionJSONUsuario.archivoUsuarioToLista("empleado.json");

        if(tipoUsuario.equalsIgnoreCase("administrador")){
            for (Administrador a : administradores){
                if (a.getDni().equals(dni)){
                    existe = true;
                }
            }

        }else{
            for (Usuario e : usuarios){
                if (e.getDni().equals(dni)){
                    existe = true;
                }
            }
        }

        return existe;
    }

    public static boolean existeUser(String username){

        boolean existe = false;

        HashSet<Administrador> administradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        HashSet<Usuario> usuarios = GestionJSONUsuario.archivoUsuarioToLista("empleado.json");
        ArrayList<String> listaDeUsernames = new ArrayList<>();

        for (Administrador admin : administradores) {
            listaDeUsernames.add(admin.getUserName());
        }

        for (Usuario emp : usuarios) {
            listaDeUsernames.add(emp.getUserName());
        }

        for (String s : listaDeUsernames){
            if (s.equals(username)) {
                existe = true;
                break;
            }
        }

        return existe;
    }

}
