package estructuras.arboles;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Nodo de un �rbol binario
 * @author alvar-go
 * 
 * @param <K> tipo del identificador de los elementos del nodo
 * @param <V> tipo de los elementos almacenados en el nodo
 */
public class NodoABO<K extends Comparable<K>, V> implements Map.Entry<K, V>
{

	/**
	 * Identificador del nodo
	 */
	private K id;

	/**
	 * Elemento alamacenado en el nodo
	 */
	private V elem;

	/**
	 * hijo izquierdo
	 */
	private NodoABO<K, V> izq;

	/**
	 * hijo derecho
	 */
	private NodoABO<K, V> der;

	/**
	 * Crea un nuevo nodo con el elemento por par�metro
	 * @param nId el identificador del nodo
	 * @param nElem el elemento almacenado en el nodo
	 */
	public NodoABO(K nId, V nElem)
	{
		id = nId;
		elem = nElem;
		izq = null;
		der = null;
	}

	/**
	 * Devuelve el hijo izquierdo
	 * @return izq
	 */
	public NodoABO<K, V> darIzquierda()
	{
		return izq; 
	}

	/**
	 * Devuelve el hijo derecho
	 * @return der
	 */ 
	public NodoABO<K, V> darDerecha()
	{
		return der; 
	}

	/**
	 * Devuelve el identificador del nodo
	 * @return id
	 */
	public K getKey() {
		return id;
	}

	/**
	 * Devuelve el elemento guardado en el nodo
	 * @return elem
	 */
	public V getValue() {
		return elem;
	}

	/**
	 * Cambia el elemento almacenado en el nodo
	 * post:	Se ha modificado el elemento del nodo
	 * @param nElem el nuevo elemento del nodo
	 * @return el elemento antiguo del nodo
	 */
	public V setValue(V nElem)
	{
		V antiguo = elem;
		elem = nElem;
		return antiguo;
	}

	/**
	 * Indica si el sub �rbol que inicia en el nodo contiene el identificador dado
	 * @param buscado el identificador buscado. buscado != null
	 * @return true si contiene el identificado o false en caso contrario
	 */
	public boolean containsKey(K buscado)
	{
		boolean contiene = false;

		if(buscado.equals(id))
		{
			contiene = true;
		}
		else if(buscado.compareTo(id)>0)
		{
			if(der!=null)
			{
				contiene=der.containsKey(buscado);
			}	
		}
		else if(buscado.compareTo(id)<0)
		{
			if(izq!=null)
			{
				contiene=izq.containsKey(buscado);
			}	
		}
		return contiene;
	}

	/**
	 * Dvuelve el elemento con el identificador dado
	 * @param buscado el identiicado del elemento buscado. buscado != null
	 * @return el elemento con el identificador dado o null si no existe
	 */
	public V get(K buscado) 
	{
		V resp =null;
		if(buscado.equals(id))
		{
			resp = elem;
		}
		else if(buscado.compareTo(id)>0)
		{
			if(der!=null)
			{
				resp = der.get(buscado);
			}	
		}
		else if(buscado.compareTo(id)<0)
		{
			if(izq!=null)
			{

				resp = izq.get(buscado);
			}	
		}
		return resp;
	}

	/**
	 * Agrega un elemento al �rbol
	 * @param nId el identificador del elemento. nId != null
	 * @param nElem el elemento que se desea agregar. nElem != null
	 * @throws IllegalArgumentException Se lanza si ya existe un elemento con el identificador dado
	 */
	public void put(K nId, V nElem) throws IllegalArgumentException 
	{
		if(containsKey(nId))
		{
			throw new IllegalArgumentException("Ya existe el nodo");
		}
		else if(nId.compareTo(id)>0)
		{
			if(der==null)
			{
				der = new NodoABO<K, V>(nId, nElem);
			}
			else
				der.put(nId, nElem);	
		}
		else if(nId.compareTo(id)<0)
		{
			if(izq==null)
			{
				izq = new NodoABO<K, V>(nId, nElem);
			}
			else
				izq.put(nId, nElem);	
		}
	}

	/**
	 * Indica el tama�o del �rbol que inicia en el nodo
	 * @return peso del �rbol
	 */
	public int size()
	{
		int peso = 1; 
		if(der!=null)
			peso+=der.size();
		if(izq!=null)
			peso+=izq.size();
		return peso; 
	}

	/**
	 * Indica el identificador menor del �rbol que inicia en el nodo
	 * @return identificador menor
	 */
	public K firstKey() 
	{
		K primero = null; 
		if (esHoja())
		{
			primero = id;
		}
		else{
			if(izq!=null)
			{
				primero = izq.firstKey();
			}
			else{
				primero = id;
			}
		}
		return primero; 
	}

	/**
	 * Indica el identificador mayor del �rbol que inicia en el nodo
	 * @return identificador mayor
	 */
	public K lastKey() 
	{
		K ultimo = id; 
		if(izq!=null)
		{
			ultimo = izq.lastKey();	
		}
		return ultimo; 
	}

	/**
	 * Agrega al conjunto por par�metro los identificadores del �rbol que inicia en el nodo
	 * @param s el conjunto donde se guardan los identificadores. s!=null
	 */
	public void keyset(Set<K> s) 
	{
		if(izq != null)
		{
			izq.keyset(s);
		}
		s.add(id);
		if(der != null)
		{
			der.keyset(s);
		}
	}

	/**
	 * Construye un �rbol con los elementos cuyo identificador es menor al l�mite
	 * @param limite identificador con el que se desea comparar
	 * @param map �rbol donde se agregan los elementos con identificador menor al l�mite
	 */
	public void headMap(K limite, SortedMap<K, V> map) 
	{
		if(id.compareTo(limite)<0)
		{
			map.put(id, elem);

			if(der!=null)
			{
				der.headMap(limite, map);
			}
			if(izq!=null)
			{
				izq.headMap(limite, map);
			}
		}
	}

	/**
	 * Construye un �rbol con los elementos cuyo identificador es mayor o igual al l�mite
	 * @param limite identificador con el que se desea comparar
	 * @param map �rbol donde se agregan los elementos con identificador mayor o igual al l�mite
	 */
	public void tailMap(K limite, SortedMap<K, V> map) 
	{
		if (id.compareTo(limite)>=0)
		{
			map.put(id, elem);
			if (der!=null)
			{
				der.tailMap(limite, map);
			}
			if (izq!=null)
			{
				izq.tailMap(limite, map);
			}
			//TODO Completar seg�n la documentaci�n RETO
		}
	}

	/**
	 * Indica si el �rbol que inicia en el nodo contiene un elemento
	 * @param buscado el elemento buscado
	 * @return true si el �rbol contiene el elemento o false en caso contrario
	 */
	public boolean containsValue(V buscado) 
	{
		boolean contiene = false;

		if(buscado.equals(id))
		{
			contiene = true;
		}
		else if(der!=null)
		{
			contiene = der.containsValue(buscado);
		}
		else if(izq!=null)
		{
			contiene = izq.containsValue(buscado);
		}	
		return contiene;
	}

	/**
	 * Construye un conjunto con los elementos del �rbol en inorder
	 * @param s el conjunto donde se agregan los elementos. s!= null
	 */
	public void entrySet(Set<java.util.Map.Entry<K, V>> s) 
	{
		if(izq != null)
		{
			izq.entrySet(s);
		}
		s.add(this);
		if(der != null)
		{
			der.entrySet(s);
		}
	}

	/**
	 * Devuelve el identificador del nodo
	 * @return id
	 */
	public K darIdentificador() 
	{
		return id;
	}

	/**
	 * Construye una colecci�n con los valores del �rbol en inroder
	 * @param s la colecci�n donde se agregan los elementos
	 */
	public void values(Collection<V> s) 
	{
		if(izq!=null)
			izq.values(s);
		s.add(elem);
		if(der!=null)
			der.values(s);
	}

	/**
	 * Construye un �rbol con los elementos cuyos identificadores est�n entre los l�mites dados
	 * @param limiteInf el identificador menor (se excluye)
	 * @param limiteSup el identificador mayor (se incluye)
	 * @param s el �rbol donde se agregan los elementos
	 */
	public void subMap(K limiteInf, K limiteSup, SortedMap<K, V>  s) 
	{
		if (id.compareTo(limiteInf)>0 && id.compareTo(limiteSup)<=0)
		{
			s.put(id, elem);
		}
		if (der!=null)
		{
			der.subMap(limiteInf, limiteSup, s);
		}
		if (izq!=null)
		{
			izq.subMap(limiteInf, limiteSup, s);
		}
		//TODO Completar seg�n la documentaci�n RETOe
	}

	/**
	 * Elimina el elemento de identificador dado del �rbol
	 * @param buscado el identificador del elemento a elimiinar. buscado != null
	 * @return el nodo que reeemplaza al nodo actual despu�s de hacer la eliminaci�n.
	 */
	public NodoABO<K,V> remove(K buscado)
	{
		NodoABO<K, V> reemplazo = null;
		if(id.compareTo(buscado)==0)
		{
			if(izq==null && der==null)
			{
				return null;
			}

			else if(izq!=null && der==null)
			{
				return izq;
			}

			else if(izq==null && der!=null)
			{
				return der; 
			}

			else if(izq!=null && der!=null)
			{
				reemplazo = der.darMenor();
				der = der.remove(reemplazo.getKey());
				reemplazo.der = der; 
				reemplazo.izq = izq;
				return reemplazo;
			}
		}
		else if(buscado.compareTo(id)<0)
		{
			izq = izq.remove(buscado);
			return this;
		}
		else if(buscado.compareTo(id)>0)
		{
			der = der.remove(buscado);
			return this;
		}
		return reemplazo;
	}

	/**
	 * Indica si el nodo es hoja
	 * @return true si el nodo es hoja o false en caso contrario
	 */
	public boolean esHoja() 
	{
		return der == null && izq ==null;
	}

	/**
	 * Devuelve el nodo con identificador menor a partir del nodo actual
	 * @return el nodo con identificador menor
	 */
	public NodoABO<K, V> darMenor() 
	{
		NodoABO<K, V> resp = null;
		if(esHoja())
			resp = this;
		else
		{
			if(izq!=null)
			{
				resp = izq.darMenor();
			}
			else{
				resp = this;
			}
		}
		return resp;
	}

	public String toString()
	{
		return elem.toString();
	}

}
