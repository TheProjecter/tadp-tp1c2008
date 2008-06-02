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

     /**
     * compara por el texto, la unidad tematica y el tipo
     * no por la dificultad ni cuanto se uso
     * @param otra pregunta
     * @return si es la misma
     */
    @Override
    public boolean equals (Object e)
    {

        if(e instanceof Ejercicio)
        {
            Ejercicio ejercicio = (Ejercicio) e;

            return this.getTextoEnunciado().equals(ejercicio.getTextoEnunciado())
                    && this.getUnidadTematica().equals(ejercicio.getUnidadTematica())
                    && (this.getTipo() == ejercicio.getTipo());
            
        }


        return false;

    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

	
}

