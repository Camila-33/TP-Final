package Gestion;

import Archivos.GestionJSONUsers.GestionJSONUsuario;
import IngresoDeDatos.InputHelper;
import Interfaces.MetodosGestion;
import Users.RegistroUser.RegistroUser;
import Users.UsuarioSistema.Usuario;

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

        listaUsuarios = GestionJSONUsuario.archivoUsuarioToLista("usuario.json");
        listaUsuarios.add(nuevoUser);
        GestionJSONUsuario.listaUsuarioToArchivo(listaUsuarios, "usuario.json");
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

        for (Usuario u : listaUsuarios) {

            if (u.getIdUsuario().equals(usuario.getIdUsuario())) {

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

                    int opcion = -1;

                    try {
                        opcion = Integer.parseInt(teclado.nextLine());
                    } catch (NumberFormatException e) {
                        System.err.println("Debe ingresar un número.");
                        continue;
                    }

                    switch (opcion) {

                        case 1:
                            u.setUserName(InputHelper.pedirUsername("Nuevo username:"));
                            break;

                        case 2:
                            u.setContrasena(InputHelper.pedirContrasenia("Nueva contraseña: "));
                            break;

                        case 3:
                            u.setNombre(InputHelper.pedirString("Nuevo nombre:"));
                            break;

                        case 4:
                            u.setApellido(InputHelper.pedirString("Nuevo apellido:"));
                            break;

                        case 5:
                            u.setEmail(InputHelper.pedirEmail("Nuevo email:"));
                            break;

                        case 6:
                            u.setTelefono(InputHelper.pedirTelefono("Nuevo teléfono:"));
                            break;

                        case 7:
                            u.setDni(InputHelper.pedirDni("usuario", "Nuevo DNI:"));
                            break;

                        case 8:
                            u.setDireccion(InputHelper.pedirDireccion("Nueva dirección:"));
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

                GestionJSONUsuario.listaUsuarioToArchivo(listaUsuarios, "usuario.json");
                System.out.println("¡Datos cambiados con éxito!");
                return;
            }
        }

        System.out.println("No se encontró ningún administrador con ese ID.");
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
