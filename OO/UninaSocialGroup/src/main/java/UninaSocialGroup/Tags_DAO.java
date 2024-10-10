package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tags_DAO {

	// Insert in un gruppo
	public void InsTag(String Parola) {
		// Inserisce un nuovo tag nel database
		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_TAG(?)");
			Call.setString(1, Parola);
			Call.execute();
			System.out.println("Tag Inserito");
			Call.close();
		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Delete su un gruppo
	public void DelTag(String Parola) {
		// Elimina un tag dal database
		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIMOZIONE_TAG(?)");
			Call.setString(1, Parola);
			Call.execute();
			System.out.println("Tags eliminato");
			Call.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Select singolo Tags per parola
	public Tags SelSigTag(String Parola) {
		// Seleziona un singolo tag dal database dato il nome del tag
		try {
			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM TAGS WHERE PAROLA = '" + Parola + "'");
			try {
				Tags Res_Tag;
				rs.next();
				Res_Tag = new Tags(rs.getString("Parola"));
				return Res_Tag;
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

	// Select tutti i tags
	public List<Tags> SelAllTags() {
		// Seleziona tutti i tag dal database
		try {
			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM TAGS");
			try {
				List<Tags> Rec_Tags = new ArrayList<Tags>();
				Tags Stampa;
				while (rs.next()) {
					Stampa = new Tags(rs.getString("Parola"));
					Rec_Tags.add(Stampa);
					Stampa = null;
				}
				return Rec_Tags;
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

	// Select tutti i tags
	public List<String> SelAllTags_String() {
		// Seleziona tutti i tag dal database e li restituisce come lista di stringhe
		try {
			ResultSet rs = Gestione_Finestre.DB.ExeQuery("SELECT * FROM TAGS");
			try {
				List<String> Rec_Tags = new ArrayList<String>();
				String Stampa;
				while (rs.next()) {
					Stampa = new String(rs.getString("Parola"));
					Rec_Tags.add(Stampa);
					Stampa = null;
				}
				return Rec_Tags;
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
}
