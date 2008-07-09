package ar.utn.tadp.techie.seis;


import java.util.Comparator;
import java.util.Random;

/**
 * Comparador para comparar items(preguntas o ejercicios) por cantidad de examenes en los que fue tomada
 * Si las materias a comparar tienen la misma cantidad entonces se devuelve un numero
 * al azar. Solo se devuelve cero si son la misma pregunta (equals->true)
 * @return un entero positivo, cero, or positivo dependiendo de si el primer arg es menor, igual o mayor que el segundo.
 * @author xuan
 */

public class UsoItemComparator implements Comparator<ItemExamen>
{

    public int compare(ItemExamen item1, ItemExamen item2)
    {
        //Si son la misma retorno 0
        if(item1.equals(item2))
            return 0;
        //Si no son la misma pero tienen el mismo uso
        //retorno un entero aleatorio no cero
        if(item1.getCantidadDeVecesQueSeUso() == item2.getCantidadDeVecesQueSeUso())
        {
            Random rnd = new Random(System.currentTimeMillis());
            int n;
            //Si da cero, que saque otro numero
            do n = rnd.nextInt();
            while(n == 0);
                
            return n;
        }
        
        //Si tienen distinto uso
        return item1.getCantidadDeVecesQueSeUso() - item2.getCantidadDeVecesQueSeUso();
    }
}

