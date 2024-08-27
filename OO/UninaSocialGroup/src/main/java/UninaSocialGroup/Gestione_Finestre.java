package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JDialog;

public class Gestione_Finestre {
	
	private Home_GUI Accesso;
	private Gruppi_GUI Gruppo;
	private Notifiche_GUI Notifica;  
	private Operazioni_Post_Commento_GUI Elimina; 
	private Report_Statistico_GUI Report;
	private InfoPost_GUI InfoPost;
	
	public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //portatile genny
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@Gennaro.homenet.telecomitalia.it:1521:xe", "system", "Database@03"); //Fisso Genny
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@DESKTOP-MLJV8GK:1521:xe", "system", "Caruso"); //Caruso
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Database@03"); //Gabbo

	
	public Gestione_Finestre() {
		
	}
	
	public static void main(String[] args) {  //main login
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB.connect();
					Home_GUI frame = new Home_GUI("Genny03cry");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					DB.close();
				}
			}
		});
	}
	
	  public void GruppiGUI(String NU, String NG) {
		  Gruppo = new Gruppi_GUI(NU, NG);
		  Gruppo.setVisible(true);
	  }
	
	  public void AccessoHome(String NU) {
		  Accesso = new Home_GUI(NU); 
		  Accesso.setVisible(true);
		
	  }
	  
	  public void Elimina_Contenuto(String NG, String NU) {
		  Elimina = new Operazioni_Post_Commento_GUI(NG, NU); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void Elimina_Commento(String NG, String NU, int Id_Contenuto) {
		  Elimina = new Operazioni_Post_Commento_GUI(NG, NU, Id_Contenuto); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void Info_Post(int Id_Contenuto, String NU, String NG, int check ) {
		  InfoPost = new InfoPost_GUI(Id_Contenuto,NU, NG, check); 
		  InfoPost.setVisible(true);
		
	  }

	  
	  public void Notifiche(String NU) {
		  Notifica = new Notifiche_GUI(NU);
		  Notifica.setVisible(true);
		  
	  }
	  
	  public void Report_S(String NU) {
		  Report = new Report_Statistico_GUI(NU);
		  Report.setVisible(true);
	  }
	  
}
