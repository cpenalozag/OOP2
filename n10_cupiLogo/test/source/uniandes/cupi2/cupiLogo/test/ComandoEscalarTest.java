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
import java.io.PrintWriter;

import javax.swing.JFrame;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoEscalar;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;

/**
 * Clase usada para verificar que los métodos de la clase ComandoEscalar estén correctamente implementados.
 */
public class ComandoEscalarTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ComandoEscalar donde se harán las pruebas.
     */
    private ComandoEscalar comando;

    /**
     * TableroLogo donde se harán las pruebas.
     */
    private TableroLogo tablero;
    
    /**
     * Gráficas donde se harán las pruebas.
     */
    private Graphics2D graphics;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye un nuevo ComandoEscalar.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoEscalar( 2.0, ComandoEscalar.AGRANDAR );
        tablero = new TableroLogo( 100, 100 );

        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase ComandoEscalar. <br>
     * <b>Métodos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoEscalar.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando no es el correcto.", ComandoEscalar.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el método ejecutar de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La escala de la tortuga cambió.
     */
    public void testEjecutar( )
    {
        setupEscenario1( );
        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertEquals( "El comando debió modificar la escala de la tortuga.", tablero.darTortuga( ).darEscala( ), 2.0, 0.1 );
    }

    /**
     * Prueba 3: Prueba el método guardar de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * guardar<br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando cargado corresponde al nombre del comando guardado.
     */
    public void testGuardarComando( )
    {
        setupEscenario1( );

        String nombreArchivo = "test/data/pruebaComandoEscalar.txt";
        File archivo = new File( nombreArchivo );

        PrintWriter pw;
        try
        {
            pw = new PrintWriter( archivo );
            comando.guardar( pw );
            pw.close( );

        }
        catch( FileNotFoundException e )
        {
            fail( "Falló en la creación y escritura del archivo." );
        }

        BufferedReader br;

        try
        {
            br = new BufferedReader( new FileReader( archivo ) );

            String linea = br.readLine( );
            if( linea != null )
            {
                String textoComando = comando.darNombre( ) + " 2.0" + " 0";
                assertEquals( "Debió haber guardado el comando correctamente.", textoComando, linea );
            }
            else
            {
                fail( "El archivo está vacio." );
            }
        }
        catch( FileNotFoundException e )
        {
            fail( "Falló en  encontrar el archivo." );
        }
        catch( IOException e )
        {
            fail( "Falló en la lectura del archivo" );
        }
        archivo.delete( );
    }
}