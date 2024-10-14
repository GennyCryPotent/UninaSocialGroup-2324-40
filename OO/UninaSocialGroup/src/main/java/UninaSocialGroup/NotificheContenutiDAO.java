package UninaSocialGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificheContenutiDAO {

	// Mostra tutte le notifiche dei gruppi di un utente
	public List<Notifiche> SelNotificheContenutiUtente(String Nome_Utente) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM NOTIFICHE_CONTENUTI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Notifiche> recNotifiche = new ArrayList<Notifiche>();

				Notifiche stampa;

				while (rs.next()) {
					stampa = new Notifiche(rs.getInt("Id_Notifica_C"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getInt("FK_Id_Contenuto"),
							rs.getString("FK_Nome_Utente"));

					recNotifiche.add(stampa);
					stampa = null;
				}

				return recNotifiche;

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
