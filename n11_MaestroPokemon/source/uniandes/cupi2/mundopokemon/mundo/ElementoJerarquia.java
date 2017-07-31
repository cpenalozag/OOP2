package uniandes.cupi2.mundopokemon.mundo;

/**
 * Representa un elemento de la jerarquía Pokemon
 * @author alvar-go
 *
 */
public class ElementoJerarquia 
{
	/**
	 * Nombre que identifica de manera única al elemento
	 */
	protected String nombre;
	
	/**
	 * El tipo de elemento
	 */
	protected TipoElementoJerarquia tipoElemento;
	
	/**
	 * Crea un nuevo elemento de la jerarquia
	 * @param nNombre el nombre del elemento. nNombre != null
	 * @param nTipoElemento el tipo del elemento. nTipoElemento != null
	 */
	public ElementoJerarquia (String nNombre, TipoElementoJerarquia nTipoElemento)
	{
		nombre = nNombre;
		tipoElemento = nTipoElemento;
	}
	
	public String toString()
	{
		return nombre;
	}
}
