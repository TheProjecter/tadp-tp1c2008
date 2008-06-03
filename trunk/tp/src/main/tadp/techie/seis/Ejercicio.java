package tadp.techie.seis;


public class Ejercicio extends ItemExamen{
	

	/**
	 * Ejercicio.<e>
	 * @param unidadTematica   	que unidad tematica?
	 * @param complejidad		valor de complejidad?
	 * @param ejercicio			que ejercicio?
	 * @param tipo				tipo de ejercicio? [TEORICO - PRACTICO - TEORICOPRACTICO]
	 */
    public Ejercicio(String unidadTematica, int complejidad, String enunciado,
                     TiposItem tipo) {
    
    	super(unidadTematica, complejidad, enunciado, tipo);
                   

    }
	
}

