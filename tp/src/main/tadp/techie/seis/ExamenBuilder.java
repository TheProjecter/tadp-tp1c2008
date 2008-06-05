package tadp.techie.seis;

import java.util.Calendar;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ExamenBuilder
{
	
	private Materia materia;
	private Set<String> unidadesAbarcadas;
	
	public ExamenBuilder()
	{
		this.setUnidadesAbarcadas(new HashSet<String>());
	}
	
	public ExamenBuilder(Materia materia, Collection<String> unidadesAbarcadas)
	{
		this();
		this.setMateria(materia);
		this.getUnidadesAbarcadas().addAll(unidadesAbarcadas);
	}
	
	
	
	HashMap<PrototipoItem<ItemExamen>, Integer> mapaPrototipos = new HashMap<PrototipoItem<ItemExamen>, Integer>();
	
	
	public void putPrototipo(PrototipoItem<ItemExamen> proto, int cantidad)
	{
		mapaPrototipos.put(proto, new Integer(cantidad));
	}
	
	
	public Examen generarExamen() throws PreguntasInsuficientesException
	{
		Set<ItemExamen> itemsTotales = new HashSet<ItemExamen>();
		
		for(PrototipoItem<ItemExamen> proto : mapaPrototipos.keySet())
		{
			itemsTotales.addAll(obtenerItems(getMateria().getItems(), mapaPrototipos.get(proto), proto));
		}
		
		return null;
	}
	
	
	private Set<ItemExamen> obtenerItems(Set<ItemExamen> origen, int cantidad, PrototipoItem<ItemExamen> proto)
		throws PreguntasInsuficientesException
	{
		Set<ItemExamen> retval = new HashSet<ItemExamen>();
		
		Iterator<ItemExamen> it = origen.iterator();
	
		while(it.hasNext() && (retval.size() < cantidad))
		{
			ItemExamen item = it.next();
	
			//Lo tengo que agregar si es de una unidad tematica que quiero y ademas se parece al prototipo
			//Si el campo del prototipo por el que comparo es null o 0 no lo tengo que comprar entonces lo tomo
			//como verdadero
			if(		getUnidadesAbarcadas().contains(item.getUnidadTematica())
				&&	proto.itemSeParece(item)
				)
				retval.add(item);
		}
	
		if(retval.size() != cantidad)
			throw new PreguntasInsuficientesException("No hay suficientes items de tipo "+ proto.getTipo() +" en la materia "+getMateria().getNombre());
	
		return retval;
	}
	
	/**
	 * Genera un nuevo examen para esta materia con la fecha en que se va a tomar,
	 * y las preguntas. Las preguntas se elijen las que correspondan a las unidades
	 * que se queran abarcar y una cantidad determinada de teoricas y practicas.<p>
	 * <p>Al elegir preguntas se priorizan las que menos veces se tomaron en examenes anteriores
	 * y si furon tomadas igual cantidad de veces se elige al azar.<p>
	 * @param fechaQueSeraTomado cuando?
	 * @param unidadesAbarcadas una coleccion con strings indicando las unidades (case sensitive)
	 * @param cantidadPreguntasTeoricas cuantas teoricas?
	 * @param cantidadEjerciciosPracticos cuantos practicos?
	 * @param materia materia del examen ?
	 * @return el examen con las preguntas
	 * @throws ExamenSinPreguntasNiEjerciciosException 
	 */
	
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

	public void setMateria(Materia materia)
	{
		this.materia = materia;
	}

	public Materia getMateria()
	{
		return materia;
	}

	private void setUnidadesAbarcadas(Set<String> unidadesAbarcadas)
	{
		this.unidadesAbarcadas = unidadesAbarcadas;
	}

	private Set<String> getUnidadesAbarcadas()
	{
		return unidadesAbarcadas;
	}
	
	public void addUnidadAbarcada(String unidad)
	{
		unidadesAbarcadas.add(unidad);
	}
	
	public void clearUnidadesAbarcadas()
	{
		unidadesAbarcadas.clear();
	}
	
	public void addAllUnidadesAbarcadas(Collection<String> unidades)
	{
		unidadesAbarcadas.addAll(unidades);		
	}

}
