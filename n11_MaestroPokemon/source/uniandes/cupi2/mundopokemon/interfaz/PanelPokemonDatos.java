package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.mundopokemon.mundo.PokemonCapturado;

public class PanelPokemonDatos extends JPanel implements MouseListener{

	private PokemonCapturado pokemon;
	
	private JButton btLiberar;
	
	private JLabel lbImagen, lbNombre;
	
	private String numero;
	
	private PanelEntrenador entrenador;
	
	public PanelPokemonDatos(PokemonCapturado nPokemon, int p, PanelEntrenador padre)
	{
		setLayout( new BorderLayout());
		entrenador = padre;
		pokemon = nPokemon;
		addMouseListener(this);
		
		if( pokemon != null )
		{
			setBorder(new TitledBorder(pokemon.darNombre()));
			lbImagen =  new JLabel(InterfazMaestrosPokemon.RUTA_POKEMONS + pokemon.darImagen() + ".png");
			lbNombre = new JLabel("Nivel: " + pokemon.darNivel());
		}
		else
		{
			setBorder(BorderFactory.createTitledBorder(""));
			lbImagen = new JLabel(new ImageIcon("data/pokeball.png"));
			lbNombre = new JLabel("");
		}
		add(lbImagen, BorderLayout.CENTER);
		add(lbNombre, BorderLayout.NORTH);
		
		btLiberar = new JButton("Liberar");
		btLiberar.addActionListener(padre);
		add(btLiberar, BorderLayout.SOUTH);
		
	}
	
	public void actualizar(PokemonCapturado nPokemon)
	{
		pokemon = nPokemon;
		if( pokemon != null )
		{
			setBorder(new TitledBorder(pokemon.darNombre()));
			numero = pokemon.darNumero();
			lbImagen.setIcon(new ImageIcon(InterfazMaestrosPokemon.RUTA_POKEMONS + pokemon.darImagen()));
			btLiberar.setActionCommand("Liberar" + pokemon.darNombre());
			lbNombre.setText("Nivel: " + pokemon.darNivel());
			
			
		}
		else
		{
			setBorder(BorderFactory.createTitledBorder(""));
			lbImagen.setIcon( new ImageIcon( "data/pokeball.png"));
			lbNombre.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		try {
			new AePlayWave("data/wav/" + numero + ".wav").start();
			pokemon = entrenador.aumentarNivel(pokemon);
			actualizar(pokemon);
		} catch (Exception ex) 
		{
			// TODO: handle exception
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
