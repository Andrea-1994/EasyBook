package test;

import static org.junit.Assert.*;


import java.sql.SQLException;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utentiPackage.UtenteBean;
import utentiPackage.UtentiManager;

public class UtentiManagerTest {

	private UtentiManager model;
	private UtenteBean utenteEsistente;
	private UtenteBean utenteNonEsistente;

	
	@Before
	public void setUp() throws Exception {
		
		

		model = new UtentiManager();
		utenteEsistente = new UtenteBean("t00001","asdf","Andrea","Di Lucia","1994-04-30","via Marthin Luther King 24");
		utenteNonEsistente = new UtenteBean("t00020","password","Marco","D'amore","12/03/1990","Via Mazzini");
	}

	@After
	public void tearDown() throws Exception {
		model = null;
		utenteEsistente = null;
		utenteNonEsistente = null;
	}
	
	@Test
	public void testRegistrazioneUtente() throws SQLException {
		boolean flag ;
		//registrazione con numero di tessera già esistente
		flag = model.registra(utenteEsistente);
		assertEquals(false,flag);
		
		//registrazione con numero di tessera non esistente
		flag = model.registra(utenteNonEsistente);
		assertEquals(true,flag);
		
	}

	@Test
	public void testLogin() throws SQLException {
		boolean flag ;
		//test su un account non presente nel database
		flag=model.Accesso("00000", "000000");
		assertEquals(false,flag);
		//test su account esistente
		flag=model.Accesso("t00001","asdf");
		assertEquals(true,flag);
		//test sull'amministratore
		flag=model.Accesso("admin","admin");
	     assertEquals(true,flag);
	}
	
	@Test
	public void testRestituisciNome() throws SQLException {
		UtenteBean u;
		//test su un account non presente nel database
		u = model.doNome("0000","0000");
		assertNull(u.getnome_S());
		//test su account esistente
		u = model.doNome("t00001","asdf");
		assertEquals(utenteEsistente.getnome_S(), u.getnome_S());
	}


	@Test
	public void testEliminaUtente() throws SQLException {
		boolean flag;
		//test su account non presente nel databse
		flag = model.doDelete("0000");
		assertEquals(false,flag);
		//test su account presente nel database
		flag = model.doDelete("t00020");
		assertEquals(true,flag);
	}
	

}
