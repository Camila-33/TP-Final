
package GestionStock.GeneradorID;

import GestionStock.Archivos.ArchivoSistema;
import GestionStock.Archivos.ManagerJSONRegistroID;
import GestionStock.Entidades.Lote;
import GestionStock.Entidades.Movimiento;
import GestionStock.Entidades.Stock;
import GestionStock.Interfaces.iActualizar;
import GestionStock.Interfaces.iCargar;
import GestionStock.Interfaces.iGuardar;

import java.time.LocalDate;
import java.util.HashMap;

public final class GeneradorID implements iGuardar, iCargar, iActualizar {


    private static final HashMap<Class<?>, String> prefijos = new HashMap<>();
    private static RegistroID registroID = null;

    public GeneradorID() {

        prefijos.put(Stock.class, "STOCK-");
        prefijos.put(Lote.class, "LOTE-");
        prefijos.put(Movimiento.class, "MOV-");
        cargar();
    }

    @Override
    public void cargar() {
        registroID = ManagerJSONRegistroID.fromArchivo(ArchivoSistema.DATOS_IDS_SISTEMA_JSON.getNombre());

        if (registroID == null)
            registroID = new RegistroID();

    }

    @Override
    public void guardar() {
        ManagerJSONRegistroID.toArchivo(registroID, ArchivoSistema.DATOS_IDS_SISTEMA_JSON.getNombre());
    }


    @Override
    public void actualizar() {
        guardar();
        cargar();
    }

    private String generarID(Class clase)
    {
        String id = prefijos.getOrDefault(clase, "ERROR-") + LocalDate.now().getYear() +"-"+ registroID.incrementar(clase);
        actualizar();
        return  id;
    }


    public String generarLoteID( )
    {
        return generarID(Lote.class);
    }

    public String generarStockID( )
    {
        return generarID(Stock.class);
    }

    public String generarMovimientoID( )
    {
        return generarID(Movimiento.class);
    }

}
