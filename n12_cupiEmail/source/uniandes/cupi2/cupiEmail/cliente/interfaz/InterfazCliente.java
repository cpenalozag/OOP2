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
package uniandes.cupi2.cupiEmail.cliente.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.TODO;

import uniandes.cupi2.cupiEmail.cliente.mundo.ClienteCupiEmail;
import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoElectronico;
import uniandes.cupi2.cupiEmail.cliente.mundo.CupiEmailClienteException;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadCerrarSesion;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadConsultarCorreos;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadConsultarEnviados;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadCrearCuenta;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadEliminarCorreo;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadEnviarCorreo;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadIniciarSesion;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadMarcarComoLeido;
import uniandes.cupi2.cupiEmail.cliente.mundo.ThreadUsuarioMasPopular;
import uniandes.cupi2.cupiEmail.cliente.mundo.Usuario;

/**
 * Ventana principal del cliente.
 */
public class InterfazCliente extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Ruta del archivo con las información de configuración del servidor.
     */
    private static final String ARCHIVO_CONFIG = "./data/servidor.properties";

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // ------------------------------------

    /**
     * Dialogo crear cuenta.
     */
    private DialogoCrearCuenta dialogoCrearCuenta;

    /**
     * Dialogo iniciar sesión.
     */
    private DialogoIniciarSesion dialogoIniciarSesion;

    /**
     * Dialogo para enviar correos.
     */
    private DialogoEscribirCorreo dialogoEscribirCorreo;

    /**
     * Panel información de usuario.
     */
    private PanelInfoUsuario panelInfoUsuario;

    /**
     * Panel con la lista de correos.
     */
    private PanelCorreos panelCorreos;

    /**
     * Panel opciones.
     */
    private PanelOpciones panelOpciones;

    /**
     * Referencia a la clase principal del mundo.
     */
    private ClienteCupiEmail cliente;

    /**
     * Dialogo inicio cliente.
     */
    private DialogoInicioCliente dialogoInicio;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva interfaz cliente
     * @throws CupiEmailClienteException En caso de encontrar un error al conectarse con el servidor.
     */
    public InterfazCliente( ) throws CupiEmailClienteException
    {
        cliente = new ClienteCupiEmail( ARCHIVO_CONFIG );
        setTitle( "CupiCorreo" );
        panelInfoUsuario = new PanelInfoUsuario( this );
        panelCorreos = new PanelCorreos( this );
        panelOpciones = new PanelOpciones( this );
        dialogoCrearCuenta = new DialogoCrearCuenta( this );
        dialogoIniciarSesion = new DialogoIniciarSesion( this );
        dialogoInicio = new DialogoInicioCliente( this );
        dialogoEscribirCorreo = new DialogoEscribirCorreo( this );
        setSize( 600, 650 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        add( panelInfoUsuario, BorderLayout.NORTH );
        add( panelCorreos, BorderLayout.CENTER );
        add( panelOpciones, BorderLayout.SOUTH );

        dialogoInicio.setVisible( true );

        if( cliente.darLogin( ) == null )
        {
            System.exit( 0 );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra el dialogo crear cuenta.
     */
    public void mostrarDialogoCrearCuenta( )
    {
        dialogoCrearCuenta.setVisible( true );
    }

    /**
     * Muestra el dialogo iniciar sesión.
     */
    public void mostrarDialogoIniciarSesion( )
    {
        dialogoIniciarSesion.setVisible( true );
    }

    /**
     * Permite iniciar sesión a un usuario con el login y contraseña dados.
     * @param pLogin Login de usuario. pLogin != null && pLogin != "".
     * @param pContrasena La contraseña. pContrasena != null && pContrasena != "".
     */
    public void iniciarSesion( String pLogin, String pContrasena )
    {
        ThreadIniciarSesion thread = new ThreadIniciarSesion( cliente, this, pLogin, pContrasena );
        thread.start( );
    }

    /**
     * Permite crear una nueva cuenta en el servidor.
     * @param pLogin Login del usuario. pLogin != null && pLogin != "".
     * @param pNombre Nombre del usuario. pNombre != null && pNombre != "".
     * @param pApellidos Apellidos del usuario. pApellidos != null && pApellidos != "".
     * @param pContrasena Contraseña del usuario. pContrasena != null && pContrasena != "".
     */
    public void crearCuenta( String pLogin, String pNombre, String pApellidos, String pContrasena )
    {
        Usuario usuario = new Usuario( pLogin, pNombre, pApellidos, pContrasena, 0, 0 );
        ThreadCrearCuenta thread = new ThreadCrearCuenta( cliente, this, usuario );
        thread.start( );
    }

    /**
     * Cierra la sesión.
     */
    public void cerrarSesion( )
    {
        ThreadCerrarSesion thread = new ThreadCerrarSesion( cliente, this );
        thread.start( );
    }

    /**
     * Consulta los correos recibidos.
     */
    public void consultarCorreos( )
    {
        ThreadConsultarCorreos thread = new ThreadConsultarCorreos( cliente, this );
        thread.start( );
    }
    
    /**
     * Consulta los correos enviados .
     */
    public void consultarCorreosEnviados( )
    {
        ThreadConsultarEnviados thread = new ThreadConsultarEnviados( cliente, this );
        thread.start( );
        
    }

    /**
     * Escribe un nuevo correo.
     * @param pMensaje Mensaje del correo. pMensaje != null && pMensaje != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pLoginDestinatarios Los destinatarios del correo. pLoginDestinatarios != null && pLoginDestinatarios != "".
     */
    public void escribirCorreo( String pMensaje, String pAsunto, String pLoginDestinatarios )
    {
        ThreadEnviarCorreo thread = new ThreadEnviarCorreo( cliente, this, pLoginDestinatarios, pAsunto, pMensaje );
        thread.start( );

    }

    /**
     * Actualiza el estado a leído del correo dado por parámetro.
     * @param pCorreo Correo electrónico que se debe marcar como leído. pCorreo != null.
     */
    public void marcarComoLeido( CorreoElectronico pCorreo )
    {
        ThreadMarcarComoLeido thread = new ThreadMarcarComoLeido( cliente, this, pCorreo );
        thread.start( );
    }

    /**
     * Elimina el correo electrónico dado por parámetro.
     * @param pCorreo Correo electrónico que va a ser eliminado. pCorreo != null.
     */
    public void eliminarCorreo( CorreoElectronico pCorreo )
    {
        ThreadEliminarCorreo thread = new ThreadEliminarCorreo( cliente, this, pCorreo );
        thread.start( );
    }

    /**
     * Consulta el usuario más popular.
     */
    public void consultarUsuarioMasPopular( )
    {
        ThreadUsuarioMasPopular thread = new ThreadUsuarioMasPopular( cliente, this );
        thread.start( );
    }

    /**
     * Muestra el dialogo para escribir un correo.
     */
    public void escribirCorreoNuevo( )
    {
        dialogoEscribirCorreo.setVisible( true );
    }

    /**
     * Muestra un mensaje de error.
     * @param pMensaje Mensaje de error. pMensaje != null && pMensaje != "".
     */
    public void mostrarMensajeError( String pMensaje )
    {
        JOptionPane.showMessageDialog( this, pMensaje, "Error", JOptionPane.ERROR_MESSAGE );
    }

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha creado un cuenta
     */
    public void actualizarCrearCuenta( )
    {
        dialogoCrearCuenta.setVisible( false );
        dialogoInicio.setVisible( false );
        panelInfoUsuario.actualizarUsuario( cliente.darLogin( ) );
    }

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha iniciado sesión
     */
    public void actualizarIniciarSesion( )
    {
        dialogoIniciarSesion.setVisible( false );
        dialogoInicio.setVisible( false );
        panelInfoUsuario.actualizarUsuario( cliente.darLogin( ) );
        consultarCorreos( );
    }

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha actualizado la lista de correos recibidos del usuario.
     */
    public void actualizarCorreosRecibidos( )
    {
        panelCorreos.actualizarRecibidos( cliente.darCorreosRecibidos( ) );
    }
    
    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha actualizado la lista de correos enviados del usuario.
     */
    public void actualizarCorreosEnviados( )
    {
        panelCorreos.actualizarEnviados( cliente.darCorreosEnviados( ) );
    }

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha escrito un nuevo correo
     */
    public void actualizarEscribirCorreo( )
    {
        dialogoEscribirCorreo.reiniciar( );
        dialogoEscribirCorreo.setVisible( false );
        JOptionPane.showMessageDialog( this, "Su correo se ha enviado con éxito", "Enviar correo", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha eliminado un correo.
     */
    public void actualizarEliminarCorreo( )
    {
        panelCorreos.actualizarRecibidos( cliente.darCorreosRecibidos( ) );
        JOptionPane.showMessageDialog( this, "El correo ha sido eliminado con éxito", "Eliminar correo", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha cerrado la sesión
     */
    public void actualizarCerrarSesion( )
    {
        dispose( );
    }

    /**
     * Muestra el login del usuario más popular.
     * @param pLogin Login del usuario más popular. pLogin != null && pLogin != "".
     */
    public void actualizarMasPopular( String pLogin )
    {
        JOptionPane.showMessageDialog( this, pLogin, "Usuario más popular", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param pArgs Parámetros de ejecución que no son usados.
     */
    public static void main( String pArgs[] )
    {
        InterfazCliente interfazCliente = null;
        try
        {
            interfazCliente = new InterfazCliente( );
            interfazCliente.setVisible( true );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, "Error inicializando el mundo. \n" + e.getMessage( ) );
        }

    }

}
