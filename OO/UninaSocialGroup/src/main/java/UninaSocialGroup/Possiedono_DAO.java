package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Possiedono_DAO {

	// Insert in Possiedono
	public void InsTagGruppo(String Nome_Gruppo, String Parola) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_POSSIEDONO(?, ?)");
			Call.setString(1, Nome_Gruppo);
			Call.setString(2, Parola);
			Call.execute();
			System.out.println("Tag aggiunto al gruppo");
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
		
	}

	// Delete su Possiedono
	public void DelTagGruppo(String Nome_Gruppo, String Parola) {

		try {
			PreparedStatement Remove = Gestione_Finestre.DB.getC().prepareStatement("DELETE FROM POSSIEDONO WHERE FK_PAROLA = '" + Parola + "' AND FK_NOME_GRUPPO = '" + Nome_Gruppo + "'");
			Remove.execute();
			Remove.close();
//			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_POSSIEDONO(?, ?)");
//			Call.setString(1, Nome_Gruppo);
//			Call.setString(2, Parola);
//			Call.execute();
			System.out.println("Tag eliminato dal gruppo");
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}

	// Select tutti i gruppi dove è associato il tag
	public List<Possiedono> SelGruppiConTag(String Parola) {

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM Possiedono WHERE FK_PAROLA = '" + Parola + "'");

			try {

				List<Possiedono> Rec_Possiedono = new ArrayList<Possiedono>();
				
				Possiedono Stampa;

				while (rs.next()) {
					
					Stampa =  new Possiedono(rs.getString("FK_Parola"),rs.getString("FK_Nome_Gruppo"));
					
					Rec_Possiedono.add(Stampa);
					Stampa = null;
				}
				
				return Rec_Possiedono;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
			
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Select tutti i tag di un gruppo
	public List<Possiedono> SelTagGruppo(String Nome_Gruppo){

		try {

			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM POSSIEDONO WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'" );
			
			try {
				List<Possiedono> Rec_Possiedono = new ArrayList<Possiedono>();
				
				Possiedono Stampa;

				while (rs.next()) {
					Stampa =  new Possiedono(rs.getString("FK_Parola"),rs.getString("FK_Nome_Gruppo"));
					
					Rec_Possiedono.add(Stampa);
					Stampa = null;
				}
				
				
				return Rec_Possiedono;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());
				
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
}
