package Gestion;

import Enums.TipoProveedor;
import Excepciones.ObjetoNoEncontradoException;
import Users.Proveedor;
import Validaciones.Validaciones;

import java.util.*;

public class GestionProveedor {

    private Set<Proveedor> listaProveedores;

    public GestionProveedor() {
        this.listaProveedores = new HashSet<>();
    }

    public void agregarProveedor(Proveedor p){
        listaProveedores.add(p);
    }

    public void modificarProveedor(Proveedor p, Scanner teclado){

        boolean salir = false;

        while (!salir) {

            System.out.println("¿Qué desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. E-mail");
            System.out.println("4. Teléfono");
            System.out.println("5. Tipo de proveedor");
            System.out.println("6. Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("Escriba el nuevo nombre");
                    String nombreNuevo = teclado.nextLine();
                    p.setNombre(nombreNuevo);

                    break;

                case 2:
                    System.out.println("Escriba el nuevo apellido");
                    String apellidoNuevo = teclado.nextLine();
                    p.setApellido(apellidoNuevo);

                    break;

                case 3:
                    System.out.println("Escriba el nuevo e-mail");
                    String emailNuevo = teclado.nextLine();
                    p.setEmail(emailNuevo);

                    break;

                case 4:
                    System.out.println("Escriba el nuevo teléfono");
                    String telefonoNuevo = teclado.nextLine();
                    p.setTelefono(telefonoNuevo);

                    break;

                case 5:
                    System.out.println("Elija el nuevo tipo de proveedor");
                    TipoProveedor tipoProveedor = elegirTipoProveedor(teclado);
                    p.setTipoProveedor(tipoProveedor);

                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    salir = true;

                    break;

                default:
                    System.out.println("Opción ivalida. Intentelo nuevamente");

                    break;
            }
        }
    }

    public void darBajaProveedor(Proveedor p){
        p.setActivo(false);
    }

    public void darAltaProveedor(Proveedor p){
        p.setActivo(true);
    }

    public void mostrarProveedoresDisponibles(){

        for (Proveedor p : listaProveedores){
            System.out.println(p);
        }
    }

    public void mostrarProveedoresPorCoincidencia(String nombre){

        boolean encontrado = false;

        for(Proveedor p : listaProveedores){
            if(p.getNombre().contains(nombre)){
                System.out.println("Id: " + p.getIdProveedor() + ", Nombre: " + p.getNombreCompleto());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron proveedores que coincidan con ese nombre");
        }
    }

    public Proveedor buscarProveedoresPorId(String id) throws ObjetoNoEncontradoException {

        boolean encontrado = false;
        Proveedor proveedor = null;

        for(Proveedor p : listaProveedores){
            if(p.getIdProveedor().equals(id)){
                proveedor = p;
                encontrado = true;
            }
        }

        if (!encontrado) {
            throw new ObjetoNoEncontradoException("No se encontraron proveedores con esa ID");
        }

        return proveedor;
    }

    public Proveedor elegirProveedor(Scanner teclado){

        List<Proveedor> proveedores = new ArrayList<>(listaProveedores);

        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor aux = proveedores.get(i);
            if (aux.isActivo()) {
                System.out.println((i + 1) + ". Id: " + aux.getIdProveedor() +
                        ", Nombre: " + aux.getNombreCompleto());
            }
        }

        int opcion = Validaciones.esValido(teclado, proveedores);

        return proveedores.get(opcion-1);
    }

    public Proveedor elegirProveedorDeBaja(Scanner teclado){

        List<Proveedor> proveedores = new ArrayList<>(listaProveedores);

        for (int i = 0; i < proveedores.size(); i++) {

            Proveedor aux = proveedores.get(i);

            if (!aux.isActivo()) {
                System.out.println((i + 1) + ". Id: " + aux.getIdProveedor() +
                        ", Nombre: " + aux.getNombreCompleto());
            }
        }

        int opcion = Validaciones.esValido(teclado, proveedores);

        return proveedores.get(opcion-1);
    }

    public Proveedor cargarProveedor(Scanner teclado){

        System.out.println("Ingresa el nombre del proveedor");
        String nombre = teclado.nextLine();

        System.out.println("Ingrese el apellido");
        String apellido = teclado.nextLine();

        System.out.println("Ingrese un e-mail");
        String email = teclado.nextLine();

        System.out.println("Ingrese un teléfono");
        String telefono = teclado.nextLine();

        System.out.println("Ingrese el CUIT");
        String cuit = teclado.nextLine();

        System.out.println("Elija el tipo de proveedor");
        TipoProveedor tipoProveedor = elegirTipoProveedor(teclado);

        Proveedor proveedor = new Proveedor(nombre, apellido, email, telefono, cuit, tipoProveedor);
        agregarProveedor(proveedor);

        return proveedor;
    }


    public TipoProveedor elegirTipoProveedor(Scanner teclado){

        TipoProveedor tipoProveedor = null;

        boolean salir = true;

        while (salir) {

            System.out.println("1. Mayorista");
            System.out.println("2. Minorista");
            System.out.println("3. Fabricante");
            System.out.println("4. Importador");
            System.out.println("5. Ensamblador");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    tipoProveedor = TipoProveedor.MAYORISTA;
                    salir = false;
                    break;

                case 2:
                    tipoProveedor = TipoProveedor.MINORISTA;
                    salir = false;
                    break;

                case 3:
                    tipoProveedor = TipoProveedor.FABRICANTE;
                    salir = false;
                    break;

                case 4:
                    tipoProveedor = TipoProveedor.IMPORTADOR;
                    salir = false;
                    break;

                case 5:
                    tipoProveedor = TipoProveedor.ENSAMBLADOR;
                    salir = false;

                default:
                    System.out.println("Opción invalida.Intentelo nuevamente");
                    break;
            }
        }

        return tipoProveedor;
    }
}
