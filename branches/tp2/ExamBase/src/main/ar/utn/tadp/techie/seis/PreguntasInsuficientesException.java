package ar.utn.tadp.techie.seis;

/**
 * @author xuan
 * La excepcion se presenta en el caso de que se quiera generar un nuevo examen
 * con cierta cantidad de preguntas y que no esten disponible dicha cantidad
 */
class PreguntasInsuficientesException extends Exception
{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PreguntasInsuficientesException()
    {
        super();
    }
    PreguntasInsuficientesException(String str)
    {
        super(str);
    }
}