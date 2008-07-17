package ar.utn.tadp.techie.seis.persistance;

import java.util.HashSet;
import java.util.Set;

import ar.utn.tadp.techie.seis.persistance.MateriaDAO;


public class MateriaDAOMock implements MateriaDAO {

	private static MateriaDAOMock instance;
	
	public static MateriaDAOMock getInstance()
	{
		if(instance == null)
			instance = new MateriaDAOMock();
    	return  instance;
	}
	@Override
	public Set<String> listarMaterias() {
		
		Set<String> materias = new HashSet<String>();
		materias.add( "TADP" );
		materias.add( "Paradigmas" );
		materias.add( "Diseño" );
		return materias;
	}

}
