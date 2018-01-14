package libriPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import libriPackage.LibriManager;

/**
 * Questa classe è la classe gestore degli oggetti di tipo "LibroBean". Si occupa
 * di effettuare tutte le operazioni sul database che coinvolgono i libri.
 */
public class LibriManager {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/bibliotecadb");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static Connection getConnection () throws SQLException {
		if (ds == null) {
			String URL ="jdbc:mysql://localhost:3306";
			String database ="bibliotecadb";
				String driver = "com.mysql.jdbc.Driver";
			String user = "root";
			String password = "asdf";
			
			
			try {
				Class.forName(driver);
				return  DriverManager.getConnection(URL + "/"+database,user,password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		} else if (ds != null) {
			return ds.getConnection();
		}
		return null;
		
	}

	
	
	
	private static final String TABLE_NAME = "libro";

	/**
	 * Questo metodo permette di aggiungere un libro al database,
	 * ha come parametro il LibroBean del libro da aggiungere.
	 */
	public synchronized boolean doAggiungiLibro(LibroBean libro) throws SQLException {
		boolean status= true;
		Connection connection = null;
		PreparedStatement preparedStatement = null, ps1 = null;
        String prova = "select * from libro where ISBN = ?";
		String insertSQL = "INSERT INTO LIBRO (ISBN,titolo,lingua,anno_pubblicazione,categoria,autore,casa_editrice) VALUES (?, ?, ?, ?, ?, ?,?)";

		try {
			connection = getConnection();
			ps1 = connection.prepareStatement(prova);//per vedere se il libro già c'è
			ps1.setString(1, libro.getISBN());
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				status = false;
			}
			if(status) {
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, libro.getISBN());
			preparedStatement.setString(2, libro.gettitolo());
			preparedStatement.setString(3, libro.getlingua());
			preparedStatement.setInt(4, libro.getanno_pubblicazione());
			preparedStatement.setString(5, libro.getcategoria());
			preparedStatement.setString(6, libro.getautore());
			preparedStatement.setString(7, libro.getcasa_editrice());

			preparedStatement.executeUpdate();
			
			 }
            
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
			
		}
		return status;
	}
	
	
	/**
	 * Questo metodo permette di rimuovere un libro dal database,
	 * ha come parametro l'ISBN del libro da rimuovere.
	 */
	
	public synchronized boolean doRimuoviLibro(String ISBN) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String deleteSQL = "DELETE FROM libro WHERE ISBN = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, ISBN);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	
	/**
	 * Questo metodo permette di ricercare un libro nel database in base all'ISBN,
	 * ha come parametro l'ISBN del libro da trovare 
	 * e come valore di ritorno il LibroBean del libro trovato.
	 */
	public synchronized LibroBean doRetrieveByKey(String ISBN) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		LibroBean bean = new LibroBean();

		String selectSQL = "SELECT ISBN,titolo,lingua,anno_pubblicazione,categoria,cod_pr,autore,casa_editrice,data_inizio,data_fine FROM prestito," + LibriManager.TABLE_NAME + " WHERE ISBN = ? and (cod_pr IS NULL or cod_pr = CodicePR)";
   
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			
			preparedStatement.setString(1, ISBN);
			

			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				bean.setISBN(rs.getString("ISBN"));
				bean.settitolo(rs.getString("TITOLO"));
				bean.setlingua(rs.getString("LINGUA"));
				bean.setanno_pubblicazione(rs.getInt("ANNO_PUBBLICAZIONE"));
				bean.setcategoria(rs.getString("CATEGORIA"));
				bean.setcod_pr(rs.getInt("COD_PR"));
				bean.setautore(rs.getString("AUTORE"));
				bean.setcasa_editrice(rs.getString("CASA_EDITRICE"));
			    bean.setdata_inizio(rs.getString("DATA_INIZIO"));
			    bean.setdata_fine(rs.getString("DATA_FINE"));
				
			}
			
		

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	
	
	/**
	 * Questo metodo permette di ricercare un libro nel database in base all'Titolo,
	 * ha come parametro il titolo del libro da trovare 
	 * e come valore di ritorno il LibroBean del libro trovato.
	 */
	public synchronized LibroBean doRetrieveBySearch(String titolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		LibroBean bean = new LibroBean();

		String selectSQL = "SELECT ISBN,titolo,lingua,anno_pubblicazione,categoria,cod_pr,autore,casa_editrice,data_inizio,data_fine FROM prestito," + LibriManager.TABLE_NAME + " WHERE TITOLO = ? and (cod_pr IS NULL or cod_pr = CodicePR)";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, titolo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setISBN(rs.getString("ISBN"));
				bean.settitolo(rs.getString("TITOLO"));
				bean.setlingua(rs.getString("LINGUA"));
				bean.setanno_pubblicazione(rs.getInt("ANNO_PUBBLICAZIONE"));
				bean.setcategoria(rs.getString("CATEGORIA"));
				bean.setcod_pr(rs.getInt("COD_PR"));
				bean.setautore(rs.getString("AUTORE"));
				bean.setcasa_editrice(rs.getString("CASA_EDITRICE"));
				bean.setdata_inizio(rs.getString("DATA_INIZIO"));
				bean.setdata_fine(rs.getString("DATA_FINE"));
			}
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return  bean;
	}

	
	/**
	 * Questo metodo restituisce la lista di tutti i libri nel database,
	 * ha come parametro l'ordine della lista 
	 * e come valore di ritorno la lista dei libri.
	 */
	
	public synchronized Collection<LibroBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<LibroBean> libri = new LinkedList<LibroBean>();

		String selectSQL = "SELECT * FROM " + LibriManager.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				LibroBean bean = new LibroBean();

				bean.setISBN(rs.getString("ISBN"));
				bean.settitolo(rs.getString("TITOLO"));
				bean.setlingua(rs.getString("LINGUA"));
				bean.setanno_pubblicazione(rs.getInt("ANNO_PUBBLICAZIONE"));
				bean.setcategoria(rs.getString("CATEGORIA"));
				bean.setcod_pr(rs.getInt("COD_PR"));
				bean.setautore(rs.getString("AUTORE"));
				bean.setcasa_editrice(rs.getString("CASA_EDITRICE"));
				libri.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return libri;
	}
	
	
}