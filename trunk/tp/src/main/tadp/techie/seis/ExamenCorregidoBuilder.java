package tadp.techie.seis;

import java.util.HashMap;
import java.util.Map;

public class ExamenCorregidoBuilder {

	private	Alumno alumno;
	private	Examen examen;

	private	int nota;

	// Mapa que contiene por cada Pregunta el resultado de la respuesta
	private Map<Pregunta, ExamenCorregido.RespuestaPregunta> notasPregunta;


	/**
	 * Este es el Constructor del Builder del examen corregido
	 * 
	 * @param al: Alumno al cual se corregira el Examen
	 * @param ex: Examen que sera corregido
	 */
	public ExamenCorregidoBuilder(Alumno al, Examen ex)
	{
		this.setAlumno(al);
		this.setExamen(ex);

		this.setNotasPregunta(new HashMap<Pregunta, ExamenCorregido.RespuestaPregunta>());

	}


	/**
	 * Este metodo carga la correcion de una nueva pregunta 
	 * 
	 * @param preg: La pregunta corregida
	 * @param rta: La nota de la correccion (Bien, Regular, Mal, etc)
	 * @throws PreguntaCargadaException: Si se quiere cargar dos veces la misma pregunta
	 * @throws PreguntaIncorrectaException: Si la pregunta que se quiere carga no pertenece al examen
	 */
	public void addCorreccionPregunta(Pregunta preg, ExamenCorregido.RespuestaPregunta rta) 
	  throws PreguntaCargadaException, PreguntaNoEstaEnExamenException
	{
		if (this.getNotasPregunta().containsKey(preg))
		{
			throw new PreguntaCargadaException();
		}

		// Si la pregunta no pertenece al examen, tiro una excepcion
		if (! (this.getExamen().getPreguntas().contains(preg) ))
		{
			throw new PreguntaNoEstaEnExamenException();
		}

		this.getNotasPregunta().put(preg, rta);
	}

	/**
	 * Este metodo es el build del ExamenCorregirlo, al invocarlo crea la correccion del examen y la asigna al Alumno 
	 */
	public ExamenCorregido corregirExamen () throws CorreccionIncompletaException
	{
		ExamenCorregido ex;
		
		if (this.getNota() ==0 && this.getNotasPregunta().isEmpty())
			throw new CorreccionIncompletaException();
		
		ex = new ExamenCorregido(this.getExamen(), this.getNota(), this.notasPregunta);
		
		this.getAlumno().addExamenCorregido(ex);
		return ex;
	} 


	public Alumno getAlumno() {
		return alumno;
	}


	public Examen getExamen() {
		return examen;
	}


	public int getNota() {
		return nota;
	}


	public Map<Pregunta, ExamenCorregido.RespuestaPregunta> getNotasPregunta() {
		return notasPregunta;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public void setExamen(Examen examen) {
		this.examen = examen;
	}


	/**
	 * 
	 * @param nota: Nota que obtuvo el Alumno (entre 1 y 10) 
	 * @throws 
	 */
	public void setNota(int nota) throws NotaIncorrectaException {
		
		if (nota > 0 && nota < 11 )
		{
			this.nota = nota;
			return;
		}

		throw new NotaIncorrectaException();
	}


	private void setNotasPregunta(
			Map<Pregunta, ExamenCorregido.RespuestaPregunta> notasPregunta) {
		this.notasPregunta = notasPregunta;
	}


}
