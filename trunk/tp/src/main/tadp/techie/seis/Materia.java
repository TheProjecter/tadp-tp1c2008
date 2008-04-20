package tadp.techie.seis;


import java.util.*;
public class Materia {

	private Set<Pregunta> preguntas;
	private List<Examen> examenes;
	
	public Materia()
	{
		
		preguntas = new TreeSet<Pregunta>();
		examenes = new ArrayList<Examen>();
		
		
	}

	public Set<Pregunta> getPreguntas() 
	{
		return preguntas;
	}

	public List<Examen> getExamenes() 
	{
		return examenes;
	}
	
	public Examen generarExamen(Calendar fechaQueSeraTomado,Set<String> unidadesAbarcadas, int cantidadPreguntasTeoricas, int cantidadPreguntasPracticas)
	{
		int contadorPreguntasTeoricas = 0;
		int contadorPreguntasPracticas = 0;
		Set<Pregunta> preguntasParaElExamen = new HashSet<Pregunta>();
		
		
		
			for(Pregunta pregunta : preguntas)
		
			{
				if(contadorPreguntasTeoricas < cantidadPreguntasTeoricas)			
				{
					if((unidadesAbarcadas.contains(pregunta.getUnidadTematica())) && (pregunta.getTipo()== Pregunta.TiposPregunta.TEORICO))
				
						{
							System.out.println("Teorico");
							preguntasParaElExamen.add(pregunta);
							contadorPreguntasTeoricas++;
						}
				}
			}
		
		
			
			for(Pregunta pregunta : preguntas)
			{
				if(contadorPreguntasPracticas < cantidadPreguntasPracticas)			
				{				
					if((unidadesAbarcadas.contains(pregunta.getUnidadTematica())) && (pregunta.getTipo()== Pregunta.TiposPregunta.PRACTICO))
					{
						System.out.println("Practico");
						preguntasParaElExamen.add(pregunta);
						contadorPreguntasPracticas++;
					}
				}
			}
			
		
		Examen examen = new Examen(fechaQueSeraTomado,unidadesAbarcadas,preguntasParaElExamen);
		
		
		for(Pregunta p : examen.getPreguntas())
		{
			System.out.println(p.getPregunta());
		}
			
		
		
		return examen;
	}
	
	
	public void recorreP()
	{//Metodo Trucho para ver si me ordeno el TreeSet
		
		for(Pregunta pregunta : preguntas)
		{
			
			System.out.println(pregunta.getPregunta());
			
		}
		
		
	}
	
}
