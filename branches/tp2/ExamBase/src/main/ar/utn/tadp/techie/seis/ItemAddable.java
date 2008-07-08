package ar.utn.tadp.techie.seis;

import ar.utn.tadp.techie.seis.Ejercicio;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Pregunta;

/**
*
* @author xuan
*/
public interface ItemAddable
{
   public void addItem(ItemExamen item);
   public void addPregunta(Pregunta pregunta);
   public void addEjercicio(Ejercicio ejercicio);
}
