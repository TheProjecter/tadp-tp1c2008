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
     * @param unidadesAbarcadas unidades abarcadas por las preguntas (no necesriamente todas se abarcan)
     * @param preguntas preguntas a incluir
     */
    public Examen(Calendar fecha, Set<String> unidadesAbarcadas, Set<Pregunta> preguntas) 
    {
        this.fecha = fecha;
        this.unidadesAbarcadas = unidadesAbarcadas;
        this.preguntas = preguntas;
     
        //Incremento la cantidad de veces que se uso
        for(Pregunta pregunta : preguntas)
                pregunta.usoEnExamen();
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

}
