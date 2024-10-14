package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Regolano_DAO {

	// Insert in Regolano
	public void InsAmministratore(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_REGOLANO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Amministratore aggiunto al gruppo");
			Call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete su un Regolano
	public void DelAmministratore(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_REGOLANO(?, ?)");
			Call.setString(1, Nome_Gruppo);
			Call.setString(2, Nome_Utente);
			Call.execute();
			System.out.println("Amministratore eliminato");
			Call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Select tutti i gruppi dove regola l'utente
	public List<Regolano> SelSigRegolano(String Nome_Utente) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM REGOLANO WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {

				List<Regolano> Rec_Regolano = new ArrayList<Regolano>();

				Regolano Stampa;

				while (rs.next()) {

					Stampa = new Regolano(rs.getString("FK_Nome_Utente"), rs.getString("FK_Nome_Gruppo"));

					Rec_Regolano.add(Stampa);
					Stampa = null;
				}

				return Rec_Regolano;

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

	// Select tutti gli amministratori di un gruppo
	public List<Regolano> SelAllRegolanoGruppo(String Nome_Gruppo) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM REGOLANO WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'");

			try {
				List<Regolano> Rec_Regolano = new ArrayList<Regolano>();

				Regolano Stampa;

				while (rs.next()) {
					Stampa = new Regolano(rs.getString("FK_Nome_Utente"), rs.getString("FK_Nome_Gruppo"));

					Rec_Regolano.add(Stampa);
					Stampa = null;
				}

				return Rec_Regolano;

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

	// Select tutti gli amministratori di un gruppo
	public boolean CheckRegola(String Nome_Gruppo, String Nome_Utente) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT COUNT(*) FROM REGOLANO WHERE FK_NOME_GRUPPO = '"
					+ Nome_Gruppo + "'AND FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {

				if (rs.next()) {

					if (rs.getInt(1) == 0) {
						return false;
					} else {
						return true;
					}

				}

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
		return false;
	}

}
