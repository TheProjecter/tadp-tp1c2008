package tadp.techie.seis;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.After;
import org.junit.Before;
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
