package tadp.techie.seis;

import java.util.ArrayList;
import java.util.Collection;

public class Alumno {

	private String eMail;
	private Collection<ExamenCorregido> examenesCorregidos = new ArrayList<ExamenCorregido>();

	
	/**
	 * Agrega el examen corregido a la lista de examenes dados por el Alumno 
	 * 
	 * @param ex: El Examen corregido
	 */
	public void addExamenCorregido(ExamenCorregido ex)
	{
		examenesCorregidos.add(ex);
		
		// Envio la nota
		if (this.getEMail() != null)
			this.enviarEMail(ex);

		return;
	}
	
	/**
	 * Metodo: enviarEMail.
	 * 
	 * Simulacion del envio del mail
	 * 
	 * @param ex: Examen Corregido
	 * 
	 */
	public void enviarEMail(ExamenCorregido ex)
	{
		// TODO Que hacemos aca?????
		return;
	}
	
	/**
	 * Consulta de un alumno para conoce
	 * 
	 * @param ex: El examen por el que se consulto
	 * @return El Examen Corregido
	 */
	public ExamenCorregido consultarNotaExamen(Examen examenBuscado)
	{

            for(ExamenCorregido ex : examenesCorregidos)
            {
                //Incluyo la unidad tematica
                if (examenBuscado.equals(ex.getExamen()))
                    return ex; 
            }
		// TODO No habria que tirar una Exception???
		return null;
	}
	
	public String getEMail() 
	{
		return eMail;
	}
	
	public void setEMail(String mail) 
	{
		eMail = mail;
	}
	
	public Collection<ExamenCorregido> getExamenesCorregidos()
	{
		return examenesCorregidos;
	}
}
