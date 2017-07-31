package uniandes.cupi2.cupiViajes.test;

import java.util.Calendar;
import java.util.Comparator;

import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.CriterioOrdenReserva;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;

import junit.framework.TestCase;

/**
 * Clase encargada de probar las clases comparadoras
 * @author alvar-go
 *
 */
public class ComparadoresReservaTest extends TestCase {

    /**
     * Clase donde se har�n las pruebas.
     */
    private ReservaViaje reserva1;

    /**
     * Clase donde se har�n las pruebas.
     */
    private ReservaViaje reserva2;

    /**
     * Clase donde se har�n las pruebas.
     */
    private ReservaViaje reserva3;



    /**
     * Escenario 1: Construye cuatro vinos
     */
    public void setUp( )
    {
        Hotel hotel = new Hotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );


        Calendar c = Calendar.getInstance( );
        c.set( 2015, Calendar.JANUARY, 5 );
        reserva1 = new ReservaViaje( "cliente1", 1, 1, 1, Aerolinea.AVIANCA, hotel, c.getTime( ) );
        c.set( 2016, Calendar.DECEMBER, 10 );
        reserva2 = new ReservaViaje( "cliente2", 2, 2, 2, Aerolinea.IBERIA, hotel, c.getTime( ) );
        c.set( 2017, Calendar.DECEMBER, 6 );
        reserva3 = new ReservaViaje( "cliente3", 3, 3, 3, Aerolinea.LAN, hotel, c.getTime( ) );

    }

    /**
     * Prueba 1: Se encarga de verificar el m�todo compare de cada uno de los comparadores disponibles.<br>
     * <b> M�todos a probar: </b> <br>
     * compare<br>
     * <b> Casos de prueba:</b><br>
     * 1. La reserva1 es igual a la reserva1.<br>
     * 2. La reserva1 es menor a la reserva2.<br>
     * 3. La reserva3 es mayor a la reserva1.
     */
    public void testCompare( )
    {
        for (CriterioOrdenReserva c : CriterioOrdenReserva.values()) 
        {
            Comparator<ReservaViaje> comparator = c.darComparador();
            assertEquals("La comparasi�n por " +c.darNombre() + " deber�a dar cero", 0, comparator.compare(reserva1, reserva1));
            assertEquals("La comparasi�n " +c.darNombre() + " deber�a dar 1", 1, comparator.compare(reserva3, reserva1));
            assertEquals("La comparasi�n " +c.darNombre() + " deber�a dar -1", -1, comparator.compare(reserva1, reserva2));
        }
    }
}
