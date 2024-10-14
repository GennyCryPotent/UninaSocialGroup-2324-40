package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartecipanoDAO {

	// Insert in Partecipano
	public void InsPartecipante(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_PARTECIPANO(?, ?)");
			call.setString(1, Nome_Utente);
			call.setString(2, Nome_Gruppo);
			call.execute();
			System.out.println("Utente aggiunto al gruppo");
			call.close();
			
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
	}

	// Delete su un Partecipano
	public void DelPartecipante(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL ABBANDONA_GRUPPO(?, ?)");
			call.setString(1, Nome_Utente);
			call.setString(2, Nome_Gruppo);
			call.execute();
			System.out.println("Partecipante eliminato");
			call.close();
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}

	// Select tutti i gruppi dove partecipa l'utente
	public List<Partecipano> SelSigPartecipanoGruppo(String Nome_Utente) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM PARTECIPANO WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {

				List<Partecipano> recPartecipano = new ArrayList<Partecipano>();
				
				Partecipano stampa;

				while (rs.next()) {
					stampa =  new Partecipano(rs.getString("FK_Nome_Utente"),rs.getString("FK_Nome_Gruppo"));
					
					recPartecipano.add(stampa);
					stampa = null;
				}
				
				return recPartecipano;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
			
				return null;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Select tutti i partecipanti di un gruppo
	public List<Partecipano> SelAllPartecipanoGruppo(String Nome_Gruppo){

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM Partecipano WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'" );
			
			try {
				List<Partecipano> recPartecipano = new ArrayList<Partecipano>();
				
				Partecipano stampa;

				while (rs.next()) {
					stampa =  new Partecipano(rs.getString("FK_Nome_Utente"),rs.getString("FK_Nome_Gruppo"));
					
					recPartecipano.add(stampa);
					stampa = null;
				}
				
				
				return recPartecipano;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
				
				return null;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
	
	
	// Ritorna true se l'utente partecipa al gruppo
	public boolean SelPartecipanoGruppo(String Nome_Gruppo, String Nome_Utente){

		try {
			
				
			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM Partecipano WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "' AND FK_NOME_UTENTE = '" + Nome_Utente + "'" );
			
			try {

					while (rs.next()) {
						return true;
					}
					return false;

					
				
			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
				
				return false;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");
			
			return false;
		}
	}
}

