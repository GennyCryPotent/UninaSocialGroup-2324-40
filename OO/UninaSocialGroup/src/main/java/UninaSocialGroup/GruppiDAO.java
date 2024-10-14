package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GruppiDAO {
	
	// Inserimento di un gruppo nel database
	public void InsGruppo(String nomeGruppo, String descrizione, String creatore) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL CREA_GRUPPO(?, ?, ?)");
			call.setString(1, nomeGruppo);
			call.setString(2, descrizione);
			call.setString(3, creatore);
			call.execute();
			System.out.println("Gruppo Inserito");
			call.close();
			
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
		
	}

	// Eliminazione di un gruppo dal database
	public void DelGruppo(String nomeGruppo) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL RIMOZIONE_GRUPPO(?)");
			call.setString(1, nomeGruppo);
			call.execute();
			System.out.println("Gruppo eliminato");
			call.close();
		
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}

	// Aggiornamento di un gruppo nel database
	public void UpGruppo(String nomeGruppo, String creatore, String campo_Mod, String new_Val) {

		try {
			CallableStatement call = GestioneFinestre.DB.getC().prepareCall("CALL MODIFICA_GRUPPO(?, ?, ?, ?)");
			call.setString(1, campo_Mod);
			call.setString(2, new_Val);
			call.setString(3, creatore);
			call.setString(4, nomeGruppo);
			call.execute();
			System.out.println("Gruppo aggiornato ");
			call.close();
		
		} catch (Exception e) {
			System.out.println("Errore");
			
		}
	}

	// Selezione di un singolo gruppo dal database in base al nome
	public Gruppi SelSigGruppo(String nome) {

		try {

			ResultSet resultSet = GestioneFinestre.DB.ExeQuery("SELECT * FROM GRUPPI WHERE NOME = '" + nome + "'");

			try {

				Gruppi resGruppo;
				resultSet.next();

				resGruppo = new Gruppi(resultSet.getString("nome"), resultSet.getDate("Data_Creazione"),
						resultSet.getString("descrizione"), resultSet.getInt("OnlineC"), resultSet.getString("FK_nome_Utente"));
				
				
				return resGruppo;

			} catch (SQLException e) {
				System.out.println("query fallita");
			
				return null;
			} finally {
				resultSet.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}

	// Selezione di tutti i gruppi il cui nome contiene una determinata sequenza di caratteri
	public List<Gruppi> SelAllGruppoFromNome(String nomeGruppo) {

		try {

			ResultSet resultSet = GestioneFinestre.DB.ExeQuery("SELECT * FROM GRUPPI WHERE UPPER(NOME) LIKE '%"+ nomeGruppo.toUpperCase() +"%'");
			
			try {
				List<Gruppi> recGruppi = new ArrayList<Gruppi>();
				
				Gruppi stampa;

				while (resultSet.next()) {
					stampa = new Gruppi(resultSet.getString("nome"), resultSet.getDate("Data_Creazione"),
							resultSet.getString("descrizione"), resultSet.getInt("OnlineC"), resultSet.getString("FK_nome_Utente"));
					
					recGruppi.add(stampa);
					stampa = null;
				}
				
				
				return recGruppi;

			} catch (SQLException e) {
				System.out.println("query fallita");
				
				return null;
			} finally {
				resultSet.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore" + e.getMessage());
			
			return null;
		}
	}
	
	
	
	
	// Selezione di tutti i gruppi dal database
	public List<Gruppi> SelAllGruppo() {

		try {

			ResultSet resultSet = GestioneFinestre.DB.ExeQuery("SELECT * FROM GRUPPI");
			
			try {
				List<Gruppi> recGruppi = new ArrayList<Gruppi>();
				
				Gruppi stampa;

				while (resultSet.next()) {
					stampa = new Gruppi(resultSet.getString("nome"), resultSet.getDate("Data_Creazione"),
							resultSet.getString("descrizione"), resultSet.getInt("OnlineC"), resultSet.getString("FK_nome_Utente"));
					
					recGruppi.add(stampa);
					stampa = null;
				}
				
				
				return recGruppi;

			} catch (SQLException e) {
				System.out.println("query fallita");
				
				return null;
			} finally {
				resultSet.close(); // chiude sempre il cursore
			}


		} catch (Exception e) {
			System.out.println("Errore");
			
			return null;
		}
	}
	
	// Selezione di tutti i gruppi creati da un utente specifico
		public List<Gruppi> SelAllGruppoUtente(String nomeUtente) {

			try {

				ResultSet resultSet = GestioneFinestre.DB.ExeQuery("SELECT * FROM GRUPPI WHERE FK_NOME_UTENTE = '" + nomeUtente + "'");
				
				try {
					List<Gruppi> recGruppi = new ArrayList<Gruppi>();
					
					Gruppi stampa;

					while (resultSet.next()) {
						stampa = new Gruppi(resultSet.getString("nome"), resultSet.getDate("Data_Creazione"),
								resultSet.getString("descrizione"), resultSet.getInt("OnlineC"), resultSet.getString("FK_nome_Utente"));
						
						recGruppi.add(stampa);
						stampa = null;
					}
					
					
					return recGruppi;

				} catch (SQLException e) {
					System.out.println("query fallita");
					
					return null;
				} finally {
					resultSet.close(); // chiude sempre il cursore
				}


			} catch (Exception e) {
				System.out.println("Errore");
				
				return null;
			}
		}
		
		
}
