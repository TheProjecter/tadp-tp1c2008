package tadp.techie.seis;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MateriaTest
        extends AbstractMateriaTest
{

    @Test()
    public final void testGetPreguntasDeTipo() throws Exception
    {
        assertNotNull("No hay preguntas cargadas", materia.getItems());
    }

    @Test
    public final void testGenerarExamen() throws Exception
    {
        assertNotNull(materia.generarExamen(ahora, unidadesAbarcadas, 2, 3));
    }

    @Test(expected = NullPointerException.class)
    public final void testGenerarExamenSinUnidades() throws Exception
    {

        assertNotNull(materia.generarExamen(ahora, null, 2, 3));
    }

    @Test(expected = PreguntasInsuficientesException.class)
    public final void testFalloExamenPorPocasPreguntasTeoricas() throws Exception
    {

        assertNotNull(materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 20, 1));
        return;
    }

    @Test(expected = PreguntasInsuficientesException.class)
    public final void testFalloExamenPorPocasEjerciciosPracticos() throws Exception
    {

        assertNotNull(materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 0, 8));

        return;
    }

    /**
     * Verifico que un examen se genere con las preguntas y ejercicios menos usados
     * @throws Exception
     */
    @Test
    public final void testItemsExamen() throws Exception
    {

        Examen examen = materia.generarExamen(ahora, unidadesAbarcadas, 3, 2);

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
