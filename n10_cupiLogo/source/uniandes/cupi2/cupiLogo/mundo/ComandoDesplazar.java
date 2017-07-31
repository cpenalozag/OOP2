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
 * Comando que desplaza la tortuga en el tablero.
 */
public class ComandoDesplazar extends ComandoTransformacion
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "desplazar";
    
    /**
     * Valor para el desplazamiento hacia adelante.
     */
    public static final int ADELANTE = 0;
    
    /**
     * Valor para el desplazamiento hacia atrás.
     */
    public static final int ATRAS = 1;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando desplazar.</br>
     * <b>post:</b>Se ha asignado la distancia a desplazar, dirección y el nombre del comando.
     * @param pValor Distancia del desplazamiento la tortuga. pValor > 0.
     * @param pDireccion Dirección del desplazamiento. pDireccion == ADELANTE || pDireccion == ATRAS.
     */
    public ComandoDesplazar( double pValor, int pDireccion )
    {
        super( pValor, pDireccion );
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta el comando definido en el tablero que viene por parámetro.<br>
     * <b>post:</b> Se ha actualizado la posición de la tortuga.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edición. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
    	Double dir = pTortuga.darOrientacion()*(Math.PI/180);
    	int x = 0;
    	int y = 0;
    	
    	if (direccion==ADELANTE)
    	{
    		x = pTortuga.darXActual() + (int)(valor * Math.cos(dir));
    		y = pTortuga.darYActual() + (int)(valor * Math.sin(dir));
    	}
    	if (direccion==ATRAS)
    	{
    		x = pTortuga.darXActual() - (int)(valor * Math.cos(dir));
    		y = pTortuga.darYActual() - (int)(valor * Math.sin(dir));
    	}
    	if (pTortuga.estaPintando())
    	{
    		pG.drawLine(pTortuga.darXActual(), pTortuga.darYActual(), x, y);
    	}
    	pTortuga.modificarXActual(x);
    	pTortuga.modificarYActual(y);
    }

}
