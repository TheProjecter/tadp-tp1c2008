package ar.utn.tadp.techie.seis;




import ar.utn.tadp.techie.seis.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.utn.tadp.techie.seis.ExamenCorregido.CalificacionPregunta;
import ar.utn.tadp.techie.seis.ItemExamen.TiposItem;


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
		Set<ItemExamen> items = new HashSet<ItemExamen>();
		
		items.add(new ADesarrollar("Filosofía", 75, "El Huevo o la Gallina?", ItemExamen.TiposItem.TEORICO));
	    
		ex = alumno.consultarNotaExamen(new Examen(Calendar.getInstance(), items));

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
		materia.addItem(pregunta);

		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICO);  
		materia.addItem(pregunta);

		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", ItemExamen.TiposItem.TEORICO);  
		materia.addItem(pregunta);

		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", ItemExamen.TiposItem.PRACTICO);  
		materia.addItem(pregunta);

		
		pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.PRACTICO);  
		materia.addItem(pregunta);
	            
	    
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", ItemExamen.TiposItem.PRACTICO);  
		materia.addItem(pregunta);

		PrototipoItem<Pregunta> proto = new PrototipoItem<Pregunta>(Pregunta.class);
		proto.setTipo(TiposItem.TEORICO);
		ExamenBuilder builder = new ExamenBuilder(materia,unidadesAbarcadas,Calendar.getInstance());
		builder.putPrototipo(proto, 3);
		
		try 
		{
			ex = builder.generarExamen();
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

