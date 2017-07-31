package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Comparador de reservas por costo.
 * @author c.penaloza
 *
 */

public class ComparadorReservaCosto implements Comparator<ReservaViaje>
{

    public int compare( ReservaViaje r1, ReservaViaje r2 )
    {
        if(r1.darCostoTotal( )==r2.darCostoTotal( ))
        {
            return 0;
        }

        else if (r1.darCostoTotal( )>r2.darCostoTotal( ))
        {
            return 1; 
        }
        else 
        {
            return -1; 
        }
    }


}
