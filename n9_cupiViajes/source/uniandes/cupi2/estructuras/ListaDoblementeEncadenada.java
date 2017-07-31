package uniandes.cupi2.estructuras;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ListaDoblementeEncadenada <T extends IdentificadoUnicamente> extends ListaEncadenadaAbstracta<T> implements List<T>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construye una lista vacia
     * post: se ha inicializado el primer nodo en null
     */
    public ListaDoblementeEncadenada()
    {
        primero = null;
    }

    /**
     * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�mentro
     * @param nPrimero el elemento a guardar en el primer nodo
     * @throws NullPointerException si el elemento recibido es nulo
     */
    public ListaDoblementeEncadenada(T nPrimero)
    {
        if(nPrimero == null)
        {
            throw new NullPointerException();
        }
        primero = new NodoListaDoble<T>( nPrimero );
    }

    /**
     * Agrega un elemento al final de la lista
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param elem el elemento que se desea agregar.
     * @return true en caso que se agregue el elemento o false en caso contrario. 
     * @throws NullPointerException si el elemento es nulo
     */
    public boolean add( T elem ) throws NullPointerException
    {
        boolean resp = false;
        boolean mismo = false;
        NodoListaDoble<T> nuevo = new NodoListaDoble<T>( elem );
        if ( elem == null )
        {
            throw new NullPointerException( );
        }
        else{
            
            if ( primero == null )
            {
                primero = nuevo;
                resp = true;
            }
            else{
                NodoListaDoble<T> x = ( NodoListaDoble<T> )primero;

                while ( x.darSiguiente( ) != null )
                {
                    if ( x.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ) )
                    {
                        mismo = true;
                        resp = false;
                        
                    }
                    x = ( NodoListaDoble<T> )x.darSiguiente( );
                }
                if ( !x.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ) )
                {
                    x.cambiarSiguiente( nuevo );
                    nuevo.cambiarAnterior( x );
                    resp = true;
                }
            }
        }
        return resp;
    }


    /**
     * Agrega un elemento en la posici�n dada de la lista. Todos los elementos siguientes se desplazan
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param pos la posici�n donde se desea agregar. Si pos es igual al tama�o de la lista se agrega al final
     * @param elem el elemento que se desea agregar
     * @throws IndexOutOfBoundsException si el inidice es < 0 o > size()
     * @throws NullPointerException Si el elemento que se quiere agregar es null.
     */
    public void add( int pos, T elem )
    {
        if(elem == null)
        {
            throw new NullPointerException( );
        }
        NodoListaSencilla<T> nuevo = new NodoListaSencilla<T>( elem );
        if(!contains( elem ))
        {

            if(pos == 0)
            {
                nuevo.cambiarSiguiente( primero );
                primero = nuevo;
            }
            else
            {
                NodoListaSencilla<T> n = primero;
                int posActual = 0;
                while( posActual < (pos-1) && n != null )
                {
                    posActual++;
                    n = n.darSiguiente( );
                }
                if(posActual != (pos-1))
                {
                    throw new IndexOutOfBoundsException( );
                }
                nuevo.cambiarSiguiente( n.darSiguiente( ) );
                n.cambiarSiguiente( nuevo );
            }
        }
    }


    /**
     * Devuelve un nuevo iterador de lista que inicie en el primer nodo
     * @return iterador
     */
    public ListIterator<T> listIterator( )
    {
        return new IteradorDeLista<T>( (NodoListaDoble<T>)primero );
    }

    /**
     * Devuelve un nuevo iterador de lista que inicie en nodo de la posici�n que llega por par�metro
     * @return iterador 
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public ListIterator<T> listIterator( int pos )
    {
        return new IteradorDeLista<T>( (NodoListaDoble<T>)(getNodo(pos)) );
    }

    /**
     * Elimina el nodo que contiene al objeto que llega por par�metro
     * @param objeto el objeto que se desea eliminar. objeto != null
     * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
     */
    public boolean remove( Object objeto )
    {
        boolean elimino = false;
        NodoListaSencilla<T> x = primero;
        if (primero.darElemento( ).equals( objeto ))
        {
            primero = x.darSiguiente( );
            elimino = true;
        }
        else{
            while ( x.darSiguiente( )!=null )
            {
                if ( x.darSiguiente( ).darElemento( ).equals( objeto ))
                {
                    x.cambiarSiguiente( x.darSiguiente( ).darSiguiente( ) );
                    elimino = true;
                }
            }

        }
        return elimino;
    }

    /**
     * Elimina el elemento de la posici�n dada
     * @param pos la posici�n que se desea eliminar
     * @return el elemento eliminado o null en caso que no exista la posici�n que se desea eliminar
     */
    public T remove( int pos )
    {
        T respuesta = null;
        NodoListaSencilla<T> x = primero;

        if (pos<0||pos>=size( ))
        {
            throw new IndexOutOfBoundsException( );
        }

        else if (pos == 0)
        {
            respuesta = ( T )primero;
        }
        else{
            int i = 1;
            while(i<size() && x.darSiguiente( )!=null)
            {
                x = x.darSiguiente( );
                if (i == pos)
                {
                    respuesta = (T)x;
                }
            }

        }
        return respuesta;
    }

    /**
     * Deja en la lista solo los elementos que est�n en la colecci�n que llega por par�metro
     * @param coleccion la colecci�n de elementos a mantener. coleccion != null
     * @return true en caso que se modifique (eliminaci�n) la lista o false en caso contrario
     */
    public boolean retainAll( Collection<?> coleccion )
    {
        boolean modifico = false;
        NodoListaDoble<T> act = ( NodoListaDoble<T> )primero.darSiguiente( );
        for ( int i=0; i<size( ); i++ )
        {
            if (coleccion.contains( primero.darElemento( ) )==false)
            {
                primero = primero.darSiguiente( );
                modifico = true;
            }
            else{
                if (coleccion.contains( act.darElemento( ) )==false)
                {
                    (act.darAnterior( )).cambiarSiguiente( (act.darAnterior( )).darSiguiente( ).darSiguiente( ) );
                    modifico = true;
                }
            }
        }
        return modifico;
    }

    /**
     * Crea una lista con los elementos de la lista entre las posiciones dadas
     * @param inicio la posici�n del primer elemento de la sublista. Se incluye en la sublista
     * @param fin la posici�n del �tlimo elemento de la sublista. Se excluye en la sublista
     * @return una lista con los elementos entre las posiciones dadas
     * @throws IndexOutOfBoundsException Si inicio < 0 o fin >= size() o fin < inicio
     */
    public List<T> subList( int inicio, int fin )
    {
        List<T> subLista = ( List<T> )new java.awt.List( );
        if (inicio<0 || fin>=size( ))
        {
            throw new IndexOutOfBoundsException( );
        }
        else{
            int i=0;
            NodoListaSencilla<T> actual = primero;
            while( actual.darSiguiente( )!=null && i<fin)
            {
                if (i>=inicio)
                {
                    subLista.add( ( T )actual );
                }
                actual=actual.darSiguiente( );
            }
        }
        return subLista;
    }
}
