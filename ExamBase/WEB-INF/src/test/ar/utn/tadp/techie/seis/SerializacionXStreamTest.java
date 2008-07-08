package ar.utn.tadp.techie.seis;



import java.io.FileInputStream;
import org.junit.Test;

import static org.junit.Assert.*;


public class SerializacionXStreamTest extends AbstractMateriaTest
{
	
	@Test()
	/*
	 * Verifico que el file preguntas.xml creado se guardo
	 */
	public void testItemsExamenFromXML()throws Exception 
	{
		FileInputStream in = new FileInputStream("items.xml");
		assertEquals(col,instanceXStream.itemsExamenFromXML(in));
		in.close();
		
	}
	//Object preguntaXML = (Object) xstream.fromXML(fs);
	//assertNotNull(preguntaXML);
	//assertNotNull(instanceXStream.itemsExamenFromXML(file));
	
}
