package ar.utn.tadp.techie.seis;

import java.util.Set;

public abstract class ItemExamen {
	
    private String unidadTematica;
    private int complejidad;
    private int id;
    
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

    public void setId(int id){
    	this.id = id;
    }
  
    public int getId(){
    	return id;
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
    
    public boolean isTeorico()
    {
        return tipo == TiposItem.TEORICO || tipo == TiposItem.TEORICOPRACTICO;
    }

    public boolean isPractico()
    {
        return tipo == TiposItem.PRACTICO || tipo == TiposItem.TEORICOPRACTICO;
    }
	
    @Override
    public boolean equals(Object o)
    {
        if(o == null || !(o instanceof ItemExamen))
            return false;
        ItemExamen item = (ItemExamen)o;
        return  this.unidadTematica.equals(item.getUnidadTematica()) &&
                this.textoEnunciado.equals(item.getTextoEnunciado()) &&
                this.complejidad == item.getComplejidad() &&
                this.getTipo() == item.getTipo();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + (this.unidadTematica != null ? this.unidadTematica.hashCode() : 0);
        hash = 89 * hash + this.complejidad;
        hash = 89 * hash + (this.textoEnunciado != null ? this.textoEnunciado.hashCode() : 0);
        return hash;
    }

    public abstract void addTo(ItemAddable materia);
    
    public abstract Set<? extends ItemExamen> getItemsFrom(Materia materia);
   
}

