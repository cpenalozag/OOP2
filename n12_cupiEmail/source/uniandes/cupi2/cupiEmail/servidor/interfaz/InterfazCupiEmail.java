package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupiEmail.servidor.mundo.ClienteRemoto;
import uniandes.cupi2.cupiEmail.servidor.mundo.CupiEmail;



/**
 * Ventana principal del servidor de cupi email.
 * @author CarlosPenaloza
 *
 */
public class InterfazCupiEmail extends JFrame
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Clase principal del servidor
	 */
	private CupiEmail servidorCupiEmail;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Es el panel donde se muestran la información de los usuarios
	 */
	private PanelBanner panelBanner;

	/**
	 * Es el panel donde se muestran los usuarios registrados
	 */
	private PanelUsuarios panelUsuarios;

	/**
	 * Es el panel donde se muestran la información de los usuarios
	 */
	private PanelInformacionUsuarios panelInformacionUsuarios;

	/**
	 * Panel con las extensiones
	 */
	private PanelOpcionesServidor panelOpciones;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye la ventana principal de la aplicaci�n<br>
	 * @param servidor Es una referencia al servidor sobre el que funciona esta interfaz
	 */
	public InterfazCupiEmail(CupiEmail servidor) {
		servidorCupiEmail = servidor;
		inicializarVentana( );
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Inicializa los elementos de la ventana principal
	 */
	private void inicializarVentana( )
	{
		// Construye la forma
		setLayout( new BorderLayout( ) );
		setSize( 700, 540 );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Servidor CupiEmail" );
		setLocationRelativeTo(null);
		setResizable(false);

		// Creaci�n de los paneles aqu�

		panelBanner = new PanelBanner( );
		add (panelBanner, BorderLayout.NORTH);

		panelUsuarios = new PanelUsuarios(this);
		add (panelUsuarios, BorderLayout.CENTER);

		panelInformacionUsuarios = new PanelInformacionUsuarios(this);
		add (panelInformacionUsuarios, BorderLayout.EAST);

		panelOpciones = new PanelOpcionesServidor(this);
		add (panelOpciones, BorderLayout.SOUTH);
	}

	/**
	 * Actualiza la lista de usuarios mostrada en el panel de usuarios
	 */
	public void actualizarUsuarios( )
	{
		try
		{
			ArrayList<ClienteRemoto> usuarios = new ArrayList<ClienteRemoto>();
			usuarios = servidorCupiEmail.darAdministradorUsuarios().darUsuarios();
			panelUsuarios.actualizarUsuarios( usuarios );
		}
		catch( SQLException e )
		{
			JOptionPane.showMessageDialog( this, "Hubo un error consultando la lista de jugadores:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

	/**
	 * Actualiza la lista de usuarios conectados mostrada en el panel de usuarios
	 */
	public void actualizarUsuariosConectados( )
	{
			ArrayList<ClienteRemoto> u = new ArrayList<>();
			try {
				u = servidorCupiEmail.darAdministradorUsuarios().darUsuariosConectados();
				panelUsuarios.actualizarUsuarios( u );
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Actualiza la información del panel información usuario.
	 * @param cliente Seleccionado en la lista de clusuarios
	 */
	public void actualizarInformacion(ClienteRemoto cliente)
	{
		int num = servidorCupiEmail.darListaActualizadaCorreos(cliente).size();
		int numNoLeidos = servidorCupiEmail.darListaActualizadaCorreosNoLeidos(cliente).size();
		
		panelInformacionUsuarios.actualizarInformacion(cliente, num, numNoLeidos);
	}


	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la extensi�n 1
	 */
	public void reqFuncOpcion1( )
	{
		String resultado = servidorCupiEmail.metodo1( );
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * M�todo para la extensi�n 2
	 */
	public void reqFuncOpcion2( )
	{
		String resultado = servidorCupiEmail.metodo2( );
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}


	/**
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
	 * @param args Par�metros de ejecuci�n que no son usados
	 */
	public static void main( String[] args )
	{
		try
		{
			String archivoPropiedades = "./data/servidor.properties";
			CupiEmail servidorCupiEmail = new CupiEmail( archivoPropiedades );

			InterfazCupiEmail interfaz = new InterfazCupiEmail( servidorCupiEmail );
			interfaz.setVisible( true );

			servidorCupiEmail.recibirConexiones();
		}
		catch( Exception e )
		{            
			e.printStackTrace( );
		}
	}

}
