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
            if(pregunta.getTipo() == tipo)
                retval.add(pregunta);
        }

        return retval;
    }

    /**
     * Genera un nuevo examen para esta materia con la fecha en que se va a tomar,
     * y las preguntas. Las preguntas se elijen las que correspondan a las unidades
     * que se queran abarcar y una cantidad determinada de teoricas y practicas
     * Al elegir preguntas se priorizan las que menos veces se tomaron en examenes anteriores
     * y si furon tomadas igual cantidad de veces se elige al azar.
     * @param fechaQueSeraTomado cuando?
     * @param unidadesAbarcadas una coleccion con strings indicando las unidades (case sensitive)
     * @param cantidadPreguntasTeoricas cuantas teoricas?
     * @param cantidadPreguntasPracticas cuantas practicas?
     * @return el examen con las preguntas
     */
    public Examen generarExamen(Calendar fechaQueSeraTomado,Set<String> unidadesAbarcadas, int cantidadPreguntasTeoricas, int cantidadPreguntasPracticas)
    {
        //Agrego las practicas
        Set<Pregunta> practicas = getPreguntasDeTipo(Pregunta.TiposPregunta.PRACTICO, new UsoPreguntaComparator());
        Iterator<Pregunta> it = practicas.iterator();

        while(it.hasNext() && practicas.size() < cantidadPreguntasPracticas)
        {
            Pregunta pregunta = it.next();

            if(unidadesAbarcadas.contains(pregunta.getUnidadTematica()))
                practicas.add(pregunta);
        }
        //Agrego las teoricas
        Set<Pregunta> teoricas = getPreguntasDeTipo(Pregunta.TiposPregunta.TEORICO, new UsoPreguntaComparator());
        it = teoricas.iterator();

        while(it.hasNext() && teoricas.size() < cantidadPreguntasTeoricas)
        {
            Pregunta pregunta = it.next();

            if(unidadesAbarcadas.contains(pregunta.getUnidadTematica()))
                teoricas.add(pregunta);
        }	

        //Mezclo todo
        Set<Pregunta> preguntasParaElExamen = new HashSet<Pregunta>();
        preguntasParaElExamen.addAll(practicas);
        preguntasParaElExamen.addAll(teoricas);

        //Instancio
        Examen examen = new Examen(fechaQueSeraTomado,unidadesAbarcadas,preguntasParaElExamen);
        //Me lo guardo
        examenes.add(examen);
        //Devuelvo
        return examen;
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
	
}
