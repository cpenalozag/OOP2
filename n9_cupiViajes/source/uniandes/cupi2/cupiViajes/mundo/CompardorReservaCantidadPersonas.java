package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Comparador de reservas por la cantidad de personas
 * @author alvar-go
 *
 */
public class CompardorReservaCantidadPersonas implements Comparator<ReservaViaje>
{

    /**
    * Compara dos reservas por cantidad de personas que van a viajar.
    * @param r1 Reserva 1 para comparar. pReserva != null.
    * @param r2 Reserva 2 para comparar. pReserva != null.
    * @return Retorna 0 si las reservas tienen la misma cantidad de personas que van a viajar. <br>
    *         Retorna -1 si la reserva r2 tiene MAYOR cantidad de personas que van a viajar que r1. <br>
    *         Retorna 1 si la reserva r2 tiene MENOR cantidad de personas que van a viajar que r1.
    */
    public int compare( ReservaViaje r1, ReservaViaje r2 )
    {
        int valorComparacion = 0;
        int totalPersonas = r1.darCantidadAdultos( ) + r1.darCantidadNinios( );
        int totalPersonasOtro = r2.darCantidadAdultos( ) + r2.darCantidadNinios( );

        if( totalPersonas < totalPersonasOtro )
        {
            valorComparacion = -1;
        }
        else if( totalPersonas > totalPersonasOtro )
        {
            valorComparacion = 1;
        }

        return valorComparacion;
    }

}
