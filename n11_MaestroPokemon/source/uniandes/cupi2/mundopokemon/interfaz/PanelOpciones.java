package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOpciones extends JPanel implements ActionListener {

	public final static String AGREGAR_ENTRENADOR = "Registrar Entrenador";
	
	public final static String[] PROBLEMAS = {"Opción 1", "Opción 2"};
	
	private JButton btAgregarEntreador, btProblema1, btProblema2;
	
	private InterfazMaestrosPokemon principal;
	
	public PanelOpciones(InterfazMaestrosPokemon interfaz)
	{
		principal = interfaz;
		setBorder(BorderFactory.createTitledBorder("Opciones"));
		setLayout(new GridLayout(1,3));
		
		btAgregarEntreador = new JButton(AGREGAR_ENTRENADOR);
		btAgregarEntreador.setActionCommand(AGREGAR_ENTRENADOR);
		btAgregarEntreador.addActionListener(this);
		add(btAgregarEntreador);
		
		
		
		btProblema1 = new JButton(PROBLEMAS[0]);
		btProblema1.setActionCommand(PROBLEMAS[0]);
		btProblema1.addActionListener(this);
		add(btProblema1);
		
		btProblema2 = new JButton(PROBLEMAS[1]);
		btProblema2.setActionCommand(PROBLEMAS[1]);
		btProblema2.addActionListener(this);
		add(btProblema2);
		
		
	}
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if( comando.equals(AGREGAR_ENTRENADOR))
		{
			principal.mostrarDialogoAgregarEntrenador();
		}
		else if (comando.equals(PROBLEMAS[0]))
		{
			principal.problema1();
		}
		else if (comando.equals(PROBLEMAS[1]))
		{
			principal.problema2();
		}
		

	}

}
