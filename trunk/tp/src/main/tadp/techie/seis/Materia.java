package tadp.techie.seis;


import java.util.*;


/**
 * 
 * @author maxi - xuan
 */
public class Materia
{
    private String nombre;
    private Set<Pregunta> preguntas;
    private List<Examen> examenes;
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
        preguntas = new TreeSet<Pregunta>(new UsoPreguntaComparator());
        examenes = new ArrayList<Examen>();	
    }


    /**
     * Filtra las materias de un determinado tipo y las ordena por el criterio
     * del comparador que recibe
     * @param tipo un tipo de pregunta
     * @param comp un comparador de preguntas para ordenar
     * @return la coleccion de materias filtradas y ordenadas
     */
    public Set<Pregunta> getPreguntasDeTipo(Pregunta.TiposPregunta tipo, Comparator<Pregunta> comp)
    {
        Set<Pregunta> retval = new TreeSet<Pregunta>(comp);

        for(Pregunta pregunta : preguntas)
        {
            if(pregunta.getTipo().equals(tipo))
                retval.add(pregunta);
        }

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
     * @param cantidadPreguntasPracticas cuantas practicas?
     * @return el examen con las preguntas
     * @throws ExamenSinPreguntasException 
     */
    public Examen generarExamen(Calendar fechaQueSeraTomado,Set<String> unidadesAbarcadas, int cantidadPreguntasTeoricas, int cantidadPreguntasPracticas)
            throws PreguntasInsuficientesException, ExamenSinPreguntasException
    {
        Set<Pregunta> practicas;
        Set<Pregunta> teoricas;
                
        try
        {
            //Agrego las practicas
            practicas = obtenerPreguntas(Pregunta.TiposPregunta.PRACTICO, cantidadPreguntasPracticas, unidadesAbarcadas);
        }
        catch(PreguntasInsuficientesException ex)
        {
            throw new PreguntasInsuficientesException("No hay suficientes preguntas de tipo PRACTICO en la materia "+nombre);
        }   
        try
        {
            //Agrego las teoricas
            teoricas = obtenerPreguntas(Pregunta.TiposPregunta.TEORICO, cantidadPreguntasTeoricas, unidadesAbarcadas);
        }
        catch(PreguntasInsuficientesException ex)
        {
            throw new PreguntasInsuficientesException("No hay suficientes preguntas de tipo TEORICO en la materia "+nombre);
        }
        
        //Mezclo todo
        Set<Pregunta> preguntasParaElExamen = new HashSet<Pregunta>();
        preguntasParaElExamen.addAll(practicas);
        preguntasParaElExamen.addAll(teoricas);


        //Instancio
        Examen examen = new Examen(fechaQueSeraTomado, unidadesAbarcadas, preguntasParaElExamen);


        examenes.add(examen);


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
    private  Set<Pregunta> obtenerPreguntas(Pregunta.TiposPregunta tipoPregunta, int cantidadDePreguntas, Set<String> unidadesAbarcadas) throws PreguntasInsuficientesException
    {
       
    	Set<Pregunta> preguntasPosibles = getPreguntasDeTipo(tipoPregunta, new UsoPreguntaComparator());
        Set<Pregunta> misPreguntas = new HashSet<Pregunta>();
        Iterator<Pregunta> it = preguntasPosibles.iterator();
       
       while(it.hasNext() && (misPreguntas.size() < cantidadDePreguntas))
        {
            Pregunta pregunta = it.next();

            if(unidadesAbarcadas.contains(pregunta.getUnidadTematica()))
                misPreguntas.add(pregunta);
        }
        
        if(misPreguntas.size() != cantidadDePreguntas)
            throw new PreguntasInsuficientesException();
      
    	return misPreguntas;
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
	public void borrarPreguntas()
	{
		preguntas.clear();
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
}
