package uniandes.cupi2.estructuras;

import java.io.Serializable;

/**
 * Contrato que deben cumplir todos los elementos únicamente identificados
 * @author alvar-go
 */
public interface IdentificadoUnicamente extends Serializable
{

    /**
     * Devuelve el identificador del elemento
     * @return identificador
     */
    public String darIdentificador();
}
