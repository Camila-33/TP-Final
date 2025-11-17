package GestionStock.GeneradorID;

import GestionStock.Entidades.Lote;
import GestionStock.Entidades.Movimiento;
import GestionStock.Entidades.Stock;

import java.util.HashMap;

;

public final class RegistroID {

    
    private final HashMap<Class<?>, Integer> IDS = new HashMap<>();

    public RegistroID() {

    }

    public int getID(Class clase) {
        return IDS.getOrDefault(clase, 0);
    }
    public void setID(Class clase, int valor) {
        IDS.put(clase, valor);
    }



    public int getLoteID( )
    {
        return getID(Lote.class);
    }

    public int getStockID( )
    {
        return getID(Stock.class);
    }

    public int getMovimientoID()
    {
        return getID(Movimiento.class);
    }

    public void setLoteID(int valor)
    {
       setID(Lote.class, valor);
    }

    public void setStockID(int valor)
    {
        setID(Stock.class, valor);
    }


    public void setLoteMovimientoID(int valor)
    {
        setID(Movimiento.class, valor);
    }


    int incrementar(Class clase)
    {
        int valor = IDS.getOrDefault(clase, 0);
        valor++;

        setID(clase, valor);
        return valor;
    }
}
