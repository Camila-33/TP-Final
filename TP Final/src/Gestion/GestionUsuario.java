package Gestion;

import Archivos.GestionJSONUsers.GestionJSONUsuario;
import Excepciones.DatoInvalidoException;
import Interfaces.MetodosGestion;
import Users.RegistroUser.RegistroUser;
import Users.UsuarioSistema.Usuario;
import Validaciones.Validaciones;

import java.util.HashSet;
import java.util.Scanner;

public class GestionUsuario implements MetodosGestion <Usuario>{

    private HashSet<Usuario> listaUsuarios;
    private RegistroUser registroUser;
    private Scanner teclado;

    public GestionUsuario() {
        this.listaUsuarios = new HashSet<>();
        this.registroUser = new RegistroUser();
        this.teclado = new Scanner(System.in);
    }

    @Override
    public void agregarYguardar (Usuario nuevoUser){

        HashSet<Usuario> listaUsers = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");
        listaUsers.add(nuevoUser);
        GestionJSONUsuario.listaUsuarioToArchivo(listaUsers, "usuario.json");
    }

    @Override
    public void ingresarUsuario(){
        Usuario user = registroUser.registroUsuario();
        agregarYguardar(user);
        System.out.println("¡Usuario " + user.getNombre() + " " + user.getApellido() + " agregado con éxito!");
    }

    @Override
    public void mostrarDatosUsuario(Usuario u) {

        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("PERFIL DE USUARIO: " + u.getNombre() + " " + u.getApellido());
        System.out.println("--------------------------------------------");

        System.out.println("ID: " + u.getIdUsuario());
        System.out.println("Username: " + u.getUserName());
        System.out.println("Contraseña: **********");
        System.out.println("Nombre: " + u.getNombre());
        System.out.println("Apellido: " + u.getApellido());
        System.out.println("DNI: " + u.getDni());
        System.out.println("Teléfono: " + u.getTelefono());
        System.out.println("Dirección: " + u.getDireccion());
        System.out.println("Email: " + u.getEmail());
        System.out.println("--------------------------------------------");
    }


    @Override
    public void modificarUsuario(Usuario usuario) {

        listaUsuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");
        boolean salir = false;

        for (Usuario a : listaUsuarios) {
            if (a.getIdUsuario().equals(usuario.getIdUsuario())) {

                while (!salir) {
                    System.out.println("¿Que desea modificar?");
                    System.out.println("1. Username");
                    System.out.println("2. Contraseña");
                    System.out.println("3. Nombre");
                    System.out.println("4. Apellido");
                    System.out.println("5. Email");
                    System.out.println("6. Teléfono");
                    System.out.println("7. DNI");
                    System.out.println("8. Dirección");
                    System.out.println("9. Salir");

                    int opcion = teclado.nextInt();
                    teclado.nextLine();

                    switch (opcion) {

                        case 1:

                            String username = "";

                            while (true){

                                System.out.println("Username: ");

                                try {
                                    username = teclado.nextLine();
                                    Validaciones.validarNombreUsuario(username);

                                    if(!Validaciones.existeUser(username)){
                                        a.setUserName(username);
                                        break;

                                    }else {
                                        System.out.println("El Username ya existe en el sistema. Por favor, ingrese otro");
                                    }

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 2:

                            String contrasenia = "";

                            while (true){

                                System.out.println("Contraseña: ");

                                try {
                                    contrasenia = teclado.nextLine();
                                    Validaciones.validarContrasenia(contrasenia);
                                    a.setContrasena(contrasenia);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 3:

                            String nombre = "";

                            while(true){

                                System.out.println("Nombre: ");

                                try {
                                    nombre = teclado.nextLine();
                                    Validaciones.validarString(nombre);
                                    a.setNombre(nombre);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 4:

                            String apellido = "";

                            while(true){

                                System.out.println("Nombre: ");

                                try {
                                    apellido = teclado.nextLine();
                                    Validaciones.validarString(apellido);
                                    a.setApellido(apellido);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 5:

                            String email = "";

                            while (true){

                                System.out.println("Email: ");

                                try {
                                    email = teclado.nextLine();
                                    Validaciones.validarEmail(email);
                                    a.setEmail(email);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 6:

                            String telefono = "";

                            while (true){

                                System.out.println("Teléfono: ");

                                try {
                                    telefono = teclado.nextLine();
                                    Validaciones.validarTelefono(telefono);
                                    a.setTelefono(telefono);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 7:

                            String dni = "";

                            while (true){

                                System.out.println("DNI: ");

                                try {
                                    dni = teclado.nextLine();
                                    Validaciones.validarDNI(dni);
                                    a.setDni(dni);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 8:

                            String direccion = "";

                            while (true){

                                System.out.println("Dirección: ");

                                try {
                                    direccion = teclado.nextLine();
                                    Validaciones.validarDireccion(direccion);
                                    a.setDireccion(direccion);

                                    break;

                                }catch (DatoInvalidoException e){
                                    System.err.println("Error: " +e.getMessage()+ ". Por favor, inténtelo nuevamente");
                                }
                            }

                            break;

                        case 9:
                            System.out.println("Saliendo del apartado de modificación...");
                            salir = true;
                            break;

                        default:
                            System.out.println("Opción invalida. Por favor, inténtelo nuevamente");
                            break;
                    }
                }

                listaUsuarios.add(a);
                GestionJSONUsuario.listaUsuarioToArchivo(listaUsuarios, "usuario.json");
                System.out.println("¡Datos cambiados con éxito!");
                return;
            }
        }

        System.out.println("No se encontró al Usuario con ese ID.");
    }

    @Override
    public void darDeBajaUsuario(Usuario u){

        listaUsuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");

        for(Usuario user : listaUsuarios){
            if(user.equals(u)){
                System.out.println("¿Estás seguro de que quieres eliminar la cuenta? (si / no)");
                String opcion = teclado.nextLine();

                while (true){

                    if(opcion.equalsIgnoreCase("si")){

                        user.setActivo(true);
                        System.out.println("¡Cuenta dada de baja con éxito!");
                        GestionJSONUsuario.listaUsuarioToArchivo(listaUsuarios,"usuario.json");

                        return;

                    }else if (opcion.equalsIgnoreCase("no")) {
                        System.out.println("Operación cancelada");
                        return;

                    }else{
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    }
                }
            }
        }

        System.out.println("No se encontró al usuario");
    }


    @Override
    public void darDeAltaUsuario(Usuario u){

        listaUsuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");

        for(Usuario user : listaUsuarios){
            if(user.equals(u)){
                System.out.println("¿Estás seguro de que quieres dar de alta al usuario " +user.getNombre()+ " " +user.getApellido()+ "? (si / no)");
                String opcion = teclado.nextLine();

                while (true){

                    if(opcion.equalsIgnoreCase("si")){

                        user.setActivo(true);
                        System.out.println("¡Cuenta dada de alta con éxito!");
                        GestionJSONUsuario.listaUsuarioToArchivo(listaUsuarios,"usuario.json");

                        return;

                    }else if (opcion.equalsIgnoreCase("no")) {
                        System.out.println("Operación cancelada");
                        return;

                    }else{
                        System.out.println("Opción invalida. Por favor, ingrese una opción valida");
                    }
                }
            }
        }

        System.out.println("No se encontró al usuario");
    }

    @Override
    public Usuario encontrarUsuario(String dni){

        listaUsuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");

        for(Usuario user : listaUsuarios){
            if(user.getDni().equals(dni)){
                return user;
            }
        }

        return null;
    }
}
