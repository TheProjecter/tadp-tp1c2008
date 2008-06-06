package tadp.techie.seis;

import java.util.Calendar;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ExamenBuilder
{

    private Materia materia;
    private Set<String> unidadesAbarcadas;
    private Calendar fecha;
    private Comparator comp = new UsoItemComparator();

    public ExamenBuilder()
    {
        this.setUnidadesAbarcadas(new HashSet<String>());
    }

    public ExamenBuilder(Materia materia, Collection<String> unidadesAbarcadas, Calendar fecha)
    {
        this();
        this.setMateria(materia);
        this.getUnidadesAbarcadas().addAll(unidadesAbarcadas);
        this.fecha = fecha;
    }
    HashMap<PrototipoItem<? extends ItemExamen>, Integer> mapaPrototipos = new HashMap<PrototipoItem<? extends ItemExamen>, Integer>();

    public void putPrototipo(PrototipoItem<? extends ItemExamen> proto, int cantidad)
    {
        mapaPrototipos.put(proto, new Integer(cantidad));
    }

    public Examen generarExamen() throws PreguntasInsuficientesException, ExamenSinPreguntasNiEjerciciosException
    {
        Set<ItemExamen> itemsTotales = new HashSet<ItemExamen>();

        for(PrototipoItem<? extends ItemExamen> proto : mapaPrototipos.keySet())
        {
            itemsTotales.addAll(obtenerItems(getSortedItems(), mapaPrototipos.get(proto), proto));
        }
        //Instancio
        Examen examen = new Examen(fecha, unidadesAbarcadas, itemsTotales);
        materia.addExamen(examen);
        return examen;
    }

    /**
     * Devuelve todos los items de la materia ordenados en el Set
     * segun el criterio de ordenamiento dado por el comparator
     * Por defecto es los menos usados primero
     * @return Los items de la materia ordenados
     */
    private Set<ItemExamen> getSortedItems()
    {
        Set<ItemExamen> retval = new TreeSet<ItemExamen>(comp);

        retval.addAll(materia.getItems());
        return retval;
    }

    private Set<ItemExamen> obtenerItems(Set<ItemExamen> origen, int cantidad, PrototipoItem<? extends ItemExamen> proto)
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
            if(getUnidadesAbarcadas().contains(item.getUnidadTematica()) && proto.itemSeParece(item))
            {
                retval.add(item);
            }
        }

        if(retval.size() != cantidad)
        {
            throw new PreguntasInsuficientesException("No hay suficientes items de tipo " + proto.getTipo() + " en la materia " + getMateria().getNombre());
        }

        return retval;
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
