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
import ar.utn.tadp.techie.seis.pools.MateriasPool;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;
import java.util.Collection;
import org.apache.tapestry.annotations.*;


public abstract class ABMPreguntas extends BasePage {
	

	private String[] unidades={""};
	private String[] complejidades = {"1","2","3","4","5"};
	private String[] tiposItem = {Pregunta.getAlias(), Ejercicio.getAlias()};
	private String[] tiposContenidoItemAsString = {ItemExamen.TiposItem.TEORICO.toString(),
										   ItemExamen.TiposItem.PRACTICO.toString(),
										   ItemExamen.TiposItem.TEORICOPRACTICO.toString()
										   };												
	public ABMPreguntas(){}
        
        public void updateUnidades()
        {
            Collection<String> unidadesCol = MateriasPool.getInstance().getUnidadesList(getMateria());
            unidades = unidadesCol.toArray(unidades);
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
		
        return new StringPropertySelectionModel(tiposContenidoItemAsString);
    }
	
	/**
	 * 
	 * @return
	 */
	private TiposItem getTipoContenidoItem(){
		
		if(getTipoContenidoItemAsString().equals(ItemExamen.TiposItem.TEORICO.toString()))
			return ItemExamen.TiposItem.TEORICO;
		else if (getTipoContenidoItemAsString().equals(ItemExamen.TiposItem.PRACTICO.toString()))
			return ItemExamen.TiposItem.PRACTICO;
		else return ItemExamen.TiposItem.TEORICOPRACTICO;
		
	}
	
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
		
		MateriasPool.getInstance().addItemToMateria(getMateria(), item);
		
		setMensaje("Item Guardado Correctamente.");
		
		cycle.activate("ABMPreguntas");
	}
	
	private boolean esEjercicio() {
		return getTipoItem().equals(Ejercicio.getAlias());
	}

	private boolean esPregunta() {
		
		return getTipoItem().equals(Pregunta.getAlias());
	}

	private boolean noHayTextoEnunciado() {
		
		return (getTextoItem()==null);
	}

	/**
	 * retorna a la pagina principal (Home)
	 */
	public void onVolver(IRequestCycle cycle){
		cycle.activate("Home");
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
}
