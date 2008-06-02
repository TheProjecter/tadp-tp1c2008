/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tadp.techie.seis;

import java.util.HashSet;
import java.util.Map;
import tadp.techie.seis.ExamenCorregido.RespuestaPregunta;

/**
 *
 * @author xuan
 */
public class UnaDeCadaUnidadCriterioDeCorreccion implements CriterioDeCorreccion
{
    /**
     * 
     * @param mapaItems
     * @return si cumple el criterio cuando hay por lo menos una pregunta bien de cada unidad
     * abarcada por las preguntas.
     */
    public boolean cumple(Map<ItemExamen, RespuestaPregunta> mapaItems)
    {
        HashSet<String> unidadesAbarcadas = new HashSet<String>();
        HashSet<String> unidadesBien = new HashSet<String>();
        
        for( ItemExamen item : mapaItems.keySet())
        {
            if(mapaItems.get(item) == RespuestaPregunta.BIEN || mapaItems.get(item) == RespuestaPregunta.BIENMENOS)
                unidadesBien.add(item.getUnidadTematica());
            
            unidadesAbarcadas.add(item.getUnidadTematica());
        }
        
        return unidadesAbarcadas.containsAll(unidadesBien);
    }
}
