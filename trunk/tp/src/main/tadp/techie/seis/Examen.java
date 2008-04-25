package tadp.techie.seis;

import java.util.*;

public class Examen
{

    private Calendar fecha;
    private Set<String> unidadesAbarcadas;
    private Set<Pregunta> preguntas;

    /**
     * Instancia un examen en una determinada Fecha que abarca unidades y tiene
     * preguntas, las cuales se les avisa que se estan usando en un examen
     * @param fecha cuando se tomo/tomara
     * @param unidadesAbarcadas unidades abarcadas por las preguntas (no necesariamente todas se abarcan)
     * @param preguntas preguntas a incluir
     */
    public Examen(Calendar fecha, Set<String> unidadesAbarcadas, Set<Pregunta> preguntas) 
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
     */
    public Examen(Calendar fecha, Set<Pregunta> preguntas) 
    {
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

    public Calendar getFecha()
    {
        return fecha;
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
