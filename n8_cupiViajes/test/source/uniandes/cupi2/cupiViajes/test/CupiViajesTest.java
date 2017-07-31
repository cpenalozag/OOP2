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

import java.util.ArrayList;
import java.util.Calendar;

import uniandes.cupi2.cupiViajes.excepciones.ClienteTieneReservaException;
import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.ComparadorReservaNombreCliente;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;
import uniandes.cupi2.ordenador.AlgoritmoOrdenamiento;
import uniandes.cupi2.ordenador.Ordenador;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los métodos de la clase CupiViajes están correctamente implementados.
 */
public class CupiViajesTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se harán las pruebas.
     */
    private CupiViajes cupiViajes1;

    /**
     * Clase donde se harán las pruebas.
     */
    private CupiViajes cupiViajes2;

    /**
     * Clase donde se harán las pruebas.
     */
    private CupiViajes cupiViajes3;

    // -------------------------------------------------------------
    // Métodos
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
        try
        {
            cupiViajes2.agregarReserva( hotel1, c.getTime( ), "1","Cliente1", 2, 1, 3, Aerolinea.AVIANCA );
            c.set( 2016, Calendar.OCTOBER, 10);
            cupiViajes2.agregarReserva( hotel2, c.getTime( ), "2","Cliente2", 4, 0, 2, Aerolinea.IBERIA );
            c.set( 2016, Calendar.DECEMBER, 6);
            cupiViajes2.agregarReserva( hotel3, c.getTime( ), "3","Cliente3", 4, 2, 1, Aerolinea.VIVA_COLOMBIA );

        }
        catch( Exception e )
        {
            fail ("No debería lanzar excepción.");

        }

        cupiViajes3 = new CupiViajes( );

        Hotel hotel4 = new Hotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
        Hotel hotel5 = new Hotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
        Hotel hotel6 = new Hotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );

        cupiViajes3.agregarHotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
        cupiViajes3.agregarHotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
        cupiViajes3.agregarHotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );

        c.set( 2015, Calendar.JANUARY, 5 );
        try
        {
            cupiViajes3.agregarReserva( hotel4, c.getTime( ), "1","Cliente1", 2, 1, 3, Aerolinea.AVIANCA );
            c.set( 2016, Calendar.DECEMBER, 10 );
            cupiViajes3.agregarReserva( hotel5, c.getTime( ), "2","Cliente2", 4, 0, 2, Aerolinea.IBERIA );
            c.set( 2016, Calendar.DECEMBER, 6 );
            cupiViajes3.agregarReserva( hotel6, c.getTime( ), "3","Cliente3", 4, 2, 1, Aerolinea.VIVA_COLOMBIA );
            c.set( 2015, Calendar.FEBRUARY, 1 );
            cupiViajes3.agregarReserva( hotel4, c.getTime( ), "4","Cliente12", 3, 1, 6, Aerolinea.JETBLUE );
            c.set( 2016, Calendar.MARCH, 14 );
            cupiViajes3.agregarReserva( hotel4, c.getTime( ), "5","Cliente22", 2, 2, 3, Aerolinea.AVIANCA );
            c.set( 2015, Calendar.MARCH, 13 );
            cupiViajes3.agregarReserva( hotel4, c.getTime( ), "6","Cliente32", 5, 0, 7, Aerolinea.LAN );
        }
        catch( Exception e )
        {
            fail ("No debería lanzar excepción.");
        }

    }


    /**
     * Prueba 1: Se encarga de verificar el método constructor de la clase.<br>
     * <b> Métodos a probar: </b> <br>
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

        assertEquals( "La lista de reservas se debe inicializar vacía.", 0, cupiViajes1.darReservas( ).size( ) );
        assertEquals( "La lista de hoteles se debe inicializar vacía.", 0, cupiViajes1.darHoteles( ).size( ) );
    }

    /**
     * Prueba 2: Se encarga de verificar el método agregarHotel de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * agregarHotel<br>
     * darHoteles<br>
     * <b> Casos de prueba:</b><br>
     * 1. No hay hoteles en el sistema.<br>
     * 2. Hay hoteles en el sistema.
     */
    public void testAgregarHotel( )
    {
        // Caso de prueba 1.

        cupiViajes1.agregarHotel( "Ciudad1", "Nombre1", 5, 600000, "Imagen1" );
        assertEquals( "Debe haber 1 hotel en el sistema.", 1, cupiViajes1.darHoteles( ).size( ) );

        // Caso de prueba 2.
        cupiViajes1.agregarHotel( "Ciudad2", "Nombre2", 3, 200000, "Imagen2" );
        assertEquals( "Debe haber 2 hotel en el sistema.", 2, cupiViajes1.darHoteles( ).size( ) );
    }

    /**
     * Prueba 3: Se encarga de verificar el método agregarReserva de la clase.<br>
     * <b> Métodos a probar: </b> <br>
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
        try
        {
            cupiViajes1.agregarReserva( hotel1, c.getTime( ), "1", "Cliente1", 2, 1, 4, Aerolinea.AVIANCA );
            assertEquals( "Debe haber 1 reserva en el sistema.", 1, cupiViajes1.darReservas( ).size( ) );

            ReservaViaje reserva = ( ReservaViaje )cupiViajes1.darReservas( ).get( 0 );
            assertEquals( "El hotel de la reserva agregada no es el correcto.", "Nombre1", reserva.darHotel( ).darNombre( ) );
            assertEquals( "El cliente de la reserva es Cliente1.", "Cliente1", reserva.darNombreCliente( ) );
        }
        catch( Exception e )
        {
            fail ("No debería lanzar excepción.");
        }
    }

    /**
     * Prueba 4: Se encarga de verificar el método agregarReserva de la clase.<br>
     * <b> Métodos a probar: </b> <br>
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
        try
        {
            cupiViajes2.agregarReserva( hotel1, c.getTime( ), "4", "Cliente4", 2, 1, 4, Aerolinea.AVIANCA );
            assertEquals( "Debe haber 4 reserva en el sistema.", 4, cupiViajes2.darReservas( ).size( ) );

            ReservaViaje reserva = ( ReservaViaje )cupiViajes2.darReservas( ).get( 3 );
            assertEquals( "El hotel de la reserva agregada no es el correcto.", "Nombre4", reserva.darHotel( ).darNombre( ) );
            assertEquals( "El cliente de la reserva es Cliente4.", "Cliente4", reserva.darNombreCliente( ) );

        }
        catch( Exception e )
        {
            fail("No debería lanzar excepción");
        }

        // Caso de prueba 2
        hotel1 = new Hotel( "Nombre4", "Ciudad4", 4, 420000, "Imagen4" );

        c.set( 2016, Calendar.JANUARY, 4 );
        try
        {
            cupiViajes2.agregarReserva( hotel1, c.getTime( ), "1", "Cliente1", 2, 1, 4, Aerolinea.AVIANCA );
            fail ("No debería llegar a este punto, la cedula está repetida.");

        }
        catch( ClienteTieneReservaException e )
        {
            assertEquals( "La cedula guardada en la excepción no es la correcta.", "1", e.darCedulaCliente( ) );
        }
    }

    /**
     * Prueba 5: Se encarga de verificar el método buscarHotelesCiudad de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * buscarHotelesCiudad<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen hoteles en la ciudad dada.<br>
     * 2. No existen hoteles en la ciudad dada.
     */
    public void testBuscarHotelesCiudad( )
    {

        // Caso de prueba 1.
        ArrayList<Hotel> hoteles = cupiViajes2.buscarHotelesCiudad( "Ciudad1" );
        assertEquals( "Debería existir un hotel en la ciudad 1.", 1, hoteles.size( ) );

        hoteles = cupiViajes2.buscarHotelesCiudad( "Ciudad2" );
        assertEquals( "Debería existir dos hoteles en la ciudad 2.", 2, hoteles.size( ) );

        // Caso de prueba 2.
        hoteles = cupiViajes2.buscarHotelesCiudad( "CiudadNoExiste" );
        assertEquals( "No deberían existir hoteles en esta ciudad.", 0, hoteles.size( ) );
    }

    /**
     * Prueba 6: Se encarga de verificar el método buscarReserva de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * buscarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen reservas a nombre del cliente dado.<br>
     * 2. No existen reservas a nombre del cliente dado.
     */
    public void testBuscarReserva( )
    {

        // Caso de prueba 1.
        ReservaViaje reserva = cupiViajes3.buscarReserva( "1" );
        assertNotNull( "Debería existir una reserva a nombre del cliente 1.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente1", reserva.darNombreCliente( ) );

        reserva = cupiViajes3.buscarReserva( "2" );
        assertNotNull( "Debería existir una reserva a nombre del cliente 2.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente2", reserva.darNombreCliente( ) );

        // Caso de prueba 2.
        reserva = cupiViajes3.buscarReserva( "ClienteNoExistente" );
        assertNull( "No debería existir una reserva a nombre de Cliente No Existente.", reserva );
    }

    /**
     * Prueba 7: Se encarga de verificar el método buscarReservaPorClienteBinario de la clase.<br>
     * <b> Métodos a probar: </b> <br>
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
        assertNotNull( "Debería existir una reserva a nombre del cliente 12.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente12", reserva.darNombreCliente( ) );

        reserva = cupiViajes3.buscarReservaPorClienteBinario( "Cliente22" );
        assertNotNull( "Debería existir una reserva a nombre del cliente 22.", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente22", reserva.darNombreCliente( ) );

        // Caso de prueba 2.
        reserva = cupiViajes3.buscarReservaPorClienteBinario( "ClienteNoExistente" );
        assertNull( "No debería existir una reserva a nombre de Cliente No Existente.", reserva );
    }

    /**
     * Prueba 8: Se encarga de verificar el método buscarReservaPorCiudad de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * buscarReservaPorCiudad<br>
     * <b> Casos de prueba:</b><br>
     * 1. Existen reservas para un hotel en la ciudad dada.<br>
     * 2. No existen reservas para un hotel en la ciudad dada.
     */
    public void testBuscarReservaPorCiudad( )
    {


        // Caso de prueba 1.
        ReservaViaje reserva = cupiViajes3.buscarReservaPorCiudad( "Ciudad2" );
        assertNotNull( "Debería existir una reserva en la ciudad 2", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente2", reserva.darNombreCliente( ) );

        reserva = cupiViajes3.buscarReservaPorCiudad( "Ciudad1" );
        assertNotNull( "Debería existir una reserva en la ciudad 1", reserva );
        assertEquals( "La reserva retornada no es la buscada.", "Cliente1", reserva.darNombreCliente( ) );

        // Caso de prueba 2.
        reserva = cupiViajes3.buscarReservaPorCiudad( "CiudadNoExistente" );
        assertNull( "No debería existir una reserva en la Ciudad No Existente.", reserva );
    }

    /**
     * Prueba 9: Se encarga de verificar el método buscarReservaMasPersonas de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * buscarReservaMasPersonas<br>
     * agregarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. Busca la reserva con mayor cantidad de personas que van a viajar.<br>
     */
    public void testBuscarReservaMasPersonas( )
    {
        ReservaViaje reserva = cupiViajes3.buscarReservaMasPersonas( );
        assertEquals( "La reserva con mayor cantidad de personas es la del cliente cliente3.", "Cliente3", reserva.darNombreCliente( ) );

        Calendar c = Calendar.getInstance( );
        c.set( 2016, Calendar.JANUARY, 5 );
        try
        {
            cupiViajes3.agregarReserva( new Hotel( "Nombre", "Ciudad", 5, 120000, "Imagen" ), c.getTime( ), "7", "Cliente4", 4, 4, 2, Aerolinea.LAN );
            reserva = cupiViajes3.buscarReservaMasPersonas( );
            assertEquals( "La reserva con mayor cantidad de personas es la del cliente cliente4.", "Cliente4", reserva.darNombreCliente( ) );

        }
        catch( Exception e )
        {
            fail ("No debería lanzar excepción.");
        }
    }

    /**
     * Prueba 10: Se encarga de verificar el método buscarReservaMenosPersonas de la clase.<br>
     * <b> Métodos a probar: </b> <br>
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
        try
        {
            cupiViajes3.agregarReserva( new Hotel( "Nombre", "Ciudad", 5, 120000, "Imagen" ), c.getTime( ), "7", "Cliente4", 4, 4, 2, Aerolinea.LAN );
            reserva = cupiViajes3.buscarReservaMasPersonas( );
            assertEquals( "La reserva con menor cantidad de personas es la del cliente cliente4.", "Cliente4", reserva.darNombreCliente( ) );

        }
        catch( Exception e )
        {
            fail("No debería lanzar excepción.");
        }
    }

    /**
     * Prueba 11: Se encarga de verificar el método buscarReservaAerolinea de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * buscarReservaAerolinea<br>
     * agregarReserva<br>
     * <b> Casos de prueba:</b><br>
     * 1. No existen reservas para la aerolínea dada.<br>
     * 2. Existen reservas para la aerolínea dada.
     */
    public void testBuscarReservasAerolinea( )
    {
        // Caso de prueba 1.
        ArrayList reservas = cupiViajes3.buscarReservasAerolinea( Aerolinea.SATENA );
        assertEquals( "No debería haber reservas con esa aerolínea.", 0, reservas.size( ) );

        // Caso de prueba 2.
        reservas = cupiViajes3.buscarReservasAerolinea( Aerolinea.AVIANCA );
        assertEquals( "Hay dos reservas con esa aerolínea.", 2, reservas.size( ) );

        Calendar c = Calendar.getInstance( );
        c.set( 2016, Calendar.JANUARY, 4 );
        try
        {
            cupiViajes3.agregarReserva( new Hotel( "Nombre", "Ciudad", 5, 120000, "Imagen" ), c.getTime( ), "7", "Cliente4", 4, 4, 2, Aerolinea.LAN );
            cupiViajes3.agregarReserva( new Hotel( "Nombre", "Ciudad", 5, 120000, "Imagen" ), c.getTime( ),  "8", "Cliente5", 4, 4, 2, Aerolinea.LAN );
            reservas = cupiViajes3.buscarReservasAerolinea( Aerolinea.LAN );
            assertEquals( "Hay tres reservas con esa aerolínea.", 3, reservas.size( ) );

        }
        catch( Exception e )
        {
            fail ("No debería lanzar excepción.");
        }

    }


}
