/**
 * 
 */
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
public class ExamenCorregidoBuilderTest {

	
	private Alumno alumno;
	private Examen examen;
	private ExamenCorregidoBuilder builder;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		alumno = new Alumno();
		examen = this.generarExamenTest();

		builder = new ExamenCorregidoBuilder(alumno, examen);
		
		return;
	}

	
	/**
	 * Test para verificar que un examen con una nota se creo correctamente
	 */
	@Test
	public void crearExamenConNotaOK() throws Exception 
	{

		builder.setNota(8);	
		// Verifico que haya creado el ExamenCorregido
		
		assertNotNull(builder.corregirExamen());
		
		// Verifico que este entre los Examenes del Alumno
		assertNotNull(alumno.consultarNotaExamen(examen));	

		return;
	}
	
	
	/**
	 * Test para verificar que un examen con la correccion de las preguntas se creo correctamente
	 */
	@Test
	public void crearExamenConRtasOK() throws Exception 
	{

		this.corregirPreguntasAll();
		
		// Verifico que haya creado el ExamenCorregido
		assertNotNull(builder.corregirExamen());
		
		// Verifico que este entre los Examenes del Alumno
		assertNotNull(alumno.consultarNotaExamen(examen));	

		return;
	}
	
	
	
	/**
	 * Test para verificar que un examen corregido sin nota ni preguntas no puede crearse
	 */
	@Test (expected = CorreccionIncompletaException.class)
	public void crearCorreccionExamenVacioKO() throws CorreccionIncompletaException 
	{
		// Verifico que haya creado el ExamenCorregido
		assertNull(builder.corregirExamen());
		
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
		builder.setNota(0);	
		return;
	}
	
	/**
	 * Test para verificar que el Builder no permite setear una nota mayor a 10
	 * @throws NotaIncorrectaException
	 */
	@Test(expected = NotaIncorrectaException.class)
	public void cargarNotaOnce() throws NotaIncorrectaException {
		builder.setNota(11);
		return;
	}

	/**
	 * Test para verificar que el Builder no tiene problemas en las notas limite
	 * 
	 */
	@Test
	public void cargarNotaDiez() throws NotaIncorrectaException 
	{
		builder.setNota(10);
		assertTrue(builder.getNota() == 10);	
		
		return;
	}
	

	/**
	 * Test para verificar que el Builder no tiene problemas en las notas limite
	 */
	@Test
	public void cargarNotaUno() throws NotaIncorrectaException
	{
		builder.setNota(1);
		assertTrue(builder.getNota() == 1);	
		
		return;
	}

	/**
	 * Test para verificar que el Builder no tiene problemas en las notas limite
	 */
	@Test(expected = PreguntaNoEstaEnExamenException.class)
	public void cargarPreguntaNoExistente() throws Exception
	{
		Pregunta preg = new ADesarrollar("Filosofía", 75, "El Huevo o la Gallina?", Pregunta.TiposPregunta.TEORICO);  
		builder.addCorreccionPregunta(preg, ExamenCorregido.RespuestaPregunta.BIEN);

		return;
	}


	@Test(expected = PreguntaCargadaException.class)
	public void cargarPreguntaCargadaAntes() throws Exception
	{
		this.corregirPreguntasAll();

		// Intento cargar todas las preguntas de vuelta
		for(Pregunta preg : examen.getPreguntas())
        {
			builder.addCorreccionPregunta(preg, ExamenCorregido.RespuestaPregunta.REGULAR);
        }
		
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
	 * Metodo auxiliar para dar todas las preguntas del examen corregidas como Bien-
	 * @throws Exception
	 */
	public void corregirPreguntasAll () throws Exception
	{
		for(Pregunta preg : examen.getPreguntas())
			builder.addCorreccionPregunta(preg, ExamenCorregido.RespuestaPregunta.BIENMENOS);

	}
	

}
