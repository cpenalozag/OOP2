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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoReiniciar;
import uniandes.cupi2.cupiLogo.mundo.ComandoSimple;

/**
 * Clase usada para verificar que los métodos de la clase ComandoSimple estén correctamente implementados.
 */
public class ComandoSimpleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Comando donde se harán las pruebas.
     */
    private ComandoSimple comando;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye un nuevo ComandoSimple.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoReiniciar( );
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase ComandoSimple. <br>
     * <b>Métodos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoReiniciar.
     */
    public void testComandoSimple( )
    {
        setupEscenario1( );
        assertEquals( "El nombre debió inicializase correctamente.", ComandoReiniciar.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el método guardar de la clase. <br>
     * <b>Métodos a probar:</b> <br>
     * darNombre<br>
     * guardar<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando cargado corresponde al nombre del comando guardado.
     */
    public void testGuardar( )
    {
        setupEscenario1( );

        String nombreArchivo = "test/data/pruebaComandoSimple.txt";
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
                assertEquals( "Debió haber guardado el comando simple correctamente.", comando.darNombre( ), linea );
            }
            else
            {
                fail( "El archivo está vacío." );
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