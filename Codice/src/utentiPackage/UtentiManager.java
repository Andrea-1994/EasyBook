package utentiPackage;

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

import utentiPackage.UtentiManager;


/**
 * Questa classe è la classe gestore degli utenti". Si occupa
 * di effettuare tutte le operazioni sul database che coinvolgono gli utenti.
 */
public class UtentiManager {

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

	
	/**
	 * Questo metodo permette di verificare i dati inseriti per il login,
	 * ha come parametro il numero di tessera e la password, 
	 * e come valore di ritorno un booleano che rappresenta l'esito della verifica.
	 */
	
	public synchronized boolean Accesso(String num_tessera, String pass) throws SQLException {
		boolean status = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
	    String selectSQL = "SELECT * FROM socio where num_tessera=? and pass=?";
	
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, num_tessera);
			preparedStatement.setString(2, pass);

		    rs = preparedStatement.executeQuery();
		    status = rs.next();
			
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
	 * Questo metodo restituisce il nome dell'utente loggato,
	 * ha come parametro il numero di tessera e la password, 
	 * e come valore di ritorno L'UtenteBean con il nome dell'utente.
	 */
	public synchronized UtenteBean doNome(String num_tessera, String pass) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		UtenteBean bean = new UtenteBean();
		
	    String selectSQL = "SELECT * FROM socio where num_tessera=? and pass=?";
	
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, num_tessera);
			preparedStatement.setString(2, pass);

			ResultSet rs = preparedStatement.executeQuery();
		    
		    while (rs.next()) {
				bean.setnome_S(rs.getString("NOME_S"));
				
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
	 * Questo metodo permette di registrare un nuovo utente nel database,
	 * ha come parametro L'UtenteBean dell'utente da registrare, 
	 * e come valore di ritorno un booleano che rappresenta l'esito della registrazione.
	 */
	public synchronized boolean registra(UtenteBean utente) throws SQLException {
		boolean status= true;
		Connection connection = null;
		PreparedStatement preparedStatement = null,ps1 = null;
		
        String prova = "select * from socio where num_tessera = ?";
		String insertSQL = "INSERT INTO SOCIO (num_tessera, pass, nome_S, cognome_S, data_nascita_S, indirizzo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = getConnection();
			ps1 = connection.prepareStatement(prova);//per vedere se l'utente è già registrato
			ps1.setString(1, utente.getnum_tessera());
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				status = false;
			}
			if(status) {
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getnum_tessera());
			preparedStatement.setString(2, utente.getpass());
			preparedStatement.setString(3, utente.getnome_S());
			preparedStatement.setString(4, utente.getcognome_S());
			preparedStatement.setString(5, utente.getdata_nascita_S());
			preparedStatement.setString(6, utente.getindirizzo());
			

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
	 * Questo metodo restituisce la lista di tutti gli utenti nel databse,
	 * ha come parametro L'ordine della lista, 
	 * e come valore di ritorno la lista di utenti.
	 */
	
	public synchronized Collection<UtenteBean> doRetrieveAlluser(String ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();

		String selectSQL = "SELECT * FROM socio";

		if (ordine != null && !ordine.equals("")) {
			selectSQL += " ORDER BY " + ordine;
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();

				bean.setnum_tessera(rs.getString("NUM_TESSERA"));
				bean.setpass(rs.getString("PASS"));
				bean.setnome_S(rs.getString("NOME_S"));
				bean.setcognome_S(rs.getString("COGNOME_S"));
				bean.setdata_nascita_S(rs.getString("DATA_NASCITA_S"));
				bean.setindirizzo(rs.getString("INDIRIZZO"));
				utenti.add(bean);
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
		return utenti;
	}
	
	
	/**
	 * Questo metodo permette di rimuovere un utente dal database,
	 * ha come parametro L'UtenteBean dell'utente da rimuovere, 
	 */
	public synchronized boolean doDelete(String num_tessera) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM socio WHERE num_tessera = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, num_tessera);

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
}