package ar.utn.tadp.techie.seis;



import ar.utn.tadp.techie.seis.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import ar.utn.tadp.techie.seis.ExamenCorregido.CalificacionPregunta;
import ar.utn.tadp.techie.seis.ItemExamen.TiposItem;
import static org.junit.Assert.*;
/**
 *
 * @author xuan
 */
public class CriterioTest
{
        private Examen examen;
    
        @Before
	public void setUp() throws Exception
        {
            examen = generarExamenTest();
        }
        /**
         * Para usar en los test y corregir los examenes
         * @param corrector
         * @param criterio
         * @return
         */
        private ExamenCorregido generarExamenCorregido(Corrector corrector, CriterioDeCorreccion criterio)
        {
            ExamenCorregidoBuilder builder = new ExamenCorregidoBuilder(new Alumno(), examen, corrector);
            builder.addCriterio(criterio);
            try
            {
                return builder.corregirExamen();
            } catch(NotaIncorrectaException e)
            {  
                return null;
            }
        }
        
        private ExamenCorregido generarExamenCorregidoNoAprobado(CriterioDeCorreccion criterio)
        {
            return generarExamenCorregido(new CorrectorMalaOnda(), criterio);
        }
        
        private ExamenCorregido generarExamenCorregidoAprobado(CriterioDeCorreccion criterio)
        {
            return generarExamenCorregido(new CorrectorBuenaOnda(), criterio);
        }
        
        /**
         * Metodo: Criterio60NoCumpleTest
         * 
         * Este Test verifica que si no se alcanza el 60% de las preguntas bien,
         * no este en condiciones de aprobar 
         */
        @Test
        public void Criterio60NoCumpleTest()
        {
            CriterioDeCorreccion criterio = new SesentaPorcientoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoNoAprobado(criterio);
            
            assertFalse(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 0);
        }
        
        /**
         * Test: Criterio60SiCumpleTest
         * 
         * Verifica que si cumple el 60% de las correcciones, se corrija y ser asigne nota
         */
        @Test
        public void Criterio60SiCumpleTest()
        {
            CriterioDeCorreccion criterio = new SesentaPorcientoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoAprobado(criterio);
            
            assertTrue(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 4);
        }
        
        
        /**
         * Test: CriterioTipoNoCumple
         * 
         * Verifica que si no tiene una pregunta de cada tipo bien (Teorico y Practico), no permita asignar nota
         */
        @Test
        public void CriterioTipoNoCumple()
        {
            CriterioDeCorreccion criterio = new UnoDeCadaTipoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoNoAprobado(criterio);
            
            assertFalse(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 0);            
        }

        
        /**
         * Test: CriterioTipoNoCumple
         * 
         * Verifica que si tiene una pregunta de cada tipo bien (Teorico y Practico), lo apruebe y permita asignar nota
         */
        @Test
        public void CriterioTipoSiCumple()
        {
            CriterioDeCorreccion criterio = new UnoDeCadaTipoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoAprobado(criterio);
            
            assertTrue(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 4);            
        }
        

        /**
         * Test: CriterioUnidadNoCumple()
         * 
         * Verifica que si el examen no tiene una pregunta de cada Unidad bien, no permita asignarle nota
         */
        @Test
        public void CriterioUnidadNoCumple()
        {
            CriterioDeCorreccion criterio = new UnaDeCadaUnidadCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoNoAprobado(criterio);
            
            assertFalse(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 0);
        }

        
        /**
         * Test: CriterioUnidadNoCumple()
         * 
         * Verifica que si el examen tiene una pregunta de cada Unidad bien, lo apruebe y asigne nota
         */
        @Test
        public void CriterioUnidadSiCumple()
        {
            CriterioDeCorreccion criterio = new UnaDeCadaUnidadCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoAprobado(criterio);
            
            assertTrue(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 4);            
        }
        
        
     /**
	 * Metodo auxiliar del SetUp para crear el Examen de referencia
	 * 
	 * @return
	 */
	public Examen generarExamenTest() {

		Pregunta pregunta;
                Ejercicio ejercicio;
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

		ejercicio = new Ejercicio("Estructurado", 75, "Que es un trampolin de datos?", ItemExamen.TiposItem.PRACTICO);  
		materia.addItem(ejercicio);

		ejercicio = new Ejercicio("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.PRACTICO);  
		materia.addItem(ejercicio);
	            
		ejercicio = new Ejercicio("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", ItemExamen.TiposItem.PRACTICO);  
		materia.addItem(ejercicio);
                
          
		PrototipoItem<Pregunta> protoPregunta = new PrototipoItem<Pregunta>(Pregunta.class);
		protoPregunta.setTipo(TiposItem.TEORICO);
		PrototipoItem<Ejercicio> protoEjercicio = new PrototipoItem<Ejercicio>(Ejercicio.class);
		protoEjercicio.setTipo(TiposItem.PRACTICO);
		ExamenBuilder builder = new ExamenBuilder(materia,unidadesAbarcadas,Calendar.getInstance());
		builder.putPrototipo(protoPregunta, 3);
		builder.putPrototipo(protoEjercicio, 3);
		
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
	 * Implementacion de la interfaz Corrector, para que devuelva la correccion
	 * de una pregunta especifica 
	 * 
	 */
	public ExamenCorregido.CalificacionPregunta getNotaPregunta(ItemExamen preg)
	{
		return ExamenCorregido.CalificacionPregunta.BIENMENOS;
	}
}

/**
 * Corrije el examen de forma que no cumpla ningun criterio
 * @author xuan
 */
class CorrectorMalaOnda implements Corrector
{

    public CalificacionPregunta getNotaPregunta(ItemExamen preg)
    {
        if(preg.getTipo().equals(ItemExamen.TiposItem.TEORICO))
            return CalificacionPregunta.BIENMENOS;
        return CalificacionPregunta.MAL;
    }

    public int getNotaFinal()
    {
        //No deberia pasar por aca
        return 4;
    }

}
/**
 * Corrije el examen de forma que compla con los criterios
 * @author xuan
 */
class CorrectorBuenaOnda implements Corrector
{

    public CalificacionPregunta getNotaPregunta(ItemExamen preg)
    {
        //Asi queda alguna mal
        if(preg.getTextoEnunciado().equals("Cuantos modos de Cohesion Existe?"))
            return CalificacionPregunta.REGULAR;
        return CalificacionPregunta.BIEN;
    }

    public int getNotaFinal()
    {
        //No era tan buena onda
        return 4;
    }

}
