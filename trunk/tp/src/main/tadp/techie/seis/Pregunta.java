package tadp.techie.seis;


public abstract class Pregunta extends ItemExamen
{

	/**
	 * Pregunta.<p>
	 * @param unidadTematica   	que unidad tematica?
	 * @param complejidad		valor de complejidad?
	 * @param pregunta			que pregunta?
	 * @param tipo				tipo de pregunta? [TEORICO - PRACTICO - TEORICOPRACTICO]
	 */
    public Pregunta(String unidadTematica, int complejidad, String enunciado,
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
    public boolean equals (Object p)
    {

        if(p instanceof Pregunta)
        {
            Pregunta pregunta = (Pregunta) p;

            return this.getTextoEnunciado().equals(pregunta.getTextoEnunciado())
                    && this.getUnidadTematica().equals(pregunta.getUnidadTematica())
                    && (this.getTipo() == pregunta.getTipo());
            
        }


        return false;

    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

	
}
