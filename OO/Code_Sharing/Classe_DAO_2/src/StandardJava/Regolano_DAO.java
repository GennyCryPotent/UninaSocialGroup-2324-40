package StandardJava;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Regolano_DAO {

	
	DB_Connection DB = new DB_Connection();

	public Regolano_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}
	
	// Insert in Regolano
	public void InsAmministratore(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL CREA_REGOLANO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Amministratore aggiunto al gruppo");
			
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
		
	}

	// Delete su un Regolano
	public void DelAmministratore(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_REGOLANO(?, ?)");
			Call.setString(1, Nome_Gruppo);
			Call.setString(2, Nome_Utente);
			Call.execute();
			System.out.println("Amministratore eliminato");
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}

	// Select tutti i gruppi dove regola l'utente
	public List<Regolano> SelSigRegolano(String Nome_Utente) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM REGOLANO WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {

				List<Regolano> Rec_Regolano = new ArrayList<Regolano>();
				
				Regolano Stampa;

				while (rs.next()) {
					
					Stampa =  new Regolano(rs.getString("FK_Nome_Utente"),rs.getString("FK_Nome_Gruppo"));
					
					Rec_Regolano.add(Stampa);
					Stampa = null;
				}
				
				return Rec_Regolano;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
			
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Select tutti gli amministratori di un gruppo
	public List<Regolano> SelAllRegolanoGruppo(String Nome_Gruppo){

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM REGOLANO WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'" );
			
			try {
				List<Regolano> Rec_Regolano = new ArrayList<Regolano>();
				
				Regolano Stampa;

				while (rs.next()) {
					Stampa =  new Regolano(rs.getString("FK_Nome_Utente"),rs.getString("FK_Nome_Gruppo"));
					
					Rec_Regolano.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Regolano;

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
