package ar.utn.tadp.techie.seis.pools;
import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import ar.utn.tadp.techie.seis.*;

public class MateriasPoolMock extends MateriasPool{
	
	Collection<Materia> materias;
		
	/** @author juanmi
	 * 
	 * @param materia
	 * Recibo una materia como string y devuelvo las preguntas de esa materia.
	 */
	private MateriasPoolMock()
	{
		materias = new HashSet<Materia>();


		List<String>	opcionesChoice;

        opcionesChoice  =  new ArrayList<String>();

        HashSet<ItemExamen> itemsMuyUsados = new HashSet<ItemExamen>();
        HashSet<ItemExamen> itemsPocoUsados = new HashSet<ItemExamen>();

        Materia materia = new Materia("Diseño");

        // Creo lotes de prueba de Unidades Tematicas
        HashSet<String> unidadesAbarcadas = new HashSet<String>();

        unidadesAbarcadas.add("Patrones");
        unidadesAbarcadas.add("Ciclos de Vida");
        unidadesAbarcadas.add("Estructurado");
        unidadesAbarcadas.add("DFDTR");	

        //Esta la use muchas veces.
        ItemExamen pregunta = new ADesarrollar("Patrones", 75, "Por que necesitamos a los patrones en las estancias?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        for(int i = 0; i < 20; i++)
            pregunta.incrementarUso();
        itemsMuyUsados.add(pregunta);                

        //Solo dos veces
        pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        pregunta.incrementarUso();                
        itemsPocoUsados.add(pregunta);
        //Solo una
        pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        itemsPocoUsados.add(pregunta);
        //Solo tres
        pregunta = new ADesarrollar("Estructurado", 75, "Que es un trampolin de datos?", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        pregunta.incrementarUso();
        pregunta.incrementarUso();                
        itemsPocoUsados.add(pregunta);

        pregunta = new ADesarrollar("Ciclos de Vida", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICOPRACTICO);  
        materia.addItem(pregunta);

        //3 veces
        pregunta = new ADesarrollar("Ciclos de Vida", 75, "Indique los pasos que aplicaria con que ciclo de vida para implementar un Sistema Contable", ItemExamen.TiposItem.TEORICO);  
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        pregunta.incrementarUso();
        pregunta.incrementarUso();
        itemsPocoUsados.add(pregunta);

        opcionesChoice.add("Cascada");
        opcionesChoice.add("Espiral");
        opcionesChoice.add("Modelo");
        opcionesChoice.add("Evolutivo");
        opcionesChoice.add("Otros");

        //Muchas veces
        pregunta = new Choice("Ciclos de Vida", 40, "Que ciclo conviene utilizar cuando no se posee experiencia?", ItemExamen.TiposItem.PRACTICO, opcionesChoice);
        materia.addItem(pregunta);
        for(int i = 0; i < 20; i++)
            pregunta.incrementarUso();
        itemsMuyUsados.add(pregunta);    


        //2 veces                
        pregunta = new Choice("Ciclos de Vida", 30, "Que ciclo conviene utilizar cuando no se sabe exactamente que se busca?", ItemExamen.TiposItem.PRACTICO, opcionesChoice);
        materia.addItem(pregunta);
        pregunta.incrementarUso();
        itemsPocoUsados.add(pregunta);

        //Ejercicios

        Ejercicio ejercicio = new Ejercicio("Ciclos de Vida", 50, "De un ejemplo de modelo en espiral.", ItemExamen.TiposItem.PRACTICO);
        for(int i = 0; i < 20; i++)
                ejercicio.incrementarUso();
        materia.addItem(ejercicio);  
        itemsMuyUsados.add(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Ciclos de Vida", 30, "De un ejemplo de modelo en cascada.", ItemExamen.TiposItem.PRACTICO);
        for(int i = 0; i < 20; i++)
                ejercicio.incrementarUso();
        materia.addItem(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Ciclos de Vida", 45, "De un ejemplo de modelo de prototipos.", ItemExamen.TiposItem.PRACTICO);
        for(int i = 0; i < 20; i++)
                ejercicio.incrementarUso();
        materia.addItem(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 50, "De un ejemplo de uso del patron Observer.", ItemExamen.TiposItem.PRACTICO);
        materia.addItem(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 65, "Ejemplo de aplicacion de Command.", ItemExamen.TiposItem.PRACTICO);
        materia.addItem(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 20, "ejemplo de aplicacion de Strategy.", ItemExamen.TiposItem.PRACTICO);
        materia.addItem(ejercicio);
        itemsPocoUsados.add(ejercicio);
        itemsMuyUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 50, "De un ejemplo de uso del patron Observer.", ItemExamen.TiposItem.PRACTICO);
        materia.addEjercicio(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 65, "Ejemplo de aplicaci?n de Command.", ItemExamen.TiposItem.PRACTICO);
        materia.addEjercicio(ejercicio);
        itemsPocoUsados.add(ejercicio);

        ejercicio = new Ejercicio("Patrones", 20, "ejemplo de aplicacion de Strategy.", ItemExamen.TiposItem.PRACTICO);
        materia.addEjercicio(ejercicio);
        itemsPocoUsados.add(ejercicio);
		
		materias.add(materia);
	}
	public static MateriasPool getInstance()
	{
		if(instance == null)
			instance = new MateriasPoolMock();
    	return  instance;
	}
	@Override
	public Set<ItemExamen> getItems(String materia){
		
		for(Materia mat : materias)
		{
			//encuentro la materia y devuelvo las preguntas	
			if(materia.compareTo(mat.getNombre())==0)
				return mat.getItems();		
		}
		return null;
		
	}
	@Override
	public String[] getUnidades(String materia) {
		String[] unidades = {"Unidad 1",  "Unidad 2", "Unidad 3"} ;
		return unidades;
	}
	

}
