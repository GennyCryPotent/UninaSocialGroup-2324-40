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
	public void InsCommento(String Testo, int Id_Contenuto, String Creatore) {

		try {
			CallableStatement Call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_COMMENTO(?, ?, ?)");
			Call.setString(1, Testo);
			Call.setInt(2, Id_Contenuto);
			Call.setString(3, Creatore);
			Call.execute();
			System.out.println("Commento Inserito");
			Call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete di un Commento
	public void DelCommento(int Id_Commento) {

		try {
			CallableStatement Call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_COMMENTO(?)");
			Call.setInt(1, Id_Commento);
			Call.execute();
			System.out.println("Commento eliminato");
			Call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i contenuti di un profilo su un gruppo
	public void DelCommentoProfilo(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_COMMENTO_PROFILO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Commenti eliminati");
			Call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Update di un Commento
	public void UpCommento(String Creatore, int Id_Commento, String New_Val) {

		try {
			CallableStatement Call = GestioneFinestre.DB.getC().prepareCall("CALL MODIFICA_COMMENTO(?, ?, ?)");
			Call.setString(1, New_Val);
			Call.setString(2, Creatore);
			Call.setInt(3, Id_Commento);
			Call.execute();
			System.out.println("Commento aggiornato ");
			Call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select Commenti per post
	public List<Commenti> SelCommentiPost(int Id_Contenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM COMMENTI WHERE FK_ID_CONTENUTO = " + Id_Contenuto);

			try {

				List<Commenti> Rec_Commenti = new ArrayList<Commenti>();

				Commenti Stampa;

				while (rs.next()) {
					Stampa = new Commenti(rs.getInt("Id_Commento"), rs.getDate("Data_Creazione"), rs.getString("Testo"),
							rs.getInt("FK_Id_Contenuto"), rs.getString("FK_Nome_Utente"));

					Rec_Commenti.add(Stampa);
					Stampa = null;
				}

				return Rec_Commenti;

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
				List<Commenti> Rec_Commenti = new ArrayList<Commenti>();

				Commenti Stampa;

				while (rs.next()) {
					Stampa = new Commenti(rs.getInt("Id_Commento"), rs.getDate("Data_Creazione"), rs.getString("Testo"),
							rs.getInt("FK_Id_Contenuto"), rs.getString("FK_Nome_Utente"));

					Rec_Commenti.add(Stampa);
					Stampa = null;
				}

				return Rec_Commenti;

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
	public int SelNumCommenti(int Id_Contenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT COUNT(*) FROM COMMENTI WHERE FK_ID_CONTENUTO = " + Id_Contenuto);

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
	public List<Commenti> SelCommentiUtentePost(String Nome_Utente, int Id_Contenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM Commenti WHERE FK_ID_CONTENUTO= '"
					+ Id_Contenuto + "' AND FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Commenti> Rec_Commenti = new ArrayList<Commenti>();

				Commenti Stampa;

				while (rs.next()) {
					Stampa = new Commenti(rs.getInt("Id_Commento"), rs.getDate("Data_Creazione"), rs.getString("Testo"),
							rs.getInt("FK_Id_Contenuto"), rs.getString("FK_Nome_Utente"));

					Rec_Commenti.add(Stampa);
					Stampa = null;
				}

				return Rec_Commenti;

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
