package uniandes.cupi2.estructuras;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Clase que unifica los elementos comunes de las listas encadendas
 * @author alvar-go
 * @param <T>
 */
public abstract class ListaEncadenadaAbstracta<T extends IdentificadoUnicamente> implements List<T>, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Primer nodo de la lista
     */
    protected NodoListaSencilla<T> primero;

    /**
     * Devuelve los elementos de la lista en forma de arreglo de objetos
     * @return un arreglo con todos los elementos de la lista
     */
    public Object[] toArray( )
    {
        IdentificadoUnicamente[] array = new IdentificadoUnicamente[size( )];
        NodoListaSencilla<T> n  = primero;
        int pos = 0;
        while(n!= null)
        {
            array[pos] = n.darElemento( );
            n = n.darSiguiente( );
            pos++;
        }
        return array;
    }

    /**
     * Devuelve los elementos de la lista como arreglo de T
     * @param array el arreglo donde se deben guardar los elementos a menos que no quepan
     * @return un arreglo con todos los elementos de la lista
     */
    public <T> T[] toArray( T[] array )
    {
        if(array.length < size( ))
        {
            return (T[])toArray( );
        }
        else
        {
            NodoListaSencilla n  = primero;
            int pos = 0;
            while(n!= null)
            {
                array[pos] = (T)n.darElemento( );
                n = n.darSiguiente( );
            }
            if(array.length > size())
            {
                array[size( )] = null;
            }
            return array;
        }
    }

    /**
     * Indica el tama�o de la lista
     * @return cantidad de nodos en la lista
     */
    public int size( )
    {
        int size = 0;
        NodoListaSencilla<T> nodo = primero;

        while( nodo != null)
        {
            size++;
            nodo = nodo.darSiguiente( );
        }
        return size;
    }

    /**
     * Reemplaza el elemento de la posici�n por el elemento que llega por par�metro
     * @param pos la posici�n en la que se desea reeemplazar el elemento
     * @param elem el nuevo elemento que se desea poner
     * @return el elemento quitado de la posici�n
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public T set( int pos, T elem ) throws IndexOutOfBoundsException
    {
        NodoListaSencilla<T> x = primero.darSiguiente( );
        T eliminado = null;
        int i = 0;
        if (pos < 0 || pos >= size( ))
        {
            throw new IndexOutOfBoundsException( );
        }
        else
        {
            if (pos == 0)
            {
                eliminado = primero.darElemento( );
                primero.cambiarElemento( elem );
            }
            else{
                while ( x.darSiguiente( )!=null )
                {
                    if (i==pos)
                    {
                        eliminado = x.darElemento( );
                        x.cambiarElemento( elem );
                    }
                }
            }
        }
        return eliminado;
    }

    /**
     * Borra de la lista todos los elementos en la colecci�n por par�metro
     * @param coleccion la colecci�n de elmentos que se desea eliminar. coleccion != null
     * @return true en caso que se elimine al menos un elemento o false en caso contrario
     */
    public boolean removeAll( Collection<?> coleccion )
    {
        boolean modificado = false;
        for( Object objeto : coleccion )
        {
            modificado |= remove( objeto );
        }
        return modificado;
    }

    /**
     * Indica la �ltima posici�n donde aparece el objeto por par�metro en la lista
     * @param objeto el objeto buscado en la lista. objeto != null
     * @return la �ltima posici�n del objeto en la lista o -1 en caso que no exista
     */
    public int lastIndexOf( Object objeto )
    {
        return indexOf( objeto );
    }

    /**
     * Devuelve un iterador sobre la lista
     * El iterador empieza en el primer elemento
     * @return un nuevo iterador sobre la lista
     */
    public Iterator<T> iterator( )
    {
        return new IteradorSencillo<T>( primero );
    }

    /**
     * Indica si la lista est� vacia
     * @return true si la lista est� vacia o false en caso contrario
     */
    public boolean isEmpty( )
    {
        return primero == null;
    }

    /**
     * Indica la posici�n del objeto que llega por par�metro en la lista
     * @param objeto el objeto que se desea buscar en la lista. objeto != null
     * @return la posici�n del objeto o -1 en caso que no se encuentre en la lista
     */
    public int indexOf( Object objeto )
    {
        int respuesta = -1;
        boolean termino = false;
        if ( objeto!=null )
        {
            for ( int i  = 0; i < size( ) && !termino; i++ )
            {
                T actual = get( i );
                if ( actual == objeto )
                {
                    termino = true;
                    respuesta = i;
                    
                }
            }
        }
        
        return respuesta;
    }

    /**
     * Devuelve el elemento de la posici�n dada
     * @param pos la posici�n  buscada
     * @return el elemento en la posici�n dada 
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public T get( int pos ) throws IndexOutOfBoundsException
    {
        T resp = null; 
        NodoListaSencilla<T> x = primero.darSiguiente( );
        if ( pos < 0 || pos >= size( ) )
        {
            throw new IndexOutOfBoundsException( );
        }
        else
        {
            if ( pos == 0 )
            {
                resp = primero.darElemento( );
            }
            else{
                for ( int i = 1; i < size( ) && x.darSiguiente( )!=null; i++ )
                {
                    if ( i == pos )
                    {
                        resp = x.darElemento( );
                    }
                    x = x.darSiguiente( );
                }
            }
        }
        return resp;
    }

    /**
     * Devuelve el nodo de la posici�n dada
     * @param pos la posici�n  buscada
     * @return el nodo en la posici�n dada 
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public NodoListaSencilla<T> getNodo( int pos )
    {
        NodoListaSencilla<T> resp = null; 
        NodoListaSencilla<T> x = primero.darSiguiente( );
        if ( pos < 0 || pos >= size( ) )
        {
            throw new IndexOutOfBoundsException( );
        }
        else
        {
            if ( pos == 0 )
            {
                resp = primero;
            }
            else{
                for ( int i = 1; i < size( ) && x.darSiguiente( )!=null; i++ )
                {
                    if ( i == pos )
                    {
                        resp = x;
                    }
                    x = x.darSiguiente( );
                }
            }
        }
        return resp;
    }

    /**
     * Indica si la lista contiene todos los objetos de la colecci�n dada
     * @param coleccion la colecci�n de objetos que se desea buscar. coleccion !=null
     * @return true en caso que todos los objetos est�n en la lista o false en caso contrario
     */
    public boolean containsAll( Collection<?> coleccion )
    {
        boolean contiene = true;
        for( Object objeto : coleccion)
        {
            contiene &= contains( objeto );
        }
        return contiene;
    }

    /**
     * Indica si la lista contiene el objeto indicado
     * @param objeto el objeto que se desea buscar en la lista. objeto != null
     * @return true en caso que el objeto est� en la lista o false en caso contrario
     */
    public boolean contains( Object objeto )
    {
        boolean contiene = false;
        if( objeto instanceof IdentificadoUnicamente )
        {
            IdentificadoUnicamente u = (IdentificadoUnicamente)objeto;
            NodoListaSencilla<T> nodo = primero;
            while( nodo!= null && !contiene)
            {
                if(nodo.darElemento( ).darIdentificador( ).equals( u.darIdentificador( ) ))
                {
                    contiene = true;
                }

                nodo = nodo.darSiguiente( );
            }
        }
        return contiene;
    }

    /**
     * Borra todos los elementos de la lista
     * post: el primer elemento es nulo
     */
    public void clear( )
    {
        primero = null;

    }

    /**
     * Agrega todos los elementos de la colecci�n a la lista a partir de la posici�n indicada
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param pos la posici�n a partir de la cual se desean agregar los elementos
     * @param coleccion la colecci�n de elementos que se desea agregar
     * @return true si al menos uno de los elementos se agrega o false en caso contrario
     * @throws NullPointerException Si alguno de los elementos que se quiere agregar es null
     * @throws IndexOutOfBoundsException si indice < 0 o indice > size()
     */
    public boolean addAll( int pos, Collection<? extends T> coleccion )
    {

        int size0 = size( );
        for( T t : coleccion )
        {
            add( pos, t );
            pos++;
        }
        return size( ) > size0;
    }

    /**
     * Agrega a la lista todos los elementos de la colecci�n
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param coleccion la colecci�n de elementos que se desea agregar
     * @return true en caso que se agregue al menos un elemento false en caso contrario
     * @throws NullPointerException si alguno de los elementos que se desea agregar es null
     */
    public boolean addAll( Collection<? extends T> coleccion )
    {
        boolean modificado = false;
        for( T t : coleccion )
        {
            modificado |= add(t);
        }
        return modificado;
    }
}
