<<<<<<< HEAD
package GestionStock.Enum;

import GestionStock.Utils.Utils;
import IngresoDeDatos.InputHelper;

public enum MenuStock {
    MOSTRAR_STOCKS_PRODUCTOS,
    CONSULTAR_STOCK_PRODUCTO,
    MOSTRAR_MOVIMIENTO_INVENTARIO;

    private static final String contenido = crearContenido();

    private static String crearContenido()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.dibujarEncabezado("MENU",80));
        String barra= Utils.dibujarBarra(80);

        int nro = 1;
        for (MenuStock i : MenuStock.values())
            sb.append( String.format("\n[ %s ] . %s\n%s", (nro++), i.toString().replace("_"," "), barra ));

        sb.append(String.format("\nSeleccione una opcion (1-%d): ", nro++) );
        return sb.toString();
    }

    public static int getOpcion( )
    {
        return InputHelper.leerInt(contenido);
    }

}
=======
package GestionStock.Enum;

import GestionStock.Utils.Utils;
import IngresoDeDatos.InputHelper;

public enum MenuStock {
    MOSTRAR_STOCKS_PRODUCTOS,
    CONSULTAR_STOCK_PRODUCTO,
    MOSTRAR_MOVIMIENTO_INVENTARIO;

    private static final String contenido = crearContenido();

    private static String crearContenido()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.dibujarEncabezado("MENU",80));
        String barra= Utils.dibujarBarra(80);

        int nro = 1;
        for (MenuStock i : MenuStock.values())
            sb.append( String.format("\n[ %s ] . %s\n%s", (nro++), i.toString().replace("_"," "), barra ));

        sb.append(String.format("\nSeleccione una opcion (1-%d): ", nro++) );
        return sb.toString();
    }

    public static int getOpcion( )
    {
        return InputHelper.leerInt(contenido);
    }

}
>>>>>>> 9100b86200ee1217be3a33b507289e6d59c984bd
