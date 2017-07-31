package uniandes.cupi2.cupiViajes.mundo;

/**
 * Aerolineas con las que tiene convenio cupiviajes
 * @author alvar-go
 *
 */
public enum Aerolinea {

    AVIANCA("Avianca"),
    LAN("Lan"),
    VIVA_COLOMBIA("Viva Colombia"),
    SATENA("Satena"),
    JETBLUE("jetBlue"),
    IBERIA("Iberia"),
    AIR_FRANCE("Air France");

    /**
     * Nombre de la Aerolinea
     */
    private String nombre;

    /**
     * Crea una nueva Aerolina
     * @param nNombre el nombre de la Aerolinea. nNombre != null
     */
    private Aerolinea(String nNombre)
    {
        nombre = nNombre;
    }

    /**
     * Devuelve el nombre de la Aerolinea
     * @return nombre
     */
    public String darNombre()
    {
        return nombre;
    }

    /**
     * Representaci�n en String de la aerolinea
     * @return nombre
     */
    public String toString()
    {
        return nombre;
    }
}
