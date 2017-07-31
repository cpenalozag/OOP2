package uniandes.cupi2.cupiViajes.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.CriterioOrdenReserva;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;
import uniandes.cupi2.ordenador.AlgoritmoOrdenamiento;
import uniandes.cupi2.ordenador.Ordenador;
import junit.framework.TestCase;

/**
 * Clase encarada de probar la clase que realiza ordenamientos
 * @author alvar-go
 *
 */
public class OrdenadorTest extends TestCase {
	
	 // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Lsita que se usara para realizar las pruebas
     */
    private ArrayList<ReservaViaje> reservas;
    
    private Ordenador<ReservaViaje> ordenador;
  
    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Inicializa la lista de vinos para probar los ordenamientos
     * Inicializa el ordenador
     */
    protected void setUp()
    {

         reservas = new ArrayList<ReservaViaje>();

         Hotel hotel4 = new Hotel( "Nombre1", "Ciudad1", 5, 500000, "Imagen1" );
         Hotel hotel5 = new Hotel( "Nombre2", "Ciudad2", 3, 250000, "Imagen2" );
         Hotel hotel6 = new Hotel( "Nombre3", "Ciudad2", 1, 25000, "Imagen3" );
         
         Calendar c = Calendar.getInstance( );
         c.set( 2015, Calendar.JANUARY, 5 );
         reservas.add(new ReservaViaje( "1","Juan", 5, 2, 5, Aerolinea.AVIANCA, hotel4, c.getTime( ) ));
         c.set( 2016, Calendar.DECEMBER, 10 );
         reservas.add(new ReservaViaje( "2","Maria", 3, 0, 4, Aerolinea.AVIANCA, hotel5, c.getTime( ) ));
         c.set( 2016, Calendar.DECEMBER, 6 );
         reservas.add(new ReservaViaje( "3","Pedroa", 2, 0, 7, Aerolinea.AVIANCA, hotel6, c.getTime( ) ));
         c.set( 2015, Calendar.FEBRUARY, 1 );
         reservas.add(new ReservaViaje( "4","Ana", 1, 5, 3, Aerolinea.AVIANCA, hotel6, c.getTime( ) ));
         c.set( 2016, Calendar.MARCH, 14 );
         reservas.add(new ReservaViaje( "5","Pepe", 4, 8, 2, Aerolinea.AVIANCA, hotel5, c.getTime( ) ));
         c.set( 2015, Calendar.MARCH, 13 );
         reservas.add(new ReservaViaje( "6","Diana", 8, 3, 5, Aerolinea.AVIANCA, hotel4, c.getTime( ) ));
         
         ordenador = new Ordenador<ReservaViaje>( );
    }

	 /**
     * Prueba 1: Se encarga de verificar todos los algoritmos de ordenamiento por todos los criterios de forma ascendente.<br>
     * <b> Métodos a probar: </b> <br>
     * ordenar<br>
     * <b> Casos de prueba:</b><br>
     * 1. Los vinos no están ordenados.<br>
     */
    public void testOrdenarAscendente( )
    {
    	
    	for (AlgoritmoOrdenamiento or : AlgoritmoOrdenamiento.values()) {
			for (CriterioOrdenReserva co : CriterioOrdenReserva.values()) {
				
				int tamPrevio = reservas.size();
				Collections.shuffle(reservas);
				ordenador.ordenar(or, reservas, co.esAscendnete( ), co.darComparador());
				
    			if(co.esAscendnete( ))
    			{
    			    for (int i = 0; i < reservas.size()-1; i++) {
    			        ReservaViaje v1 = reservas.get(i);
    			        ReservaViaje v2 = reservas.get(i+1);
    			        assertTrue("Ordenamiento ascendente por " + co.darNombre() + " y " + or.darNombre() + " es incorrecto", co.darComparador().compare(v1, v2) <= 0);
    			    }
    			    assertEquals("Se borraron elementos en " + or.darNombre(), tamPrevio, reservas.size());
    			}
    			else
    			{
    			    
    			    for (int i = 0; i < reservas.size()-1; i++) {
                        ReservaViaje v1 = reservas.get(i);
                        ReservaViaje v2 = reservas.get(i+1);
                        assertTrue("Ordenamiento ascendente por " + co.darNombre() + " y " + or.darNombre() + " es incorrecto", co.darComparador().compare(v1, v2) >= 0);
                    }
                    assertEquals("Se borraron elementos en " + or.darNombre(), tamPrevio, reservas.size());
    			}
			}
		}
    }
   
}
