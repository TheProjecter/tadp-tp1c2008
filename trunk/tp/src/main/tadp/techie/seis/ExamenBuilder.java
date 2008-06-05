package tadp.techie.seis;

import java.util.Calendar;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ExamenBuilder {
	
	
	
	
	public Examen generarExamen(Calendar fechaQueSeraTomado,Set<String> unidadesAbarcadas, int cantidadPreguntasTeoricas, int cantidadEjerciciosPracticos, Materia materia)
	throws PreguntasInsuficientesException, ExamenSinPreguntasNiEjerciciosException
	{
		Set<Ejercicio> ejerciciosPracticos;
		Set<Pregunta> preguntasTeoricas;      

		//Agrego las practicas
		ejerciciosPracticos = obtenerEjercicios( ItemExamen.TiposItem.PRACTICO,  cantidadEjerciciosPracticos, unidadesAbarcadas, materia);

		//Agrego las teoricas
		preguntasTeoricas = obtenerPreguntas(ItemExamen.TiposItem.TEORICO, cantidadPreguntasTeoricas, unidadesAbarcadas, materia);



		//Mezclo todo
		Set<ItemExamen> preguntasParaElExamen = new HashSet<ItemExamen>();
		preguntasParaElExamen.addAll(ejerciciosPracticos);
		preguntasParaElExamen.addAll(preguntasTeoricas);


		//Instancio
		Examen examen = new Examen(fechaQueSeraTomado, unidadesAbarcadas, preguntasParaElExamen);
		materia.addExamen(examen);
		return examen;
	}
   
    
/**
* @author juan martin
* Genero un conjunto de preguntas para el examen. Tendran prioridad las menos utilizadas y 
* ante igualdad de uso la eleccion sera aleatoria. 
* @param tipoPregunta teorica, practica o practica-teorica?
* @param cantidadPreguntas cuantas preguntas de este tipo quiero?
* @param unidadesAbarcadas una coleccion con strings indicando las unidades (case sensitive)
* @return un conjunto de preguntas (sin repetidas)
*/
private  Set<Pregunta> obtenerPreguntas(Pregunta.TiposItem tipoPregunta, int cantidadDePreguntas, Set<String> unidadesAbarcadas, Materia materia) throws PreguntasInsuficientesException
{

	Set<Pregunta> preguntasPosibles = materia.getPreguntasDeTipo(tipoPregunta, new UsoItemComparator());
	Set<Pregunta> misPreguntas = new HashSet<Pregunta>();
	Iterator<Pregunta> it = preguntasPosibles.iterator();

	while(it.hasNext() && (misPreguntas.size() < cantidadDePreguntas))
	{
		Pregunta pregunta = it.next();

		if(unidadesAbarcadas.contains(pregunta.getUnidadTematica()))
			misPreguntas.add(pregunta);
	}

	if(misPreguntas.size() != cantidadDePreguntas)
		throw new PreguntasInsuficientesException("No hay suficientes preguntas de tipo "+ tipoPregunta +" en la materia "+materia.getNombre());

	return misPreguntas;
}

private  Set<Ejercicio> obtenerEjercicios( ItemExamen.TiposItem tipoEjercicio, int cantidadDeEjercicios, Set<String> unidadesAbarcadas, Materia materia) throws PreguntasInsuficientesException
{

	Set<Ejercicio> ejerciciosPosibles = materia.getEjerciciosDeTipo(tipoEjercicio, new UsoItemComparator());
	Set<Ejercicio> misEjercicios = new HashSet<Ejercicio>();
	Iterator<Ejercicio> it = ejerciciosPosibles.iterator();

	while(it.hasNext() && (misEjercicios.size() < cantidadDeEjercicios))
	{
		Ejercicio ejercicio = it.next();

		if(unidadesAbarcadas.contains(ejercicio.getUnidadTematica()))
			misEjercicios.add(ejercicio);
	}

	if(misEjercicios.size() != cantidadDeEjercicios)
		throw new PreguntasInsuficientesException("No hay suficientes preguntas de tipo "+ tipoEjercicio +" en la materia "+materia.getNombre());      
	return misEjercicios;
}

}
