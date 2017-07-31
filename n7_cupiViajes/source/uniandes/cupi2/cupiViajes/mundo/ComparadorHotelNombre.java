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
        int diferencia = h1.darNombre( ).compareTo( h2.darNombre( ) );
        return diferencia == 0?0:(diferencia>0?1:-1);
      
    }

}
