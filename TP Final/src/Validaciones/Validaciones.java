package Validaciones;

import Archivos.GestionJSONUsers.GestionJSONAdministrador;
import Archivos.GestionJSONUsers.GestionJSONProveedor;
import Archivos.GestionJSONUsers.GestionJSONUsuario;
import Excepciones.DatoInvalidoException;
import Users.Proveedor;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;

import java.util.*;

public class Validaciones {

    public Validaciones() {
    }


    public static <T> void ingresarOpcionValida(List<T> lista, int opcion){

        if (opcion < 1 || opcion > lista.size()){
            throw new IndexOutOfBoundsException("Numero fuera de rango");
        }
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

        if (contrasenia.length() < 8) {
            throw new DatoInvalidoException("La contraseña debe contener al menos 8 caracteres.");
        }

        if (!contrasenia.matches(".*[0-9].*")) {
            throw new DatoInvalidoException("La contraseña debe contener al menos un número.");
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

    public static void validarNombreProducto(String nombre) throws DatoInvalidoException {

        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\\s\\-_,.]+$")) {
            throw new DatoInvalidoException("El nombre del producto solo puede contener letras, números y los símbolos -, _ , .");
        }
    }

    public static void validarDescripcionProducto(String descripcion) throws DatoInvalidoException {

        if (!descripcion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\\s.,;:!¡¿?\\-_%()\"']+$")) {
            throw new DatoInvalidoException("La descripción solo puede contener letras, números y signos de puntuación válidos.");
        }

        if (descripcion.trim().length() < 10) {
            throw new DatoInvalidoException("La descripción debe tener al menos 10 caracteres.");
        }
    }

    public static <T extends Number> void validarNumero(T valor) throws DatoInvalidoException {
        if (valor.doubleValue() <= 0) {
            throw new DatoInvalidoException("El valor debe ser mayor a 0.");
        }
    }

    public static void validarDimension(String dimension) throws DatoInvalidoException {
        if (dimension == null || dimension.trim().isEmpty()) {
            throw new DatoInvalidoException("La dimensión no puede estar vacía.");
        }

        if (!dimension.matches("[0-9xX. ]+[a-zA-Z]*")) {
            throw new DatoInvalidoException("La dimensión solo puede contener números, 'x', puntos y unidades como mm, cm, etc.");
        }
    }

    public static void validarMarca(String marca) throws DatoInvalidoException {
        if (marca == null || marca.trim().isEmpty()) {
            throw new DatoInvalidoException("La marca no puede estar vacía.");
        }

        if (!marca.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new DatoInvalidoException("La marca solo puede contener letras y espacios.");
        }

        if (marca.length() < 2 || marca.length() > 50) {
            throw new DatoInvalidoException("La marca debe tener entre 2 y 50 caracteres.");
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

    public static void validarCuit(String cuit) throws DatoInvalidoException {

        String regex = "^\\d{2}-\\d{8}-\\d$";

        if (cuit == null || !cuit.matches(regex)) {
            throw new DatoInvalidoException("El CUIT ingresado no es válido.");
        }
    }

    public static void validarIDYCodigo(String id) throws DatoInvalidoException {

        if (!id.matches("\\d+")) {
            throw new DatoInvalidoException("El ID o código solo debe contener números.");
        }
    }


    public static boolean existeDni (String dni, String tipoUsuario){

        boolean existe = false;

        HashSet<Administrador> administradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        HashSet<Usuario> usuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");

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
        HashSet<Usuario> usuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");
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

    public static boolean existeCodigoProveedor(String codigoProveedor) {

        HashSet<Proveedor> listaProveedores = GestionJSONProveedor.archivoProveedorToLista("proveedor.json");

        for (Proveedor p : listaProveedores) {
            if (p.getIdProveedor().equals(codigoProveedor)) {
                return true;
            }
        }
        return false;
    }
}
