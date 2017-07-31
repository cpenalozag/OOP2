package uniandes.cupi2.cupiViajes.excepciones;

public class ClienteTieneReservaException extends Exception
{
    private String cedulaCliente;
    
    public ClienteTieneReservaException(String mensaje, String nCedulaCliente)
    {
        super( mensaje );
        cedulaCliente = nCedulaCliente;
    }
    
    public String darCedulaCliente()
    {
        return cedulaCliente;
    }

}
