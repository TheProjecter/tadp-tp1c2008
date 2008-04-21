package tadp.techie.seis;

import java.util.ArrayList;
import java.util.List;

public class Choice extends Pregunta{
	
	private List<String> opciones; //opciones para las respuestas

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
