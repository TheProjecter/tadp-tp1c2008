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
		opcionesChoice  =  new ArrayList<String>();

		materia = new Materia("Disenio");

		// Creo lotes de prueba de Unidades Tematicas
		unidadesAbarcadas =  new HashSet<String>();

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("DFDTR");	

		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", Pregunta.TiposPregunta.TEORICOPRACTICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
		
		opcionesChoice.add("Cascada");
		opcionesChoice.add("Espiral");
		opcionesChoice.add("Modelo");
		opcionesChoice.add("Evolutivo");
		opcionesChoice.add("Otros");
		pregunta = new Choice("Ciclos de Vida", 40, "Que ciclo conviene utilizar cuando no se posee experiencia?", Pregunta.TiposPregunta.TEORICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Ciclos de Vida", 30, "Que ciclo conviene utilizar cuando no se sabe exactamente que se busca?", Pregunta.TiposPregunta.TEORICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposPregunta.PRACTICO);  
		materia.addPregunta(pregunta);

		
	}

	@After
	public void tearDown() throws Exception {
		materia.borrarPreguntas();
		unidadesAbarcadas.clear();
	}

	@Test()
	public final void testGetPreguntasDeTipo() throws Exception {
		
		assertNotNull("No hay preguntas cargadas", materia.getPreguntas());
	}

	@Test
	public final void testGenerarExamen() throws Exception{
	
		/*// Creo lotes de prueba de Unidades Tematicas
		unidadesAbarcadas =  new HashSet<String>();

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("DFDTR");
*/
		assertNotNull(materia.generarExamen(ahora, unidadesAbarcadas, 2, 3));
	}
	
	@Test
	public final void testNoRepitoPreguntasHastaUsarTodas() throws Exception
	{
			
		Examen ex1 = materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 2, 1);
		Examen ex2 = materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 2, 1);
	
		
		//veo que en examen2 no se incluyo ninguna de las 2 preguntas usadas en examen 1
		assertFalse("Se repitieron preguntas antes de agotar las no-usadas.", 
				ex1.getPreguntas().containsAll(ex2.getPreguntas()) );
		
	}
	
}
