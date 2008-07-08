package main.ar.utn.tadp.techie.seis;


import java.util.HashSet;
import java.util.Map;
import  main.ar.utn.tadp.techie.seis.ExamenCorregido.CalificacionPregunta;

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
    public boolean cumple(Map<ItemExamen, CalificacionPregunta> mapaItems)
    {
        HashSet<String> unidadesAbarcadas = new HashSet<String>();
        HashSet<String> unidadesBien = new HashSet<String>();
        
        for( ItemExamen item : mapaItems.keySet())
        {
            if(mapaItems.get(item) == CalificacionPregunta.BIEN || mapaItems.get(item) == CalificacionPregunta.BIENMENOS)
                unidadesBien.add(item.getUnidadTematica());
            
            unidadesAbarcadas.add(item.getUnidadTematica());
        }
        
        return unidadesBien.containsAll(unidadesAbarcadas);
    }
}

