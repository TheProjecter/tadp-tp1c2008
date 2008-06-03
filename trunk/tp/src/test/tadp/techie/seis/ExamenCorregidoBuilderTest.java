package tadp.techie.seis;


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase para Testear la Correccion de un Examen
 * 
 * @author Nico
 *
 */
public class ExamenCorregidoBuilderTest implements Corrector {

	private Alumno alumno;
	private Examen examen;
	private ExamenCorregidoBuilder builder;
	private int notaExamen;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		alumno = new Alumno();
		examen = this.generarExamenTest();

		builder = new ExamenCorregidoBuilder(alumno, examen, this);
		
		notaExamen = 6;
		//builder.addCriterioCorreccion(Criterio.getInstance);

		return;
	}

	
	/**
	 * Test para verificar que un examen con una nota se creo correctamente
	 */
	@Test
	public void crearExamenConNotaOK() throws Exception 
	{
		// Verifico que haya creado el ExamenCorregido
		assertNotNull(builder.corregirExamen());

		// Verifico que este entre los Examenes del Alumno
		assertNotNull(alumno.consultarNotaExamen(examen));	

		return;
	}



	/**
	 * Test para verificar que el Builder no permite setear una nota = 0
	 * @throws NotaIncorrectaException
	 */
	@Test(expected = NotaIncorrectaException.class)
	public void cargarNotaCero() throws NotaIncorrectaException {
		notaExamen = 0;
		builder.corregirExamen();	
		return;
	}


	/**
	 * Test para verificar que el Builder no permite setear una nota mayor a 10
	 * @throws NotaIncorrectaException
	 */
	@Test(expected = NotaIncorrectaException.class)
	public void cargarNotaOnce() throws NotaIncorrectaException {
		notaExamen = 11;
		builder.corregirExamen();	
		return;
	}

	/**
	 * Test para verificar que el Builder no tiene problemas en las notas limite
	 * 
	 */
	@Test
	public void cargarNotaDiez() throws NotaIncorrectaException 
	{
		notaExamen = 10;
		builder.corregirExamen();	
		return;
	}
	

	/**
	 * Test para verificar que el Builder no tiene problemas en las notas limite
	 */
	@Test
	public void cargarNotaUno() throws NotaIncorrectaException
	{
		notaExamen = 1;
		builder.corregirExamen();	
		return;
	}


	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		alumno	= null;
		builder	= null;
		examen	= null;
		return;
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
	 * Metodo auxiliar para dar todas las preguntas del examen corregidas como Bien-
	 * @throws Exception
	 */
/*
	public void corregirPreguntasAll () throws Exception
	{
		for(Pregunta preg : examen.getPreguntas())
			builder.addCorreccionPregunta(preg, ExamenCorregido.RespuestaPregunta.BIENMENOS);

	}
*/

	/**
	 * Implementacion de la interfaz Corrector, para que devuelva la correccion
	 * de una pregunta especifica 
	 * 
	 */
	public ExamenCorregido.RespuestaPregunta getNotaPregunta(ItemExamen preg)
	{
		return ExamenCorregido.RespuestaPregunta.BIENMENOS;
	}
	
	
	/**
	 * Implementacion de la interfaz Corrector, para que devuelva 
	 * la nota final de un examen
	 * 
	 */
	public int getNotaFinal()
	{
		return notaExamen;
	}
}
