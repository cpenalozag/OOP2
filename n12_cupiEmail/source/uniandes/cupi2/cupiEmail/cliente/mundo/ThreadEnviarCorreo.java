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
 * Clase que representa un hilo de ejecución cuando se quiere enviar un correo.
 */
public class ThreadEnviarCorreo extends Thread
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
    
    /**
     * Login de los destinatarios.
     */
    private String loginDestinatarios;
    
    /**
     * Asunto del correo.
     */
    private String asunto;
    
    /**
     * Mensaje del correo.
     */
    private String mensaje;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo hilo para enviar un correo.<br>
     * <b>post: </b> Se inicializaron el cliente y la interfaz principal con los valores dados por parámetro.
     * @param pCliente Clase principal del mundo con la información del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     * @param pLoginDestinatarios Login de los destinatarios del correo. pLoginDestinatarios != null && pLoginDestinatarios != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pMensaje Mensaje del correo. pMensaje != null && pMensaje != "". 
     */
    public ThreadEnviarCorreo( ClienteCupiEmail pCliente, InterfazCliente pPrincipal, String pLoginDestinatarios, String pAsunto, String pMensaje )
    {
        cliente = pCliente;
        principal = pPrincipal;
        loginDestinatarios = pLoginDestinatarios;
        asunto = pAsunto;
        mensaje = pMensaje;
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que envía el correo.<br>
     * <b>post: </b> Se envió el correo.<br>
     */
    public void run( )
    {
        try
        {
            cliente.enviarCorreo( loginDestinatarios, asunto, mensaje );
            principal.actualizarEscribirCorreo( );
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
