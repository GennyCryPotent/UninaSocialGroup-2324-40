package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Notifiche_Gruppi_DAO {

	DB_Connection DB = new DB_Connection();

	public Notifiche_Gruppi_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}
	
	//Mostra tutte le notifiche dei gruppi di un utente
	public List<Notifiche_Gruppi> SelAllGruppo(String Nome_Utente) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM NOTIFICHE_GRUPPI WHERE FK_NOME_UTENTE = '" + Nome_Utente + "'");
			
			try {
				List<Notifiche_Gruppi> Rec_Notifiche_Gruppi = new ArrayList<Notifiche_Gruppi>();
				
				Notifiche_Gruppi Stampa;

				while (rs.next()) {
					Stampa = new Notifiche_Gruppi(rs.getInt("Id_Notifica_G"), rs.getString("Testo"),
							rs.getDate("Data_Notifica"), rs.getString("Visualizzato"), rs.getString("FK_Nome_Gruppo"),
							rs.getString("FK_Nome_Utente"));
					
					Rec_Notifiche_Gruppi.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Notifiche_Gruppi;

			} catch (SQLException e) {
				System.out.println("query fallita");
				
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
