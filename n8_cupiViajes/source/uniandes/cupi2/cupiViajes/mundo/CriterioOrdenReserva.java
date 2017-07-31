package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Enumeración con los posibles citerios de ordenamiento
 * @author Alvaro
 *
 */
public enum CriterioOrdenReserva 
{
    RESERVA_COSTO_ASC("Costo ascendente", new ComparadorReservaCosto(), true),
    RESERVA_COSTO_DESC("Costo descendente", new ComparadorReservaCosto(), false),
    RESERVA_CLIENTE_ASC("Nombre cliente ascendente", new ComparadorReservaCosto(), true),
    RESERVA_CLIENTE_DESC("Nombre cliente descendente", new ComparadorReservaCosto(), false),
    RESERVA_PERSONAS_ASC("Cantidad personas ascendente", new ComparadorReservaCosto(), true),
    RESERVA_PERSONAS_DESC("Cantidad personas descendente", new ComparadorReservaCosto(), false);
    
    /**
     * Nombre del criterio
     */
    private String nombre;
    
    /**
     * Clase que sabe comparar elementos por el criterio
     */
    private Comparator<ReservaViaje> comparador;
    
    /**
     * Indica si el criterio es ascendnete o no
     */
    private boolean ascendente;
    
    /**
     * Crea un nuevo criterio de orden
     * @param nNombre el nombre del criterio. nNombre != null
     * @param nComparator el comparador por el criterio. nComparator != null
     * @param nAscendente indica si el comparador es ascendnete o descendente
     */
    private CriterioOrdenReserva(String nNombre, Comparator<ReservaViaje> nComparator, boolean nAscendente)
    {
        nombre = nNombre;
        comparador = nComparator;
        ascendente = nAscendente;
    }
    
    /**
     * Da el nombre del criterio de comparación
     * @return nombre
     */
    public String darNombre()
    {
        return nombre;
    }
    
    /**
     * Da el comparardor por el criterio
     * @return comparator
     */
    public Comparator<ReservaViaje> darComparador()
    {
        return comparador;
    }
    
    /**
     * Indica si el comparador es ascendnete o descendnete
     * @return true si es ascendente o false en caso contrario
     */
    public boolean esAscendnete()
    {
        return ascendente;
    }
    
    public String toString()
    {
        return nombre;
    }
}
