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
package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Clase que representa a la tortuga. <br>
 * <b>inv:</b> <br>
 * xActual >= 0 <br>
 * yActual >= 0 <br>
 * 0 <= orientacion <= 360 <br>
 * 1 <= escala <= 3.
 */
public class Tortuga
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Coordenada X actual.
	 */
	private int xActual;

	/**
	 * Coordenada Y actual.
	 */
	private int yActual;

	/**
	 * Coordenada X inicial, corresponde al centro del tablero.
	 */
	private int xInicial;

	/**
	 * Coordenada Y inicial, corresponde al centro del tablero.
	 */
	private int yInicial;

	/**
	 * Orientación en grados.
	 */
	private double orientacion;

	/**
	 * Escala en porcentaje.
	 */
	private double escala;

	/**
	 * Estado que determina si está pintando la trayectoria o no.
	 */
	private boolean pintando;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea una nueva tortuga.</br>
	 * <b>post:</b>Las coordenadas y la orientación se inicializaron en 0.<br>
	 * El estado de pintando la trayectoria se inicializó en true.<br>
	 * El color de la trayectoria de la tortuga fue inicializado en negro.<br>
	 * .
	 * @param pXInicial Coordenada en X inicial. pXInicial > 0.
	 * @param pYInicial Coordenada en Y inicial. pYInicial > 0.
	 * @param pOrientacion Orientación inicial en grado. 0<= pOrientacion && pOrientacion <= 360.
	 * @param pEscala Escala inicial. pEscala >= 1 && pEscala <= 3.
	 */
	public Tortuga( int pXInicial, int pYInicial, double pOrientacion, double pEscala )
	{
		xInicial = pXInicial;
		yInicial = pYInicial;
		xActual = pXInicial;
		yActual = pYInicial;
		orientacion = pOrientacion;
		escala = pEscala;
		pintando = true;
		verificarInvariante( );
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna la coordenada X actual de la tortuga.
	 * @return Coordenada X actual de la tortuga.
	 */
	public int darXActual( )
	{
		return xActual;
	}

	/**
	 * Retorna la coordenada Y actual de la tortuga.
	 * @return Coordenada Y actual de la tortuga.
	 */
	public int darYActual( )
	{
		return yActual;
	}

	/**
	 * Retorna la coordenada X inicial de la tortuga
	 * @return Coordenada X incial de la tortuga.
	 */
	public int darXInicial( )
	{
		return xInicial;
	}

	/**
	 * Retorna la coordenada Y inicial de la tortuga
	 * @return Coordenada Y incial de la tortuga.
	 */
	public int darYInicial( )
	{
		return yInicial;
	}

	/**
	 * Retorna la orientación de la tortuga.
	 * @return Orientación de la tortuga.
	 */
	public double darOrientacion( )
	{
		return orientacion;
	}

	/**
	 * Retorna la escala de la tortuga.
	 * @return Escala de la tortuga.
	 */
	public double darEscala( )
	{
		return escala;
	}

	/**
	 * Retorna el estado que determina si la tortuga está pintando su trayectoria o no.
	 * @return True si está pintando su trayectoria, false de lo contrario.
	 */
	public boolean estaPintando( )
	{
		return pintando;
	}

	/**
	 * Modifica el valor de la coordenada X de la tortuga.<br>
	 * <b>post:</b> Actualizó la posición en X de la tortuga.
	 * @param pXActual Nuevo valor de la coordenada X de la tortuga. pXActual >= 0.
	 */
	public void modificarXActual( int pXActual )
	{
		xActual = pXActual;
		verificarInvariante( );
	}

	/**
	 * Modifica el valor de la coordenada Y de la tortuga.<br>
	 * <b>post:</b> Se ha actualizado la posición en Y de la tortuga.
	 * @param pYActual Nuevo valor de la coordenada Y de la tortuga. pYActual >= 0.
	 */
	public void modificarYActual( int pYActual )
	{
		yActual = pYActual;
		verificarInvariante( );
	}

	/**
	 * Modifica la orientación de la tortuga.</br>
	 * <b>post:</b> Se ha actualizado la orientación de la tortuga.
	 * @param pOrientacion Nuevo valor de la orientación en grados. pOrientacion >= 0 && pOrientacion < 360.
	 */
	public void modificarOrientacion( double pOrientacion )
	{
		orientacion = pOrientacion;
		verificarInvariante( );
	}

	/**
	 * Modifica la escala de la tortuga.</br>
	 * <b>post:</b> Se ha actualizado la escala de la tortuga.
	 * @param pEscala Nuevo valor de la escala. pEscala >= 1 && pEscala <= 3.
	 */
	public void modificarEscala( double pEscala )
	{
		escala = pEscala;
		verificarInvariante( );
	}

	/**
	 * Modifica el valor de la activación de trayectoria de la tortuga. <br>
	 * <b>post:</b> Se ha actualizado la activación del dibujo de la trayectoria de la tortuga.
	 * @param pPintando Nuevo esta que determina si está pintando la trayectoria o no.
	 */
	public void modificarPintando( boolean pPintando )
	{
		pintando = pPintando;
		verificarInvariante( );
	}

	/**
	 * Modifica las coordenadas en X y Y de la tortuga y le asigna el valor inicial, que corresponde al centro del tablero.<br>
	 * <b> post: </b> La coordenada xActual quedó en xInicial. La coordenada yActual quedó en yInicial.
	 */
	public void centrarTortuga( )
	{
		xActual = xInicial;
		yActual = yInicial;
	}

	/**
	 * Asigna a cada uno de los atributos los valores iniciales.<br>
	 * <b> post: </b> La coordenada xActual quedó en xInicial. <br>
	 * La coordenada yActual quedó en yInicial.<br>
	 * La orientación quedó en 0.<br>
	 * La escala quedó en 1.<br>
	 * El estado de pintando quedó en true.
	 */
	public void reiniciarTortuga( )
	{
		xActual = xInicial;
		yActual = yInicial;
		escala = 1;
		orientacion = 0;
		pintando=true;
	}

	/**
	 * Pinta la tortuga en el tablero que llega por parámetro.<br>
	 * <b>post:</b> Se ha dibujado la en la posición, orientación y escala definidas.
	 * @param pG Superficie donde se debe pintar. pG != null.
	 */
	public void pintar( Graphics2D pG )
	{

		// Diámetro caparazón
		int d1 = ( int ) ( 40 * escala );

		// Diámetro cabeza
		int d2 = ( int ) ( 20 * escala );

		// Diámetro patas
		int d3 = ( int ) ( 10 * escala );

		// Conversión de los ángulos de grados a radianes.

		double a1 = Math.PI * orientacion / 180;
		double a2 = Math.PI * 45 / 180;
		double a3 = Math.PI * 135 / 180;
		double a4 = Math.PI * 225 / 180;
		double a5 = Math.PI * 315 / 180;

		// Cálculo de las coordenadas en X y Y (esquina superior izquierda) de cada una de las partes de la tortuga.

		int patax1 = xActual + ( int ) ( d1 * Math.cos( a2 + a1 ) / 2 - d3 / 2 );
		int patay1 = yActual + ( int ) ( d1 * Math.sin( a2 + a1 ) / 2 - d3 / 2 );

		int patax2 = xActual + ( int ) ( d1 * Math.cos( a3 + a1 ) / 2 - d3 / 2 );
		int patay2 = yActual + ( int ) ( d1 * Math.sin( a3 + a1 ) / 2 - d3 / 2 );

		int patax3 = xActual + ( int ) ( d1 * Math.cos( a4 + a1 ) / 2 - d3 / 2 );
		int patay3 = yActual + ( int ) ( d1 * Math.sin( a4 + a1 ) / 2 - d3 / 2 );

		int patax4 = xActual + ( int ) ( d1 * Math.cos( a5 + a1 ) / 2 - d3 / 2 );
		int patay4 = yActual + ( int ) ( d1 * Math.sin( a5 + a1 ) / 2 - d3 / 2 );

		int cabezax = xActual + ( int ) ( d1 * Math.cos( a1 ) / 2 - d2 / 2 );
		int cabezay = yActual + ( int ) ( d1 * Math.sin( a1 ) / 2 - d2 / 2 );

		int caparazonx = xActual - d1/2;
		int caparazony = yActual - d1/2;

		// Dibujo de las extremidades
		pG.setColor( new Color( 220, 253, 172 ) );
		pG.fillOval( patax1, patay1, d3, d3 );
		pG.fillOval( patax2, patay2, d3, d3 );
		pG.fillOval(patax3, patay3, d3, d3);
		pG.fillOval(patax4, patay4, d3, d3);

		// Dibujo contorno de las extremidades
		pG.setColor( new Color( 175, 201, 124 ) );
		pG.drawOval( patax1, patay1, d3, d3 );
		pG.drawOval( patax2, patay2, d3, d3 );
		pG.drawOval(patax3, patay3, d3, d3);
		pG.drawOval(patax4, patay4, d3, d3);

		// Dibujo de la cabeza
		pG.setColor( new Color( 220, 253, 172 ) );
		pG.fillOval( cabezax, cabezay, d2, d2 );

		// Dibujo contorno de la cabeza
		pG.setColor( new Color( 175, 201, 124 ) );
		pG.drawOval( cabezax, cabezay, d2, d2 );

		// Dibujo del caparazón
		pG.setColor( new Color( 142 ,179 ,65 ) );
		pG.drawOval(caparazonx, caparazony, d1, d1);
		pG.fillOval(caparazonx, caparazony, d1, d1);
	}

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase. <br>
	 * xActual >= 0. <br>
	 * yActual >= 0. <br>
	 * 0 <= orientación <= 360. <br>
	 * 1 <= escala <= 3.
	 */
	private void verificarInvariante( )
	{
		assert xActual >= 0 : "La tortuga esta fuera del escenario en el eje X";
		assert yActual >= 0 : "La tortuga esta fuera del escenario en el eje Y";
		assert 0 <= orientacion && orientacion <= 360 : "La tortuga no tiene una orientación válida: " + orientacion;
		assert escala >= 1 && escala <= 3 : "La tortuga debe tener una escala mínima de 1 y máximo de 3.";
	}
}
