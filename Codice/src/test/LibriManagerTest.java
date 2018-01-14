package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import libriPackage.LibroBean;
import libriPackage.LibriManager;


public class LibriManagerTest {
	LibriManager model;
 	LibroBean libEsiste;
 	LibroBean libNuovo;
 	LibroBean libNonEsiste;
	
	@Before
	public void setUp() throws Exception {
		model = new LibriManager();
		libNuovo = new LibroBean("1111111111111" ,"titolo" ,"italiano",1990,"categoria",0,"autore","casa_ed","","","");
		libEsiste = new LibroBean("9781533419613" ,"Timeless" ,"italiano",2016,"fantascienza",0,"Luigi Toto","Fanucci","","","");
		libNonEsiste = new LibroBean("2222222222222" ,"titolo" ,"italiano",1990,"categoria",0,"autore","casa_ed","","","");
	}

	@After
	public void tearDown() throws Exception {
		model = null;
		libNuovo = null;
		libEsiste = null;
	}

	@Test
	public void testReturnLibri() throws SQLException {
		Collection<LibroBean> lista = model.doRetrieveAll("titolo");
		assertNotNull(lista);
	}

	@Test
	public void testAggiungiLibro() throws SQLException {
		boolean flag;
		flag = model.doAggiungiLibro(libEsiste); //provo ad aggiungere un libro già esistente
		assertEquals(false, flag);
		flag = model.doAggiungiLibro(libNuovo);//aggiungo un libro nuovo
		assertEquals(true, flag);
	}
	
	@Test
	public void testRicercaPerChiave() throws SQLException {
		LibroBean libro;
		libro = model.doRetrieveByKey("9781533419613");
		assertNotNull(libro);
	}
	
	@Test
	public void testRicercaPerTitolo() throws SQLException {
		LibroBean libro;
		libro = model.doRetrieveByKey("Timeless");
		assertNotNull(libro);
	}

	
	@Test
	public void testRimuoviLibro() throws SQLException {
		boolean flag;
		flag = model.doRimuoviLibro(libNonEsiste.getISBN());//elimino libro che non esiste
		assertEquals(false, flag);
		flag = model.doRimuoviLibro(libNuovo.getISBN());//elimino libro esistente
		assertEquals(true,flag);
	}


	

}
