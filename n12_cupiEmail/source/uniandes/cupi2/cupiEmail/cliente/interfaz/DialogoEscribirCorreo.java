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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Dialogo para escribir un nuevo correo.
 */
public class DialogoEscribirCorreo extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando de la opción registrar correo.
     */
    private static final String REGISTRAR_CORREO = "REGISTRAR_CORREO";

    /**
     * Comando de la opción cancelar.
     */
    private static final String CANCELAR = "CANCELAR";

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
     * Usuarios a los que se les envía el correo.
     */
    private JTextField txtUsuarios;

    /**
     * Asunto del correo.
     */
    private JTextField txtAsunto;

    /**
     * Texto del correo.
     */
    private JTextArea txtTexto;

    /**
     * Label ingresar usuarios.
     */
    private JLabel lblIngresarUsuarios;

    /**
     * Label ingresar asunto.
     */
    private JLabel lblIngresarAsunto;

    /**
     * Botón registrar correo.
     */
    private JButton btnRegistrarCorreo;

    /**
     * Botón Cancelar.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo escribir correo.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public DialogoEscribirCorreo( InterfazCliente pPrincipal )
    {
        super( pPrincipal, true );
        setSize( 500, 500 );
        setLocationRelativeTo( null );
        principal = pPrincipal;
        setTitle( "Nuevo correo" );
        setLayout( new BorderLayout( ) );
        JPanel panelBotones = new JPanel( );
        JPanel panelInfoCorreo = new JPanel( );
        lblIngresarAsunto = new JLabel( "Asunto:" );
        lblIngresarUsuarios = new JLabel( "Para:" );
        txtAsunto = new JTextField( );
        txtUsuarios = new JTextField( );
        txtTexto = new JTextArea( 5, 20 );
        txtTexto.setLineWrap( true );
        txtTexto.setWrapStyleWord( true );
        panelInfoCorreo.setLayout( new GridLayout( 4, 1 ) );
        panelInfoCorreo.add( lblIngresarUsuarios );
        panelInfoCorreo.add( txtUsuarios );
        panelInfoCorreo.add( lblIngresarAsunto );
        panelInfoCorreo.add( txtAsunto );

        btnRegistrarCorreo = new JButton( "Enviar correo" );
        btnRegistrarCorreo.addActionListener( this );
        btnRegistrarCorreo.setActionCommand( REGISTRAR_CORREO );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );

        panelBotones.setLayout( new GridLayout( 1, 2 ) );
        panelBotones.add( btnRegistrarCorreo );
        panelBotones.add( btnCancelar );

        JScrollPane scrollPane = new JScrollPane( txtTexto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        add( panelInfoCorreo, BorderLayout.NORTH );
        add( scrollPane, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.SOUTH );
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
        if( command.equals( REGISTRAR_CORREO ) )
        {
            String mensaje = txtTexto.getText( ).replaceAll( "\n", "&n" );
            String asunto = txtAsunto.getText( );
            String usuarios = txtUsuarios.getText( );

            if( mensaje != null && !mensaje.equals( "" ) )
            {
                if( txtTexto.getText( ).length( ) > 512 )
                {
                    principal.mostrarMensajeError( "El tamaño máximo del correo es 140 caracteres, tamaño actual:" + txtTexto.getText( ).length( ) );
                    return;
                }
                else
                {
                    if( asunto != null && !asunto.equals( "" ) )
                    {
                        if( usuarios != null && !usuarios.equals( "" ) )
                        {
                            principal.escribirCorreo( mensaje, asunto, usuarios );
                        }
                        else
                        {
                            JOptionPane.showMessageDialog( this, "Por favor ingrese los destinatarios del mensaje", "Escribir Correo", JOptionPane.ERROR_MESSAGE );
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Por favor ingrese el asunto del mensaje", "Escribir Correo", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Por favor ingrese el mensaje", "Escribir Correo", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }

    /**
     * Limpia los campos de asunto, mensaje y destinatarios
     */
    public void reiniciar( )
    {
        txtAsunto.setText( "" );
        txtTexto.setText( "" );
        txtUsuarios.setText( "" );
    }

}
