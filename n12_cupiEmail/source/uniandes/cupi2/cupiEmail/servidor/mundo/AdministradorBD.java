package uniandes.cupi2.cupiEmail.servidor.mundo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Esta es la clase que se encarga de registrar los resultados de los usuarios y sus correos sobre la base de datos. <br>
 * <b>inv:</b><br>
 * config!=null <br>
 */


public class AdministradorBD 
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Constante que representa el estado conectado de un usuario
	 */
	public final static String ESTADO_USUARIO_CONECTADO = "1";

	/**
	 * Constante que representa el estado no conectado de un usuario
	 */
	public final static String ESTADO_USUARIO_DESCONECTADO = "0";

	/**
	 * Constante que representa el estado leido de un correo
	 */
	public final static String ESTADO_CORREO_LEIDO = "1";

	/**
	 *  Constante que representa el estado no leido de un correo
	 */
	public final static String ESTADO_CORREO_NO_LEIDO = "0";

	/**
	 * Constante con el formato de la fecha.
	 */
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";


	/**
	 * Conexi�n a la base de datos
	 */
	private Connection conexion;

	/**
	 * Conjunto de propiedades que contienen la configuraci�n de la aplicaci�n
	 */
	private Properties config;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el administrador y lo deja listo para conectarse a la base de datos
	 * @param propiedades Las propiedades para la configuraci�n del administrador - Debe tener las propiedades "admin.db.path", "admin.db.driver", "admin.db.url" y
	 *        "admin.db.shutdown"
	 */
	public AdministradorBD(Properties propiedades)
	{
		config = propiedades;

		File data = new File (config.getProperty("admin.db.path"));
		System.setProperty("derby.system.home", data.getAbsolutePath());
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Conecta el administrador a la base de datos
	 * @throws SQLException Se lanza esta excepci�n si hay problemas realizando la operaci�n
	 * @throws Exception Se lanza esta excepci�n si hay problemas con los controladores
	 */
	public void conectarABD( ) throws SQLException, Exception
	{
		String driver = config.getProperty( "admin.db.driver" );
		Class.forName( driver ).newInstance( );

		String url = config.getProperty( "admin.db.url" );
		conexion = DriverManager.getConnection( url );
		verificarInvariante();
	}

	/**
	 * Desconecta el administrador de la base de datos y la detiene
	 * @throws SQLException Se lanza esta excepci�n si hay problemas realizando la operaci�n
	 */
	public void desconectarBD( ) throws SQLException
	{ 
		conexion.close( );
		String down = config.getProperty( "admin.db.shutdown" );
		try
		{
			DriverManager.getConnection( down );
		}
		catch( SQLException e )
		{
			// Al bajar la base de datos se produce siempre una excepci�n
		}
		verificarInvariante();
	}

	/**
	 * Crea la tabla necesaria para guardar los usuarios. Si la tabla ya estaba creada entonces no hace nada. <br>
	 * @throws SQLException Se lanza esta excepci�n si hay problemas creando la tabla
	 */
	public void inicializarTablaUsuarios( ) throws SQLException
	{
		Statement s = conexion.createStatement( );

		boolean crearTabla = false;
		try
		{
			// Verificar si ya existe la tabla resultados
			s.executeQuery( "SELECT * FROM usuarios WHERE 1=2" );
		}
		catch( SQLException se )
		{
			// La excepci�n se lanza si la tabla resultados no existe
			crearTabla = true;
		}

		// Se crea una nueva tabla vac�a
		if( crearTabla )
		{
			s.execute( "CREATE TABLE usuarios (login varchar(32), nombre varchar(50), apellido varchar(50), contrasena varchar(32), conectado varchar (1), PRIMARY KEY (login))" );
		}

		s.close( );
		verificarInvariante();
	}

	/**
	 * Crea la tabla necesaria para guardar los correos. Si la tabla ya estaba creada entonces no hace nada. <br>
	 * @throws SQLException Se lanza esta excepci�n si hay problemas creando la tabla
	 */
	public void inicializarTablaCorreos( ) throws SQLException
	{
		Statement s = conexion.createStatement( );

		boolean crearTabla = false;
		try
		{
			// Verificar si ya existe la tabla resultados
			s.executeQuery( "SELECT * FROM correos WHERE 1=2" );
		}
		catch( SQLException se )
		{
			// La excepci�n se lanza si la tabla resultados no existe
			crearTabla = true;
		}

		// Se crea una nueva tabla vac�a
		if( crearTabla )
		{
			String sql = "CREATE TABLE correos (login_remitente varchar(32), login_destinatario varchar(32), fecha_envio varchar(20), Asunto varchar(140), Mensaje varchar (512), Leido varchar(1), PRIMARY KEY (login_destinatario, fecha_envio, Asunto))" ;
			s.execute(sql);
		}

		s.close( );
		verificarInvariante();
	}

	/**
	 * Crea el resgistro de un usuario de Cupi Email cuyo login llega por parametrp
	 * @param login Login del usuario del que se creará un regustro
	 * @return registro Registro del cliente
	 * @throws SQLException Si no se encuentra el usuario con el login dado
	 */
	public ClienteRemoto darUsuarioPorLogin( String login ) throws SQLException
	{
		ClienteRemoto registro = null;

		String sql = "SELECT login, nombre, apellido, contrasena, conectado FROM usuarios WHERE login ='" + login + "'";

		Statement st = conexion.createStatement( );
		ResultSet resultado = st.executeQuery( sql );

		if( resultado.next( ) ) // Se encontr� el usuario
		{
			String nombres = resultado.getString(2);
			String apellidos = resultado.getString(3);
			String contrasena = resultado.getString(4);
			String conectado = resultado.getString(5);

			registro = new ClienteRemoto( login, nombres, apellidos, contrasena, conectado );

			resultado.close( );
			st.close();
			verificarInvariante();
		}
		return registro;
	}

	/**
	 * Devuelve los usuarios de CupiEmail
	 * @return usuarios Lista con los usuarios
	 * @throws SQLException si no existe un usuario
	 */
	public ArrayList<ClienteRemoto> darUsuarios( ) throws SQLException
	{
		ArrayList<ClienteRemoto> usuarios = new ArrayList<ClienteRemoto>( );

		String sql = "SELECT login, nombre, apellido, contrasena, conectado FROM usuarios";

		Statement st = conexion.createStatement( );
		ResultSet resultado = st.executeQuery( sql );

		while( resultado.next( ) )
		{
			String login = resultado.getString( 1 );
			String nombres = resultado.getString(2);
			String apellidos = resultado.getString(3);
			String contrasena = resultado.getString(4);
			String conectado = resultado.getString(5);

			ClienteRemoto actual = new ClienteRemoto( login, nombres, apellidos, contrasena, conectado );
			usuarios.add( actual );
		}

		resultado.close( );
		st.close( );

		return usuarios;
	}
	
	/**
	 * Busca el usuario mas popular
	 * @return popu Usuario mas popuar
	 */
	public ClienteRemoto masPopular()
	{
		int masMensajes = 0;
		ClienteRemoto popu = null;
		try {
			ArrayList<ClienteRemoto> clientes = darUsuarios();
			for (ClienteRemoto c : clientes) {
				int mensajes = consultarCorreos(c.darLogin()).size();
				
				if (mensajes>masMensajes)
				{
					masMensajes = mensajes;
					popu = c;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return popu;
	}
	
	/**
	 * Retorna el usuario con mayor flujo
	 * @return mas flujo
	 */
	public ClienteRemoto darMasFlujo ()
	{
		int masMensajes = 0;
		ClienteRemoto popu = null;
		try {
			ArrayList<ClienteRemoto> clientes = darUsuarios();
			for (ClienteRemoto c : clientes) {
				int mensajes = consultarCorreos(c.darLogin()).size() + consultarCorreosEnviados(c.darLogin()).size();
				
				if (mensajes>masMensajes)
				{
					masMensajes = mensajes;
					popu = c;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return popu;
	}

	/**
	 * Devuelve los usuarios conectados de CupiEmail
	 * @return usuarios Lista con los usuarios
	 * @throws SQLException si no existe un usuario
	 */
	public ArrayList<ClienteRemoto> darUsuariosConectados( ) throws SQLException
	{
		ArrayList<ClienteRemoto> usuariosConectados = new ArrayList<ClienteRemoto>( );

		String sql = "SELECT * FROM usuarios WHERE conectado ='" + ESTADO_USUARIO_CONECTADO + "'";

		Statement st = conexion.createStatement( );
		ResultSet resultado = st.executeQuery( sql );

		while( resultado.next( ) )
		{
			String login = resultado.getString( 1 );
			String nombres = resultado.getString(2);
			String apellidos = resultado.getString(3);
			String contrasena = resultado.getString(4);
			String conectado = resultado.getString(5);

			ClienteRemoto act = new ClienteRemoto( login, nombres, apellidos, contrasena, conectado );
			usuariosConectados.add( act );
		}

		resultado.close( );
		st.close( );

		return usuariosConectados;
	}

	/**
	 * Agrega un usuario a la tabla de usuarios.<br>
	 * @param info Parámetros
	 * @return Resultado agregar usuario
	 * @throws SQLException si no se logra realizar la operación
	 */
	public String agregarUsuario(String info) throws SQLException
	{
		try{
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String login = partes[0];
			String nombres = partes[1];
			String apellidos = partes[2];
			String contrasena = partes [3];
			Statement st = conexion.createStatement( );
			String insert = "INSERT INTO usuarios VALUES ('" + login + "','" + nombres + "','" + apellidos + "','" + contrasena + "','" + ESTADO_USUARIO_DESCONECTADO + "')";
			st.execute( insert );
			cambiarEstado(login, ESTADO_USUARIO_CONECTADO);
			st.close();
			return Atender.CREAR_CUENTA_OK;
		}
		catch (SQLException e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Error creando usuario";
		}
	}

	/**
	 * Cambia el estado de un usuario.<br>
	 * @param login Login del usuario
	 * @param estado Estado nuevo del usuario
	 * @throws SQLException si no se logra realizar la operación
	 */
	public void cambiarEstado(String login, String estado) throws SQLException
	{
		try{
			Statement st = conexion.createStatement( );
			String s = "UPDATE usuarios SET conectado='" + estado + "' WHERE login='" + login + "'";
			st.execute( s );
			st.close();}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Verifica la existencia de un usuario de CupiEmail
	 * @param info Información del usuario
	 * @return usuarios Lista con los usuarios
	 * @throws SQLException si no existe un usuario
	 */
	public String iniciarSesion(String info ) throws SQLException
	{	
		try
		{
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String login = partes[0];
			String contrasena = partes [1];
			String sql = "SELECT contrasena FROM usuarios WHERE login ='" + login + "'";
			Statement st = conexion.createStatement( );
			ResultSet resultado = st.executeQuery( sql );	

			if (resultado.getString(1).equals(contrasena))
			{
				cambiarEstado(login, ESTADO_USUARIO_CONECTADO);
				resultado.close();
				st.close();
				return Atender.INICIAR_SESION_OK;
			}
			else
			{
				resultado.close();
				st.close();
				return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Contraseña incorrecta";
			}
		}
		catch (SQLException e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Error iniciando sesión";
		}

	}

	/**
	 * Verifica la existencia de un usuario de CupiEmail
	 * @param info Información del usuario
	 * @return usuarios Lista con los usuarios
	 * @throws SQLException si no existe un usuario
	 */
	public String cerrarSesion(String info ) throws SQLException
	{	
		try
		{
			cambiarEstado(info,ESTADO_USUARIO_DESCONECTADO);
			return Atender.CERRAR_SESION_OK;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Error al cambiar el estado del usuario";
		}

	}


	/**
	 * Retorna el correo remoto con la fecha y el asunto dados por parámetro
	 * @param fecha Fecha en la que se envió el correo
	 * @param asunto Asunto del correo
	 * @param login log
	 * @return Correo remoto buscado
	 */
	public CorreoRemoto darCorreoPorFechaYAsunto(String login, String fecha, String asunto)
	{
		CorreoRemoto resp = null;
		for (CorreoRemoto correoRemoto : consultarCorreos(login)) {
			if (correoRemoto.darAsunto().equals(asunto) && correoRemoto.darFechaEnvio().equals(fecha))
			{
				resp = correoRemoto;
			}
		}
		return resp;
	}

	/**
	 * Marca como leído un correo.<br>
	 * @param info Información de los parámetros del correo
	 * @param login log
	 * @return Resultado de la operación
	 */
	public String marcarComoLeido(String info, String login)
	{
		try{
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String fechaP = partes[0];
			String asuntoP = partes [1];
			Statement st = conexion.createStatement( );
			String up = "UPDATE correos SET Leido = '" + ESTADO_CORREO_LEIDO + "' WHERE FECHA_ENVIO = '" + fechaP + "' AND Asunto = '" + asuntoP + "' AND login_remitente = '" + login + "'";
			boolean a = st.execute( up );
			System.out.println(a);
			st.close();
			return Atender.MARCAR_COMO_LEIDO_OK;
		}
		catch (SQLException e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Error actualizando el estado del correo";
		}
	}
	
	/**
	 * Elimina un correo.<br>
	 * @param info Información de los parámetros del correo
	 * @param login Login del usuario
	 * @return Resultado de la operación
	 */
	public String eliminarCorreo(String info, String login)
	{
		try{
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String asuntoP = partes[0];
			String fechaP = partes [1];
			Statement st = conexion.createStatement( );
			String del = "DELETE FROM correos WHERE Asunto = '" +asuntoP +"' AND fecha_envio = '" + fechaP + "' AND login_remitente = '" + login + "'";
			st.execute(del);
			st.close();
			return Atender.ELIMINAR_CORREO_OK;
		}
		catch (SQLException e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Error eliminando el correo";
		}
	}

	/**
	 * Envía un correo.<br>
	 * @param loginCliente Login del cliente que envía el correo
	 * @param info Información de los parámetros del correo
	 * @return Resultado de la operación
	 */
	public String enviarCorreo(String loginCliente, String info)
	{
		try{
			Calendar c = Calendar.getInstance();
			SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT);
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String destinatarios = partes[0];
			String[] partesDestinatarios = destinatarios.split(Atender.SEPARADOR_DESTINATARIOS);
			String asunto = partes [1];
			String mensaje = partes[2];

			int num = partesDestinatarios.length;
			Statement st = conexion.createStatement( );
			for (int i=0; i<num; i++)
			{
				String d = s.format(c.getTime());
				String insert = "INSERT INTO correos VALUES ('" + loginCliente + "','" + partesDestinatarios[i] + 
						"','" +  d + "','" + asunto + "','" + mensaje + "','"
						+ ESTADO_CORREO_NO_LEIDO +"')";
				st.execute( insert );
			}
			st.close();
			return Atender.ENVIAR_CORREO_OK;
		}
		catch (SQLException e)
		{
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String destinatarios = partes[0];
			return Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Error registrando el correo para: " +
			destinatarios;
		}
	}

	/**
	 * Consulta los correos de un usuario en la base de datos.<br>
	 * @param loginCliente Login del cliente del que se consultaran los correos
	 * @return Resultado de la operación
	 */
	public ArrayList<CorreoRemoto> consultarCorreos(String loginCliente)
	{
		ArrayList<CorreoRemoto> correos = new ArrayList<CorreoRemoto>();
		CorreoRemoto correo = null;

		try{
			String sql = "SELECT * FROM correos WHERE login_destinatario ='" + loginCliente + "'";
			Statement st = conexion.createStatement( );
			ResultSet resultado = st.executeQuery( sql );
			while( resultado.next() ) // Se encontr� el correo
			{
				String loginRemitente = resultado.getString(1);
				String fechaEnvio = resultado.getString(3);
				String asunto = resultado.getString(4);
				String mensaje = resultado.getString(5);
				String leido = resultado.getString(6);

				correo = new CorreoRemoto( loginCliente, loginRemitente, fechaEnvio, asunto, mensaje, leido );
				correos.add(correo);
			}
			resultado.close( );
			st.close();
			verificarInvariante();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		verificarInvariante();
		return correos;
	}

	/**
	 * Consulta los correos sin leer de un usuario en la base de datos.<br>
	 * @param loginCliente Login del cliente del que se consultaran los correos
	 * @return Resultado de la operación
	 */
	public ArrayList<CorreoRemoto> consultarCorreosNoLeidos(String loginCliente)
	{
		ArrayList<CorreoRemoto> correos = new ArrayList<CorreoRemoto>();
		CorreoRemoto correo = null;

		try{
			String sql = "SELECT * FROM correos WHERE login_destinatario ='" + loginCliente + "' AND Leido = '" + ESTADO_CORREO_NO_LEIDO + "'";
			Statement st = conexion.createStatement( );
			ResultSet resultado = st.executeQuery( sql );
			while( resultado.next() ) // Se encontr� el correo
			{
				String loginRemitente = resultado.getString(1);
				String fechaEnvio = resultado.getString(3);
				String asunto = resultado.getString(4);
				String mensaje = resultado.getString(5);
				String leido = resultado.getString(6);

				correo = new CorreoRemoto( loginCliente, loginRemitente, fechaEnvio, asunto, mensaje, leido );
				correos.add(correo);
			}
			resultado.close( );
			st.close();
			verificarInvariante();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return correos;
	}
	
	/**
	 * Consulta los correos enviados de un usuario en la base de datos.<br>
	 * @param loginCliente Login del cliente del que se consultaran los correos
	 * @return Resultado de la operación
	 */
	public ArrayList<CorreoRemoto> consultarCorreosEnviados(String loginCliente)
	{
		ArrayList<CorreoRemoto> correosLeidos = new ArrayList<CorreoRemoto>();
		CorreoRemoto correo = null;

		try{
			String sql = "SELECT * FROM correos WHERE login_remitente ='" + loginCliente + "'";
			Statement st = conexion.createStatement( );
			ResultSet resultado = st.executeQuery( sql );
			while( resultado.next() ) // Se encontr� el correo
			{
				String loginDestinatario = resultado.getString(2);
				String fechaEnvio = resultado.getString(3);
				String asunto = resultado.getString(4);
				String mensaje = resultado.getString(5);
				String leido = resultado.getString(6);

				correo = new CorreoRemoto( loginDestinatario, loginCliente, fechaEnvio, asunto, mensaje, leido );
				correosLeidos.add(correo);
			}
			resultado.close( );
			st.close();
			verificarInvariante();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return correosLeidos;
	}
	
	
	

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------
	/**
	 * Verifica el invariante de la clase <br>
	 * <b>inv:</b><br>    
	 * config!=null <br>
	 */
	private void verificarInvariante( )
	{                
		assert config != null : "Conjunto de propiedades inv�lidas";                           
	}
}
