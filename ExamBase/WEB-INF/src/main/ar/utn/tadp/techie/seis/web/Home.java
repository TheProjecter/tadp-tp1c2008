package ar.utn.tadp.techie.seis.web;


//import org.apache.tapestry.annotations.InitialValue;
//import org.apache.tapestry.annotations.InjectState;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.*;

import ar.utn.tadp.techie.seis.ADesarrollar;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Pregunta;


public abstract class Home extends BasePage {
	 
	
	private List<ItemExamen> items;
	
	
	@InjectPage("ABMUnidades")
	abstract public ABMUnidades getUnidadesPage() ;
	
	@InjectPage("ABMPreguntas")
	abstract public ABMPreguntas getPreguntasPage() ;
	
	@InjectPage("GenerarExamen")
	abstract public GenerarExamen getGenerarExamenPage() ;
	/*
	abstract public String getMateriaId();
	
	public IPage onBuscar(IRequestCycle cycle)
	{
		
	}*/
	//@Store(store=session)
	//public abstract String getMateria();

	
	public IPropertySelectionModel getMateriasModel() {
		// para prueba ...las materias van a venir del resultado de un query a la base
		 String[] materias = {"TADP", "Paradigmas", "Diseño"}; 
        return new StringPropertySelectionModel(materias);
    }
	
	
	public IPage unidadesPage(IRequestCycle cycle)
	{
		ABMUnidades abmUnidadesPage = getUnidadesPage();
		return abmUnidadesPage;
		
	}
	public IPage preguntasPage(IRequestCycle cycle)
	{
		ABMPreguntas preguntasPage = getPreguntasPage();
		return preguntasPage;
		
	}
	public IPage generarExamenPage(IRequestCycle cycle)
	{
		GenerarExamen examenPage = getGenerarExamenPage();
		return examenPage;
		
	}
	
	//TODO --este metodo deberia devolver las preguntas de la materia seleccionada
	public List<ItemExamen> getItems() 
	{
		items = new ArrayList<ItemExamen>();
		 
		Pregunta pregunta = new ADesarrollar("Patrones", 75, "Por que necesitamos a los patrones en las estancias?", ItemExamen.TiposItem.TEORICO);   
		items.add(pregunta);

		pregunta = new ADesarrollar("Estructurado", 10, "Alguien usa estructurado hoy en Dia?", ItemExamen.TiposItem.TEORICO);  
		items.add(pregunta);

		pregunta = new ADesarrollar("Estructurado", 40, "Cuantos modos de Cohesion Existe?", ItemExamen.TiposItem.TEORICO);  
		items.add(pregunta);
		 
		 
		return items;
		
	}
}
