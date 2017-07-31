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
 * Clase que representa un hilo de ejecución cuando se quiere iniciar sesión.
 */
public class ThreadIniciarSesion extends Thread
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
     * Login ingresado por el usuario.
     */
    private String login;
    
    /**
     * Contrasena ingresada por el usuario.
     */
    private String contrasena;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Crea un nuevo hilo para iniciar sesión en el servidor.<br>
     * <b>post: </b> Se inicializaron el cliente, la interfaz principal, el login y la contraseña con los valores dados por parámetro.
     * @param pCliente Clase principal del mundo con la información del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     * @param pLogin Login de usuario. pLogin != null && pLogin != "".
     * @param pContrasena La contraseña. pContrasena != null && pContrasena != "".
     */
    public ThreadIniciarSesion (ClienteCupiEmail pCliente, InterfazCliente pPrincipal, String pLogin, String pContrasena)
    {
        cliente = pCliente;
        principal = pPrincipal;
        login = pLogin;
        contrasena = pContrasena;
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza el inicio de sesión.<br>
     * <b>post: </b> Se inició sesión en el servidor.<br>
     */
    public void run( )
    {
        try
        {
            cliente.iniciarSesion( login, contrasena );
            principal.actualizarIniciarSesion( );
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
