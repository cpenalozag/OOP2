package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Comparador de reservas por costo total
 * @author alvar-go
 *
 */
public class ComparadorReservaCosto implements Comparator<ReservaViaje>
{

    /**
     * Compara dos reservas por el costo total.
     * @param r1 Reserva 1 para comarar. pReserva != null.
     * @param r2 Reserva 2 para comarar. pReserva != null.
     * @return Retorna 0 si las reservas tienen el mismo costo total. <br>
     *         Retorna -1 si la reserva r2 tiene un costo total MAYOR que r1. <br>
     *         Retorna 1 si la reserva r2 tiene un costo total MENOR que r1.
     */
    public int compare( ReservaViaje r1, ReservaViaje r2 )
    {
        int valorComparacion = 0;

        if( r1.darCostoTotal( ) < r2.darCostoTotal( ) )
        {
            valorComparacion = -1;
        }
        else if( r1.darCostoTotal( ) > r2.darCostoTotal( ) )
        {
            valorComparacion = 1;
        }

        return valorComparacion;
    }

}
