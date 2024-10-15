package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;


public class GestioneFinestre {
	
	private HomeGUI home;
	private GruppiGUI gruppo;
	private NotificheGUI notifica;  
	private OperazioniPostCommentoGUI elimina; 
	private ReportStatisticoGUI report;
	private InfoPostGUI infoPost;
	private RegistrazioneGUI registrazione;
	private LoginGUI login;
	private RicercaGUI ricerca;
	private OperazioniPartecipanteGUI eliminaPartecpiante;
	private CreaGruppoGUI creaGruppo;
	
	//public static DBConnection DB = new DBConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //portatile genny
	//public static DB_Connection DB = new DBConnection("jdbc:oracle:thin:@Gennaro.homenet.telecomitalia.it:1521:xe", "system", "Database@03"); //Fisso Genny

	public static DBConnection DB = new DBConnection("jdbc:oracle:thin:@DESKTOP-MLJV8GK:1521:xe", "system", "Caruso"); //Caruso

	//public static DB_Connection DB = new DBConnection("jdbc:oracle:thin:@errore31:1521:xe", "SYSTEM", "Caruso"); //Caruso Portatile
	
	//public static DBConnection DB = new DBConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Database@03"); //Gabbo

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
					
					//LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					DB.close();
				}
			}
		});
	}
	
	  public void MostraGruppi(String nomeUtente, String nomeGruppo) {
		  gruppo = new GruppiGUI(nomeUtente, nomeGruppo);
		  gruppo.setVisible(true);
	  
	  }
	
	  public void AccessoHome(String nomeUtente) {
		  home = new HomeGUI(nomeUtente); 
		  home.setVisible(true);
		  
	  }
	  
	  public void EliminaContenuto(String nomeGruppo, String nomeUtente, GruppiGUI gruppiView) {
		  elimina = new OperazioniPostCommentoGUI(nomeGruppo, nomeUtente, gruppiView); 
		  elimina.setVisible(true);
		
	  }
	  
	  public void EliminaCommento(String nomeGruppo, String nomeUtente, int Id_Contenuto, int checkSchermata, JFrame schermataVecchia) {
		  elimina = new OperazioniPostCommentoGUI(nomeGruppo, nomeUtente, Id_Contenuto, checkSchermata, schermataVecchia); 
		  elimina.setVisible(true);
		
	  }
	  
	  public void InfoPost(int Id_Contenuto, String nomeUtente, String nomeGruppo, int check ) {
		  infoPost = new InfoPostGUI(Id_Contenuto,nomeUtente, nomeGruppo, check); 
		  infoPost.setVisible(true);
		
	  }

	  
	  public void MostraNotifiche(String nomeUtente) {
		  notifica = new NotificheGUI(nomeUtente);
		  notifica.setVisible(true);
		  
	  }
	  
	  public void ReportS(String nomeUtente) {
		  report = new ReportStatisticoGUI(nomeUtente);
		  report.setVisible(true);
	  }
	  
	  public void MostraRegistrazione() {
		  registrazione = new RegistrazioneGUI();
		  registrazione.setVisible(true);
	  }
	  
	  public void MostraLogin() {
		  login = new LoginGUI();
		  login.setVisible(true);
		  
	  }
	  
	  public void MostraRicerca(String nomeUtente, HomeGUI homeView) {
		  ricerca = new RicercaGUI(nomeUtente, homeView);
		  ricerca.setVisible(true);
	  }
	  
	  public void EliminaPartecipante(String nomeUtente, String nomeGruppo, String Ruolo, int checkOp) {
		  eliminaPartecpiante = new OperazioniPartecipanteGUI(nomeUtente, nomeGruppo, Ruolo, checkOp);
		  eliminaPartecpiante.setVisible(true);
	  }
	  
	  public void CreaGruppo(String nomeUtente, HomeGUI home) {
		  creaGruppo = new CreaGruppoGUI(nomeUtente, home);
		  creaGruppo.setVisible(true);
	  }
	  
}
