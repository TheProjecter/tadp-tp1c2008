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
	private TreeSet<Pregunta> misPreguntas;
	private Set<String> unidadesAbarcadas;
	private Calendar now;
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
		
		now = Calendar.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		unidadesAbarcadas.clear();
		misPreguntas.clear();
	}

	@Test
	public final void testCrearExamen() throws ExamenSinPreguntasException {
		assertNotNull(examen = new Examen(Calendar.getInstance(), unidadesAbarcadas, misPreguntas ));
	}

	@Test
	public final void testCrearExamenSinUnidadesTematicas() throws ExamenSinPreguntasException {
		//se puede crear un examen sin preguntas (ver contrato de Examen)
		assertNotNull(examen = new Examen(Calendar.getInstance(), null, misPreguntas ));
		assertEquals(examen.getUnidades().size(), misPreguntas.size()/*2*/);
	}
	
	@Test (expected = ExamenSinPreguntasException.class)
	public final void testCrearExamenSinPreguntas() throws ExamenSinPreguntasException {
		//Un examen sin preguntas no puede ser creado
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
	public final void testEqualsPorFechaExamen() throws ExamenSinPreguntasException {
		
		examen = new Examen(now, unidadesAbarcadas, misPreguntas );
		
		examenAux = new Examen(now, unidadesAbarcadas, misPreguntas );
		assertEquals("Los examenes deberian ser iguales.", examen, examenAux);
		
		try {
			//1000 millisecs -- espero un segundo para que no sean iguales las fechas
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		examenAux = new Examen( Calendar.getInstance(), unidadesAbarcadas, misPreguntas );
		assertFalse( "Los examenes deberian deberian ser distintos.", examen.equals(examenAux) );
		
		
	}
	@Test
	public final void testEqualsPorUnidades() throws ExamenSinPreguntasException {
		
		examen = new Examen(now, unidadesAbarcadas, misPreguntas );
		examenAux = new Examen(now, unidadesAbarcadas, misPreguntas );
		
		assertEquals("Los examenes deberian ser iguales.", examen, examenAux);
		
		unidadesAbarcadas.add("UML");
		examenAux = new Examen( now, unidadesAbarcadas, misPreguntas );
		
		assertFalse( "Los examenes deberian deberian ser distintos.", examen.equals(examenAux) );
		
		
	}
}
