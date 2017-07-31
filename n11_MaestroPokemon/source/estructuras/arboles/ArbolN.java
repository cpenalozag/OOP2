package estructuras.arboles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * �rbol N-ario
 * @author Alvaro
 *
 * @param <K>
 * @param <V>
 */
public class ArbolN <K extends Comparable<K>, V> implements Serializable, Map<K, V> {

	/**
	 * Nodo ra�z del �rbol n-ario
	 */
	private NodoAN<K, V> raiz;

	/**
	 * Construye un nuevo �rbol n-ario sin elementos
	 */
	public ArbolN() 
	{
		raiz = null;
	}
	/**
	 * Construye un �rbol con la ra�z que llega por par�metro
	 * @param nRaiz la nueva ra�z del �rbol
	 */
	public ArbolN(NodoAN<K, V>  nRaiz) 
	{
		raiz = nRaiz;
	}


	/**
	 * Elimina todos los elementos del �rbol
	 */
	public void clear() 
	{
		raiz = null;
	}

	/**
	 * Indica si en el �rbol alg�n nodo tiene el identificador por par�metro
	 * @param id identificador que se desea buscar
	 * @return true en caso de que el �rbol contenga un el nodo con id dado por par�metro, false en caso contrario
	 */
	public boolean containsKey(Object id) 
	{
		K buscado = (K)id;
		boolean cont = false;
		if(raiz!=null)
		{
			cont = raiz.containsKey(buscado);
		}
		return cont;
	}

	/**
	 * Indica si alg�n nodo del �rbol tiene el elemento por par�metro
	 * @param elem el elemento que se desea buscador
	 * @return true en caso de que el �rbol contenga un nodo con el elemento dado por par�metro
	 */
	public boolean containsValue(Object elem) 
	{
		V buscado = (V)elem;
		boolean cont = false;
		if(raiz!=null)
		{
			cont = raiz.containsValue(buscado);
		}
		return cont;
	}


	/**
	 * Devuelve el elemento con el identificador dado por par�metro
	 * @param id el identificador del elemento que se desea buscar
	 * @return el elemento con el identificador dado por par�metro o null en caso que no se encuentre en el �rbol
	 */
	public V get(Object id) 
	{
		K buscado = (K)id;
		V resp = null;
		if(raiz!=null)
		{
			resp = raiz.get(buscado);
		}
		return resp;
	}

	/**
	 * Deuvelve un nodo del �rbol dado su identificador
	 * @param id el identificador del nodo
	 * @return el nodo o null en caso de que no exista
	 */
	public NodoAN<K,V> getNodo(Object id) 
	{
		K buscado = (K)id;
		NodoAN<K,V> n = null;
		if(raiz!=null)
		{
			n = raiz.getNodo(buscado);
		}
		return n;
	}


	/**
	 * Indica si el �rbol est� vac�o
	 * @return true en caso que el �rbol est� vac�o o false en caso contrario
	 */
	public boolean isEmpty() 
	{
		boolean vacio = false;
		if(raiz!=null)
		{
			vacio = true; 
		}
		return vacio;
	}

	/**
	 * Devuelve un conjunto con los nodos del �rbol
	 * @return nodos del �rbol o un conjunto vac�o en caso que el �rbol lo est�
	 */
	public Set<java.util.Map.Entry<K, V>> entrySet() 
	{
		Set<java.util.Map.Entry<K, V>> s = new HashSet<java.util.Map.Entry<K, V>>();
		if(raiz!=null)
		{
			raiz.entrySet(s);
		}

		return s;
	}

	/**
	 * Devuelve un conjunto con todos los id del �rbol
	 * @return conjunto con los identificadores de todos los nodos del �rbol o un conjunto vac�o si el �rbol lo est�
	 */
	public Set<K> keySet() 
	{
		Set<K> s = new HashSet<K>();
		if(raiz!=null)
		{
			raiz.keyset(s);
		}

		return s;
	}

	/**
	 * Agrega un nodo al �rbol dada su informaci�n
	 * @param idPadre identificador del padre del nodo si es null el nuevo nodo se agrega como ra�z
	 * @param id el identificador del nuevo nodo
	 * @param elem el elemento del nuevo nodo
	 * @return el elemento del nodo agregado
	 * @throws NullPointerException Se lanza en caso que el id o elemento se�n nulos
	 * @throws IllegalArgumentException Se lanza en caso que ya exista un nodo con el id dado o que no exista el padre en el �rbol
	 */
	public V put(K idPadre, K id, V elem) throws NullPointerException, IllegalArgumentException
	{
		V resp = elem;
		if(raiz==null)
		{
			raiz = new NodoAN<K, V>(id, elem);
		}
		else
		{
			NodoAN<K, V> padre = raiz.getNodo(idPadre);
			try {
				padre.put(id, elem);
			} 
			catch (Exception e){
				resp = null;
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return resp;
	}

	public V put(K key, V value) 
	{
		// TODO No se implementa
		return null;
	}


	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO No se implementa
	}

	/**
	 * Elimina un nodo del �rbol
	 * @param id el identificador del nodo
	 * @return el elemento almacenado en el nodo eliminado
	 */
	public V remove(Object id) {
		K buscado = (K)id;
		V resp = null; 
		if(raiz!=null)
		{
			resp = raiz.remove(buscado);
		}
		return resp;
	}

	/**
	 * Devuelve la cantidad de nodos en el �rbol
	 * @return nodos en el �rbol
	 */
	public int size() 
	{
		int tam = 0;
		if(raiz!=null)
		{
			tam = raiz.size();
		}
		return tam;
	}

	/**
	 * Devuelve la colecci�n de elementos del �rbol en preorden
	 * @return colecci�n de elementos almacenados en el �rbol o colecci�n vac�a si el �rbol lo est�
	 */
	public Collection<V> values() {
		Collection<V> c = new LinkedList<V>();
		if (raiz!=null)
		{
			c.add(raiz.getValue());
			if (raiz.darHijos()!=null)
			{
				for (NodoAN<K, V> h : raiz.darHijos()) {
					h.values(c);
				}
			}
		}
		return c;
	}

	/**
	 * Devuelve el nodo ra�z del �rbol
	 * @return raiz
	 */
	public NodoAN<K,V> darRaiz()
	{
		return raiz;
	}

	/**
	 * Devuelve la colecci�n de elementos del �rbol en postorden
	 * @return colecci�n de elementos almacenados en el �rbol o colecci�n vac�a si el �rbol lo est�
	 */
	public Collection<V> valuesPos() {
		Collection<V> c = new LinkedList<V>();
		
		return c;
	}

	/**
	 * Devuelve la colecci�n de elementos del �rbol por nivel
	 * @param level el nivel del que se desean los elementos
	 * @return colecci�n de elementos almacenados en el �rbol o colecci�n vac�a si el �rbol lo est�
	 */
	public Collection<V> valuesLevel(int level) {
		Collection<V> s = new LinkedList<V>();
		raiz.valuesLevel(s, level);
		return s;
	}
}
