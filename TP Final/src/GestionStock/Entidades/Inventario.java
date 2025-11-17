package GestionStock.Entidades;

import GestionStock.Archivos.ArchivoSistema;
import GestionStock.Enum.TipoMovimiento;
import GestionStock.Gestor.GestionLote;
import GestionStock.Gestor.GestionStock;
import GestionStock.Gestor.GestorMovimiento;
import GestionStock.Interfaces.iCargar;
import Users.UsuarioSistema.Usuario;

import java.util.ArrayList;

public final class Inventario implements  iCargar
{
    private GestionLote gestionLote;
    private GestionStock gestionStock;
    private GestorMovimiento gestorMovimiento;

    private Usuario usuario;


    public Inventario()
    {
        ArchivoSistema.inicializarArchivosSistema();
        this.usuario = usuario;
        cargar();
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public GestionLote getGestionLote() {
        return gestionLote;
    }

    public void setGestionLote(GestionLote gestionLote) {
        this.gestionLote = gestionLote;
    }

    public GestionStock getGestionStock() {
        return gestionStock;
    }

    public void setGestionStock(GestionStock gestionStock) {
        this.gestionStock = gestionStock;
    }



    @Override
    public void cargar()
    {
        gestionLote = new GestionLote();
        gestionStock = new GestionStock();
        gestorMovimiento = new GestorMovimiento();
    }

    public void ingresarProducto(Lote nuevo)
    {
        String idProductoIngresado = nuevo.getIdProducto();

        //Si el stock no existe se crea:
        if (  !gestionStock.verificarExistenciaStock(idProductoIngresado)  )
        {
            gestionStock.agregar( new Stock(idProductoIngresado, nuevo.getCantidadInicial()) );
        }

        //En caso si existe:
        gestionLote.agregar(nuevo);

        //Se calcula la cantidad total:
        int cantidadActualizada = gestionLote.calcularStockProducto( idProductoIngresado );

        //Se actualiza el stock:
        gestionStock.actualizarCantStock( idProductoIngresado, cantidadActualizada );

        gestorMovimiento.agregar(
          new Movimiento(usuario.getIdUsuario(),TipoMovimiento.ENTRADA, nuevo.getIdProducto(), nuevo.getId(), nuevo.getCantidadInicial())
        );
    }

    public void sacarProducto(String idProducto, int cantidad)
    {
        //validar parametros:

        //verificar existencia del stock disponible:
        if ( gestionStock.verificarExistenciaStock(idProducto) )
        {
            //se extrae la cantidad de los lotes:
            gestionLote.extraer(idProducto, cantidad);

            //se calcula su nuevo stock y se actualiza:
            gestionStock.actualizarCantStock( idProducto, gestionLote.calcularStockProducto(idProducto));

            gestorMovimiento.agregar(
                    new Movimiento(usuario.getIdUsuario(),TipoMovimiento.SALIDA, idProducto, null, cantidad)
            );
        }
    }


    public String listarRegistroMovimientosInventario()
    {
        return gestorMovimiento.listar();
    }
    public String listarRegistroMovimientosInventario(String idUsuario)
    {
        return gestorMovimiento.listar(idUsuario);
    }

    public String listarStockProductos( )
    {
        return  gestionStock.listar();
    }

    public String listarLotesProducto( )
    {
        return gestionLote.listar();
    }

    public String listarLotesProducto(String idProducto)
    {
        return gestionLote.listar(idProducto);
    }



    public boolean darAltaStockProducto(String idProducto)
    {
        return gestionStock.altaStockProducto(idProducto);
    }

    public boolean darBajaStockProducto(String idProducto)
    {
        return gestionStock.bajaStockProducto(idProducto);
    }

    public boolean darBajaLoteProducto(String idLote)
    {
        return gestionLote.eliminar(idLote);
    }

    public double calcularValoracionInventario( )
    {
        double total = 0;

        ArrayList<Lote> lotes = gestionLote.getArrayListLote();

        for (Lote i : lotes)
            total += i.calcularValoracion();

        return total;
    }

}
