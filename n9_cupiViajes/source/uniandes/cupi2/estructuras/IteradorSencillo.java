package uniandes.cupi2.estructuras;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Clase que permite iterar sobre una lista secinllamente encadenada
 * @author alvar-go
 * @param <T>
 */
public class IteradorSencillo<T extends IdentificadoUnicamente> implements Iterator<T>, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * El nodo donde se encuentra el iterado
     */
    private NodoListaSencilla<T> actual;
    
    /**
     * Crea un nuevo iterador iniciando en el nodo indicado
     * @param nActual el nodo donde se desea que inicie el iterador
     */
    public IteradorSencillo(NodoListaSencilla<T> nActual)
    {
        actual = nActual;
    }

    /**
     * Indica si aún hay elementos por recorrer
     * @return true en caso de que aún haya elemetos o false en caso contrario
     */
    public boolean hasNext( )
    {
        return actual != null;
    }

    /**
     * Devuelve el siguiente elemento a recorrer
     * post: se actualizado actual al siguiente del actual
     * @return objeto en actual
     */
    public T next( )
    {
        T valor = actual.darElemento( );
        actual = actual.darSiguiente( );
        return valor;
    }


}
