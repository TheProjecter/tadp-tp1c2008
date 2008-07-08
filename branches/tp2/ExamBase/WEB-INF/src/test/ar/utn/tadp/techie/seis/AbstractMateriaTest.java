package test.ar.utn.tadp.techie.seis;

import main.ar.utn.tadp.techie.seis.*;


import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author xuan
 */
public abstract class AbstractMateriaTest
{
    protected Materia materia;
    protected Calendar ahora ;
    protected Set<String> unidadesAbarcadas;
    protected Pregunta pregunta;
    protected Ejercicio ejercicio;
    protected Set<ItemExamen> itemsMuyUsados;
    protected Set<ItemExamen> itemsPocoUsados;
	protected SerializacionXStream instanceXStream;
	protected Set<ItemExamen> col= new HashSet<ItemExamen>();
    @Before
    public void setUp() throws Exception
    {
        //Pregunta		pregunta;
        
    	//Creo objeto XML
    	
    	instanceXStream = new SerializacionXStream();
    	
    	XStream xstream = new XStream(new DomDriver());
    	col.add(new ADesarrollar("Geografia",3,"Cual es la Capital de Brazil?", ItemExamen.TiposItem.TEORICO));
 		col.add(new ADesarrollar("TADP",3,"Explique el concepto de reflection", ItemExamen.TiposItem.TEORICO));
 		col.add(new ADesarrollar("TADP",3,"Enuncie ventajas del uso de JUnit", ItemExamen.TiposItem.TEORICO));
 		col.add(new Ejercicio("JUNIT",1,"Aplique JUNIT en ...",ItemExamen.TiposItem.TEORICO));		
 		xstream.toXML(col,new FileOutputStream("items.xml")); 
    	
 		List<String>	opcionesChoice;

        ahora		= Calendar.getInstance();
        opcionesChoice  =  new ArrayList<String>();

        itemsMuyUsados = new HashSet<ItemExamen>();
        itemsPocoUsados = new HashSet<ItemExamen>();

        materia = new Materia("Disenio");

        // Creo lotes de prueba de Unidades Tematicas
        unidadesAbarcadas =  new HashSet<String>();

        unidadesAbarcadas.add("Patrones");
        unidadesAbarcadas.add("Ciclos de Vida");
        unidadesAbarcadas.add("Estructurado");
        unidadesAbarcadas.add("DFDTR");	

        //Esta la use muchas veces.
        pregunta = new ADesarrollar("Patrones", 75, "Por que necesitamos a los patrones en las estancias?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        for(int i = 0; i < 20; i++)
            pregunta.incrementarUso();
        itemsMuyUsados.add(pregunta);                

        //Solo dos veces
        pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        pregunta.incrementarUso();                
        itemsPocoUsados.add(pregunta);
        //Solo una
        pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        itemsPocoUsados.add(pregunta);
        //Solo tres
        pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        pregunta.incrementarUso();
        pregunta.incrementarUso();                
        itemsPocoUsados.add(pregunta);

        pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICOPRACTICO);  
        materia.addItem(pregunta);

        //3 veces
        pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        pregunta.incrementarUso();
        pregunta.incrementarUso();
        itemsPocoUsados.add(pregunta);

        opcionesChoice.add("Cascada");
        opcionesChoice.add("Espiral");
        opcionesChoice.add("Modelo");
        opcionesChoice.add("Evolutivo");
        opcionesChoice.add("Otros");

        //Muchas veces
        pregunta = new Choice("Ciclos de Vida", 40, "Que ciclo conviene utilizar cuando no se posee experiencia?", ItemExamen.TiposItem.PRACTICO, opcionesChoice);
        materia.addItem(pregunta);
        for(int i = 0; i < 20; i++)
            pregunta.incrementarUso();
        itemsMuyUsados.add(pregunta);    


        //2 veces                
        pregunta = new Choice("Ciclos de Vida", 30, "Que ciclo conviene utilizar cuando no se sabe exactamente que se busca?", ItemExamen.TiposItem.PRACTICO, opcionesChoice);
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        itemsPocoUsados.add(pregunta);

        //Ejercicios

        ejercicio = new Ejercicio("Ciclos de Vida", 50, "De un ejemplo de modelo en espiral.", ItemExamen.TiposItem.PRACTICO);
        for(int i = 0; i < 20; i++)
                ejercicio.incrementarUso();
        materia.addItem(ejercicio);  
        itemsMuyUsados.add(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Ciclos de Vida", 30, "De un ejemplo de modelo en cascada.", ItemExamen.TiposItem.PRACTICO);
        for(int i = 0; i < 20; i++)
                ejercicio.incrementarUso();
        materia.addItem(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Ciclos de Vida", 45, "De un ejemplo de modelo de prototipos.", ItemExamen.TiposItem.PRACTICO);
        for(int i = 0; i < 20; i++)
                ejercicio.incrementarUso();
        materia.addItem(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 50, "De un ejemplo de uso del patr?n Observer.", ItemExamen.TiposItem.PRACTICO);
        materia.addItem(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 65, "Ejemplo de aplicaci?n de Command.", ItemExamen.TiposItem.PRACTICO);
        materia.addItem(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 20, "ejemplo de aplicacion de Strategy.", ItemExamen.TiposItem.PRACTICO);
        materia.addItem(ejercicio);
        itemsPocoUsados.add(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 50, "De un ejemplo de uso del patr?n Observer.", ItemExamen.TiposItem.PRACTICO);
        materia.addEjercicio(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 65, "Ejemplo de aplicaci?n de Command.", ItemExamen.TiposItem.PRACTICO);
        materia.addEjercicio(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 20, "ejemplo de aplicacion de Strategy.", ItemExamen.TiposItem.PRACTICO);
        materia.addEjercicio(ejercicio);
        itemsPocoUsados.add(ejercicio);
    }

    @After
    public void tearDown() throws Exception
    {
        materia.borrarItems();
        unidadesAbarcadas.clear();
        itemsMuyUsados.clear();
        itemsPocoUsados.clear();
    }
}

