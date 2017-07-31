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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Panel donde se se dibuja la tortuga y sus acciones.
 */
public class PanelTablero extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiLogo principal;

    /**
     * Ancho del panel.
     */
    private int ancho;

    /**
     * Altura del panel.
     */
    private int alto;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Método constructor por defecto. Coloca la imagen del encabezado de la aplicación.<br>
     * <b> post: </b> Se ha inicializado el panel vacío con las dimensiones definidas.
     * @param pAncho Ancho del panel. pAncho > 0.
     * @param pAlto Altura del panel. pAlto > 0. 
     * @param pPrincipal Ventana principal. pPrincipal != null. 
     */
    public PanelTablero( int pAncho, int pAlto, InterfazCupiLogo pPrincipal )
    {
        principal = pPrincipal;

        ancho = pAncho;
        alto = pAlto;

        setPreferredSize( new Dimension( ancho, alto ) );
        setBackground( Color.WHITE );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que re-dibuja el panel.
     * <b> post: </b> Se ha dibujado la tortuga y las acciones que se han ejecutado sobre ella.
     * @param pG Tablero para pintar. pG != null.
     */
    public void paintComponent( Graphics pG )
    {
        super.paintComponent( pG );

        Graphics2D g2 = ( Graphics2D )pG;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2.setColor( getBackground( ) );
        g2.fillRect( 0, 0, getWidth( ), getHeight( ) );

        principal.actualizar( g2 );
    }

    /**
     * Refresca la información y el dibujo del panel.
     */
    public void refrescar( )
    {
        repaint( );
    }
}
