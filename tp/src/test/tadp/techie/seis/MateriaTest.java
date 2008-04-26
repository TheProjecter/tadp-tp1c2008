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




public class MateriaTest {

	private Materia materia;
	private Calendar ahora ;
	private Set<String> unidadesAbarcadas;
	private Pregunta pregunta;
    private Set<Pregunta> preguntasMuyUsadas;
    private Set<Pregunta> preguntasPocoUsadas;
	
	@Before
	public void setUp() throws Exception {
		//Pregunta		pregunta;
		List<String>	opcionesChoice;
		
		ahora		= Calendar.getInstance();
		opcionesChoice  =  new ArrayList<String>();

		preguntasMuyUsadas = new HashSet<Pregunta>();
		preguntasPocoUsadas = new HashSet<Pregunta>();
		
		materia = new Materia("Disenio");

		// Creo lotes de prueba de Unidades Tematicas
		unidadesAbarcadas =  new HashSet<String>();

		unidadesAbarcadas.add("Patrones");
		unidadesAbarcadas.add("Ciclos de Vida");
		unidadesAbarcadas.add("Estructurado");
		unidadesAbarcadas.add("DFDTR");	

                //Esta la use muchas veces.
		pregunta = new ADesarrollar("Patrones", 75, "Por que necesitamos a los patrones en las estancias?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
                for(int i = 0; i < 20; i++)
                    pregunta.incrementarUso();
                preguntasMuyUsadas.add(pregunta);                
                
                //Solo dos veces
		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
                pregunta.incrementarUso();
                pregunta.incrementarUso();                
                preguntasPocoUsadas.add(pregunta);
                //Solo una
		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
                pregunta.incrementarUso();
                preguntasPocoUsadas.add(pregunta);
                //Solo tres
		pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", Pregunta.TiposPregunta.TEORICO);  
		materia.addPregunta(pregunta);
                pregunta.incrementarUso();
                pregunta.incrementarUso();
                pregunta.incrementarUso();                
                preguntasPocoUsadas.add(pregunta);

		pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", Pregunta.TiposPregunta.TEORICOPRACTICO);  
		materia.addPregunta(pregunta);
                
                //3 veces
		pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", Pregunta.TiposPregunta.PRACTICO);  
		materia.addPregunta(pregunta);
                pregunta.incrementarUso();
                pregunta.incrementarUso();
                pregunta.incrementarUso();
		preguntasPocoUsadas.add(pregunta);
                
		opcionesChoice.add("Cascada");
		opcionesChoice.add("Espiral");
		opcionesChoice.add("Modelo");
		opcionesChoice.add("Evolutivo");
		opcionesChoice.add("Otros");
                
                //Muchas veces
		pregunta = new Choice("Ciclos de Vida", 40, "Que ciclo conviene utilizar cuando no se posee experiencia?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);
                for(int i = 0; i < 20; i++)
                    pregunta.incrementarUso();
                preguntasMuyUsadas.add(pregunta);    
                
                
		//2 veces                
		pregunta = new Choice("Ciclos de Vida", 30, "Que ciclo conviene utilizar cuando no se sabe exactamente que se busca?", Pregunta.TiposPregunta.PRACTICO, opcionesChoice);
		materia.addPregunta(pregunta);
                pregunta.incrementarUso();
                preguntasPocoUsadas.add(pregunta);
	}

	@After
	public void tearDown() throws Exception {
		materia.borrarPreguntas();
		unidadesAbarcadas.clear();
		preguntasMuyUsadas.clear();
		preguntasPocoUsadas.clear();
	}

	@Test()
	public final void testGetPreguntasDeTipo() throws Exception {
		
		assertNotNull("No hay preguntas cargadas", materia.getPreguntas());
	}

	@Test
	public final void testGenerarExamen() throws Exception{
	
		assertNotNull(materia.generarExamen(ahora, unidadesAbarcadas, 2, 3));
	}
	@Test(expected = NullPointerException.class)
	public final void testGenerarExamenSinUnidades() throws Exception{
	
		assertNotNull(materia.generarExamen(ahora, null, 2, 3));
	}
        
    @Test(expected = PreguntasInsuficientesException.class)
    public final void testGenerarExamenConPreguntasInsuficientes() throws PreguntasInsuficientesException, ExamenSinPreguntasException
    {
            materia.generarExamen(ahora, unidadesAbarcadas, 20, 1);
    }	
        
	@Test
	public final void testPreguntasExamen() throws Exception{
	
            Set<Pregunta> preguntas = materia.generarExamen(ahora, unidadesAbarcadas, 3, 2).getPreguntas();
            
            assertEquals( preguntas.size(), 5);
            
            for( Pregunta preg : preguntasPocoUsadas)
            {
                assertTrue( preguntas.contains(preg));
            }
            for( Pregunta preg : preguntasMuyUsadas)
            {
                assertFalse( preguntas.contains(preg));
            }

            
            
	}

}