package tadp.techie.seis;

import static org.junit.Assert.*;

import java.io.FileInputStream;


import org.junit.Test;

public class MateriaTest extends AbstractMateriaTest
{

    @Test()
    public final void testGetPreguntasDeTipo() throws Exception
    {
        assertNotNull("No hay preguntas cargadas", materia.getItems());
    }

    @Test()
    public final void testgetItemsXML() throws Exception
    {
    	//TODO Crear archivo y pasarlo como parametro
    	assertNotNull(instanceXStream.itemsExamenFromXML(new FileInputStream("")));
    	
    }
}
