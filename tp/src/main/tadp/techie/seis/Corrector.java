package tadp.techie.seis;

public interface Corrector {

	public ExamenCorregido.RespuestaPregunta getNotaPregunta(ItemExamen preg);
	
	public int getNotaFinal();

}
