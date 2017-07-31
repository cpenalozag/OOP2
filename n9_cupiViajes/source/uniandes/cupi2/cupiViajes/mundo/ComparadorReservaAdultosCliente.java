package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

public class ComparadorReservaAdultosCliente implements Comparator<ReservaViaje>
{

    /**
     * Compara dos reservas de acuerdo a la cantidad de adultos y al nombre del cliente que realizó la reserva
     * @param r1 La primera reserva para realizar la comapración. r1 != null
     * @param r2 La primera reserva para realizar la comapración. r2 != null
     * @return  1 si la reserva r1 tiene más adultos que la reserva r2
     *          -1 si la reserva r1 tiene menos adultos que la reserva r2
     *          1 si la reserva r1 tiene la misma cantidad de adultos que la reserva r2 y la reserva r1 tiene "mayor" nombre de cliente alfabéticamente que la reserva r2
     *          -1 si la reserva r1 tiene la misma cantidad de adultos que la reserva r2 y la reserva r1 tiene "menor" nombre de cliente alfabéticamente que la reserva r2
     *          0 si las reservas tienen la misma cantidad de adultos y nombre de cliente
     */
    public int compare( ReservaViaje o1, ReservaViaje o2 )
    {
        int c1 = o1.darCantidadAdultos( ) - o2.darCantidadAdultos( );
        if( c1 > 0 )
        {
            return 1;
        }
        else if(c1 < 0)
        {
            return -1;
        }
        else
        {
            int c2 = o1.darNombreCliente( ).compareTo( o2.darNombreCliente( ) );
            if( c2 > 0 )
            {
                return 1;
            }
            else if( c2 < 0 )
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

}
