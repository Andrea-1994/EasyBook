package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import carrelloPackage.CarrelloBean;
import libriPackage.LibroBean;

public class CarrelloBeanTest {
	CarrelloBean carrello;
	LibroBean libro;
	LibroBean libro1;
	ArrayList<LibroBean> libri;
	
	@Before
	public void setUp() throws Exception {
		libro = new LibroBean("1111111111111" ,"titolo" ,"italiano",1990,"categoria",2,"autore","casa_ed","11/11/11","10/10/10","t0000");
		libro1 = new LibroBean("1111111111112" ,"titolo" ,"italiano",1990,"categoria",2,"autore","casa_ed","11/11/11","10/10/10","t0000");
		libri = new ArrayList<LibroBean>();
		libri.add(libro);
		carrello = new CarrelloBean (libri);
		
		
	}

	@After
	public void tearDown() throws Exception {
		carrello = null;
		libro = null;
		libro1 = null;
		libri= null;
	}
	
	@Test
	public void testGetLibri() {
		assertEquals(libri,carrello.getlibri());
	}
	
	@Test
	public void testTrovaLibro() {
		boolean flag;
		flag = carrello.trovalibro(libro);
		assertEquals(true,flag);
	}
	
	@Test
	public void testAddLibro() {
		carrello.addlibro(libro1);
		boolean flag;
		flag = carrello.trovalibro(libro1);
		assertEquals(true,flag);
	}

	@Test
	public void testDeleteLibro() {
		boolean flag;
		carrello.deletelibro(libro1);
		flag = carrello.trovalibro(libro1);
		assertEquals(false,flag);
	}
	
	


}



