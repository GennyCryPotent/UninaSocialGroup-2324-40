package StandardJava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;


public class Profili_DAO {

	DB_Connection DB = new DB_Connection();

	public Profili_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}

	// Insert in un gruppo
	public void InsProfilo(String Nome_Utente, String Password, String Nome, String Cognome, String Genere, Date DataNascita) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL CREA_PROFILO(?, ?, ?, ?, ?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Password);
			Call.setString(3, Nome);
			Call.setString(4, Cognome);
			Call.setString(5, Genere); 
			Call.setDate(6, DataNascita);
			Call.execute();
			System.out.println("Utente creato");
			
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
		
	}

	// Delete su un gruppo
	public void DelProfilo(String Nome_Utente) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_PROFILO(?)");
			Call.setString(1, Nome_Utente);
			Call.execute();
			System.out.println("Profilo eliminato");
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}

	// Update di un gruppo
	public void UpProfilo(String Nome_Utente, String Campo_Mod, String New_Val) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL MODIFICA_PROFILO(?, ?, ?)");
			Call.setString(1, Campo_Mod);
			Call.setString(2, New_Val);
			Call.setString(3, Nome_Utente);
			Call.execute();
			System.out.println("Profilo aggiornato ");
		
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
	}

	// Select singolo gruppo per nome
	public Profili SelSigProfilo(String Nome) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM PROFILI WHERE NOME_UTENTE = '" + Nome + "'");

			try {

				Profili Res_Profilo;
				rs.next();

				Res_Profilo = new Profili(rs.getString("Nome_Utente"), rs.getString("Password"), rs.getString("Nome"), 
										  rs.getString("Cognome"), rs.getString("Genere"), rs.getDate("Data_Nascita"));
				
				
				return Res_Profilo;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
			
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Select singolo gruppo
	public List<Profili> SelAllProfili() {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM PROFILI");
			
			try {
				List<Profili> Rec_Profili = new ArrayList<Profili>();
				
				Profili Stampa;

				while (rs.next()) {
					Stampa =  new Profili(rs.getString("Nome_Utente"), rs.getString("Password"), rs.getString("Nome"), 
							  rs.getString("Cognome"), rs.getString("Genere"), rs.getDate("Data_Nascita"));
					
					Rec_Profili.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Profili;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
				
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
	
	public void Close_Connection() { 
		DB.close();
	}
	
	
}
