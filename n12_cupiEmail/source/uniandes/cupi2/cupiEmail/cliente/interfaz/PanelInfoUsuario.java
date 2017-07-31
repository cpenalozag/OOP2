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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel con la información del usuario.
 */
public class PanelInfoUsuario extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de la opción cerrar sesión.
     */
    private static final String CERRAR_SESION = "CERRAR_SESION";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón cerrar sesión.
     */
    private JButton btnCerrarSesion;

    /**
     * Label imagen.
     */
    private JLabel lblImagen;

    /**
     * Label bienvenido.
     */
    private JLabel lblBienvenido;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelInfoUsuario( InterfazCliente pPrincipal )
    {
        principal = pPrincipal;
        lblBienvenido = new JLabel( );
        btnCerrarSesion = new JButton( );
        lblImagen = new JLabel( );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/cliente.png" ) );

        setBackground( new Color( 255, 255, 255 ) );
        setLayout( new BorderLayout( ) );

        lblBienvenido.setText( "<html> Bienvenid@: <p> usuario  </html>" );
        add( lblBienvenido, BorderLayout.WEST );

        btnCerrarSesion.setText( "Cerrar Sesión" );
        btnCerrarSesion.addActionListener( this );
        btnCerrarSesion.setActionCommand( CERRAR_SESION );
        add( btnCerrarSesion, BorderLayout.EAST );

        add( lblImagen, BorderLayout.NORTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método para actualizar la interfaz con la información de un usuario.
     * @param pLogin Login del usuario. pLogin != null && pLogin != "".
     */
    public void actualizarUsuario( String pLogin )
    {
        lblBienvenido.setText( "<html> Bienvenid@: <p> " + pLogin + "  </html>" );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( CERRAR_SESION ) )
        {
            principal.cerrarSesion( );
        }
    }
}