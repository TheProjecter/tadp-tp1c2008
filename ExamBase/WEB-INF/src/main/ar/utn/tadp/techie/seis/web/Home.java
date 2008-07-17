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
import ar.utn.tadp.techie.seis.persistance.MateriaDAOMock;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;


public abstract class Home extends BasePage {
	 
	private String materia; 
	
	private Set<ItemExamen> items=null;
	
	
	public Home(){
		
	}
	
	//Persist("session")
	//public abstract String getMateria();
	//public abstract void setMateria(String m);
	/** tuve que desistir de  abstract porke no pude hacer ke pase la materia a las otras paginas **/
	public String getMateria(){ 
		return this.materia; 
	}
	public void setMateria(String m)
	{
		this.materia=m;
	}
	
	
	
	public IPropertySelectionModel getMateriasModel() {
		// para prueba ...las materias van a venir del resultado de un query a la base
		Set<String> materiasSet = MateriaDAOMock.getInstance().listarMaterias(); 
		
		String materias = "Seleccione...";
		
		//convierto el set en un array de strings para poder pasarlo al componente
		for(String m: materiasSet)
		{
			materias += "," + m;
		}
			
        return new StringPropertySelectionModel((String[])materias.split(","));
    }
	
	@InitialValue("literal: ")
	public abstract String getMensaje();
	public abstract void setMensaje(String m);
	
	@InjectPage("ABMUnidades")
	abstract public ABMUnidades getUnidadesPage() ;
	
	@InjectPage("ABMPreguntas")
	abstract public ABMPreguntas getPreguntasPage() ;
	
	@InjectPage("GenerarExamen")
	abstract public GenerarExamen getGenerarExamenPage() ;
	
	
	public IPage unidadesPage(IRequestCycle cycle)
	{
		ABMUnidades abmUnidadesPage = getUnidadesPage();
		abmUnidadesPage.setMateria((String)materia);
		return goToPage(abmUnidadesPage);
		
	}
	public IPage preguntasPage(IRequestCycle cycle)
	{
		ABMPreguntas preguntasPage = getPreguntasPage();
		preguntasPage.setMateria((String)materia);
		return goToPage(preguntasPage);
		
	}
	public IPage generarExamenPage(IRequestCycle cycle)
	{
		
		GenerarExamen examenPage = getGenerarExamenPage();
		examenPage.setMateria((String)materia);
		return goToPage(examenPage);
		
	}
	/**
	 * @author juanmi
	 * @param nextPage pagina a la que desea acceder
	 * @return en caso de que se haya seleccionado una materia retorna la pagina correspondiente. 
	 * Sino se vuelve a la pagina anterior con mensaje de error.
	 */
	public IPage goToPage(IPage nextPage)
	{
		if(isMateriaSeleccionada()){
			return nextPage;
		}
		return this;
	}
	
	//TODO
	/**@author juanmi 
 	 * 
	 * valida que se haya seleccionado una materia en la pagina.
	 * @return true: la materia fue seleccionada
	 * 			false: la materia no fue seleccionada
	 * 
	 */
	private boolean isMateriaSeleccionada()
	{
		if( getMateria() == null ) //si no hay ninguna materia seleccionada
		{	
			setMensaje("Seleccione la Materia con la que desea trabajar.");
			return false;
		}
		return true;
	}
	/**
	 * @author juanmi
	 * devuelve los itemsExamen de la materia que se encuentra seleccionada.
	 * Si no tiene items devuelve un HashSet con una pregunta aDesarrollar pero sin datos.
	 * No me gusta eso pero si devuelvo null se rompe.
	 * **/
	public Set<ItemExamen> getItems() 
	{
		if(items == null) {
		//TODO	mmm...
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
