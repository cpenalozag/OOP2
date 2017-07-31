package uniandes.cupi2.estructuras.test;

import junit.framework.TestCase;
import uniandes.cupi2.estructuras.IdentificadoUnicamente;
import uniandes.cupi2.estructuras.NodoListaDoble;
import uniandes.cupi2.estructuras.NodoListaSencilla;

public class NodoDobleTest extends TestCase
{
    private NodoListaDoble<ElementoBasico> nodo;
    
    protected void setUp()
    {
        nodo = new NodoListaDoble<ElementoBasico>( new ElementoBasico( "A" ) );
    }
    
    public void testDarAnteriorCambiarAnterior()
    {
        assertNull( "No debe tener anterior", nodo.darAnterior( ) );
        NodoListaDoble<ElementoBasico> nodo2 = new NodoListaDoble<ElementoBasico>( new ElementoBasico( "B" ) );
        nodo.cambiarAnterior( nodo2 );
        assertNotNull( "Debe tener anterior", nodo.darAnterior( ) );
        assertEquals( "El anterior debe ser B", "B", nodo.darAnterior( ).darIdentificador( ) );
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
