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
/**
 * Clase que representa un correo enviado.
 */
public class CorreoEnviado
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Login del usuario que escribió el correo.
     */
    private String loginDestinatarios;

    /**
     * Fecha de envío del correo.
     */
    private String fechaEnvio;

    /**
     * Asunto del correo.
     */
    private String asunto;

    /**
     * Texto del correo.
     */
    private String mensaje;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo correo con los valores dados por parámetro. <br>
     * <b> post: </b> El login de destinatarios, la fecha de envío , el asunto el mensajecon los valores dados por parámetro.
     * @param pLoginDestinatarios login del remitente. pLoginDestinatarios != null && pLoginDestinatarios != "".
     * @param pFechaEnvio Fecha de envío del correo. pFechaEnvio != null && pFechaEnvio != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pMensaje Texto del correo. pMensaje != null && pMensaje != "".
     */
    public CorreoEnviado( String pLoginDestinatarios, String pFechaEnvio, String pAsunto, String pMensaje)
    {
        loginDestinatarios = pLoginDestinatarios;
        fechaEnvio = pFechaEnvio;
        asunto = pAsunto;
        mensaje = pMensaje;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna login del remitente.
     * @return Login del remitente.
     */
    public String darLoginDestinatarios( )
    {
        return loginDestinatarios;
    }

    /**
     * Retorna la fecha de envío del correo.
     * @return Fecha de envío del correo.
     */
    public String darFechaEnvio( )
    {
        return fechaEnvio;
    }

    /**
     * Retorna el asunto del correo.
     * @return Asunto del correo.
     */
    public String darAsunto( )
    {
        return asunto;
    }

    /**
     * Retorna el texto del correo.
     * @return Texto del correo.
     */
    public String darMensaje( )
    {
        return mensaje;
    }
}
