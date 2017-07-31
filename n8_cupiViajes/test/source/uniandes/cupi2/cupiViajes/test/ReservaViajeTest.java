/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiViajes.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;

/**
 * Clase usada para verificar que los métodos de la clase ReservaViaje están correctamente implementados.
 */
public class ReservaViajeTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se harán las pruebas.
     */
    private ReservaViaje reserva1;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye una reserva de viaje.
     */
    protected void setUp( )
    {
        Hotel hotelReserva = new Hotel( "Hilton Cartagena", "Cartagena", 5, 390000, "Imagen" );
        Calendar c = Calendar.getInstance( );
        c.set( Calendar.DAY_OF_MONTH, 17 );
        c.set (Calendar.MONTH, Calendar.JANUARY);
        c.set( Calendar.YEAR, 2016);
        reserva1 = new ReservaViaje("1", "Pepe", 4, 0, 3, Aerolinea.AVIANCA, hotelReserva, c.getTime( ) );
    }

    /**
     * Prueba 1: Se encarga de verificar el método constructor de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * ReservaViaje<br>
     * darCliente<br>
     * darCantidadAdultos<br>
     * darCantidadNinios<br>
     * darDiasEstadia<br>
     * darAerolinea<br>
     * darHotel<br>
     * darFechaLlegada<br>
     * <b> Casos de prueba:</b><br>
     * 1. Construye correctamente la reserva, cada uno de los valores corresponde al esperado.<br>
     */
    public void testReservaViaje( )
    {

        assertNotNull( "El nombre del cliente que hizo la reserva no puede ser null.", reserva1.darNombreCliente( ) );
        assertEquals( "El nombre del cliente de la reserva no fue inicializado correctamente.", "Pepe", reserva1.darNombreCliente( ) );

        assertEquals( "La cantidad de adultos no se inicializó correctamente, debería haber 4 adultos en la reserva.", 4, reserva1.darCantidadAdultos( ) );
        assertEquals( "La cantidad de niños no se incializó correctamente, no debería haber niños en la reserva. ", 0, reserva1.darCantidadNinios( ) );
        assertEquals( "La cantidad de días de estadía no se inicializó correctamente, la reserva se hizo por 3 días.", 3, reserva1.darCantidadNochesEstadia( ) );

        assertNotNull( "La aerolínea que se va a reservar no puede ser null.", reserva1.darAerolinea( ) );
        assertEquals( "La aerolínea no se inicializó correctamente.", Aerolinea.AVIANCA, reserva1.darAerolinea( ) );

        assertNotNull( "La reserva debería tener un hotel asociado.", reserva1.darHotel( ) );
        assertEquals( "El hotel no fue inicializado correctamente.", "Hilton Cartagena", reserva1.darHotel( ).darNombre( ) );

        assertNotNull( "La reserva debería tener una fecha de llegada.", reserva1.darFechaLlegada( ) );
        Calendar c = Calendar.getInstance( );
        c.setTime( reserva1.darFechaLlegada( ) );
        assertEquals( "La fecha no fue inicializada correctamente.", 17, c.get( Calendar.DAY_OF_MONTH ) );
    }

    

    /**
     * Prueba 2: Se encarga de verificar el método toString de la clase. <br>
     * <b> Métodos a probar: </b> toString <br>
     * <b> Casos de prueba: </b> 1. El formato de texto es el correcto.
     */
    public void testToString( )
    {

        assertEquals( "El formato del toString no es el esperado.", "Pepe (Hilton Cartagena)", reserva1.toString( ) );
    }

}
