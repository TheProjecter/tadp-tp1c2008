package tadp.techie.seis;


public abstract class Pregunta
{

    private String unidadTematica;
    private int complejidad;
    public enum TiposPregunta {TEORICO,PRACTICO,TEORICOPRACTICO};
    private String textoPregunta;
    private int cantidadDeVecesQueSeUso;
    private TiposPregunta tipo;


    public Pregunta(String unidadTematica, int complejidad, String pregunta,
                     TiposPregunta tipo) {

            this.unidadTematica = unidadTematica;
            this.complejidad = complejidad;
            this.textoPregunta = pregunta;		
            this.tipo = tipo; 
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

    public String getPregunta() {
            return textoPregunta;
    }

    public void setPregunta(String pregunta) {
            this.textoPregunta = pregunta;
    }

    public int getCantidadDeVecesQueSeUso() {
            return cantidadDeVecesQueSeUso;
    }

    public void setCantidadDeVecesQueSeUso(int cantidadDeVecesQueSeUso) {
            this.cantidadDeVecesQueSeUso = cantidadDeVecesQueSeUso;
    }

    public TiposPregunta getTipo() {
            return tipo;
    }

    public void setTipo(TiposPregunta tipo) {
            this.tipo = tipo;
    }
    
    public void usoEnExamen() {
        cantidadDeVecesQueSeUso ++;
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

            return this.textoPregunta.equals(pregunta.getPregunta())
                    && this.unidadTematica.equals(pregunta.getUnidadTematica())
                    && (this.tipo == pregunta.getTipo());
            
        }


        return false;

    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + (this.unidadTematica != null ? this.unidadTematica.hashCode() : 0);
        hash = 97 * hash + (this.textoPregunta != null ? this.textoPregunta.hashCode() : 0);
        hash = 97 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        return hash;
    }
}
