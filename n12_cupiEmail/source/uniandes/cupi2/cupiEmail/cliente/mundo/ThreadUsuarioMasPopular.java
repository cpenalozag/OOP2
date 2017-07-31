/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiEmail.cliente.mundo;

import uniandes.cupi2.cupiEmail.cliente.interfaz.InterfazCliente;

/**
 * Clase que representa un hilo de ejecuci�n cuando se quiere consultar el usuario mas popular.
 */
public class ThreadUsuarioMasPopular extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cliente de CupiEmail.
     */
    private ClienteCupiEmail cliente;
    
    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCliente principal;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo hilo para consultar el usuario m�s popular.<br>
     * @param pCliente Clase principal del mundo con la informaci�n del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public ThreadUsuarioMasPopular( ClienteCupiEmail pCliente, InterfazCliente pPrincipal )
    {
        cliente = pCliente;
        principal = pPrincipal;
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que realiza la consulta del usuario m�s popular.<br>
     * <b>post: </b> Se consult� el usuario m�s popular.<br>
     */
    public void run( )
    {
        try
        {
            String masPopular = cliente.darUsuarioMasPopular( );
            principal.actualizarMasPopular(masPopular);
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
