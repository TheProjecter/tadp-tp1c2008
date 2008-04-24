package tadp.techie.seis;

import java.util.ArrayList;
import java.util.List;

public class Choice extends Pregunta{
	
	private List<String> opciones; //opciones para las respuestas
	/**
	 * @param unidadTematica que unidad abarca?
	 * @param complejidad nivel de complejidad?
	 * @param pregunta que preguntas?
	 * @param tipo tipo de pregunta? [TEORICO - PRACTICO - TEORICOPRACTICO]
	 * @param opciones De una lista de opciones para responder.
	 */
    public Choice(String unidadTematica, int complejidad, String pregunta,
            TiposPregunta tipo, List<String> opciones){
    	super(unidadTematica, complejidad, pregunta, tipo);
    	this.opciones = new ArrayList<String>();
    	this.opciones = opciones;
    }

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}
    
    
    
}
