/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tadp.techie.seis;

import java.util.Map;

/**
 * Este criterio de correccion determina si un examen cumple las condiciones
 * minimas de aprobacion. Estas condiciones las determina la implementecion
 * de este criterio.
 * @author xuan
 */
public interface CriterioDeCorreccion
{

    public boolean cumple(Map<ItemExamen,ExamenCorregido.RespuestaPregunta> mapaItems);
    
}
