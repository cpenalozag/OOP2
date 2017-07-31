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
 * Clase que representa un hilo de ejecuci�n cuando se quiere consultar los correos enviados.
 */
public class ThreadConsultarEnviados extends Thread
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
     * Crea un nuevo hilo para consultar los correos enviados.<br>
     * <b>post: </b> Se inicializaron el cliente y la interfaz principal con los valores dados por par�metro.
     * @param pCliente Clase principal del mundo con la informaci�n del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public ThreadConsultarEnviados( ClienteCupiEmail pCliente, InterfazCliente pPrincipal )
    {
        cliente = pCliente;
        principal = pPrincipal;
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que realiza la consulta de correos enviados.<br>
     * <b>post: </b> Se consultaron los correos en el servidor.<br>
     */
    public void run( )
    {
        try
        {
            cliente.consultarEnviados( );
            principal.actualizarCorreosEnviados( );
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
