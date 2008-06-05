package tadp.techie.seis;


public class Ejercicio extends ItemExamen{
	

	/**
	 * Ejercicio.<e>
	 * 
	 * Un ejercicio (a diferencia de una pregunta) no admite multiple choice.-
	 * 
	 * @param unidadTematica   	que unidad tematica?
	 * @param complejidad		valor de complejidad?
	 * @param ejercicio			que ejercicio?
	 * @param tipo				tipo de ejercicio? [TEORICO - PRACTICO - TEORICOPRACTICO]
	 */
    public Ejercicio(String unidadTematica, int complejidad, String enunciado,
                     TiposItem tipo) {
    
    	super(unidadTematica, complejidad, enunciado, tipo);
                   

    }
    public  void addTo(ItemAddable materia){
    	
    	materia.addEjercicio(this);
    }
}

