package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contenuti_DAO {

	// Insert in Contenuti
	public void InsContenuto(String Foto, String Testo, String Nome_Gruppo, String Nome_Utente) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_CONTENUTO(?, ?, ?, ?)");
			Call.setString(1, Foto);
			Call.setString(2, Testo);
			Call.setString(3, Nome_Gruppo);
			Call.setString(4, Nome_Utente);
			Call.execute();
			System.out.println("Contenuto creato");
			Call.close();

		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Delete su un Contenuto
	public void DelContenuto(int Id_Contenuto) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIMOZIONE_CONTENUTO(?)");
			Call.setInt(1, Id_Contenuto);
			Call.execute();
			System.out.println("Contenuto eliminato");
			Call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i contenuti di un profilo su un gruppo
	public void DelContenutoProfilo(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIMOZIONE_CONTENUTO_PROFILO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Contenuti eliminato");
			Call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Update di un Contenuto
	public void UpContenuto(String Nome_Utente, String New_Val, int Id_Contenuto) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL MODIFICA_CONTENUTO(?, ?, ?)");
			Call.setString(1, New_Val);
			Call.setString(2, Nome_Utente);
			Call.setInt(3, Id_Contenuto);
			Call.execute();
			System.out.println("Contenuto aggiornato ");
			Call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select singolo Contenuto
	public Contenuti SelSigContenuto(int Id_Contenuto) {

		try {

			ResultSet rs = Gestione_Finestre.DB
					.ExeQuery("SELECT * FROM CONTENUTI WHERE ID_CONTENUTO = " + Id_Contenuto);

			try {

				Contenuti Res_Contenuto;
				rs.next();

				Res_Contenuto = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
						rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

				return Res_Contenuto;

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

	// Select tutti i contenuti di un gruppo
	public List<Contenuti> SelAllContenutiGruppo(String Nome_Gruppo) {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '"
					+ Nome_Gruppo + "' ORDER BY DATA_CREAZIONE DESC");

			try {
				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rs.next()) {
					Stampa = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
							rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

					Rec_Contenuti.add(Stampa);
					Stampa = null;
				}

				return Rec_Contenuti;

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

	// Select tutti i contenuti dei gruppi a cui un utente partecipa
	public List<Contenuti> SelContenutiGruppiUtente(String Nome_Utente) {

		String TMP_Nome_Gruppo;

		try {

			ResultSet rsNome_Gruppo = Gestione_Finestre.DB
					.ExeQuery("SELECT FK_NOME_GRUPPO From Partecipano Where FK_Nome_Utente = '" + Nome_Utente + "'"); // Prende
			// i nomi
			// dei gruppi
			// dove l'utente
			// è creatore

			try {

				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rsNome_Gruppo.next()) {

					TMP_Nome_Gruppo = rsNome_Gruppo.getString("FK_NOME_GRUPPO");

					ResultSet rsF = Gestione_Finestre.DB.ExeQuery("SELECT * FROM Contenuti WHERE FK_Nome_Gruppo = '"
							+ TMP_Nome_Gruppo + "' ORDER BY DATA_CREAZIONE DESC");

					while (rsF.next()) {
						Stampa = new Contenuti(rsF.getInt("Id_Contenuto"), rsF.getDate("Data_Creazione"),
								rsF.getString("Testo"), rsF.getString("FK_Nome_Gruppo"),
								rsF.getString("FK_Nome_Utente"));

						Rec_Contenuti.add(Stampa);

						Stampa = null;
					}
					rsF.close();
				}
				
				return Rec_Contenuti;

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

	// Select tutti i contenuti di un utente
	public List<Contenuti> SelAllContenutiUtente(String Nome_Utente) {

		try {

			ResultSet rs = Gestione_Finestre.DB
					.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rs.next()) {
					Stampa = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
							rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

					Rec_Contenuti.add(Stampa);
					Stampa = null;
				}

				return Rec_Contenuti;

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

	// Select tutti i contenuti di un utente in un gruppo
	public List<Contenuti> SelContenutiUtenteGruppo(String Nome_Gruppo, String Nome_Utente) {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '"
					+ Nome_Gruppo + "' AND FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rs.next()) {
					Stampa = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
							rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

					Rec_Contenuti.add(Stampa);
					Stampa = null;
				}

				return Rec_Contenuti;

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

	// Select tutti i contenuti di un gruppo
	public List<Contenuti> SelAllContenutiMeseGruppo(String Nome_Gruppo, int Mese) {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '"
					+ Nome_Gruppo + "' AND EXTRACT(MONTH from DATA_CREAZIONE) =  " + Mese);

			try {
				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rs.next()) {
					System.out.println(rs.getString("Testo"));
					Stampa = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
							rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

					Rec_Contenuti.add(Stampa);
					Stampa = null;
				}

				return Rec_Contenuti;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());

				return null;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore : " + e.getMessage());

			return null;
		}
	}

}
