package tadp.techie.seis;

import java.util.Calendar;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * ExamenBuilder sirve para crear examenes cargados de ItemExamen seleccionados
 * automaticamente por los criterios indicados. Los criterios se crean formando
 * prototipos de items a traves de la clase {@link PrototipoItem} y de cada prototipo
 * la cantidad de items
 * <p>
 * Pasos para la creacion de un Examen
 * - Instanciar ExamenBuilder con la materia, las unidades abarcadas y la fecha
 * - Con el metodo putPrototipo() cargar todos los prototipos que sean necesarios
 *   indicando cuantos items que respondan a ese prototipo deben aparecer
 * - Opcionalmente se puede setear un Comparator<ItemExamen> para indicarle al builder
 *   la prioridad con la que se seleccionaran los items. Si no se le setea ninguno tomara
 *   el {@link UsoItemComparator} por defecto (ver javadoc para mas detalles)
 * - Invocar al metodo generarExamen() para obtener el examen     
 * 
 * @see PrototipoItem, UsoItemComparator
 * @author xuan
 *
 */
public class ExamenBuilder
{

    private Materia materia;
    private Set<String> unidadesAbarcadas;
    private Calendar fecha;
    private Comparator<ItemExamen> comparator = new UsoItemComparator();
    private HashMap<PrototipoItem<? extends ItemExamen>, Integer> mapaPrototipos = new HashMap<PrototipoItem<? extends ItemExamen>, Integer>();
    
    
    private ExamenBuilder()
    {
        this.setUnidadesAbarcadas(new HashSet<String>());
    }
    /**
     * Instancia un nuevo Builder para poder generar el examen con items seleccionados automaticamente
     * @param materia la materia a la que pertenecera el examen
     * @param unidadesAbarcadas un colleccion de String que contiene los nombres de las unidades
     * @param fecha la fecha en que se tomara el examen
     */
    public ExamenBuilder(Materia materia, Collection<String> unidadesAbarcadas, Calendar fecha)
    {
        this();
        this.setMateria(materia);
        this.getUnidadesAbarcadas().addAll(unidadesAbarcadas);
        this.fecha = fecha;
    }
    
    /**
     * Agrega el prototipo a la lista de los prototipos que se usaran para seleccionar los items
     * Si se indica dos veces el mismo prototipo y dos prototipos iguales se tomara la ultima cantidad
     * 
     * @param proto
     * @param cantidad
     */
    public void putPrototipo(PrototipoItem<? extends ItemExamen> proto, int cantidad)
    {
        mapaPrototipos.put(proto, new Integer(cantidad));
    }

    /**
     * Crea el examen seleccionando de la materia cargada los items que respondan a los
     * prototipos cargados. Elije por prioridad indicada por el Comparator (por defecto se usa
     * el UsoItemComparator, si no se indico ninguno)
     * @return La instancia del Examen con los items seleccionados
     * @throws PreguntasInsuficientesException Si la cantidad de Items pedidos es mayor a los que
     * 	se encontraron en la materia y no se pudo crear el examen con la catidad de items deseada
     * @throws ExamenSinPreguntasNiEjerciciosException Si se intento intanciar sin items
     */
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
        Set<ItemExamen> retval = new TreeSet<ItemExamen>(getComparator());

        retval.addAll(materia.getItems());
        return retval;
    }
    /**
     * De la coleccion de items origen selecciona los primeros cantidad de items que
     * responden al protoripo y ademas el item es de una unidad tematica contenida en
     * la coleccion de unidades
     * @param origen Una coleccion de Items
     * @param cantidad La cantidad a seleccionar
     * @param proto El prototipo que se usa para ejejir items
     * @return
     * @throws PreguntasInsuficientesException
     */
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

	public void setComparator(Comparator<ItemExamen> comparator) {
		this.comparator = comparator;
	}

	public Comparator<ItemExamen> getComparator() {
		return comparator;
	}
}
