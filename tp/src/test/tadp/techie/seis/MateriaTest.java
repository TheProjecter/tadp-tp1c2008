package tadp.techie.seis;

import static org.junit.Assert.*;


import org.junit.Test;

public class MateriaTest
        extends AbstractMateriaTest
{

    @Test()
    public final void testGetPreguntasDeTipo() throws Exception
    {
        assertNotNull("No hay preguntas cargadas", materia.getItems());
    }

}
