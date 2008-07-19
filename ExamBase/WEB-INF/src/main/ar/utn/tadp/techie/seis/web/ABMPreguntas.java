package ar.utn.tadp.techie.seis.web;


import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

import ar.utn.tadp.techie.seis.ADesarrollar;
import ar.utn.tadp.techie.seis.Ejercicio;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.ItemExamen.TiposItem;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;
import org.apache.tapestry.annotations.*;


public abstract class ABMPreguntas extends BasePage {
	

	private String[] unidades;
	private String[] complejidades = {"F�cil","Normal","Dif�cil","Muy Dif�cil","Imposible"};
	private String[] tiposItem = {"Pregunta","Ejercicio"};
	private String[] tiposContenidoItem = {"Teorico","Pr�ctico","TeoricoPr�ctico"};
	
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
	@InitialValue("literal:Escriba aqu� el enunciado.")
	public abstract String getTextoItem();
	
	public void onGuardar(IRequestCycle cycle){
		ItemExamen item;
		int complejidad=Integer.parseInt(getComplejidad());
		
		if(getTipoItem().equals("Pregunta"))
				item = new ADesarrollar(getUnidad(), complejidad, getTextoItem(), getTipoContenidoItem());
		else item = new Ejercicio(getUnidad(), complejidad, getTextoItem(),
				getTipoContenidoItem());
		MateriasPoolMock.getInstance().setNewItem(getMateria(),item);
	}
	private TiposItem getTipoContenidoItem(){
		
		if(getTipoContenidoItemAsString().equals("Te�rico"))
			return ItemExamen.TiposItem.TEORICO;
		else if (getTipoContenidoItemAsString().equals("Pr�ctico"))
			return ItemExamen.TiposItem.PRACTICO;
		else return ItemExamen.TiposItem.TEORICOPRACTICO;
		
	}
}
