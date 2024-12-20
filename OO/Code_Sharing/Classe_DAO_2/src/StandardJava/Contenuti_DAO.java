package StandardJava;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contenuti_DAO {

	DB_Connection DB = new DB_Connection();

	public Contenuti_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}

	// Insert in Contenuti
	public void InsContenuto(String Foto, String Testo, String Nome_Gruppo, String Nome_Utente) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL CREA_CONTENUTO(?, ?, ?, ?)");
			Call.setString(1, Foto);
			Call.setString(2, Testo);
			Call.setString(3, Nome_Gruppo);
			Call.setString(4, Nome_Utente);
			Call.execute();
			System.out.println("Contenuto creato");

		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Delete su un Contenuto
	public void DelContenuto(int Id_Contenuto) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_CONTENUTO(?)");
			Call.setInt(1, Id_Contenuto);
			Call.execute();
			System.out.println("Contenuto eliminato");

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i contenuti di un profilo su un gruppo
	public void DelContenutoProfilo(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_CONTENUTO_PROFILO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Contenuti eliminato");

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Update di un Contenuto
	public void UpContenuto(String Nome_Utente, String Campo_Mod, String New_Val, int Id_Contenuto) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL MODIFICA_CONTENUTO(?, ?, ?, ?)");
			Call.setString(1, Campo_Mod);
			Call.setString(2, New_Val);
			Call.setString(3, Nome_Utente);
			Call.setInt(4, Id_Contenuto);
			Call.execute();
			System.out.println("Contenuto aggiornato ");

		} catch (Exception e) {
			System.out.println("Errore");

		}
	}

	// Select singolo Contenuto per nome
	public Contenuti SelSigContenuto(int Id_Contenuto) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM CONTENUTI WHERE ID_CONTENUTO = " + Id_Contenuto);

			try {

				Contenuti Res_Contenuto;
				rs.next();

				Res_Contenuto = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
						rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

				return Res_Contenuto;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());

				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	// Select tutti i contenuti di un gruppo
	public List<Contenuti> SelAllContenutiGruppo(String Nome_Gruppo) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM CONTENUTI WHERE FK_NOME_GRUPPO = '" + Nome_Gruppo + "'");

			try {
				List<Contenuti> Rec_Contenuti = new ArrayList<Contenuti>();

				Contenuti Stampa;

				while (rs.next()) {
					Stampa = new Contenuti(rs.getInt("Id_Contenuto"), rs.getDate("Data_Creazione"),
							rs.getString("Testo"), rs.getString("FK_Nome_Gruppo"), rs.getString("FK_Nome_Utente"));

					Rec_Contenuti.add(Stampa);
					Stampa = null;
				}

				return Rec_Contenuti;

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
