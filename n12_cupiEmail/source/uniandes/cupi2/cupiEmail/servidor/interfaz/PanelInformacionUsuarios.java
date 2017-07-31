package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiEmail.cliente.mundo.Usuario;
import uniandes.cupi2.cupiEmail.servidor.mundo.ClienteRemoto;

/**
 * Panel con la ifnromación de los usuarios de CupiEmail
 * @author CarlosPenaloza
 */
public class PanelInformacionUsuarios extends JPanel
{
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
	 * Etiqueta para el login
	 */
	private JLabel labLogin;

	/**
	 * Etiqueta para el nombre
	 */
	private JLabel labNombre;

	/**
	 * Etiqueta para el apellidos
	 */
	private JLabel labApellidos;

	/**
	 * Etiqueta para el numero total de correos
	 */
	private JLabel labTotalCorreos;

	/**
	 * Etiqueta para el numero total de correos sin leer
	 */
	private JLabel labTotalCorreosSinLeer;
	

	/**
	 * Etiqueta para el login
	 */
	private JTextField txtLogin;

	/**
	 * Etiqueta para el nombre
	 */
	private JTextField txtNombre;

	/**
	 * Etiqueta para el apellidos
	 */
	private JTextField txtApellidos;

	/**
	 * Etiqueta para el numero total de correos
	 */
	private JTextField txtTotalCorreos;

	/**
	 * Etiqueta para el numero total de correos sin leer
	 */
	private JTextField txtTotalCorreosSinLeer;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel
	 * @param ventanaPrincipal Es una referencia a la ventana principal del servidor
	 */
	public PanelInformacionUsuarios( InterfazCupiEmail ventanaPrincipal )
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
		TitledBorder b = new TitledBorder( "Información del usuario" );
		b.setTitleFont(font1);
		this.setBorder( b );
		setLayout( new GridLayout(9, 2 ) );
		
		labLogin = new JLabel("Login:");
		labLogin.setFont(font1);
		labNombre = new JLabel("Nombre:");
		labNombre.setFont(font1);
		labApellidos = new JLabel("Apellidos:");
		labApellidos.setFont(font1);
		labTotalCorreos = new JLabel("Total correos:");
		labTotalCorreos.setFont(font1);
		labTotalCorreosSinLeer = new JLabel("Total correos sin leer:");
		labTotalCorreosSinLeer.setFont(font1);
		
		txtLogin = new JTextField();
		txtLogin.setEditable(false);
		txtLogin.setBackground(null);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBackground(null);
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setBackground(null);
		
		txtTotalCorreos = new JTextField();
		txtTotalCorreos.setEditable(false);
		txtTotalCorreos.setBackground(null);
		
		txtTotalCorreosSinLeer = new JTextField();
		txtTotalCorreosSinLeer.setEditable(false);
		txtTotalCorreosSinLeer.setBackground(null);

		add(labLogin);
		add(txtLogin);
		add(new JLabel());
		add(new JLabel());
		add(labNombre);
		add(txtNombre);
		add(new JLabel());
		add(new JLabel());
		add(labApellidos);
		add(txtApellidos);
		add(new JLabel());
		add(new JLabel());
		add(labTotalCorreos);
		add(txtTotalCorreos);
		add(new JLabel());
		add(new JLabel());
		add(labTotalCorreosSinLeer);
		add(txtTotalCorreosSinLeer);

	}
	
	/**
	 * Actualiza la lista de inforación sobre el usuario seleccionado.<br>
	 * <b>pre:</b> La lista de usuarios se encuentra inicializada.<br>
	 * <b>post:</b> Se ha actualizado el panel de información de datos.
	 * @param actual Usuario actualmente seleccionado en la lista
	 * @param correos Cantidad total de correos
	 * @param correosNoLeidos Cantidad de correos no leidos
	 */
	public void actualizarInformacion (ClienteRemoto actual, int correos, int correosNoLeidos)
	{
		txtLogin.setText(actual.darLogin());
		txtNombre.setText(actual.darNombresUsuario());
		txtApellidos.setText(actual.darApellidos());
		txtTotalCorreos.setText(""+correos);
		txtTotalCorreosSinLeer.setText(""+correosNoLeidos);
		;
	}
}
