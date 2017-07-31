package uniandes.cupi2.cupiViajes.excepciones;

/**
 * Maneja los errores que se generan al leer o escribir archivos
 * @author alvar-go
 *
 */
public class PersistenciaException extends Exception
{
    private String rutaArchivo;


    public PersistenciaException (String ruta, String msj)
    {
        super(msj);
        rutaArchivo=ruta;
    }
    public String darMensaje()
    {
        return super.getMessage( )+rutaArchivo;
    }

    public String darRutaNombre()
    {
        return rutaArchivo;
    }
}
