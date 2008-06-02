package tadp.techie.seis;

public interface Corrector {

	public ExamenCorregido.RespuestaPregunta getNotaPregunta(Pregunta preg);
	
	public int getNotaFinal();

}
