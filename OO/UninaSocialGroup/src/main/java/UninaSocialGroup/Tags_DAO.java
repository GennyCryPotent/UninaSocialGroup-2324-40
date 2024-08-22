package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tags_DAO {

	// Insert in un gruppo
	public void InsTag(String Parola) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL CREA_TAG(?)");
			Call.setString(1, Parola);
			Call.execute();
			System.out.println("Tag Inserito");

		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete su un gruppo
	public void DelTag(String Parola) {

		try {
			CallableStatement Call = Gestione_Finestre.DB.getC().prepareCall("CALL RIMOZIONE_TAG(?)");
			Call.setString(1, Parola);
			Call.execute();
			System.out.println("Tags eliminato");

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Select singolo Tags per parola
	public Tags SelSigTag(String Parola) {

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
			}

		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	// Select tutti i tags
	public List<Tags> SelAllTags() {

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
			}

		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}
}
