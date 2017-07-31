package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListTransducedAccessorImpl;

import uniandes.cupi2.cupiEmail.servidor.mundo.ClienteRemoto;

/**
 * Es el panel donde se muestran los usuarios registrados en la base de datos
 */
public class PanelUsuarios extends JPanel implements ActionListener
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para el bot�n Ver todos los usuarios
	 */
	private static final String VER_TODOS = "Ver todos los usuarios";

	/**
	 * Comando para el bot�n Ver solo usuarios conectados
	 */
	private static final String VER_CONECTADOS = "Ver solo usuarios conectados";

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
	 * Es la lista donde se muestran los jugadores
	 */
	private JList listaUsuarios;

	/**
	 * Es el bot�n que se usa para ver todos los usuarios
	 */
	private JButton botonVerTodos;

	/**
	 * Es el bot�n que se usa para ver solo los usuarios conectados
	 */
	private JButton botonVerConectados;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel
	 * @param ventanaPrincipal Es una referencia a la ventana principal del servidor
	 */
	public PanelUsuarios( InterfazCupiEmail ventanaPrincipal )
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
		setLayout( new BorderLayout( ) );
		Font font1 = new Font("Arial", Font.BOLD, 13);
		Font font2 = new Font("Arial", Font.PLAIN, 13);

		JScrollPane scroll = new JScrollPane( );
		scroll.setPreferredSize(new Dimension(500, 80));
		listaUsuarios = new JList( );
		scroll.getViewport( ).add( listaUsuarios );
		add(scroll, BorderLayout.CENTER);
		listaUsuarios.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarActual();
			}
		});

		JPanel panelBotones= new JPanel();
		panelBotones.setLayout(new GridLayout());

		botonVerTodos = new JButton( VER_TODOS );
		botonVerTodos.setFont(font2);
		botonVerTodos.addActionListener( this );
		botonVerTodos.setActionCommand( VER_TODOS );

		botonVerConectados = new JButton( VER_CONECTADOS );
		botonVerConectados.setFont(font2);
		botonVerConectados.addActionListener( this );
		botonVerConectados.setActionCommand( VER_CONECTADOS );

		panelBotones.add(botonVerTodos);
		panelBotones.add(botonVerConectados);

		add(panelBotones, BorderLayout.NORTH); 

		TitledBorder b = new TitledBorder( "Usuarios" );
		b.setTitleFont(font1);
		scroll.setBorder( b );
	}

	/**
	 * Manda a la interfaz la información del cliente seleccionado en la lista
	 */
	public void actualizarActual()
	{
		if (listaUsuarios.getSelectedValue()!=null)
		{
			ClienteRemoto c = (ClienteRemoto) listaUsuarios.getSelectedValue();
			principal.actualizarInformacion(c);
		}
	}

	/**
	 * Actualiza la lista mostrada de jugadores
	 * @param usuariosP Es una colecci�n con la informaci�n de los jugadores que hay actualmente en la bd
	 */
	public void actualizarUsuarios( Collection usuariosP )
	{
		listaUsuarios.removeAll();
		listaUsuarios.setListData( usuariosP.toArray() );
		repaint();
		validate();
	}

	/**
	 * Es el m�todo llamado cuando se hace click sobre los botones
	 * @param e Es el evento de click sobre el bot�n
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(VER_TODOS))
		{
			botonVerTodos.setSelected(true);
			botonVerConectados.setSelected(false);
			principal.actualizarUsuarios();
		}
		else if (comando.equals(VER_CONECTADOS))
		{
			botonVerConectados.setSelected(true);
			botonVerTodos.setSelected(false);
			principal.actualizarUsuariosConectados();
		}
	}


}
