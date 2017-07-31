package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Graphics2D;import javax.print.attribute.standard.PDLOverrideSupported;

public class ComandoGirar extends ComandoTransformacion
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "girar";
    
    /**
     * Valor para girar la tortuga hacia la derecha.
     */
    public static final int HORARIO = 0;
    
    /**
     * Valor para girar la tortuga hacia la izquierda.
     */
    public static final int ANTIHORARIO = 1;

 // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando para girar la tortuga.<br>
     * <b>post:</b>Se han asignado la nueva escala, dirección y el nombre del comando.</br>
     * @param pValor Valor del giro (en grados) . pValor >0.
     * @param pDireccion Dirección del giro. pDireccion == 0 || pDireccion == 1.
     */
	public ComandoGirar(double pValor, int pDireccion) 
	{
		super(pValor, pDireccion);
		nombre = COMANDO;
		
	}
	
	public void ejecutar(Tortuga pTortuga, Graphics2D pG) 
	{
		if ( direccion == HORARIO )
		{
			pTortuga.modificarOrientacion(pTortuga.darOrientacion() + valor);
		}
		else if ( direccion == ANTIHORARIO )
		{
			pTortuga.modificarOrientacion(pTortuga.darOrientacion() - valor);
		}
	}
	
}
