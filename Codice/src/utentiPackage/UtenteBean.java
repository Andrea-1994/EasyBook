package utentiPackage;

/**Questa è la classe bean dell'utente, contiene tutte le informazioni
 * relative all'utente*/

public class UtenteBean {

	/**Questo attributo è il numero di tessera dell'utente e lo identifica.
	 * è reso accessibile tramite metodi get e set*/
	String num_tessera;
	
	/**Questo attributo è la password dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	String pass;
	
	/**Questo attributo è il nome dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	String nome_S;
	
	/**Questo attributo è il cognome dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	String cognome_S;
	
	/**Questo attributo è la data di nascita dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	String data_nascita_S;
	
	/**Questo attributo è l'indirizzo dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	String indirizzo;
	
	/**Il costruttore vuoto*/
	public UtenteBean(){};
	
	/**Questo costruttore vuole come parametri un valore per ogni
	 * variabile dell'utente*/
	public UtenteBean(String num_tessera,String pass,String nome_S,String cognome_S,String data_nascita_S,String indirizzo) {
	    this.num_tessera=num_tessera;
		this.pass=pass;
		this.nome_S=nome_S;
		this.cognome_S=cognome_S;
		this.data_nascita_S= data_nascita_S;
		this.indirizzo=indirizzo;
		
	}
	
	public String getnum_tessera() {
		return num_tessera;
	}

	public void setnum_tessera(String num_tessera) {
		this.num_tessera = num_tessera;
	}


	public String getpass() {
		return pass;
	}

	public void setpass(String pass) {
		this.pass = pass;
	}

	

	public String getnome_S() {
		return nome_S;
	}

	public void setnome_S(String nome_S) {
		this.nome_S = nome_S;
	}

	
	public String getcognome_S() {
		return cognome_S;
	}

	public void setcognome_S(String cognome_S) {
		this.cognome_S = cognome_S;
	}
	
	
	public String getdata_nascita_S() {
		return data_nascita_S;
	}

	public void setdata_nascita_S(String data_nascita_S) {
		this.data_nascita_S = data_nascita_S;
	}
	
	public String getindirizzo() {
		return indirizzo;
	}

	public void setindirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	@Override
	public String toString() {return(nome_S);}

}
