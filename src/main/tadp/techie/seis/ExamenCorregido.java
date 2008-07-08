package tadp.techie.seis;

import java.util.Map;

public class ExamenCorregido {

	public enum CalificacionPregunta {MAL, REGULAR, REGULARMAS, BIENMENOS, BIEN};

	private Examen examen;
	private int nota;
	private Map<ItemExamen, CalificacionPregunta> notasPregunta;
	
	
	public ExamenCorregido(Examen ex, int nota, Map<ItemExamen, CalificacionPregunta> notasPregunta)
	{
		this.setExamen(ex);
		this.setNota(nota);
		this.setNotasPregunta(notasPregunta);
		
		return;
		
	}


	public Examen getExamen() {
		return examen;
	}
	public int getNota() {
		return nota;
	}
	public Map<ItemExamen, CalificacionPregunta> getNotasPregunta() {
		return notasPregunta;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public void setNotasPregunta(Map<ItemExamen, CalificacionPregunta> notasPregunta) {
		this.notasPregunta = notasPregunta;
	}
	
	
	
}
