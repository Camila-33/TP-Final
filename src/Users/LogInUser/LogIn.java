package Users.LogInUser;

import Archivos.GestionJSONUsers.GestionJSONAdministrador;
import Archivos.GestionJSONUsers.GestionJSONUsuario;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Usuario;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class LogIn {

    private Scanner teclado;

    public LogIn() {
        this.teclado = new Scanner(System.in);
    }

    public Administrador inicioSesionAdministrador(String nombreArchivo) throws FileNotFoundException {

        if (nombreArchivo == null) {
            throw new FileNotFoundException("El archivo no existe");
        }

        HashSet<Administrador> administradores = GestionJSONAdministrador.archivoAdminToLista(nombreArchivo);
        Administrador adminLeido = null;
        boolean salir = false;

        while (adminLeido == null && !salir) {

            System.out.println("Ingrese el nombre de usuario:");
            String username = teclado.next();

            System.out.println("Ingrese la contraseña:");
            String contrasenia = teclado.next();

            boolean encontrado = false;

            for (Administrador a : administradores) {
                if (a.getUserName().equals(username) && a.getContrasena().equals(contrasenia)) {
                    encontrado = true;

                    if (a.isActivo()) {
                        adminLeido = a;

                    } else {
                        System.out.println("Su cuenta se encuentra dada de baja.");
                    }
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Username o contraseña incorrectos.");
            }

            if (adminLeido == null) {
                System.out.println("¿Desea salir o intentar nuevamente? Seleccione una opción:");
                System.out.println("1. Salir");
                System.out.println("2. Reintentar");

                int opcion = teclado.nextInt();
                teclado.nextLine();

                if (opcion == 1) {
                    System.out.println("Saliendo del inicio de sesión...");
                    salir = true;
                } else if (opcion != 2) {
                    System.out.println("Opción inválida. Se lo enviará al inicio de sesión nuevamente.");
                }
            }
        }

        return adminLeido;
    }


    public Usuario inicioSesionUsuario(String nombreArchivo) throws FileNotFoundException {

        if (nombreArchivo == null) {
            throw new FileNotFoundException("El archivo no existe");
        }

        HashSet<Usuario> usuarios = GestionJSONUsuario.archivoUsuarioToLista(nombreArchivo);
        Usuario usuarioLeido = null;
        boolean salir = false;

        while (usuarioLeido == null && !salir) {

            System.out.println("Ingrese el nombre de usuario:");
            String username = teclado.next();

            System.out.println("Ingrese la contraseña:");
            String contrasenia = teclado.next();

            boolean encontrado = false;

            for (Usuario u : usuarios) {
                if (u.getUserName().equals(username) && u.getContrasena().equals(contrasenia)) {
                    encontrado = true;
                    if (u.isActivo()) {
                        usuarioLeido = u;

                    } else {
                        System.out.println("Su cuenta se encuentra dada de baja.");
                    }
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Username o contraseña incorrectos.");
            }

            if (usuarioLeido == null) {
                System.out.println("¿Desea salir o intentar nuevamente? Seleccione una opción:");
                System.out.println("1. Salir");
                System.out.println("2. Reintentar");

                int opcion = teclado.nextInt();
                teclado.nextLine();

                if (opcion == 1) {
                    System.out.println("Saliendo del inicio de sesión...");
                    salir = true;
                } else if (opcion != 2) {
                    System.out.println("Opción inválida. Se lo enviará al inicio de sesión nuevamente.");
                }
            }
        }

        return usuarioLeido;
    }

}
