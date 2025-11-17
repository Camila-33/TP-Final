package Gestion;

import Archivos.GestionJSONUsers.GestionJSONAdministrador;
import IngresoDeDatos.InputHelper;
import Interfaces.MetodosGestion;
import Users.RegistroUser.RegistroUser;
import Users.UsuarioSistema.Administrador;

import java.util.HashSet;

import static IngresoDeDatos.InputHelper.teclado;

public class GestionAdministrador implements MetodosGestion <Administrador>{

    private HashSet<Administrador> listaAdministradores;
    private RegistroUser registroUser;

    public GestionAdministrador() {
        this.listaAdministradores = new HashSet<>();
        this.registroUser = new RegistroUser();
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

                    int opcion = InputHelper.leerEnteroSwitch();
                    teclado.nextLine();

                    switch (opcion) {

                        case 1:
                            a.setUserName(InputHelper.pedirUsername("Nuevo username:"));
                            break;

                        case 2:
                            a.setContrasena(InputHelper.pedirContraseniaRegistro("Nueva contraseña: "));
                            break;

                        case 3:
                            a.setNombre(InputHelper.pedirString("Nuevo nombre:"));
                            break;

                        case 4:
                            a.setApellido(InputHelper.pedirString("Nuevo apellido:"));
                            break;

                        case 5:
                            a.setEmail(InputHelper.pedirEmail("administrador", "Nuevo email:"));
                            break;

                        case 6:
                            a.setTelefono(InputHelper.pedirTelefono("Nuevo teléfono:"));
                            break;

                        case 7:
                            a.setDni(InputHelper.pedirDniRegistro("administrador", "Nuevo DNI:"));
                            break;

                        case 8:
                            a.setDireccion(InputHelper.pedirDireccion("Nueva dirección:"));
                            break;

                        case 9:
                            System.out.println("Saliendo del apartado de modificación...");
                            salir = true;
                            break;

                        default:
                            System.out.println("Opción invalida. Por favor, inténtelo nuevamente.");
                            break;
                    }
                }

                GestionJSONAdministrador.listaAdminsToArchivo(listaAdministradores, "administrador.json");
                System.out.println("¡Datos cambiados con éxito!");
                return;
            }
        }

        System.out.println("No se encontró ningún administrador con ese ID.");
    }


    @Override
    public void darDeBajaUsuario(Administrador a) {

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for (Administrador admin : listaAdministradores) {
            if (admin.equals(a)) {

                if (!admin.isActivo()) {
                    System.err.println("Error: el administrador ya está dado de baja.");
                    return;
                }

                char opcion = InputHelper.leerChar("¿Estás seguro de que quieres dar de baja la cuenta? (s / n)");

                if (opcion == 's') {

                    int intentos = 0;

                    while (intentos < 3) {
                        String contrasenia = InputHelper.pedirContrasenia("Ingrese su contraseña para eliminar su cuenta");

                        if (contrasenia.equals(admin.getContrasena())) {
                            admin.setActivo(false);
                            System.out.println("¡Cuenta eliminada con éxito!");
                            GestionJSONAdministrador.listaAdminsToArchivo(listaAdministradores, "administrador.json");
                            return;

                        } else {
                            intentos++;

                            if (intentos < 3) {
                                System.out.println(
                                        "Contraseña incorrecta, inténtelo nuevamente (" + (3 - intentos) + " intentos restantes)"
                                );
                            } else {
                                System.out.println("Ha superado el número máximo de intentos. Operación cancelada.");
                            }
                        }
                    }

                } else {
                    System.out.println("Operación cancelada");
                    return;
                }
            }
        }

        System.out.println("No se encontró al usuario");
    }


    @Override
    public void darDeAltaUsuario(Administrador a) {

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for (Administrador admin : listaAdministradores) {
            if (admin.equals(a)) {

                if (admin.isActivo()) {
                    System.err.println("Error: el administrador ya está dado de alta.");
                    return;
                }

                char opcion = InputHelper.leerChar("¿Estás seguro de que quieres dar de alta al administrador " + admin.getNombreCompleto() + "? (s / n)");

                if (opcion == 's') {
                    admin.setActivo(true);
                    System.out.println("¡Cuenta dada de alta con éxito!");
                    GestionJSONAdministrador.listaAdminsToArchivo(listaAdministradores, "administrador.json");

                } else {
                    System.out.println("Operación cancelada");
                }
                return;
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

    @Override
    public void mostrarTodosLosUsuarios(){

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for(Administrador admin : listaAdministradores) {
            mostrarDatosUsuario(admin);
        }
    }

    @Override
    public void mostrarDatosUsuario(Administrador a){

        listaAdministradores = GestionJSONAdministrador.archivoAdminToLista("administrador.json");

        for (Administrador administrador : listaAdministradores) {
            if (administrador.getIdUsuario().equals(a.getIdUsuario())) {

                a = administrador;

                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE ADMINISTRADOR: " + a.getNombreCompleto());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + a.getIdUsuario());
                System.out.println("Username: " + a.getUserName());
                System.out.println("Contraseña: **********");
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Apellido: " + a.getApellido());
                System.out.println("DNI: " + a.getDni());
                System.out.println("Teléfono: " + a.getTelefono());
                System.out.println("Activo: " + (a.isActivo() ? "Sí" : "No"));
                System.out.println("Dirección: " + a.getDireccion());
                System.out.println("Email: " + a.getEmail());
                System.out.println("--------------------------------------------");
            }
        }
    }
}
