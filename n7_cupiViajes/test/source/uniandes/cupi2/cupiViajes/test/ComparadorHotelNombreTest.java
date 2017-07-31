package uniandes.cupi2.cupiViajes.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.cupiViajes.mundo.ComparadorHotelNombre;
import uniandes.cupi2.cupiViajes.mundo.Hotel;

/**
 * Clase prara probar el correcto funcionamiento del comaprador de hoteles por nombre
 * @author alvar-go
 *
 */
public class ComparadorHotelNombreTest extends TestCase
{

    /**
     * Hotel para realizar la prueba
     */
    private Hotel hotel1;

    /**
     * Hotel para realizar la prueba
     */
    private Hotel hotel2;

    private ComparadorHotelNombre comparador;

    private Properties datosHotel; 

    /**
     * Escenario 1:Inicializa los hoteles en un estado conocido para poder realizar las pruebas
     * post: Se inicializan los hoteles con valores conocidos y el comparador
     */
    public void setUp()
    {
        datosHotel = new Properties( );
        comparador = new ComparadorHotelNombre( );

        FileInputStream in;

        try{
            in = new FileInputStream( "./data/viajes.properties" );
            datosHotel.load( in );
            in.close( );

        }
        catch (Exception e)
        {
            e.printStackTrace( );
        }


        hotel1 = new Hotel( datosHotel.getProperty( "hotel1.ciudad" ), datosHotel.getProperty( "hotel1.hotel" ), Integer.parseInt( datosHotel.getProperty( "hotel1.estrellas" ) ), Integer.parseInt( datosHotel.getProperty( "hotel1.costoNoche" ) ), datosHotel.getProperty( "hotel1.imagen" ) );
        hotel2 = new Hotel( datosHotel.getProperty( "hotel2.ciudad" ), datosHotel.getProperty( "hotel2.hotel" ), Integer.parseInt( datosHotel.getProperty( "hotel2.estrellas" ) ), Integer.parseInt( datosHotel.getProperty( "hotel2.costoNoche" ) ), datosHotel.getProperty( "hotel2.imagen" ) );
    }

    /**
     * Prueba 1: Se encarga de verificar el m�todo compare del comparador de hoteles.<br>
     * <b> M�todos a probar: </b> <br>
     * compare<br>
     * <b> Casos de prueba:</b><br>
     * 1. El hotel1 es igual al hotel1.<br>
     * 2. El hotel1 es menor al hotel2.<br>
     * 3. El hotel2 es mayor al hotel1.
     */
    public void testCompare()
    {
        assertEquals( "El hotel 1 es igual al hotel 1.", 0, comparador.compare( hotel1, hotel1 ));
        assertEquals("El hotel 1 no es menor al hotel 2.", -1, comparador.compare( hotel1, hotel2 ));
        assertEquals( "El hotel 2 no es mayor al hotel 1.", 1, comparador.compare( hotel2, hotel1 ) );
    }
}
