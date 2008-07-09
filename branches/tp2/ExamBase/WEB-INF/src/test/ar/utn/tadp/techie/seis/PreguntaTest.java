package ar.utn.tadp.techie.seis;


/**
 * @author Juanmi
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PreguntaTest {
	private ADesarrollar preguntaADesarrollar;
	
	@Before
	public void setUp() throws Exception {
		preguntaADesarrollar = new ADesarrollar(null, 0, null, null);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testGetUnidadTematica()
	{
		assertNull(preguntaADesarrollar.getUnidadTematica());
	}
	
	
}