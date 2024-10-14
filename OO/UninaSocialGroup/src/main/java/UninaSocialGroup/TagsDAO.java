package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagsDAO {

	// Insert in un gruppo
	public void InsTag(String parola) {
		// Inserisce un nuovo tag nel database
		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_TAG(?)");
			call.setString(1, parola);
			call.execute();
			System.out.println("Tag Inserito");
			call.close();
		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Delete su un gruppo
	public void DelTag(String parola) {
		// Elimina un tag dal database
		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_TAG(?)");
			call.setString(1, parola);
			call.execute();
			System.out.println("Tags eliminato");
			call.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Select singolo Tags per parola
	public Tags SelSigTag(String parola) {
		// Seleziona un singolo tag dal database dato il nome del tag
		try {
			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM TAGS WHERE PAROLA = '" + parola + "'");
			try {
				Tags resTag;
				rs.next();
				resTag = new Tags(rs.getString("Parola"));
				return resTag;
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
			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM TAGS");
			try {
				List<Tags> recTags = new ArrayList<Tags>();
				Tags stampa;
				while (rs.next()) {
					stampa = new Tags(rs.getString("Parola"));
					recTags.add(stampa);
					stampa = null;
				}
				return recTags;
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
			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM TAGS");
			try {
				List<String> recTags = new ArrayList<String>();
				String stampa;
				while (rs.next()) {
					stampa = new String(rs.getString("Parola"));
					recTags.add(stampa);
					stampa = null;
				}
				return recTags;
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
