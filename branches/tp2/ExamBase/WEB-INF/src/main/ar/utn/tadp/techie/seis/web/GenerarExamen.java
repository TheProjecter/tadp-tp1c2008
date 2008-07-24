package ar.utn.tadp.techie.seis.web;


import java.util.Calendar;

import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.*;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;


import ar.utn.tadp.techie.seis.Materia;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;

public abstract class GenerarExamen extends BasePage {

	private Materia materia;	
	private String unidadesString; //para mantener unidades agregadas y poder agregar nuevas
	private String[]  unidades = {""}; //para mostrar en la tabla

	
	public abstract String getNombreMateria();
	public abstract void setNombreMateria(String materia);
	
	public Materia getMateria(){
		return materia;
	}
	public void setMateria(Materia m){
		materia = m;
	}
	
	public GenerarExamen(){
		//unidadesSet = new HashSet<String>();
		unidadesString = "";
	}
	
	@InitialValue("literal:3")
	public abstract String getCantidadPreguntasTeoricas();
	@InitialValue("literal:2")
	public abstract String getCantidadPreguntasPracticas();
	@InitialValue("literal:3")
	public abstract String getCantidadEjerciciosTeoricos();
	@InitialValue("literal:2")
	public abstract String getCantidadEjerciciosPracticos();
	public abstract String getUnidad();
	public abstract void setUnidad(String u);
	
	@InitialValue("literal:")
	public abstract String getMensaje(); //mensaje de aviso al generar un examen
	public abstract void setMensaje(String m);
	
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
		 String[] unidades = MateriasPoolMock.getInstance().getUnidadesAsStringArray(getNombreMateria()); 
		 //MateriasPoolMock.getInstance().getUnidades(getMateria());
        return new StringPropertySelectionModel(unidades);
    }
	
	public String[] getUnidades(){	       
		
			return unidades;
		
	}
	public void setUnidades(String[] u){
		unidades = u;
	}
	/*
	public Set<String> getUnidadesSet(){	       
		
		return unidadesSet;
		
	}
	public void setUnidadesSet(Set<String> u){
		unidadesSet = u;
	}*/
	
	//////////////////////////
	/**
	 * Cada vez que se agrega una unidad al Examen se invoca este metodo.
	 * Agrega la unidad seleccionada al String de Unidades y actualiza el array De unidades para mostrar en la tabla.
	 */
	public void onSelectUnidad(IRequestCycle cycle){

	
		unidadesString += getUnidad() + ",";
		//probe usando un set y el mensaje toArray pero despues de agregar una unidad no cambia.. =(... 
		//por eso opte por esta solucion
		setUnidades(unidadesString.split(","));
	}
	
	/**
	 * Genera y guarda un examen con los requisitos definidos en la pagina.
	 * @param cycle
	 */
	public void onGenerarExamen(IRequestCycle cycle){
		
		//TODO --juanmi
		int cantTeoricos  = Integer.parseInt(getCantidadEjerciciosTeoricos());
		int cantPracticos = Integer.parseInt(getCantidadEjerciciosPracticos());
		int cantTeoricas  = Integer.parseInt(getCantidadPreguntasTeoricas());
		int cantPracticas = Integer.parseInt(getCantidadPreguntasPracticas());

		
		MateriasPoolMock.getInstance().generarExamen( getNombreMateria(), cantTeoricos, cantPracticos, 
				cantTeoricas, cantPracticas, getUnidades(), Calendar.getInstance()
				);
		
		setMensaje("Examen generado y guardado correctamente.");
		
	}
	/**
	 * retorna a la pagina principal (Home)
	 */
	public void onVolver(IRequestCycle cycle){
		String[] aux = {""};
		unidades = aux;
		unidadesString = "";
		cycle.activate("Home");
	}
}
