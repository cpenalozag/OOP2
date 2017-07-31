package uniandes.cupi2.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import uniandes.cupi2.cupiViajes.excepciones.PersistenciaException;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;


/**
 * Clase que permite interpretar el contenido del archivo CSV de puestos
 * @author alvar-go
 *
 */
public class ManejadorArchivosTexto {

	/**
	 * Separador de columnas de archivos CSV
	 */
	public final static String SEPARADOR = ";";
	
	public final static String LINEA_PUNTEADA = "==============================================================";
	
	/**
	 * Importa la información del archivo que llega porparámetro en el sistema que llega por parámetro
	 * post:	Se agregaron los hoteles del archivo al sistema
	 * @param ruta la ruta donde se encuentra el archivo. ruta != null
	 * @param cv el sistema donde se deben cargar los datos del archivo. cv != null
	 * @throws PersistenciaException Se lanza en caso que no se pueda leer el archivo
	 */
	public static void importarCSV(String ruta, CupiViajes cv)throws PersistenciaException
	{
		
		try {
			File f = new File(ruta);
			BufferedReader in = new BufferedReader(new FileReader(f));
			String linea = in.readLine();
			while(linea != null)
			{
				
				String[] datos = linea.split(SEPARADOR);
				try
                {
                    cv.agregarHotel( datos[0], datos[1], Integer.parseInt( datos[2] ), Double.parseDouble( datos[3]), datos[4] );
                }
                catch( Exception e )
                {
                    in.close( );
                    throw new PersistenciaException( "El archivo no tiene el formato esperado", ruta );
                }
				
				linea = in.readLine();
			}
			in.close();
			
		} catch (IOException e) {
			throw new PersistenciaException("No fue posible leer el archivo: " + e.getMessage(), ruta);
		}
	}
	
	/**
	 * Crea un reporte de texto con las reservas realizadas hasta el moemnto.
	 * @param ruta. La ruta donde se desea guardar el reporte. ruta != null
	 * @param cv el sistema cupiviajes del que se desea generar el reporte. cv != null
	 * @throws PersistenciaException Se lanza en caso de que se genere algún error creando el reporte
	 */
	public static void generarReporte(String ruta, CupiViajes cv) throws PersistenciaException
	{
	    File f = new File( ruta );
	    
	    try
        {
	        PrintWriter out = new PrintWriter( f );
	        Date fecha = new Date( );
            out.println( "Reporte reservas hechas" );
            out.println( fecha.toString( ) );
            out.println(  LINEA_PUNTEADA );
            for( ReservaViaje reserva : cv.darReservas( ) )
            {
                out.println( "Cédula cliente: " + reserva.darCedula( ) );
                out.println( "Nombre cliente: " + reserva.darNombreCliente( ) );
                out.println( "Total personas en el viaje: " + (reserva.darCantidadAdultos( )+reserva.darCantidadAdultos( )) );
                out.println( "Noches de estadia: " + reserva.darCantidadNochesEstadia( ) );
                out.println( "Aerolinea: " + reserva.darAerolinea( ) );
                out.println( "Nombre del hotel: " + reserva.darHotel( ).darNombre( ) );
                out.println( "Fecha de llegada: " +  reserva.darFechaLlegada( ).toString( ));
                out.println( "Costo total de la reserva: " + reserva.darCostoTotal( ) );
            }
            out.close( );
        }
        catch( Exception e )
        {
            throw new PersistenciaException( "No fue posible generar el reporte: " + e.getMessage( ), ruta );
        }
	}
	
}
