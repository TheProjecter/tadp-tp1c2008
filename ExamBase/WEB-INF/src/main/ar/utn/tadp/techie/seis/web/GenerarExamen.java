package ar.utn.tadp.techie.seis.web;

import org.apache.tapestry.annotations.InitialValue;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

public abstract class GenerarExamen extends BasePage {

	@InitialValue("literal:3")
	public abstract String getCantidadPreguntasTeoricas();
	@InitialValue("literal:3")
	public abstract String getCantidadPreguntasPracticas();
	@InitialValue("literal:3")
	public abstract String getCantidadEjerciciosTeoricos();
	@InitialValue("literal:1")
	public abstract String getCantidadEjerciciosPracticos();
	@InitialValue("literal:1")
	public abstract String getCantidadUnidades();

	
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
	
	public IPropertySelectionModel getUnidadesModel() {
		// para prueba ...las materias van a venir del resultado de un query a la base
		 String[] unidades = {"Unidad 1", "Unidad 2", "Unidad 3"}; 
        return new StringPropertySelectionModel(unidades);
    }
}
