package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentiDAO {

	public CommentiDAO() {

	}

	// Insert di un Commento
	public void InsCommento(String testo, int IdContenuto, String creatore) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_COMMENTO(?, ?, ?)");
			call.setString(1, testo);
			call.setInt(2, IdContenuto);
			call.setString(3, creatore);
			call.execute();
			System.out.println("Commento Inserito");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete di un Commento
	public void DelCommento(int idCommento) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_COMMENTO(?)");
			call.setInt(1, idCommento);
			call.execute();
			System.out.println("Commento eliminato");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i contenuti di un profilo su un gruppo
	public void DelCommentoProfilo(String nomeUtente, String nomeGruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_COMMENTO_PROFILO(?, ?)");
			call.setString(1, nomeUtente);
			call.setString(2, nomeGruppo);
			call.execute();
			System.out.println("Commenti eliminati");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Update di un Commento
	public void UpCommento(String creatore, int idCommento, String newVal) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL MODIFICA_COMMENTO(?, ?, ?)");
			call.setString(1, newVal);
			call.setString(2, creatore);
			call.setInt(3, idCommento);
			call.execute();
			System.out.println("Commento aggiornato ");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select Commenti per post
	public List<Commenti> SelCommentiPost(int idContenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM COMMENTI WHERE FK_ID_CONTENUTO = " + idContenuto);

			try {

				List<Commenti> recCommenti = new ArrayList<Commenti>();

				Commenti stampa;

				while (rs.next()) {
					stampa = new Commenti(rs.getInt("Id_Commento"), rs.getDate("Data_Creazione"), rs.getString("Testo"),
							rs.getInt("FK_Id_Contenuto"), rs.getString("FK_Nome_Utente"));

					recCommenti.add(stampa);
					stampa = null;
				}

				return recCommenti;

			} catch (SQLException e) {
				System.out.println("query fallita");

				return null;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	// Select tutti i Commenti
	public List<Commenti> SelAllCommento() {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM Commenti");

			try {
				List<Commenti> recCommenti = new ArrayList<Commenti>();

				Commenti stampa;

				while (rs.next()) {
					stampa = new Commenti(rs.getInt("Id_Commento"), rs.getDate("Data_Creazione"), rs.getString("Testo"),
							rs.getInt("FK_Id_Contenuto"), rs.getString("FK_Nome_Utente"));

					recCommenti.add(stampa);
					stampa = null;
				}

				return recCommenti;

			} catch (SQLException e) {
				System.out.println("query fallita");

				return null;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	// Select numeri di commenti di un post
	public int SelNumCommenti(int idContenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT COUNT(*) FROM COMMENTI WHERE FK_ID_CONTENUTO = " + idContenuto);

			try {

				int N_Like = 0;

				if (rs.next()) {

					N_Like = rs.getInt(1); // 1 corrisponde all'elemento "COUNT(*)"

				}

				return N_Like;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());

				return 0;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");

			return 0;
		}
	}

	// Select tutti i Commenti di un utente in un post
	public List<Commenti> SelCommentiUtentePost(String nomeUtente, int idContenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM Commenti WHERE FK_ID_CONTENUTO= '"
					+ idContenuto + "' AND FK_NOME_UTENTE = '" + nomeUtente + "'");

			try {
				List<Commenti> recCommenti = new ArrayList<Commenti>();

				Commenti stampa;

				while (rs.next()) {
					stampa = new Commenti(rs.getInt("Id_Commento"), rs.getDate("Data_Creazione"), rs.getString("Testo"),
							rs.getInt("FK_Id_Contenuto"), rs.getString("FK_Nome_Utente"));

					recCommenti.add(stampa);
					stampa = null;
				}

				return recCommenti;

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

}
