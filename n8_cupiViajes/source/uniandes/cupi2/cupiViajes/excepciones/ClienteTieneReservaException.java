package uniandes.cupi2.cupiViajes.excepciones;

public class ClienteTieneReservaException extends Exception
{
    private String cedulaCliente;


    public ClienteTieneReservaException( String pCedula, String msj )
    {
        super(msj);
        cedulaCliente=pCedula;
    }

    public String darMensaje()
    {
        return super.getMessage( )+cedulaCliente;
    }

    public String darCedulaCliente()
    {
        return cedulaCliente;
    }
}
