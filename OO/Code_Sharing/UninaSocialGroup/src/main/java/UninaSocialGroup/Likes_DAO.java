package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Likes_DAO {

	DB_Connection DB = new DB_Connection();

	public Likes_DAO(String USR, String PSW) {
		DB.connect(USR, PSW);
	}

	// Insert di un Like
	public void InsLike(int Id_Contenuto, String Nome_Utente) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL CREA_Like(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setInt(2, Id_Contenuto);
			Call.execute();
			System.out.println("Like Inserito");

		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());

		}

	}

	// Delete di un Like
	public void DelLike(int Id_Contenuto, String Nome_Utente) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_Like(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setInt(2, Id_Contenuto);
			Call.execute();
			System.out.println("Like eliminato");

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i like di un profilo su un gruppo
	public void DelLikeProfilo(String Nome_Utente, String Nome_Gruppo) {

		try {
			CallableStatement Call = DB.getC().prepareCall("CALL RIMOZIONE_LIKE_PROFILO(?, ?)");
			Call.setString(1, Nome_Utente);
			Call.setString(2, Nome_Gruppo);
			Call.execute();
			System.out.println("Likes eliminati");

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Select Likes per post
	public List<Likes> SelLikesPost(int Id_Contenuto) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT * FROM LIKES WHERE FK_ID_CONTENUTO = " + Id_Contenuto);

			try {

				List<Likes> Rec_Likes = new ArrayList<Likes>();

				Likes Stampa;

				while (rs.next()) {
					Stampa = new Likes(rs.getString("FK_Nome_Utente"), rs.getInt("FK_Id_Contenuto"));

					Rec_Likes.add(Stampa);
					Stampa = null;
				}

				return Rec_Likes;

			} catch (SQLException e) {
				System.out.println("query fallita");

				return null;
			}

		} catch (Exception e) {
			System.out.println("Errore");

			return null;
		}
	}

	// Select numeri di Like di un post
	public int SelNumLike(int Id_Contenuto) {

		try {

			ResultSet rs = DB.ExeQuery("SELECT COUNT(*) FROM LIKES WHERE FK_ID_CONTENUTO = " + Id_Contenuto);

			try {

				int N_Like = 0;

				if (rs.next()) {

					N_Like = rs.getInt(1); // 1 corrisponde all'elemento "COUNT(*)"

				}

				return N_Like;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());

				return 0;
			}

		} catch (Exception e) {
			System.out.println("Errore");

			return 0;
		}
	}

	public void Close_Connection() {
		DB.close();
	}

}
