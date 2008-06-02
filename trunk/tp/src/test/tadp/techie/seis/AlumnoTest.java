package tadp.techie.seis;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tadp.techie.seis.ExamenCorregido.RespuestaPregunta;


public class AlumnoTest implements Corrector {

	private Alumno alumno;
	private Examen examen;
	
	@Before
	public void setUp() throws Exception {
		
		alumno = new Alumno();
		examen = this.generarExamenTest();

		ExamenCorregidoBuilder ex = new ExamenCorregidoBuilder(alumno, examen, this);

		ex.corregirExamen();
		
		// Genero un segundo examen
		ex = new ExamenCorregidoBuilder(alumno, this.generarExamenTest(), this);
		ex.corregirExamen();
	}


	
		
	/**
	 * Test para verificar que me devuelve correctamente la correccion si encuentra el examen
	 * 
	 */
	@Test
	public void examenEncontradoTest() throws ExamenSinPreguntasException 
	{
		ExamenCorregido ex;
		
		ex = alumno.consultarNotaExamen(examen);

		assertEquals(ex.getExamen(),examen);
		
		return;
	}

	
	/**
	 * Test para verificar que me devuelve correctamente la correccion si encuentra el examen
	 * 
	 */
	@Test
	public void examenNoEncontradoTest() throws ExamenSinPreguntasException 
	{
		ExamenCorregido ex = null;
		Set<Pregunta> preguntas = new HashSet<Pregunta>();
		
		preguntas.add(new ADesarrollar("Filosofía", 75, "El Huevo o la Gallina?", Pregunta.TiposPregunta.TEORICO));
	    
		ex = alumno.consultarNotaExamen(new Examen(Calendar.getInstance(), preguntas ));

		assertNull(ex);
		
		return;
	}

	
	@After
	public void tearDown() throws Exception {
		alumno = null;
	}

	
	/**
	 * Metodo auxiliar del SetUp para crear el Examen de referencia
	 * 
	 * @return
	 */
	public Examen generarExamenTest() {

		Pregunta pregunta;
		Materia materia = new Materia("Disenio");
		Examen ex;

		// Creo lotes de prueba de Unidades Tematicas
		Set<String> unidadesAbarcadas =  new HashSet<String>();
	
		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("DFDTR");	
	
		pregunta = new ADesarrollar("Patrones", 75, "Por que necesitamos a los patrones en las estancias?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", Pregunta.TiposPregunta.PRACTICO);  
		materia.addPregunta(pregunta);

		
		pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.PRACTICO);  
		materia.addPregunta(pregunta);
	            
	    
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposPregunta.PRACTICO);  
		materia.addPregunta(pregunta);


		try 
		{
			ex = materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 3, 2);
		}
		catch (Exception e)
		{
			// No se controlan errores de creacion ya que se genera el examen para el Test
			return null;
		}
		
		return ex;

	}


	/**
	 * getNotaFinal() Metodo implementado para que pueda implementar la interfaz Corrector
	 * 
	 */
	public int getNotaFinal() {
		return 4;
	}

	/**
	 * getNotaPregunta() Metodo implementado para que pueda implementar la interfaz Corrector
	 * 
	 */
	public RespuestaPregunta getNotaPregunta(Pregunta preg) {
		return ExamenCorregido.RespuestaPregunta.BIENMENOS;
	}

	
}
