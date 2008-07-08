package main.ar.utn.tadp.techie.seis;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
 

public class Materia  implements ItemAddable
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
        {
            retval += pregunta + "\n\t";
        }
        for(Ejercicio ejercicio : ejercicios)
        {
            retval += ejercicio + "\n\t";
        }

        retval += "Examenes:\n\t";

        for(Examen examen : examenes)
        {
            retval += examen + "\n\t";
        }

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
    public boolean equals(Object m)
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

    public void addEjercicio(Ejercicio ejercicio)
    {

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

    public void borrarItems()
    {
        ejercicios.clear();
        preguntas.clear();
    }

    public void addItem(ItemExamen item)
    {
        item.addTo(this);

    }

    public Set<ItemExamen> getItemsXML(String archivo) throws IOException
    {
        Set<ItemExamen> items = new HashSet<ItemExamen>();
        
         items.addAll(instanceXStream.itemsExamenFromXML(new FileInputStream(archivo)));
   
         addAllItems(items);
         
         return items;
    
    }
    public void addAllItems(Collection<ItemExamen> items)
    {
    	
    	for(ItemExamen item :items)
    	{
    		
    		addItem(item);
    		
    	}
    	
    	
    }
    
    
    
    public void addExamen(Examen examen)
    {
        examenes.add(examen);

    }
}
