package uniandes.cupi2.mundopokemon.mundo;

import java.util.Collection;

import estructuras.arboles.ArbolBO;
import estructuras.arboles.NodoABO;

/**
 * Clase que representa a un entrenador
 * @author alvar-go
 *
 */
public class Entrenador 
{
	
	/**
	 * Arbol Binario ordenado por nombre de pokemon del entrenador
	 */
	private ArbolBO<String, PokemonCapturado> pokemons;
	
	/**
	 * Nombre del entrenador
	 */
	private String nombre;
	
	/**
	 * Imagen del entrenador
	 */
	private String imagen;
	
	/**
	 * Crea un nuevo entrenador a partir de la información entregada
	 * post: se ha inicializado el árbol de pokemons
	 * @param nNombre el nombre del entrenador. nNombre !=null && nNombre != ""
	 * @param nImagen la ruta de la imagen del entrenador. nImagen !=null && nImagen != ""
	 */
	public Entrenador(String nNombre, String nImagen)
	{
		nombre = nNombre;
		imagen = nImagen;
		pokemons = new ArbolBO<String, PokemonCapturado>();
	}
	
	/**
	 * Devuelve el nombre del entrenador
	 * @return nombre
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * Devuelve la imagen del entrenador
	 * @return imagen
	 */
	public String darImagen()
	{
		return imagen;
	}
	
	/**
	 * Devuelve el arreglo de pokemons del entrenador ordenados alfabétiacmente
	 * @return pokemons
	 */
	public Collection<PokemonCapturado> darPokemons()
	{
		return pokemons.values();
	}
	
	/**
	 * Captura un nuevo pokemon y lo agrega al arreglo de pokemones del entrenador
	 * post:	Se ha agregado el pokemon al árbol de pokemons
	 * @param nombreP el nombre del pokemon. nombreP != null && nombreP != ""
	 * @param nNumero el número del pokemon. nNumero != null
	 * @param nivelP el nivel en que se captura al pokemon. nivel > 0
	 * @return devuelve el pokemon capturado
	 * @throws Se lanza en caso que ya se tenga al mismo pokemon
	 */
	public PokemonCapturado capturarPokemon(String nombreP, String nNumero, int nivelP) throws Exception
	{
		PokemonCapturado p = new PokemonCapturado(nombreP, nNumero, nivelP);
		try {
			pokemons.put(p.darNombre(), p);
			return p;
			
		} catch (IllegalArgumentException e) {
			throw new Exception("Ya existe un pokemon con el nombre dado");
		}
	}
	
	/**
	 * Libera el pokemon de la posición que llega por parámetro
	 * post: se ha eliminado el pokemon del árbol
	 * @param nombre el nombre del pokemon a eliminar. nombre != null y existe en el árbol
	 */
	public void liberarPokemon(String nombre)
	{
		pokemons.remove(nombre);
		
	}
	
	/**
	 * Indica cuantos pokemon ha capturado el entrenador.
	 * pre: el árbol de pokemons se ha inicializado
	 * @return cantidad de capturados
	 */
	public int cuantosCapturados()
	{
		return pokemons.size();
	}
	
	/**
	 * Indica si el entrenador tiene un Pokemon
	 * @param s el nombre el pokemon buscado. s!=null
	 * @return true si el entrenador tiene al pokemon o false en caso contrario
	 */
	public boolean tienePokemon(String s)
	{
		return pokemons.containsKey(s);
	}
	
	/**
	 * Devuelve el nodo raiz del árbol de pokemons
	 * @return raiz del árbol
	 */
	public NodoABO<String, PokemonCapturado> darRaiz() {
		return pokemons.darRaiz();
	}
	
	/**
	 * Representación en String de la clase entrenador
	 */
	public String toString()
	{
		return nombre;
	}

	/**
	 * Da el nombre del pokemon capturado con menor nombre
	 * @return nombre pokemon con menor nombre o null si no hay pokemon
	 */
	public String darMenorNombre() {
		return pokemons.firstKey();
	}

	/**
	 * Devuelve un árbol con los pokemons con nombre mayor o igual al límite
	 * @param limite límite de la busqueda
	 * @return árbol con los pokemons con nombre mayor o igual al límite
	 */
	public ArbolBO<String, PokemonCapturado> darMayoresQue(String limite) {
		return (ArbolBO<String, PokemonCapturado>) pokemons.tailMap(limite);
	}
	
	/**
	 * Devuelve un árbol con los pokemons con nombres entre los límites dados
	 * @param limiteI límite inferior de la busqueda (excluido)
	 * @param limiteS límite superior de la busqueda (incluido)
	 * @return árbol con los pokemons con nombre entre los límites
	 */
	public ArbolBO<String, PokemonCapturado> darEntre(String limiteI, String limiteS) {
		return (ArbolBO<String, PokemonCapturado>) pokemons.subMap(limiteI, limiteS);
	}

}
