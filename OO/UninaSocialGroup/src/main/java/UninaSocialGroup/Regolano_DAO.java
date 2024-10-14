package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Regolano_DAO {

	// Insert in Regolano
	public void InsAmministratore(String nomeUtente, String nomeGruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_REGOLANO(?, ?)");
			call.setString(1, nomeUtente);
			call.setString(2, nomeGruppo);
			call.execute();
			System.out.println("Amministratore aggiunto al gruppo");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete su un Regolano
	public void DelAmministratore(String nomeUtente, String nomeGruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_REGOLANO(?, ?)");
			call.setString(1, nomeGruppo);
			call.setString(2, nomeUtente);
			call.execute();
			System.out.println("Amministratore eliminato");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Select tutti i gruppi dove regola l'utente
	public List<Regolano> SelSigRegolano(String nomeUtente) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM REGOLANO WHERE FK_NOME_UTENTE = '" + nomeUtente + "'");

			try {

				List<Regolano> recRegolano = new ArrayList<Regolano>();

				Regolano stampa;

				while (rs.next()) {

					stampa = new Regolano(rs.getString("FK_Nome_Utente"), rs.getString("FK_Nome_Gruppo"));

					recRegolano.add(stampa);
					stampa = null;
				}

				return recRegolano;

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
	public List<Regolano> SelAllRegolanoGruppo(String nomeGruppo) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM REGOLANO WHERE FK_NOME_GRUPPO = '" + nomeGruppo + "'");

			try {
				List<Regolano> recRegolano = new ArrayList<Regolano>();

				Regolano stampa;

				while (rs.next()) {
					stampa = new Regolano(rs.getString("FK_Nome_Utente"), rs.getString("FK_Nome_Gruppo"));

					recRegolano.add(stampa);
					stampa = null;
				}

				return recRegolano;

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
	public boolean CheckRegola(String nomeGruppo, String nomeUtente) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT COUNT(*) FROM REGOLANO WHERE FK_NOME_GRUPPO = '"
					+ nomeGruppo + "'AND FK_NOME_UTENTE = '" + nomeUtente + "'");

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
