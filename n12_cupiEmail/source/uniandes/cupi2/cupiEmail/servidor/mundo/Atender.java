/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Encuentro.java 2110 2010-11-23 15:32:12Z cm.rodriguez155 $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 23/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiEmail.servidor.mundo;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

import uniandes.cupi2.cupiEmail.cliente.mundo.CupiEmailClienteException;


/**
 * Esta clase administra una sesión. Esta clase define la parte fija de los mensajes del protocolo de comunicación.<br>
 * Cada encuentro funciona en un thread diferente, permitiendo que en el mismo servidor se lleven a cabo <br> 
 * varios encuentros a la vez.<br>
 * <b>inv:</b><br>
 * !finSesion => socketJugador1 != null <br>
 * !finSesion => out1 != null <br>
 * !finSesion => in1 != null <br>
 * cliente != null <br>
 */
public class Atender extends Thread
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Separador del comando y los parámetros
	 */
	public static final String SEPARADOR_COMANDO_PARAMETROS = ";;;";

	/**
	 * Separador parámetros
	 */
	public static final String SEPARADOR_PARAMETROS = ":::";

	/**
	 * Separador destinatarios
	 */
	public static final String SEPARADOR_DESTINATARIOS = ",";

	/**
	 * Constante que representa el comando para iniciar sesión
	 */
	public static final String INICIAR_SESION = "INICIAR_SESION";

	/**
	 * Constante que representa el comando para indicar que se ha iniciado sesión
	 */
	public static final String INICIAR_SESION_OK = "INICIAR_SESION_OK";

	/**
	 * Constante que representa el comando para cerrar sesión
	 */
	public static final String CERRAR_SESION = "CERRAR_SESION";

	/**
	 * Constante que representa el comando para indicar que se ha cerrado sesión
	 */
	public static final String CERRAR_SESION_OK = "CERRAR_SESION_OK";

	/**
	 * Constante que representa el comando para crear una cuenta
	 */
	public static final String CREAR_CUENTA = "CREAR_CUENTA";

	/**
	 * Constante que representa el comando para indicar que se ha creado una cuenta
	 */
	public static final String CREAR_CUENTA_OK = "CREAR_CUENTA_OK";

	/**
	 * Constante que representa el comando para marcar un correo como leido
	 */
	public static final String MARCAR_COMO_LEIDO = "MARCAR_COMO_LEIDO";

	/**
	 * Constante que representa el comando para indicar que se ha marcado un correo como leido
	 */
	public static final String MARCAR_COMO_LEIDO_OK = "MARCAR_COMO_LEIDO_OK";

	/**
	 * Constante que representa el comando para consultar los correos
	 */
	public static final String CONSULTAR_CORREOS = "CONSULTAR_CORREOS";

	/**
	 * Constante que representa el comando para indicar que se han consultado los correos
	 */
	public static final String CONSULTAR_CORREOS_OK = "CONSULTAR_CORREOS_OK";

	/**
	 * Constante que representa el comando para enviar un correo
	 */
	public static final String ENVIAR_CORREO = "ENVIAR_CORREO";

	/**
	 * Constante que representa el comando para indicae que se ha enviado un correo
	 */
	public static final String ENVIAR_CORREO_OK = "ENVIAR_CORREO_OK";

	/**
	 * Constante que representa el comando de error
	 */
	public static final String ERROR = "ERROR";

	/**
	 * Constante que representa el comando de cantidad de correos
	 */
	public static final String CANTIDAD_CORREOS = "CANTIDAD_CORREOS";

	/**
	 * Constante que representa el comando de información del correo
	 */
	public static final String INFO_CORREO = "INFO_CORREO";

	/**
	 * Constante que representa el comando de lista correos enviados
	 */
	public static final String LISTA_CORREOS_ENVIADOS = "LISTA_CORREOS_ENVIADOS";

	/**
	 * Constante que representa el comando de correo enviado
	 */
	public static final String CORREO_ENVIADO = "CORREO_ENVIADO";

	/**
	 * Constante que representa el comando de eliminar correo
	 */
	public static final String ELIMINAR_CORREO = "ELIMINAR_CORREO";

	/**
	 * Constante que representa el comando que indica que se pudo eliminar un correo
	 */
	public static final String ELIMINAR_CORREO_OK = "ELIMINAR_CORREO_OK";
	
	/**
	 * Constante que representa que el cliente desea consultar el usuario más popular
	 */
	public static final String USUARIO_MAS_POPULAR = "USUARIO_MAS_POPULAR";
	
	/**
	 * Constante que representa al usuario más popular
	 */
	public static final String MAS_POPULAR = "MAS_POPULAR";



	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * El canal usado para comunicarse con el cliente
	 */
	private Socket socketCliente;

	/**
	 * El flujo de escritura conectado con el cliente
	 */
	private PrintWriter out;

	/**
	 * El flujo de lectura conectado con cliente
	 */
	private BufferedReader in;

	/**
	 * El objeto con la información sobre el cliente: visibilidad protegida, para facilitar las pruebas
	 */
	protected ClienteRemoto cliente;

	/**
	 * Indica que la sesión debe terminar
	 */
	private boolean finSesion;

	/**
	 * Es el administrador que permite registrar el resultado del encuentro sobre la base de datos y consultar la información de los jugadores
	 */
	private AdministradorBD adminBD;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Establece la comunicación con los dos jugadores y el encuentro queda listo para iniciar
	 * @param canal El socket para comunicarse con el cliente - cliente1 != null
	 * @param administrador Es el objeto que permite consultar y registrar resultados sobre la base de datos - administrador != null
	 * @throws IOException Se lanza esta excepción si hay problemas estableciendo la comunicación con los dos jugadores
	 */
	public Atender( Socket canal, AdministradorBD administrador ) throws IOException
	{
		adminBD = administrador;

		out = new PrintWriter( canal.getOutputStream( ), true );
		in = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
		socketCliente = canal;

		finSesion = false;
		verificarInvariante( );
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el socket usado para comunicarse con cliente
	 * @return socketJugador1
	 */
	public Socket darSocketCliente( )
	{
		return socketCliente;
	}

	/**
	 * Retorna el administrador de resultados en el que se registran los resultados del encuentro
	 * @return adminResultados
	 */
	public AdministradorBD darAdministradorResultados( )
	{
		return adminBD;
	}

	/**
	 * Retorna el cliente remoto
	 * @return cliente
	 */
	public ClienteRemoto darClienteRemoto( )
	{
		return cliente;
	}

	/**
	 * Indica si la sesión ya terminó.
	 * @return Retorna true si la sesión terminó. Retorna false en caso contrario.
	 */
	public boolean sesionTerminada( )
	{
		return finSesion;
	}

	/**
	 * Inicia la sesión 
	 * @param info Informacion comando
	 * @return comando iniciar sesión o mensaje de error
	 */
	public String iniciarSesion( String info )
	{
		String resp = null;
		try {
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String login = partes[0];
			cliente = adminBD.darUsuarioPorLogin(login);
			resp = adminBD.iniciarSesion(info);
			finSesion = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp==null?Atender.ERROR +Atender.SEPARADOR_COMANDO_PARAMETROS + "El login o contrasña es inválido":Atender.INICIAR_SESION_OK;
	}

	/**
	 * Cierra la sesión 
	 * @return comando iniciar sesión o mensaje de error
	 */
	public String cerrarSesion( )
	{
		String resp;
		try {
			String info = cliente.darLogin();
			resp = adminBD.cerrarSesion(info);
			cliente = null;
			finSesion=true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp = Atender.ERROR + Atender.SEPARADOR_COMANDO_PARAMETROS + "Errorrr";
		}
		return resp;
	}

	/**
	 * Crea una cuenta
	 * @param info Informacion comando
	 * @return comando iniciar sesión o mensaje de error
	 */
	public String crearCuenta( String info )
	{
		String resp = null;
		try {
			resp = adminBD.agregarUsuario(info);
			String[] partes = info.split(Atender.SEPARADOR_PARAMETROS);
			String login = partes[0];
			cliente = adminBD.darUsuarioPorLogin(login);
			finSesion = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * Marca un correo como leido
	 * @param info Informacion comando
	 * @return comando iniciar sesión o mensaje de error
	 */
	public String marcarComoLeido( String info )
	{
		String resp = adminBD.marcarComoLeido(info, cliente.darLogin());
		return resp;
	}

	/**
	 * Eliimina un correo
	 * @param info Informacion comando
	 * @return comando iniciar sesión o mensaje de error
	 */
	public String eliminarCorreo( String info )
	{
		String resp = adminBD.eliminarCorreo(info, cliente.darLogin());
		return resp;
	}

	/**
	 * Envía un correo
	 * @param info Informacion comando
	 * @return comando iniciar sesión o mensaje de error
	 */
	public String enviarCorreo( String info )
	{
		String resp = adminBD.enviarCorreo(cliente.darLogin(), info);
		return resp;
	}

	/**
	 * Consulta los correos
	 */
	public void consultarCorreo( )
	{
		try{
			ArrayList<CorreoRemoto> correos = adminBD.consultarCorreos(cliente.darLogin());
			String m1 = CANTIDAD_CORREOS + SEPARADOR_COMANDO_PARAMETROS + correos.size();
			out.println(m1);
			for (CorreoRemoto correoRemoto : correos) {
				String m2 = INFO_CORREO + SEPARADOR_COMANDO_PARAMETROS + correoRemoto.darLoginRemitente()+ SEPARADOR_PARAMETROS +
						correoRemoto.darFechaEnvio() + SEPARADOR_PARAMETROS + correoRemoto.darAsunto() + SEPARADOR_PARAMETROS +
						correoRemoto.darMensaje() + SEPARADOR_PARAMETROS + correoRemoto.darEstado();
				out.println(m2);
			}
			out.println(CONSULTAR_CORREOS_OK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Consulta los correos enviados
	 */
	public void consultarCorreoEnviados( )
	{
		try{
			ArrayList<CorreoRemoto> correos = adminBD.consultarCorreosEnviados(cliente.darLogin());
			String m1 = CANTIDAD_CORREOS + SEPARADOR_COMANDO_PARAMETROS + correos.size();
			out.println(m1);
			for (CorreoRemoto correoRemoto : correos) {
				String m2 = CORREO_ENVIADO + SEPARADOR_COMANDO_PARAMETROS + correoRemoto.darAsunto() + SEPARADOR_PARAMETROS + 
						correoRemoto.darLoginDestinatario() + SEPARADOR_PARAMETROS + correoRemoto.darFechaEnvio() + SEPARADOR_PARAMETROS + 
						correoRemoto.darMensaje();
				out.println(m2);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Manda el mensaje con el usuario mas popular
	 */
	public void masPopular()
	{
		ClienteRemoto c = adminBD.masPopular();
		out.println(MAS_POPULAR+SEPARADOR_COMANDO_PARAMETROS+c.darLogin()+SEPARADOR_PARAMETROS+adminBD.consultarCorreos(c.darLogin()).size());
	}

	/**
	 * Inicia el encuentro y realiza todas las acciones necesarias mientras que este dure.<br>
	 * El ciclo de vida de un encuentro tiene tres partes:<br>
	 * 1. Iniciar el encuentro <br>
	 * 2. Realizar el encuentro (permitir la comunicación entre los clientes)<br>
	 * 3. Terminar el encuentro y enviar la información sobre el ganador
	 */
	public void run( )
	{
		String comando;
		try {
			while (!finSesion){
				comando = in.readLine();
				if (comando!=null){
					String[] partes = comando.split(SEPARADOR_COMANDO_PARAMETROS);
					switch (partes[0])
					{
					case INICIAR_SESION:
						out.println(iniciarSesion(partes[1]));
						break;
					case CREAR_CUENTA:
						out.println(crearCuenta(partes[1]));
						break;
					case CERRAR_SESION:
						out.println(cerrarSesion());
						break;
					case MARCAR_COMO_LEIDO:
						out.println(marcarComoLeido(partes[1]));
						break;
					case ENVIAR_CORREO:
						out.println(enviarCorreo(partes[1]));
						break;
					case CONSULTAR_CORREOS:
						consultarCorreo();
						break;
					case LISTA_CORREOS_ENVIADOS:
						consultarCorreoEnviados();
						break;
					case ELIMINAR_CORREO:
						out.println(eliminarCorreo(partes[1]));
						break;
					case USUARIO_MAS_POPULAR:
						masPopular();
						break;
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			out.close();
			try {
				in.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			e.printStackTrace();
			finSesion = true;
			try {
				socketCliente.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase
	 * <b>inv:</b><br>
	 * !finJuego => socketJugador1 != null <br>
	 * !finJuego => out1 != null <br>
	 * !finJuego => in1 != null <br>
	 * !finJuego => socketJugador2 != null <br>
	 * !finJuego => out2 != null <br>
	 * !finJuego => in2 != null <br>
	 * cliente != null <br>
	 */
	private void verificarInvariante( )
	{
		if( !finSesion )
		{
			assert socketCliente != null : "Canal inválido";
			assert out != null : "Flujo inválido";
			assert in != null : "Flujo inválido";
		}

		assert cliente != null : "Cliente nulo";
	}
}
