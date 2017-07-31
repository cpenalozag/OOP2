package uniandes.cupi2.estructuras;

public class NodoListaDoble<T extends IdentificadoUnicamente> extends NodoListaSencilla<T>
{
   
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Nodo anterior
     */
    private NodoListaDoble<T> anterior;
    
    /**
     * Construye un nuevo nodo doblemente encadenado
     * @param elem el elemento que almacena el nodo
     */
    public NodoListaDoble (T elem)
    {
        super(elem);
        anterior = null;
    }
    
    /**
     * Devuelve el nodo anterior
     * @return anterior
     */
    public NodoListaDoble<T> darAnterior()
    {
        return anterior;
    }

    /**
     * Modifica el nodo anterior 
     * @param ant el nuevo nodo anteiror.
     */
    public void cambiarAnterior( NodoListaDoble<T>  ant )
    {
        anterior = ant;
    }
    
}
