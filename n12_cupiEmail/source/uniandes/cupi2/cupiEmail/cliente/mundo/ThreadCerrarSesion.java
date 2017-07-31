/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
 * Clase que representa un hilo de ejecución cuando se quiere cerrar sesión.
 */
public class ThreadCerrarSesion extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cliente de CupiEmail.
     */
    private ClienteCupiEmail cliente;

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo hilo para cerrar sesión en el servidor.<br>
     * <b>post: </b> Se inicializaron el cliente y la interfaz principal con los valores dados por parámetro.
     * @param pCliente Clase principal del mundo con la información del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public ThreadCerrarSesion( ClienteCupiEmail pCliente, InterfazCliente pPrincipal )
    {
        cliente = pCliente;
        principal = pPrincipal;
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza el cierre de sesión.<br>
     * <b>post: </b> Se cerró  la sesión en el servidor.<br>
     */
    public void run( )
    {
        try
        {
            cliente.cerrarSesion( );
            principal.actualizarCerrarSesion( );
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
