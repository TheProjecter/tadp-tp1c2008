package ar.utn.tadp.techie.seis.web;


import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.*;

import ar.utn.tadp.techie.seis.ADesarrollar;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Pregunta;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;


public abstract class Home extends BasePage {
	 
	
	private Set<ItemExamen> items=null;
	
	
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

	public abstract String getMateria();
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
		examenPage.setMateria(this.getMateria());
		return examenPage;
		
	}
	
	//TODO --este metodo deberia devolver las preguntas de la materia seleccionada
	public Set<ItemExamen> getItems() 
	{
		if(items == null) {
			
		items = new HashSet<ItemExamen>();
				
		Pregunta pregunta = new ADesarrollar(" ", 0, " ", ItemExamen.TiposItem.TEORICO);   
		items.add(pregunta);
		
		}
		return items;
		
	}
	/**
	 * @author juanmi
	 * Este metodo stea la lista de items pertenecientes
	 *  a una materia seleccionada en el combo box de la pagina
	 * **/
	public void onBuscarPreguntas(IRequestCycle cycle)
	{
		String materiaSeleccionada = (String)getMateria();
		//String diseño = "Diseño";

		items = MateriasPoolMock.getInstance().getItems(materiaSeleccionada);
		//con la materia que seleccione genero el query para buscar las preguntas de la misma
			
		cycle.activate("Home");
	}
}
