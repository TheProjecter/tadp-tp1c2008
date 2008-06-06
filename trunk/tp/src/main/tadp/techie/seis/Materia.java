package tadp.techie.seis;
import java.io.FileInputStream;
import java.util.*;


public class Materia implements ItemAddable
{
	private String nombre;
	private Set<Pregunta> preguntas;
	private List<Examen> examenes;
	private Set<Ejercicio> ejercicios;
	private SerializacionXStream instanceXStream;
	/**
	 * Instancia la nueva materia sin preguntas ni examanes
	 */
	public Materia()
	{
		this("");
	}
	/**
	 * Instancia y le da un nombre
	 * @param nombre
	 */
	public Materia(String nombre)
	{
		this.nombre = nombre;
		preguntas = new TreeSet<Pregunta>(new UsoItemComparator());
		examenes = new ArrayList<Examen>();	
		ejercicios = new TreeSet<Ejercicio>(new UsoItemComparator());
		instanceXStream = new SerializacionXStream();
	}




	/**
	 * Genera un nuevo examen para esta materia con la fecha en que se va a tomar,
	 * y las preguntas. Las preguntas y ejercicios se eligen según correspondan a las unidades
	 * que se quieran abarcar y una cantidad determinada de teóricas y prácticas.<p>
	 * <p>Al elegir preguntas/ejercicios se priorizan los que menos veces se tomaron en examenes anteriores
	 * y si fueron tomadas igual cantidad de veces se elige al azar.<p>
	 * @param fechaQueSeraTomado cuando?
	 * @param unidadesAbarcadas una coleccion con strings indicando las unidades (case sensitive)
	 * @param cantidadPreguntas cuantas teoricas?
	 * @param cantidadEjercicios cuantos practicos?
	 * @return el examen con las preguntas
	 * @throws ExamenSinPreguntasNiEjerciciosException 
	 */
	public Examen generarExamen(Calendar fechaQueSeraTomado,Set<String> unidadesAbarcadas, int cantidadPreguntas, int cantidadEjercicios)
	throws PreguntasInsuficientesException, ExamenSinPreguntasNiEjerciciosException
	{
		//delego la responsanilidad de la construccion del examen en otro objeto: examenBuilder
		ExamenBuilder examenBuilder = new ExamenBuilder();
		return examenBuilder.generarExamen(fechaQueSeraTomado, unidadesAbarcadas, cantidadPreguntas, cantidadEjercicios, this);

	}
	/**
	 * Filtra las materias de un determinado tipo y las ordena por el criterio
	 * del comparador que recibe
	 * @param tipo un tipo de pregunta
	 * @param comp un comparador de preguntas para ordenar
	 * @return la coleccion de materias filtradas y ordenadas
	 */
	public Set<Pregunta> getPreguntasDeTipo(Pregunta.TiposItem tipo, Comparator<ItemExamen> comp)
	{
		Set<Pregunta> retval = new TreeSet<Pregunta>(comp);

	try{
		
    	Set<ItemExamen> items = instanceXStream.itemsExamenFromXML(new FileInputStream(" "));
	
    	for(ItemExamen item : items ){
    		
    		if (item instanceof Pregunta){
    		
    			Pregunta pregunta = (Pregunta) item;
    			
    			preguntas.add(pregunta);
    			
    		}
    		
    			
    		
    	}
    	
	
	
	
	
	}
	catch(Exception e){
		
		e.printStackTrace();
	}
		
    	
		
		
		for(Pregunta pregunta : preguntas)
		{
			if(pregunta.getTipo().equals(tipo))
				retval.add(pregunta);
		}

		return retval;
	}
	public Set<Ejercicio> getEjerciciosDeTipo(ItemExamen.TiposItem tipo, Comparator<ItemExamen> comp)
	{
		Set<Ejercicio> retval = new TreeSet<Ejercicio>(comp);

		
		try{
			
	    	Set<ItemExamen> items = instanceXStream.itemsExamenFromXML(new FileInputStream(" "));
		
	    	for(ItemExamen item : items ){
	    		
	    		if (item instanceof Ejercicio){
	    		
	    			Ejercicio ejercicio = (Ejercicio) item;
	    			
	    			ejercicios.add(ejercicio);
	    			
	    		}
	    		
	    			
	    		
	    	}
	    	
		
		
		
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for(Ejercicio ejercicio : ejercicios)
		{

			if(ejercicio.getTipo().equals(tipo))
				retval.add(ejercicio);

		}

		return retval;
	}
	
	public Set<ItemExamen> getItemsDeTipo(ItemExamen.TiposItem tipo, Comparator<ItemExamen> comp)
	{
	
		Set<ItemExamen> retval = new TreeSet<ItemExamen>(comp);

		for(Ejercicio ejercicio : ejercicios)
		{

			if(ejercicio.getTipo().equals(tipo))
				retval.add(ejercicio);

		}
		for(Pregunta pregunta : preguntas)
		{
			if(pregunta.getTipo().equals(tipo))
				retval.add(pregunta);
		}
		return retval;
	}
	/**
	 * @return solo el nombre para no hacerlo tan denso
	 */
	 @Override
	 public String toString()
	 {
		 return nombre;
	 }
	 /**
	  * @return un string que describe mas detalladamente que toString
	  */
	 public String detalle()
	 {
		 String retval = nombre + "\n Preguntas:\n\t";

		 for(Pregunta pregunta : preguntas)
			 retval += pregunta + "\n\t";
		 for(Ejercicio ejercicio : ejercicios)
			 retval += ejercicio + "\n\t";

		 retval += "Examenes:\n\t";

		 for(Examen examen : examenes)
			 retval += examen + "\n\t";

		 return retval;
	 }
	 public Set<Pregunta> getPreguntas() 
	 {
		 return preguntas;
	 }
	 public List<Examen> getExamenes() 
	 {
		 return examenes;
	 }
	 public String getNombre()
	 {
		 return nombre;
	 }
	 public void setNombre(String nombre)
	 {
		 this.nombre = nombre;
	 }
	 public void addPregunta(Pregunta pregunta)
	 {
		 preguntas.add(pregunta);
	 }


	 /**
	  * Dos materias son iguales si tienen el mismo nombre.
	  */
	 @Override
	 public boolean equals (Object m)
	 {
		 if(m instanceof Materia)
		 {
			 Materia materia = (Materia) m;
			 return this.nombre.equals(materia.getNombre());        
		 }

		 return false;
	 }

	 @Override
	 public int hashCode()
	 {
		 int hash = 7;
		 hash = 23 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
		 return hash;
	 }
	 public void addEjercicio(Ejercicio ejercicio) {

		 ejercicios.add(ejercicio);

	 }
	 public Set<Ejercicio> getEjercicios()
	 {
		 return ejercicios;
	 }
	 
	 public Set<ItemExamen> getItems()
	 {
		 Set<ItemExamen> items = new HashSet<ItemExamen>();
		
		 items.addAll(preguntas);
		 items.addAll(ejercicios);
		 return items;
	 }
	 public void borrarPreguntas()
	 {
		 preguntas.clear();
	 }
	 public void borrarEjercicios()
	 {
		 ejercicios.clear();
	 }
	 public void borrarItems() {
		 ejercicios.clear();
		 preguntas.clear();
	 }
	 public void addItem(ItemExamen item) {
		 item.addTo(this);

	 }


	 public void addExamen(Examen examen) {
		 examenes.add(examen);

	 }


}
