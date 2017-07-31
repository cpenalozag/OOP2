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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoElectronico;
import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoEnviado;

/**
 * Panel con la información de un correo.
 */
public class PanelCorreo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para mostrar un correo.
     */
    private final static String MOSTRAR_CORREO = "MOSTRAR_CORREO";

    /**
     * Comando para eliminar un correo.
     */
    private final static String ELIMINAR_CORREO = "ELIMINAR_CORREO";
    
    /**
     * Constante para los correos recibidos.
     */
    public final static int RECIBIDOS = 0;
    
    /**
     * Constante para los correos enviados.
     */
    public final static int ENVIADOS = 1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCliente principal;

    /**
     * Correo electrónico que se muestra en el panel.
     */
    private CorreoElectronico correoElectronico;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Label información.
     */
    private JLabel lblInfo;

    /**
     * Botón ver correo.
     */
    private JButton btnVerCorreo;

    /**
     * Botón eliminar correo.
     */
    private JButton btnEliminarCorreo;

    /**
     * Scroll para el texto del correo.
     */
    private JScrollPane scpTextoCorreo;

    /**
     * Texto del correo.
     */
    private JTextArea txtTextoCorreo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel de correo.
     * @param pPrincipal Venta principal de la aplicación. pPrincipal != null.
     * @param pCorreo Correo con la información que se debe mostrar en el panel. pCorreo != null.
     * @param pTipo Tipo de correos a visualizar. pTipo == ENVIADOS || pTipo == RECIBIDOS.
     */
    public PanelCorreo( InterfazCliente pPrincipal, CorreoElectronico pCorreo, int pTipo )
    {
        correoElectronico = pCorreo;
        principal = pPrincipal;
        JPanel panelDatos = new JPanel( );
        lblInfo = new JLabel( pCorreo.darLoginRemitente( ) + " - " + pCorreo.darFechaEnvio( ) + " - " + pCorreo.darAsunto( ) );
        btnVerCorreo = new JButton( "Leer correo" );
        btnEliminarCorreo = new JButton( "Eliminar" );
        scpTextoCorreo = new JScrollPane( );
        txtTextoCorreo = new JTextArea( pCorreo.darMensaje( ).replaceAll( "&n", "\n" ) );

        setBackground( new Color( 255, 255, 255 ) );
        setBorder( BorderFactory.createEmptyBorder( 1, 1, 1, 1 ) );
        setForeground( new Color( 255, 255, 255 ) );
        setLayout( new BorderLayout( ) );

        btnVerCorreo.addActionListener( this );
        btnVerCorreo.setActionCommand( MOSTRAR_CORREO );
        btnEliminarCorreo.addActionListener( this );
        btnEliminarCorreo.setActionCommand( ELIMINAR_CORREO );

        txtTextoCorreo.setColumns( 20 );
        txtTextoCorreo.setEditable( false );
        txtTextoCorreo.setLineWrap( true );
        txtTextoCorreo.setRows( 20 );
        txtTextoCorreo.setWrapStyleWord( true );
        scpTextoCorreo.setViewportView( txtTextoCorreo );

        scpTextoCorreo.setVisible( false );

        panelDatos.setBackground( new Color( 255, 255, 255 ) );
        panelDatos.setLayout( new BorderLayout( ) );

        if( pCorreo.darEstado( ) == false )
        {
            lblInfo.setFont( lblInfo.getFont( ).deriveFont( ( lblInfo.getFont( ).getStyle( ) | Font.ITALIC ) | Font.BOLD, lblInfo.getFont( ).getSize( ) + 1 ) );
        }
        else
        {
            lblInfo.setFont( null );
        }
        panelDatos.add( lblInfo, BorderLayout.WEST );
        if(pTipo==RECIBIDOS)
        {
            JPanel pnlAux = new JPanel( );
            pnlAux.setLayout( new GridLayout( 1, 2 ) );
            pnlAux.add( btnVerCorreo );
            pnlAux.add( btnEliminarCorreo );
            panelDatos.add( pnlAux, BorderLayout.EAST );
        }
        else
        {
            panelDatos.add( btnVerCorreo, BorderLayout.EAST );
        }

        add( panelDatos, BorderLayout.NORTH );
        add( scpTextoCorreo, BorderLayout.CENTER );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( MOSTRAR_CORREO ) )
        {
            if( correoElectronico.darEstado( ) == false )
            {
                principal.marcarComoLeido( correoElectronico );
            }
            if( scpTextoCorreo.isVisible( ) )
            {
                scpTextoCorreo.setVisible( false );
                btnVerCorreo.setText( "Leer correo" );
            }
            else
            {
                scpTextoCorreo.setVisible( true );
                btnVerCorreo.setText( "Ocultar" );
            }
            lblInfo.setFont( null );
            updateUI( );
        }
        else if( comando.equals( ELIMINAR_CORREO ) )
        {
            principal.eliminarCorreo( correoElectronico );
            updateUI( );
        }
    }
}