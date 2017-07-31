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

import java.io.PrintWriter;

/**
 * Comando que modifica el estado de la trayectoria de la tortuga en el tablero.
 */
public abstract class ComandoTrayectoria extends Comando
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Valor que modifica trayectoria.
     */
    protected int valor;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando de Trayectoria.<br>
     * <b>post:</b> Se ha asignado el valor del comando.
     * @param pValor Valor que modifica la trayectoria. pValor < Integer.MAX_VALUE && pValor > Integer.MIN_VALUE. 
     */
    public ComandoTrayectoria( int pValor )
    {
        valor = pValor;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Guarda el comando a través del flujo de escritura que llega por parámetro.<br>
     * <b>post:</b> Se ha guardado el nombre y valor del comando separado por espacios.
     * @param pPw Es el flujo de escritura del archivo. pPw != null.
     */
    public void guardar( PrintWriter pPw )
    {
        pPw.println( nombre + " " + valor );
    }
}
