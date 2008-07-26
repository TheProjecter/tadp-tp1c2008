package ar.utn.tadp.techie.seis.web;



import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;


import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.*;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;


import ar.utn.tadp.techie.seis.ADesarrollar;
import ar.utn.tadp.techie.seis.Ejercicio;
import ar.utn.tadp.techie.seis.Examen;
import ar.utn.tadp.techie.seis.ExamenBuilder;
import ar.utn.tadp.techie.seis.ExamenSinPreguntasNiEjerciciosException;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.Materia;
import ar.utn.tadp.techie.seis.PreguntasInsuficientesException;
import ar.utn.tadp.techie.seis.PrototipoItem;
import ar.utn.tadp.techie.seis.pools.MateriasPool;
import ar.utn.tadp.techie.seis.pools.MateriasPoolMock;

public abstract class GenerarExamen extends BasePage {

	private Materia materia;	//referencia a la materia a la que se agregara el examen
	//private String  unidadesString; //para mantener unidades agregadas y poder agregar nuevas
	private Collection<String> unidades;
	//private String[] unidades = {""}; //para mostrar en la tabla
	
	int cantTeoricos  = 0;
	int cantPracticos = 0;
	int cantTeoricas  = 0;
	int cantPracticas = 0;
	int complejidad = 0;
	
	public abstract String getNombreMateria();
	public abstract void setNombreMateria(String materia);
	
	public Materia getMateria(){
		return materia;
	}
	public void setMateria(Materia m){
		materia = m;
	}
	
	public GenerarExamen(){
		unidades = new HashSet<String>();
		//unidadesString = "";
		//Collection<String> unidades=new HashSet<String>();
	}
	
	@InitialValue("literal:3")
	public abstract String getCantidadPreguntasTeoricas();
	@InitialValue("literal:2")
	public abstract String getCantidadPreguntasPracticas();
	@InitialValue("literal:3")
	public abstract String getCantidadEjerciciosTeoricos();
	@InitialValue("literal:2")
	public abstract String getCantidadEjerciciosPracticos();
	@InitialValue("literal:2")
	public abstract String getComplejidadText();
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
		
		String[] unidadesArray={""};
		unidadesArray = MateriasPool.getInstance().getUnidadesList(getNombreMateria()).toArray(unidadesArray); 
		//String[] unidades = materia.getUnidades();
		 //MateriasPoolMock.getInstance().getUnidades(getMateria());
        return new StringPropertySelectionModel(unidadesArray);
    }
	
	public Collection<String> getUnidades(){	       
		
			return unidades;
		
	}
	public void setUnidades(Collection<String>  u){
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
/*
		unidadesString += getUnidad() + ",";
		//probe usando un set y el mensaje toArray pero despues de agregar una unidad no cambia.. =(... 
		//por eso opte por esta solucion.
		setUnidades(unidadesString.split(","));*/
		unidades.add(getUnidad());
	}
	
	/**
	 * Genera y guarda un examen con los requisitos definidos en la pagina.
	 * @param cycle
	 */
	public void onGenerarExamen(IRequestCycle cycle){
		
		//TODO --juanmi
		cantTeoricos  = Integer.parseInt(getCantidadEjerciciosTeoricos());
		cantPracticos = Integer.parseInt(getCantidadEjerciciosPracticos());
		cantTeoricas  = Integer.parseInt(getCantidadPreguntasTeoricas());
		cantPracticas = Integer.parseInt(getCantidadPreguntasPracticas());
		complejidad = Integer.parseInt(getComplejidadText());
		/*
		Collection<String> unidadesAbarcadas = new HashSet<String>();

		for(String u : unidades){
			unidadesAbarcadas.add(u);
	
		}*/
                materia = MateriasPool.getInstance().getMateria(getNombreMateria());
		ExamenBuilder examenBuilder = new ExamenBuilder(materia,  unidades, Calendar.getInstance());
		
		if( hayEjerciciosPracticos()){
			
			PrototipoItem<Ejercicio> protoEjercicioPractico = new PrototipoItem<Ejercicio>(Ejercicio.class);
			protoEjercicioPractico.setComplejidad(complejidad);
			protoEjercicioPractico.setTipo(ItemExamen.TiposItem.PRACTICO);
			examenBuilder.putPrototipo(protoEjercicioPractico, cantTeoricos);
		}
		if( hayEjerciciosTeoricos()){
			
			PrototipoItem<Ejercicio> protoEjercicioTeorico = new PrototipoItem<Ejercicio>(Ejercicio.class);
			protoEjercicioTeorico.setComplejidad(complejidad);
			protoEjercicioTeorico.setTipo(ItemExamen.TiposItem.TEORICO);
			examenBuilder.putPrototipo(protoEjercicioTeorico, cantPracticos);
		}

		if( hayPreguntasTeoricas()){

			PrototipoItem<ADesarrollar> protoPreguntaTeorica = new PrototipoItem<ADesarrollar>(ADesarrollar.class);
			protoPreguntaTeorica.setComplejidad(complejidad);
			protoPreguntaTeorica.setTipo(ItemExamen.TiposItem.TEORICO);
			examenBuilder.putPrototipo(protoPreguntaTeorica, cantTeoricas);
		}
		if( hayPreguntasPracticas()){

			PrototipoItem<ADesarrollar> protoPreguntaPractica = new PrototipoItem<ADesarrollar>(ADesarrollar.class);
			protoPreguntaPractica.setComplejidad(complejidad);
			protoPreguntaPractica.setTipo(ItemExamen.TiposItem.PRACTICO);
			examenBuilder.putPrototipo(protoPreguntaPractica, cantPracticas);
		}
		
		try {
			Examen examen = examenBuilder.generarExamen();
			//agrego el examen a la materia ? o le aviso al pool y el se encarga? ... 
			//opto por la opcion b por ahora
			MateriasPool.getInstance().addExamen(getNombreMateria(), examen);
			
		} catch (PreguntasInsuficientesException e) {
			setMensaje("No se pudo generar el Examen. "+ e.toString());
			//e.printStackTrace();
		} catch (ExamenSinPreguntasNiEjerciciosException e) {
			setMensaje("No se pudo generar el Examen. "+ e.toString());
			//e.printStackTrace();
		}catch(Exception e){
			setMensaje("No se pudo generar el Examen. "+ e.toString());
		}
		
		
		setMensaje("Examen generado y guardado correctamente.");
		
	}
	private boolean hayPreguntasPracticas() {
		// TODO Auto-generated method stub
		return cantPracticas>0;
	}
	private boolean hayPreguntasTeoricas() {
		// TODO Auto-generated method stub
		return cantTeoricas>0;
	}
	private boolean hayEjerciciosTeoricos() {
		// TODO Auto-generated method stub
		return cantTeoricos>0;
	}
	private boolean hayEjerciciosPracticos() {
		// TODO Auto-generated method stub
		return cantPracticos>0;
	}
	/**
	 * retorna a la pagina principal (Home)
	 */
	public void onVolver(IRequestCycle cycle){
		/*String[] aux = {""};
		unidades = aux;
		unidadesString = "";*/
		unidades.clear();
		cycle.activate("Home");
	}
}
