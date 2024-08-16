package UninaSocialGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Notifiche_Contenuti_DAO {

	DB_Connection DB = new DB_Connection();

	public Notifiche_Contenuti_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}
	
	//Mostra tutte le notifiche dei gruppi di un utente
	public List<Notifiche_Contenuti> SelNotificheContenutiUtente(String Nome_Utente) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM NOTIFICHE_CONTENUTI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");
			
			try {
				List<Notifiche_Contenuti> Rec_Notifiche_Contenuti = new ArrayList<Notifiche_Contenuti>();
				
				Notifiche_Contenuti Stampa;

				while (rs.next()) {
					Stampa = new Notifiche_Contenuti(rs.getInt("Id_Notifica_C"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getInt("FK_Id_Contenuto"),
							rs.getString("FK_Nome_Utente"));
					
					Rec_Notifiche_Contenuti.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Notifiche_Contenuti;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
				
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
	
	public void Close_Connection() { 
		DB.close();
	}	
	
	
	
}
