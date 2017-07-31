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

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoReiniciar;
import uniandes.cupi2.cupiLogo.mundo.IComando;

/**
 * Clase usada para verificar que los métodos de la clase Comando estén correctamente implementados.
 */
public class ComandoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Comando donde se harán las pruebas.
     */
    private IComando comando;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye un nuevo Comando Simple.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoReiniciar();
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase Comando. <br>
     * <b>Métodos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoReiniciar.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre debió inicializase correctamente.", ComandoReiniciar.COMANDO, comando.darNombre( ) );
    }
}