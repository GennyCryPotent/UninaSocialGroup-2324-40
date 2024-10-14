package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class NotificheGruppiDAO {

	// Mostra tutte le notifiche dei gruppi di un utente
	public List<Notifiche> SelNotificheUtente(String Nome_Utente) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM NOTIFICHE_GRUPPI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Notifiche> recNotifiche = new ArrayList<Notifiche>();

				Notifiche stampa;

				while (rs.next()) {
					stampa = new Notifiche(rs.getInt("Id_Notifica_G"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getString("FK_Nome_Gruppo"),
							rs.getString("FK_Nome_Utente"));

					recNotifiche.add(stampa);

					stampa = null;
				}

				return recNotifiche;

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

	// Select tutte le notifiche dei gruppie dei contenuti di un utente
	public List<Notifiche> SelNotifiche(String Nome_Utente) {

		List<Notifiche> recNotifiche = new ArrayList<Notifiche>();

		try {

			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("{CALL Mostra_Notifica(?, ?)}");

			// Imposta il parametro di input (se la function ha dei parametri)
			call.setString(1, Nome_Utente); // Es. nome del gruppo come parametro IN

			// Imposta il parametro di output
			call.registerOutParameter(2, OracleTypes.CURSOR);

			// Esegui la chiamata alla funzione
			call.execute();

			// Ottieni il risultato come ResultSet
			ResultSet rs = (ResultSet) call.getObject(2); // Restituisce l'oggeto 2 cioè il cursore

			try {

				Notifiche stampa;

				while (rs.next()) {

					stampa = new Notifiche(rs.getInt("Id_Notifica_C"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getString("FK_Nome_Gruppo"));
					recNotifiche.add(stampa);

					stampa = null;
				}

				return recNotifiche;

			} catch (SQLException e) {
				System.out.println("query fallita" + e.getMessage());
				return null;

			} finally {
				rs.close(); // chiude sempre il cursore
			}

		} catch (Exception e) {
			System.out.println("Errore" + e.getMessage());
			return null;
		}

	}
}
