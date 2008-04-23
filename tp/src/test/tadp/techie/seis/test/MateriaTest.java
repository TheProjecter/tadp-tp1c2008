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
		
		materia = new Materia("Diseño");
		
		ahora = Calendar.getInstance();
		
		unidadesAbarcadas =  new HashSet<String>();
		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Estructurado");
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
		assertNotNull(materia.generarExamen(ahora, unidadesAbarcadas, 2, 3));
	}
	
}

