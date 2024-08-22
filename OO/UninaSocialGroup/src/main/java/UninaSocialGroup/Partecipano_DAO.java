package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Partecipano_DAO {

	// Insert in Partecipano
	public void InsPartecipante(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_PARTECIPANO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Utente aggiunto al gruppo");
			
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
	}

	// Delete su un Partecipano
	public void DelPartecipante(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL ABBANDONA_GRUPPO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Partecipante eliminato");
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}
	
	// Delete di tutti i Partecipano di un profilo su un gruppo
		public void DelPartecipanoProfilo(String Nome_Utente, String Nome_Gruppo) {

			try {
				CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIMOZIONE_Partecipano_PROFILO(?, ?)");
				Call.setString(1, Nome_Utente);
				Call.setString(2, Nome_Gruppo);
				Call.execute();
				System.out.println("Partecipano eliminato");
			
			} catch (Exception e) {
				System.out.println(e);
				
			}
			
		}

	// Select tutti i gruppi dove partecipa l'utente
	public List<Partecipano> SelSigPartecipanoGruppo(String Nome_Utente) {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM PARTECIPANO WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {

				List<Partecipano> Rec_Partecipano = new ArrayList<Partecipano>();
				
				Partecipano Stampa;

				while (rs.next()) {
					Stampa =  new Partecipano(rs.getString("FK_Nome_Utente"),rs.getString("FK_Nome_Gruppo"));
					
					Rec_Partecipano.add(Stampa);
					Stampa = null;
				}
				
				return Rec_Partecipano;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
			
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Select tutti i partecipanti di un gruppo
	public List<Partecipano> SelAllPartecipanoGruppo(String Nome_Gruppo){

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM Partecipano WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'" );
			
			try {
				List<Partecipano> Rec_Partecipano = new ArrayList<Partecipano>();
				
				Partecipano Stampa;

				while (rs.next()) {
					Stampa =  new Partecipano(rs.getString("FK_Nome_Utente"),rs.getString("FK_Nome_Gruppo"));
					
					Rec_Partecipano.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Partecipano;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
				
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
}

