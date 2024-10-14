package UninaSocialGroup;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikesDAO {

	// Insert di un Like
	public void InsLike(int idContenuto, String nomeUtente) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_Like(?, ?)");
			call.setString(1, nomeUtente);
			call.setInt(2, idContenuto);
			call.execute();
			System.out.println("Like Inserito");
			call.close();

		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());

		}

	}

	// Delete di un Like
	public void DelLike(int idContenuto, String nomeUtente) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_Like(?, ?)");
			call.setString(1, nomeUtente);
			call.setInt(2, idContenuto);
			call.execute();
			System.out.println("Like eliminato");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// Delete di tutti i like di un profilo su un gruppo
	public void DelLikeProfilo(String nomeUtente, String NomeGruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_LIKE_PROFILO(?, ?)");
			call.setString(1, nomeUtente);
			call.setString(2, NomeGruppo);
			call.execute();
			System.out.println("Likes eliminati");
			call.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}
	
	
	// Controlla se l'utente ha gi� messo like al post
	public boolean SelLikeProfilo(String nomeUtente, int idContenuto) {

		try {
			
			
			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT COUNT(*) FROM LIKES WHERE FK_NOME_UTENTE = '" + nomeUtente + "' AND FK_ID_CONTENUTO =" + idContenuto);
			
			rs.next();
			
			if(rs.getInt(1) >= 1) {
				rs.close();
				return true;
			}else {
				rs.close();
				return false;
			}

		} catch (Exception e) {
			System.out.println(e);
			return false;

		} 
		
	}

	// Select Likes per post
	public List<Likes> SelLikesPost(int idContenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB.ExeQuery("SELECT * FROM LIKES WHERE FK_ID_CONTENUTO = " + idContenuto);

			try {

				List<Likes> recLikes = new ArrayList<Likes>();

				Likes stampa;

				while (rs.next()) {
					stampa = new Likes(rs.getString("FK_Nome_Utente"), rs.getInt("FK_Id_Contenuto"));

					recLikes.add(stampa);
					stampa = null;
				}

				return recLikes;

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

	// Select numeri di Like di un post
	public int SelNumLike(int idContenuto) {

		try {

			ResultSet rs = GestioneFinestre.DB
					.ExeQuery("SELECT COUNT(*) FROM LIKES WHERE FK_ID_CONTENUTO = " + idContenuto);

			try {

				int nLike = 0;

				if (rs.next()) {

					nLike = rs.getInt(1); // 1 corrisponde all'elemento "COUNT(*)"

				}

				return nLike;

			} catch (SQLException e) {
				System.out.println("query fallita: " + e.getMessage());

				return 0;
			} finally {
				rs.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");

			return 0;
		}
	}
}
