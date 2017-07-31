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

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

/**
 * Dialogo que muestra las opciones iniciales de un cliente
 */
public class DialogoInicioCliente extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de la opción crear cuenta.
     */
    private static final String CREAR_CUENTA = "CREAR_CUENTA";

    /**
     * Comando de la opción iniciar sesión.
     */
    private static final String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Comando de la opción salir.
     */
    private static final String SALIR = "SALIR";

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
     * Botón para crear una cuenta.
     */
    private JButton btnCrearCuenta;

    /**
     * Botón para iniciar sesión.
     */
    private JButton btnIniciarSesion;

    /**
     * Botón para salir.
     */
    private JButton btnSalir;

    /**
     * Label imagen.
     */
    private JLabel lblImagenCupi2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo inicio cliente.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public DialogoInicioCliente( InterfazCliente pPrincipal )
    {
        super( pPrincipal, true );
        setUndecorated( true );
        getRootPane( ).setWindowDecorationStyle( JRootPane.PLAIN_DIALOG );
        setSize( 380, 180 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setTitle( "CupiEmail" );
        principal = pPrincipal;
        JPanel panelOpciones;
        lblImagenCupi2 = new JLabel( );
        panelOpciones = new JPanel( );
        btnCrearCuenta = new JButton( );
        btnIniciarSesion = new JButton( );
        btnSalir = new JButton( );

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        setBackground( Color.WHITE );
        lblImagenCupi2.setIcon( new ImageIcon( "data/imagenes/inicio.png" ) );
        add( lblImagenCupi2, java.awt.BorderLayout.WEST );

        panelOpciones.setLayout( new GridLayout( 3, 0 ) );

        btnCrearCuenta.setText( "Crear cuenta" );
        btnCrearCuenta.addActionListener( this );
        btnCrearCuenta.setActionCommand( CREAR_CUENTA );
        panelOpciones.add( btnCrearCuenta );

        btnIniciarSesion.setText( "Iniciar sesión" );
        btnIniciarSesion.addActionListener( this );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        panelOpciones.add( btnIniciarSesion );

        btnSalir.setText( "Salir" );
        btnSalir.addActionListener( this );
        btnSalir.setActionCommand( SALIR );
        panelOpciones.add( btnSalir );

        add( panelOpciones, java.awt.BorderLayout.CENTER );
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
        if( command.equals( CREAR_CUENTA ) )
        {
            principal.mostrarDialogoCrearCuenta( );
        }
        else if( command.equals( INICIAR_SESION ) )
        {
            principal.mostrarDialogoIniciarSesion( );
        }
        else if( command.equals( SALIR ) )
        {
            System.exit( 0 );
        }
    }

}
