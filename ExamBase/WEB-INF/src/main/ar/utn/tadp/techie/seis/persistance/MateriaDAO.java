/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.persistance;

import ar.utn.tadp.techie.seis.Materia;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author xuan
 */
public interface MateriaDAO
{

    public void addUnidadTematica(String materia, String unidad);

    public Collection<String> getMateriasNameList();

    public Set<String> listarMaterias();
    
    public Materia getMateriaByNombre(String nombre);
    
    public Collection<String> getUnidadesList(String materia);
}
