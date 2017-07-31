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

import uniandes.cupi2.cupiViajes.mundo.Hotel;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los m�todos de la clase Hotel est�n correctamente implementados.
 */
public class HotelTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Hotel hotel1;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye un hotel.
     */
    protected void setUp( )
    {
        hotel1 = new Hotel( "Kualaman�", "Melgar", 4, 250000, "Ruta" );
    }

    /**
     * Prueba 1: Se encarga de verificar el m�todo constructor de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * Hotel<br>
     * darNombre<br>
     * darCiudad<br>
     * darEstrellas<br>
     * darCostoNoche<br>
     * darRutaImagen<br>
     * <b> Casos de prueba:</b><br>
     * 1. Construye correctamente el hotel, cada uno de los valores corresponde al esperado.<br>
     */
    public void testHotel( )
    {

        assertNotNull( "El nombre del hotel no fue inicializado.", hotel1.darNombre( ) );
        assertEquals( "El nombre del hotel no fue inicializado correctamente.", "Kualaman�", hotel1.darNombre( ) );

        assertNotNull( "La ciudad donde se encuentra el hotel no fue inicializada.", hotel1.darCiudad( ) );
        assertEquals( "La ciudad donde se encuentra el hotel no fue inicializada correctamente.", "Melgar", hotel1.darCiudad( ) );

        assertNotNull( "La cantidad de estrellas del hotel no fue inicializada.", hotel1.darEstrellas( ) );
        assertEquals( "La cantidad de estrellas del hotel no fue inicializada correctamente.", 4, hotel1.darEstrellas( ) );

        assertNotNull( "El costo por noche del hotel no fue inicializado.", hotel1.darCostoNoche( ) );
        assertEquals( "El costo por noche del hotel no fue inicializado correctamente.", 250000, hotel1.darCostoNoche( ), 0 );

        assertNotNull( "La ruta de la imagen del hotel no fue inicializada.", hotel1.darRutaImagen( ) );
        assertEquals( "La ruta de la imagen no fue inicializada correctamente.", "Ruta", hotel1.darRutaImagen( ) );
    }  



    /**
     * Pruba2: Se encarga de verificar el m�todo toString de la clase. <br>
     * <b> M�todos a probar: </b>
     * toString <br>
     * <b> Casos de prueba: </b>
     * 1. El formato de texto es el correcto.
     */
    public void testToString( )
    {
        assertEquals( "El formato del toString no es el esperado.", hotel1.darNombre( ), hotel1.toString( ) );
    }
}
