package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

public class ComparadorHotelEstrellas implements Comparator <ReservaViaje>
{

    
    public int compare( ReservaViaje r1, ReservaViaje r2 )
    {
        int diferencia;
        if ( r1.darAerolinea( ).compareTo( r2.darAerolinea( ) ) == 0 )
        {
            if (r1.darNombreCliente( ).compareTo( r2.darNombreCliente( )) > 0 )
                    {
                diferencia=1;
                    }
            else if (r1.darNombreCliente( ).compareTo( r2.darNombreCliente( ) )==0)
            {
                diferencia=0;
            }
            else{
                diferencia=-1;
            }
        }
        else if ( r1.darAerolinea( ).compareTo( r2.darAerolinea( ) ) > 0 )
        {
            diferencia = 1;
        }
        else{
            diferencia = -1; 
        }
        return diferencia;
    }

}
