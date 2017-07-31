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

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.prism.paint.Color;

import uniandes.cupi2.cupiLogo.mundo.ComandoCambiarColor;
import uniandes.cupi2.cupiLogo.mundo.IComando;
import uniandes.cupi2.cupiLogo.mundo.PersistenciaException;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;
import uniandes.cupi2.cupiLogo.mundo.Tortuga;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazCupiLogo extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Ancho del tablero del logo.
     */
    private static final int ANCHO = 800;

    /**
     * Alto del tablero del logo.
     */
    private static final int ALTO = 450;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private TableroLogo tableroLogo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones.
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con el tablero.
     */
    private PanelTablero panelTablero;

    /**
     * Panel con los botones de los comandos.
     */
    private PanelComandos panelComandos;

    /**
     * Barra de menús.
     */
    private BarraMenu barraMenu;

    /**
     * Archivo donde se guardan los comandos.
     */
    private File archivoGuardar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz del cupiLogo.<br>
     * <b> post: </b> Todos los páneles fueron inicializados y agregados.
     */
    public InterfazCupiLogo( )
    {
        // Construye la forma
        setTitle( "CupiLogo" );
        setLayout( new BorderLayout( ) );
        setSize( ANCHO + 50, ALTO + 250 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelTablero = new PanelTablero( ANCHO, ALTO, this );
        add( panelTablero, BorderLayout.CENTER );

        panelComandos = new PanelComandos( this );
        add( panelComandos, BorderLayout.WEST );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        barraMenu = new BarraMenu( this );
        setJMenuBar( barraMenu );

        // Centrar la ventana
        setLocationRelativeTo( null );

        // Inicializa el mundo
        tableroLogo = new TableroLogo( ANCHO, ALTO );
        panelTablero.refrescar( );
        ComandoCambiarColor comando = new ComandoCambiarColor( Color.BLACK.getIntArgbPre() );
        agregarComando(comando);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un comando y actualiza el panel.<br>
     * <b> post: </b> Se ha agregado el comando a la lista de comandos.
     * @param pComando Comando a agregar. pComando != null.
     */
    public void agregarComando( IComando pComando )
    {
        tableroLogo.agregarComando( pComando );
        panelTablero.refrescar( );
    }

    /**
     * Deshace la última acción de la lista de comandos.
     */
    public void deshacer( )
    {
        if( !tableroLogo.eliminarUltimoComando( ) )
        {
            JOptionPane.showMessageDialog( this, "No hay más acciones para deshacer.", "Deshacer", JOptionPane.INFORMATION_MESSAGE );
        }
        panelTablero.refrescar( );
    }

    /**
     * Crea una nueva lista de comandos que reemplaza a la actual, preguntando al usuario si quiere guardar la anterior.
     */
    public void nuevo( )
    {
        int opcion = JOptionPane.showConfirmDialog( this, "¿Desea guardar los cambios antes de crear una nueva trayectoria?", "Nueva trayectoria", JOptionPane.INFORMATION_MESSAGE );
        if( opcion == JOptionPane.OK_OPTION )
        {
            guardarComo( );
        }
        tableroLogo.limpiarComandos( );
        panelTablero.refrescar( );
    }

    /**
     * Carga los comandos de un archivo seleccionado.
     */
    public void abrir( )
    {
        JFileChooser chooser = new JFileChooser( new File("./data") );
        chooser.showOpenDialog( this );

        File archivo = chooser.getSelectedFile( );
        if( archivo != null )
        {
            archivoGuardar = archivo;
            try
            {
                tableroLogo.cargarComandos( archivoGuardar );
                panelTablero.refrescar( );
            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Guarda los comandos en un archivo nuevo.
     */
    public void guardarComo( )
    {
        JFileChooser chooser = new JFileChooser( new File("./data") );
        chooser.showSaveDialog( this );

        File archivo = chooser.getSelectedFile( );
        if( archivo != null )
        {
            archivoGuardar = archivo;
            guardar( );
        }
    }

    /**
     * Guarda los comandos en el archivo.
     */
    public void guardar( )
    {
        try
        {
            if( archivoGuardar != null )
                tableroLogo.guardarComandos( archivoGuardar );
            else
                guardarComo( );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error guardando", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Pregunta si se desea guardar el archivo antes de salir.
     */
    public void dispose( )
    {
        int opcion = JOptionPane.showConfirmDialog( this, "¿Desea guardar los cambios antes de cerrar?", "Antes de salir", JOptionPane.INFORMATION_MESSAGE );
        if( opcion == JOptionPane.OK_OPTION )
        {
            guardarComo( );
            super.dispose( );
        }
        else if( opcion == JOptionPane.NO_OPTION )
        {
            super.dispose( );
        }
    }

    /**
     * Actualiza el panel de dibujo y los comandos.
     * @param pG el graphics del panel. pG != null.
     */
    public void actualizar( Graphics2D pG )
    {
        if( tableroLogo != null )
        {
            tableroLogo.ejecutarComandos( pG );
        }
    }

    /**
     * Verifica si el próximo movimiento se encuentra dentro del tablero.
     * @param pValor El valor de desplazamiento. pValor >= 0. 
     * @param pDireccion Dirección del desplazamiento. pDireccion == 0 || pDireccion == 1.
     * @return Retorna true si el movimiento se encuentra dentro del tablero, false en caso contrario.
     */
    public boolean verificarMovimiento( double pValor, int pDireccion )
    {
        return tableroLogo.verificarMovimiento( pValor, pDireccion );
    }

    /**
     * Verifica si el giro encuentra dentro del rango definido.
     * @param pValor El valor del giro. pValor >= 0.
     * @param pDireccion Dirección del giro. pDireccion == 0 || pDireccion == 1.
     * @return True si el giro se encuentra dentro del rango, false en caso contrario.
     */
    public boolean verificarGiro( double pValor, int pDireccion )
    {
        return tableroLogo.verificarGiro( pValor, pDireccion );
    }

    /**
     * Verifica si la escala se encuentra dentro del rango definido.
     * @param pValor El valor de la escala. pValor >= 0.
     * @param pDireccion Direccion de la escala. pDireccion == 0 || pDireccion == 1.
     * @return True si la escala se encuentra dentro del rango, false en caso contrario.
     */
    public boolean verificarEscala( double pValor, int pDireccion )
    {
        return tableroLogo.verificarEscala( pValor, pDireccion );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = tableroLogo.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = tableroLogo.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param pArgs Argumentos para la ejecución de la aplicación. En este caso no son necesarios.
     */
    public static void main( String[] pArgs )
    {
        InterfazCupiLogo interfaz = new InterfazCupiLogo( );
        interfaz.setVisible( true );
    }

}