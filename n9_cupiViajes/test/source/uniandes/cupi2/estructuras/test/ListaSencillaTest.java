package uniandes.cupi2.estructuras.test;

import junit.framework.TestCase;
import uniandes.cupi2.estructuras.IdentificadoUnicamente;
import uniandes.cupi2.estructuras.ListaSencillamenteEncadenada;

public class ListaSencillaTest extends TestCase
{
protected ListaSencillamenteEncadenada<ElementoBasico> lista;
    
    
    public void testAdd()
    {
        boolean resp = lista.add( new ElementoBasico( "A" ) );
        assertTrue( resp );
        resp = lista.add( new ElementoBasico( "A" ) );
        assertFalse( resp );
        lista.add( new ElementoBasico( "B" ) );
        lista.add( new ElementoBasico( "C" ) );
        lista.add( new ElementoBasico( "D" ) );
        ElementoBasico elem = lista.get( 2 );
        assertEquals( "E", elem.darIdentificador( ) );
    }

    public void testClear( )
    {
        lista.add( new ElementoBasico( "B" ) );
        lista.add( new ElementoBasico( "C" ) );
        lista.add( new ElementoBasico( "D" ) );
        
        lista.clear( );
        
        assertTrue( lista.isEmpty( ) );
        
    }

    public void testContains( )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        assertFalse( lista.contains( a ) );
        lista.add( a );
        assertTrue( lista.contains( a ) );
    }

    public void testGet(  )
    {
        try
        {
            lista.get( -2 );
            fail("Debe lanzar la excepci�n");
        }
        catch( Exception e )
        {
            //Debe lanzar la excepci�n
        }
        try
        {
            lista.get(100);
            fail("Debe lanzar la excepci�n");
        }
        catch( Exception e )
        {
            //Debe lanzar la excepci�n
        }
        
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        lista.add( b );
        lista.add( c );
        lista.add( d );
        
        assertEquals( "C",lista.get( 1 ).darIdentificador( ));
    }

    public void testIndexOf( )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        lista.add( b );
        lista.add( c );
        lista.add( d );
        
        assertEquals( -1, lista.indexOf( a) );
        assertEquals( 1, lista.indexOf( c) );

    }

    public void testIsEmpty( )
    {
       assertTrue( lista.isEmpty( ) );
       ElementoBasico d = new ElementoBasico( "D" );
       lista.add( d );
       assertFalse( lista.isEmpty( ) );
       
    }


    public void testRemove( )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        lista.add( a );
        lista.add( b );
        lista.add( c );
        lista.add( d );
        
        assertEquals( 4, lista.size( ) );
        
        ElementoBasico r = new ElementoBasico(null);
        assertEquals (a, r = lista.removerPrimero());
        assertEquals( 3, lista.size( ) );
        
        ElementoBasico s = new ElementoBasico(null);
        assertEquals (b, s = lista.removerPrimero());
        assertEquals( 2, lista.size( ) );
        
        ElementoBasico t = new ElementoBasico(null);
        assertEquals (c, t = lista.removerPrimero());
        assertEquals( 1, lista.size( ) );
        
        lista.removerPrimero();
        
        assertEquals( 0, lista.size( ) );
        
    }

    public void testSize( )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        
        assertEquals( 0, lista.size( ) );
        
        lista.add( a );
        lista.add( b );
        lista.add( c );
        lista.add( d );
        
        assertEquals( 4, lista.size( ) );
        
        lista.removerPrimero();
        
        assertEquals( 3, lista.size( ) );
        
    }
    
    protected class ElementoBasico implements IdentificadoUnicamente
    {
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
