package tadp.techie.seis;


/**
 * @author Nico
 * La excepcion se presenta en el caso de que se quiera agregar la correcion  
 * de una pregunta que no existe en el examen
 */
public class PreguntaNoEstaEnExamenException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	PreguntaNoEstaEnExamenException()
    {
        super();
    }
	PreguntaNoEstaEnExamenException(String str)
    {
        super(str);
    }
}
