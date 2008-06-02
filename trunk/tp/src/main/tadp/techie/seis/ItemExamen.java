package tadp.techie.seis;

public abstract class ItemExamen {
	
	private String unidadTematica;
	private int complejidad;
	public enum TiposItem {TEORICO,PRACTICO,TEORICOPRACTICO};
	private int cantidadDeVecesQueSeUso;
	private TiposItem tipo;
	private String textoEnunciado;
	
	
	/**
	 * Pregunta.<p>
	 * @param unidadTematica   	que unidad tematica?
	 * @param complejidad		valor de complejidad?
	 * @param pregunta			que pregunta?
	 * @param tipo				tipo de pregunta? [TEORICO - PRACTICO - TEORICOPRACTICO]
	 */
    public ItemExamen(String unidadTematica, int complejidad, String enunciado,
    		TiposItem tipo) {

            this.unidadTematica = unidadTematica;
            this.complejidad = complejidad;	
            this.tipo = tipo;
            this.textoEnunciado = enunciado;
            this.cantidadDeVecesQueSeUso = 0;

    }

    public String getUnidadTematica() {
            return unidadTematica;
    }

    public void setUnidadTematica(String unidadTematica) {
            this.unidadTematica = unidadTematica;
    }


    public int getComplejidad() {
            return complejidad;
    }

    public void setComplejidad(int complejidad) {
            this.complejidad = complejidad;
    }

    
    public int getCantidadDeVecesQueSeUso() {
            return cantidadDeVecesQueSeUso;
    }

    public void setCantidadDeVecesQueSeUso(int cantidadDeVecesQueSeUso) {
            this.cantidadDeVecesQueSeUso = cantidadDeVecesQueSeUso;
    }
    public void incrementarUso() {
		
		this.cantidadDeVecesQueSeUso++;
		
	}
    public TiposItem getTipo() {
            return tipo;
    }

    public void setTipo(TiposItem tipo) {
            this.tipo = tipo;
    }

	public String getTextoEnunciado() {
		return textoEnunciado;
	}

	public void setTextoEnunciado(String textoEnunciado) {
		this.textoEnunciado = textoEnunciado;
	}
	
	
	@Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + (this.getUnidadTematica() != null ? this.getUnidadTematica().hashCode() : 0);
        hash = 97 * hash + (this.getTextoEnunciado() != null ? this.getTextoEnunciado().hashCode() : 0);
        hash = 97 * hash + (this.getTipo() != null ? this.getTipo().hashCode() : 0);
        return hash;
    }
}
