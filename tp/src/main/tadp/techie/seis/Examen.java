package tadp.techie.seis;


import java.util.*;
public class Examen {

	private Calendar fecha;
	private Set<String> unidadesAbarcadas;
	private Set<Pregunta> preguntas;
	
	
	public Examen(Calendar fecha, Set<String> unidadesAbarcadas, Set<Pregunta> preguntas) 
	{
		this.fecha = fecha;
		this.unidadesAbarcadas = unidadesAbarcadas;
		this.preguntas = preguntas;
	}


	public Calendar getFecha() {
		return fecha;
	}


	public Set<String> getUnidades() {
		return unidadesAbarcadas;
	}


	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}
	
	
	
}
