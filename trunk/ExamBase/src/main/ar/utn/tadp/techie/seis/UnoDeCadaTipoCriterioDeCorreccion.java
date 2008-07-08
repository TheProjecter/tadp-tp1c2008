package ar.utn.tadp.techie.seis;

import java.util.Map;
import ar.utn.tadp.techie.seis.ExamenCorregido.CalificacionPregunta;

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
    public boolean cumple(Map<ItemExamen, CalificacionPregunta> mapaItems)
    {
        boolean teoricaBien = false;
        boolean practicaBien = false;
        
        for( ItemExamen item : mapaItems.keySet())
        {
            if(mapaItems.get(item) == CalificacionPregunta.BIEN || mapaItems.get(item) == CalificacionPregunta.BIENMENOS)
            {
                //Si es lo setea en true, sino lo deja como esta
                teoricaBien |= item.isTeorico();
                practicaBien |= item.isPractico();
            }
                
            if(teoricaBien && practicaBien)
                return true;
        }
        
        return false;
    }
    
}
