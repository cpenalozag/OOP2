package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import estructuras.arboles.NodoAN;

import uniandes.cupi2.mundopokemon.mundo.ElementoJerarquia;
import uniandes.cupi2.mundopokemon.mundo.Pokemon;

public class PanelPokemons extends JPanel implements ActionListener{

	public final static String ATRAPAR = "Atrapar Pokemon";
	
	private JTree listaPokemons;
	
	private DefaultTreeModel modelo;
	
	private JButton btAtrapar;
	
	private InterfazMaestrosPokemon principal;
	
	public PanelPokemons( InterfazMaestrosPokemon i){
		principal = i;
		setLayout( new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Pokemons"));
		
		listaPokemons = new JTree();
		modelo = new DefaultTreeModel(null);
		listaPokemons.setModel(modelo);
		listaPokemons.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		JScrollPane scroll = new JScrollPane(listaPokemons);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		add(scroll, BorderLayout.CENTER);
		

		btAtrapar = new JButton(ATRAPAR);
		btAtrapar.setActionCommand(ATRAPAR);
		btAtrapar.addActionListener(this);
		add(btAtrapar, BorderLayout.SOUTH);
	}
	
	public void actualizar(NodoAN<String, ElementoJerarquia> raiz)
	{
		DefaultMutableTreeNode nRaiz = new DefaultMutableTreeNode(raiz.getValue());
		
		pintarHijos(raiz, nRaiz);
		
		modelo = new DefaultTreeModel(nRaiz);
		listaPokemons.setModel(modelo);
	}
	
	private void pintarHijos(NodoAN<String, ElementoJerarquia> nodo, DefaultMutableTreeNode nodoGrafico)
	{
		Collection<NodoAN<String, ElementoJerarquia>> hijos = nodo.darHijos();
		Iterator<NodoAN<String, ElementoJerarquia>> it = hijos.iterator();
		while( it.hasNext())
		{
			NodoAN<String, ElementoJerarquia> act = it.next();
			DefaultMutableTreeNode nNodoG = new DefaultMutableTreeNode(act.getValue());
			nodoGrafico.add(nNodoG);
			pintarHijos(act, nNodoG);
		}
	}

	public Pokemon darSeleccionado() 
	{
		TreePath camino = listaPokemons.getSelectionPath();
		DefaultMutableTreeNode pok  = (DefaultMutableTreeNode)camino.getLastPathComponent();
		Object obj = pok.getUserObject();
		if( obj instanceof Pokemon)
		{
			return (Pokemon)pok.getUserObject();
		}
		else
		{
			return null;
		}
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(ATRAPAR))
		{
			principal.atraparPokemon();
		}


	}
}
