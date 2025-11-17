package GestionStock.Utils;

import GestionStock.GeneradorID.GeneradorID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static GeneradorID generadorID = new GeneradorID();



    public static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


    public static String localDateTimeToString(LocalDateTime localDateTime)
    {
        if (localDateTime == null) return "N/A";
        return localDateTime.format(FORMATO_FECHA_HORA);
    }

    public static LocalDateTime stringToLocalDateTime(String string)
    {
        if (string.isEmpty() || string == null || string.equals("N/A") ) return null;
        return LocalDateTime.parse(string, FORMATO_FECHA_HORA);
    }

    public static String localDateToString(LocalDate localDate)
    {
        if (localDate == null) return "N/A";
        return localDate.format(FORMATO_FECHA);
    }

    public static LocalDate stringToLocalDate(String string)
    {
        if (string.isEmpty() || string == null || string.equals("N/A") ) return null;
        return LocalDate.parse(string, FORMATO_FECHA);
    }


    public static String dibujarLinea(int longitud)
    {
        return String.format("%s","─".repeat(longitud));
    }
    public static String dibujarBarra(int longitud)
    {
        return dibujarBarra(longitud, "═");
    }

    public static String dibujarBarra(int longitud, String simbolo)
    {
        return String.format("%s",simbolo.repeat(longitud));
    }

    public static String dibujarEncabezado(String titulo, int longitud)
    {
        String barra = dibujarBarra(longitud);
        return String.format("\n%s\n%s\n%S", barra, titulo, barra);
    }

    public static String dibujarEncabezadoTabla(String campos)
    {
        String barra = dibujarBarra( campos.length() );
        return String.format("\n%s\n%s\n%s", barra, campos, barra);
    }

    public static String dibujarEncabezadoTablaStock( )
    {
        String campos = String.format(
                "%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
                "FECHA","ID_STOCK","ID_PRODUCTO","CANTD","MIN","MAX","ACTIVO","FECHA_BAJA","FECHA_ULTIMA_ACTUALIZACION"
        );
        return dibujarEncabezadoTabla(campos);
    }

    public static String dibujarEncabezadoTablaLote()
    {
        String campos = String.format("%-15s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
                "FECHA", "ID_LOTE", "ID_PRODUCTO", "ID_PROVEEDOR", "CANTIDAD_INICIAL", "CANTIDAD_DISPONIBLE",
                "PRECIO_UNITARIO", "ACTIVO", "FECHA_BAJA", "FECHA_ACTUALIZACION");

        return dibujarEncabezadoTabla(campos);
    }


    public static String dibujarEncabezadoTablaMovimiento()
    {
        String campos = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
                "FECHA","ID_MOVIMIENTO", "ID_USUARIO", "TIPO_MOVIMIENTO", "ID_PRODUCTO", "ID_LOTE", "CANTIDAD");

        return dibujarEncabezadoTabla(campos);
    }


}
