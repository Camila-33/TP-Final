package Gestion;

import Archivos.GestionJSONUsers.GestionJSONAdministrador;
import Excepciones.DatoInvalidoException;
import Interfaces.MetodosGestion;
import Users.RegistroUser.RegistroUser;
import Users.UsuarioSistema.Administrador;
import Validaciones.Validaciones;

import java.util.HashSet;
import java.util.Scanner;

public class GestionAdministrador implements MetodosGestion <Administrador>{

    private HashSet<Administrador> listaAdministradores;
    private RegistroUser registroUser;
    private Scanner teclado;

    public GestionAdministrador() {
        this.listaAdministradores = new HashSet<>();
        this.registroUser = new RegistroUser();
        this.teclado = new Scanner(System.in);
    }

    @Override
    public void agregarYguardar(Administrador nuevoAdmin){

        HashSet<Administrador> listaAdmins = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        listaAdmins.add(nuevoAdmin);
        GestionJSONAdministrador.listaAdminsToArchivo(listaAdmins, "administrador.json");
    }

    @Override
    public void ingresarUsuario(){
        Administrador admin = registroUser.registroAdministrador();
        agregarYguardar(admin);
        System.out.println("¡Administrador/a " + admin.getNombre() + " " + admin.getApellido() + " agregado con éxito!");

    }

    @Override
    public void mostrarDatosUsuario(Administrador a){

        HashSet<Administrador> listaAdmins = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        boolean encontrado = false;

        for (Administrador admin : listaAdmins){
            if (admin.getIdUsuario().equals(a.getIdUsuario())){
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE ADMINISTRADOR: " + a.getNombre() + " " + a.getApellido());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + a.getIdUsuario());
                System.out.println("Username: " + a.getUserName());
                System.out.println("Contraseña: **********");
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Apellido: " + a.getApellido());
                System.out.println("DNI: " + a.getDni());
                System.out.println("Teléfono: " + a.getTelefono());
                System.out.println("Dirección: " + a.getDireccion());
                System.out.println("Email: " + a.getEmail());
                System.out.println("--------------------------------------------");

                encontrado = true;
                break;
            }
        }

        if(!encontrado){
            System.out.print("No se encontró al usuario");
        }
    }

    @Override
    public void modificarUsuario(Administrador administrador) {

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        boolean salir = false;

        for (Administrador a : listaAdministradores) {
            if (a.getIdUsuario().equals(administrador.getIdUsuario())) {

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

                                System.out.println("Apellido: ");

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

                listaAdministradores.add(a);
                GestionJSONAdministrador.listaAdminsToArchivo(listaAdministradores, "administrador.json");
                System.out.println("¡Datos cambiados con éxito!");
                return;
            }
        }

        System.out.println("No se encontró ningún administrador con ese ID.");
    }


    @Override
    public void darDeBajaUsuario(Administrador a){

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for(Administrador admin : listaAdministradores){
            if(admin.equals(a)){
                System.out.println("¿Estás seguro de que quieres eliminar la cuenta? (si / no)");
                String opcion = teclado.nextLine();

                if(opcion.equalsIgnoreCase("si")){

                    while (true){
                        System.out.println("Ingrese su contraseña para eliminar su cuenta"); //debería haber intentos limitados?
                        String contrasenia = teclado.nextLine();

                        if (contrasenia.equals(admin.getContrasena())){
                            
                            admin.setActivo(false);
                            System.out.println("¡Cuenta eliminada con éxito!");
                            GestionJSONAdministrador.listaAdminsToArchivo(listaAdministradores,"administrador.json");
                            
                            return;

                        }else{
                            System.out.println("Contraseña incorrecta, inténtelo nuevamente");
                        }

                    }

                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operación cancelada");
                    return;

                }else{
                    System.out.println("Opción invalida");
                }
            }
        }

        System.out.println("No se encontró al usuario");
    }


    @Override
    public void darDeAltaUsuario(Administrador a){

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for(Administrador admin : listaAdministradores){
            if(admin.equals(a)){
                System.out.println("¿Estás seguro de que quieres dar de alta al administrador" +admin.getNombre()+ " " +admin.getApellido()+ "? (si / no)");
                String opcion = teclado.nextLine();

                if(opcion.equalsIgnoreCase("si")){

                    while (true){
                        System.out.println("Ingrese su contraseña para dar de alta la cuenta");
                        String contrasenia = teclado.nextLine();

                        if (contrasenia.equals(admin.getContrasena())){

                            admin.setActivo(true);
                            System.out.println("¡Cuenta dada de alta con éxito!");
                            GestionJSONAdministrador.listaAdminsToArchivo(listaAdministradores,"administrador.json");

                            return;

                        }else{
                            System.out.println("Contraseña incorrecta, inténtelo nuevamente");
                        }
                    }

                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operación cancelada");
                    return;

                }else{
                    System.out.println("Opción invalida");
                }
            }
        }

        System.out.println("No se encontró al usuario");
    }

    @Override
    public Administrador encontrarUsuario(String dni){

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for(Administrador admin : listaAdministradores){
            if(admin.getDni().equals(dni)){
                return admin;
            }
        }

        return null;
    }
}
