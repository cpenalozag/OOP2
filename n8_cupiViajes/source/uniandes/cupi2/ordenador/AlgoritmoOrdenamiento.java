package uniandes.cupi2.ordenador;

/**
 * Enumeración de los tipos de ordenamiento disponibles en el programa
 * @author alvar-go
 */
public enum AlgoritmoOrdenamiento {

	BURBUJA ("Burbuja"),
	SELECCION ("Selección"),
	INSERCION ("Inserción"),
	SHAKER("Burbuja bi-direccional"),
	GNOME("Gnomo de jardin");
	
	/**
	 * Nombre del ordenamiento
	 */
	private final String nombre;
	
	/**
	 * Crea un elemento de la enumeración
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
	 * Representación como String de cada elemento de la enumeración
	 * @return nombre
	 */
	public String toString()
	{
		return nombre;
	}
	
	
}
