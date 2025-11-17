package GestionStock.Archivos;

import GestionStock.Enum.TipoArchivoJSON;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public enum ArchivoSistema {
    
    DATOS_LOTE_JSON("datos_lote.json", TipoArchivoJSON.JSON_ARRAY),
    DATOS_STOCK_JSON("datos_stock_.json",  TipoArchivoJSON.JSON_ARRAY),
    DATOS_INVENTARIO_JSON("datos_inventario.json",  TipoArchivoJSON.JSON_ARRAY),
    DATOS_IDS_SISTEMA_JSON("datos_ids.json",  TipoArchivoJSON.JSON_OBJECT),
    DATOS_MOVIMIENTO_JSON("datos_movimiento.json",  TipoArchivoJSON.JSON_ARRAY);

    private final String nombre;
    private final TipoArchivoJSON tipo;

    ArchivoSistema(String nombre, TipoArchivoJSON tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoArchivoJSON getTipo() {
        return tipo;
    }



    //Crea el archivo si no existe:
    public void crearArchivo()
    {
        File file = new File(nombre);

        if (!file.exists())
        {
                try (FileWriter fileWriter = new FileWriter(file))
                {
                        if (tipo == TipoArchivoJSON.JSON_OBJECT)
                            fileWriter.write("{}");
                        else
                            fileWriter.write("[]");


                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
    }

    //Inicializa los archivos necesario para el gestorStock:
    public static void inicializarArchivosSistema( )
    {
        for (ArchivoSistema archivo : ArchivoSistema.values())
            archivo.crearArchivo();
    }
}