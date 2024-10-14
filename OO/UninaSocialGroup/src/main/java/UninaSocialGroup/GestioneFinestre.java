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
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@Gennaro.homenet.telecomitalia.it:1521:xe", "system", "Database@03"); //Fisso Genny

	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@DESKTOP-MLJV8GK:1521:xe", "system", "Caruso"); //Caruso

	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@errore31:1521:xe", "SYSTEM", "Caruso"); //Caruso Portatile
	
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Database@03"); //Gabbo

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
	
	  public void MostraGruppi(String NU, String NG) {
		  Gruppo = new GruppiGUI(NU, NG);
		  Gruppo.setVisible(true);
	  
	  }
	
	  public void AccessoHome(String NU) {
		  Accesso = new HomeGUI(NU); 
		  Accesso.setVisible(true);
		  
	  }
	  
	  public void EliminaContenuto(String NG, String NU, GruppiGUI gruppiView) {
		  Elimina = new OperazioniPostCommentoGUI(NG, NU, gruppiView); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void EliminaCommento(String NG, String NU, int Id_Contenuto, int checkSchermata) {
		  Elimina = new OperazioniPostCommentoGUI(NG, NU, Id_Contenuto, checkSchermata); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void InfoPost(int Id_Contenuto, String NU, String NG, int check ) {
		  InfoPost = new InfoPost_GUI(Id_Contenuto,NU, NG, check); 
		  InfoPost.setVisible(true);
		
	  }

	  
	  public void MostraNotifiche(String NU) {
		  Notifica = new NotificheGUI(NU);
		  Notifica.setVisible(true);
		  
	  }
	  
	  public void ReportS(String NU) {
		  Report = new ReportStatisticoGUI(NU);
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
	  
	  public void MostraRicerca(String NU, HomeGUI homeView) {
		  Ricerca = new RicercaGUI(NU, homeView);
		  Ricerca.setVisible(true);
	  }
	  
	  public void EliminaPartecipante(String NU, String NG, String Ruolo, int checkOp) {
		  EliminaP = new OperazioniPartecipanteGUI(NU, NG, Ruolo, checkOp);
		  EliminaP.setVisible(true);
	  }
	  
	  public void CreaGruppo(String NU, HomeGUI home) {
		  CreaG = new CreaGruppoGUI(NU, home);
		  CreaG.setVisible(true);
	  }
	  
}
