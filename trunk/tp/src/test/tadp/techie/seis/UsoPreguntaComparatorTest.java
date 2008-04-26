package tadp.techie.seis;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de Test para realizas las Pruebas Unitarias sobre la Clase UsoPreguntaComparator
 * Si las materias a comparar tienen la misma cantidad entonces se devuelve un numero
 * al azar. Solo se devuelve cero si son la misma pregunta (equals->true)
 * @return un entero positivo, cero, or positivo dependiendo de si el primer arg es menor, igual o mayor que el segundo.
 * @author xuan
 */

public class UsoPreguntaComparatorTest 
{

	static Pregunta pregunta1;
	static Pregunta pregunta2;
	static UsoPreguntaComparator comparador;

	@Before
	public void setUp() throws Exception {
	
		comparador = new UsoPreguntaComparator();

		ArrayList<String> opcionesChoice;

		// Cargo las perguntas de Patrones
		opcionesChoice	= new ArrayList<String>();
		opcionesChoice.add("Factory");
		opcionesChoice.add("Templathe Method");
		opcionesChoice.add("Strategy");
		opcionesChoice.add("Singleton");
		opcionesChoice.add("Observer");
		opcionesChoice.add("Ninguno de los Anteriores");

		pregunta1 = new Choice("Patrones", 30, "¿Que patron me conviene utilizar en la situacion V?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		pregunta2 = new ADesarrollar("Estructurado", 40, "¿Cuantos niveles de Cohesion Existe?", Pregunta.TiposPregunta.TEORICO);

	}


	@Test
	public void seleccionarPregunta1Test() 
	{
		pregunta2.incrementarUso();
		assertTrue ((comparador.compare(pregunta1, pregunta2) < 0));
	}
	
	
	@Test
	public void seleccionarPregunta2Test() 
	{
		pregunta1.incrementarUso();
		assertTrue ((comparador.compare(pregunta1, pregunta2) > 0));
	}

	
	@Test
	public void mismaPreguntaTest() 
	{
		assertTrue ((comparador.compare(pregunta1, pregunta1) ==  0));
	}
	
}
