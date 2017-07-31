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

package uniandes.cupi2.cupiEmail.servidor.mundo;

/**
 * Clase que representa un correo electrónico.
 */
public class CorreoRemoto
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
     * Login del usuario que recibió el correo.
     */
    private String loginDestinatario;
	
    /**
     * Login del usuario que escribió el correo.
     */
    private String loginRemitente;

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

    /**
     * Indica si el correo ha sido leído o no.
     */
    private String leido;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo correo con los valores dados por parámetro. <br>
     * <b> post: </b> El login del remitente, la fecha de envío , el asunto el mensaje y el estado de leido tienen los valores dados por parámetro.
     * @param pLoginDestinatario login de destinatario.pLoginDestinatario != null && pLoginDestinatario != "".
     * @param pLoginRemitente login del remitente. pLoginRemitente != null && pLoginRemitente != "".
     * @param pFechaEnvio Fecha de envío del correo. pFechaEnvio != null && pFechaEnvio != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pMensaje Texto del correo. pMensaje != null && pMensaje != "".
     * @param pLeido Indica si el correo ha sido leído o no.
     */
    public CorreoRemoto( String pLoginDestinatario, String pLoginRemitente, String pFechaEnvio, String pAsunto, String pMensaje, String pLeido )
    {
    	loginDestinatario = pLoginDestinatario;
        loginRemitente = pLoginRemitente;
        fechaEnvio = pFechaEnvio;
        asunto = pAsunto;
        mensaje = pMensaje;
        leido = AdministradorBD.ESTADO_CORREO_NO_LEIDO;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    
    
    /**
     * Actualiza un correo
     * @param pLoginDestinatario login
     * @param pLoginRemitente login
     * @param pFechaEnvio fecha
     * @param pAsunto asunto
     * @param pMensaje mensaje
     * @param pLeido leido
     */
    public void actualizar( String pLoginDestinatario, String pLoginRemitente, String pFechaEnvio, String pAsunto, String pMensaje, String pLeido )
    {
    	loginDestinatario = pLoginDestinatario;
        loginRemitente = pLoginRemitente;
        fechaEnvio = pFechaEnvio;
        asunto = pAsunto;
        mensaje = pMensaje;
        leido = pLeido;
    }
    
    /**
     * Retorna login del remitente.
     * @return Login del remitente.
     */
    public String darLoginRemitente( )
    {
        return loginRemitente;
    }
    
    /**
     * Retorna login del destinatario.
     * @return Login del destinatario.
     */
    public String darLoginDestinatario( )
    {
        return loginDestinatario;
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

    /**
     * Retorna el estado del correo (leído o no leído).
     * @return Estado del correo.
     */
    public String darEstado( )
    {
        return leido;
    }

    /**
     * Cambia el estado del correo a leído.<br>
     * <b> post: </b> El estado del correo es leído.
     */
    public void macarComoLeido( )
    {
        leido = AdministradorBD.ESTADO_CORREO_LEIDO;
    }

    /**
     * Compara el correo con otro correo dado por parámetro teniendo en cuenta la fecha de envío.
     * @param pCorreo Correo con el que se desea comparar. pCorreo != null.
     * @return 1 en caso de que el correo sea mayor al correo dado, -1 en caso contrario y 0 si los dos son iguales.
     */
    public int compararPorFechaEnvio( CorreoRemoto pCorreo )
    {
        String[] eFecha = fechaEnvio.split( "-" );
        String dia = eFecha[ 0 ];
        String mes = eFecha[ 1 ];
        String anio = eFecha[ 2 ].substring( 0, 4 );
        String hora = eFecha[ 2 ].substring( 5, 7 );
        String minuto = eFecha[ 2 ].substring( 8, 10 );
        String[] eFechaOtro = pCorreo.darFechaEnvio( ).split( "-" );
        String diaOtro = eFechaOtro[ 0 ];
        String mesOtro = eFechaOtro[ 1 ];
        String anioOtro = eFechaOtro[ 2 ].substring( 0, 4 );
        String horaOtro = eFechaOtro[ 2 ].substring( 5, 7 );
        String minutoOtro = eFechaOtro[ 2 ].substring( 8, 10 );

        Double fecha1 = new Double( anio + mes + dia + hora + minuto );
        Double fecha2 = new Double( anioOtro + mesOtro + diaOtro + horaOtro + minutoOtro );
        Double diferencia = fecha1 - fecha2;
        if( diferencia > 0 )
            return 1;
        else if( diferencia < 0 )
            return -1;
        return 0;
    }

}