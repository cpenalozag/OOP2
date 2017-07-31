/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiLogo.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiLogo.mundo.ComandoEscalar;

/**
 * Diálogo para agregar el comando Escalar.
 */
public class DialogoEscalar extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------s

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante que representa el comando para ejecutar el comando.
     */
    private final static String EJECUTAR = "Ejecutar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiLogo principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para ejecutar el comando.
     */
    private JButton btnEjecutar;

    /**
     * Combobox con la dirección del movimiento.
     */
    private JComboBox cbDireccion;

    /**
     * Campo de texto con la distancia a recorrer.
     */
    private JTextField txtEscala;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo para el comando Escalar.<br>
     * <b> post: </b> Todos los elementos gráficos del diálogo fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public DialogoEscalar( InterfazCupiLogo pPrincipal )
    {
        principal = pPrincipal;

        setTitle( "Escalar" );
        setSize( new Dimension( 300, 150 ) );
        setResizable( true );
        setLocationRelativeTo( principal );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        JPanel panel = new JPanel( );
        panel.setLayout( new GridLayout( 3, 2, 5, 5 ) );
        panel.setBorder( new TitledBorder( "Agregar nueva escala:" ) );

        panel.add( new JLabel( "La escala a ejecutar:" ) );
        txtEscala = new JTextField( );
        panel.add( txtEscala );

        panel.add( new JLabel( "Dirección:" ) );
        cbDireccion = new JComboBox( );
        cbDireccion.addItem( "Aumentar" );
        cbDireccion.addItem( "Reducir" );
        panel.add( cbDireccion );

        panel.add( new JLabel( ) );
        btnEjecutar = new JButton( EJECUTAR );
        btnEjecutar.setActionCommand( EJECUTAR );
        btnEjecutar.addActionListener( this );
        panel.add( btnEjecutar );

        add( panel );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {

        if( pEvento.getActionCommand( ).equals( EJECUTAR ) )
        {
            boolean puede = true;
            double escala = 0;
            int direccion = cbDireccion.getSelectedIndex( );
            try
            {
                escala = Double.parseDouble( txtEscala.getText( ) );
            }
            catch( Exception e2 )
            {
                puede = false;
                JOptionPane.showMessageDialog( principal, "Debe agregar un valor numérico.", "Escalar", JOptionPane.ERROR_MESSAGE );
            }
            if( !principal.verificarEscala( escala, direccion ) )
            {
                puede = false;
                JOptionPane.showMessageDialog( principal, "La escala debe ser estar entre 1.0 y 3.0", "Escalar", JOptionPane.ERROR_MESSAGE );
            }

            if( puede )
            {
                ComandoEscalar comando = new ComandoEscalar( escala, direccion );
                principal.agregarComando( comando );
                this.dispose( );
            }
        }

    }

}
