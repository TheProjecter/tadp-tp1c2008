package tadp.techie.seis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MateriaTest {

	private Materia materia;
	private Calendar ahora ;
	private Set<String> unidadesAbarcadas;
	private Pregunta pregunta;
	private Ejercicio ejercicio;
    private Set<ItemExamen> itemsMuyUsados;
    private Set<ItemExamen> itemsPocoUsados;
	
	@Before
	public void setUp() throws Exception {
		//Pregunta		pregunta;
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
	public void tearDown() throws Exception {
		materia.borrarItems();
		unidadesAbarcadas.clear();
		itemsMuyUsados.clear();
		itemsPocoUsados.clear();
	}

	@Test()
	public final void testGetPreguntasDeTipo() throws Exception {
		
		assertNotNull("No hay preguntas cargadas", materia.getItems());
	}

	@Test
	public final void testGenerarExamen() throws Exception{
	
		assertNotNull(materia.generarExamen(ahora, unidadesAbarcadas, 2, 3));
	}
	@Test(expected = NullPointerException.class)
	public final void testGenerarExamenSinUnidades() throws Exception{
	
		assertNotNull(materia.generarExamen(ahora, null, 2, 3));
	}
    
    @Test(expected = PreguntasInsuficientesException.class)  
	public final void testFalloExamenPorPocasPreguntasTeoricas() throws Exception {
		
		assertNotNull( materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 20, 1));
		return;
	}
		@Test(expected = PreguntasInsuficientesException.class)  
	public final void testFalloExamenPorPocasEjerciciosPracticos() throws Exception {
		
		assertNotNull( materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 0, 8));

		return;
	}
	 /**
	  * Verifico que un examen se genere con las preguntas y ejercicios menos usados
	  * @throws Exception
	  */   
	@Test
	public final void testItemsExamen() throws Exception{
	
       Examen examen =  materia.generarExamen(ahora, unidadesAbarcadas, 3, 2);    
		
		Set<ItemExamen> itemsExamen = new HashSet<ItemExamen>();
		
		itemsExamen.addAll(	examen.getItems());
		
            
            assertEquals( itemsExamen.size(), 5);
           
            for( ItemExamen item : itemsExamen)
            {
                assertTrue( itemsPocoUsados.contains(item));
            }
            for( ItemExamen item : itemsExamen)
            {
                assertFalse( itemsMuyUsados.contains(item));
            }

            
            
	}

}
