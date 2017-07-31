package uniandes.cupi2.mundopokemon.mundo;

/**
 * Representa un pokemon una vez ha sido cpaturado
 * @author alvar-go
 *
 */
public class PokemonCapturado extends Pokemon
{
	/**
	 * Cantidad de niveles para evolucionar
	 */
	public final static int NIVEL_EVOLUCION = 12;
	
	/**
	 * El nivel actual del pokemon
	 */
	private int nivel;
	
	/**
	 * Número de niveles restantes para evolucionar
	 */
	private int paraEvolucionar;
	
	/**
	 * Crea un pokemon capturado a partir de sus datos
	 * post: los niveles restantes para evolucionar se han iniciado en NIVEL_EVOLUCION
	 * @param nNombre el nombre del pokemon. nNombre != null
	 * @param nNumero el número del pokemon. nNumero != null
	 * @param nNivel el nivel en que inicia el pokemon. nNivel > 0
	 */
	public PokemonCapturado(String nNombre, String nNumero, int nNivel)
	{
		super(nNombre, nNumero);
		nivel = nNivel;
		paraEvolucionar = NIVEL_EVOLUCION;
	}
	
	/**
	 * Da el nivel actaul del pokemon
	 * @return nivel
	 */
	public int darNivel()
	{
		return nivel;
	}
	
	/**
	 * Aumenta el nivel del pokemon
	 * post:	Se ha aumentado el nivel en 1
	 * 			Se ha disminuido la cantidad de niveles para evolucionar en 1
	 * 			Si debe evolucionar se ha reiniciado la cantidad de niveles para evolucionar en NIVEL_EVOLUCION
	 * @return true si ha completado la cantidad de niveles para evolucionar o false en caso contrario
	 */
	public boolean aumentarNivel()
	{
		boolean evoluciona = false;
		nivel++;
		paraEvolucionar--;
		if(paraEvolucionar == 0)
		{
			evoluciona = true;
			paraEvolucionar = NIVEL_EVOLUCION;
		}
		return evoluciona;
	}
}
