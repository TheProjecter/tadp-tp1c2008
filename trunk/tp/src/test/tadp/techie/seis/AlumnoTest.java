package tadp.techie.seis;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tadp.techie.seis.ExamenCorregido.CalificacionPregunta;


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
	public void examenEncontradoTest() throws ExamenSinPreguntasNiEjerciciosException 
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
	public void examenNoEncontradoTest() throws ExamenSinPreguntasNiEjerciciosException 
	{
		ExamenCorregido ex = null;
		Set<Pregunta> preguntas = new HashSet<Pregunta>();
		Set<Ejercicio> ejercicios = new HashSet<Ejercicio>();
		preguntas.add(new ADesarrollar("Filosofía", 75, "El Huevo o la Gallina?", ItemExamen.TiposItem.TEORICO));
	    
		ex = alumno.consultarNotaExamen(new Examen(Calendar.getInstance(), preguntas, ejercicios ));

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
	
		pregunta = new ADesarrollar("Patrones", 75, "Por que necesitamos a los patrones en las estancias?", ItemExamen.TiposItem.TEORICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", ItemExamen.TiposItem.TEORICO);  
		materia.addPregunta(pregunta);

		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", ItemExamen.TiposItem.PRACTICO);  
		materia.addPregunta(pregunta);

		
		pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.PRACTICO);  
		materia.addPregunta(pregunta);
	            
	    
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", ItemExamen.TiposItem.PRACTICO);  
		materia.addPregunta(pregunta);


		try 
		{
			ex = materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 3, 0);
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
	public CalificacionPregunta getNotaPregunta(ItemExamen preg) {
		return ExamenCorregido.CalificacionPregunta.BIENMENOS;
	}

	
}
