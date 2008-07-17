/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.pools;

import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Materia;
import ar.utn.tadp.techie.seis.persistance.MateriaDAO;
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
    public Materia getMateria(String nombre) {
        return null;
    }
    /**
     * Avisa que la materia se modifico y antes de sacarla del pool
     * hay que actualizar en la db. Sino se avisa cuando el pool elimine
     * la materia va a pensar que nadie la cambio y se pierden los cambios
     * @param mat la materia
     */
    public void materiaModified(Materia mat) {
        
    }
    /**
     * Fuerza que se guarden todos los cambios en la db
     */
    public void forceWrite() {
        
    }

	public Set<ItemExamen> getItems(String materia) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param materia
	 * @return devuelve una lista de las unidades pertenecientes a la materia
	 */
	public String[] getUnidades(String materia) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Guarda una nueva unidad en la materia seleccionada
	 * @param materiaSeleccionada
	 */
	public void setUnidad(String materiaSeleccionada) {
		// TODO Auto-generated method stub
		
	}
}
