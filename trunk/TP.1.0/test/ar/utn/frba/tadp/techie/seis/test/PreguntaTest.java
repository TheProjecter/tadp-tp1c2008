package ar.utn.frba.tadp.techie.seis.test;
/**
 * @author Juanmi
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.tadp.techie.seis.exambase.ADesarrollar;

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
