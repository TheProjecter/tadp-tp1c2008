package main.ar.utn.tadp.techie.seis;

import main.ar.utn.tadp.techie.seis.Ejercicio;
import main.ar.utn.tadp.techie.seis.ItemExamen;
import main.ar.utn.tadp.techie.seis.Pregunta;

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
