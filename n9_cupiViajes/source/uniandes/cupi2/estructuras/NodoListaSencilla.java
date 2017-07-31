package uniandes.cupi2.estructuras;

import java.io.Serializable;

/**
 * Nodo de una lista sencillamente encadenada
 * @author alvar-go
 *
 * @param <T>
 */
public class NodoListaSencilla<T extends IdentificadoUnicamente> implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    

    /**
     * Elemento alamacenado en el nodo
     */
    protected T elemento;
    
    /**
     * Siguiente nodo
     */
    protected NodoListaSencilla<T> siguiente;
    
    /**
     * Crea un nuevo nodo
     * @param nElemento el elemento a almacenar en el nodo. nElemento != null
     */
    public NodoListaSencilla(T nElemento)
    {
        elemento = nElemento;
        siguiente=null;
    }
    
    /**
     * Cambia el nodo siguiente
     * post: Se ha cambiado el nodo siguiente
     * @param nSiguiente el nuevo elemento siguiente
     */
    public void cambiarSiguiente(NodoListaSencilla<T> nSiguiente)
    {
        siguiente = nSiguiente;
    }
    
    /**
     * Devuelve el elemento almacenado en el nodo
     * @return elemento
     */
    public T darElemento()
    {
        return elemento;
    }
    
    /**
     * Cambia el elemento almacenado en el nodo
     * @param nElemento el nuevo elemento a almacenar en el nodo
     */
    public void cambiarElemento(T nElemento)
    {
        elemento = nElemento;
    }
    
    /**
     * Devuelve el identificador del nodo
     * Corresponde al identificador del elemento almacenado
     * @return identificador
     */
    public String darIdentificador()
    {
        return elemento.darIdentificador( );
    }
    
    /**
     * Devuelve el siguiente nodo
     * @return siguiente
     */
    public NodoListaSencilla<T> darSiguiente()
    {
        return siguiente;
    }
   
    
}
