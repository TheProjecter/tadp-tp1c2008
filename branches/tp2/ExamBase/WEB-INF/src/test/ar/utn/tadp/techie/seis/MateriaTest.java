package ar.utn.tadp.techie.seis;

import static org.junit.Assert.*;

import org.junit.Test;

public class MateriaTest extends AbstractMateriaTest
{

    @Test()
    public final void testGetPreguntasDeTipo() throws Exception
    {
        assertNotNull(materia.getItems());
    }
   /* @Test()
    public final void testgetItemsXML() throws Exception
    {
    	assertEquals(col.size(),materia.getItemsXML("items.xml").size());
    }*/
    

}
