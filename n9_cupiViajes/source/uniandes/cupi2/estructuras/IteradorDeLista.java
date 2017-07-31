package uniandes.cupi2.estructuras;

import java.io.Serializable;
import java.util.ListIterator;

public class IteradorDeLista<T extends IdentificadoUnicamente> implements ListIterator<T>, Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Nodo en que se encuentra el iterador
     */
    private NodoListaDoble<T> actual;
    
    /**
     * Crea un unevo iterador de lista iniciando en el nodo que llega por par�metro
     * @param nActual el nodo en el que inicia el iterador. nActual != null
     */
    public IteradorDeLista(NodoListaDoble<T> nActual)
    {
        actual = nActual;
    }

    /**
     * Indica si hay nodo siguiente
     * true en caso que haya nodo siguiente o false en caso contrario
     */
    public boolean hasNext( )
    {
        if ( actual.darSiguiente( ) != null )
        {
            return true;
        }
        else{
            return false;
        } 
    }

    /**
     * Indica si hay nodo anterior
     * true en caso que haya nodo anterior o false en caso contrario
     */
    public boolean hasPrevious( )
    {
        if ( actual.darAnterior( ) != null )
        {
            return true;
        }
        else{
            return false;
        } 
    }

    /**
     * Devuelve el elemento del nodo siguiente
     * @return elemento del nodo siguiente
     */
    public T next( )
    {
        return actual.darSiguiente( ).darElemento( );
    }

    
    /**
     * Devuelve el elemento del nodo anterior
     * @return elemento del nodo anterior
     */
    public T previous( )
    {
     return actual.darAnterior( ).darElemento( );
    }
    
    //============================================
    //  Los siguientes m�todos no se implementan
    //=============================================

    public int nextIndex( )
    {
        throw new UnsupportedOperationException();
    }

    public int previousIndex( )
    {
        throw new UnsupportedOperationException();
    }

    public void remove( )
    {
        throw new UnsupportedOperationException();
    }


    public void set( T e )
    {
        throw new UnsupportedOperationException();
    }
    
    public void add( T e )
    {
        throw new UnsupportedOperationException();
    }

}
