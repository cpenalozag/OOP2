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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Dialogo para iniciar sesión.
 */
public class DialogoIniciarSesion extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando de la opción iniciar sesión
     */
    private static final String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Comando de la opción salir
     */
    private static final String CANCELAR = "SALIR";

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
     * Botón iniciar sesión.
     */
    private JButton btnIniciarSesion;

    /**
     * Botón cancelar.
     */
    private JButton btnCancelar;

    /**
     * Label imagen.
     */
    private JLabel lblImagen;

    /**
     * Label contraseña.
     */
    private JLabel lblContrasena;

    /**
     * Label usuario.
     */
    private JLabel lblUsuario;

    /**
     * Texto contraseña.
     */
    private JPasswordField txtContrasena;

    /**
     * Texto usuario.
     */
    private JTextField txtUsuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo iniciar sesión
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public DialogoIniciarSesion( InterfazCliente pPrincipal )
    {
        super( pPrincipal, true );
        setLayout( new BorderLayout( ) );
        principal = pPrincipal;
        setSize( 230, 270 );
        setLocationRelativeTo( null );
        setTitle( "Iniciar sesión" );
        lblImagen = new JLabel( );
        JPanel panelDatos = new JPanel( );
        lblUsuario = new JLabel( );
        txtUsuario = new JTextField( );
        lblContrasena = new JLabel( );
        txtContrasena = new JPasswordField( );
        JPanel panelBotones = new JPanel( );
        btnIniciarSesion = new JButton( );
        btnCancelar = new JButton( );

        lblImagen.setHorizontalAlignment( SwingConstants.CENTER );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/inicio_sesion.png" ) );
        add( lblImagen, BorderLayout.NORTH );

        panelDatos.setLayout( new java.awt.GridLayout( 5, 0 ) );

        lblUsuario.setText( "Usuario" );
        panelDatos.add( lblUsuario );
        panelDatos.add( txtUsuario );

        lblContrasena.setText( "Contraseña" );
        panelDatos.add( lblContrasena );
        panelDatos.add( txtContrasena );

        panelBotones.setLayout( new java.awt.GridLayout( 1, 2 ) );

        btnIniciarSesion.setText( "Iniciar sesión" );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        btnIniciarSesion.addActionListener( this );
        panelBotones.add( btnIniciarSesion );

        btnCancelar.setText( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelBotones.add( btnCancelar );

        panelDatos.add( panelBotones );

        add( panelDatos, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( INICIAR_SESION ) )
        {
            if( txtUsuario.getText( ) != null && !txtUsuario.getText( ).isEmpty( ) )
            {
                if( txtContrasena.getText( ) == null || txtContrasena.getText( ).isEmpty( ) )
                {
                    JOptionPane.showMessageDialog( this, "Por favor, ingrese su contraseña", "Error Contraseña", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.iniciarSesion( txtUsuario.getText( ), txtContrasena.getText( ) );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Por favor, ingrese el nombre de usuario", "Error Usuario", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }
}
