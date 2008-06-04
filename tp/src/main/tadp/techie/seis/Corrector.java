package tadp.techie.seis;

public interface Corrector {

	public ExamenCorregido.CalificacionPregunta getNotaPregunta(ItemExamen preg);
	
	public int getNotaFinal();

}
