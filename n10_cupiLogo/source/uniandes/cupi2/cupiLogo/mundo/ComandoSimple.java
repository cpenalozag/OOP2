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
 * Representa un comando simple.
 */
public abstract class ComandoSimple extends Comando
{

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un comando simple.
     */
    public ComandoSimple( )
    {
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Guarda el comando a través del flujo de escritura que llega por parámetro.<br>
     * <b>post:</b> Se ha guardado el nombre del comando.
     * @param pPw Flujo de escritura del archivo. pPw != null.
     */
    public void guardar( PrintWriter pPw )
    {
        pPw.println( nombre );
    }
}
