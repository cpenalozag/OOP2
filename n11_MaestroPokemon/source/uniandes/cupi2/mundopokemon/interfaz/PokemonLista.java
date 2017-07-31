package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import uniandes.cupi2.mundopokemon.mundo.Pokemon;

public class PokemonLista implements ListCellRenderer {

	public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
		
		if( arg1 instanceof Pokemon)
		{
			Pokemon poke = (Pokemon)arg1;
			JPanel p = new JPanel( new BorderLayout());
			if(arg3)
			{
				p.setBackground(Color.WHITE);
			}
			JLabel lbImagen = new JLabel( new ImageIcon(InterfazMaestrosPokemon.RUTA_POKEMONS + poke.darNombre() + ".png"));
			p.add(lbImagen, BorderLayout.WEST);
			p.add( new JLabel(poke.darNombre().substring(4, poke.darNombre().length())), BorderLayout.CENTER);
			return p;
		}
		return null;
	}


}
