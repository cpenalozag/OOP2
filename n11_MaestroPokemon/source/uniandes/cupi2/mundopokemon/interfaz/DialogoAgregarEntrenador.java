package uniandes.cupi2.mundopokemon.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoAgregarEntrenador extends JDialog implements ActionListener {

	public final static String REGISTRAR = "Registrar", CANCELAR = "Cancelar", BUSCAR ="...";
	
	private JButton btRegistrar, btCancelar, btBuscar;
	
	private JTextField txtNombre, txtImagen;
	
	private InterfazMaestrosPokemon principal;
	
	public DialogoAgregarEntrenador(InterfazMaestrosPokemon interfaz)
	{
		setTitle("Registrar entrenador");
		principal = interfaz;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(400, 180));
		setMaximumSize(new Dimension(400, 180));
		setMinimumSize(new Dimension(400, 180));
		setPreferredSize(new Dimension(400, 180));
		setLayout( new BorderLayout());
		
		JPanel panelBotones = new JPanel(new GridLayout(1,2));
		panelBotones.setBorder(BorderFactory.createTitledBorder("Opciones"));
		
		btRegistrar = new JButton(REGISTRAR);
		btRegistrar.setActionCommand(REGISTRAR);
		btRegistrar.addActionListener(this);
		panelBotones.add(btRegistrar);
		
		btCancelar = new JButton(CANCELAR);
		btCancelar.setActionCommand(CANCELAR);
		btCancelar.addActionListener(this);
		panelBotones.add(btCancelar);
		
		add(panelBotones, BorderLayout.SOUTH);
	
		JPanel panelDatos = new JPanel( new GridLayout(2,2));
		panelDatos.setBorder(BorderFactory.createTitledBorder("Datos"));
		

		
		panelDatos.add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		panelDatos.add(txtNombre);

		panelDatos.add( new JLabel("Imagen: "));
		


		JPanel pImagen = new JPanel(new BorderLayout());
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		pImagen.add(txtImagen, BorderLayout.CENTER);

		btBuscar = new JButton(BUSCAR);
		btBuscar.setActionCommand(BUSCAR);
		btBuscar.addActionListener(this);
		pImagen.add(btBuscar, BorderLayout.EAST);
		panelDatos.add(pImagen);
		
		add(panelDatos, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String c = e.getActionCommand();
		if(c.equals(BUSCAR))
		{
			JFileChooser fc = new JFileChooser("data/entrenadores");
			fc.setDialogTitle("Imagen");
			fc.setMultiSelectionEnabled(false);
			int x = fc.showOpenDialog(this);
			if( x == JFileChooser.APPROVE_OPTION)
			{
				String s = fc.getSelectedFile().getName();
				txtImagen.setText(s);
			}
		}
		if(c.equals(CANCELAR))
		{
			super.dispose();
		}
		if( c.equals(REGISTRAR))
		{
			String n = txtNombre.getText();
			String  i = txtImagen.getText();
			if( n == null || n.trim().equals("") || i == null || i.trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Complete todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				principal.agregarEntreador(n,i);
				super.dispose();
			}
		}

	}

}
