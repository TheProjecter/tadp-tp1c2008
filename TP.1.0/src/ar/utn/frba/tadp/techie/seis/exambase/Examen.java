package ar.utn.frba.tadp.techie.seis.exambase;

import java.util.*;

public class Examen
{

    private Calendar fecha;
    private Set<String> unidadesAbarcadas;
    private Set<Pregunta> preguntas;

/*
    public Examen(Calendar fecha, Set<String> unidadesAbarcadas, Set<Pregunta> preguntas) 
    {
            this.fecha = fecha;
            this.unidadesAbarcadas = unidadesAbarcadas;
            this.preguntas = preguntas;
    }*/
    /**@author juan martin
     *  @param fecha cuando?
     * @param unidadesAbarcadas una coleccion con strings indicando las unidades (case sensitive)
     * @param cantidadPreguntasTeoricas cuantas teoricas?
     * @param cantidadPreguntasPracticas cuantas practicas?
     * @param materia para que materia?
     * @return el examen con las preguntas
     */
    public Examen(Calendar fecha, Set<String> unidadesAbarcadas , int cantidadPreguntasTeoricas, int cantidadPreguntasPracticas, Materia materia) 
    {
            this.fecha = fecha;
            this.unidadesAbarcadas = unidadesAbarcadas;
            //this.preguntas = preguntas;
            
            //Agrego las practicas
            Set<Pregunta> practicas = obtenerPreguntas(Pregunta.TiposPregunta.PRACTICO, cantidadPreguntasPracticas, materia);
            //Agrego las teoricas
            Set<Pregunta> teoricas = obtenerPreguntas(Pregunta.TiposPregunta.TEORICO, cantidadPreguntasTeoricas, materia);

            //Mezclo todo
            this.preguntas = new HashSet<Pregunta>();
            this.preguntas.addAll(practicas);
            this.preguntas.addAll(teoricas);
            
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
    private  Set<Pregunta> obtenerPreguntas(Pregunta.TiposPregunta tipoPregunta, int cantidadDePreguntas, Materia materia)
    {
        Set<Pregunta> misPreguntas = materia.getPreguntasDeTipo(tipoPregunta, new UsoPreguntaComparator());
        Iterator<Pregunta> it = misPreguntas.iterator();

        while(it.hasNext() && misPreguntas.size() < cantidadDePreguntas)
        {
            Pregunta pregunta = it.next();

            if(this.unidadesAbarcadas.contains(pregunta.getUnidadTematica()))
            	misPreguntas.add(pregunta);
        }
    	return misPreguntas;
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

}