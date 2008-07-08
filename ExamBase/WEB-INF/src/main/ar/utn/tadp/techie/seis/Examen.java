package main.ar.utn.tadp.techie.seis;

import java.util.*;

public class Examen implements ItemAddable
{

    private Calendar fecha;
    private Set<String> unidadesAbarcadas;
    private Set<Pregunta> preguntas;
    private Set<Ejercicio> ejercicios;
    
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
     * @param ejercicios ejercicios a incluir
     * @throws ExamenSinPreguntasNiEjerciciosException 
     */
    public Examen(Calendar fecha, Collection<String> unidadesAbarcadas, Collection<ItemExamen> items) 
                throws ExamenSinPreguntasNiEjerciciosException 
    {
      
    	this(fecha, items);
   	    	
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
     * @throws ExamenSinPreguntasNiEjerciciosException 
     */
    public Examen(Calendar fecha, Collection<ItemExamen> items) throws ExamenSinPreguntasNiEjerciciosException 
    {
    	if(items == null || items.size() == 0) // ver: hacer otra excepcion para el caso de que no ahay ejercicios o generalizar apra cuando no haya ni preguntas ni ejercicios
          	throw new ExamenSinPreguntasNiEjerciciosException("No se puede crear un examen sin preguntas ni ejercicios.");
    	
    	
        this.fecha = fecha;
        this.unidadesAbarcadas = new HashSet<String>();

        this.ejercicios = new HashSet<Ejercicio>();
        this.preguntas  = new HashSet<Pregunta>();
        
        this.addAllItems(items);
        
        for(ItemExamen item : this.getItems())
        {  
            //Incremento la cantidad de veces que se uso
            item.incrementarUso();
            //Incluyo la unidad tematica
            this.unidadesAbarcadas.add(item.getUnidadTematica());
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
    public void borrarEjercicios()
    {
        ejercicios.clear();
    }
    public void borrarItems()
    {
        this.borrarPreguntas();
        this.borrarEjercicios();
    }
    /*
     * compara por la fecha, las unidades tematicas y la cantidad de preguntas y ejercicios
     * @param otro examen
     * @return si es el mismo
     */
    @Override
    public boolean equals (Object e)
    {

        if(e != null && e instanceof Examen)
        {
            Examen examen = (Examen) e;

            return this.fecha.equals(examen.getFecha())
                && this.unidadesAbarcadas.equals(examen.getUnidades())
                && (this.preguntas.equals(examen.getPreguntas()))
                && (this.ejercicios.equals(examen.getEjercicios()));
            
        }

        return false;

    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 83 * hash + (this.fecha != null ? this.fecha.hashCode() : 0);
        hash = 83 * hash + (this.unidadesAbarcadas != null ? this.unidadesAbarcadas.hashCode() : 0);
        hash = 83 * hash + (this.preguntas != null ? this.preguntas.hashCode() : 0);
        hash = 83 * hash + (this.ejercicios != null ? this.ejercicios.hashCode() : 0);
        return hash;
    }

    public Set<Ejercicio> getEjercicios()
    {
            return ejercicios;
    }

    public void setEjercicios(Set<Ejercicio> ejercicios)
    {
            this.ejercicios = ejercicios;
    }
    /**
     * 
     * @return  todos los items, preguntas mas ejercicios
     * en una sola coleccion
     */
    public Collection<ItemExamen> getItems()
    {
        HashSet<ItemExamen> retval = new HashSet<ItemExamen>();

        retval.addAll(getEjercicios());
        retval.addAll(getPreguntas());

        return retval;
    }
   
   

    private void addAllItems(Collection<ItemExamen> items)
    {
        for( ItemExamen item : items )
            item.addTo(this);
    }

    public void addItem(ItemExamen item)
    {
        item.addTo(this);
    }

    public void addEjercicio(Ejercicio ejercicio)
    {
        ejercicios.add(ejercicio);
    }
        
        
}
