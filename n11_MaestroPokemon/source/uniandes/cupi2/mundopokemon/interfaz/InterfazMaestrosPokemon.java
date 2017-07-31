package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import estructuras.arboles.ArbolBO;
import estructuras.arboles.ArbolN;
import uniandes.cupi2.mundopokemon.mundo.ElementoJerarquia;
import uniandes.cupi2.mundopokemon.mundo.Entrenador;
import uniandes.cupi2.mundopokemon.mundo.MundoPokemon;
import uniandes.cupi2.mundopokemon.mundo.Pokemon;
import uniandes.cupi2.mundopokemon.mundo.PokemonCapturado;
import uniandes.cupi2.mundopokemon.mundo.TipoElementoJerarquia;

public class InterfazMaestrosPokemon extends JFrame implements ActionListener {

	public final static String RUTA_POKEMONS = "data/pokemons/";
	
	public final static String RUTA_ENTRENADORES = "data/entrenadores/";
	
	public final static String COMBO_ENTRENADORES = "Entrenadores";
	
	private PanelPokemons panelPokemons;
	
	private PanelEntrenador panelEntrenador;
	
	private PanelOpciones panelOpciones;
	
	private JComboBox entrenadores;
	
	private MundoPokemon mundo;
	
	public InterfazMaestrosPokemon()
	{
		setTitle("Maestros Pokemon");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setSize(900, 500);
		setLayout( new BorderLayout());
		
		
		panelPokemons = new PanelPokemons(this);
		panelPokemons.setMinimumSize(new Dimension(200, 0));
		panelPokemons.setMaximumSize(new Dimension(200, 0));
		panelPokemons.setPreferredSize(new Dimension(200, 0));
		add(panelPokemons, BorderLayout.EAST);
		
		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBorder(BorderFactory.createTitledBorder("Entrenador"));
		
		entrenadores = new JComboBox();
		entrenadores.setActionCommand(COMBO_ENTRENADORES);
		entrenadores.setEditable(false);
		entrenadores.addActionListener(this);
		panelCentral.add(entrenadores, BorderLayout.NORTH);
		
		panelEntrenador = new PanelEntrenador(null, this);
		panelCentral.add(panelEntrenador, BorderLayout.CENTER);
		
		add(panelCentral, BorderLayout.CENTER);
		
		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		
		mundo = new MundoPokemon();
		panelPokemons.actualizar(mundo.darRaiz());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		InterfazMaestrosPokemon i = new InterfazMaestrosPokemon();
		i.setVisible(true);
	}

	public void actionPerformed(ActionEvent a) 
	{
		String c  = a.getActionCommand();
		if( c.equals(COMBO_ENTRENADORES))
		{
			Entrenador e = (Entrenador)entrenadores.getSelectedItem();
			panelEntrenador.actualizar(e);
		}
		
	}

	public void agregarEntreador(String nombre, String imagen) 
	{
		// TODO Auto-generated method stub
		boolean b = mundo.agregarEntrenador(nombre, imagen);
		if( b) 
		{
			JOptionPane.showMessageDialog(null,"Se agreg� el entrenador", "Agregar", JOptionPane.INFORMATION_MESSAGE); 
			actualizar();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Ya existe un entrenador llamado: " + nombre, "Agregar", JOptionPane.ERROR_MESSAGE); 
		}
			
		
	}

	public void actualizar() 
	{
		entrenadores.removeAllItems();
		for (Entrenador e : mundo.darEntrenadores()) {
			entrenadores.addItem(e);
		}
		entrenadores.setSelectedIndex(0);
		
		Entrenador e = (Entrenador)entrenadores.getSelectedItem();
		panelEntrenador.actualizar(e);
		
	}

	public void atraparPokemon() 
	{
		Pokemon p = panelPokemons.darSeleccionado();
		if(p!= null)
		{
			String s = panelEntrenador.capturar(panelPokemons.darSeleccionado());
			JOptionPane.showMessageDialog(this, s, "Capturar", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Debe seleccionar un pokem�n", "Capturar", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	public void mostrarDialogoAgregarEntrenador()
	{
		DialogoAgregarEntrenador d = new DialogoAgregarEntrenador(this);
		d.setVisible(true);
	}


	public void problema1()
	{
		String mensaje = mundo.opcion1();
		JOptionPane.showMessageDialog(this, mensaje, "Opci�n 1", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void problema2()
	{
		String mensaje = mundo.opcion2();
		JOptionPane.showMessageDialog(this, mensaje, "Opci�n 2", JOptionPane.INFORMATION_MESSAGE);

		
	}
	
	public PokemonCapturado aumentarNivel(PokemonCapturado pokemon, Entrenador entranador) {
		try {
			PokemonCapturado nuevo =  mundo.aumentarNivel(pokemon, entranador);
			if(!nuevo.darNombre().equals(pokemon.darNombre()))
			{
				JOptionPane.showMessageDialog(this, "Felicidades, " + pokemon.darNombre() + " ha evolucionado en: " + nuevo.darNombre(), "Evoluci�n", JOptionPane.INFORMATION_MESSAGE);

			}
			return nuevo;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, pokemon.darNombre() + " no puede evolucionar. Ya ha atrapado la evoluci�n.", "Evoluci�n", JOptionPane.ERROR_MESSAGE);
			return pokemon;
		}
	}

}
