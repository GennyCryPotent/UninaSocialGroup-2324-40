package StandardJava;

import java.sql.*;
import java.util.ArrayList;

public class DB_Connection {

	
	String driver;
	String URL;
	public Connection c;
	
	public DB_Connection(String driver, String uRL) {
		this.driver=driver;
		URL = uRL;
	}
	
	
	
	public Connection getC() {
		return c;
	}


	public void connect(String user, String psw) {
		
		try {
			
			// REGISTRAZIONE DEL DRIVER
			Class.forName(driver);		
			//CONNESSIONE		
			c =DriverManager.getConnection(URL, user, psw);
			
		}catch(ClassNotFoundException e) {
			System.out.println("Driver non trovato");
		}
		catch(SQLException se) {
			System.out.println("Connessione fallita");
		}
		
	}
	
	public ResultSet ExeQuery(String query) {
		
		
		try {
		//ESEGUIRE UNA QUERY
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
		}catch(SQLException se) {
			System.out.println("query fallita");
			return null;
		}
		
		
	}
	
	public void ExeUpdate(String comando) {
		
		
		try {
		//ESEGUIRE UN UPDATE
		PreparedStatement update = c.prepareStatement(comando); 
		update.executeUpdate(comando); 
		update.close();
		}catch(SQLException se) {
			System.out.println("update fallito");
			
		}
		
		
	}
	
	/*
	 * ESEGUE UN UPDATE CON UNA PREPAREDSTATEMENT
	 * NELLA STRINGA DELL'UPDATE INSERIAMO UN "?", SUCCESIVAMENTE USIAMO SETSTRING(INDEX, "STRING")
	 * IN STRING POSSIAMO METTERE QUALSIASI STRINGA IN MODO DA MODIFICARE L'UPDATE
	 * */
	public void ExeUpdatePreparedeStatement(String comando) {
		
		
		try {
			PreparedStatement update = c.prepareStatement(comando); 
			update.setString(1, "giuseppe");
			update.executeUpdate(); 
			update.close();
			} catch (SQLException e) {
			System.out.println("update fallito");
		} 
		

	}
	
	public void close() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Connessione fallita");
		}
	}
  }






