package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PossiedonoDAO {

	// Insert in Possiedono
	public void InsTagGruppo(String Nome_Gruppo, String Parola) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_POSSIEDONO(?, ?)");
			call.setString(1, Nome_Gruppo);
			call.setString(2, Parola);
			call.execute();
			System.out.println("Tag aggiunto al gruppo");
			call.close();
		} catch (Exception e) {
			System.out.println("Errore");

		}

	}

	// Delete su Possiedono
	public void DelTagGruppo(String Nome_Gruppo, String Parola) {

		try {
			PreparedStatement remove = GestioneFinestre.DB.getC()
					.prepareStatement("DELETE FROM POSSIEDONO WHERE FK_PAROLA = '" + Parola + "' AND FK_NOME_GRUPPO = '"
							+ Nome_Gruppo + "'");
			remove.execute();
			remove.close();
			System.out.println("Tag eliminato dal gruppo");

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	// Select tutti i gruppi dove � associato il tag
	public List<Possiedono> SelGruppiConTag(String Parola) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM Possiedono WHERE FK_PAROLA = '" + Parola + "'");

			try {

				List<Possiedono> recPossiedono = new ArrayList<Possiedono>();

				Possiedono stampa;

				while (rs.next()) {

					stampa = new Possiedono(rs.getString("FK_Parola"), rs.getString("FK_Nome_Gruppo"));

					recPossiedono.add(stampa);
					stampa = null;
				}

				return recPossiedono;

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

	// Select tutti i tag di un gruppo
	public List<Possiedono> SelTagGruppo(String Nome_Gruppo) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT * FROM POSSIEDONO WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'");

			try {
				List<Possiedono> recPossiedono = new ArrayList<Possiedono>();

				Possiedono stampa;

				while (rs.next()) {
					stampa = new Possiedono(rs.getString("FK_Parola"), rs.getString("FK_Nome_Gruppo"));

					recPossiedono.add(stampa);
					stampa = null;
				}

				return recPossiedono;

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
