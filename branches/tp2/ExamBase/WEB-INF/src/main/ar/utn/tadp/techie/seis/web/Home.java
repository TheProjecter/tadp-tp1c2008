package ar.utn.tadp.techie.seis.web;


import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.html.BasePage;

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
