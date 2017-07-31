package uniandes.cupi2.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import uniandes.cupi2.cupiViajes.excepciones.PersistenciaException;


/**
 * Clase encargada de serializar y deserializar objetos
 * @author alvar-go
 */
public class Serializador {

    /**
     * M�todo encargado de serializar objetos
     * @param ruta ruta donde se crear� el archivo con el objeto serializado. ruta != null. Si no existe el archivo con la ruta se crea
     * @param objeto el objeto que se desea serializar
     * @throws PersistenciaException Se lanza en caso que no se pueda escribir el archivo
     */
    public static void guardar(String ruta, Serializable objeto)throws PersistenciaException
    {
        File f = new File (ruta);
        try
        {

            if (!f.exists( ))
            {
                f.createNewFile( );
            }

            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( f ) );
            oos.writeObject( objeto );
            oos.close( );
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace( );
            throw new PersistenciaException( e.getMessage( ), ruta );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
            throw new PersistenciaException( e.getMessage( ), ruta );
        }
    }



    /**
     * Carga un objeto desde un archivo serializado
     * pre: El archivo solo tiene un objeto
     * @param ruta la ruta donde se encuentra el archivo que se desea cargar
     * @param objeto el objeto donde se guadar� la informaci�n cargada. objeto != null
     * @return en caso que se pueda cargar la informaci�n se guarda en el objeto y se devuelve, de lo contrario solo se devuelve el objeto
     * @throws PersistenciaException Se lanza en caso que no se pueda leer el archivo o se genere alg�n tipo de error al leerlo
     */
    public static <T> T cargar(String ruta, T objeto)throws PersistenciaException
    {
        try {
            File archivo = new File(ruta);
            if(archivo.exists())
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
                objeto = (T)ois.readObject( );
                ois.close();
            }
        }
        catch ( Exception e ){
            throw new PersistenciaException( e.getMessage( ), ruta );
        }
        return objeto;
    }
}


