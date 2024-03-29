package tadp.techie.seis;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExamenTest {
	private Examen examen;
	private Examen examenAux; 
	private Pregunta pregunta;
	private Ejercicio ejercicio;
	private TreeSet<ItemExamen> misItems;
	//private TreeSet<Ejercicio> misEjercicios;
	private Set<String> unidadesAbarcadas;
	private Calendar now;
	@Before
	public void setUp() throws Exception {
		
		misItems = new TreeSet<ItemExamen>(new UsoItemComparator());
		//misEjercicios = new TreeSet<Ejercicio>(new UsoItemComparator());
		
		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", Pregunta.TiposItem.TEORICO);  
		misItems.add(pregunta);
		
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposItem.PRACTICO);  
		misItems.add(pregunta);
		
		ejercicio = new Ejercicio("Patrones de Dise�o",80,"Indique un ejemplo de un strategy", Ejercicio.TiposItem.TEORICO);
		misItems.add(ejercicio);
		// Creo lotes de prueba de Unidades Tematicas
		unidadesAbarcadas =  new HashSet<String>();

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("Carta Estructurada");	
		
		now = Calendar.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		unidadesAbarcadas.clear();
		misItems.clear();
	}

	@Test
	public final void testCrearExamen() throws ExamenSinPreguntasNiEjerciciosException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misItems));
	}

	@Test
	public final void testCrearExamenSinUnidadesTematicas() throws ExamenSinPreguntasNiEjerciciosException {
		//se puede crear un examen sin preguntas (ver contrato de Examen)
		assertNotNull(examen = new Examen(Calendar.getInstance(), null, misItems ));
		assertEquals(examen.getUnidades().size(),misItems.size()/*3*/);
	}
	
	@Test (expected = ExamenSinPreguntasNiEjerciciosException.class)
	public final void testCrearExamenSinPreguntas() throws ExamenSinPreguntasNiEjerciciosException {
		//Un examen sin preguntas no puede ser creado
		examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, null );
		
	}
	@Test (expected = ExamenSinPreguntasNiEjerciciosException.class)
	public final void testCrearExamenSinPreguntasYSinUnidades() throws ExamenSinPreguntasNiEjerciciosException {
		examen = new Examen(Calendar.getInstance(), null, null );
		
	}
	@Test
	public final void testCrearExamenSinFecha() throws ExamenSinPreguntasNiEjerciciosException {
		//puedo setear la fecha dsps
		examen = new Examen(null, unidadesAbarcadas, misItems);
		
	}
	@Test
	public final void testBorrarItems() throws ExamenSinPreguntasNiEjerciciosException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misItems));
		assertNotNull(examen.getItems());
		examen.borrarItems();
		assertTrue("Se deberian haber borrado todas las preguntas", examen.getItems().isEmpty());
	}
	

	@Test
	public final void testEqualsPorFechaExamen() throws ExamenSinPreguntasNiEjerciciosException {
		
		examen = new Examen(now, unidadesAbarcadas, misItems);
		
		examenAux = new Examen(now, unidadesAbarcadas, misItems);
		assertEquals("Los examenes deberian ser iguales.", examen, examenAux);
		
		try {
			//1000 millisecs -- espero un segundo para que no sean iguales las fechas
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		examenAux = new Examen( Calendar.getInstance(), unidadesAbarcadas, misItems);
		assertFalse( "Los examenes deberian deberian ser distintos.", examen.equals(examenAux) );
		
		
	}
	@Test
	public final void testEqualsPorUnidades() throws ExamenSinPreguntasNiEjerciciosException {
		
		examen = new Examen(now, unidadesAbarcadas, misItems);
		examenAux = new Examen(now, unidadesAbarcadas, misItems);
		
		assertEquals("Los examenes deberian ser iguales.", examen, examenAux);
		
		unidadesAbarcadas.add("UML");
		examenAux = new Examen( now, unidadesAbarcadas, misItems);
                
		assertFalse( "Los examenes deberian deberian ser distintos.", examen.equals(examenAux) );
		
		
	}
}
