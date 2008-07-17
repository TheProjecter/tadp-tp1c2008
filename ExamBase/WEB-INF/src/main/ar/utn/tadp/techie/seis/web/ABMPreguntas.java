package ar.utn.tadp.techie.seis.web;

import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

public abstract class ABMPreguntas extends BasePage {

	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClientId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public PropertyChangeObserver getPropertyChangeObserver() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract String getMateria();
	public abstract  void setMateria(String materia);

}
