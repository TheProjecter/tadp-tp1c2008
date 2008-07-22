package ar.utn.tadp.techie.seis.web;


import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

import ar.utn.tadp.techie.seis.ADesarrollar;
import ar.utn.tadp.techie.seis.Ejercicio;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Pregunta;
import ar.utn.tadp.techie.seis.ItemExamen.TiposItem;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;
import org.apache.tapestry.annotations.*;


public abstract class ABMPreguntas extends BasePage {
	

	private String[] unidades;
	private String[] complejidades = {"1","2","3","4","5"};
	private String[] tiposItem = {Pregunta.class.toString(), Ejercicio.class.toString()};
	private String[] tiposContenidoItem = {ItemExamen.TiposItem.TEORICO.toString(),
										   ItemExamen.TiposItem.PRACTICO.toString(),
										   ItemExamen.TiposItem.TEORICOPRACTICO.toString()
										   };												
	public ABMPreguntas(){
		
		unidades = MateriasPoolMock.getInstance().getUnidadesAsStringArray(getMateria());
		
	}
	
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

	public IPropertySelectionModel getUnidadesModel() {
			
        return new StringPropertySelectionModel(unidades);
    }
	
	public IPropertySelectionModel getComplejidadesModel() {
		
        return new StringPropertySelectionModel(complejidades);
    }
	public IPropertySelectionModel getTiposItemModel() {
		
        return new StringPropertySelectionModel(tiposItem);
    }
	public IPropertySelectionModel getTiposContenidoItemModel() {
		
        return new StringPropertySelectionModel(tiposContenidoItem);
    }
	
	public abstract String getUnidad();
	public abstract String getTipoItem();
	public abstract String getComplejidad();
	public abstract String getTipoContenidoItemAsString();
	@InitialValue("literal:Escriba aquí el enunciado.")
	public abstract String getTextoItem();
	@InitialValue("literal:")
	public abstract String getMensaje();
	public abstract void setMensaje(String m);

	
	/**
	 * Genera el item (pregunta o ejercicio) con los parametros elegidos.
	 * @param cycle
	 */
	public void onGuardar(IRequestCycle cycle){
		
		if(noHayTextoEnunciado()) {
			setMensaje("Escriba un enunciado.");
			cycle.activate("GenerarExamen");
			return;
		}
		
		ItemExamen item=null;
		int complejidad=Integer.parseInt(getComplejidad());
		
		if(esPregunta())
				item = new ADesarrollar(getUnidad(), complejidad, getTextoItem(), getTipoContenidoItem());
		if(esEjercicio())
				item = new Ejercicio(getUnidad(), complejidad, getTextoItem(),getTipoContenidoItem());
		
		MateriasPoolMock.getInstance().setNewItem(getMateria(),item);
	}
	
	private boolean esEjercicio() {
		return getTipoItem().equals(Ejercicio.class.toString());
	}

	private boolean esPregunta() {
		
		return getTipoItem().equals(Pregunta.class.toString());
	}

	private boolean noHayTextoEnunciado() {
		
		return (getTextoItem()==null);
	}

	/**
	 * 
	 * @return
	 */
	private TiposItem getTipoContenidoItem(){
		
		if(getTipoContenidoItemAsString().equals("Teórico"))
			return ItemExamen.TiposItem.TEORICO;
		else if (getTipoContenidoItemAsString().equals("Práctico"))
			return ItemExamen.TiposItem.PRACTICO;
		else return ItemExamen.TiposItem.TEORICOPRACTICO;
		
	}
}
