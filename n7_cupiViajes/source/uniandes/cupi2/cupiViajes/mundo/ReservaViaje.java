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

import java.util.Date;

/**
 * Clase que representa una reserva de un viaje. <br>
 * <b> inv: </b> <br>
 * nombreCliente != null && nombreCliente != "". <br>
 * cantidadAdultos >= 1. <br>
 * nochesEstadia >= 1. <br>
 * costoTotal > 0. <br>
 * aerolinea !=null. <br>
 * hotel != null. <br>
 * fechaLlegada != null. <br>
 */
public class ReservaViaje
{

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Nombre del cliente responsable de la reserva del viaje.
     */
    private String nombreCliente;

    /**
     * Cantidad de adultos por los que se va a reservar el viaje.
     */
    private int cantidadAdultos;

    /**
     * Cantidad de ni�os por los que se va a reservar el viaje.
     */
    private int cantidadNinios;

    /**
     * Cantidad de noches de estad�a en el hotel que se va a reservar.
     */
    private int cantidadNochesEstadia;

    /**
     * Costo total de la reserva.
     */
    private double costoTotal;

    /**
     * Aerol�nea que se va a reservar.
     */
    private Aerolinea aerolinea; 


    /**
     * Hotel que se va a reservar.
     */
    private Hotel hotel;

    /**
     * Fecha de llegada al hotel que se va a reservar.
     */
    private Date fechaLlegada;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Inicializa la reserva del viaje con los valores dados por par�metro. <br>
     * <b> post: </b> Los atributos cliente, cantidadAdultos, cantidadNinios, cantidadNochesEstadia, aerolinea, hotel y fechaLlegada fueron inicializados con los valores dados por par�metro. <br>
     * El costo total se inicializ� como el costo por noche de estad�a en el hotel multiplicado por la cantidad de noches de la reserva.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pNombreCliente Nombre del cliente responsable de la reserva. pNombreCliente != null && pNombreCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de ni�os que van a viajar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estad�a en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerol�nea que se va a reservar. pAerolinea != null.
     * @param pFechaLlegada Fecha de llegada al hotel que se va a reservar. pFecha != null.
     */
    public ReservaViaje( String pNombreCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea, Hotel pHotel, Date pFechaLlegada )
    {
        nombreCliente = pNombreCliente;
        cantidadAdultos = pCantidadAdultos;
        cantidadNinios = pCantidadNinios;
        cantidadNochesEstadia = pNochesEstadia;
        aerolinea = pAerolinea;
        hotel = pHotel;
        fechaLlegada = pFechaLlegada;
        costoTotal = hotel.darCostoNoche( ) * cantidadNochesEstadia;
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna el nombre del cliente responsable de la reserva.
     * @return Nombre del cliente responsable de la reserva.
     */
    public String darNombreCliente( )
    {
        return nombreCliente;
    }

    /**
     * Retorna la cantidad de adultos por los que se va reservar el viaje.
     * @return Cantidad de adultos por los que se va reservar el viaje.
     */
    public int darCantidadAdultos( )
    {
        return cantidadAdultos;
    }

    /**
     * Retorna la cantidad de ni�os por los que se va reservar el viaje.
     * @return Cantidad de ni�os por los que se va reservar el viaje.
     */
    public int darCantidadNinios( )
    {
        return cantidadNinios;
    }

    /**
     * Retorna la cantidad de noches de estad�a en el hotel que se va a reservar.
     * @return Cantidad de noches de estad�a en el hotel que se va a reservar.
     */
    public int darCantidadNochesEstadia( )
    {
        return cantidadNochesEstadia;
    }

    /**
     * Retorna el costo total de la reserva.
     * @return Costo total de la reserva.
     */
    public double darCostoTotal( )
    {
        return costoTotal;
    }

    /**
     * Retorna la aerol�nea que se va a reservar.
     * @return Aerol�nea que se va a reservar.
     */
    public Aerolinea darAerolinea( )
    {
        return aerolinea; 
    }

    /**
     * Retorna el hotel que se va a reservar.
     * @return Hotel del viaje que se va a reservar.
     */
    public Hotel darHotel( )
    {
        return hotel;
    }

    /**
     * Retorna la fecha de llegada al hotel que se va a reservar.
     * @return Fecha de llegada al hotel que se va a reservar.
     */
    public Date darFechaLlegada( )
    {
        return fechaLlegada;
    }


    /**
     * Retorna una cadena con el nombre del cliente y el hotel de la reserva.
     * @return Representaci�n de la reserva en una cadena de caracteres con el siguiente formato: <cliente> (<hotel>).
     */
    public String toString( )
    {
        return nombreCliente +" "+ "("+ hotel+")";

    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * nombreCliente != null && nombreCliente != "". <br>
     * cantidadAdultos >= 1. <br>
     * nochesEstadia >= 1. <br>
     * costoTotal > 0. <br>
     * aerolinea !=null <br>
     * hotel != null. <br>
     * fechaLlegada != null. <br>
     */
    private void verificarInvariante( )
    {
        assert nombreCliente !=null && nombreCliente !="" : 
            "La variable nombre no puede estar vacia y debe estar inicializada";
        assert cantidadAdultos >=1 : 
            "La cantidad de adultos debe ser mayor o igual a 1";
        assert cantidadNochesEstadia >= 1:
            "Las noches de estadia debe ser igual o mayor a 1";
        assert costoTotal >0: 
            "El costo de la noche debe ser mayor a 0";
        assert aerolinea != null:
            "La aerolina no esta inicializada";
        assert hotel != null: 
            "El hotel no está inicializado";
        assert fechaLlegada != null :
            "La fecha de llegada no está inicializada";
    }

}
