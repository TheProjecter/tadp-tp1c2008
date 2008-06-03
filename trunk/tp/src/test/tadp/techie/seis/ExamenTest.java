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
	private TreeSet<Pregunta> misPreguntas;
	private TreeSet<Ejercicio> misEjercicios;
	private Set<String> unidadesAbarcadas;
	private Calendar now;
	@Before
	public void setUp() throws Exception {
		
		misPreguntas = new TreeSet<Pregunta>(new UsoItemComparator());
		misEjercicios = new TreeSet<Ejercicio>(new UsoItemComparator());
		
		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", Pregunta.TiposItem.TEORICO);  
		misPreguntas.add(pregunta);
		
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposItem.PRACTICO);  
		misPreguntas.add(pregunta);
		
		ejercicio = new Ejercicio("Patrones de Diseño",80,"Indique un ejemplo de un strategy", Ejercicio.TiposItem.TEORICO);
		misEjercicios.add(ejercicio);
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
		misPreguntas.clear();
		misEjercicios.clear();
	}

	@Test
	public final void testCrearExamen() throws ExamenSinPreguntasNiEjerciciosException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misPreguntas , misEjercicios));
	}

	@Test
	public final void testCrearExamenSinUnidadesTematicas() throws ExamenSinPreguntasNiEjerciciosException {
		//se puede crear un examen sin preguntas (ver contrato de Examen)
		assertNotNull(examen = new Examen(Calendar.getInstance(), null, misPreguntas, misEjercicios ));
		assertEquals(examen.getUnidades().size(), misPreguntas.size() + misEjercicios.size()/*3*/);
	}
	
	@Test (expected = ExamenSinPreguntasNiEjerciciosException.class)
	public final void testCrearExamenSinPreguntas() throws ExamenSinPreguntasNiEjerciciosException {
		//Un examen sin preguntas no puede ser creado
		examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, null , null);
		
	}
	@Test (expected = ExamenSinPreguntasNiEjerciciosException.class)
	public final void testCrearExamenSinPreguntasYSinUnidades() throws ExamenSinPreguntasNiEjerciciosException {
		examen = new Examen(Calendar.getInstance(), null, null );
		
	}
	@Test
	public final void testCrearExamenSinFecha() throws ExamenSinPreguntasNiEjerciciosException {
		//puedo setear la fecha dsps
		examen = new Examen(null, unidadesAbarcadas, misPreguntas , misEjercicios);
		
	}
	@Test
	public final void testBorrarPreguntas() throws ExamenSinPreguntasNiEjerciciosException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misPreguntas , misEjercicios));
		assertNotNull(examen.getPreguntas());
		examen.borrarPreguntas();
		assertTrue("Se deberian haber borrado todas las preguntas", examen.getPreguntas().isEmpty());
	}
	

	@Test
	public final void testEqualsPorFechaExamen() throws ExamenSinPreguntasNiEjerciciosException {
		
		examen = new Examen(now, unidadesAbarcadas, misPreguntas , misEjercicios);
		
		examenAux = new Examen(now, unidadesAbarcadas, misPreguntas , misEjercicios);
		assertEquals("Los examenes deberian ser iguales.", examen, examenAux);
		
		try {
			//1000 millisecs -- espero un segundo para que no sean iguales las fechas
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		examenAux = new Examen( Calendar.getInstance(), unidadesAbarcadas, misPreguntas , misEjercicios);
		assertFalse( "Los examenes deberian deberian ser distintos.", examen.equals(examenAux) );
		
		
	}
	@Test
	public final void testEqualsPorUnidades() throws ExamenSinPreguntasNiEjerciciosException {
		
		examen = new Examen(now, unidadesAbarcadas, misPreguntas , misEjercicios);
		examenAux = new Examen(now, unidadesAbarcadas, misPreguntas , misEjercicios);
		
		assertEquals("Los examenes deberian ser iguales.", examen, examenAux);
		
		unidadesAbarcadas.add("UML");
		examenAux = new Examen( now, unidadesAbarcadas, misPreguntas , misEjercicios);
		
		assertFalse( "Los examenes deberian deberian ser distintos.", examen.equals(examenAux) );
		
		
	}
}
