package tadp.techie.seis.test;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tadp.techie.seis.Materia;



public class MateriaTest {

	private Materia materia;
	private Calendar ahora ;
	private Set<String> unidadesAbarcadas;
	
	@Before
	public void setUp() throws Exception {
		Pregunta		pregunta;
		List<String>	opcionesChoice;
		
		ahora		= Calendar.getInstance();
		opcionesChoice  =  new HashSet<String>();

		materia = new Materia("Diseño");

/*
 * 
 * 		unidadesAbarcadas.add("Patrones");
 *		unidadesAbarcadas.add("Ciclos de Vida");
 *		unidadesAbarcadas.add("Estructurado");
 *		unidadesAbarcadas.add("DFDTR");
 *
 */	

		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", TEORICO);  
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", TEORICO);  
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", TEORICOPRACTICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", TEORICO);  
		materia.addPregunta(pregunta);
		
		opcionesChoice.add("Cascada");
		opcionesChoice.add("Espiral");
		opcionesChoice.add("Modelo");
		opcionesChoice.add("Evolutivo");
		opcionesChoice.add("Otros");
		pregunta = new Choice("Ciclos de Vida", 40, "Que ciclo conviene utilizar cuando no se posee experiencia?", TEORICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Ciclos de Vida", 30, "Que ciclo conviene utilizar cuando no se sabe exactamente que se busca?", TEORICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", PRACTICO);  
		materia.addPregunta(pregunta);


	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test()
	public final void testGetPreguntasDeTipo() throws Exception {
		//fail("Not yet implemented"); // TODO
		assertNotNull("No hay preguntas cargadas", materia.getPreguntas());
	}

	@Test
	public final void testGenerarExamen() throws Exception{
		// Creo lotes de prueba de Unidades Tematicas
		unidadesAbarcadas =  new HashSet<String>();

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("DFDTR");

		assertNotNull(materia.generarExamen(ahora, unidadesAbarcadas, 2, 3));
	}
	
}

