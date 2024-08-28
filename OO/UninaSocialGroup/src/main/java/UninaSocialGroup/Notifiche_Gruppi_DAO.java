package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class Notifiche_Gruppi_DAO {

	// Mostra tutte le notifiche dei gruppi di un utente
	public List<Notifiche> SelNotificheUtente(String Nome_Utente) {

		try {

			ResultSet rs = Gestione_Finestre.DB
					.ExeQuery("SELECT * FROM NOTIFICHE_GRUPPI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");

			try {
				List<Notifiche> Rec_Notifiche = new ArrayList<Notifiche>();

				Notifiche Stampa;

				while (rs.next()) {
					Stampa = new Notifiche(rs.getInt("Id_Notifica_G"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getString("FK_Nome_Gruppo"),
							rs.getString("FK_Nome_Utente"));

					Rec_Notifiche.add(Stampa);
					
					Stampa = null;
				}

				return Rec_Notifiche;

			} catch (SQLException e) {
				System.out.println("query fallita");

				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	
	  
	// Select tutte le notifiche dei gruppie dei contenuti di un utente
	public List<Notifiche> SelNotifiche(String Nome_Utente) {

		List<Notifiche> Rec_Notifiche = new ArrayList<Notifiche>();

		try {

			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("{CALL Mostra_Notifica(?, ?)}");

			// Imposta il parametro di input (se la function ha dei parametri)
			Call.setString(1, Nome_Utente); // Es. nome del gruppo come parametro IN

			// Imposta il parametro di output
			Call.registerOutParameter(2, OracleTypes.CURSOR);

			// Esegui la chiamata alla funzione
			Call.execute();

			// Ottieni il risultato come ResultSet
			ResultSet rs = (ResultSet) Call.getObject(2); // Restituisce l'oggeto 2 cioè il cursore

			try {

				Notifiche Stampa;

				while (rs.next()) {

					Stampa = new Notifiche(rs.getInt("Id_Notifica_C"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getString("FK_Nome_Gruppo"));
					Rec_Notifiche.add(Stampa);

					Stampa = null;
				}

				return Rec_Notifiche;

			} catch (SQLException e) {
				System.out.println("query fallita" + e.getMessage());
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore" + e.getMessage());
			return null;
		}

	}
}
