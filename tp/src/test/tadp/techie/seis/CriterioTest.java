/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tadp.techie.seis;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import tadp.techie.seis.ExamenCorregido.CalificacionPregunta;
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
        
        @Test
        public void Criterio60NoCumpleTest()
        {
            CriterioDeCorreccion criterio = new SesentaPorcientoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoNoAprobado(criterio);
            
            assertFalse(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 0);
        }
        
        @Test
        public void Criterio60SiCumpleTest()
        {
            CriterioDeCorreccion criterio = new SesentaPorcientoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoAprobado(criterio);
            
            assertTrue(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 4);
        }
        
        @Test
        public void CriterioTipoNoCumple()
        {
            CriterioDeCorreccion criterio = new UnoDeCadaTipoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoNoAprobado(criterio);
            
            assertFalse(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 0);            
        }
        
        @Test
        public void CriterioTipoSiCumple()
        {
            CriterioDeCorreccion criterio = new UnoDeCadaTipoCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoAprobado(criterio);
            
            assertTrue(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 4);            
        }
        
        @Test
        public void CriterioUnidadNoCumple()
        {
            CriterioDeCorreccion criterio = new UnaDeCadaUnidadCriterioDeCorreccion();
            ExamenCorregido corregido = generarExamenCorregidoNoAprobado(criterio);
            
            assertFalse(criterio.cumple(corregido.getNotasPregunta()));
            assertEquals(corregido.getNota(), 0);              
        }
        
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
                
                


		try 
		{
			ex = materia.generarExamen(Calendar.getInstance(), unidadesAbarcadas, 3, 3);
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