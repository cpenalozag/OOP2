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

import javax.swing.JFrame;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoModificarPintando;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;

/**
 * Clase usada para verificar que los métodos de la clase ComandoActivarTrayectoria estén correctamente implementados.
 */
public class ComandoModificarPintandoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ComandoActivarTrayectoria donde se harán las pruebas.
     */
    private ComandoModificarPintando comando;

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
     * <b> Escenario 1 :</b> Construye un nuevo Comando para Activar la Trayectoria que activa la trayectoria.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoModificarPintando( ComandoModificarPintando.PINTA );
        tablero = new TableroLogo( 100, 100 );
        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * <b> Escenario 2 :</b> Construye un nuevo ComandoActivarTrayectoria que desactiva la trayectoria.
     */
    private void setupEscenario2( )
    {
        comando = new ComandoModificarPintando( ComandoModificarPintando.NO_PINTA );
        tablero = new TableroLogo( 100, 100 );
        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase ComandoActivarTrayectoria. <br>
     * <b>Métodos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoActivarTrayectoria.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando no es el correcto.", ComandoModificarPintando.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el método ejecutar de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga no se encuentra pintando la trayectoria..
     */
    public void testEjecutar1( )
    {
        setupEscenario1( );

        tablero.darTortuga( ).modificarPintando( false );

        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertTrue( "El comando debió modificar el estado de la tortuga", tablero.darTortuga( ).estaPintando( ) );
    }

    /**
     * Prueba 3: Prueba el método ejecutar de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga se encuentra pintando la trayectoria.
     */
    public void testEjecutar2( )
    {
        setupEscenario2( );

        tablero.darTortuga( ).modificarPintando( true );

        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertFalse( "El comando debió modificar el estado de la tortuga", tablero.darTortuga( ).estaPintando( ) );
    }
}