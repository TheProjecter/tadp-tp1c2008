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

import java.util.ArrayList;
import java.util.List;


import tadp.techie.seis.*;


public class MateriaIntegralesTest {

	private Materia materia;
	private Calendar ahora ;
	private Set<String> unidadesAbarcadas;
		
    private Set<Pregunta> preguntasMuyUsadas = new HashSet<Pregunta>();
    private Set<Pregunta> preguntasPocoUsadas = new HashSet<Pregunta>();
	
	@Before
	public void setUp() throws Exception {
		Pregunta pregunta;
		List <String> opcionesChoice;
		unidadesAbarcadas = new HashSet<String>();

		ahora		= Calendar.getInstance();

		// Creo la materia nueva
		materia		= new Materia("Diseño");

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("Tiempo Real");


		// Cargo las perguntas de Patrones
		opcionesChoice	= new ArrayList<String>();
		opcionesChoice.add("Factory");
		opcionesChoice.add("Templathe Method");
		opcionesChoice.add("Strategy");
		opcionesChoice.add("Singleton");
		opcionesChoice.add("Observer");
		opcionesChoice.add("Ninguno de los Anteriores");


		//Cargo las Preguntas de Patrones (5 Practicos)
		pregunta = new Choice("Patrones", 30, "¿Que patron me conviene utilizar en la situacion V?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Patrones", 50, "¿Que patron me conviene utilizar en la situacion W?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Patrones", 50, "¿Que patron me conviene utilizar en la situacion X?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Patrones", 80, "¿Que patron me conviene utilizar en la situacion Y?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Patrones", 30, "¿Que patron me conviene utilizar en la situacion Z?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);


		//Cargo las Preguntas de Estructurado (2 Teoricos, 2 Practicos)
		pregunta = new ADesarrollar("Estructurado", 10, "¿Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 40, "¿Cuantos niveles de Cohesion Existe?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 75, "Indique en el diagrama los trampolines de datos", Pregunta.TiposPregunta.PRACTICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Estructurado", 75, "¿Como armo una carta estructurada a partir del DFD?", Pregunta.TiposPregunta.PRACTICO);
		materia.addPregunta(pregunta);


		// Creo el nuevo conjunto de Choices
		opcionesChoice	= new ArrayList<String>();
		opcionesChoice.add("Cascada");
		opcionesChoice.add("Espiral");
		opcionesChoice.add("Modelo");
		opcionesChoice.add("Evolutivo");
		opcionesChoice.add("Otros");

		//Cargo las Preguntas de Ciclos de Vida (3 Teoricos, 2 Practicos)
		pregunta = new Choice("Ciclos de Vida", 40, "Que ciclo conviene utilizar cuando no se posee experiencia?", Pregunta.TiposPregunta.TEORICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new Choice("Ciclos de Vida", 30, "Que ciclo conviene utilizar cuando no se sabe exactamente que se busca?", Pregunta.TiposPregunta.TEORICO, opcionesChoice);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposPregunta.PRACTICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Ciclos de Vida", 100, "De acuerdo al texto adjunto, indique que ciclo de vida conviene utilizar. Justifique", Pregunta.TiposPregunta.PRACTICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los Errores del Analista al tratar este Desarrollo comparando con el Ciclo de Vida en Espiral", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);


		//Cargo las Preguntas de DFDTR (5 Teoricos)
		pregunta = new ADesarrollar("Tiempo Real", 50, "¿Que diferencia hay entre un DFD y un DFDTR?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Tiempo Real", 30, "¿Cual es la principal aplicacion de los DFDTR?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Tiempo Real", 60, "¿Cuales son las caracteristicas de esta herramienta?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Tiempo Real", 80, "¿Que tipo de lenguaje conviene utilizar para estos desarrollos?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);
		pregunta = new ADesarrollar("Tiempo Real", 15, "¿Por que es tan importante el tiempo de respuesta en estos sistemas?", Pregunta.TiposPregunta.TEORICO);
		materia.addPregunta(pregunta);


		System.out.println(materia.getPreguntas().size());
		
	}
	@After
	public void tearDown() throws Exception {
		materia.borrarPreguntas();
		unidadesAbarcadas.clear();
	}
	
	@Test(expected = PreguntasInsuficientesException.class)
	public void testGenerarExamenOK() throws PreguntasInsuficientesException, ExamenSinPreguntasException {

		HashSet<String> colUT = new HashSet<String>();
		
		colUT.add("Estructurado");
		assertNotNull (materia.generarExamen(Calendar.getInstance(), colUT, 3, 2));
		return;
	}
	@Test
	public final void testGenerarExamenPatrones() throws Exception {
		HashSet<String> colUT = new HashSet<String>();
		
		colUT.add("Patrones");
		assertNotNull (materia.generarExamen(Calendar.getInstance(), colUT, 0, 2));
		return;
	}
	@Test(expected = Exception.class) // Definir Clase!!! 
	public final void testFalloExamenPorFecha() throws Exception 
	{
		HashSet<String> unidadesTematicas = new HashSet<String>();
		
		Calendar fechaPasada = Calendar.getInstance();
		fechaPasada.add(Calendar.YEAR, -2);

		unidadesTematicas.add("Patrones");
		materia.generarExamen(fechaPasada, unidadesTematicas, 0, 2);
		return;
	}
		@Test(expected = Exception.class) // Definir Clase!!! 
	public final void testFalloExamenPorPocasPreguntasTeoricas() throws Exception {
		HashSet<String> colUT = new HashSet<String>();
		
		colUT.add("Ciclos de Vida");
		assertNotNull( materia.generarExamen(Calendar.getInstance(), colUT, 4, 1));
		return;
	}
		@Test(expected = Exception.class) // Definir Clase!!! 
	public final void testFalloExamenPorPocasPreguntasPracticas() throws Exception {
		HashSet<String> colUT = new HashSet<String>();
		
		colUT.add("Ciclos de Vida");
		assertNotNull(materia.generarExamen(Calendar.getInstance(), colUT, 0, 8));

		return;
	}
	
	

}

