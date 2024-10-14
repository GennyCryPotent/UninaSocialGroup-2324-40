package UninaSocialGroup;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;


public class GestioneFinestre {
	
	private HomeGUI Accesso;
	private GruppiGUI Gruppo;
	private NotificheGUI Notifica;  
	private OperazioniPostCommentoGUI Elimina; 
	private ReportStatisticoGUI Report;
	private InfoPost_GUI InfoPost;
	private Registrazione_GUI Registrazione;
	private LoginGUI Login;
	private RicercaGUI Ricerca;
	private OperazioniPartecipanteGUI EliminaP;
	private CreaGruppoGUI CreaG;
	
	public static DBConnection DB = new DBConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //portatile genny
	//public static DB_Connection DB = new DBConnection("jdbc:oracle:thin:@Gennaro.homenet.telecomitalia.it:1521:xe", "system", "Database@03"); //Fisso Genny

	//public static DBConnection DB = new DBConnection("jdbc:oracle:thin:@DESKTOP-MLJV8GK:1521:xe", "system", "Caruso"); //Caruso

	//public static DB_Connection DB = new DBConnection("jdbc:oracle:thin:@errore31:1521:xe", "SYSTEM", "Caruso"); //Caruso Portatile
	
	//public static DB_Connection DB = new DBConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Database@03"); //Gabbo

	static {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put( "Button.arc", 10 ); //rende i bottoni circolari
			UIManager.put( "ScrollBar.showButtons", true ); //mostra le frecce nella scrollBar
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {  //main login
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB.connect();
					HomeGUI frame = new HomeGUI("Genny03cry");

					//Login_GUI frame = new Login_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					DB.close();
				}
			}
		});
	}
	
	  public void MostraGruppi(String nomeUtente, String nomeGruppo) {
		  Gruppo = new GruppiGUI(nomeUtente, nomeGruppo);
		  Gruppo.setVisible(true);
	  
	  }
	
	  public void AccessoHome(String nomeUtente) {
		  Accesso = new HomeGUI(nomeUtente); 
		  Accesso.setVisible(true);
		  
	  }
	  
	  public void EliminaContenuto(String nomeGruppo, String nomeUtente, GruppiGUI gruppiView) {
		  Elimina = new OperazioniPostCommentoGUI(nomeGruppo, nomeUtente, gruppiView); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void EliminaCommento(String nomeGruppo, String nomeUtente, int Id_Contenuto, int checkSchermata) {
		  Elimina = new OperazioniPostCommentoGUI(nomeGruppo, nomeUtente, Id_Contenuto, checkSchermata); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void InfoPost(int Id_Contenuto, String nomeUtente, String nomeGruppo, int check ) {
		  InfoPost = new InfoPost_GUI(Id_Contenuto,nomeUtente, nomeGruppo, check); 
		  InfoPost.setVisible(true);
		
	  }

	  
	  public void MostraNotifiche(String nomeUtente) {
		  Notifica = new NotificheGUI(nomeUtente);
		  Notifica.setVisible(true);
		  
	  }
	  
	  public void ReportS(String nomeUtente) {
		  Report = new ReportStatisticoGUI(nomeUtente);
		  Report.setVisible(true);
	  }
	  
	  public void MostraRegistrazione() {
		  Registrazione = new Registrazione_GUI();
		  Registrazione.setVisible(true);
	  }
	  
	  public void MostraLogin() {
		  Login = new LoginGUI();
		  Login.setVisible(true);
		  
	  }
	  
	  public void MostraRicerca(String nomeUtente, HomeGUI homeView) {
		  Ricerca = new RicercaGUI(nomeUtente, homeView);
		  Ricerca.setVisible(true);
	  }
	  
	  public void EliminaPartecipante(String nomeUtente, String nomeGruppo, String Ruolo, int checkOp) {
		  EliminaP = new OperazioniPartecipanteGUI(nomeUtente, nomeGruppo, Ruolo, checkOp);
		  EliminaP.setVisible(true);
	  }
	  
	  public void CreaGruppo(String nomeUtente, HomeGUI home) {
		  CreaG = new CreaGruppoGUI(nomeUtente, home);
		  CreaG.setVisible(true);
	  }
	  
}
