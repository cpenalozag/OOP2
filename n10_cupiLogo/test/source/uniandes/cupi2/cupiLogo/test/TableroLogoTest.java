/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiLogo.test;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoModificarPintando;
import uniandes.cupi2.cupiLogo.mundo.IComando;
import uniandes.cupi2.cupiLogo.mundo.PersistenciaException;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;
import uniandes.cupi2.cupiLogo.mundo.Tortuga;

/**
 * Clase usada para verificar que los métodos de la clase TableroLogo estén correctamente implementados.
 */
public class TableroLogoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * TableroLogo donde se harán las pruebas.
     */
    private TableroLogo tableroLogo;

    /**
     * Gráficas donde se harán las pruebas.
     */
    private Graphics2D graphics;
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye un nuevo TableroLogo vacío.
     */
    private void setupEscenario1( )
    {
    	JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = ( Graphics2D ) frame.getGraphics( );
        tableroLogo = new TableroLogo( 200, 200 );
        tableroLogo.darTortuga( ).reiniciarTortuga( );
    }

    /**
     * <b> Escenario 2 :</b> Construye un nuevo TableroLogo con una lista de comandos.
     */
    private void setupEscenario2( )
    {
    	JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = ( Graphics2D ) frame.getGraphics( );
        tableroLogo = new TableroLogo( 200, 200 );
        tableroLogo.darTortuga( ).reiniciarTortuga( );

        try
        {
            File archivo = new File( "test/data/pruebaCargar.txt" );
            tableroLogo.cargarComandos( archivo );
        }
        catch( Exception e )
        {
            fail( "No pudo cargar la información del archivo: " + e.getMessage( ) );
        }
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase TableroLogo. <br>
     * <b>Métodos a probar:</b> <br>
     * darAlto<br>
     * darAncho<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. El ancho, el alto y la tortuga fueron inicializados.
     */
    public void testTableroLogo( )
    {
        setupEscenario1( );

        assertEquals( "Se inicializó incorrectamente la altura del tablero", 200, tableroLogo.darAlto( ) );
        assertEquals( "Se inicializó incorrectamente el ancho del tablero", 200, tableroLogo.darAncho( ) );
        assertNotNull( "Se inicializó incorrectamente la tortuga del tablero", tableroLogo.darTortuga( ) );
    }

    /**
     * Prueba 2: Prueba el método inicializarTortuga de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * inicializarTortuga<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga se encuentra en la posición 0,0.
     */
    public void testInicializarTortuga( )
    {
        setupEscenario1( );

        tableroLogo.darTortuga( ).reiniciarTortuga( );

        Tortuga tortuga = tableroLogo.darTortuga( );
        assertEquals( "No se inicializó la tortuga en X", 100, tortuga.darXActual( ) );
        assertEquals( "No se inicializó la tortuga en Y", 100, tortuga.darYActual( ) );
    }

    /**
     * Prueba 3: Prueba el método agregarComando de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * agregarComando<br>
     * <b> Casos de prueba: <b><br>
     * 1. No hay comandos en la lista.
     */
    public void testAgregarComando( )
    {
        setupEscenario1( );

        IComando comando = new ComandoModificarPintando( 1 );
        tableroLogo.agregarComando( comando );

        assertEquals( "Debió cambiar el tamaño de la lista de comandos.", 1, tableroLogo.darComandos( ).size( ) );
    }

    /**
     * Prueba 4: Prueba el método ejecutarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * ejecutarComandos<br>
     * <b> Casos de prueba: <b><br>
     * 1. No hay comandos en la lista.
     */
    public void testEjecutarComandos1( )
    {
        setupEscenario1( );
        Tortuga tortuga = tableroLogo.darTortuga( );

        int antesX = tortuga.darXActual( );
        int antesY = tortuga.darYActual( );

        IComando comando = new ComandoModificarPintando( ComandoModificarPintando.NO_PINTA );
        tableroLogo.agregarComando( comando );

        tableroLogo.ejecutarComandos( graphics );

        assertEquals( "No debería haber cambiado de posición en X.", antesX, tortuga.darXActual( ) );
        assertEquals( "No debería haber cambiado de posición en Y.", antesY, tortuga.darYActual( ) );
        assertEquals( "Debío haber cambiado el estado de pintando.", false, tortuga.estaPintando( ) );
    }

    /**
     * Prueba 5: Prueba el método ejecutarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * ejecutarComandos<br>
     * <b> Casos de prueba: </b><br>
     * 1. Comandos cargados de un archivo.
     */
    public void testEjecutarComandos2( )
    {
        setupEscenario2( );

        Tortuga tortuga = tableroLogo.darTortuga( );

        int antesX = tortuga.darXActual( ) + 50;
        int antesY = tortuga.darYActual( ) + 50;
        tableroLogo.ejecutarComandos( graphics );
        assertEquals( "Debió haber cambiado de posición en X.", antesX, tortuga.darXActual( ) );
        assertEquals( "Debió haber cambiado de posición en Y.", antesY, tortuga.darYActual( ) );
        System.out.println( tortuga.estaPintando( ) );
        assertEquals( "Debío haber cambiado el estado del pintando.", false, tortuga.estaPintando( ) );
    }

    /**
     * Prueba 6: Prueba el método ejecutarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * verificarMovmiento<br>
     * <b> Casos de prueba: </b><br>
     * 1. Comandos cargados de un archivo.
     */
    public void testEjecutarComandos3( )
    {
        setupEscenario2( );
        boolean verifica = tableroLogo.verificarMovimiento( 3000, 0 );
        assertFalse( "No debe ser un movimiento válido.", verifica );
    }

    /**
     * Prueba 7: Prueba el método ejecutarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * verificarGiro<br>
     * <b> Casos de prueba: </b><br>
     * 1. Comandos cargados de un archivo.
     */
    public void testEjecutarComandos4( )
    {
        setupEscenario2( );
        boolean verifica = tableroLogo.verificarGiro( 361, 0 );
        assertFalse( "No debe ser un giro válido.", verifica );
    }

    /**
     * Prueba 8: Prueba el método ejecutarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * verificarEscala<br>
     * <b> Casos de prueba: </b><br>
     * 1. Comandos cargados de un archivo.
     */
    public void testEjecutarComandos5( )
    {
        setupEscenario2( );
        boolean verifica = tableroLogo.verificarEscala( 3.1, 0 );
        assertFalse( "No debe ser una escala válido.", verifica );
    }

    /**
     * Prueba 9: Prueba el método eliminarUltimoComando de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * eliminarUltimoComando<br>
     * darTortuga<br>
     * ejecutarComandos<br>
     * <b> Casos de prueba: </b><br>
     * 1. Comandos cargados de un archivo.
     */
    public void testEliminarUltimoComando( )
    {
        setupEscenario2( );

        tableroLogo.eliminarUltimoComando( );

        Tortuga tortuga = tableroLogo.darTortuga( );

        int antesX = tortuga.darXActual( ) + 50;
        int antesY = tortuga.darYActual( );
        tableroLogo.ejecutarComandos( graphics );
        assertEquals( "Debió haber cambiado de posición en X.", antesX, tortuga.darXActual( ) );
        assertEquals( "debió haber cambiado de posición en Y.", antesY, tortuga.darYActual( ) );
        assertEquals( "debió haber cambiado de orientación.", 90, tortuga.darOrientacion( ), 0.01 );
        assertEquals( "Debío haber cambiado el estado del lápiz.", false, tortuga.estaPintando( ) );
    }

    /**
     * Prueba 9: Prueba el método guardarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * guardarComandos<br>
     * <b> Casos de prueba: </b><br>
     * 1. Hay comandos cargados de un archivo.
     */
    public void testGuardarComandos( )
    {
        setupEscenario2( );

        String nombreArchivo = "test/data/pruebaGuardar.txt";
        File archivo = new File( nombreArchivo );
        try
        {
            tableroLogo.guardarComandos( archivo );

            BufferedReader br = new BufferedReader( new FileReader( nombreArchivo ) );

            String linea = br.readLine( );
            int i = 0;

            assertEquals( "El número de comandos en la lista debe ser el mismo que en el archivo.", Integer.parseInt( linea ), tableroLogo.darComandos( ).size( ) );

            linea = br.readLine( );
            while( linea != null )
            {
                IComando comando = ( IComando )tableroLogo.darComandos( ).get( i );

                assertTrue( "La linea en el archivo debe comenzar con el nombre del comando.", linea.startsWith( comando.darNombre( ) ) );

                linea = br.readLine( );
                i++;
            }
            br.close();
        }
        catch( PersistenciaException e )
        {
            fail( "Debió haber guardado los comandos: " + e.getMessage( ) );
        }
        catch( FileNotFoundException e )
        {
            fail( "El archivo se debió haber creado." );
        }
        catch( IOException e )
        {
            fail( "Error en la lectura del archivo." );
        }
        catch( IndexOutOfBoundsException e )
        {
            fail( "El archivo tiene más comandos que la lista." );
        }
    }

    /**
     * Prueba 10: Prueba el método cargarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * cargar<br>
     * <b> Casos de prueba: </b><br>
     * 1. Hay comandos cargados de un archivo.
     */
    public void testCargarComandos1( )
    {
        setupEscenario1( );

        String nombreArchivo = "test/data/pruebaCargar.txt";
        File archivo = new File( nombreArchivo );
        try
        {
            tableroLogo.cargarComandos( archivo );

            BufferedReader br = new BufferedReader( new FileReader( nombreArchivo ) );

            String linea = br.readLine( );
            int i = 0;

            assertEquals( "El número de comandos en la lista debe ser el mismo que en el archivo", Integer.parseInt( linea ), tableroLogo.darComandos( ).size( ) );

            linea = br.readLine( );
            while( linea != null )
            {
                IComando comando = ( IComando )tableroLogo.darComandos( ).get( i );

                assertTrue( "La linea en el archivo debe comenzar con el nombre del comando", linea.startsWith( comando.darNombre( ) ) );

                linea = br.readLine( );
                i++;
            }
            br.close();
        }
        catch( PersistenciaException e )
        {
            fail( "Debió haber guardado los comandos: " + e.getMessage( ) );
        }
        catch( FileNotFoundException e )
        {
            fail( "El archivo se debió haber creado" );
        }
        catch( IOException e )
        {
            fail( "Error en la lectura del archivo" );
        }
        catch( IndexOutOfBoundsException e )
        {
            fail( "El archivo tiene más comandos que la lista" );
        }
    }

    /**
     * Prueba 11: Prueba el método cargarComandos de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * cargarComandos<br>
     * <b> Casos de prueba: </b><br>
     * 1. Hay comandos cargados de un archivo.
     */
    public void testCargarComandos2( )
    {
        setupEscenario1( );

        String nombreArchivo = "test/data/prueba-falla.txt";
        File archivo = new File( nombreArchivo );
        try
        {
            tableroLogo.cargarComandos( archivo );
            fail( "Debió Lanzar excepción porque el archivo no existe" );
        }
        catch( Exception e )
        {
            // debe lanzar excepción
        }
    }
}