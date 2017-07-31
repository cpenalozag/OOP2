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
package uniandes.cupi2.cupiViajes.mundo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Clase que representa CupiViajes. <br>
 * <b> inv: </b> <br>
 * hoteles != null. <br>
 * reservas != null. <br>
 * No pueden existir dos reservas con el mismo cliente.
 */
public class CupiViajes
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Lista de hoteles disponibles en el sistema de viajes.
     */                                     
    private ArrayList<Hotel> hoteles;

    /**
     * Lista de reservas.
     */
    private ArrayList<ReservaViaje> reservas;

    private CompardorReservaCantidadPersonas comparadorPersonas; 

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Construye un nuevo sistema de viajes sin hoteles y sin reservas.<br>
     * <b> post: </b> Las listas de hoteles y de viajes han sido inicializadas.
     */
    public CupiViajes( )
    {
        hoteles = new ArrayList<Hotel>( );
        reservas = new ArrayList<ReservaViaje>( );
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna la lista de hoteles disponibles en el sistema.
     * @return Lista de hoteles disponibles.
     */
    public ArrayList<Hotel> darHoteles( )
    {
        return hoteles;
    }

    /**
     * Retorna la lista de reservas.
     * @return Lista de reservas.
     */
    public ArrayList<ReservaViaje> darReservas( )
    {
        return reservas;
    }

    /**
     * Agrega una nueva reserva a la lista si no existe una reserva con el cliente dado. <br>
     * <b> pre </b>: El hotel con el nombre dado ya existe.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pFechaLLegada fecha de llegada al hotel que se va a reservar. pDiaLlegada !=null.
     * @param pNombreCliente Nombre del cliente responsable de la reserva. pCliente != null && pCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar al hotel que se va a reservar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de ni�os que van a viajar al hotel que se va a reservar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estad�a en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerol�nea que se va a reservar. pAerolinea != null
     * @return True si la reserva se agreg� correctamente. False de lo contrario.
     */
    public boolean agregarReserva( Hotel pHotel, Date pFechaLLegada, String pNombreCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea )
    {
        ReservaViaje buscado = buscarReserva( pNombreCliente );
        boolean agregado = false;

        if( buscado == null )
        {
            ReservaViaje reservaAAgregar = new ReservaViaje( pNombreCliente, pCantidadAdultos, pCantidadNinios, pNochesEstadia, pAerolinea, pHotel, pFechaLLegada );
            reservas.add( reservaAAgregar );
            agregado = true;
            verificarInvariante( );
        }

        return agregado;
    }


    /**
     * Agrega un nuevo hotel a la lista de hoteles. <br>
     * @param pNombre Nombre del hotel. pNombre != null && pNombre != "".
     * @param pCiudad Ciudad en la que se encuentra el hotel. pCiudad != null && pCiudad != "".
     * @param pEstrellas Cantidad de estrellas que tiene el hotel. pEstrellas >= 1 && pEstrellas <=5.
     * @param pCostoNoche Costo por noche en el hotel. pCostoNoche >= 1.
     * @param pRutaImagen Ruta de la imagen del hotel. pRutaImagen != null && pRutaImagen != "".
     */
    public void agregarHotel( String pNombre, String pCiudad, int pEstrellas, double pCostoNoche, String pRutaImagen )
    {
        Hotel hotel = new Hotel( pNombre, pCiudad, pEstrellas, pCostoNoche, pRutaImagen );
        hoteles.add( hotel );
    }

    /**
     * Busca los hoteles que est�n en la ciudad dada por par�metro.
     * @param pCiudad Ciudad  de los hoteles que se est�n buscando. pCiudad != null && pCiudad != "".
     * @return Lista con los hoteles que est�n en la ciudad dada.
     */
    public ArrayList<Hotel> buscarHotelesCiudad( String pCiudad )
    {
        ArrayList <Hotel> respuesta = new ArrayList<>( ); 

        for (int i =0; i <= hoteles.size( )-1; i++)
        {
            Hotel temporalHotel = (Hotel) hoteles.get( i );
            if(temporalHotel.darCiudad( ).compareToIgnoreCase( pCiudad )==0)
            {
                respuesta.add( temporalHotel );
            }
        }
        return respuesta; 

    }

    /**
     * Busca la reserva del cliente dado por par�metro. <br>
     * @param pCliente Cliente se la reserva que se va a buscar. pCliente != null && pCliente != "".
     * @return Reserva de viaje con el cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReserva( String pCliente )
    {
        ReservaViaje temporal = null; 
        for(int i = 0; i <= reservas.size( )-1; i++)
        {
            ReservaViaje temporal1 = (ReservaViaje) reservas.get( i );
            if(temporal1.darNombreCliente( ).compareToIgnoreCase( pCliente )==0)
            {
                temporal = temporal1;
            }
        }
        return temporal; 
        //TODO Parte 5 Punto 1b Implemente seg�n la documentaci�n


    }

    /**
     * Busca la reserva del cliente dado por par�metro utilizando una b�squeda binaria. <br>
     * <b> pre: </b> La lista de reservas se encuentra ordenada por clientes.
     * @param pNombreCliente Nombre del cliente de la reserva que se va a buscar. pNombreCliente != null && pNombreCliente != "".
     * @return Reserva de viaje del cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReservaPorClienteBinario( String pNombreCliente )
    {
        int medio; int sup; int inf; 

        ReservaViaje temp = null; 
        ReservaViaje respuesta = null; 
        sup = reservas.size( )-1;
        inf = 0; 
        boolean encontrado = false;
        while( inf <= sup && !encontrado)
        {
            medio = (inf + sup)/2; 
            temp = reservas.get( medio );
            if(pNombreCliente.compareToIgnoreCase( temp.darNombreCliente( ) )==0)
            {
                respuesta = temp; 
                encontrado = true; 
            }
            else if(pNombreCliente.compareToIgnoreCase( temp.darNombreCliente( ) )<0)
            {
                sup = medio -1;
            }
            else
            {
                inf = medio+1; 
            }
        }
        return respuesta; 

    }

    /**
     * Busca la primera reserva del viaje a la ciudad dada por par�metro.
     * @param pCiudad Ciudad del hotel de la reserva que se va a buscar. pCiudad != null && pCiudad != "";
     * @return Reserva del viaje a la ciudad dada por par�metro.
     */
    public ReservaViaje buscarReservaPorCiudad( String pCiudad )
    {
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp = null;
        boolean encontrado = false;

        for( int i = 0; i < reservas.size( ) && !encontrado; i++ )
        {
            reservaTemp = reservas.get( i );
            if( reservaTemp.darHotel( ).darCiudad( ).compareToIgnoreCase( pCiudad )==0 )
            {
                buscado = reservaTemp;
                encontrado = true;
            }
        }

        return buscado;
    }

    /**
     * Busca la reserva con la mayor cantidad de personas que van a viajar.
     * @return Reserva con la mayor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la mayor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMasPersonas( )
    {
        ReservaViaje respuesta = null; 
        int mayor = 0;
        int actual = 0;

        if(reservas.size( )==0)
        {
            return respuesta; 
        }
        for(int i = 0; i< reservas.size( ); i++)
        {
            actual  = reservas.get( i ).darCantidadAdultos( )+reservas.get( i ).darCantidadNinios( );

            if(actual> mayor)
            {
                mayor = actual; 
                respuesta = reservas.get( i );
            }

        }
        return respuesta; 

    }

    /**
     * Busca la reserva con la menor cantidad de personas que van a viajar.
     * @return Reserva con la menor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la menor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMenosPersonas( )
    {
        ReservaViaje respuesta = null; 
        int menor = 100;
        int actual = 0;

        if(reservas.size( )==0)
        {
            return respuesta; 
        }
        for(int i = 0; i< reservas.size( ); i++)
        {
            actual  = reservas.get( i ).darCantidadAdultos( )+reservas.get( i ).darCantidadNinios( );

            if(actual < menor)
            {
                menor = actual; 
                respuesta = reservas.get( i );
            }
        }
        return respuesta; 

    }

    /**
     * Busca las reservas con aerol�nea dada por par�metro.
     * @param pAerolinea Aerol�nea de las reservas que se est�n buscando. pAerolinea != "" && pAerolinea pertenece a {ReservaViaje.AVIANCA, ReservaViaje.LAN,
     *        ReservaViaje.VIVA_COLOMBIA, ReservaViaje.SATENA, ReservaViaje.JETBLUE, ReservaViaje.IBERIA, ReservaViaje.AIR_FRANCE}.
     * @return Lista de reservan con aerol�nea dada.
     */
    public ArrayList<ReservaViaje> buscarReservasAerolinea( Aerolinea pAerolinea )
    {
        ArrayList<ReservaViaje> respuesta = new ArrayList<ReservaViaje>( );
        ReservaViaje reservaTemp = null;

        for( int i = 0; i < reservas.size( ); i++ )
        {
            reservaTemp = reservas.get( i );
            if( reservaTemp.darAerolinea( ).equals( pAerolinea ) )
            {
                respuesta.add( reservaTemp );
            }
        }

        return respuesta;
    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * hoteles != null. <br>
     * reservas != null. <br>
     * No pueden existir dos reservas con el mismo cliente.
     */
    private void verificarInvariante( )
    {
        assert ( hoteles != null ) : "La lista de hoteles no puede ser nula.";
        assert ( reservas != null ) : "La lista de reservas no puede ser nula.";
        assert ( !buscarReservasMismoCliente( ) ) : "No pueden existir reservas con el mismo cliente.";
    }

    /**
     * Verifica si hay dos reservas con el mismo cliente.
     * @return True si hay dos reservas con el mismo cliente. False de lo contrario.
     */
    private boolean buscarReservasMismoCliente( )
    {
        boolean hay = false;
        for( int i = 0; i < reservas.size( ) && !hay; i++ )
        {
            ReservaViaje reservaI = ( ReservaViaje )reservas.get( i );
            for( int j = i + 1; j < reservas.size( ) && !hay; j++ )
            {
                ReservaViaje reservaJ = ( ReservaViaje )reservas.get( j );
                if( reservaI.darNombreCliente( ).equals( reservaJ.darNombreCliente( ) ) )
                {
                    hay = true;
                }
            }
        }
        return hay;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * M�todo para la extensi�n 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }

}
