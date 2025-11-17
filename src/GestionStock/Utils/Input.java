package GestionStock.Utils;

import java.util.Scanner;

public class Input {

    public enum MensajeEntradaInvalida {

        MEI_INT("\n¡Solo se permiten caracteres numericos!"),
        MEI_FLOAT_DOUBLE("\n¡Solo permiten caracteres reales!");

        private final String MENSAJE;

        MensajeEntradaInvalida(String MENSAJE) {
            this.MENSAJE = MENSAJE;
        }

        public String getMENSAJE() {
            return "[¡ENTRADA INVALIDA!] ["+MENSAJE+"]";
        }
    }



    public static Scanner scanner = new Scanner(System.in);

    public static String getString(String texto)
    {
        System.out.print(texto);
        return scanner.nextLine();
    }


    public static int getInt(String texto)
    {
        System.out.print(texto);

        int value = 0;

        //mientra la entrada no sea un numero entero:
        while ( !scanner.hasNextInt())
        {
            System.out.println(MensajeEntradaInvalida.MEI_INT.getMENSAJE());
            System.out.print(texto);
            scanner.next();
        }

        int valor = scanner.nextInt();
        scanner.nextLine();

        return valor;
    }

    public static float getFloat(String texto)
    {
        System.out.print(texto);

        float valor;

        while ( !scanner.hasNextFloat())
        {
            System.out.println(MensajeEntradaInvalida.MEI_FLOAT_DOUBLE.getMENSAJE());
            System.out.print(texto);
            scanner.next();
        }

        valor = scanner.nextFloat();
        scanner.nextLine();
        return valor;
    }


    public static double getDouble(String texto)
    {
        System.out.print(texto);

        double valor;

        while (  !scanner.hasNextDouble() )
        {
            System.out.println(MensajeEntradaInvalida.MEI_FLOAT_DOUBLE.getMENSAJE());
            System.out.print(texto);
            scanner.next();
        }

        valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}
