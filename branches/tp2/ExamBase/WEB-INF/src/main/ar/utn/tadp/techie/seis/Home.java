package ar.utn.tadp.techie.seis;

import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.html.BasePage;

public abstract class Home extends BasePage {
	
	
	@InjectPage("ABMUnidades")
	abstract public ABMUnidades getUnidadesPage() ;	
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
}
