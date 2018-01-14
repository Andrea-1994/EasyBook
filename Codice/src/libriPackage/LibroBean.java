package libriPackage;


/**Questa � la classe bean del libro, contiene tutte le informazioni
 * relative al libro*/

public class LibroBean {

	/**Questo attributo � l'ISBN del libro e lo identifica.
	 * � reso accessibile tramite metodi get e set*/
	String ISBN;
	
	/**Questo attributo � il titolo del libro.
	 * � reso accessibile tramite metodi get e set*/
	String titolo;
	
	/**Questo attributo � la lingua del libro.
	 * � reso accessibile tramite metodi get e set*/
	String lingua;
	
	/**Questo attributo � l'anno di pubblicazione del libro.
	 * � reso accessibile tramite metodi get e set*/
	int anno_pubblicazione;
	
	/**Questo attributo � la categoria del libro.
	 * � reso accessibile tramite metodi get e set*/
	String categoria;
	
	/**Questo attributo � il codice identificativo del prestito associato al libro.
	 * � reso accessibile tramite metodi get e set*/
	int cod_pr;
	
	/**Questo attributo � l'autore del libro.
	 * � reso accessibile tramite metodi get e set*/
	String autore;
	
	/**Questo attributo � la casa editrice del libro.
	 * � reso accessibile tramite metodi get e set*/
	String casa_editrice;
	
	/**Questo attributo � la data di inizio del prestito associato al libro.
	 * � reso accessibile tramite metodi get e set*/
	String data_inizio;
	
	/**Questo attributo � la data di fine del prestito associato al libro.
	 * � reso accessibile tramite metodi get e set*/
	String data_fine;
	
	/**Questo attributo � il codice identificativo dell'utente che ha in prestito il libro.
	 * � reso accessibile tramite metodi get e set*/
	String cod_s;
	
	/**Il costruttore vuoto*/
	public LibroBean(){};
	
	/**Questo costruttore vuole come parametri un valore per ogni
	 * variabile del libro*/
	public LibroBean(String ISBN,String titolo,String lingua,int anno_pubblicazione,String categoria,int cod_pr,String autore, String casa_editrice, String data_inizio, String data_fine, String cod_s ) {
		this.ISBN= ISBN;
		this.titolo=titolo;
		this.lingua=lingua;
		this.anno_pubblicazione= anno_pubblicazione;
		this.categoria=categoria;
		this.cod_pr= cod_pr;
		this.autore= autore;
		this.casa_editrice= casa_editrice;
		this.data_inizio=data_inizio;
		this.data_fine= data_fine;
		this.cod_s=cod_s;
		
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}


	public String gettitolo() {
		return titolo;
	}

	public void settitolo(String titolo) {
		this.titolo = titolo;
	}

	

	public String getlingua() {
		return lingua;
	}

	public void setlingua(String lingua) {
		this.lingua = lingua;
	}

	
	public int getanno_pubblicazione() {
		return anno_pubblicazione;
	}

	public void setanno_pubblicazione(int anno_pubblicazione) {
		this.anno_pubblicazione = anno_pubblicazione;
	}
	
	
	public String getcategoria() {
		return categoria;
	}

	public void setcategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getcod_pr() {
		return cod_pr;
	}

	public void setcod_pr(int cod_pr) {
		this.cod_pr = cod_pr;
	}
	
	
	public String getautore() {
		return autore;
	}

	public void setautore(String autore) {
		this.autore = autore;
	}
	
	public String getcasa_editrice() {
		return casa_editrice;
	}

	public void setcasa_editrice(String casa_editrice) {
		this.casa_editrice= casa_editrice;
	}

	public String getdata_inizio() {
		return data_inizio;
	}

	public void setdata_inizio(String data_inizio) {
		this.data_inizio = data_inizio;
	}
	
	public String getdata_fine() {
		return data_fine;
	}

	public void setdata_fine(String data_fine) {
		this.data_fine = data_fine;
	}
	
	public String getcod_s() {
		return cod_s;
	}

	public void setcod_s(String cod_s) {
		this.cod_s = cod_s;
	}
	

}
