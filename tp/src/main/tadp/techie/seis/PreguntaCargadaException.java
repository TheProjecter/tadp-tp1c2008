package tadp.techie.seis;

/**
 * @author Nico
 * La excepcion se presenta en el caso de que se quiera agregar dos veces la misma  
 * correccion de una pregunta para el examen de un alumno
 */
public class PreguntaCargadaException extends Exception
{
   
	private static final long serialVersionUID = 1L;
	
	
	PreguntaCargadaException()
    {
        super();
    }
	PreguntaCargadaException(String str)
    {
        super(str);
    }
	
}

