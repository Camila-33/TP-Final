package Gestion;

import Archivos.GestionJSONAdministrador;
import Excepciones.DatoInvalidoException;
import Users.RegistroUser.RegistroUser;
import Users.UsuarioSistema.Administrador;
import Validaciones.Validaciones;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GestionAdministrador {

    private HashSet<Administrador> listaAdministradores;
    private RegistroUser registroUser;
    private Scanner teclado;

    public GestionAdministrador() {
        this.listaAdministradores = new HashSet<>();
        this.registroUser = new RegistroUser();
        this.teclado = new Scanner(System.in);
    }

    public void agregarYguardar (Administrador nuevoAdmin){

        HashSet<Administrador> listaAdmins = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        listaAdmins.add(nuevoAdmin);
        GestionJSONAdministrador.listaAdminsToArchivo(listaAdmins, "administrador.json");
    }

    public void ingresarUsuario(){
        Administrador admin = registroUser.registroAdministrador();
        agregarYguardar(admin);
        System.out.println("Administrador/a " + admin.getNombre() + " " + admin.getApellido() + " agregado con éxito!");

    }

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


    public Administrador modificarUsuario(Administrador administrador) {

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");
        boolean salir = false;

        for (Administrador a : listaAdministradores) {
            if (a.getIdUsuario().equals(administrador.getIdUsuario())) {
                listaAdministradores.remove(administrador);
                a = administrador;

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

                            break;

                        case 2:

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

                            break;

                        case 3:

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

                            break;

                        case 4:

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

                            break;

                        case 5:

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

                            break;

                        case 6:

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

                            break;

                        case 7:

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

                            break;

                        case 8:

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
                return a;
            }
        }

        return null;
    }


    public void darDeBajaUsuario(Administrador a){

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for(Administrador admin : listaAdministradores){
            if(admin.equals(a)){
                System.out.println("¿Estás seguro de que quieres eliminar la cuenta? (si / no)");
                String opcion = teclado.nextLine();

                if(opcion.equalsIgnoreCase("si")){

                    while (true){
                        System.out.println("Ingrese su contraseña para eliminar su cuenta");
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






}
