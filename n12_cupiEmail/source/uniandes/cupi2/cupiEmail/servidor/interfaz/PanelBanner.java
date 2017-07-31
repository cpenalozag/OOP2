package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Clase donde se coloca la imagen del encabezado.<br>
 * @author CarlosPenaloza
 */
public class PanelBanner extends JPanel
{
	/**
     * Metodo constructor.
     */
    public PanelBanner()
    {
        setPreferredSize( new Dimension( 810, 105 ) );
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/imagenes/servidor.png" );
        // La agrega a la etiqueta
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );
        setBorder( new LineBorder( Color.BLACK ) );
    }

}
