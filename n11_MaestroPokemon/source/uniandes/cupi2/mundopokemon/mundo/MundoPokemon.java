package uniandes.cupi2.mundopokemon.mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import estructuras.arboles.ArbolN;
import estructuras.arboles.NodoAN;

/**
 * Clase que representa el mundo pokemon
 * @author alvar-go
 *
 */
public class MundoPokemon 
{

	/**
	 * La lista de entrenadores del mundo pokemon
	 */
	private List<Entrenador> entrenadores;
	
	/**
	 * Árbol con los pokemons registrados en el mundo
	 */
	private ArbolN<String, ElementoJerarquia> pokemons;
	
	/**
	 * Crea un nuevo mundo pokemon
	 * post: 	se ha inicializado la lista de entrenadores como vacio
	 * 			se ha inicializado y poblado el árbol de pokemons
	 */
	public MundoPokemon()
	{
		entrenadores = new ArrayList<Entrenador>();
		pokemons = new ArbolN<String, ElementoJerarquia>();
		cargarPokemons();
	}
	
	/**
	 * Carga los pokemons en el árbol
	 */
    private void cargarPokemons(){
		
		pokemons.put(null, "Pokemon", new ElementoJerarquia("Pokemon", TipoElementoJerarquia.PRINCIPAL));
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("data/tipos.txt"));
			String t = in.readLine();
			while(t != null)
			{
				pokemons.put("Pokemon", t, new ElementoJerarquia(t, TipoElementoJerarquia.TIPO));
				t = in.readLine();
				
			}
			in.close();
		} catch (Exception e) {
		}
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("data/Pokemons.txt"));
			String t = in.readLine();
			while(t != null)
			{
				String[] datos = t.split("-");
				pokemons.put(datos[1], datos[2], new Pokemon(datos[2], datos[0]));
				t = in.readLine();
				
			}
			in.close();
		} catch (Exception e) {
		}
	}
    
    /**
     * Devuelve el elemento principal de la jerarquia de pokemons
     * @return raiz del árbol
     */
    public NodoAN<String, ElementoJerarquia> darRaiz()
    {
    	return pokemons.darRaiz();
    }
	
	/**
	 * Agrega un nuevo entrenador al mundo pokemon
	 * @param nombre el nombre del entrenador. nombre != null && nombre != ""
	 * @param imagen la imagen del entrenador. imagen != null && imagen != ""
	 * @return true en caso de que se pueda agregar el entrenador o false si ya existia un entrenador con el mismo nombre
	 */
	public boolean agregarEntrenador(String nombre, String imagen)
	{
		boolean agregado = false;
		if( buscarEntrenador(nombre) == null )
		{
			entrenadores.add(new Entrenador(nombre, imagen));
			agregado = true;
		}
		return agregado;
	}

	/**
	 * Busca un entrenador a partir de su nombre
	 * @param nombre el nombre del entrenador buscado. nombre != null && nombre != ""
	 * @return el entrenador con el nombre que llega por parámetro o null en caso de que no exista un entrenador con el nombre dado
	 */
	public Entrenador buscarEntrenador(String nombre) 
	{
		Entrenador b = null;
		for (int i = 0; i < entrenadores.size() && b == null; i++) {
			if( entrenadores.get(i).darNombre().equals(nombre))
			{
				b = entrenadores.get(i);
			}
		}
		return b;
	}
	

	/**
	 * Devuelve las lista de entrenadores del mundo pokemon
	 * @return entrenadores
	 */
	public List<Entrenador> darEntrenadores() {
		return entrenadores;
	}

	/**
	 * Aumenta el nivel de un pokemon capturado por un entrenador
	 * @param pokemon el pokemon al que se le aumenta el nivel. pokemon != null
	 * @param entrenador el entrenador dueño del pokemon. entrenador != null
	 * @return el pokemon después de aumentar de nivel, es decir el pokemon o su evolución
	 * @throws Exception Se lanza si el pokemon puede evolucionar pero ya se ha capturado la evolución
	 */
	public PokemonCapturado aumentarNivel(PokemonCapturado pokemon, Entrenador entrenador) throws Exception{
		boolean evol = pokemon.aumentarNivel();
		PokemonCapturado reemplazo = pokemon;
		if(evol)
		{
			NodoAN<String, ElementoJerarquia> nuevo = pokemons.getNodo(pokemon.nombre);
			Collection<NodoAN<String, ElementoJerarquia>> hijos = nuevo.darHijos();
			if(hijos.size() > 0)
			{
				NodoAN<String, ElementoJerarquia> evolucion = null;
				int x = (int)(Math.random()*hijos.size());
				Iterator<NodoAN<String, ElementoJerarquia>> it = hijos.iterator();
				while(x >= 0)
				{
					evolucion = it.next();
					x--;
				}
				Pokemon p = (Pokemon)evolucion.getValue();
				reemplazo = entrenador.capturarPokemon(p.darNombre(), p.darNumero(), pokemon.darNivel());
				entrenador.liberarPokemon(pokemon.darNombre());
			}
		}
		
		return reemplazo;
	}

	/**
	 * Extensión 1
	 * @return
	 */
	public String opcion1() {
		return "Mensaje 1";
	}
	
	/**
	 * Extensión 2
	 * @return
	 */
	public String opcion2() {
		return "Mensaje 2";
	}
	
	
}
