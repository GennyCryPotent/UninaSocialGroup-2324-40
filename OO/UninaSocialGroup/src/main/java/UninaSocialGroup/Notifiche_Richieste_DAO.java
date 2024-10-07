package UninaSocialGroup;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;


public class Notifiche_Richieste_DAO {

	// Insert in Notifiche
	public void InsNotifica_R(String Nome_Gruppo, String Nome_Utente) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_RICHIESTA(?, ?)");
			Call.setString(1, Nome_Gruppo);
			Call.setString(2, Nome_Utente);
			Call.execute();
			System.out.println("Richiesta inviata al gruppo");
			Call.close();
		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete su Notifiche
	public void DelNotifica_R(int Id_Notifica_Richieste) {

		try {
			PreparedStatement Remove = Gestione_Finestre.DB.getC().prepareStatement(
					"DELETE FROM NOTIFICHE_RICHIESTE WHERE ID_NOTIFICA_RE = " + Id_Notifica_Richieste);
			Remove.execute();
			Remove.close();
			System.out.println("Notifica Eliminata");

		} catch (Exception e) {
			System.out.println(e);

		}
	}
	
	public void Accetta_Richiesta(String Nome_Gruppo, String Nome_Utente) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL ACCETTA_PROFILO(?, ?)");
			Call.setString(1, Nome_Utente );
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Profilo accettato");
			Call.close();
		} catch (Exception e) {
			System.out.println("Errore");

		}
	}
	
	public void Rifiuta_Richiesta(String Nome_Gruppo, String Nome_Utente) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIFIUTA_PROFILO(?, ?)");
			Call.setString(1, Nome_Utente );
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Profilo riiutato");
			Call.close();
		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select tutte le Notifiche che riceve un creatore
	public List<Notifiche> SelNoitificheRichiesteDiUnCreatore(String Nome_Utente) {

		String TMP_Nome_Gruppo;

		try {

			ResultSet rsNome_Gruppo = Gestione_Finestre.DB.ExeQuery("SELECT Nome From Gruppi Where FK_Nome_Utente = '" + Nome_Utente + "'"); // Prende 
																										//i nomi
																										// dei gruppi
																										// dove l'utente
																										// è creatore

			try {

				List<Notifiche> Rec_Notifiche = new ArrayList<Notifiche>();

				Notifiche Stampa;

				while (rsNome_Gruppo.next()) {

					TMP_Nome_Gruppo = rsNome_Gruppo.getString("Nome");

					ResultSet rsF = Gestione_Finestre.DB.ExeQuery("SELECT * FROM NOTIFICHE_RICHIESTE WHERE FK_Nome_Gruppo = '"
							+ TMP_Nome_Gruppo + "' AND Esitato = '0'");

					while (rsF.next()) {
						Stampa = new Notifiche(rsF.getInt("Id_Notifica_RE"), rsF.getString("Testo"),
								rsF.getDate("Data_Notifica"), rsF.getString("Esitato"), rsF.getString("FK_Nome_Gruppo"),
								rsF.getString("FK_Nome_Utente"));

						Rec_Notifiche.add(Stampa);

						Stampa = null;
					}
					rsF.close();
				}

				return Rec_Notifiche;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());

				return null;
			} finally {
				rsNome_Gruppo.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	
	
	// Select se esiste un utente che ha in sospeso una richiesta di un gurppo
		public boolean SelNoitificheRichiesteDiUtenteDiGruppo(String Nome_Gruppo, String Nome_Utente) {

			try {

				ResultSet rsF = Gestione_Finestre.DB.ExeQuery("SELECT * FROM NOTIFICHE_RICHIESTE WHERE FK_Nome_Gruppo = '"
						+ Nome_Gruppo + "' AND FK_Nome_Utente = '" + Nome_Utente  +"' AND Esitato = '0'");
				try {

					

						while (rsF.next()) {

							return true;
						}
					


				} catch (SQLException e) {
					System.out.println("query fallita: " + e.getMessage());

					return false;
				} finally {
					rsF.close(); // chiude sempre il cursore
				}

			} catch (Exception e) {
				System.out.println("Errore");

				return false;
			}
			return false;
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Select tutte le notifiche archiviate di un utente (esitato!=0)
	public List<Notifiche> SelNoitificheArchiviate(String Nome_Utente) {

		int check = 0;
		String TMP_Nome_Gruppo;
		List<Notifiche> Rec_Notifiche = new ArrayList<Notifiche>();

		try {

			ResultSet rsNome_Gruppo = Gestione_Finestre.DB.ExeQuery("SELECT Nome From Gruppi Where FK_Nome_Utente = '" + Nome_Utente + "'");

			while (rsNome_Gruppo.next()) {

				CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("{? = CALL Mostra_Archiviata_F(?, ?)}");

				// Registra il parametro di output (in tutti casi saranno dei cursori)
				Call.registerOutParameter(1, OracleTypes.CURSOR);

				// Imposta il parametro di input (se la function ha dei parametri)
				Call.setString(2, Nome_Utente); // Es. nome del gruppo come parametro IN

				// Imposta il parametro di input (se la function ha dei parametri)
				Call.setString(3, rsNome_Gruppo.getString("Nome")); // Es. nome del gruppo come parametro IN

				// Esegui la chiamata alla funzione
				Call.execute();

				// Ottieni il risultato come ResultSet
				ResultSet rs = (ResultSet) Call.getObject(1); // Restituisce l'oggeto 1 cioè il cursore

				try {

					Notifiche Stampa;

					while (rs.next()) {

						Stampa = new Notifiche(rs.getInt("Id_Notifica_RE"), rs.getString("Testo"),
								rs.getDate("Data_Notifica"), rs.getString("Esitato"), rs.getString("FK_Nome_Gruppo"),
								rs.getString("FK_Nome_Utente"));

						check = NoDuplicati(check, Rec_Notifiche, Stampa, rs.getInt("Id_Notifica_RE"));

						Stampa = null;
					}

				} catch (SQLException e) {
					System.out.println("query fallita" + e.getMessage());
					return null;
				}
			}
		} catch (Exception e) {
			System.out.println("Errore" + e.getMessage());
			return null;
		}

		return Rec_Notifiche;

	}

	// Non inserisce le notifiche duplicate nella lista
	private int NoDuplicati(int check, List<Notifiche> Rec_Notifiche, Notifiche AddR,
			int Id_Richiesta) {

		if (check == 0) { // se il primo elemento della lista

			Rec_Notifiche.add(AddR);
			return check = 1;
		} else {

			for (int i = 0; i < Rec_Notifiche.size(); i++) {
				if (Rec_Notifiche.get(i).getId_Notifica() == Id_Richiesta) { // Controllo il duplicato vedendo se esiste già
																						// l'identificativo della
																						// notifica
					check = 2;
					continue; //esco dal ciclo
				}
			}

			if (check == 1) { //se non esiste un duplicato, viene aggiunto l'elemento alla lista
				Rec_Notifiche.add(AddR);
			}

			return check = 1;
		}

	}
}
