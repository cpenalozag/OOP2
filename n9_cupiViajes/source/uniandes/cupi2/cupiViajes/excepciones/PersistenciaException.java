package uniandes.cupi2.cupiViajes.excepciones;

public class PersistenciaException extends Exception
{
    private String rutaArchivo;
    
    public PersistenciaException(String mensaje, String nRutaArchivo)
    {
        super( mensaje );
        rutaArchivo = nRutaArchivo;
    }
    
    public String darRutaArchivo()
    {
        return rutaArchivo;
    }
}
