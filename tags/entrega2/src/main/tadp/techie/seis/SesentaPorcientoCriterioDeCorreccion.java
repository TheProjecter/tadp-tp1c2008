/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tadp.techie.seis;

import java.util.Map;
import tadp.techie.seis.ExamenCorregido.CalificacionPregunta;

/**
 *  El criterio se cumple cuando la cantidad de items bien/bien- es >= al 60%
 * @author xuan
 */
public class SesentaPorcientoCriterioDeCorreccion implements CriterioDeCorreccion
{
    /**
     * 
     * @param mapaItems
     * @return Devueve si cumple el criterio si la cantidad de preguntas bien o bien-
     * es mayor o igual al 60%
     */
    public boolean cumple(Map<ItemExamen, CalificacionPregunta> mapaItems)
    {
        int preguntasBien = 0;
        
        for( CalificacionPregunta rta : mapaItems.values())
        {
            if(rta == CalificacionPregunta.BIEN || rta == CalificacionPregunta.BIENMENOS)
                preguntasBien ++;
            if(preguntasBien >= 0.6*(float)mapaItems.size() )
                return true;
        }
        return false;
    }

}
