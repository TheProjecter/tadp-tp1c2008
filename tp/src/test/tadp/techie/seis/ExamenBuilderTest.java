package tadp.techie.seis;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static org.junit.Assert.*;



public class ExamenBuilderTest extends AbstractMateriaTest
{

    /**
     * Verifico que un examen se genere con las preguntas y ejercicios menos usados
     * @throws Exception
     */
    @Test
    public final void testItemsExamen() throws Exception
    {
        //Prototipo de una ejercicio practico
        PrototipoItem<Ejercicio> protoEjercicioPractico = new PrototipoItem<Ejercicio>(Ejercicio.class);
        protoEjercicioPractico.setTipo(ItemExamen.TiposItem.PRACTICO);
        //Prototipo de una pregunta teorica
        PrototipoItem<Pregunta> protoPreguntaTeorica = new PrototipoItem<Pregunta>(Pregunta.class);
        protoPreguntaTeorica.setTipo(ItemExamen.TiposItem.TEORICO);
        
        ExamenBuilder builder = new ExamenBuilder(materia,unidadesAbarcadas,ahora);
        
        builder.putPrototipo(protoEjercicioPractico, 2);
        builder.putPrototipo(protoPreguntaTeorica, 3);
        
        Examen examen = builder.generarExamen();
        
//        Examen examen = materia.generarExamen(ahora, unidadesAbarcadas, 3, 2);

        Set<ItemExamen> itemsExamen = new HashSet<ItemExamen>();

        itemsExamen.addAll(examen.getItems());


        assertEquals(itemsExamen.size(), 5);

        for(ItemExamen item : itemsExamen)
        {
            assertTrue(itemsPocoUsados.contains(item));
        }
        for(ItemExamen item : itemsExamen)
        {
            assertFalse(itemsMuyUsados.contains(item));
        }



    }
    
	

}
