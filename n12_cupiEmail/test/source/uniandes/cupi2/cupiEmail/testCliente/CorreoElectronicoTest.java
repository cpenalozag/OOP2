/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiEmail.testCliente;

import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoElectronico;
import junit.framework.TestCase;

/**
 * Clase que verifica la correcta implementación de CorreoElectronico.
 */
public class CorreoElectronicoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase sobre la que se realizan las pruebas.
     */
    private CorreoElectronico correoElectronico;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Crea un correo electrónico.
     */
    private void setupEscenario1( )
    {
        correoElectronico = new CorreoElectronico( "amigo1", "02-05-2009 05:28", "asunto", "hola", false );
    }

    /**
     * Prueba 1: Verifica el constructor de la clase <br>
     * <b> Métodos a probar: </b> <br>
     * CorreoElectronico <br>
     * darAsunto<br>
     * darEstado<br>
     * darFechaEnvio<br>
     * darMensaje<br>
     * darLoginRemitente<br> <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Al crear un CorreoElectronico y pedir su información, esta debe ser retornada de forma correcta.
     */
    public void testCorreoElectronico( )
    {
        setupEscenario1( );

        assertNotNull( "El asunto del correo no fue inicializado", correoElectronico.darAsunto( ) );
        assertEquals( "El asunto del correo no es correcto", "asunto", correoElectronico.darAsunto( ) );
        assertNotNull( "El estado del correo no fue inicializado", correoElectronico.darEstado( ) );
        assertEquals( "El estado del correo no es correcto", false, correoElectronico.darEstado( ) );
        assertNotNull( "La fecha del correo no fue inicializada", correoElectronico.darFechaEnvio( ) );
        assertEquals( "La fecha del correo no es correcta", "02-05-2009 05:28", correoElectronico.darFechaEnvio( ) );
        assertNotNull( "El texto del correo no fue inicializado", correoElectronico.darMensaje( ) );
        assertEquals( "El texto del correo no es correcto", "hola", correoElectronico.darMensaje( ) );
        assertNotNull( "El usuario no fue inicializado", correoElectronico.darLoginRemitente( ) );
        assertEquals( "El usuario destinatario del correo no es correcto", "amigo1", correoElectronico.darLoginRemitente( ) );
    }

    /**
     * Prueba 2: Verifica el método compararPorFecha de la clase <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorFechaEnvio <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Al comparar con un correo de fecha mayor la respuesta debe ser -1
     */
    public void testCompararPorFecha1( )
    {
        setupEscenario1( );

        CorreoElectronico correo = new CorreoElectronico( "amigo2", "01-08-2010 07:59", "asunto", "hola", true );
        assertEquals( "Respuesta incorrecta", -1, correoElectronico.compararPorFechaEnvio( correo ) );
    }

    /**
     * Prueba 3: Verifica el método compararPorFecha de la clase <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorFechaEnvio <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Al comparar con un correo de fecha menor la respuesta debe ser 1
     */
    public void testCompararPorFecha2( )
    {
        setupEscenario1( );

        CorreoElectronico correo = new CorreoElectronico( "amigo2", "01-02-2009 07:59", "asunto", "hola", true );
        assertEquals( "Respuesta incorrecta", 1, correoElectronico.compararPorFechaEnvio( correo ) );
    }

    /**
     * Prueba 4: Verifica el método compararPorFecha de la clase <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorFechaEnvio <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Al comparar con un correo con la misma fecha, la respuesta debe ser 0
     */
    public void testCompararPorFecha3( )
    {
        setupEscenario1( );

        CorreoElectronico correo = new CorreoElectronico( "amigo2", "02-05-2009 05:28", "asunto", "hola", true );
        assertEquals( "Respuesta incorrecta", 0, correoElectronico.compararPorFechaEnvio( correo ) );
    }
}
