package ar.utn.frba.tadp.techie.seis.exambase;
import java.util.Comparator;
import java.util.Random;

/**
 * Comparador para comparar preguntas por cantidad de examenes en los que fue tomada
 * Si las materias a comparar tienen la misma cantidad entonces se devuelve un numero
 * al azar. Solo se devuelve cero si son la misma pregunta (equals->true)
 * @author xuan
 */

public class UsoPreguntaComparator implements Comparator<Pregunta>
{

    public int compare(Pregunta pregunta1, Pregunta pregunta2)
    {
        //Si son la misma retorno 0
        if(pregunta1.equals(pregunta2))
            return 0;
        //Si no son la misma pero tienen el mismo uso
        //retorno un entero aleatorio no cero
        if(pregunta1.getCantidadDeVecesQueSeUso() == pregunta2.getCantidadDeVecesQueSeUso())
        {
            Random rnd = new Random(System.currentTimeMillis());
            int n;
            //Si da cero, que saque otro numero
            do n = rnd.nextInt();
            while(n == 0);
                
            return n;
        }
        
        //Si tienen distinto uso
        return pregunta1.getCantidadDeVecesQueSeUso() - pregunta2.getCantidadDeVecesQueSeUso();
    }
}

