package uniandes.cupi2.ordenador;

import java.util.Comparator;
import java.util.List;

/**
 * Clase g�nerica que se encarga de ordenar colecciones de elementos de tipo T
 * @author alvar-go
 * @param <T> tipo de elementos que debe contener la colecci�n a ordenar
 */
public class Ordenador<T> 
{
    /**
     * Ordena la lista que llega por par�metro en el sentido que llega por par�metro usando el comparador que llega por par�metro
     * post: la lista se encuentra ordenada por los cirterios ingresados
     * @param ordenamiento el algoritmo de ordenamieto que se debe usar. ordenamiento pertenece a la enumeraci�n Ordenamiento
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendente indica si se debe ordenar de manera ascendente, de lo contrario se debe ordenar descendentemente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    public void ordenar(AlgoritmoOrdenamiento ordenamiento, List<T> lista, boolean ascendente, Comparator<T> comparador)
    {
        if(ordenamiento.equals(AlgoritmoOrdenamiento.BURBUJA))
        {
            ordenarBurbuja(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.SELECCION))
        {
            ordenarSeleccion(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.INSERCION))
        {
            ordenarInsercion(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.SHAKER))
        {
            ordenarShaker(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.GNOME))
        {
            ordenarGnome(lista, ascendente, comparador);
        }
    }

    /**
     * Ordena la lista usando el algoritmo de inscerci�n
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarInsercion(List<T> lista,boolean ascendnte, Comparator<T> comparador) {

        for (int i = 0; i < lista.size( ); i++)
        {
            for(int j = i; j>0; j--)
            {
                T menorI = lista.get( j-1 );
                T otro = lista.get( j );

                if(ascendnte){
                    if(comparador.compare( menorI, otro )>0)
                    {
                        T temp = lista.get( j );
                        lista.set( j, menorI );
                        lista.set( j-1, temp);

                    }
                }
                else
                {
                    if(comparador.compare( menorI, otro )<0)
                    {
                        T temp = lista.get( j );
                        lista.set( j, menorI );
                        lista.set( j-1, temp);

                    }
                }
            }
        }

    }

    /**
     * Ordena la lista usando el algoritmo de selecci�n
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lsita != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarSeleccion(List<T> lista,boolean ascendnte, Comparator<T> comparador) {

        for( int i = 0; i<lista.size( )-1; i++ )
        {
            T menor = lista.get( i );
            int cual = i; 
            for ( int j = i+1; j<lista.size( ); j++)
            {
                if(ascendnte){
                    T actual  = lista.get( j );
                    if(comparador.compare(menor, actual ) > 0)
                    {
                        menor = lista.get( j );
                        cual = j; 
                    }
                }
                else{
                    T actual  = lista.get( j );
                    if(comparador.compare(menor, actual ) < 0)
                    {
                        menor = lista.get( j );
                        cual = j; 
                    }
                }
            }
            T temp = lista.get( i );
            lista.set( i, menor );
            lista.set( cual, temp );
        }
    }


    /**
     * Ordena la lista usando el algoritmo de burbuja
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lsita != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarBurbuja(List<T> lista,boolean ascendnte, Comparator<T> comparador) {


        for(int i = lista.size( ); i>0; i--)
        {
            for(int j = 0; j < i-1; j++)
            {
                T t1 = lista.get( j ); 
                T t2 = lista.get( j+1 );

                if(ascendnte)
                {
                    if(comparador.compare( t1, t2 ) > 0)
                    {
                        T temp = lista.get( j );
                        T temp2 = lista.get( j+1 );
                        lista.set( j+1, temp );
                        lista.set( j, temp2 );
                    }
                }
                else
                {
                    if(comparador.compare( t1, t2 ) < 0)
                    {
                        T temp = lista.get( j );
                        T temp2 = lista.get( j+1 );

                        lista.set( j+1, temp );
                        lista.set( j, temp2 );
                    }
                }

            }
        }
    }

    /**
     * Ordena la lista usando el algoritmo de shake (burbuja bidireccional)
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lsita != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarShaker(List<T> lista,boolean ascendnte, Comparator<T> comparador)
    {
        if(ascendnte){
            int fin = lista.size( )-1, ini = 0; 
            while (ini <= fin)
            {
                for(int j = ini; j < fin; j++)
                {
                    T t1 = lista.get( j );
                    T t2 = lista.get( j + 1 );
                    if(comparador.compare( t1, t2 ) > 0)
                    {
                        lista.set( j, t2 );
                        lista.set( j+1, t1 );
                    }
                }
                fin--;

                for(int j = fin; j > ini; j--)
                {
                    T t1 = lista.get( j );
                    T t2 = lista.get( j - 1);
                    if(comparador.compare( t1, t2 ) < 0)
                    {
                        lista.set( j, t2 );
                        lista.set( j-1, t1 );
                    }
                }
                ini++;
            }
        }
        else{
            int fin = lista.size( ) - 1, ini = 0; 
            while( ini <= fin)
            {
                for( int j = ini; j < fin; j++ )
                {
                    T t1 = lista.get( j );
                    T t2 = lista.get( j + 1 );
                    if(comparador.compare( t1, t2 ) < 0)
                    {
                        lista.set( j, t2 );
                        lista.set( j+1, t1 );
                    }
                }
                fin--;
                for( int j = fin; j > ini; j-- )
                {
                    T t1 = lista.get( j );
                    T t2 = lista.get( j - 1);
                    if(comparador.compare( t1, t2 )>0)
                    {
                        lista.set( j, t2 );
                        lista.set( j-1, t1 );
                    }
                }
                ini++;
            }
        }

    }

    /**
     * Ordena la lista usando el algoritmo de Gnome
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lsita != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarGnome(List<T> lista,boolean ascendnte, Comparator<T> comparador) {

        if(ascendnte)
        {
            int i = 1; 
            while ( i <lista.size( ) )
            {
                T t2 = lista.get( i );
                T t1 = lista.get( i-1 );
                if( comparador.compare( t1, t2 ) <= 0 )
                {
                    i++; 
                }
                else 
                {
                    lista.set( i, t1 );
                    lista.set( i-1, t2 );  
                    i--;

                    if(i==0)
                    {
                        i = 1; 
                    }
                }

            }
        }
        else
        {
            int i = 1; 
            while ( i <lista.size( ) )
            {
                T t2 = lista.get( i );
                T t1 = lista.get( i-1 );
                if( comparador.compare( t1, t2 ) >= 0 )
                {
                    i++; 
                }
                else {
                    lista.set( i, t1 );
                    lista.set( i-1, t2 );  
                    i--;

                    if(i==0)
                    {
                        i = 1; 
                    }
                }

            }
        }

    }
}
