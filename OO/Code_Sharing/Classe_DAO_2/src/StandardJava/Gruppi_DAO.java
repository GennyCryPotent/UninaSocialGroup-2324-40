package StandardJava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Gruppi_DAO {

	DB_Connection DB = new DB_Connection("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521:ORCL");

	public Gruppi_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}

	// Insert in un gruppo
	public void InsGruppo(String Nome_Gruppo, String Descrizione, String Creatore) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL CREA_GRUPPO(?, ?, ?)");
			Call.setString(1, Nome_Gruppo);
			Call.setString(2, Descrizione);
			Call.setString(3, Creatore);
			Call.execute();
		} catch (Exception e) {
			System.out.println("Errore");
		}

	}

	// Delete su un gruppo
	public void DelGruppo(int Id_Gruppo, String Creatore) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_GRUPPO(?, ?)");
			Call.setInt(1, Id_Gruppo);
			Call.setString(2, Creatore);
			Call.execute();
		} catch (Exception e) {
			System.out.println("Errore");
		}

	}

	// Update di un gruppo
	public void UpGruppo(int Id_Gruppo, String Creatore, String Campo_Mod, String New_Val) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL MODIFICA_GRUPPO(?, ?, ?, ?)");
			Call.setString(1, Campo_Mod);
			Call.setString(2, New_Val);
			Call.setString(3, Creatore);
			Call.setInt(4, Id_Gruppo);
			Call.execute();
		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Select singolo gruppo per nome (siccome è unique)
	public Gruppi SelSigGruppo(String Nome) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM GRUPPI WHERE NOME = "+ Nome);

			try {
				
				Gruppi Res_Gruppo;
				rs.next();
				
				Res_Gruppo = new Gruppi(rs.getInt("Id_Gruppo"), rs.getString("Nome"), rs.getDate("Data_Creazione"),
						rs.getString("Descrizione"), rs.getInt("OnlineC"), rs.getString("FK_Nome_Utente"));

				return Res_Gruppo;

			} catch (SQLException e) {
				System.out.println("query fallita");
				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");
			return null;
		}
	}
	

	// Select singolo gruppo
	public List<Gruppi> SelAllGruppo(String Nome) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM GRUPPI");

			try {
				List<Gruppi> Rec_Gruppi = new ArrayList<Gruppi>();
				Gruppi Stampa;

				while (rs.next()) {
					Stampa = new Gruppi(rs.getInt("Id_Gruppo"), rs.getString("Nome"), rs.getDate("Data_Creazione"),
							rs.getString("Descrizione"), rs.getInt("OnlineC"), rs.getString("FK_Nome_Utente"));
					Rec_Gruppi.add(Stampa);
					Stampa = null;
				}

				return Rec_Gruppi;

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
