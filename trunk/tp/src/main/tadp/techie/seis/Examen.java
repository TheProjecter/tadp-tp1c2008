package tadp.techie.seis;

import java.util.*;

public class Examen
{

    private Calendar fecha;
    private Set<String> unidadesAbarcadas;
    private Set<Pregunta> preguntas;

    /**
     * <p>Instancia un examen en una determinada Fecha que abarca unidades y tiene
     * preguntas, las cuales se les avisa que se estan usando en un examen
     * Si se envian preguntas que abarcan unidades no declaradas, estas unidades
     * tambien seran parte del examen.<p>
     * Si el parametro unidadesAbarcadas es null o una coleccion vacia el llamado tendra
     * el mismo efecto que new Examen(fecha, preguntas), tomandose como unidadesAbarcadas
     * solo las incluidas en las preguntas.
     * <p>No se pueden crear examenes sin preguntas.<p>
     * <p>Se pueden crear examenes sin fecha, aunque no es recomendable.<p>
     * @param fecha cuando se tomo/tomara
     * @param unidadesAbarcadas unidades abarcadas por las preguntas (no necesariamente todas se abarcan)
     * @param preguntas preguntas a incluir
     * @throws ExamenSinPreguntasException 
     */
    public Examen(Calendar fecha, Collection<String> unidadesAbarcadas, Set<Pregunta> preguntas) throws ExamenSinPreguntasException 
    {
      
    	this(fecha, preguntas);
   	
        //Si me mandan null null
        if(unidadesAbarcadas == null)
            return;
        
        for(String unidad : unidadesAbarcadas)
        {
            //Incluyo la unidad tematica
            this.unidadesAbarcadas.add(unidad);
        }
    }
    /**
     * Instancia un examen en una determinada Fecha que abarca unidades y tiene
     * preguntas, las cuales se les avisa que se estan usando en un examen
     * Las unidades abarcadas son las mismas que abarcan las materias
     * @param fecha cuando se tomo/tomara
     * @param preguntas preguntas a incluir
     * @throws ExamenSinPreguntasException 
     */
    public Examen(Calendar fecha, Set<Pregunta> preguntas) throws ExamenSinPreguntasException 
    {
    	if(preguntas == null)
          	throw new ExamenSinPreguntasException("No se puede crear un examen sin preguntas");
    	
        this.fecha = fecha;
        this.preguntas = preguntas;
        this.unidadesAbarcadas = new HashSet<String>();
        
        for(Pregunta pregunta : preguntas)
        {
            //Incremento la cantidad de veces que se uso
            pregunta.incrementarUso();
            //Incluyo la unidad tematica
            this.unidadesAbarcadas.add(pregunta.getUnidadTematica());
        }
    }
    
    public void setFecha(Calendar fecha)
    {
        this.fecha = fecha;
    }
    
    public Calendar getFecha()
    {
        return fecha;
    }

    public void setUnidades( Set<String> unidades)
    {
        this.unidadesAbarcadas = unidades;
    }
    public Set<String> getUnidades()
    {
        return unidadesAbarcadas;
    }


    public Set<Pregunta> getPreguntas()
    {
        return preguntas;
    }
    
    public void addPregunta(Pregunta pregunta)
    {
            preguntas.add(pregunta);
    }
    public void borrarPreguntas()
	{
		preguntas.clear();
	}
    /**@author Juan Martin
     * compara por la fehca, las unidades tematicas y la cantidad de preguntas
     * @param otro examen
     * @return si es el mismo
     */
    @Override
    public boolean equals (Object e)
    {

        if(e instanceof Examen)
        {
        	Examen examen = (Examen) e;

            return this.fecha.equals(examen.getFecha())
                    && this.unidadesAbarcadas.equals(examen.getUnidades())
                    && (this.preguntas.equals(examen.getPreguntas()));
            
        }

        return false;

    }
}
