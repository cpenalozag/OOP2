/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiViajes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiViajes.excepciones.ClienteTieneReservaException;
import uniandes.cupi2.cupiViajes.excepciones.PersistenciaException;
import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.ComparadorReservaAdultosCliente;
import uniandes.cupi2.cupiViajes.mundo.ComparadorReservaNombreCliente;
import uniandes.cupi2.cupiViajes.mundo.CriterioOrdenReserva;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.Estado;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;
import uniandes.cupi2.ordenador.AlgoritmoOrdenamiento;
import uniandes.cupi2.ordenador.Ordenador;
import uniandes.cupi2.persistencia.ManejadorArchivosTexto;
import uniandes.cupi2.persistencia.Serializador;

/**
 * Ventana principal de la aplicaci�n.
 */
public class InterfazCupiViajes extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la ubicaci�n del archivo con los datos de los viajes.
     */
    private final static String ARCHIVO_SERIALIZACION = "./data/cupiViajes.data";

    /**
     * Constante que representa la ubicaci�n del archivo con los datos de los hoteles.
     */
    public final static String ARCHIVO_HOTELES = "./data/hoteles.csv";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private CupiViajes cupiViajes;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen del encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con la informaci�n del hotel de la reserva.
     */
    private PanelHotel panelHotel;

    /**
     * Panel con la informaci�n de la reserva.
     */
    private PanelReserva panelReserva;

    /**
     * Panel con las opciones de b�squeda y extensi�n.
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel con la lista de reservas hechas.
     */
    private PanelListaReservas panelListaReservas;

    private Ordenador<ReservaViaje> ordenadorReservas;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal.<br>
     * <b> post: </b> Construye la ventana principal de la aplicaci�n.
     */
    public InterfazCupiViajes( )
    {
        cupiViajes = new CupiViajes( );

        setLayout( new BorderLayout( ) );
        setTitle( "CupiViajes" );
        setSize( new Dimension( 750, 650 ) );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setLocationRelativeTo( null );
        setResizable( true );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        JPanel panelAux = new JPanel( );
        panelAux.setLayout( new BorderLayout( ) );
        panelAux.setBorder( new TitledBorder( "Informaci�n de la reserva" ) );

        panelReserva = new PanelReserva(this );
        panelAux.add( panelReserva, BorderLayout.CENTER );

        panelHotel = new PanelHotel( this );
        panelAux.add( panelHotel, BorderLayout.SOUTH );


        add( panelAux, BorderLayout.CENTER );

        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.SOUTH );

        panelListaReservas = new PanelListaReservas( this );
        add( panelListaReservas, BorderLayout.WEST );

        cargar( );
        ordenadorReservas = new Ordenador<ReservaViaje>( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Carga la informaci�n de los viajes
     */
    private void cargar( ) 
    {
        File f = new File( ARCHIVO_SERIALIZACION );
        if(f.exists( ))
        {
            try
            {
                cupiViajes = Serializador.cargar( ARCHIVO_SERIALIZACION, cupiViajes );
                actualizarListaReservas( );

            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else
        {
            try
            {
                ManejadorArchivosTexto.importarCSV( ARCHIVO_HOTELES, cupiViajes );
            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    public void dispose()
    {
        File f = new File( ARCHIVO_SERIALIZACION );

        try
        {
            if (!f.exists( ))
            {
                try
                {
                    f.createNewFile( );
                }
                catch ( IOException e )
                {
                    e.printStackTrace( );
                    throw new PersistenciaException( e.getMessage( ), ARCHIVO_SERIALIZACION );
                }
            }

            if (f.exists( ))
            {
                Serializador s = new Serializador( );
                s.guardar( ARCHIVO_SERIALIZACION, cupiViajes );
                JOptionPane.showMessageDialog( this, "El archivo se guardó correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE );
                super.dispose( );
            }
            super.dispose( );
        }
        catch( PersistenciaException e )
        {
            int seleccion = JOptionPane.showConfirmDialog( this, e.getMessage( ) + "\n�Desea cerrar de todos modos?", "Error", JOptionPane.YES_NO_OPTION );
            if(seleccion == JOptionPane.YES_OPTION)
            {
                super.dispose( );

            }
        }
    }

    /**
     * Retorna la lista de hoteles disponibles en el sistema.
     * @return Lista de hoteles disponibles.
     */
    public ArrayList<Hotel> darHoteles( )
    {
        return cupiViajes.darHoteles( );
    }

    /**
     * Actualiza la lista de reservas.
     */
    public void actualizarListaReservas( )
    {
        panelListaReservas.actualizarLista( cupiViajes.darReservas( ) );
    }

    /**
     * Actualiza la informaci�n de la reserva y del hotel dados por par�metro.
     * @param pReserva Reserva que se va a visualizar. pReserva != null.
     * @param pHotel Hotel que se va a visualizar. pHotel != null.
     */
    public void actualizarInformacion( ReservaViaje pReserva, Hotel pHotel )
    {
        panelReserva.actualizarInformacion( pReserva );
        panelHotel.actualizar( pHotel );
    }

    /**
     * Retorna la lista de hoteles que se encuentran en la ciudad dada por par�metro.
     * @param pCiudad Ciudad de los hoteles buscados. pCiudad != null && pCiudad != "".
     * @return Lista de hoteles que se encuentran en la ciudad dada.
     */
    public ArrayList buscarHotelesPorCiudad( String pCiudad )
    {
        return cupiViajes.buscarHotelesCiudad( pCiudad );
    }

    /**
     * Busca la reserva seg�n el criterio dado por par�metro. <br>
     * <b> post: </b> Se actualiza la informaci�n de los paneles con la informaci�n de la reserva. 
     * @param pCriterio Criterio por el que se va a buscar la reserva. pCriterio != null && pCriterio pertenece a { PanelOpciones.CLIENTE, PanelOpciones.CIUDAD, PanelOpciones.MAYOR_PERSONAS, PanelOpciones.MENOR_PERSONAS }.
     */
    public void buscarReserva( String pCriterio )
    {
        if( pCriterio.equals( PanelOpciones.CLIENTE ) )
        {
            ordenadorReservas.ordenar( AlgoritmoOrdenamiento.BURBUJA, cupiViajes.darReservas( ), true, new ComparadorReservaNombreCliente( ) );
            String cliente = JOptionPane.showInputDialog( this, "Por favor indique el nombre del cliente: " );
            if( cliente != null && !cliente.equals( "" ))
            {
                ReservaViaje reserva = cupiViajes.buscarReservaPorClienteBinario( cliente );
                if( reserva != null )
                {
                    panelReserva.actualizarInformacion( reserva );
                    panelHotel.actualizar( reserva.darHotel( ) );
                    panelListaReservas.seleccionarReserva( reserva );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se encontr� una reserva con el cliente dado.", "Buscar reserva por cliente", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El nombre del cliente no puede ser vac�o.", "Buscar reserva por cliente", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( pCriterio.equals( PanelOpciones.CIUDAD ) )
        {
            String ciudad = JOptionPane.showInputDialog( this, "Por favor indique el nombre de la ciudad: " );
            if( ciudad != null && !ciudad.equals( "" ) )
            {
                ReservaViaje reserva = cupiViajes.buscarReservaPorCiudad( ciudad );
                if( reserva != null )
                {
                    panelReserva.actualizarInformacion( reserva );
                    panelHotel.actualizar( reserva.darHotel( ) );
                    panelListaReservas.seleccionarReserva( reserva );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se encontr� una reserva con la ciudad dada.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El nombre de la ciudad no puede ser vac�a.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( pCriterio.equals( PanelOpciones.AEROLINEA ) )
        {
            Aerolinea[] opciones = Aerolinea.values( );
            JComboBox comboOpciones = new JComboBox( opciones );
            JOptionPane.showMessageDialog( null, comboOpciones, "�Por qu� aerol�nea desea buscar la reserva?", JOptionPane.QUESTION_MESSAGE );
            Aerolinea aerolinea = ( Aerolinea )comboOpciones.getSelectedItem( );
            if( aerolinea != null )
            {
                ArrayList<ReservaViaje> listaReservas = cupiViajes.buscarReservasAerolinea( aerolinea );
                panelListaReservas.actualizarLista( listaReservas );
            }
        }
        else if( pCriterio.equals( PanelOpciones.MAYOR_PERSONAS ) )
        {
            ReservaViaje reserva = cupiViajes.buscarReservaMasPersonas( );
            if( reserva != null )
            {
                panelReserva.actualizarInformacion( reserva );
                panelHotel.actualizar( reserva.darHotel( ) );
                panelListaReservas.seleccionarReserva( reserva );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay reservas en el sistema.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( pCriterio.equals( PanelOpciones.MENOR_PERSONAS ) )
        {
            ReservaViaje reserva = cupiViajes.buscarReservaMenosPersonas( );
            if( reserva != null )
            {
                panelReserva.actualizarInformacion( reserva );
                panelHotel.actualizar( reserva.darHotel( ) );
                panelListaReservas.seleccionarReserva( reserva );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay reservas en el sistema.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Guarda una reserva en el sistema con los valores dados por par�metro.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pFechaLlegada fecha de llegada al hotel que se va a reservar. pDiaLlegada !=null
     * @param pCliente Cliente responsable de la reserva. pCliente != null && pCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar al hotel que se va a reservar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de ni�os que van a viajar al hotel que se va a reservar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estad�a en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerol�nea que se va a reservar. pAerolinea != "" && pAerolinea pertenece a {ReservaViaje.AVIANCA, ReservaViaje.LAN, ReservaViaje.VIVA_COLOMBIA,
     *        ReservaViaje.SATENA, ReservaViaje.JETBLUE, ReservaViaje.IBERIA, ReservaViaje.AIR_FRANCE}.
     */
    public void guardarReserva( Hotel pHotel, Date pFechaLlegada, String pCedula, String pCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea )
    {
        try
        {
            cupiViajes.agregarReserva( pHotel, pFechaLlegada, pCedula, pCliente, pCantidadAdultos, pCantidadNinios, pNochesEstadia, pAerolinea );
            actualizarListaReservas( );
        }
        catch( ClienteTieneReservaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( )+" "+e.darCedulaCliente( ), "Error", JOptionPane.ERROR_MESSAGE );
        }


    }

    /**
     * Crea y visualiza el di�logo para guardar una reserva.
     */
    public void iniciarDialogoGuardarReserva( )
    {
        DialogoAgregarReserva dialogo = new DialogoAgregarReserva( this );
        dialogo.setVisible( true );
    }

    /**
     * Crea y visualiza el di�logo para seleccionar el hotel de la reserva.
     * @param pDialogoReserva Referencia al di�logo de reserva. pDialogoReserva != null.
     */
    public void iniciarDialogoSeleccionarHotel( DialogoAgregarReserva pDialogoReserva )
    {
        DialogoSeleccionHotel dialogo = new DialogoSeleccionHotel( this, pDialogoReserva );
        dialogo.inicializarCiudades( cupiViajes.darHoteles( ) );
        dialogo.setVisible( true );
    }

    /**
     * Ordena la lista de reservas seg�n el criterio dado por par�metro. <br>
     * <b> post: </b> La lista de reservas qued� ordenada seg�n el criterio.
     */
    public void ordenarLista( )
    {
        CriterioOrdenReserva[] opciones = CriterioOrdenReserva.values( );
        JComboBox comboOpciones = new JComboBox( opciones );
        JOptionPane.showMessageDialog( null, comboOpciones, "�Seg�n que criterio ordenar la lista?", JOptionPane.QUESTION_MESSAGE );
        CriterioOrdenReserva opcion = ( CriterioOrdenReserva )comboOpciones.getSelectedItem( );

        AlgoritmoOrdenamiento[] algoritmos = AlgoritmoOrdenamiento.values( );
        JComboBox comboAlgoritmos = new JComboBox( algoritmos );
        JOptionPane.showMessageDialog( null, comboAlgoritmos, "�C�mo desea ordenar la lista?", JOptionPane.QUESTION_MESSAGE );
        AlgoritmoOrdenamiento algoritmo = ( AlgoritmoOrdenamiento )comboAlgoritmos.getSelectedItem( );


        if( opcion != null && algoritmo !=null)
        {
            ordenadorReservas.ordenar( algoritmo, cupiViajes.darReservas( ), opcion.esAscendnete( ), opcion.darComparador( ) );
            actualizarListaReservas( );
        }
    }

    /**
     * Cambia ele stado de una reserva
     * @param cedula la c�dula asociada a la reserva. cedula != null && cedual != ""
     * @param estado el nuevo estado de la reserva. estado !=null
     */
    public void cambiarEstadoReserva( String cedula, Estado estado )
    {
        cupiViajes.cambiarEstadoReserva(cedula, estado);
        JOptionPane.showMessageDialog( this, "Se ha cambiado el estado de la reserva", "Estado", JOptionPane.INFORMATION_MESSAGE );

    }

    /**
     * Genera el reporte de reservas
     */
    public void generarReporte( )
    {
        JFileChooser chooser = new JFileChooser( "./data" );
        chooser.setDialogTitle( "Generar reporte" );
        int returnVal = chooser.showSaveDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            File arch = chooser.getSelectedFile( );
            try
            {
                ManejadorArchivosTexto.generarReporte( arch.getAbsolutePath( ), cupiViajes );
                JOptionPane.showMessageDialog( this, "Reporte generado.", "Generar reporte", JOptionPane.INFORMATION_MESSAGE );
            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Generar reporte", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupiViajes.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupiViajes.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param pArgs Argumentos para la ejecuci�n de la aplicaci�n. pArgs != null.
     */
    public static void main( String[] pArgs )
    {
        InterfazCupiViajes interfaz = new InterfazCupiViajes( );
        interfaz.setVisible( true );

    }



}
