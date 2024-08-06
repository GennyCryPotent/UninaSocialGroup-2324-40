package StandardJava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class Gruppi_DAO {

	DB_Connection DB = new DB_Connection();

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
			System.out.println("Gruppo Inserito");
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
			System.out.println("Gruppo eliminato");
		} catch (Exception e) {
			System.out.println(e);
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
			System.out.println("Gruppo aggiornato ");
		} catch (Exception e) {
			System.out.println("Errore");
		}
	}

	// Select singolo gruppo per nome (siccome è unique)
	public Gruppi SelSigGruppo(String Nome) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM GRUPPI WHERE NOME = '" + Nome + "'");

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
	public List<Gruppi> SelAllGruppo() {

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

	// Select tutti i gruppi con chiamata alla funzione
//	public List<Gruppi> SelAllGruppoFunction() {
//
//		try {
//			CallableStatement Call = DB.getC().prepareCall("{? = call Mostra_Gruppi()}");
//
//			// Registra il parametro di output (in tutti casi saranno dei cursori)
//			Call.registerOutParameter(1, OracleTypes.CURSOR);
//
//			// Esegui la chiamata alla funzione
//			Call.execute();
//
//			// Imposta il parametro di input (se la function ha dei parametri)
//			// callableStatement.setString(2, "SSC_Napoli_Ultras"); Es. nome del gruppo come
//			// parametro IN
//
//			// Ottieni il risultato come ResultSet
//			ResultSet rs = (ResultSet) Call.getObject(1); // Restituisce l'oggeto 1 cioè il cursore
//
//			try {
//				List<Gruppi> Rec_Gruppi = new ArrayList<Gruppi>();
//				Gruppi Stampa;
//
//				while (rs.next()) {
//					Stampa = new Gruppi(rs.getInt("Id_Gruppo"), rs.getString("Nome"), rs.getDate("Data_Creazione"),
//							rs.getString("Descrizione"), rs.getInt("OnlineC"), rs.getString("FK_Nome_Utente"));
//					Rec_Gruppi.add(Stampa);
//					Stampa = null;
//				}
//
//				return Rec_Gruppi;
//
//			} catch (SQLException e) {
//				System.out.println("query fallita");
//				return null;
//			}
//
//		} catch (Exception e) {
//			System.out.println("Errore");
//			return null;
//		}
//	}

}
