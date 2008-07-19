package ar.utn.tadp.techie.seis.web;

import java.util.ArrayList;


import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.*;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;


import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;

public abstract class GenerarExamen extends BasePage {
/*
	private String materia;
	
	public String getMateria(){
		return this.materia; 
	}
	public void setMateria(String m)
	{
		this.materia=m;
	}*/
	
	public abstract String getMateria();
	public abstract void setMateria(String materia);
	private ArrayList<IPropertySelectionModel> combosUnidades;
	
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
	
	//**@InitialValue("literal:N/A")
	//**@InjectAsset("readonly")
	/*@InjectComponent("materia")
	@Persist("session")
	public abstract String getMateria();
	
	public abstract void setMateria(String materia);

	*/
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
	/** 
	 * @author juanmi
	 * getUnidadesModel()
	 * lista todas las unidades correspondientes a la materia en un combo box
	 * @return "combo box"
	 */
	public IPropertySelectionModel getUnidadesModel() {
		// para prueba ...las materias van a venir del resultado de un query a la base
		 String[] unidades = MateriasPoolMock.getInstance().getUnidadesAsStringArray(getMateria()); 
		 //MateriasPoolMock.getInstance().getUnidades(getMateria());
        return new StringPropertySelectionModel(unidades);
    }
	
	/**
	 * @author juanmi
	 * cuando se define la cantidad de unidades que se incluiran en
	 *  el examen se deben mostrar la cantidad de combos 
	 *  correspondiente a esa cantidad definida.
	 * @param cycle
	 */
	/*public void onSetCantidadUnidades(IRequestCycle cycle){
		
		int cantUnidades = Integer.parseInt(getCantidadUnidades());
		IPropertySelectionModel auxUnidadesSelectionModel = getUnidadesModel();
		
		for(int i=0;i<cantUnidades;i++) 
			combosUnidades.add(auxUnidadesSelectionModel);
		
		cycle.activate("GenerarExamen");
	}*/
	public void onSetCantidadUnidades(IRequestCycle cycle){
		
		int cantUnidades = Integer.parseInt(getCantidadUnidades());
		IPropertySelectionModel auxUnidadesSelectionModel = getUnidadesModel();
		
		for(int i=0;i<cantUnidades;i++) 
			combosUnidades.add(auxUnidadesSelectionModel);
		
		cycle.activate("GenerarExamen");
	}
	
	public void onGenerarExamen(IRequestCycle cycle){
		//TODO --juanmi
	}
	
	
}
