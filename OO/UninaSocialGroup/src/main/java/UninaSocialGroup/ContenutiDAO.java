package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContenutiDAO {

	// Insert in Contenuti
	public void InsContenuto(String foto, String testo, String nomeGruppo, String nomeUtente) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_CONTENUTO(?, ?, ?, ?)");
			call.setString(1, foto);
			call.setString(2, testo);
			call.setString(3, nomeGruppo);
			call.setString(4, nomeUtente);
			call.execute();
			System.out.println("Contenuto creato");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Delete su un Contenuto
	public void DelContenuto(int idContenuto) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_CONTENUTO(?)");
			call.setInt(1, idContenuto);
			call.execute();
			System.out.println("Contenuto eliminato");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i contenuti di un profilo su un gruppo
	public void DelContenutoProfilo(String nomeUtente, String nomeGruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_CONTENUTO_PROFILO(?, ?)");
			call.setString(1, nomeUtente);
			call.setString(2, nomeGruppo);
			call.execute();
			System.out.println("Contenuti eliminato");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Update di un Contenuto
	public void UpContenuto(String nomeUtente, String newVal, int idContenuto) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL MODIFICA_CONTENUTO(?, ?, ?)");
			call.setString(1, newVal);
			call.setString(2, nomeUtente);
			call.setInt(3, idContenuto);
			call.execute();
			System.out.println("Contenuto aggiornato ");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select singolo Contenuto
	public Contenuti SelSigContenuto(int idContenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM CONTENUTI WHERE ID_CONTENUTO = " + idContenuto);

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
	public List<Contenuti> SelAllContenutiGruppo(String nomeGruppo) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '"
					+ nomeGruppo + "' ORDER BY DATA_CREAZIONE DESC");

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
	public List<Contenuti> SelContenutiGruppiUtente(String nomeUtente) {

		String TMP_Nome_Gruppo;

		try {

			ResultSet rsNome_Gruppo = GestioneFinestre.DB
					.ExeQuery("SELECT FK_NOME_GRUPPO From Partecipano Where FK_Nome_Utente = '" + nomeUtente + "'"); // Prende
			// i nomi
			// dei gruppi
			// dove l'utente
			// è creatore

			try {

				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rsNome_Gruppo.next()) {

					TMP_Nome_Gruppo = rsNome_Gruppo.getString("FK_NOME_GRUPPO");

					ResultSet rsF = GestioneFinestre.DB.ExeQuery("SELECT * FROM Contenuti WHERE FK_Nome_Gruppo = '"
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
	public List<Contenuti> SelAllContenutiUtente(String nomeUtente) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_UTENTE = '" + nomeUtente + "'");

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
	public List<Contenuti> SelContenutiUtenteGruppo(String nomeGruppo, String nomeUtente) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '"
					+ nomeGruppo + "' AND FK_NOME_UTENTE = '" + nomeUtente + "'");

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
	public List<Contenuti> SelAllContenutiMeseGruppo(String nomeGruppo, int Mese) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '"
					+ nomeGruppo + "' AND EXTRACT(MONTH from DATA_CREAZIONE) =  " + Mese);

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
