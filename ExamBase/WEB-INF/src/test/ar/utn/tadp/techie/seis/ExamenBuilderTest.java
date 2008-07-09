package ar.utn.tadp.techie.seis;


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


    /**
     * testGenerarExamenItems
     * Verifica que, en caso de pretender generar el examen sin preguntas,
     * arroje la excepcion: ExamenSinPreguntasNiEjerciciosException
     * 
     * @throws Exception
     */
    @Test(expected = ExamenSinPreguntasNiEjerciciosException.class)
    public final void testGenerarExamenItems() throws Exception
    {
        ExamenBuilder builder = new ExamenBuilder(materia,unidadesAbarcadas,ahora);
        builder.generarExamen();   
    }

    /**
     * testFalloExamenPorPocosItems
     * 
     * Verifica que, en caso de no tener preguntas suficientes, arroje
     * la excepcion: PreguntasInsuficientesException 
     */
    @Test(expected = PreguntasInsuficientesException.class)
    public final void testFalloExamenPorPocosItems() throws Exception
    {
        //Prototipo de una ejercicio practico
        PrototipoItem<Ejercicio> protoEjercicioPractico = new PrototipoItem<Ejercicio>(Ejercicio.class);
        protoEjercicioPractico.setTipo(ItemExamen.TiposItem.PRACTICO);
        ExamenBuilder builder = new ExamenBuilder(materia,unidadesAbarcadas,ahora);
        
        builder.putPrototipo(protoEjercicioPractico, 20);
        
        assertNotNull(builder.generarExamen());
    }

}
