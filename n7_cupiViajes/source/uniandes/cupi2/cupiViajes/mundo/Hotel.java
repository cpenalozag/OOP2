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

/**
 * Clase que representa un hotel. <br>
 * <b> inv: </b> <br>
 * nombre != null && nombre != "". <br>
 * ciudad != null && ciudad != "". <br>
 * estrellas >= 0 && estrellas <= 5. <br>
 * costoNoche > 0. <br>
 */
public class Hotel
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Nombre del hotel.
     */
    private String nombre;

    /**
     * Ciudad en la que se encuentra el hotel.
     */
    private String ciudad;

    /**
     * Cantidad de estrellas del hotel.
     */
    private int estrellas;

    /**
     * Costo por noche de estad�a en el hotel.
     */
    private double costoNoche;

    /**
     * Ruta de la imagen del hotel.
     */
    private String rutaImagen;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Inicializa el hotel con los valores dados por par�metro. <br>
     * <b> post: </b> Los atributos nombre, ciudad, estrellas, costoNoche y rutaImagen fueron inicializados con los valores dados por par�metro.
     * @param pNombre Nombre del hotel. pNombre != null && pNombre != "".
     * @param pCiudad Ciudad en la que se encuentra el hotel. pCiudad != null && pCiuidad != "".
     * @param pEstrellas Estrellas del hotel. pEstrellas >= 0 && pEstrellas <= 5.
     * @param pCostoNoche Costo por noche de estad�a en el hotel. pCostoNoche > 0.
     * @param pRutaImagen Ruta de la imagen del hotel. pRutaImagen != null && pRutaImagen != "".
     */
    public Hotel( String pNombre, String pCiudad, int pEstrellas, double pCostoNoche, String pRutaImagen )
    {
        nombre = pNombre;
        ciudad = pCiudad;
        estrellas = pEstrellas;
        costoNoche = pCostoNoche;
        rutaImagen = pRutaImagen;
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna el nombre del hotel.
     * @return Nombre del hotel.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la ciudad en la que se encuentra el hotel.
     * @return Ciudad en la que se encuentra el hotel.
     */
    public String darCiudad( )
    {
        return ciudad;
    }

    /**
     * Retorna la cantidad de estrellas del hotel.
     * @return Cantidad de estrellas del hotel.
     */
    public int darEstrellas( )
    {
        return estrellas;
    }

    /**
     * Retorna el costo por noche de estad�a en el hotel.
     * @return Costo por noche de estad�a.
     */
    public double darCostoNoche( )
    {
        return costoNoche;
    }

    /**
     * Retorna la ruta de la imagen del hotel.
     * @return Ruta de la imagen del hotel.
     */
    public String darRutaImagen( )
    {
        return rutaImagen;
    }

    /**
     * Retorna una cadena con el nombre del hotel.
     * @return Representaci�n del hotel en una cadena de caracteres.
     */
    public String toString( )
    {
        return nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * nombre != null && nombre != "". <br>
     * ciudad != null && ciudad != "". <br>
     * estrellas >= 0 && estrellas <= 5. <br>
     * costoNoche > 0. <br>
     */
    private void verificarInvariante( )
    {
        assert nombre!= null && nombre != ""; 
        assert ciudad!= null && ciudad != ""; 
        assert estrellas>= 0 && estrellas <= 5;
        assert costoNoche>0; 
    }

}
