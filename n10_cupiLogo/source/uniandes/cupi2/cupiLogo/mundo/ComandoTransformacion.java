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
 * Comando que modifica la tortuga.
 */
public abstract class ComandoTransformacion extends Comando
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Valor de la transformación.
     */
    protected double valor;

    /**
     * Direccion de la transformación.
     */
    protected int direccion;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando transformación.<br>
     * <b>post:</b> Se ha asignado el valor y dirección del comando.
     * @param pValor Valor de la transformación. pValor > 0.
     * @param pDireccion Dirección de la transformación. pDireccion == 0 || pDireccion == 1.
     */
    public ComandoTransformacion( double pValor, int pDireccion )
    {
        valor = pValor;
        direccion = pDireccion;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Guarda el comando a través del flujo de escritura que llega por parámetro.<br>
     * <b>post:</b> Se ha guardado el nombre, valor y dirección del comando separado por espacios.
     * @param pPw Flujo de escritura del archivo. pPw != null.
     */
    public void guardar( PrintWriter pPw )
    {
        pPw.println( nombre + " " + valor + " " + direccion );
    }
}
