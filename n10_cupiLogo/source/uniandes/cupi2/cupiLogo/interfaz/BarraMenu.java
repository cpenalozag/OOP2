/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiLogo.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Barra que contiene los menús de la aplicación.
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante que representa el comando Nuevo.
     */
    private static final String NUEVO = "Nuevo";

    /**
     * Constante que representa el comando Abrir.
     */
    private static final String ABRIR = "Abrir";

    /**
     * Constante que representa el comando Guardar.
     */
    private static final String GUARDAR = "Guardar";
    
    /**
     * Constante que representa el comando Guardar como.
     */
    private static final String GUARDAR_COMO = "GuardarComo";

    /**
     * Constante que representa el comando Salir.
     */
    private static final String SALIR = "Salir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiLogo principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El menú Archivo.
     */
    private JMenu menuArchivo;

    /**
     * La opción Nuevo del menú Archivo.
     */
    private JMenuItem itemNuevo;

    /**
     * La opción Abrir del menú Archivo.
     */
    private JMenuItem itemAbrir;

    /**
     * La opción Guardar del menú Archivo.
     */
    private JMenuItem itemGuardar;

    /**
     * La opción Guardar como del menú Archivo.
     */
    private JMenuItem itemGuardarComo;

    /**
     * La opción Salir del menú Archivo.
     */
    private JMenuItem itemSalir;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la barra de menú.<br>
     * <b> post: </b> Todos los elementos gráficos del menú fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public BarraMenu( InterfazCupiLogo pPrincipal )
    {
        principal = pPrincipal;

        menuArchivo = new JMenu( "Archivo" );
        add( menuArchivo );

        itemNuevo = new JMenuItem( "Nuevo" );
        itemNuevo.setActionCommand( NUEVO );
        itemNuevo.addActionListener( this );
        menuArchivo.add( itemNuevo );

        itemAbrir = new JMenuItem( "Abrir" );
        itemAbrir.setActionCommand( ABRIR );
        itemAbrir.addActionListener( this );
        menuArchivo.add( itemAbrir );
        
        itemGuardar = new JMenuItem( "Guardar" );
        itemGuardar.setActionCommand(GUARDAR);
        itemGuardar.addActionListener(this);
        menuArchivo.add(itemGuardar);
        
        itemGuardarComo = new JMenuItem( "Guardar como" );
        itemGuardarComo.setActionCommand(GUARDAR_COMO);
        itemGuardarComo.addActionListener(this);
        menuArchivo.add(itemGuardarComo);
        
        menuArchivo.addSeparator();
        
        itemSalir = new JMenuItem( "Salir" );
        itemSalir.setActionCommand(SALIR);
        itemSalir.addActionListener(this);
        menuArchivo.add(itemSalir);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los menús.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        	
        switch (comando){
        case NUEVO:
        	principal.nuevo();
        	break;
        case ABRIR:
        	principal.abrir();
        	break;
        case GUARDAR:
        	principal.guardar();
        	break;
        case GUARDAR_COMO:
        	principal.guardarComo();
        	break;
        }
    }

}