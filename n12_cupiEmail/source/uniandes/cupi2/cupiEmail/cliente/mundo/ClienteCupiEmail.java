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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase que representa un cliente de CupiEmail.
 */
public class ClienteCupiEmail
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el separador de un comando.
     */
    public static final String SEPARADOR_COMANDO = ";;;";

    /**
     * Constante que representa el separador de los parámetros.
     */
    public static final String SEPARADOR_PARAMETROS = ":::";

    /**
     * Constante que representa el comando para crear un cuenta.
     */
    public static final String CREAR_CUENTA = "CREAR_CUENTA";

    /**
     * Constante que representa el comando para indicar que la cuenta fue creada.
     */
    public static final String CREAR_CUENTA_OK = "CREAR_CUENTA_OK";

    /**
     * Constante que representa el comando para iniciar sesión.
     */
    public final static String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Constante que representa el comando para indicar que se ha iniciado sesión.
     */
    public final static String INICIAR_SESION_OK = "INICIAR_SESION_OK";

    /**
     * Constante que representa el comando para cerrar sesión.
     */
    public final static String CERRAR_SESION = "CERRAR_SESION";

    /**
     * Constante que representa el comando para indicar que se ha cerrado sesión.
     */
    public final static String CERRAR_SESION_OK = "CERRAR_SESION_OK";

    /**
     * Constante que representa el comando para marcar un correo como leído,
     */
    public final static String MARCAR_COMO_LEIDO = "MARCAR_COMO_LEIDO";

    /**
     * Constante que representa el comando para indicar que un correo se ha marcado como leído.
     */
    public final static String MARCAR_COMO_LEIDO_OK = "MARCAR_COMO_LEIDO_OK";

    /**
     * Constante que representa el comando para consultar los correos.
     */
    public final static String CONSULTAR_CORREOS = "CONSULTAR_CORREOS";

    /**
     * Constante que representa el comando para indicar la cantidad de correos.
     */
    public static final String CANTIDAD_CORREOS = "CANTIDAD_CORREOS";

    /**
     * Constante que representa el comando para indicar que se han consultado los correos.
     */
    public final static String CONSULTAR_CORREOS_OK = "CONSULTAR_CORREOS_OK";

    /**
     * Constante que representa el comando para indicar la información de un correo.
     */
    public final static String INFO_CORREO = "INFO_CORREO";

    /**
     * Constante que representa el comando para enviar un nuevo correo.
     */
    public final static String ENVIAR_CORREO = "ENVIAR_CORREO";

    /**
     * Constante que representa el comando para indicar que se ha enviado un correo.
     */
    public static final String ENVIAR_CORREO_OK = "ENVIAR_CORREO_OK";

    /**
     * Constante que representa un error en la comunicación.
     */
    public final static String ERROR = "ERROR";
    
    /**
     * Constante que representa el comando para consultar los correos enviados.
     */
    public final static String LISTA_CORREOS_ENVIADOS = "LISTA_CORREOS_ENVIADOS";
    
    /**
     * Constante que representa el comando para mostrar la información de un correo enviado.
     */
    public final static String CORREO_ENVIADO = "CORREO_ENVIADO";
    
    /**
     * Constante que representa el comando para eliminar un correo.
     */
    public final static String ELIMINAR_CORREO = "ELIMINAR_CORREO";
    
    /**
     * Constante que representa el comando para indicar que se eliminó un correo.
     */
    public final static String ELIMINAR_CORREO_OK = "ELIMINAR_CORREO_OK";
    
    /**
     * Constante que representa el comando para consultar el usuario más popular.
     */
    public final static String USUARIO_MAS_POPULAR = "USUARIO_MAS_POPULAR";
    
    /**
     * Constante que representa el comando para mostrar la información del usuario más popular.
     */
    public final static String MAS_POPULAR = "MAS_POPULAR";
    

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Login del usuario.
     */
    private String loginUsuario;

    /**
     * Canal de comunicación con el servidor.
     */
    private Socket canal;

    /**
     * Flujo que lee los datos que llegan del servidor a través del socket.
     */
    private BufferedReader in;

    /**
     * Flujo que envía los datos al servidor a través del socket.
     */
    private PrintWriter out;

    /**
     * Lista de correos recibidos del cliente.
     */
    private ArrayList correosRecibidos;

    /**
     * Lista de correos enviados del cliente.
     */
    private ArrayList correosEnviados;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un cliente de CupiEmail.<br>
     * <b> post: </b> Se creó la conexión con el servidor con el archivo de propiedades dado por parámetro.
     * @param pArchivoConfig Ruta del archivo con la información de configuración del servidor. pArchivoConfig != null && pArchivoConfig != "".
     * @throws CupiEmailClienteException Si se presenta un error al conectarse con el servidor.
     */
    public ClienteCupiEmail( String pArchivoConfig ) throws CupiEmailClienteException
    {
        try
        {
            FileInputStream fis = new FileInputStream( pArchivoConfig );
            Properties p = new Properties( );
            p.load( fis );
            fis.close( );

            String dir = p.getProperty( "servidor.dirIp" );
            int puerto = Integer.parseInt( p.getProperty( "servidor.puerto" ) );
            canal = new Socket( dir, puerto );
            out = new PrintWriter( canal.getOutputStream( ), true );
            in = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( "Se presentaron problemas con la conexión al servidor. " + e.getMessage( ) );
        }
        correosRecibidos = new ArrayList( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la sesión de usuario en el servidor.<br>
     * <b> post: </b> Inició sesión en el servidor.
     * @param pLoginUsuario Login del usuario. pLoginUsuario != null && pLoginUsuario != "".
     * @param pContrasena Contraseña del usuario. pContrasena != null && pContrasena != "".
     * @throws CupiEmailClienteException Si se presentan problemas al iniciar sesión.
     */
    public void iniciarSesion( String pLoginUsuario, String pContrasena ) throws CupiEmailClienteException
    {

        try
        {
            String mensaje = INICIAR_SESION + SEPARADOR_COMANDO + pLoginUsuario + SEPARADOR_PARAMETROS + pContrasena;
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( INICIAR_SESION_OK ) )
            {
                loginUsuario = pLoginUsuario;
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Crear una cuenta en el servidor.<br>
     * <b> post: </b> Se creó la cuenta en el servidor.
     * @param pLogin Login del usuario. pLogin != null && pLogin != "".
     * @param pNombre Nombre del usuario. pNombre != null && pNombre != "".
     * @param pApellidos Apellidos del usuario. pApellidos != null && pApellidos != "".
     * @param pContrasena Contraseña del usuario. pContrasena != null && pContrasena !="".
     * @throws CupiEmailClienteException Si hay problemas en crear la cuenta.
     */
    public void crearCuenta( String pLogin, String pNombre, String pApellidos, String pContrasena ) throws CupiEmailClienteException
    {
        try
        {
            String linea = CREAR_CUENTA + SEPARADOR_COMANDO + pLogin + SEPARADOR_PARAMETROS + pNombre + SEPARADOR_PARAMETROS + pApellidos + SEPARADOR_PARAMETROS + pContrasena;
            out.println( linea );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CREAR_CUENTA_OK ) )
            {
                loginUsuario = pLogin;
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Cierra la sesión del usuario.<br>
     * <b> post: </b> Se cerró sesión.
     * @throws CupiEmailClienteException Si hay problema al cerrar sesión.
     */
    public void cerrarSesion( ) throws CupiEmailClienteException
    {
        try
        {
            out.println( CERRAR_SESION );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CERRAR_SESION_OK ) )
            {
                loginUsuario = null;
                correosRecibidos = new ArrayList( );
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Consulta la lista de correosElectronicos en el servidor y los agrega a correosElectronicos.<br>
     * <b> post: </b> Se actualizó la lista de correos.
     * @throws CupiEmailClienteException Si hay problema al consultar los correos.
     */
    public void consultarCorreos( ) throws CupiEmailClienteException
    {
        try
        {
            out.println( CONSULTAR_CORREOS );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CANTIDAD_CORREOS ) )
            {
                String cantidadStr = partesRespuesta[ 1 ];
                int cantidad = Integer.parseInt( cantidadStr );
                correosRecibidos = new ArrayList( );
                for( int i = 0; i < cantidad; i++ )
                {
                    String nuevoCorreo = in.readLine( );
                    String[] infoCorreo = nuevoCorreo.split( SEPARADOR_COMANDO );
                    if( !infoCorreo[ 0 ].equals( INFO_CORREO ) )
                    {
                        throw new CupiEmailClienteException( "Error al consultar los correos." );
                    }
                    else
                    {
                        String[] partesCorreo = infoCorreo[ 1 ].split( SEPARADOR_PARAMETROS );
                        boolean leido = false;
                        if( partesCorreo[ 4 ].equals( "0" ) )
                        {
                            leido = true;
                        }
                        CorreoElectronico correo = new CorreoElectronico( partesCorreo[ 0 ], partesCorreo[ 1 ], partesCorreo[ 2 ], partesCorreo[ 3 ], leido );
                        correosRecibidos.add( correo );
                    }
                }
                String finalCorreos = in.readLine( );
                if(!finalCorreos.equals( CONSULTAR_CORREOS_OK ))
                {
                    throw new CupiEmailClienteException( "Error al consultar los correos." );
                }
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }
    
    /**
     * Consulta la lista de correos electronicos enviados en el servidor y los agrega a correosEnviados.<br>
     * <b> post: </b> Se actualizó la lista de correos.
     * @throws CupiEmailClienteException Si hay problema al consultar los correos.
     */
    public void consultarEnviados( ) throws CupiEmailClienteException
    {
        try
        {
            out.println( LISTA_CORREOS_ENVIADOS );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CANTIDAD_CORREOS ) )
            {
                String cantidadStr = partesRespuesta[ 1 ];
                int cantidad = Integer.parseInt( cantidadStr );
                correosEnviados = new ArrayList( );
                for( int i = 0; i < cantidad; i++ )
                {
                    String nuevoCorreo = in.readLine( );
                    String[] infoCorreo = nuevoCorreo.split( SEPARADOR_COMANDO );
                    if( !infoCorreo[ 0 ].equals( CORREO_ENVIADO ) )
                    {
                        throw new CupiEmailClienteException( "Error al consultar los correos." );
                    }
                    else
                    {
                        String[] partesCorreo = infoCorreo[ 1 ].split( SEPARADOR_PARAMETROS );
                        CorreoEnviado correo = new CorreoEnviado( partesCorreo[ 1 ], partesCorreo[ 2 ], partesCorreo[ 0 ], partesCorreo[ 3 ] );
                        correosEnviados.add( correo );
                    }
                }
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Envía un correo.<br>
     * <b> post: Se envió el correo.</b>
     * @param pLoginDestinatarios Logins de los destinatarios del correo. pLoginDestinatarios != null && pLoginDestinatarios != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pMensaje Mensaje del correo. pMensaje != null && pMensaje != "".
     * @throws CupiEmailClienteException Si hay problemas al escribir el correo.
     */
    public void enviarCorreo( String pLoginDestinatarios, String pAsunto, String pMensaje ) throws CupiEmailClienteException
    {
        try
        {
            String mensaje = ENVIAR_CORREO + SEPARADOR_COMANDO + pLoginDestinatarios + SEPARADOR_PARAMETROS + pAsunto + SEPARADOR_PARAMETROS + pMensaje;
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( ENVIAR_CORREO_OK ) )
            {
                // No pasa nada
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Marca el correo dado por parámetro como leído.<br>
     * <b> post: </b> El correo dado por parámetro se marcó como leído.
     * @param pCorreo Correo electrónico que se desea marcar como leído. pCorreo != null.
     * @throws CupiEmailClienteException Si hay problemas al marcar como leído el correo.
     */
    public void marcarComoLeido( CorreoElectronico pCorreo ) throws CupiEmailClienteException
    {
        try
        {
            String mensaje = MARCAR_COMO_LEIDO + SEPARADOR_COMANDO + pCorreo.darFechaEnvio( ) + SEPARADOR_PARAMETROS + pCorreo.darAsunto( );
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( MARCAR_COMO_LEIDO_OK ) )
            {
                pCorreo.macarComoLeido( );
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    
    /**
     * Elimina el correo dado por parámetro <br>
     * <b> post: </b> Eliminó el correo dado por parámetro.
     * @param pCorreo Correo a ser eliminado. pCorreo != null.
     * @throws CupiEmailClienteException Si hay problemas al marcar como leído el correo.
     */
    public void eliminarCorreo( CorreoElectronico pCorreo ) throws CupiEmailClienteException
    {
        try
        {
            String mensaje = ELIMINAR_CORREO + SEPARADOR_COMANDO + pCorreo.darAsunto( ) + SEPARADOR_PARAMETROS + pCorreo.darFechaEnvio( );
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if(partesRespuesta[0].equals( ERROR ))
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if(partesRespuesta[0].equals( ELIMINAR_CORREO_OK ))
            {
                for( int i = 0; i < correosRecibidos.size( ); i++ )
                {
                    CorreoElectronico actual = (CorreoElectronico) correosRecibidos.get( i );
                    if(actual.darAsunto( ).equals( pCorreo.darAsunto( ) )&&actual.darFechaEnvio( ).equals( pCorreo.darFechaEnvio( ) ))
                    {
                        correosRecibidos.remove( i );
                    }
                }
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }
    

    /**
     * Retorna el login del usuario más popular.
     * @return Login del usuario más popular.
     * @throws CupiEmailClienteException Si hay problemas al marcar como leído el correo.
     */
    public String darUsuarioMasPopular( ) throws CupiEmailClienteException
    {
        String rta = "El usuario más popular es ";
        try
        {
            String mensaje = USUARIO_MAS_POPULAR;
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if(partesRespuesta[0].equals( MAS_POPULAR ))
            {
                String[] params = partesRespuesta[1].split( SEPARADOR_PARAMETROS );
                rta += params[0] + " con un total de " + params[1] + " correos.";
            }
            else
            {
                throw new CupiEmailClienteException( "Respuesta incorrecta del servidor." );
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
        return rta;
    }
    
    /**
     * Retorna el login del usuario.
     * @return Login del usuario.
     */
    public String darLogin( )
    {
        return loginUsuario;
    }

    /**
     * Retorna la lista de correos recibidos.
     * @return Lista de correos recibidos.
     */
    public ArrayList darCorreosRecibidos( )
    {
        return correosRecibidos;
    }
    
    /**
     * Retorna la lista de correos enviados.
     * @return Lista de correos enviados.
     */
    public ArrayList darCorreosEnviados()
    {
        return correosEnviados;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv: </b><br>
     * escuchaCambios != null <br>
     * canal != null <br>
     * correosElectronicos != null <br>
     */
    private void verificarInvariante( )
    {
        assert ( canal != null ) : "El canal no puede ser null";
        assert ( correosRecibidos != null ) : "La lista de correos no puede ser null";
    }
}
