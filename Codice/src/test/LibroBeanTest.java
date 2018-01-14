package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import libriPackage.LibroBean;

public class LibroBeanTest {
	private LibroBean lib;

	@Before
	public void setUp() throws Exception {
		lib = new LibroBean("1111111111111" ,"titolo" ,"italiano",1990,"categoria",2,"autore","casa_ed","11/11/11","10/10/10","t0000");
	}

	@After
	public void tearDown() throws Exception {
		lib = null;
	}

	
	//metodi get
	@Test
	public void testGetISBN() {
		String t = lib.getISBN();
		assertEquals("1111111111111",t);
	}

	
	@Test
	public void testGetTitolo() {
		String t= lib.gettitolo();
		assertEquals("titolo",t);
	}
	
	@Test
	public void testGetLingua() {
		String t = lib.getlingua();
		assertEquals("italiano",t);
	}
	
	@Test
	public void testGetAnnoPubblicazione() {
		int  t = lib.getanno_pubblicazione();
		assertEquals(1990,t);
	}
	
	@Test
	public void testGetCategoria() {
		String  t = lib.getcategoria();
		assertEquals("categoria",t);
	}
	
	
	@Test
	public void testGetCod_pr() {
		int  t = lib.getcod_pr();
		assertEquals(2,t);
	}
	
	@Test
	public void testGetAutore() {
		String  t = lib.getautore();
		assertEquals("autore",t);
	}
	
	@Test
	public void testGetCasaEditrice() {
		String  t = lib.getcasa_editrice();
		assertEquals("casa_ed",t);
	}
	
	@Test
	public void testGetDataInizio() {
		String  t = lib.getdata_inizio();
		assertEquals("11/11/11",t);
	}
	
	@Test
	public void testGetDataFine() {
		String  t = lib.getdata_fine();
		assertEquals("10/10/10",t);
	}
	
	@Test
	public void testGetCod_s() {
		String  t = lib.getcod_s();
		assertEquals("t0000",t);
	}
	
	
	
	//metodi set
	
	@Test
	public void testSetISBN() {
		 lib.setISBN("1111111111111");
		assertEquals("1111111111111",lib.getISBN());
	}
	
	@Test
	public void testSetTitolo() {
		 lib.settitolo("titolo");
		assertEquals("titolo",lib.gettitolo());
	}
	
	@Test
	public void testSetLingua() {
		 lib.setlingua("italiano");
		assertEquals("italiano",lib.getlingua());
	}
	
	@Test
	public void testSetAnnoPubblicazione() {
		 lib.setanno_pubblicazione(1990);;
		assertEquals(1990,lib.getanno_pubblicazione());
	}
	
	@Test
	public void testSetCategoria() {
		 lib.setcategoria("categoria");
		assertEquals("categoria",lib.getcategoria());
	}
	
	@Test
	public void testSetCod_pr() {
		 lib.setcod_pr(2);
		assertEquals(2,lib.getcod_pr());
	}
	
	@Test
	public void testSetAutore() {
		 lib.setautore("autore");
		assertEquals("autore",lib.getautore());
	}
	
	@Test
	public void testSetCasaEditrice() {
		 lib.setcasa_editrice("casa_ed");
		assertEquals("casa_ed",lib.getcasa_editrice());
	}
	
	@Test
	public void testSetDataInizio() {
		 lib.setdata_inizio("11/11/11");
		assertEquals("11/11/11",lib.getdata_inizio());
	}
	
	@Test
	public void testSetDataFine() {
		 lib.setdata_fine("10/10/10");
		assertEquals("10/10/10",lib.getdata_fine());
	}
	
	@Test
	public void testSetCod_s() {
		 lib.setcod_s("t0000");
		assertEquals("t0000",lib.getcod_s());
	}
	
	
}
