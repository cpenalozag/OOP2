package estructuras.arboles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


/**
 * �rbol binario ordenado
 * @author adlvar-go
 * @param <K> tipo del identificador
 * @param <V> tipo de los elementos guardados
 */
public class ArbolBO<K extends Comparable<K>, V> implements SortedMap<K, V>
{

	/**
	 * Ra�z del �rbol
	 */
	private NodoABO<K, V> raiz;

	/**
	 * Crea un nuevo �rbol sin elementos
	 * post: la ra�z del �rbol como null
	 */
	public ArbolBO() 
	{
		raiz = null;
	}

	/**
	 * Crea un nuevo �rbol con elementos
	 * @param nRaiz la ra�z del �rbol
	 */
	public ArbolBO(NodoABO<K, V>  nRaiz) 
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
	 * Indica si el �rbol contiene un elemento con el identificador dado
	 * @return true si el �rbol contiene el identificador o false en caso contrario
	 */
	public boolean containsKey(Object id) 
	{
		boolean contiene = false;
		K buscado = (K)id;
		if(!id.equals("")||id!=null){
			if(raiz!=null)
			{
				contiene = raiz.containsKey(buscado);
			}
		}
		return contiene;
	}

	/**
	 * Indica si el �rbol contiene el elemento dado por par�metro
	 * @return true si el �rbol contiene el elemento o false en caso contrario
	 */
	public boolean containsValue(Object elem) 
	{
		boolean contiene = false;
		V buscado = (V)elem;
		if(raiz!=null)
		{
			raiz.containsValue(buscado);
		}
		return contiene;
	}

	/**
	 * Devuelve el elemento con el identificador dado
	 * @return el elemento o null si el elemento no est� en el �rbol
	 */
	public V get(Object id) 
	{
		V resp = null;
		K buscado = (K)id;
		if(raiz!=null)
		{
			resp = raiz.get(buscado);
		}
		return resp;
	}

	/**
	 * Indica si el �rbol est� vacio
	 * @return true en caso que el �rbol est� vaio o false en caso contrario
	 */
	public boolean isEmpty() 
	{
		boolean vacio = false;
		if(raiz==null)
		{
			vacio = true;
		}
		return vacio;
	}

	/**
	 * Agrega un nuevo elemento al �rbol
	 * @param id el identificador del elemento
	 * @param elem el elemento que se desea agregar
	 * @return null
	 * @throws NullPointerException si el identificador o el elemento son nulos
	 * @throws IllegalArgumentException si el elemento ya existe en el �rbol
	 */
	public V put(K id, V elem)  throws NullPointerException, IllegalArgumentException
	{
		NodoABO<K, V> n = new NodoABO<K,V>(id, elem);
		if(raiz==null)
		{
			raiz = n; 
		}
		else 
		{
			raiz.put(id, elem);	
		}
		return null;
	}


	/**
	 * Agrega todos los elementos dados por par�metro en el �rbol
	 * @param map mapa con los elementos que se desean agregar
	 */
	public void putAll(Map<? extends K, ? extends V> map) 
	{
		Iterator<? extends K> llaves = map.keySet().iterator();
		while(llaves.hasNext())
		{
			K llave = llaves.next();
			put(llave, map.get(llave));
		}
	}


	/**
	 * Elimina un elemento del �rbol
	 * @param id el identificador del elemento a agregar
	 * @return el elemento elimminado
	 */
	public V remove(Object id) 
	{
		K buscado = (K)id;
		V resp = null;
		if (raiz!=null)
		{
			raiz = raiz.remove(buscado);
		}
		else{
			if (raiz.remove(buscado)!=null)
			{
				resp = raiz.remove(buscado).getValue();
			}
		}
		return resp;
	}

	/**
	 * Indica la cantidad de elementos en el �rbol
	 * @return peso del �rbol
	 */
	public int size() 
	{
		return raiz==null?0:raiz.size();
	}


	public Comparator<? super K> comparator() 
	{
		//No se implementa
		return null;
	}

	/**
	 * Devuelve los nodos del �rbol en forma de conjunto
	 * @return conjunto de ids
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
	 * Devuelve el menor id del �rbol
	 * @return el menor id o null si el �rbol est� vacio
	 */
	public K firstKey() 
	{
		K resp = null;
		if(raiz!=null)
		{
			resp = raiz.firstKey();
		}
		return resp;
	}


	/**
	 * Crea y devuelve un �rbol con los elementos cuyo identificador es estrictamente menor al par�metro
	 * @param limite identificador debajo del cual se quiere que est�n todos los identificadores del �rbol
	 * @return un �rbol con los elementos con identificador al l�mite o un �rbol vacio si el �rbol est� vacio
	 */
	public SortedMap<K, V> headMap(K limite) 
	{
		SortedMap<K, V> arbol = new ArbolBO<K,V>();
		if(raiz != null )
		{
			raiz.headMap(limite, arbol);
		}
		return arbol;
	}

	/**
	 * Devuelve el conjunto de todos los identificadores del �rbol
	 * @return conjunto de identificadores
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
	 * Devuelve el identificador m�s grande del �rbol
	 * @return identificador m�s grande
	 */
	public K lastKey() 
	{
		K resp = null;
		if(raiz!=null)
		{
			resp = raiz.lastKey();
		}
		return resp;
	}

	/**
	 * Devuelve un �rbol con los elementos cuyos identificadores est�n en los l�mites
	 * @param limiteInf identificador inferior (se incluye)
	 * @param limiteSupo identificador superior (se excluye)
	 * @return un �rbol con los elementos entre los l�mites o un �rbol vacio si el �rbol est� vacio
	 */
	public SortedMap<K, V> subMap(K limiteInf, K limiteSup) 
	{
		ArbolBO<K, V> s = new ArbolBO<K,V>();
		if(raiz != null && !limiteInf.equals(limiteSup))
		{
			raiz.subMap(limiteInf, limiteSup, s);
		}
		return s;
	}


	/**
	 * Devuelve un �rbol con los elementos cuyo identificador es mayor o igual al l�mite
	 * @param limite identificador usado como l�mite para construir el �rbol
	 * @return un �rbol con los elementos con identificador mayor o igual al l�mite o un �rbol vacio si el �rbol est� vacio
	 */
	public SortedMap<K, V> tailMap(K limite) {
		SortedMap<K, V> arbol = new ArbolBO<K,V>();
		if(raiz != null )
		{
			raiz.tailMap(limite, arbol);
		}
		return arbol;
	}


	/**
	 * Devuelve una colecci�n con los elementos del �rbol inorder
	 * @return colecci�n con los elementos del �rbol o una colecci�n vacia si no hay elementos
	 */
	public Collection<V> values() {
		Collection<V> c = new LinkedList<V>();
		if(raiz!=null)
		{
			raiz.values(c);
		}
		return c;
	}

	/**
	 * Devuelve el nodo ra�z del �rbol
	 * @return raiz
	 */
	public NodoABO<K, V> darRaiz() {
		return raiz;
	}

}
