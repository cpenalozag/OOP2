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

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoCambiarColor;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;

/**
 * Clase usada para verificar que los métodos de la clase ComandoCambiarColor estén correctamente implementados.
 */
public class ComandoCambiarColorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ComandoCambiarColor donde se harán las pruebas.
     */
    private ComandoCambiarColor comando;

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
     *  <b> Escenario 1 :</b> Construye un nuevo ComandoCambiarColor.
     */
    private void setupEscenario1( )
    {
        Color color = new Color( 13, 200, 130 );

        comando = new ComandoCambiarColor( color.getRGB( ) );
        tablero = new TableroLogo( 100, 100 );

        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase ComandoCambiarColor. <br>
     * <b>Métodos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoCambiarColor.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando no es el correcto.", ComandoCambiarColor.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el método ejecutar de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. El color de graphics cambió.
     */
    public void testEjecutar( )
    {
        setupEscenario1( );

        graphics.setColor( Color.BLACK );

        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertEquals( "El comando debió modificar el color del tablero", graphics.getColor( ), new Color( 13, 200, 130 ) );
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

        String nombreArchivo = "test/data/pruebaCambiarColor.txt";
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
                Color color = new Color( 13, 200, 130 );
                String textoComando = comando.darNombre( ) + " " + color.getRGB( );
                assertEquals( "Debió haber guardado el comando simple correctamente.", textoComando, linea );
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
            fail( "Falló en la lectura del archivo." );
        }
    }
}