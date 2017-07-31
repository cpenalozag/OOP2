/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Graphics2D;

/**
 * Comando que modifica el estado de pintar la trayectoria de la tortuga.
 */
public class ComandoModificarPintando extends ComandoTrayectoria
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "modificarPintando";
    
    /**
     * Valor para activar el estado de pintando.
     */
    public final static int PINTA = 1;
    
    /**
     * Valor para activar el estado de no pintando.
     */
    public final static int NO_PINTA = 0;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando modificar pintando.<br/>
     * <b>post:</b> El valor y nombre del comando se han inicializado.
     * @param pValor Valor que representa activar o desactivar el estado de pintando. pValor == PINTA || pValor == NO_PINTA.
     */
    public ComandoModificarPintando( int pValor )
    {
        super( pValor );
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta el comando de modificar el estado pintando de la tortuga.<br>
     * <b>post:</b> Se ha actualizado el valor de dibujo de la trayectoria en la tortuga. 
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edición. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
        if(valor == PINTA)
        {
            pTortuga.modificarPintando( true );   
        }
        else if(valor==NO_PINTA)
        {
            pTortuga.modificarPintando( false );
        }
    }
}
