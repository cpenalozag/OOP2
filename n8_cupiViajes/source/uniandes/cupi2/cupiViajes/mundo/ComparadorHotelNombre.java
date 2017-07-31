package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Comaprador de hoteles por nombre
 * @author alvar-go
 *
 */
public class ComparadorHotelNombre implements Comparator<Hotel>
{

    /**
     * Compara dos hoteles por el nombre.
     * @param h1 primer hotel para comparar. h1 != null.
     * @param h2 segundo hotel para comparar. h2 != null.
     * @return Retorna 0 si los hoteles tienen el mismo nombre. <br>
     *         Retorna -1 si el hotel h2 tiene un valor "MAYOR" para el nombre que h1. <br>
     *         Retorna 1 si el hotel h2 tiene un valor "MENOR" para el nombre que h1.
     */
    public int compare( Hotel h1, Hotel h2 )
    {
        int valorComparacion = 0;

        if( h1.darNombre( ).toLowerCase( ).compareTo( h2.darNombre( ).toLowerCase( ) ) < 0 )
        {
            valorComparacion = -1;
        }
        else if( h1.darNombre( ).toLowerCase( ).compareTo( h2.darNombre( ).toLowerCase( ) ) > 0 )
        {
            valorComparacion = 1;
        }

        return valorComparacion;
    }

}
