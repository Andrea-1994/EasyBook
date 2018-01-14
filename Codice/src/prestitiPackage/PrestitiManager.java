package prestitiPackage;

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

import libriPackage.LibroBean;
import prestitiPackage.PrestitiManager;

/**
 * Questa classe è la classe gestore dei prestiti. Si occupa
 * di effettuare tutte le operazioni sul database che coinvolgono i prestiti.
 */

public class PrestitiManager {
	
    

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

	private static final String TABLE_NAME = "libro";



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
	
	/**
	 * Questo metodo permette di creare un nuovo prestito,
	 * ha come parametro il numero di tessera dell'utente che ha effettuato il prestito.
	 */
	
	public synchronized boolean doPrestito(String num_tessera) throws SQLException {
		boolean status = false;
		Connection connection = null;
		PreparedStatement ps = null;
		int rs = 0;
		

		String sql = ("INSERT INTO prestito (data_inizio, data_fine, cod_S) VALUE(CURDATE(),(SELECT DATE_ADD(CURDATE(),INTERVAL 1 MONTH)),?)");
		
		
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
		
		    ps.setString(1, num_tessera);
			    
		    rs = ps.executeUpdate();
			if(rs>0) {
				status=true;
			}
			

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	    return status;
	
	}

	
	/**
	 * Questo metodo permette aggiornare il codice prestito associato ad un libro,
	 * ha come parametro l'ISBN del libro da aggiornare. 
	 */
	public synchronized boolean doAggiorna(String ISBN) throws SQLException {
		boolean status = false;
		Connection connection = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String sql = ("UPDATE libro SET cod_pr = (SELECT MAX(CodicePR) FROM prestito) WHERE ISBN = ? ");
		
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
		
		    ps.setString(1, ISBN);
			    
		    rs = ps.executeUpdate();
			if(rs>0) {
				status=true;
			}
			

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	    return status;
	
	}
	
	
	/**
	 * Questo metodo permette di chiudere un prestito,
	 * ha come parametro il codice identificativo del prestito.
	 */
	public synchronized Boolean doChiudi(int cod_pr) throws SQLException {
		boolean status = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rs = 0;


		String deleteSQL = "DELETE FROM prestito WHERE CodicePR = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, cod_pr);

			rs = preparedStatement.executeUpdate();
			if(rs>0) {
				status=true;
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
	 * Questo metodo restituisce una lista di tutti i prestiti attivi,
	 * ha come parametro l'ordine della lista, 
	 * e come valore di ritorno la lista di libri in prestito.
	 */
	public synchronized Collection<LibroBean> doTrovaPrestiti(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<LibroBean> libri = new LinkedList<LibroBean>();

		String selectSQL = "SELECT * FROM prestito," + PrestitiManager.TABLE_NAME+ " WHERE cod_pr = CodicePR";
		   
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}
			

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
			    bean.setdata_inizio(rs.getString("DATA_INIZIO"));
			    bean.setdata_fine(rs.getString("DATA_FINE"));
			    bean.setcod_s(rs.getString("COD_S"));
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
	
	
	/**
	 * Questo metodo permette di ricercare i prestiti associati ad un utente,
	 * ha come parametro il cnumero di tessera dell'utente, 
	 * e come valore di ritorno la lista dei libri in prestito associati all'utente.
	 */
	public synchronized Collection<LibroBean> doTrovaPrestito(String num_tessera) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<LibroBean> libri = new LinkedList<LibroBean>();

		String selectSQL = "SELECT ISBN,titolo,lingua,anno_pubblicazione,categoria,cod_pr,autore,casa_editrice,data_inizio,data_fine FROM prestito," + PrestitiManager.TABLE_NAME + " WHERE cod_S = ? and cod_pr = CodicePR";
		   
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			
			preparedStatement.setString(1, num_tessera);
			

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
			    bean.setdata_inizio(rs.getString("DATA_INIZIO"));
			    bean.setdata_fine(rs.getString("DATA_FINE"));
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