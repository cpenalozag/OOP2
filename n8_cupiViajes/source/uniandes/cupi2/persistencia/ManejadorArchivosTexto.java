package uniandes.cupi2.persistencia;

import java.io.*;
import java.util.ArrayList;

import uniandes.cupi2.cupiViajes.excepciones.PersistenciaException;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
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
     * Importa la informaci�n del archivo que llega por par�metro en el sistema que llega por par�metro
     * post:	Se agregaron los hoteles del archivo al sistema
     * @param ruta la ruta donde se encuentra el archivo. ruta != null
     * @param cv el sistema donde se deben cargar los datos del archivo. cv != null
     * @throws PersistenciaException Se lanza en caso que no se pueda leer el archivo
     */
    public static void importarCSV(String ruta, CupiViajes cv)throws PersistenciaException
    {
        try
        {
            File f = new File (ruta);

            BufferedReader in = new BufferedReader ( new FileReader( f ) );
            String linea = in.readLine( );

            while (in.readLine( )!=null)
            {
                String[] partes = linea.split( SEPARADOR );
                System.out.println( partes[0]+ partes[1]+ Integer.parseInt( partes[2] ) + Double.parseDouble( partes[3] )+ partes[4] );
                cv.agregarHotel( partes[0], partes[1], Integer.parseInt( partes[2] ), Double.parseDouble( partes[3] ), partes[4]  );
                linea=in.readLine( );
            }
            in.close( );
        }
        catch (Exception e)
        {
            e.printStackTrace( );
            throw new PersistenciaException( e.getMessage( ), ruta );
        }
    }

    /**
     * Crea un reporte de texto con las reservas realizadas hasta el moemnto.
     * @param ruta. La ruta donde se desea guardar el reporte. ruta != null
     * @param cv el sistema cupiviajes del que se desea generar el reporte. cv != null
     * @throws PersistenciaException Se lanza en caso de que se genere alg�n error creando el reporte
     */
    public static void generarReporte(String ruta, CupiViajes cv) throws PersistenciaException
    {
        ArrayList reservas = cv.darReservas( );
        
        try
        {
            File f = new File (ruta);
            
            if (!f.exists( ))
            {
                f.createNewFile( );
            }
            PrintWriter out = new PrintWriter (f);
            out.println( LINEA_PUNTEADA );
            out.println( "Reporte reservas" );
            out.println( LINEA_PUNTEADA );
            
            for ( int i=0; i<reservas.size( ); i++ )
            {
                ReservaViaje r = (ReservaViaje)reservas.get( i );
                out.println( "Reserva No. " + (i+1) + ":"  );
                out.println( "Nombre hotel: " + r.darHotel( ).darNombre( ) );
                out.println( "ID Cliente: " + r.darCedula( ) );
                out.println( "Fecha llegada: " + r.darFechaLlegada( ) );
                out.println( "Nombre cliente: " + r.darNombreCliente( ) );
                out.println( "Noches estadía: " + r.darCantidadNochesEstadia( ) );
                out.println( "Cantidad adultos: " + r.darCantidadAdultos( ) );
                out.println( "Cantidad niños: " + r.darCantidadNinios( ) );
                out.println( "Aerolínea: " + r.darAerolinea( ) );
                out.println( "Costo total reserva: " + r.darCostoTotal( ) );
                out.println( LINEA_PUNTEADA );
            }
            out.close( );
        }
        catch (Exception e)
        {
           throw new PersistenciaException( ruta, e.getMessage( ) ) ;
        }
    }
}
