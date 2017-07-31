/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiViajes.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiViajes.mundo.Estado;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;

/**
 * Panel con la información detallada de una reserva.
 */
public class PanelReserva extends JPanel implements ActionListener
{
    
    public final static String CAMBIAR_ESTADO = "Cambiar estado";
    
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    
    /**
     * Campo de texto con la cedula del cliente que hizo la reserva.
     */
    private JTextField txtCedula;
    
    /**
     * Campo de texto con el nombre del cliente que hizo la reserva.
     */
    private JTextField txtCliente;
    
    /**
     * Campo de texto con el día de llegada al hotel.
     */
    private JTextField txtDia;
    
    /**
     * Campo de texto con el mes de llegada al hotel.
     */
    private JTextField txtMes;
    
    /**
     * Campo de texto de el año de llegada al hotel.
     */
    private JTextField txtAnio;
    
    /**
     * Campo de texto con las noches de estadía.
     */
    private JTextField txtNochesEstadia;
    
    /**
     * Campo de texto con la cantidad de adultos en la reserva.
     */
    private JTextField txtCantidadAdultos;
    
    /**
     * Campo de texto con la cantidad de niños en la reserva.
     */
    private JTextField txtCantidadNinios;
    
    /**
     * Campo de texto con la aerolínea de la reserva.
     */
    private JTextField txtAerolinea;
    
    /**
     * Campo de texto con el costo total.
     */
    private JTextField txtCosto;
    
    /**
     * Combo con el estado de la reserva
     */
    private JComboBox cbEstado;
    
    /**
     * Botón para cambiar el estado de la reserva
     */
    private JButton btCambiarEstado;
    
    /**
     * Clase principal de la aplciación
     */
    private InterfazCupiViajes principal;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con la información de la reserva. <br>
     * <b> post: </b> Todos los elementos gráficos fueron inicializados.
     */
    public PanelReserva( InterfazCupiViajes i )
    {
        principal = i;
        setLayout( new GridLayout( 9, 2, 2, 2 ) );
        
        add( new JLabel( "Cedula del cliente:" ) );
        txtCedula = new JTextField( );
        txtCedula.setEditable( false );
        add( txtCedula );
        
        add( new JLabel( "Nombre del cliente:" ) );
        txtCliente = new JTextField( );
        txtCliente.setEditable( false );
        add( txtCliente );
        
        add( new JLabel( "Fecha de llegada:" ) );
        JPanel panelFecha = new JPanel( );
        panelFecha.setLayout( new GridLayout( 1, 5 ) );
        txtDia = new JTextField( );
        txtDia.setEditable( false );
        panelFecha.add( txtDia );
        JLabel labelAux = new JLabel( "/" );
        labelAux.setHorizontalAlignment( JLabel.CENTER );
        panelFecha.add( labelAux );
        txtMes = new JTextField( );
        txtMes.setEditable( false );
        panelFecha.add( txtMes );
        JLabel labelAux2 = new JLabel( "/" );
        labelAux2.setHorizontalAlignment( JLabel.CENTER );
        panelFecha.add( labelAux2 );
        txtAnio = new JTextField( );
        txtAnio.setEditable( false );
        panelFecha.add( txtAnio );
        add( panelFecha );
        
        add( new JLabel( "Noches de estadía:" ) );
        txtNochesEstadia = new JTextField( );
        txtNochesEstadia.setEditable( false );
        add( txtNochesEstadia );
        
        add( new JLabel( "Cantidad adultos:" ) );
        txtCantidadAdultos = new JTextField( );
        txtCantidadAdultos.setEditable( false );
        add(  txtCantidadAdultos );
        
        add( new JLabel( "Cantidad niños:" ) );
        txtCantidadNinios = new JTextField( );
        txtCantidadNinios.setEditable( false );
        add( txtCantidadNinios );
        
        add( new JLabel( "Aerolínea:" ) );
        txtAerolinea = new JTextField( );
        txtAerolinea.setEditable( false );
        add( txtAerolinea );
        
        add( new JLabel( "Costo total de la reserva:" ) );
        txtCosto = new JTextField( );
        txtCosto.setEditable( false );
        add( txtCosto );
        
        add( new JLabel( "Estado:" ) );
        cbEstado = new JComboBox( );
        btCambiarEstado = new JButton( CAMBIAR_ESTADO);
        btCambiarEstado.setActionCommand( CAMBIAR_ESTADO );
        btCambiarEstado.addActionListener( this );
        JPanel panelEstado = new JPanel( new GridLayout( 1,2));
        panelEstado.add( cbEstado );
        panelEstado.add( btCambiarEstado );
        add( panelEstado );
        
        for( Estado e : Estado.values( ) )
        {
            cbEstado.addItem( e );
        }
        btCambiarEstado.setEnabled( false );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la información del panel con la reserva dada por parámetro.
     * @param pReservaViaje Reserva que se va a visualizar. pReservaViaje != null.
     */
    public void actualizarInformacion( ReservaViaje pReservaViaje )
    {
        txtCedula.setText( pReservaViaje.darCedula( ) );
        txtCliente.setText( pReservaViaje.darNombreCliente( ) );
        Calendar c = Calendar.getInstance( );
        c.setTime(pReservaViaje.darFechaLlegada( ));
        
        txtDia.setText( String.valueOf( c.get( Calendar.DAY_OF_MONTH )));
        txtMes.setText( String.valueOf( c.get(Calendar.MONTH)) );
        txtAnio.setText( String.valueOf( c.get( Calendar.YEAR ) ) );
        txtNochesEstadia.setText( String.valueOf( pReservaViaje.darCantidadNochesEstadia( ) ) );
        txtCantidadAdultos.setText( String.valueOf( pReservaViaje.darCantidadAdultos( ) ) );
        txtCantidadNinios.setText( String.valueOf( pReservaViaje.darCantidadNinios( ) ) );
        txtAerolinea.setText( pReservaViaje.darAerolinea( ).toString( ) );
        txtCosto.setText( String.valueOf( pReservaViaje.darCostoTotal( ) ) );
        cbEstado.setSelectedItem( pReservaViaje.darEstado() );
        btCambiarEstado.setEnabled( true );
        
    }


    public void actionPerformed( ActionEvent ev )
    {
       String comando = ev.getActionCommand( );
       if(comando.equals( CAMBIAR_ESTADO ))
       {
           String cedula = txtCedula.getText( );
           Estado estado = (Estado)cbEstado.getSelectedItem( );
           principal.cambiarEstadoReserva(cedula, estado);
           
       }
        
    }

}
