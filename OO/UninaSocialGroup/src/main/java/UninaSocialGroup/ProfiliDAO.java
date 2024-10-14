package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class ProfiliDAO {

	// Insert in un gruppo
	public void InsProfilo(String nomeUtente, String password, String nome, String cognome, String genere,
			Date dataNascita) throws SQLException, SQLIntegrityConstraintViolationException {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_PROFILO(?, ?, ?, ?, ?, ?)");
			call.setString(1, nomeUtente);
			call.setString(2, password);
			call.setString(3, nome);
			call.setString(4, cognome);
			call.setString(5, genere);
			call.setDate(6, dataNascita);
			call.execute();
			System.out.println("Utente creato");
			call.close();

		} catch (SQLIntegrityConstraintViolationException SQLError) {
			throw SQLError;
		}

		catch (SQLException e) {
			throw e;
		}

	}

	// Delete su un gruppo
	public void DelProfilo(String Nome_Utente) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_PROFILO(?)");
			call.setString(1, Nome_Utente);
			call.execute();
			System.out.println("Profilo eliminato");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Update di un gruppo
	public void UpProfilo(String nomeUtente, String campoMod, String newVal) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL MODIFICA_PROFILO(?, ?, ?)");
			call.setString(1, campoMod);
			call.setString(2, newVal);
			call.setString(3, nomeUtente);
			call.execute();
			System.out.println("Profilo aggiornato ");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select singolo per nome per nome
	public Profili SelSigProfilo(String nome) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM PROFILI WHERE NOME_UTENTE = '" + nome + "'");

			try {

				Profili resProfilo;
				rs.next();

				resProfilo = new Profili(rs.getString("Nome_Utente"), rs.getString("Password"), rs.getString("Nome"),
						rs.getString("Cognome"), rs.getString("Genere"), rs.getDate("Data_Nascita"));

				return resProfilo;

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

	// Select tutti i profili
	public List<Profili> SelAllProfili() {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM PROFILI");

			try {
				List<Profili> recProfili = new ArrayList<Profili>();

				Profili stampa;

				while (rs.next()) {
					stampa = new Profili(rs.getString("Nome_Utente"), rs.getString("Password"), rs.getString("Nome"),
							rs.getString("Cognome"), rs.getString("Genere"), rs.getDate("Data_Nascita"));

					recProfili.add(stampa);
					stampa = null;
				}

				return recProfili;

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
