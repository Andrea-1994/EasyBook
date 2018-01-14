package carrelloPackage;

import java.util.ArrayList;
import java.util.List;

import libriPackage.LibroBean;

/**
 * 
 *Questa classe è un bean del carrello e contiene tutte le informazioni relative al carrello
 *
 */

public class CarrelloBean {
    /**
     * Questo attributo è una lista di Libri presenti nel carrello.
     */
	private List<LibroBean> libri;
	/**
	 * Questo costruttore setta la lista di libri nel carrello vuota.
	 */
	public CarrelloBean() {
		libri = new ArrayList<LibroBean>();
	}
	/**
	 * Questo costruttore setta la lista di libri nel carrello.
	 */
	public CarrelloBean(ArrayList<LibroBean> libri) {
		this.libri = libri;
	}
	/**
	 * Aggiunge un libro alla lista dei libri
	 * @param libro setta il libro da aggiungere alla lista
	 */
	public void addlibro(LibroBean libro) {
		libri.add(libro);
	}
	
	/**
	 * Rimuove un libro dalla lista dei libri
	 */
	public void deletelibro(LibroBean libro) {
		for(LibroBean lib : libri) {
		if(lib.getISBN().equals(libro.getISBN())) {
				libri.remove(lib);
				break;
			}
		}
   }
	
	/**
	 * Cerca un libro nella lista libri e restituisce il risultato della ricerca.
	 */
	
	public boolean trovalibro(LibroBean libro) {
		boolean status=false;
		for(LibroBean lib : libri) {
		if(lib.getISBN().equals(libro.getISBN())) {
				status= true;
				break;
			}
		}
		return status;
   }
	
	/**
	 * Restituisce la lista dei libri nel carrello.
	 */
	public List<LibroBean> getlibri() {
		return  libri;
	}
}
