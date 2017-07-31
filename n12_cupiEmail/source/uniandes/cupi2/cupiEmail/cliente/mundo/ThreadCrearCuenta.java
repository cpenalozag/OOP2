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
 * Clase que representa un hilo de ejecución cuando se quiere crear una cuenta.
 */
public class ThreadCrearCuenta extends Thread
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
     * Objeto con toda la información ingresada por el usuario.
     */
    private Usuario usuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo hilo para crear cuenta en el servidor.<br>
     * <b>post: </b> Se inicializaron el cliente, la interfaz principal y el usuario con los valores dados por parámetro.
     * @param pCliente Clase principal del mundo con la información del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     * @param pUsuario Objeto con la información del usuario. pUsuario != null.
     */
    public ThreadCrearCuenta( ClienteCupiEmail pCliente, InterfazCliente pPrincipal, Usuario pUsuario )
    {
        cliente = pCliente;
        principal = pPrincipal;
        usuario = pUsuario;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza la creación de una cuenta.<br>
     * <b>post: </b> Se creó una cuenta en el servidor.<br>
     */
    public void run( )
    {
        try
        {
            cliente.crearCuenta( usuario.darLogin( ), usuario.darNombre( ), usuario.darApellidos( ), usuario.darContrasena( ) );
            principal.actualizarCrearCuenta( );
        }
        catch (Exception e)
        {   
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
    
}
