package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Gruppi_DAO {
	
	// Insert in un gruppo
	public void InsGruppo(String Nome_Gruppo, String Descrizione, String Creatore) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_GRUPPO(?, ?, ?)");
			Call.setString(1, Nome_Gruppo);
			Call.setString(2, Descrizione);
			Call.setString(3, Creatore);
			Call.execute();
			System.out.println("Gruppo Inserito");
			
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
		
	}

	// Delete su un gruppo
	public void DelGruppo(String Nome_Gruppo) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIMOZIONE_GRUPPO(?)");
			Call.setString(1, Nome_Gruppo);
			Call.execute();
			System.out.println("Gruppo eliminato");
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}

	// Update di un gruppo
	public void UpGruppo(String Nome_Gruppo, String Creatore, String Campo_Mod, String New_Val) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL MODIFICA_GRUPPO(?, ?, ?, ?)");
			Call.setString(1, Campo_Mod);
			Call.setString(2, New_Val);
			Call.setString(3, Creatore);
			Call.setString(4, Nome_Gruppo);
			Call.execute();
			System.out.println("Gruppo aggiornato ");
		
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
	}

	// Select singolo gruppo per nome
	public Gruppi SelSigGruppo(String Nome) {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM GRUPPI WHERE NOME = '" + Nome + "'");

			try {

				Gruppi Res_Gruppo;
				rs.next();

				Res_Gruppo = new Gruppi(rs.getString("Nome"), rs.getDate("Data_Creazione"),
						rs.getString("Descrizione"), rs.getInt("OnlineC"), rs.getString("FK_Nome_Utente"));
				
				
				return Res_Gruppo;

			} catch (SQLException e) {
				System.out.println("query fallita");
			
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Select tutti i gruppi
	public List<Gruppi> SelAllGruppo() {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM GRUPPI");
			
			try {
				List<Gruppi> Rec_Gruppi = new ArrayList<Gruppi>();
				
				Gruppi Stampa;

				while (rs.next()) {
					Stampa = new Gruppi(rs.getString("Nome"), rs.getDate("Data_Creazione"),
							rs.getString("Descrizione"), rs.getInt("OnlineC"), rs.getString("FK_Nome_Utente"));
					
					Rec_Gruppi.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Gruppi;

			} catch (SQLException e) {
				System.out.println("query fallita");
				
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
	
	// Select tutti i gruppi creati da un utente
		public List<Gruppi> SelAllGruppoUtente(String Nome_Utente) {

			try {

				ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM GRUPPI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");
				
				try {
					List<Gruppi> Rec_Gruppi = new ArrayList<Gruppi>();
					
					Gruppi Stampa;

					while (rs.next()) {
						Stampa = new Gruppi(rs.getString("Nome"), rs.getDate("Data_Creazione"),
								rs.getString("Descrizione"), rs.getInt("OnlineC"), rs.getString("FK_Nome_Utente"));
						
						Rec_Gruppi.add(Stampa);
						Stampa = null;
					}
					
					
					return Rec_Gruppi;

				} catch (SQLException e) {
					System.out.println("query fallita");
					
					return null;
				}

			} catch (Exception e) {
				System.out.println("Errore");
				
				return null;
			}
		}
		
		
}
