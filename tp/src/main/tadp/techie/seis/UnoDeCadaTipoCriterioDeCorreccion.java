/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tadp.techie.seis;

import java.util.Map;
import tadp.techie.seis.ExamenCorregido.RespuestaPregunta;

/**
 *
 * @author xuan
 */
public class UnoDeCadaTipoCriterioDeCorreccion implements CriterioDeCorreccion
{
    /**
     * 
     * @param mapaItems
     * @return Devuelve que cumple cuando hay al menos una pregunta bien de cada tipo
     * (Teorico y Practico). Las TeoricoPracticas cuantan para las dos. Asi que con que haya
     * solo una teorico practica alcanza para que se cumpla el criterio
     */
    public boolean cumple(Map<ItemExamen, RespuestaPregunta> mapaItems)
    {
        boolean teoricaBien = false;
        boolean practicaBien = false;
        
        for( ItemExamen item : mapaItems.keySet())
        {
            if(mapaItems.get(item) == RespuestaPregunta.BIEN || mapaItems.get(item) == RespuestaPregunta.BIENMENOS)
            {
                if(item.getTipo() == ItemExamen.TiposItem.TEORICOPRACTICO || item.getTipo() == ItemExamen.TiposItem.TEORICO)
                    teoricaBien = true;
                if(item.getTipo() == ItemExamen.TiposItem.TEORICOPRACTICO || item.getTipo() == ItemExamen.TiposItem.PRACTICO)
                    practicaBien = true;
            }
                
            if(teoricaBien && practicaBien)
                return true;
        }
        
        return false;
    }

}
