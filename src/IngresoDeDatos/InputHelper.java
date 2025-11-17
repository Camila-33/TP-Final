package IngresoDeDatos;

import Enums.TipoCertificacion;
import Excepciones.DatoInvalidoException;
import Validaciones.Validaciones;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {

    public static Scanner teclado = new Scanner(System.in);
    private static final SecureRandom random = new SecureRandom();

    public InputHelper() {
    }

    public static String leerIDOCodigo(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String input = teclado.nextLine().trim();
            try {
                Validaciones.validarIDYCodigo(input);
                return input;
            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }


    public static String pedirString(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String valor = teclado.nextLine();
                Validaciones.validarString(valor);
                return valor;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirEmail(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String email = teclado.nextLine();
                Validaciones.validarEmail(email);
                return email;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirTelefono(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String tel = teclado.nextLine();
                Validaciones.validarTelefono(tel);
                return tel;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirDireccion(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String dir = teclado.nextLine();
                Validaciones.validarDireccion(dir);
                return dir;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirUsername(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String username = teclado.nextLine();

                Validaciones.validarNombreUsuario(username);

                if (Validaciones.existeUser(username)) {
                    System.out.println("El Username ya existe. Ingrese otro.");
                } else {
                    return username;
                }

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirContraseniaRegistro(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String contrasenia = teclado.nextLine();
                Validaciones.validarContrasenia(contrasenia);
                return contrasenia;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirContrasenia(String mensaje) {
        System.out.println(mensaje);
        String contrasenia = teclado.nextLine();
        return contrasenia;
    }

    public static String pedirDniRegistro(String tipo, String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String dni = teclado.next();

                Validaciones.validarDNI(dni);

                if (!Validaciones.existeDni(dni, tipo)) {
                    return dni;
                } else {
                    System.out.println("El DNI ya existe en el sistema.");
                }

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirDni(String tipo, String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String dni = teclado.next();

                Validaciones.validarDNI(dni);
                return dni;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String pedirCuit(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                String cuit = teclado.nextLine();
                Validaciones.validarCuit(cuit);
                return cuit;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String leerNombreProducto(String mensaje) {
        while(true) {
            System.out.println(mensaje);
            String input = teclado.nextLine();
            try {
                Validaciones.validarNombreProducto(input);
                return input;
            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String leerDescripcion(String mensaje) {
        while(true) {
            System.out.println(mensaje);
            String input = teclado.nextLine();
            try {
                Validaciones.validarDescripcionProducto(input);
                return input;
            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static double leerDouble(String mensaje) {
        while(true) {
            System.out.println(mensaje);
            try {
                double valor = teclado.nextDouble();
                teclado.nextLine();
                Validaciones.validarNumero(valor);
                return valor;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static int leerInt(String mensaje) {
        while(true) {
            System.out.println(mensaje);
            try {
                int valor = teclado.nextInt();
                teclado.nextLine();
                Validaciones.validarNumero(valor);
                return valor;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.err.println("Debe ingresar un número válido. Intente nuevamente.");
                teclado.nextLine();
            }
        }
    }


    public static boolean leerBoolean(String mensaje) {
        while(true) {
            System.out.println(mensaje);
            char c = teclado.next().toLowerCase().charAt(0);
            teclado.nextLine();
            try {
                Validaciones.validarBoton(c);
                return c == 's';
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String leerString(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextLine();
    }

    public static TipoCertificacion leerCertificacion() {
        while(true) {
            System.out.println("Elija el tipo de certificación:");
            System.out.println("1. Bronze");
            System.out.println("2. Silver");
            System.out.println("3. Gold");
            System.out.println("4. Platinum");
            System.out.println("5. Titanium");

            int op = teclado.nextInt();
            teclado.nextLine();
            switch(op) {
                case 1: return TipoCertificacion.BRONZE;
                case 2: return TipoCertificacion.SILVER;
                case 3: return TipoCertificacion.GOLD;
                case 4: return TipoCertificacion.PLATINUM;
                case 5: return TipoCertificacion.TITANIUM;
                default: System.err.println("Opción inválida."); break;
            }
        }
    }

    public static char leerChar(String mensaje) {
        while(true) {
            System.out.println(mensaje);
            char c = teclado.next().toLowerCase().charAt(0);
            teclado.nextLine();

            try {
                Validaciones.validarBoton(c);
                return c;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static String leerDimension(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String input = teclado.nextLine();
            try {
                Validaciones.validarDimension(input);
                return input;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage() + ". Por favor, inténtelo nuevamente.");
            }
        }
    }

    public static String leerMarca(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String input = teclado.nextLine();
            try {
                Validaciones.validarMarca(input);
                return input;

            } catch (DatoInvalidoException e) {
                System.err.println("Error: " + e.getMessage() + ". Por favor, inténtelo nuevamente.");
            }
        }
    }

    public static int leerEnteroSwitch() {
        int numero;

        while (true) {
            try {
                numero = teclado.nextInt();
                teclado.nextLine();
                return numero;

            } catch (InputMismatchException e) {
                System.err.println("Debe ingresar un número válido. Intente nuevamente.");
                teclado.nextLine();
            }
        }
    }

    public static String generarCodigoUnico() {
        int numero = 100000000 + random.nextInt(900000000);
        return String.valueOf(numero);
    }
}
