package ar.utn.tadp.techie.seis;

/**
 * 
 * @param unidadTematica que unidad abarca?
 * @param complejidad nivel de complejidad?
 * @param pregunta que preguntas?
 * @param tipo tipo de pregunta? [TEORICO - PRACTICO - TEORICOPRACTICO]
 * 
 */
public class ADesarrollar extends Pregunta
{
    public ADesarrollar(String unidadTematica, int complejidad, String pregunta,
                     TiposItem tipo)
    {
        super(unidadTematica, complejidad, pregunta, tipo);
    }
    
}

