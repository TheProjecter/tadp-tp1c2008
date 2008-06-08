package tadp.techie.seis;

import java.io.FileInputStream;

import org.junit.Test;

import static org.junit.Assert.*;


public class SerializacionXStreamTest extends AbstractMateriaTest{
	
	
	@Test()
	public void testItemsExamenFromXML(FileInputStream file)
	{
		assertNotNull(instanceXStream.itemsExamenFromXML(file));
	}
	
	

}
