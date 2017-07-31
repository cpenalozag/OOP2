package uniandes.cupi2.cupiEmail.servidor.mundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/**
 * El Servidor CupiEmail es el que se encarga de recibir conexiones de los clientes y organizar la información de los correos. <br>
 * <b>inv:</b><br>
 * receptor!= null <br>
 * config!=null <br>
 * adminResultados!=null <br>
 * encuentros!=null <br>
 */
public class CupiEmail 
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es el punto por el cual los clientes solicitan conexiones
	 */
	private ServerSocket receptor;

	/**
	 * Es el conjunto de propiedades que contienen la configuraci�n de la aplicaci�n
	 */
	private Properties config;

	/**
	 * Es el administrador de la base de datos
	 */
	private AdministradorBD adminBD;

	/**
	 * Este es el canal utilizado para establecer una comunicaci�n con un cliente. <br>
	 * Si no hay clientes en espera, este canal debe ser null.
	 */
	private Socket socketUsuario;

	/**
	 * Colección con todos los usuarios de cupi email
	 */
	private Collection<Atender> usuarios;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicia el servidor y deja listo el administrador de resultados
	 * @param archivo El archivo de propiedades que tiene la configuraci�n del servidor - archivo != null
	 * @throws Exception Se lanza esta excepci�n si hay problemas con el archivo de propiedades o hay problemas en la conexi�n a la base de datos
	 * @throws SQLException Se lanza esta excepci�n si hay problemas conectando el almac�n a la base de datos.
	 */
	public CupiEmail(String archivo ) throws SQLException, Exception
	{
		usuarios = new ArrayList<>( );

		cargarConfiguracion( archivo );

		adminBD = new AdministradorBD( config );
		adminBD.conectarABD( );
		adminBD.inicializarTablaUsuarios();
		adminBD.inicializarTablaCorreos();
		//verificarInvariante(); 
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Carga la configuraci�n a partir de un archivo de propiedades
	 * @param archivo El archivo de propiedades que contiene la configuraci�n que requiere el servidor - archivo != null y el archivo debe contener la propiedad
	 *        "servidor.puerto" y las propiedades que requiere el administrador de resultados
	 * @throws Exception Se lanza esta excepci�n si hay problemas cargando el archivo de propiedades
	 */
	private void cargarConfiguracion( String archivo ) throws Exception
	{
		FileInputStream fis = new FileInputStream( archivo );
		config = new Properties( );
		config.load( fis );
		fis.close( );
	}

	/**
	 * Retorna al administrador de resultados
	 * @return adminResultados;
	 */
	public AdministradorBD darAdministradorUsuarios( )
	{
		return adminBD;
	}
	
	/**
	 * Crea una colección con todos los correos de un cliente
	 * @param cliente Cliente del cual se revisaran los correos
	 * @return correos del clinte
	 */
	public Collection<CorreoRemoto> darListaActualizadaCorreos(ClienteRemoto cliente)
	{
		String login = cliente.darLogin();
		ArrayList<CorreoRemoto> correos =  adminBD.consultarCorreos(login);
		return correos;
	}
	
	/**
	 * Crea una colección con todos los correos de un cliente
	 * @param cliente Cliente del cual se revisaran los correos
	 * @return correos del clinte
	 */
	public int darNumeroCorreos(ClienteRemoto cliente)
	{
		String login = cliente.darLogin();
		ArrayList<CorreoRemoto> correos =  adminBD.consultarCorreos(login);
		int cantCorreos = correos.size();
		return cantCorreos;
	}
	
	/**
	 * Retorna el cliente con mayor flujo
	 * @return cliente mas flujo
	 */
	public String darMayorFlujo ()
	{
		try{
		String nom = adminBD.darMasFlujo().darLogin();
		return nom;
		}
		catch (Exception e)
		{
			return "ningun usuario tiene correos.";
		}
	}
	
	/**
	 * Busca cual es el cliente más popular
	 * @return popu Cliente más popular de cupiEmail
	 */
	public ClienteRemoto masFlujo()
	{
		int masFlujo = 0;
		ClienteRemoto activo = null;
		for (Atender a : usuarios) {
			int mensajesActual = darAdministradorUsuarios().consultarCorreos(a.darClienteRemoto().darLogin()).size() + 
					darAdministradorUsuarios().consultarCorreosEnviados(a.darClienteRemoto().darLogin()).size();
			if (mensajesActual > masFlujo)
			{
				activo = a.darClienteRemoto();
				masFlujo = mensajesActual;
			}
		}
		System.out.println(masFlujo);
		System.out.println(activo);
		
		return activo;
	}
	
	/**
	 * Crea una colección con todos los correos sin leer de un cliente
	 * @param cliente Cliente del cual se revisaran los correos
	 * @return correos del clinte
	 */
	public Collection darListaActualizadaCorreosNoLeidos(ClienteRemoto cliente)
	{
		String login = cliente.darLogin();
		Collection correos = new ArrayList<CorreoRemoto>();
		correos = adminBD.consultarCorreosNoLeidos(login);
		return correos;
	}

	/**
	 * Este m�todo se encarga de recibir todas las conexiones entrantes y crear los encuentros cuando fuera necesario.
	 */
	public void recibirConexiones( )
	{
		String aux = config.getProperty( "servidor.puerto" );
		int puerto = Integer.parseInt( aux );
		try
		{
			receptor = new ServerSocket( puerto );

			while( true )
			{
				// Esperar una nueva conexi�n
				Socket socketNuevoCliente = receptor.accept( );

				// Intentar iniciar un encuentro con el nuevo cliente
				crearAtender( socketNuevoCliente );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
		finally
		{
			try
			{
				receptor.close( );
			}
			catch( IOException e )
			{
				e.printStackTrace( );
			}
		}
	}

	/**
	 * Intenta crear e iniciar un nuevo atender con el usuario que se acaba de conectar. <br>
	 * @param socketNuevoUsuario El canal que permite la comunicaci�n con el nuevo usuario - socket != null
	 * @throws IOException Se lanza esta excepci�n si se presentan problemas de comunicaci�n
	 */
	synchronized private void crearAtender( Socket socketNuevoUsuario ) throws IOException
	{
		Atender at = new Atender(socketNuevoUsuario, adminBD);
		at.start();
		verificarInvariante();
	}
	
	/**
     * Agrega el atender a la lista de encuentros en curso y lo inicia
     * @param nuevoAtender Un encuentro que no ha sido inicializado ni agregado a la lista de encuentros - nuevoEncuentro != null
     */
    protected void iniciarAtender( Atender nuevoAtender )
    {
        usuarios.add( nuevoAtender );
        nuevoAtender.start( );
        
    }
    
    

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase <br>
	 * <b>inv:</b><br>
	 * receptor!= null <br>
	 * config!=null <br>
	 * adminBD!=null <br>
	 * encuentros!=null <br>
	 */
	private void verificarInvariante( )
	{        
		assert receptor != null : "Canal inv�lido";
		assert config != null : "Conjunto de propiedades inv�lidas";
		assert adminBD != null : "El administrador de la base de datos no deber�a ser null";
		assert usuarios != null : "La lista de encuentros no deber�a ser null";            
	}

	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la extensi�n 1
	 * @return respuesta1
	 */
	public String metodo1( )
	{
		return "El usuario con mayor flujo de correos es: " + darMayorFlujo();
	}

	/**
	 * M�todo para la extensi�n2
	 * @return respuesta2
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}

}
