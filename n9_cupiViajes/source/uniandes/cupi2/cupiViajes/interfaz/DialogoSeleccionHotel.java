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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uniandes.cupi2.cupiViajes.mundo.Hotel;

/**
 * Diálogo para seleccionar un hotel para reservar.
 */
public class DialogoSeleccionHotel extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante que representa el cambio en la selección del combo box ciudad.
     */
    private final static String COMBO_CIUDAD = "Combo ciudad";
    
    /**
     * Constante que representa el cambio en la selección del combo box hotel.
     */
    private final static String COMBO_HOTEL = "Combo hotel";
    
    /**
     * Constante que representa el comando para seleccionar el hotel.
     */
    private final static String SELECCIONAR = "Selecciona hotel";
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiViajes principal;
    
    /**
     * Dialogo para agregar una reserva.
     */
    private DialogoAgregarReserva dialogoReserva;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Etiqueta ciudad del hotel.
     */
    private JLabel lblCiudad;

    /**
     * Etiqueta nombre del hotel.
     */
    private JLabel lblHotel;

    /**
     * Etiqueta con las estrellas del hotel.
     */
    private JLabel lblEstrellas;

    /**
     * Etiqueta costo por noche en el hotel.
     */
    private JLabel lblCostoNoche;

    /**
     * Etiqueta con la imagen del hotel.
     */
    private JLabel lblImagenHotel;

    /**
     * Combo box con las ciudades que tienen hoteles disponibles.
     */
    private JComboBox cbCiudad;

    /**
     * Combo box con los nombres de los hoteles.
     */
    private JComboBox cbHotel;

    /**
     * Campo de texto con el costo por noche en el hotel.
     */
    private JTextField txtCostoNoche;
    
    /**
     * Botón para seleccionar el hotel.
     */
    private JButton btnSeleccionar;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo con la información del hotel.
     * <b> post: </b> Todos los elementos gráficos fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     * @param pDialogoReserva Dialogo para agregar una nueva reserva. pDialogoReserva != null.
     */
    public DialogoSeleccionHotel( InterfazCupiViajes pPrincipal, DialogoAgregarReserva pDialogoReserva )
    {   
        dialogoReserva = pDialogoReserva;
        principal = pPrincipal;
        setLayout( new BorderLayout( 10, 10 ) );
        setSize( new Dimension( 550, 250 ) );
        setLocationRelativeTo( principal );
        
        JPanel panelSuperior = new JPanel( );
        panelSuperior.setLayout( new GridLayout( 1, 1 ) );
        JLabel titulo = new JLabel( "Escoja su destino:" );
        titulo.setFont( new Font( "Verdana", Font.BOLD, 14 ) );
        panelSuperior.add( titulo );
        add( panelSuperior, BorderLayout.NORTH );

        JPanel panelAux1 = new JPanel( );
        panelAux1.setLayout( new GridLayout( 1, 1 ) );
        panelAux1.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

        lblImagenHotel = new JLabel( );
        panelAux1.add( lblImagenHotel );
        add( panelAux1, BorderLayout.EAST );

        JPanel panelAux2 = new JPanel( );
        panelAux2.setLayout( new GridLayout( 5, 2, 5, 5 ) );

        lblCiudad = new JLabel( "Ciudad:" );
        panelAux2.add( lblCiudad );

        cbCiudad = new JComboBox( );
        cbCiudad.setActionCommand( COMBO_CIUDAD );
        cbCiudad.addActionListener( this );
        panelAux2.add( cbCiudad );

        lblHotel = new JLabel( "Hotel:" );
        panelAux2.add( lblHotel );

        cbHotel = new JComboBox( );
        cbHotel.setActionCommand( COMBO_HOTEL );
        cbHotel.addActionListener( this );
        panelAux2.add( cbHotel );

        panelAux2.add( new JLabel( ) );

        lblEstrellas = new JLabel( );
        panelAux2.add( lblEstrellas );

        lblCostoNoche = new JLabel( "Costo por noche:" );
        panelAux2.add( lblCostoNoche );

        txtCostoNoche = new JTextField( );
        txtCostoNoche.setEditable( false );
        panelAux2.add( txtCostoNoche );
        
        panelAux2.add( new JLabel( ) );
        btnSeleccionar = new JButton( SELECCIONAR );
        btnSeleccionar.setActionCommand( SELECCIONAR );
        btnSeleccionar.addActionListener( this );
        panelAux2.add( btnSeleccionar );

        add( panelAux2, BorderLayout.WEST );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Inicializa el combo box con la lista de ciudades que tienen hotel.
     * <b> post: </b> El combo box tiene la lista de ciudades.
     * @param pCiudades Lista de ciudades con hotel. pCiudades != null.
     */
    public void inicializarCiudades( List<Hotel> pCiudades )
    {
        Hotel hotelTemp = null;
        for( int i = 0; i < pCiudades.size( ); i++ )
        {
            hotelTemp = pCiudades.get( i );
            if( !buscarCiudad( hotelTemp.darCiudad( ) ) )
            {
                cbCiudad.addItem( hotelTemp.darCiudad( ) );
            }
        }
    }
    
    /**
     * Actualiza los campos con la información del hotel dado por parámetro.
     * @param pHotel Hotel seleccionado. pHotel != null.
     */
    public void actualizar( Hotel pHotel )
    {
        cbCiudad.setSelectedItem( pHotel.darCiudad( ) );
        cbHotel.setSelectedItem( pHotel );
        lblEstrellas.setIcon( new ImageIcon( "./data/imagenes/"+pHotel.darEstrellas( )+"_estrellas.png" ) );
        txtCostoNoche.setText( pHotel.darCostoNoche( )+"" );
        lblImagenHotel.setIcon( new ImageIcon( pHotel.darRutaImagen( ) ) );
    }
    
    /**
     * Busca si existe una ciudad dada en el comboBox de ciudades.
     * @param pCiudad Ciudad buscada. pCiudad != null && pCiudad != "".
     * @return true si la ciudad ya existe en el comboBox de ciudades. False de lo contrario.
     */
    public boolean buscarCiudad( String pCiudad )
    {
        boolean encontro = false;
        String ciudad = "";
        for( int i = 0; i < cbCiudad.getItemCount( ) && !encontro; i++ )
        {
            ciudad = ( String )cbCiudad.getItemAt( i );
            if( ciudad.equals( pCiudad ) )
            {
                encontro = true;
            }
        }
        return encontro;
    }

    /**
     * Manejo de los eventos de los combo box y del evento generado por el botón.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( COMBO_CIUDAD ) )
        {
            String ciudad = ( String )cbCiudad.getSelectedItem( );
            List<Hotel> hoteles = principal.buscarHotelesPorCiudad( ciudad );
            cbHotel.removeAllItems( );
            for( int i = 0; i < hoteles.size( ); i++ )
            {
                cbHotel.addItem( ( Hotel  )hoteles.get( i ) );
            }
        }
        else if( comando.equals( COMBO_HOTEL ) )
        {
            Hotel hotelSeleccionado = ( Hotel )cbHotel.getSelectedItem( );
            if( hotelSeleccionado != null )
            {
                actualizar( hotelSeleccionado );
            }
        }
        else if( comando.equals( SELECCIONAR ) )
        {
            dialogoReserva.cambiarHotelSeleccionado( ( Hotel )cbHotel.getSelectedItem( ) );
            this.dispose( );
        }
    }

}
