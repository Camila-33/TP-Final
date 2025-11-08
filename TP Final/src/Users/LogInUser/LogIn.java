package Users.LogInUser;

import Archivos.GestionJSONAdministrador;
import Archivos.GestionJSONEmpleado;
import Users.UsuarioSistema.Administrador;
import Users.UsuarioSistema.Empleado;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LogIn {

    private Scanner teclado;

    public LogIn() {
        this.teclado = new Scanner(System.in);
    }

    public Administrador inicioSesionAdmin(String nombreArchivo) throws FileNotFoundException {

        if (nombreArchivo == null) {
            throw new FileNotFoundException("El archivo no existe");
        }

        HashSet<Administrador> administradores = GestionJSONAdministrador.archivoAdminToLista(nombreArchivo); //hay que volver a serializarlo?
        Administrador adminLeido = null;

        while (adminLeido == null) {

            System.out.println("Ingrese el nombre de usuario");
            String username = teclado.next();

            System.out.println("Ingrese la contraseña");
            String contrasenia = teclado.next();

            boolean encontrado = false;

            for (Administrador admin : administradores) {
                if (admin.getUserName().equals(username) &&
                        admin.getContrasena().equals(contrasenia) &&
                        admin.isActivo()) {

                    adminLeido = admin;
                    encontrado = true;
                    return adminLeido;

                } else if (admin.getUserName().equals(username) &&
                        admin.getContrasena().equals(contrasenia) &&
                        !admin.isActivo()) {

                    System.out.println("Su cuenta se encuentra dada de baja");
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Username o contraseña incorrectos");
            }


            System.out.println("¿Desea salir o intentar nuevamente? Seleccione una opción.");
            System.out.println("1. Salir.");
            System.out.println("2. Reintentar.");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {
                System.out.println("Saliendo del inicio de sesion...");
                break;
            } else if (opcion != 2) {
                System.out.println("Opción invalida. Se lo enviara al inicio de sesión.");
            }
        }

        return adminLeido;
    }


    public Empleado inicioSesionEmpleado(String nombreArchivo) throws FileNotFoundException {

        if (nombreArchivo == null) {
            throw new FileNotFoundException("El archivo no existe");
        }

        ArrayList<Empleado> empleados = GestionJSONEmpleado.archivoEmpleadoToLista(nombreArchivo);
        Empleado empleadoLeido = null;

        while (empleadoLeido == null) {

            System.out.println("Ingrese el nombre de usuario");
            String username = teclado.next();

            System.out.println("Ingrese la contraseña");
            String contrasenia = teclado.next();

            for (int i = 0; i < empleados.size(); i++) {
                if (empleados.get(i).getUserName().equals(username) && empleados.get(i).getContrasena().equals(contrasenia) && empleados.get(i).isActivo()) {
                    empleadoLeido = new Empleado();
                    return empleadoLeido;

                } else if (empleados.get(i).getUserName().equals(username) && empleados.get(i).getContrasena().equals(contrasenia) && !empleados.get(i).isActivo()) {
                    System.out.println("Su cuenta se encuentra dada de baja");

                } else if (empleados.get(i).getUserName().equals(username) || empleados.get(i).getContrasena().equals(contrasenia)) {
                    System.out.println("Username o contraseña incorrectos");
                }
            }

            System.out.println("¿Desea salir o intentar nuevamente? Seleccione una opción.");
            System.out.println("1. Salir.");
            System.out.println("2. Reintentar.");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {
                System.out.println("Saliendo del inicio de sesion...");
                break;
            } else if (opcion != 2) {
                System.out.println("Opción invalida. Se lo enviara al inicio de sesión.");
            }
        }

        return empleadoLeido;
    }
}
