package test;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import prestitiPackage.PrestitiManager;
import libriPackage.LibroBean;


public class PrestitiManagertest {
	PrestitiManager model;

	
	@Before
	public void setUp() throws Exception {
		model = new PrestitiManager();		
	}

	@After
	public void tearDown() throws Exception {
		model = null;
	}
	
	@Test
	public void testCreaOrdine() throws SQLException {
		boolean flag;
		flag = model.doPrestito("t00002");
		assertEquals(true,flag);
		flag = model.doAggiorna("0000000000000");//libro non esiste
		assertEquals(false,flag);
		flag = model.doAggiorna("9780399167324");//libro esiste
		assertEquals(true,flag);
	}

	@Test
	public void testReturnOrdiniAmministratore() throws SQLException {
		Collection<LibroBean> lista = model.doTrovaPrestiti("cod_pr");
		assertNotNull(lista);
	}

	@Test
	public void testReturnOrdiniUtente() throws SQLException {
		Collection<LibroBean> lista = model.doTrovaPrestito("t00001");
		assertNotNull(lista);
	}

	@Test
	public void testChiusuraPrestito() throws SQLException {
		boolean flag;
		flag = model.doChiudi(86);
		assertEquals(true,flag);
		flag = model.doChiudi(86);//prestito già chiuso
		assertEquals(false,flag);
		
	}

	


}
