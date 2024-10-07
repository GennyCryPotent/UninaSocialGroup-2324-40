package UninaSocialGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Notifiche_Contenuti_DAO {

	// Mostra tutte le notifiche dei gruppi di un utente
	public List<Notifiche> SelNotificheContenutiUtente(String Nome_Utente) {

		try {

			ResultSet rs = Gestione_Finestre.DB
					.ExeQuery("SELECT * FROM NOTIFICHE_CONTENUTI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Notifiche> Rec_Notifiche = new ArrayList<Notifiche>();

				Notifiche Stampa;

				while (rs.next()) {
					Stampa = new Notifiche(rs.getInt("Id_Notifica_C"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getInt("FK_Id_Contenuto"),
							rs.getString("FK_Nome_Utente"));

					Rec_Notifiche.add(Stampa);
					Stampa = null;
				}

				return Rec_Notifiche;

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
