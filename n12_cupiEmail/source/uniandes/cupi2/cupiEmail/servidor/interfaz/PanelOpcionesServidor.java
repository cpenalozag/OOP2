package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Clase con las opciones del programa
 * @author CarlosPenaloza
 */
public class PanelOpcionesServidor extends JPanel implements ActionListener
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para el bot�n de la opcion 1
	 */
	private static final String OPCION1 = "Opción 1";
	
	/**
	 * Comando para el bot�n de la opcion 1
	 */
	private static final String OPCION2 = "Opción 2";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es una referencia a la ventana principal de la aplicaci�n del servidor
	 */
	private InterfazCupiEmail principal;

	// -----------------------------------------------------------------
	// Atributos de la Interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta para la opcion 1
	 */
	private JButton butOpcion1;

	/**
	 * Etiqueta para la opcion 2
	 */
	private JButton butOpcion2;

	/**
	 * Inicializa el panel
	 * @param ventanaPrincipal Es una referencia a la ventana principal del servidor
	 */
	public PanelOpcionesServidor( InterfazCupiEmail ventanaPrincipal )
	{
		principal = ventanaPrincipal;
		inicializarPanel( );
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Inicializa los elementos dentro del panel
	 */
	private void inicializarPanel( )
	{
		Font font1 = new Font("Arial", Font.BOLD, 13);
		Font font2 = new Font("Arial", Font.PLAIN, 13);
		TitledBorder b = new TitledBorder( "Opciones" );
		b.setTitleFont(font1);
		this.setBorder( b );
		setLayout( new GridLayout(1, 2 ) );
		setPreferredSize(new Dimension (680,45));

		butOpcion1 = new JButton("Opción 1");
		butOpcion1.setFont(font2);
		butOpcion1.addActionListener(this);
		butOpcion1.setActionCommand(OPCION1);
		add(butOpcion1);

		butOpcion2 = new JButton("Opción 2");
		butOpcion2.setFont(font2);
		butOpcion2.addActionListener(this);
		butOpcion2.setActionCommand(OPCION2);
		add(butOpcion2);

	}

	/**
	 * Es el m�todo llamado cuando se hace click sobre los botones
	 * @param e Es el evento de click sobre el bot�n
	 */
	
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		switch(comando)
		{
		case (OPCION1):
			principal.reqFuncOpcion1();
			break;
		case (OPCION2):
			principal.reqFuncOpcion2();
			break;
		}
		
	}

}
