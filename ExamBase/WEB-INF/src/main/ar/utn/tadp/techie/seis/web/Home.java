package ar.utn.tadp.techie.seis.web;


//import org.apache.tapestry.annotations.InitialValue;
//import org.apache.tapestry.annotations.InjectState;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.InjectPage;


public abstract class Home extends BasePage {
	 
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
	public IPropertySelectionModel getMateriasModel() {
		// para prueba ...las materias van a venir de un resultado del query a la base
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
}
