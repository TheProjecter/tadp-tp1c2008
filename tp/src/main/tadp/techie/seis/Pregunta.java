package tadp.techie.seis;



public class Pregunta implements Comparable<Pregunta>{

	private String unidadTematica;
	private int complejidad;
	public enum TiposPregunta {TEORICO,PRACTICO,TEORICOPRACTICO};
	private String pregunta;
	private int cantidadDeVecesQueSeUso;
	private TiposPregunta tipo;
	
	


	


	public Pregunta(String unidadTematica, int complejidad, String pregunta,
			 TiposPregunta tipo) {
		
		this.unidadTematica = unidadTematica;
		this.complejidad = complejidad;
		this.pregunta = pregunta;		
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
		return pregunta;
	}







	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
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

	public int compareTo(Pregunta pregunta)
	{
		int resultado = 0;
		
		if(this.getCantidadDeVecesQueSeUso() > pregunta.getCantidadDeVecesQueSeUso())
		{
			resultado = 1;
		}
		else
		{
			resultado = -1;//No chequeo == ni < porque ambos tienen que devolver lo mismo, sino me saca uno del TreeSet
		}
		
	
		return resultado;
	}
	
	public boolean Equals (Object p)//Compra si dos preguntas son iguales en base a todos los atributos excepto el de cantidadDeVecesQueSeUso
	{
		
		if(p instanceof Pregunta)
		{
			Pregunta pregunta = (Pregunta) p;
		
		
			if((this.complejidad == pregunta.getComplejidad()) && (this.pregunta == pregunta.getPregunta()) && (this.unidadTematica == pregunta.getUnidadTematica()) && (this.tipo == pregunta.getTipo()))
			{
				return true;
			}
		
		}
		
		
		return false;
		
	}
}
