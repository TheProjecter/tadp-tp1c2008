package tadp.techie.seis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;



import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.FileConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SerializacionXStream {

	/**
	 * @param args
	 */
	
	Set<ItemExamen> items;
	
		private void convertirXMLBasico(){//Este es el que crea el XML
						 
			 XStream xstream = new XStream(new DomDriver());
			 String xml = xstream.toXML(new ADesarrollar("Geografia",3,"Cual es la Capital de Brazil?", ItemExamen.TiposItem.TEORICO));
			 System.out.println(xml);
					try {
	            FileOutputStream fs = new FileOutputStream("Preguntas.xml");
	            xstream.toXML(xml, fs);
	     } 
		 catch (Exception e1) {
	            e1.printStackTrace();
	     }

	
	
	}
	
	
	
	public Set<ItemExamen> itemsExamenFromXML(FileInputStream file){//Este es el que levanta el XML
		 XStream xstream = new XStream(new DomDriver());
		 
		
		try{
			
			
		
			Object preguntaXML = (Object) xstream.fromXML(file);
			 
		   	 Set<ItemExamen> preguntas = (Set<ItemExamen>) preguntaXML;
		   	 
		   	 
			//Tener cuidado con el tipo de dato que va como cabecera del XML, porq es el tipo del objeto
			   	items  = preguntas;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
		return items; 
	
	}

}//xuan 47963431