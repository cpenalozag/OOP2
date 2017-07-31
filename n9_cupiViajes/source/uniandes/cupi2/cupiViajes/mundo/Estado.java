package uniandes.cupi2.cupiViajes.mundo;

/**
 * Estados de una reserva
 * @author alvar-go
 *
 */
public enum Estado 
{
    SIN_PAGO ("Sin pago"),
    PAGO_TARJETA ("Pago con tarjeta"),
    PAGO_TARJETA_EFECTIVO ("Pago con efectivo");
    
    /**
     * Nombre del estado
     */
    private String nombre;
    
    /**
     * Crea un nuevo estado
     * @param nNombre el nombre del estado. nNombre != null
     */
    private Estado(String nNombre)
    {
        nombre = nNombre;
    }
    
    public String toString()
    {
        return nombre;
    }
}
