package ar.utn.tadp.techie.seis.web;


import ar.utn.tadp.techie.seis.Materia;
import ar.utn.tadp.techie.seis.pools.MateriasPool;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.InitialValue;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;
import java.util.Collection;

public abstract class ABMUnidades extends BasePage {

	private String[] unidades={"u1","u2"};

	
	public ABMUnidades(){		
	}
	
	
	@InitialValue("literal:Ingrese una unidad")
	public abstract String getUnidad();
	public abstract void setUnidad(String s);
	
	
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
	public abstract void setMateria(String materia);

	public String[] getUnidades(){	 

            return unidades;
	}
	public void setUnidades(String[] u){
		unidades = u;
	}
	
	/**
	 * @author juanmi
	 * Este metodo stea la lista de items pertenecientes
	 *  a una materia seleccionada en el combo box de la pagina
	 * **/
	public void onGuardar(IRequestCycle cycle)
	{
		String materiaSeleccionada = getMateria();
		
//		MateriasPoolMock.getInstance().setUnidad(materiaSeleccionada, getUnidad());
//                Materia materia = MateriasPool.getInstance().getMateria(materiaSeleccionada);
                MateriasPool.getInstance().addUnidadTematica(materiaSeleccionada, getUnidad());
                updateUnidades();
		//unidades = MateriasPoolMock.getInstance().getUnidadesAsStringArray(materiaSeleccionada);
//		unidades = (String[]) MateriasPoolMock.getInstance().getUnidades(getMateria()).toArray(unidades);		
                
			
		cycle.activate("ABMUnidades");
	}	
	/**
	 * retorna a la pagina principal (Home)
	 */
	public void onVolver(IRequestCycle cycle){
		cycle.activate("Home");
	}

    public void updateUnidades()
    {
        Collection<String> unidadesCol = MateriasPool.getInstance().getUnidadesList(getMateria());
        unidades = unidadesCol.toArray(unidades);
    }
	
}

