package uniandes.cupi2.estructuras.test;

import junit.framework.TestCase;
import uniandes.cupi2.estructuras.IdentificadoUnicamente;
import uniandes.cupi2.estructuras.NodoListaSencilla;

public class NodoSencilloTest extends TestCase
{
    private NodoListaSencilla<ElementoBasico> nodo;
    
    protected void setUp()
    {
        nodo = new NodoListaSencilla<ElementoBasico>( new ElementoBasico( "A" ) );
    }
    
    public void testDarIdentificador()
    {
        assertEquals( "Identificador incorrecto", "A" , nodo.darIdentificador( ));
    }
    
    public void testDarSiguienteCambiarSiguientes()
    {
        assertNull( "No debe tener siguiente", nodo.darSiguiente( ) );
        NodoListaSencilla<ElementoBasico> nodo2 = new NodoListaSencilla<ElementoBasico>( new ElementoBasico( "B" ) );
        nodo.cambiarSiguiente( nodo2 );
        assertNotNull( "Debe tener siguiente", nodo.darSiguiente( ) );
        assertEquals( "El siguiente debe ser B", "B", nodo.darSiguiente( ).darIdentificador( ) );
    }
    
    public void testCambiarElemento()
    {
        assertNotNull( "Debe tener elemento", nodo.darElemento( ) );
        assertEquals( "El elemento debe ser A", "A", nodo.darElemento( ).darIdentificador( ) );
    }
    
    private class ElementoBasico implements IdentificadoUnicamente
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        
        private String valor;
        
        public ElementoBasico( String nValor )
        {
           valor = nValor;
        }
        
        public String darIdentificador( )
        {
            return valor;
        }
        
    }
}
