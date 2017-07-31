package uniandes.cupi2.estructuras.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.estructuras.IdentificadoUnicamente;

public abstract class ListaAbstractaTest extends TestCase
{

    protected List<ElementoBasico> lista;
    
    
    public void testAdd()
    {
        boolean resp = lista.add( new ElementoBasico( "A" ) );
        assertTrue( resp );
        resp = lista.add( new ElementoBasico( "A" ) );
        assertFalse( resp );
        lista.add( new ElementoBasico( "B" ) );
        lista.add( new ElementoBasico( "C" ) );
        lista.add( new ElementoBasico( "D" ) );
        lista.add( 2, new ElementoBasico( "E" ) );
        IdentificadoUnicamente elem = lista.get( 2 );
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
            fail("Debe lanzar la excepción");
        }
        catch( Exception e )
        {
            //Debe lanzar la excepción
        }
        try
        {
            lista.get(100);
            fail("Debe lanzar la excepción");
        }
        catch( Exception e )
        {
            //Debe lanzar la excepción
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
        assertEquals( 1, lista.indexOf( b ) );
        
        lista.remove( b );
        
        assertEquals( 3, lista.size( ) );
        assertEquals( -1, lista.indexOf( b ) );
        
        lista.remove( 1 );
        
        assertEquals( 2, lista.size( ) );
        assertEquals( -1, lista.indexOf( c ) );
        
    }

    public void testRetainAll(  )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        lista.add( a );
        lista.add( b );
        lista.add( c );
        lista.add( d );
        
        ArrayList<IdentificadoUnicamente> retener = new ArrayList<IdentificadoUnicamente>( );
        retener.add( a );
        retener.add( c );
        
        assertEquals( 4, lista.size( ) );
        assertEquals( 1, lista.indexOf( b ) );
        
        lista.retainAll( retener );
        
        assertEquals( 2, lista.size( ) );
        assertEquals( -1, lista.indexOf( b ) );
        assertEquals( 1, lista.indexOf( c ) );
        
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
        
        lista.remove( 0 );
        
        assertEquals( 3, lista.size( ) );
        
    }
    
    protected class ElementoBasico implements IdentificadoUnicamente
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
