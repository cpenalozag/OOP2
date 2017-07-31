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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Dialogo para crear una cuenta.
 */
public class DialogoCrearCuenta extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando de la opción crear cuenta.
     */
    private static final String CREAR_CUENTA = "CREAR_CUENTA";

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
     * Botón crear cuenta.
     */
    private JButton btnCrearCuenta;

    /**
     * Botón cancelar.
     */
    private JButton btnCancelar;

    /**
     * Label apellidos.
     */
    private JLabel lblApellidos;

    /**
     * Label nombre.
     */
    private JLabel lblNombres;

    /**
     * Label contraseña.
     */
    private JLabel lblContrasena;

    /**
     * Label confirmación de contraseña.
     */
    private JLabel lblContrasenaConfirmacion;

    /**
     * Label login.
     */
    private JLabel lblLogin;

    /**
     * Texto apellidos.
     */
    private JTextField txtApellidos;

    /**
     * Texto nombre.
     */
    private JTextField txtNombre;

    /**
     * Texto contraseña.
     */
    private JPasswordField txtContrasena;

    /**
     * Texto confirmación de contraseña.
     */
    private JPasswordField txtContrasenaConfirmacion;

    /**
     * Texto login de usuario.
     */
    private JTextField txtLogin;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo crear cuenta.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public DialogoCrearCuenta( InterfazCliente pPrincipal )
    {
        super( pPrincipal, true );
        setSize( 240, 370 );
        setLocationRelativeTo( null );
        setTitle( "Crear cuenta" );
        principal = pPrincipal;
        JPanel panelDatos = new JPanel( );
        lblLogin = new JLabel( );
        txtLogin = new JTextField( );
        lblNombres = new JLabel( );
        txtNombre = new JTextField( );
        lblApellidos = new JLabel( );
        txtApellidos = new JTextField( );
        lblContrasena = new JLabel( );
        txtContrasena = new JPasswordField( );
        lblContrasenaConfirmacion = new JLabel( );
        txtContrasenaConfirmacion = new JPasswordField( );
        JPanel panelOpciones = new JPanel( );
        btnCrearCuenta = new JButton( );
        btnCancelar = new JButton( );

        setLayout( new java.awt.GridLayout( ) );

        panelDatos.setBorder( BorderFactory.createTitledBorder( "Información de registro" ) );
        panelDatos.setLayout( new java.awt.GridLayout( 0, 1 ) );

        lblLogin.setText( "Login" );
        panelDatos.add( lblLogin );
        panelDatos.add( txtLogin );

        lblNombres.setText( "Nombre" );
        panelDatos.add( lblNombres );
        panelDatos.add( txtNombre );

        lblApellidos.setText( "Apellidos" );
        panelDatos.add( lblApellidos );
        panelDatos.add( txtApellidos );

        lblContrasena.setText( "Contraseña" );
        panelDatos.add( lblContrasena );
        panelDatos.add( txtContrasena );

        lblContrasenaConfirmacion.setText( "Confirmación contraseña" );
        panelDatos.add( lblContrasenaConfirmacion );
        panelDatos.add( txtContrasenaConfirmacion );

        panelOpciones.setLayout( new java.awt.GridLayout( 1, 2 ) );

        btnCrearCuenta.setText( "Crear cuenta" );
        btnCrearCuenta.addActionListener( this );
        btnCrearCuenta.setActionCommand( CREAR_CUENTA );
        panelOpciones.add( btnCrearCuenta );

        btnCancelar.setText( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        panelOpciones.add( btnCancelar );

        panelDatos.add( panelOpciones );

        add( panelDatos );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento!= null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( CREAR_CUENTA ) )
        {
            String pass1 = txtContrasena.getText( );
            String pass2 = txtContrasenaConfirmacion.getText( );
            String usu = txtLogin.getText( );
            String nombre = txtNombre.getText( );
            String apellido = txtApellidos.getText( );

            if( nombre != null && !nombre.isEmpty( ) && apellido != null && !apellido.isEmpty( ) )
            {
                if( usu != null && !usu.isEmpty( ) )
                {
                    if( pass1 != null && !pass1.isEmpty( ) && pass2 != null && !pass2.isEmpty( ) )
                    {
                        if( !pass1.equals( pass2 ) )
                        {
                            JOptionPane.showMessageDialog( this, "Las contraseñas no coinciden", "Crear Cuenta", JOptionPane.ERROR_MESSAGE );
                        }
                        else
                        {
                            principal.crearCuenta( usu, nombre, apellido, pass1 );
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Por favor, ingrese la contraseña y su confirmación", "Crear Cuenta", JOptionPane.ERROR_MESSAGE );
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Por favor, ingrese el nombre de usuario", "Crear Cuenta", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Por favor, ingrese su nombre y apellido", "Crear Cuenta", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }

}
