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
	private Pregunta pregunta;
	private TreeSet<Pregunta> misPreguntas;
	private Set<String> unidadesAbarcadas;
	
	@Before
	public void setUp() throws Exception {
		
		misPreguntas = new TreeSet<Pregunta>(new UsoPreguntaComparator());
		
		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", Pregunta.TiposPregunta.TEORICO);  
		misPreguntas.add(pregunta);
		
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposPregunta.PRACTICO);  
		misPreguntas.add(pregunta);
		
		// Creo lotes de prueba de Unidades Tematicas
		unidadesAbarcadas =  new HashSet<String>();

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("Carta Estructurada");	

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCrearExamen() throws ExamenSinPreguntasException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misPreguntas ));
	}

	@Test
	public final void testCrearExamenSinUnidadesTematicas() throws ExamenSinPreguntasException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), null, misPreguntas ));
		assertEquals(examen.getUnidades().size(), misPreguntas.size()/*2*/);
	}
	
	@Test (expected = ExamenSinPreguntasException.class)
	public final void testCrearExamenSinPreguntas() throws ExamenSinPreguntasException {
		//Un examen sin preguntas no tiene sentido
		examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, null );
		
	}
	@Test (expected = ExamenSinPreguntasException.class)
	public final void testCrearExamenSinPreguntasYSinUnidades() throws ExamenSinPreguntasException {
		examen = new Examen(Calendar.getInstance(), null, null );
		
	}
	@Test
	public final void testCrearExamenSinFecha() throws ExamenSinPreguntasException {
		//puedo setear la fecha dsps
		examen = new Examen(null, unidadesAbarcadas, misPreguntas );
		
	}
	@Test
	public final void testBorrarPreguntas() throws ExamenSinPreguntasException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misPreguntas ));
		assertNotNull(examen.getPreguntas());
		examen.borrarPreguntas();
		assertTrue("Se deberian haber borrado todas las preguntas", examen.getPreguntas().isEmpty());
	}
	

	@Test
	public final void testEqualsExamen() {
		fail("Not yet implemented"); // TODO
		
	}

}
