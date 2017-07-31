/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiEmail.cliente.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel con las opciones de la interfaz.
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de la opción actualizar los correos.
     */
    private static final String ACTUALIZAR_CORREOS = "ACTUALIZAR_CORREOS";

    /**
     * Comando de la opción escribir correo.
     */
    private static final String ESCRIBIR_CORREOS = "ESCRIBIR_CORREOS";

    /**
     * Comando de la opción usuario más popular.
     */
    private static final String MAS_POPULAR = "MAS_POPULAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     *Referencia a la clase principal de la interfaz del cliente.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón actualizar
     */
    private JButton btnActualizar;

    /**
     * Botón escribir correo.
     */
    private JButton btnEscribirCorreo;

    /**
     * Botón usuario más popular.
     */
    private JButton btnMasPopular;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel de opciones.
     * @param pPrincipal Referencia a la ventana principal. pPrincipal != null.
     */
    public PanelOpciones( InterfazCliente pPrincipal )
    {
        principal = pPrincipal;
        GridBagConstraints gridBagConstraints;
        btnEscribirCorreo = new JButton( );
        btnActualizar = new JButton( );
        btnMasPopular = new JButton( );

        setBorder( BorderFactory.createTitledBorder( "Opciones" ) );
        setLayout( new GridBagLayout( ) );

        btnEscribirCorreo.setText( "Escribir correo" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        btnEscribirCorreo.addActionListener( this );
        btnEscribirCorreo.setActionCommand( ESCRIBIR_CORREOS );
        add( btnEscribirCorreo, gridBagConstraints );

        gridBagConstraints.gridy = 1;
        btnActualizar.setText( "Refrescar" );
        btnActualizar.setActionCommand( ACTUALIZAR_CORREOS );
        btnActualizar.addActionListener( this );
        add( btnActualizar, gridBagConstraints );


        btnMasPopular.setText( "Usuario más popular" );
        btnMasPopular.setActionCommand( MAS_POPULAR );
        btnMasPopular.addActionListener( this );

        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

        add( btnMasPopular, gridBagConstraints );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( ACTUALIZAR_CORREOS ) )
        {
            principal.consultarCorreos( );
        }
        else if( command.equals( ESCRIBIR_CORREOS ) )
        {
            principal.escribirCorreoNuevo( );
        }
        else if( MAS_POPULAR.equals( pEvento.getActionCommand( ) ) )
        {
            principal.consultarUsuarioMasPopular( );
        }
    }
}