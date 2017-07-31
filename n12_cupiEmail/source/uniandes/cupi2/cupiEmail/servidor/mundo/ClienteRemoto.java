package uniandes.cupi2.cupiEmail.servidor.mundo;

/**
 * Esta clase mantiene la informaci�n del usuario y sus correos <br>
 * <b>inv:</b><br>
 * nombreJugador != null<br>
 * encuentrosGanados >= 0<br>
 * encuentrosPerdidos >= 0<br>
 */
public class ClienteRemoto {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
     * El login del usuario
     */
	private String login;
	
    /**
     * El nombre del usuario
     */
    private String nombres;

    /**
     * El apellido del usuario
     */
    private String apellidos;
    
    /**
     * La contraseña del usuario
     */
    private String contrasena;
    
    /**
     * El estado de conección del usuario
     */
    private String conectado;
    
    /**
     * Crea un nuevo registro
     * @param loginP Login del usuario
     * @param nombresP Nombres del usuario
     * @param apellidosP Apellidos del usuario
     * @param contrasenaP Contrasena del usuario
     * @param conectadoP Estado de conección del usuario
     */
    public ClienteRemoto( String loginP, String nombresP, String apellidosP, String contrasenaP, String conectadoP )
    {
    	login = loginP;
        nombres = nombresP;
        apellidos = apellidosP;
        contrasena = contrasenaP;
        conectado = conectadoP;
        
        //verificarInvariante( );
    }
    
 // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el login del usuario
     * @return nombresJugador
     */
    public String darLogin( )
    {
        return login;
    }
    
    /**
     * Retorna los nombres del usuario
     * @return nombresJugador
     */
    public String darNombresUsuario( )
    {
        return nombres;
    }
    
    /**
     * Retorna los apellidos del usuario
     * @return nombresJugador
     */
    public String darApellidos( )
    {
        return apellidos;
    }
    
    /**
     * Retorna la contraseña del usuario
     * @return nombresJugador
     */
    public String darContrasena()
    {
        return contrasena;
    }
    
    /**
     * Retorna el estado de conección del usuario
     * @return nombresJugador
     */
    public String darEstadoConeccion( )
    {
        return conectado;
    }
    
   /**
    * Devuelve la representacion en caracteres del cliente
    * @return nombres y apellidos del usuario
    */
    public String toString()
    {
    	return login;
    }


}
