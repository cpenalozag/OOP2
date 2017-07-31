package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import uniandes.cupi2.mundopokemon.mundo.ElementoJerarquia;
import uniandes.cupi2.mundopokemon.mundo.PokemonCapturado;

import estructuras.arboles.NodoABO;

public class DialogoVerArbolEntrenador extends JDialog{

	private JTree arbol;
	
	private DefaultTreeModel modelo;
	
	public DialogoVerArbolEntrenador(NodoABO<String, PokemonCapturado> raiz)
	{
		setSize(300, 500);
		setMaximumSize(new Dimension(300, 500));
		setMinimumSize(new Dimension(300, 500));
		setPreferredSize(new Dimension(300, 500));
		setTitle("Vista arbol");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(1,1));
		
		arbol = new JTree();
		poblarModelo(raiz);
		arbol.setModel(modelo);
		
		JScrollPane scroll = new JScrollPane(arbol);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void poblarModelo(NodoABO<String, PokemonCapturado> raiz)
	{
		DefaultMutableTreeNode raizG = new DefaultMutableTreeNode(raiz);
		pintarHijos(raiz, raizG);
		modelo = new DefaultTreeModel(raizG);
	}
	
	private void pintarHijos(NodoABO<String, PokemonCapturado> nodo, DefaultMutableTreeNode nodoGrafico)
	{
		if(nodo.darIzquierda() != null)
		{
			DefaultMutableTreeNode nodoG = new DefaultMutableTreeNode(nodo.darIzquierda());
			pintarHijos(nodo.darIzquierda(), nodoG);
			nodoGrafico.add(nodoG);
		}
		else
		{
			DefaultMutableTreeNode nodoG = new DefaultMutableTreeNode("IZQ ?");
			nodoGrafico.add(nodoG);
		}
		if(nodo.darDerecha() != null)
		{
			DefaultMutableTreeNode nodoG = new DefaultMutableTreeNode(nodo.darDerecha());
			pintarHijos(nodo.darDerecha(), nodoG);
			nodoGrafico.add(nodoG);
		}
		else
		{
			DefaultMutableTreeNode nodoG = new DefaultMutableTreeNode("DER ?");
			nodoGrafico.add(nodoG);
		}
	}
}
