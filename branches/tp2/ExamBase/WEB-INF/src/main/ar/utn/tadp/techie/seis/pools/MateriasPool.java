/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.pools;

import ar.utn.tadp.techie.seis.Examen;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Materia;
import ar.utn.tadp.techie.seis.persistance.MateriaDAO;

import ar.utn.tadp.techie.seis.persistance.SQLServerMateriaDAO;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author xuan
 */
public class MateriasPool
{

    protected static MateriasPool instance;
    
    protected Map<Materia,Boolean> mapaMaterias;
    protected int size;
    protected MateriaDAO dao;
    
    protected MateriasPool()
    {
        mapaMaterias = new HashMap<Materia,Boolean>();
        size = 3; //TODO Hardcodeado!, sacarlo de configuracion
        dao = new SQLServerMateriaDAO();
    }
    
    public static MateriasPool getInstance()
    {
        if(instance == null)
            instance = new MateriasPool();
        return instance;
    }
    
    /**
     * Devuelve la instancia de la materia, si no esta en el pool
     * la busca en la db
     * @param nombre el nombre de una materia
     * @return la materia con ese nombre
     */
    public Materia getMateria(String nombre)
    {
        
        //Busco en las que estan cargadas
        for(Materia materia:mapaMaterias.keySet())
            if(materia.getNombre().equals(nombre))
                return materia;
        
        Materia retval = dao.getMateriaByNombre(nombre);
        addMateria(retval);
        return retval;
    }
    
    
    public Collection<String> getMateriasNameList()
    {
        return dao.getMateriasNameList();
    }
    
    private void addMateria(Materia materia)
    { 
        if(mapaMaterias.size() < size)
        {
            mapaMaterias.put(materia, false);
            return;
        }
        //Si esta lleno busco una no-dirty para borrar
        for(Materia materiaASacar:mapaMaterias.keySet())
        {
            //Si no esta dirty la borro
            if(!mapaMaterias.get(materiaASacar))
            {
                mapaMaterias.remove(materiaASacar);
                addMateria(materia);
                return;
            }
        }
        //Si esta todas modificadas tengo que elejir una
        Materia materiaASacar = mapaMaterias.keySet().iterator().next();
        //TODO Actualizarla en el dao, pero por como esta hecha la implementacion
        //hasta ahora no lo necesitamos
        mapaMaterias.remove(materiaASacar);
        addMateria(materia);
        //Y por que recursivo? porque puede ser que haya mas materias que la capacidad permite
        //si llamo al metodo materiaModified con una materia que no estaba en el pool y el pool esta lleno
        //voy a agregar una materia demas que voy a eliminar en este motodo cuando tenga que liberar espacio
        //pero como todo esto de dirty no se usa no importa
    }
    
    /**
     * Avisa que la materia se modifico y antes de sacarla del pool
     * hay que actualizar en la db. Sino se avisa cuando el pool elimine
     * la materia va a pensar que nadie la cambio y se pierden los cambios
     * @param mat la materia
     */
    public void materiaModified(Materia mat)
    {
        mapaMaterias.put(mat, true);
    }
    /**
     * Fuerza que se guarden todos los cambios en la db
     */
    public void forceWrite()
    {
        for(Materia materia:mapaMaterias.keySet())
        {
            mapaMaterias.put(materia,true);
        }
    }

	public Set<ItemExamen> getItems(String materia)
        {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param materia
	 * @return devuelve una lista de las unidades pertenecientes a la materia
	 */
	public Set<String> getUnidades(String materia)
        {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Guarda una nueva unidad en la materia seleccionada
	 * @param materiaSeleccionada
	 * @param string 
	 */
	public void setUnidad(String materiaSeleccionada, String string)
        {
		// TODO Auto-generated method stub
		
	}

	public void setNewItem(String materia, ItemExamen item)
        {
		// TODO Auto-generated method stub
		
	}
	@Deprecated
	public void generarExamen(String materia, int cantEjerciciosTeoricos,
			int cantEjerciciosPracticos, int cantPreguntasTeoricas,
			int cfantPreguntasPracticas, String[] strings,
			Calendar fecha)
        {
		// TODO Auto-generated method stub
		return;
	}

	public void addExamen(Materia materia, Examen examen) {
		// TODO Auto-generated method stub
		
	}
}
