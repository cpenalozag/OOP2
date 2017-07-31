package estructuras.arboles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Nodo de un �rbol n-ario
 * @author Alvaro
 *
 * @param <K>
 * @param <V>
 */
public class NodoAN<K extends Comparable<K>,V> implements Serializable, Map.Entry<K, V> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador del nodo 
	 */
	private K id;

	/**
	 * Elemento del nodo
	 */
	private V elem;

	/**
	 * Lista de hijos del nodo
	 */
	private List<NodoAN<K, V>> hijos;


	/**
	 * Construye un nuevo nodo con la informaci�n por par�metro
	 * @param nId el id del nodo. nId != null
	 * @param nElem el elemento del nodo. nElem != null
	 */
	public NodoAN(K nId, V nElem)
	{
		id = nId;
		elem = nElem;
		hijos = new ArrayList<NodoAN<K,V>>();

	}

	/**
	 * Devuelve el id del nodo
	 * @return id
	 */
	public K getKey() {
		return id;
	}

	/**
	 * Devuelve el elemento del nodo
	 * @return elem
	 */
	public V getValue() {
		return elem;
	}

	/**
	 * Cambia el elemento alamcenado en el nodo
	 * @param nElem el nuevo elemento del nodo
	 * @return el elemento previo del nodo
	 */
	public V setValue(V nElem)
	{
		V antiguo = elem;
		elem = nElem;
		return antiguo;
	}

	/**
	 * Inidica si el sub �rbol que inicia en el nodo contiene el id por par�metro
	 * @param buscado el id buscado
	 * @return true en caso que el sub �rbol contenga la llave o false en caso contrario
	 */
	public boolean containsKey(K buscado)
	{
		boolean contiene = false; 
		if(buscado.equals(id))
		{
			contiene = true;
		}
		else
		{
			for(int i = 0; i<hijos.size()&&!contiene;i++)
			{
				contiene = hijos.get(i).containsKey(buscado);
			}
		}
		return contiene;
	}

	/**
	 * Devuelve el elemento del nodo con el id dado por par�metro
	 * @param buscado el id del nodo buscado
	 * @return el elemento del nodo o null en caso que no exista un nodo con el id dado
	 */
	public V get(K buscado) 
	{
		V resp = null; 
		if(buscado.equals(id))
		{
			resp = elem; 
		}
		else
		{
			for(int i = 0; i<hijos.size()&&resp==null;i++)
			{
				resp = hijos.get(i).get(buscado);
			}
		}
		return resp; 
	}

	/**
	 * Devuelve un nodo del sub �rbol dado su id
	 * @param buscado el id del nodo buscado
	 * @return el nodo con el id buscado o null en caso que no exista
	 */
	public NodoAN<K,V> getNodo(K buscado) 
	{
		NodoAN<K, V> resp = null; 
		if(buscado.equals(id))
		{
			resp = this; 
		}
		else
		{
			for(int i = 0; i<hijos.size()&&resp==null;i++)
			{
				resp = hijos.get(i).getNodo(buscado);
			}
		}
		return resp;
	}

	/**
	 * Agrega un nuevo nodo a la lista de hijos del nodo
	 * @param nId el id del nuevo nodo. nId != null
	 * @param nElem el elemento del nuevo nodo. nElem != null
	 */
	public void put(K nId, V nElem) throws Exception
	{
		if(containsKey(nId))
		{
			throw new IllegalArgumentException("Ya existe el nodo");
		}
		else
		{
			hijos.add(new NodoAN<K, V>(nId, nElem));
		}
	}

	/**
	 * Devuelve la cantidad de nodos del sub �rbol que inicia en el nodo
	 * @return cantidad de nodos
	 */
	public int size()
	{
		int tam = 1;
		for(int i = 0; i<hijos.size();i++)
		{
			tam += hijos.get(i).size();
		}
		return tam; 
	}

	/**
	 * Construye un conjunto de los ids de los nodos del �rbol
	 * @param s el conjunto donde se acumulan los ids de los nodos del �rbol
	 */
	public void keyset(Set<K> s) 
	{
		s.add(id);
		for (int i = 0; i < hijos.size(); i++) {
			hijos.get(i).keyset(s);			
		}
	}

	/**
	 * Indica si el sub �rbol que inicia en el nodo contiene el elemento por par�metro
	 * @param buscado el valor buscado
	 * @return true en caso que el �rbol tenga el elemento o false en caso contrario
	 */
	public boolean containsValue(V buscado) 
	{
		boolean contiene = false; 
		if(buscado.equals(id))
		{
			contiene = true;
		}
		else
		{
			for(int i = 0; i<hijos.size()&&!contiene;i++)
			{
				contiene = hijos.get(i).containsValue(buscado);
			}
		}
		return contiene; 
	}

	/**
	 * Construye un conjunto con los nodos del �rbol en pre orden
	 * @param s el conjunto donde se acumulan los nodos del �rbol
	 */
	public void entrySet(Set<java.util.Map.Entry<K, V>> s) 
	{
		s.add(this);
		for (int i = 0; i < hijos.size(); i++) {
			hijos.get(i).entrySet(s);			
		}

	}

	/**
	 * Construye una colecci�n de los elementos del �rbol en pre orden
	 * @param s la colecci�n donde se acumulan los elementos del �rbol
	 */
	public void values(Collection<V> s) 
	{
		s.add(elem);
		if(hijos.size()!=0)
		{
			for(NodoAN<K, V> n : hijos)
			{
				n.values(s);
			}
		}
	}

	/**
	 * Construye una colecci�n de los elementos del �rbol en pos orden
	 * @param s la colecci�n donde se acumulan los elementos del �rbol
	 */
	public void valuesPos(Collection<V> s) 
	{

		if(hijos.size()!=0)
		{
			for(int i = 0; i<hijos.size();i++)
			{
				s.add(hijos.get(i).getValue());
				hijos.get(i).valuesPos(s);

			}
		}
		s.add(elem);
	}

	/**
	 * Construye una colecci�n de los elementos de un nivel del �rbol
	 * @param s la colecci�n donde se acumulan los elementos del �rbol
	 * @param level el nivel del que se desean los elementos
	 */
	public void valuesLevel(Collection<V> s, int level) 
	{
		for(NodoAN<K, V> n: hijos)
		{
			if (level==1)
			{
				n.values(s);
			}
			else{
				level--;
				n.valuesLevel(s, level);
			}
		}
	}

	/**
	 * Elimina un nodo del �rbol, en caso que tenga hijos estos tambien se eliminan
	 * @param buscado el identificador del nodo a eliminar
	 * @return el elemento del nodo eleminado o null en caso que no se encuentre el nodo a eliminar
	 */
	public V remove(K buscado)
	{
		NodoAN<K, V> n = getNodo(buscado);
		V resp = null;
		if(n!=null)
		{
			Collection<NodoAN<K, V>> c = n.darHijos();
			if(c !=null)
			{
				hijos.removeAll(c);
				resp = n.getValue();
			}
			else
			{
				hijos.remove(n);
				resp = n.getValue();
			}
		}
		return resp;
	}

	/**
	 * Indica si el nodo es hoja
	 * @return true en caso que el nodo sea hoja o false en caso contrario
	 */
	public boolean esHoja() 
	{
		boolean hoja = false;
		if(hijos.size()==0)
		{
			hoja = true;
		}
		return hoja;
	}

	/**
	 * Construye una colecci�n con los valores en las hojas del �rbol
	 * @param s la colecci�n donde se acumulan las hojas del �rbol
	 */
	public void darHojas(Collection<V> s) 
	{
		Collection<V> c = new LinkedList<V>();
		if(esHoja())
		{
			c.add(elem);
		}
		else
		{
			for(int i = 0; i < hijos.size(); i++)
			{
				boolean h = hijos.get(i).esHoja();
				if(h)
				{
					c.add(hijos.get(i).getValue());
				}
			}
		}
	}

	/**
	 * Devuelve la lista de hijos del �rbol
	 * @return hijos
	 */
	public Collection<NodoAN<K, V>> darHijos()
	{
		return hijos;
	}

	public String toString()
	{
		return elem.toString();
	}

}
