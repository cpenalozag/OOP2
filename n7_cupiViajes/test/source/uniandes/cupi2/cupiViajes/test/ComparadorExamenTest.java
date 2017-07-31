package uniandes.cupi2.cupiViajes.test;

import java.util.Calendar;

import junit.framework.TestCase;
import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;

/**
 * Clase prara probar el correcto funcionamiento del comaprador de hoteles por nombre
 * @author alvar-go
 *
 */

public class ComparadorExamenTest extends TestCase{
    
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
    
    private ReservaViaje reserva4;22

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

}
