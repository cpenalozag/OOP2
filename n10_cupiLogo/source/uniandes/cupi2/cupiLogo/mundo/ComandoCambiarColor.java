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

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Comando que cambia el color de la trayectoria de la tortuga en el tablero.
 */
public class ComandoCambiarColor extends ComandoTrayectoria
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "cambiarColor";

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando para cambiar el color de la trayectoria.<br>
     * <b>post:</b> Se ha asignado el color de la trayectoria y el nombre del comando.<br>
     * @param pValor Valor entero del color. pValor < Integer.MAX_VALUE && pValor > Integer.MIN_VALUE.
     */
    public ComandoCambiarColor( int pValor )
    {
        super( pValor );
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el color de la trayectoria para el tablero de edición que viene por parámetro.<br/>
     * <b>post:</b> Se ha actualizado el color con el que se dibuja la trayectoria de la tortuga.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edición. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
        pG.setColor( new Color( valor ) );
    }

}
