package tadp.techie.seis;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ExamenCorregidoBuilder {

	private	Alumno alumno;
	private	Examen examen;
	private Corrector corrector;
        private HashSet<CriterioDeCorreccion> criterios;

	private	int nota;

	// Mapa que contiene por cada Pregunta el resultado de la respuesta
	private Map<ItemExamen, ExamenCorregido.CalificacionPregunta> notasItems;


	/**
	 * Este es el Constructor del Builder del examen corregido
	 * 
	 * @param al: Alumno al cual se corregira el Examen
	 * @param ex: Examen que sera corregido
	 */
	public ExamenCorregidoBuilder(Alumno al, Examen ex, Corrector cor)
	{
		this.setAlumno(al);
		this.setExamen(ex);
		this.setCorrector(cor);
                this.setCriterios(new HashSet<CriterioDeCorreccion>());

		this.setNotasPregunta(new HashMap<ItemExamen, ExamenCorregido.CalificacionPregunta>());

	}


	/**
	 * Este metodo es el build del ExamenCorregirlo, al invocarlo crea la correccion del examen y la asigna al Alumno 
	 */
	public ExamenCorregido corregirExamen() throws NotaIncorrectaException
	{
		ExamenCorregido ex;

		// Tomo por cada pregunta la respuesta que corresponde
		for(ItemExamen preg : this.getExamen().getItems())
			this.getNotasPregunta().put(preg, this.getCorrector().getNotaPregunta(preg)); 

                boolean cumpleCriterios = true;
                
		for(CriterioDeCorreccion criterio : this.getCriterios())
                {
                    if(!criterio.cumple(this.getNotasPregunta()))
                    {
                        cumpleCriterios = false;
                        break;
                    }
                }
		if (cumpleCriterios)
	//		try {
				this.setNota(this.getCorrector().getNotaFinal());				
	//		}
	//		catch (NotaIncorrectaException notaInvalida)
			// TODO Que hacemos aca con la exception notaInvalida?????
	
			
		
		// Creo el Examen Corregido
		ex = new ExamenCorregido(this.getExamen(), this.getNota(), this.getNotasPregunta());

		this.getAlumno().addExamenCorregido(ex);
		return ex;
	} 

	/*
	 * Getters de los Atributos de la Clase  
	 */
	public Alumno getAlumno() {
		return alumno;
	}

	public Examen getExamen() {
		return examen;
	}
	
	public Corrector getCorrector() {
		return corrector;
	}

	public int getNota() {
		return nota;
	}

	public Map<ItemExamen, ExamenCorregido.CalificacionPregunta> getNotasPregunta() {
		return notasItems;
	}


	/*
	 * Setters de los Atributos de la Clase  
	 */
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	private void setCorrector(Corrector cor) {
		this.corrector = cor;
	}

	private void setNotasPregunta(Map<ItemExamen, ExamenCorregido.CalificacionPregunta> notasItems) {
		this.notasItems = notasItems;
	}
	
	
	/**
	 * 
	 * @param nota: Nota que obtuvo el Alumno (entre 1 y 10) 
	 * @throws 
	 */
	private void setNota(int nota) throws NotaIncorrectaException {
		
		if (nota > 0 && nota < 11 )
		{
			this.nota = nota;
			return;
		}

		throw new NotaIncorrectaException();
	}

        public HashSet<CriterioDeCorreccion> getCriterios()
        {
            return criterios;
        }

        public void setCriterios(Collection<CriterioDeCorreccion> criterios)
        {
            if(this.criterios == null)
                this.criterios = new HashSet<CriterioDeCorreccion>();
            this.criterios.clear();
            this.criterios.addAll(criterios);
        }
        
        public void addCriterio(CriterioDeCorreccion criterio)
        {
            this.getCriterios().add(criterio);
        }


}
