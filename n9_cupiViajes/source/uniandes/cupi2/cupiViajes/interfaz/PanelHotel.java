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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiViajes.mundo.Hotel;

/**
 * Panel con la información detallada de un hotel.
 */
public class PanelHotel extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiViajes principal;

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
     * Campo de texto con la ciudad dónde se encuentra el hotel reservado.
     */
    private JTextField txtCiudad;

    /**
     * Campo de texto con el nombre del hotel reservado.
     */
    private JTextField txtHotel;

    /**
     * Campo de texto con el costo por noche en el hotel.
     */
    private JTextField txtCostoNoche;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con la información del hotel.
     * <b> post: </b> Todos los elementos gráficos fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelHotel( InterfazCupiViajes pPrincipal )
    {
        principal = pPrincipal;
        
        setBorder( new TitledBorder( "Hotel reservado" ) );
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 500, 170 ) );

        JPanel panelAux1 = new JPanel( );
        panelAux1.setLayout( new GridLayout( 1, 1 ) );
        panelAux1.setBorder( new EmptyBorder( 0, 5, 0, 0 ) );

        lblImagenHotel = new JLabel( );
        panelAux1.add( lblImagenHotel );
        add( panelAux1, BorderLayout.EAST );

        JPanel panelAux2 = new JPanel( );
        panelAux2.setLayout( new GridLayout( 4, 2 ) );

        lblCiudad = new JLabel( "Ciudad:" );
        panelAux2.add( lblCiudad );

        txtCiudad = new JTextField( );
        txtCiudad.setEditable( false );
        panelAux2.add( txtCiudad );

        lblHotel = new JLabel( "Hotel:" );
        panelAux2.add( lblHotel );

        txtHotel = new JTextField( );
        txtHotel.setEditable( false );
        panelAux2.add( txtHotel );

        panelAux2.add( new JLabel( ) );

        lblEstrellas = new JLabel( );
        panelAux2.add( lblEstrellas );

        lblCostoNoche = new JLabel( "Costo por noche:" );
        panelAux2.add( lblCostoNoche );

        txtCostoNoche = new JTextField( );
        txtCostoNoche.setEditable( false );
        panelAux2.add( txtCostoNoche );

        add( panelAux2, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza los campos con la información del hotel dado por parámetro.
     * @param pHotel Hotel seleccionado. pHotel != null.
     */
    public void actualizar( Hotel pHotel )
    {
        txtCiudad.setText( pHotel.darCiudad( ) );
        txtHotel.setText( pHotel.darNombre( ) );
        lblEstrellas.setIcon( new ImageIcon( "./data/imagenes/"+pHotel.darEstrellas( )+"_estrellas.png" ) );
        txtCostoNoche.setText( pHotel.darCostoNoche( )+"" );
        lblImagenHotel.setIcon( new ImageIcon( pHotel.darRutaImagen( ) ) );
    }

}
