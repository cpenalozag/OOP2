package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Graphics2D;

public class ComandoCentrar extends ComandoSimple
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante con el nombre del comando.
	 */
	public static final String COMANDO = "centrar";

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Construye el comando para centrar la tortuga en el tablero.</br>
	 * <b>post:</b>El nombre del comando se ha asignado.
	 */
	public ComandoCentrar( )
	{
		nombre = COMANDO;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Ejecuta el comando definido en la tortuga que viene por parámetro.<br>
	 * <b>post:</b>La tortuga está en el centro con orientación.
	 * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
	 * @param pG Tablero de edición. pG != null.
	 */
	public void ejecutar(Tortuga pTortuga, Graphics2D pG) 
	{
		pTortuga.centrarTortuga();
	}

}
