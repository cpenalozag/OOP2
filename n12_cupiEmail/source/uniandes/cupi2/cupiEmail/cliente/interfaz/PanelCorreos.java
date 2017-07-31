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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoElectronico;
import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoEnviado;

/**
 * Panel con la lista de correos
 */
public class PanelCorreos extends JPanel implements ActionListener
{
    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para consultar los correos recibidos.
     */
    private final static String RECIBIDOS = "RECIBIDOS";
    
    /**
     * Constante que representa el comando para consultar los correos enviados.
     */
    private final static String ENVIADOS = "ENVIADOS";
    
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para la lista de correos.
     */
    private JPanel panelListaCorreos;

    /**
     * Scroll para lista de correos.
     */
    private JScrollPane scpcorreos;

    /**
     * Botón para consultar los correos recibidos.
     */
    private JButton btnRecibidos;
    
    /**
     * Botón para consultar los correos enviados.
     */
    private JButton btnEnviados;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelCorreos( InterfazCliente pPrincipal )
    {
        principal = pPrincipal;
        scpcorreos = new JScrollPane( );
        panelListaCorreos = new JPanel( );
        setLayout( new BorderLayout( ) );
        panelListaCorreos.setLayout( new BoxLayout( panelListaCorreos, BoxLayout.PAGE_AXIS ) );
        scpcorreos.setViewportView( panelListaCorreos );
        add(scpcorreos,BorderLayout.CENTER);
        
        JPanel panelAux = new JPanel( );
        panelAux.setLayout( new GridLayout( 1, 2 ) );
        btnRecibidos = new JButton( "Recibidos" );
        btnRecibidos.addActionListener( this );
        btnRecibidos.setActionCommand( RECIBIDOS );
        btnEnviados = new JButton( "Enviados" );
        btnEnviados.addActionListener( this );
        btnEnviados.setActionCommand( ENVIADOS );
        panelAux.add( btnRecibidos );
        panelAux.add( btnEnviados );
        add(panelAux, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que actualiza la lista de correos recibidos.
     * @param pCorreos Lista de correos. pCorreos != null.
     */
    public void actualizarRecibidos( ArrayList pCorreos )
    {
        panelListaCorreos.removeAll( );
        for( int i = 0; i < pCorreos.size( ); i++ )
        {
            CorreoElectronico correo = ( CorreoElectronico )pCorreos.get( i );
            PanelCorreo pCorreo = new PanelCorreo( principal, correo, PanelCorreo.RECIBIDOS );
            panelListaCorreos.add( pCorreo );
            panelListaCorreos.add( new JSeparator( ) );
        }
        updateUI( );
    }
    
    /**
     * Método que actualiza la lista de correos enviados.
     * @param pCorreos Lista de correos. pCorreos != null.
     */
    public void actualizarEnviados (ArrayList pCorreos )
    {
        panelListaCorreos.removeAll( );
        for( int i = 0; i < pCorreos.size( ); i++ )
        {
            CorreoEnviado correoEnv = ( CorreoEnviado )pCorreos.get( i );
            CorreoElectronico correo= new CorreoElectronico( correoEnv.darLoginDestinatarios( ), correoEnv.darFechaEnvio( ), correoEnv.darAsunto( ), correoEnv.darMensaje( ), false );
            PanelCorreo pCorreo = new PanelCorreo( principal, correo, PanelCorreo.ENVIADOS );
            panelListaCorreos.add( pCorreo );
            panelListaCorreos.add( new JSeparator( ) );
        }
        updateUI( );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if(comando.equals( RECIBIDOS ))
        {
            principal.consultarCorreos( );
        }
        else if(comando.equals( ENVIADOS ))
        {
            principal.consultarCorreosEnviados( );
        }
    }
}