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
 * Comando que reinicia el tablero de edición y ubica a la tortuga en el centro del tablero con orientación 0.
 */
public class ComandoReiniciar extends ComandoSimple
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "reiniciar";

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando para reiniciar el tablero.</br>
     * <b>post:</b>El nombre del comando se ha asignado.
     */
    public ComandoReiniciar( )
    {
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta el comando definido en la tortuga que viene por parámetro.<br>
     * <b>post:</b>El tablero no tiene nada pintado y la tortuga está en el centro con orientación 0.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edición. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
        int ancho = pTortuga.darXInicial( )*2;
        int alto = pTortuga.darYInicial( )*2;
        
        pG.clearRect( 0, 0, ancho, alto );

        pTortuga.reiniciarTortuga( );;
    }
}
