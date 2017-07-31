/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiViajes.test;

import java.util.ArrayList;
import java.util.Calendar;

import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.ComparadorReservaNombreCliente;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;
import uniandes.cupi2.ordenador.AlgoritmoOrdenamiento;
import uniandes.cupi2.ordenador.Ordenador;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los m�todos de la clase CupiViajes est�n correctamente implementados.
 */
public class CupiViajesTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private CupiViajes cupiViajes1;

    /**
     * Clase donde se har�n las pruebas.
     */
    private CupiViajes cupiViajes2;

    /**
     * Clase donde se har�n las pruebas.
     */
    private CupiViajes cupiViajes3;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye el sistema de viajes sin reservas.
     */
    protected void setUp( )
    {
        cupiViajes1 = new CupiViajes( );

        cupiViajes2 = new CupiViajes( );

        Hotel hotel1 = new Hotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
        Hotel hotel2 = new Hotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
        Hotel hotel3 = new Hotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );

        cupiViajes2.agregarHotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
        cupiViajes2.agregarHotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
        cupiViajes2.agregarHotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );

        Calendar c = Calendar.getInstance( );
        c.set( 2015, Calendar.JANUARY, 5);
        cupiViajes2.agregarReserva( hotel1, c.getTime( ), "Cliente1", 2, 1, 3, Aerolinea.AVIANCA );
        c.set( 2016, Calendar.OCTOBER, 10);
        cupiViajes2.agregarReserva( hotel2, c.getTime( ), "Cliente2", 4, 0, 2, Aerolinea.IBERIA );
        c.set( 2016, Calendar.DECEMBER, 6);
        cupiViajes2.agregarReserva( hotel3, c.getTime( ), "Cliente3", 4, 2, 1, Aerolinea.VIVA_COLOMBIA );

        cupiViajes3 = new CupiViajes( );

        Hotel hotel4 = new Hotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
        Hotel hotel5 = new Hotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
        Hotel hotel6 = new Hotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );

        cupiViajes3.agregarHotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
        cupiViajes3.agregarHotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
        cupiViajes3.agregarHotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );

        c.set( 2015, Calendar.JANUARY, 5 );
        cupiViajes3.agregarReserva( hotel4, c.getTime( ), "Cliente1", 2, 1, 3, Aerolinea.AVIANCA );
        c.set( 2016, Calendar.DECEMBER, 10 );
        cupiViajes3.agregarReserva( hotel5, c.getTime( ), "Cliente2", 4, 0, 2, Aerolinea.IBERIA );
        c.set( 2016, Calendar.DECEMBER, 6 );
        cupiViajes3.agregarReserva( hotel6, c.getTime( ), "Cliente3", 4, 2, 1, Aerolinea.VIVA_COLOMBIA );
        c.set( 2015, Calendar.FEBRUARY, 1 );
        cupiViajes3.agregarReserva( hotel4, c.getTime( ), "Cliente12", 3, 1, 6, Aerolinea.JETBLUE );
        c.set( 2016, Calendar.MARCH, 14 );
        cupiViajes3.agregarReserva( hotel4, c.getTime( ), "Cliente22", 2, 2, 3, Aerolinea.AVIANCA );
        c.set( 2015, Calendar.MARCH, 13 );
        cupiViajes3.agregarReserva( hotel4, c.getTime( ), "Cliente32", 5, 0, 7, Aerolinea.LAN );
    }


    /**
     * Prueba 1: Se encarga de verificar el m�todo constructor de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * CupiViajes<br>
     * darReservas<br>
     * darHoteles<br>
     * <b> Casos de prueba:</b><br>
     * 1. Construye correctamente el sistema de CupiViajes, cada uno de los valores corresponde al esperado.<br>
     */
    public void testCupiViajes( )
    {

        assertNotNull( "La lista de reservas no puede ser nula.", cupiViajes1.darReservas( ) );
        assertNotNull( "La lista de hoteles no puede ser nula.", cupiViajes1.darHoteles( ) );

        assertEquals( "La lista de reservas se debe inicializar vac�a.", 0, cupiViajes1.darReservas( ).size( ) );
        assertEquals( "La lista de hoteles se debe inicializar vac�a.", 0, cupiViajes1.darHoteles( ).size( ) );
    }

    /**
     * Prueba 2: Se encarga de verificar el m�todo agregarHotel de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * agregarHotel<br>
     * darHoteles<br>
     * <b> Casos de prueba:</b><br>
     * 1. No hay hoteles en el sistema.<br>
     * 2. Hay hoteles en el sistema.
     */
    public void testAgregarHotel( )
    {

        assertFalse( "No hay hoteles registrados.", cupiViajes1.darHoteles( )==null );
        assertTrue("Hay hoteles registrados.", cupiViajes2.darHoteles( )!= null);

    }

    /**
     * Prueba 3: Se encarga de verificar el m�todo agregarReserva de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * agregarReserva<br>
     * darReservas<br>
     * <b> Casos de prueba:</b><br>
     * 1. Se agrega una reserva cuando no hay reservas en el sistema.<br>
     */
    public void testAgregarReserva1( )
    {

        Hotel hotel1 = new Hotel( "Nombre1", "Ciudad1", 4, 420000, "Imagen1" );
        Calendar c = Calendar.getInstance( );
        c.set( 2016, Calendar.JANUARY, 4 );
        cupiViajes1.agregarReserva( hotel1, c.getTime( ), "Cliente1", 2, 1, 4, Aerolinea.AVIANCA );
        assertEquals( "Debe haber 1 reserva en el sistema.", 1, cupiViajes1.darReservas( ).size( ) );

        ReservaViaje reserva = ( ReservaViaje )cupiViajes1.darReservas( ).get( 0 );
        assertEquals( "El hotel de la reserva agregada no es el correcto.", "Nombre1", reserva.darHotel( ).darNombre( ) );
        assertEquals( "El cliente de la reserva es Cliente1.", "Cliente1", reserva.darNombreCliente( ) );
    }

    /**
     * Prueba 4: Se encarga de verificar el m�todo agregarReserva de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * agregarReserva<br>
     * darReservas<br>
     * <b> Casos de prueba:</b><br>
     * 1. Se agrega una reserva a un cliente que no tiene reserva.<br>
     * 2. Se agrega una reserva a un cliente que ya tiene reserva.
     */
    public void testAgregarReserva2( )
    {
        // Caso de prueba 1
        Hotel hotel1 = new Hotel( "Nombre4", "Ciudad4", 4, 420000, "Imagen4" );
        Calendar c = Calendar.getInstance( );
        c.set( 2016, Calendar.JANUARY, 4 );
        cupiViajes2.agregarReserva( hotel1, c.getTime( ), "Cliente4", 2, 1, 4, Aerolinea.AVIANCA );
        assertEquals( "Debe haber 4 reserva en el sistema.", 4, cupiViajes2.darReservas( ).size( ) );

        ReservaViaje reserva = ( ReservaViaje )cupiViajes2.darReservas( ).get( 3 );
        assertEquals( "El hotel de la reserva agregada no es el correcto.", "Nombre4", reserva.darHotel( ).darNombre( ) );
        assertEquals( "El cliente de la reserva es Cliente4.", "Cliente4", reserva.darNombreCliente( ) );

        // Caso de prueba 2
        hotel1 = new Hotel( "Nombre4", "Ciudad4", 4, 420000, "Imagen4" );

        c.set( 2016, Calendar.JANUARY, 4 );
        boolean pudo = cupiViajes2.agregarReserva( hotel1, c.getTime( ), "Cliente1", 2, 1, 4, Aerolinea.AVIANCA );
        assertFalse( "No se deber�a agregar la reserva. Ya existe una reserva con ese cliente.", pudo );
    }

    /**
     * Prueba 5: Se encarga de verificar el m�todo buscarHotelesCiudad de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarHotelesCiudad<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen hoteles en la ciudad dada.<br>
     * 2. No existen hoteles en la ciudad dada.
     */
    public void testBuscarHotelesCiudad( )
    {

        // Caso de prueba 1.
        ArrayList<Hotel> hoteles = cupiViajes2.buscarHotelesCiudad( "Ciudad1" );
        assertEquals( "Deber�a existir un hotel en la ciudad 1.", 1, hoteles.size( ) );

        hoteles = cupiViajes2.buscarHotelesCiudad( "Ciudad2" );
        assertEquals( "Deber�a existir dos hoteles en la ciudad 2.", 2, hoteles.size( ) );

        // Caso de prueba 2.
        hoteles = cupiViajes2.buscarHotelesCiudad( "CiudadNoExiste" );
        assertEquals( "No deber�an existir hoteles en esta ciudad.", 0, hoteles.size( ) );
    }

    /**
     * Prueba 6: Se encarga de verificar el m�todo buscarReserva de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen reservas a nombre del cliente dado.<br>
     * 2. No existen reservas a nombre del cliente dado.
     */
    public void testBuscarReserva( )
    {

        // Caso de prueba 1.
        ReservaViaje reserva = cupiViajes3.buscarReserva( "Cliente1" );
        assertNotNull( "Deber�a existir una reserva a nombre del cliente 1.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente1", reserva.darNombreCliente( ) );

        reserva = cupiViajes3.buscarReserva( "Cliente2" );
        assertNotNull( "Deber�a existir una reserva a nombre del cliente 2.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente2", reserva.darNombreCliente( ) );

        // Caso de prueba 2.
        reserva = cupiViajes3.buscarReserva( "ClienteNoExistente" );
        assertNull( "No deber�a existir una reserva a nombre de Cliente No Existente.", reserva );
    }

    /**
     * Prueba 7: Se encarga de verificar el m�todo buscarReservaPorClienteBinario de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarReservaPorClienteBinario<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen reservas a nombre del cliente dado.<br>
     * 2. No existen reservas a nombre del cliente dado.
     */
    public void testBuscarReservaPorClienteBinario( )
    {

        Ordenador<ReservaViaje> ordenador = new Ordenador<ReservaViaje>( );
        ordenador.ordenar( AlgoritmoOrdenamiento.BURBUJA, cupiViajes3.darReservas( ), true, new ComparadorReservaNombreCliente( ) );


        // Caso de prueba 1.
        ReservaViaje reserva = cupiViajes3.buscarReservaPorClienteBinario( "Cliente12" );
        assertNotNull( "Deber�a existir una reserva a nombre del cliente 12.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente12", reserva.darNombreCliente( ) );

        reserva = cupiViajes3.buscarReservaPorClienteBinario( "Cliente22" );
        assertNotNull( "Deber�a existir una reserva a nombre del cliente 22.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente22", reserva.darNombreCliente( ) );

        // Caso de prueba 2.
        reserva = cupiViajes3.buscarReservaPorClienteBinario( "ClienteNoExistente" );
        assertNull( "No deber�a existir una reserva a nombre de Cliente No Existente.", reserva );
    }

    /**
     * Prueba 8: Se encarga de verificar el m�todo buscarReservaPorCiudad de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarReservaPorCiudad<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen reservas para un hotel en la ciudad dada.<br>
     * 2. No existen reservas para un hotel en la ciudad dada.
     */
    public void testBuscarReservaPorCiudad( )
    {
        ReservaViaje reservaCiudad = cupiViajes2.buscarReservaPorCiudad( "Ciudad1" );
        assertNotNull("Deberia existir una reserva en la  Ciudad1.", reservaCiudad);
        assertEquals("La ciudad retornada no es la deseada.", "Ciudad1", reservaCiudad.darHotel( ).darCiudad( ));

        reservaCiudad = cupiViajes3.buscarReservaPorCiudad( "Ciudad2" );
        assertNotNull("Deberia existir una reserva en la Ciudad2.", reservaCiudad);
        assertEquals("La ciudad retornada no es la deseada.", "Ciudad2", reservaCiudad.darHotel( ).darCiudad( ));
        
        reservaCiudad = cupiViajes3.buscarReservaPorCiudad( "Ciudad4" );
        assertEquals("La ciudad dada por parametro no existe.", null , reservaCiudad);

    }
    /**
     * Prueba 9: Se encarga de verificar el m�todo buscarReservaMasPersonas de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarReservaMasPersonas<br>
     * agregarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. Busca la reserva con mayor cantidad de personas que van a viajar.<br>
     */
    public void testBuscarReservaMasPersonas( )
    {
        Calendar c = Calendar.getInstance( );
        c.set( 2016, Calendar.JANUARY, 5 );

        ReservaViaje reserva = cupiViajes2.buscarReservaMasPersonas( );
        assertEquals("La reserva con mayor cantidad de personas es la del cliente Cliente3", "Cliente3", reserva.darNombreCliente( ));

    }

    /**
     * Prueba 10: Se encarga de verificar el m�todo buscarReservaMenosPersonas de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarReservaMenosPersonas<br>
     * agregarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. Busca la reserva con menor cantidad de personas que van a viajar.<br>
     */
    public void testBuscarReservaMenosPersonas( )
    {

        ReservaViaje reserva = cupiViajes3.buscarReservaMenosPersonas( );
        assertEquals( "La reserva con menor cantidad de personas es la del cliente cliente1.", "Cliente1", reserva.darNombreCliente( ) );

        Calendar c = Calendar.getInstance( );
        c.set( 2016, Calendar.JANUARY, 4 );
        cupiViajes3.agregarReserva( new Hotel( "Nombre", "Ciudad", 5, 120000, "Imagen" ), c.getTime( ), "Cliente4", 4, 4, 2, Aerolinea.LAN );
        reserva = cupiViajes3.buscarReservaMasPersonas( );
        assertEquals( "La reserva con menor cantidad de personas es la del cliente cliente4.", "Cliente4", reserva.darNombreCliente( ) );
    }

    /**
     * Prueba 11: Se encarga de verificar el m�todo buscarReservaAerolinea de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * buscarReservaAerolinea<br>
     * agregarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. No existen reservas para la aerol�nea dada.<br>
     * 2. Existen reservas para la aerol�nea dada.
     */
    public void testBuscarReservasAerolinea( )
    {
        ArrayList<ReservaViaje> reserva = cupiViajes1.buscarReservasAerolinea(Aerolinea.VIVA_COLOMBIA );

        assertEquals("No existen reservas para la aerolina dada", 0, reserva.size( ));

        reserva = cupiViajes2.buscarReservasAerolinea( Aerolinea.AVIANCA );
        assertEquals("Existen reservas para la aerolina dada Avianca",Aerolinea.AVIANCA, reserva.get( 0 ).darAerolinea( ) );

    }


}
