package uniandes.cupi2.mundopokemon.mundo;

/**
 * Clase que representa un pokemon
 * @author alvar-go
 */
public class Pokemon extends ElementoJerarquia{

	/**
	 * El número del pokemon
	 */
	private String numero;
	
	/**
	 * Crea un pokemon a partir de su nombre y número
	 * @param nom el nombre del pokemon. nom != null
	 * @param num el número del pojemon. num != num
	 */
	public Pokemon(String nom, String num)
	{
		super(nom, TipoElementoJerarquia.POKEMON);
		numero = num;
	}
	
	/**
	 * Da el nombre del pokemon
	 * @return nombre
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * Da la imagen del pokemon
	 * @return numero + " " + nombre + ".png"
	 */
	public String darImagen()
	{
		
		return numero + " " + nombre+".png";
	}
	
	/**
	 * Da el número del pokemon
	 * @return número
	 */
	public String darNumero()
	{
		return numero;
	}
}
