package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utentiPackage.UtenteBean;

public class UtenteBeanTest {
	private UtenteBean test;
	
	@Before
	public void setUp() throws Exception {
		test = new UtenteBean("t00020","password","Marco","D'amore","12/03/1990","Via Mazzini");
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}
	
	@Test
	public void testGetNumTessera() {
		String t = test.getnum_tessera();
		assertEquals("t00020",t);
	}
	
	@Test
	public void testGetPassword() {
		String t = test.getpass();
		assertEquals("password",t);
	}

	
	@Test
	public void testGetNome() {
		String t = test.getnome_S();
		assertEquals("Marco",t);
	}
	
	@Test
	public void testGetCognome() {
		String t = test.getcognome_S();
		assertEquals("D'amore",t);
	}
	
	@Test
	public void testGetDataNascita() {
		String t = test.getdata_nascita_S();
		assertEquals("12/03/1990",t);
	}
	
	
	@Test
	public void testGetIndirizzo() {
		String t = test.getindirizzo();
		assertEquals("Via Mazzini",t);
	}

	@Test
	public void testSetNumTessera() {
		String t = "t00020";
		test.setnum_tessera(t);
		assertEquals("t00020",test.getnum_tessera());
	}
	
	@Test
	public void testSetPassword() {
		String t = "password";
		test.setpass(t);
		assertEquals("password",test.getpass());
	}
	
	@Test
	public void testSetNome() {
		String t = "Marco";
		test.setnome_S(t);
		assertEquals("Marco",test.getnome_S());
	}
	
	@Test
	public void testSetCognome() {
		String t = "D'amore";
		test.setcognome_S(t);
		assertEquals("D'amore",test.getcognome_S());
	}
	
	@Test
	public void testSetDataNascita() {
		String t = "12/03/1990";
		test.setdata_nascita_S(t);
		assertEquals("12/03/1990",test.getdata_nascita_S());
	}
	
	@Test
	public void testSetIndirizzo() {
		String t = "Via Mazzini";
		test.setindirizzo(t);
		assertEquals("Via Mazzini",test.getindirizzo());
	}
	
	
	
}
