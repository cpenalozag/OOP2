package uniandes.cupi2.ordenador;

/**
 * Enumeraci�n de los tipos de ordenamiento disponibles en el programa
 * @author alvar-go
 */
public enum AlgoritmoOrdenamiento {

    BURBUJA ("Burbuja"),
    SELECCION ("Selecci�n"),
    INSERCION ("Inserci�n"),
    SHAKER("Burbuja bi-direccional"),
    GNOME("Gnomo de jardin");

    /**
     * Nombre del ordenamiento
     */
    private final String nombre;

    /**
     * Crea un elemento de la enumeraci�n
     * @param nNombre el nombre del ordenamiento.
     */
    private AlgoritmoOrdenamiento(String nNombre)
    {
        nombre = nNombre;
    }

    /**
     * Devuelve el nombre el ordenamiento
     * @return nombre
     */
    public String darNombre()
    {
        return nombre;
    }

    /**
     * Representaci�n como String de cada elemento de la enumeraci�n
     * @return nombre
     */
    public String toString()
    {
        return nombre;
    }


}
