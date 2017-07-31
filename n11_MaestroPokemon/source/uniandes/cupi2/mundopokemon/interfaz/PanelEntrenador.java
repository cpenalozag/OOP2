package uniandes.cupi2.mundopokemon.interfaz;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.mundopokemon.mundo.Entrenador;
import uniandes.cupi2.mundopokemon.mundo.Pokemon;
import uniandes.cupi2.mundopokemon.mundo.PokemonCapturado;

public class PanelEntrenador extends JPanel implements ActionListener{

	public final static String CUANTOS = "Cuantos capturados", VER_ARBOL ="Ver arbol", MENOR = "Menor nombre", MAYORES_QUE = "Reto: Mayores que", ENTRE= "Reto: Entre", TIENE="Tiene Pokemon";
	
	private PanelPokemonDatos[ ] pokemons;
	
	private Entrenador entrenador;
	
	private JLabel lbImagen;
	
	private JButton btCuantos, btVerArbol, btMayores, btEntre, btMenor, btTiene;
	
	private InterfazMaestrosPokemon principal;
	
	public PanelEntrenador(Entrenador e, InterfazMaestrosPokemon interfaz)
	{
		setLayout( new BorderLayout());
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout( new GridLayout(1,2));
		principal = interfaz;
				
		entrenador = e;
		if( entrenador != null )
		{
			lbImagen = new JLabel(new ImageIcon(InterfazMaestrosPokemon.RUTA_ENTRENADORES + entrenador.darImagen()));
			lbImagen.setHorizontalAlignment(JLabel.CENTER);
			panelCentral.add(lbImagen);
			
			pokemons = new PanelPokemonDatos[80];
			JPanel panelPokemons = new JPanel(new GridLayout(20, 4));
			
			Iterator<PokemonCapturado> it = entrenador.darPokemons().iterator();
			int i = 0;
			while( it.hasNext() )
			{
				pokemons[i] = new PanelPokemonDatos(it.next(), i, this);
				panelPokemons.add(pokemons[i]);
				i++;
			}
			for (int j = i; j<80; j++) 
			{
				pokemons[j] = new PanelPokemonDatos(null, j, this);
				panelPokemons.add(pokemons[j]);
			}

			JScrollPane s = new JScrollPane(panelPokemons);
			s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			panelCentral.add(s);
		}
		else
		{
			lbImagen = new JLabel("");
			lbImagen.setHorizontalAlignment(JLabel.CENTER);
			panelCentral.add(lbImagen);
			
			pokemons = new PanelPokemonDatos[80];
			JPanel panelPokemons = new JPanel(new GridLayout(20, 4));
			
			
			for (int i = 0; i <80; i++)	
			{
				pokemons[i] = new PanelPokemonDatos(null, i, this);
				panelPokemons.add(pokemons[i]);
			}

			JScrollPane s = new JScrollPane(panelPokemons);
			s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			panelCentral.add(s);
		}
		
		add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel( new GridLayout(2,3));
		btVerArbol = new JButton(VER_ARBOL);
		btVerArbol.setActionCommand(VER_ARBOL);
		btVerArbol.addActionListener(this);
		panelBotones.add(btVerArbol);
		
		btCuantos = new JButton(CUANTOS);
		btCuantos.setActionCommand(CUANTOS);
		btCuantos.addActionListener(this);
		panelBotones.add(btCuantos);
		
		btMenor = new JButton(MENOR);
		btMenor.setActionCommand(MENOR);
		btMenor.addActionListener(this);
		panelBotones.add(btMenor);
		
		btTiene = new JButton(TIENE);
		btTiene.setActionCommand(TIENE);
		btTiene.addActionListener(this);
		panelBotones.add(btTiene);
		
		btMayores = new JButton(MAYORES_QUE);
		btMayores.setActionCommand(MAYORES_QUE);
		btMayores.addActionListener(this);
		panelBotones.add(btMayores);
		
		btEntre = new JButton(ENTRE);
		btEntre.setActionCommand(ENTRE);
		btEntre.addActionListener(this);
		panelBotones.add(btEntre);
		
		panelBotones.setBorder(BorderFactory.createTitledBorder("Información"));
		add(panelBotones, BorderLayout.SOUTH);
		
		habilitar();
	}
	
	private void habilitar()
	{
		if( entrenador == null )
		{
			btCuantos.setEnabled(false);
			btVerArbol.setEnabled(false);
			btMenor.setEnabled(false);
			btMayores.setEnabled(false);
			btEntre.setEnabled(false);
			btTiene.setEnabled(false);
		}
		else
		{
			btCuantos.setEnabled(true);
			btVerArbol.setEnabled(true);
			btMenor.setEnabled(true);
			btMayores.setEnabled(true);
			btEntre.setEnabled(true);
			btTiene.setEnabled(true);
		}
	}
	
	public void actualizar(Entrenador e)
	{
		entrenador = e;
		if( entrenador != null )
		{
			lbImagen.setIcon(new ImageIcon(InterfazMaestrosPokemon.RUTA_ENTRENADORES + entrenador.darImagen()));
			Iterator<PokemonCapturado> it = entrenador.darPokemons().iterator();
			int i = 0;
			while( it.hasNext() )
			{
				pokemons[i].actualizar(it.next());
				i++;
			}
			for (int j = i; j<80; j++) 
			{
				pokemons[j].actualizar(null);
			}
		}
		else
		{
			lbImagen.setIcon(null);
			
			
			for (int i = 0; i < 80; i++) 
			{
				pokemons[i].actualizar(null);
			}
		}
		habilitar();
	}

	public String capturar(Pokemon p)
	{
		if(entrenador != null)
		{
			String m = "Se ha atrapado  el pokemon";
			try {
				
				entrenador.capturarPokemon(p.darNombre(), p.darNumero(), 5 + (int)(Math.random()*7));
				actualizar(entrenador);
			} catch (Exception e) {
				m = e.getMessage();
			}
			
			return m;
		}
		return "Debe seleccionar un entrenador";
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String c = e.getActionCommand();
		if( entrenador != null )
		{
			if( c.equals(CUANTOS))
			{
				JOptionPane.showMessageDialog(null, "El entrenador tiene: " + entrenador.cuantosCapturados() + " pokemons", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
			else if( c.startsWith("Liberar"))
			{
				c = c.replace("Liberar", "");
				entrenador.liberarPokemon(c);
				principal.actualizar();
			}
			else if(c.equals(VER_ARBOL))
			{
				DialogoVerArbolEntrenador d = new DialogoVerArbolEntrenador(entrenador.darRaiz());
				d.setVisible(true);
			}
			else if(c.equals(MAYORES_QUE))
			{
				String lim = JOptionPane.showInputDialog(null, "Ingrese el límite", "Busqueda mayores que", JOptionPane.DEFAULT_OPTION);
				DialogoVerArbolEntrenador d = new DialogoVerArbolEntrenador(entrenador.darMayoresQue(lim).darRaiz());
				d.setVisible(true);
			}
			else if(c.equals(ENTRE))
			{
				String lim = JOptionPane.showInputDialog(null, "Ingrese el límite inferior", "Busqueda entre", JOptionPane.DEFAULT_OPTION);
				String lim2 = JOptionPane.showInputDialog(null, "Ingrese el límite superior", "Busqueda entre", JOptionPane.DEFAULT_OPTION);
				
				DialogoVerArbolEntrenador d = new DialogoVerArbolEntrenador(entrenador.darEntre(lim, lim2).darRaiz());
				d.setVisible(true);
			}
			else if(c.equals(MENOR))
			{
				String p = entrenador.darMenorNombre();
				JOptionPane.showMessageDialog(null, p==null?"El entrenador no tiene pokemon":"El pokemon con menor nombre es: " + p, "Información", JOptionPane.INFORMATION_MESSAGE);

			}
			
			else if(c.equals(TIENE))
			{
				String buscado = JOptionPane.showInputDialog(null, "Ingrese el pokemon buscado", "Busqueda", JOptionPane.DEFAULT_OPTION);
				JOptionPane.showMessageDialog(null, (entrenador.tienePokemon(buscado)?"El entrenador SI tiene al pokemon":"El entrenador NO tiene al pokemon"),"Busqeuda", JOptionPane.INFORMATION_MESSAGE);

			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Debe seleccionar un entrenador", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public PokemonCapturado aumentarNivel(PokemonCapturado pokemon) {
		return principal.aumentarNivel(pokemon, entrenador);
	}
	
	public Entrenador darEntrenador()
	{
		return entrenador;
	}
	

}
