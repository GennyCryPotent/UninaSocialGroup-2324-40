package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JDialog;

public class Gestione_Finestre {
	
	private Home_GUI Accesso;
	private Gruppi_GUI Gruppo;
	private Notifiche_GUI Notifica;  
	private Elimina_Post_Commento_GUI Elimina; 
	private Report_Statistico_GUI Report;
	
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //portatile genny
	public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@Gennaro.homenet.telecomitalia.it:1521:xe", "system", "Database@03"); //Fisso Genny
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Caruso"); //Caruso
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Database@03"); //Gabbo
	
	public Gestione_Finestre() {
		
	}
	
	public static void main(String[] args) {  //main login
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB.connect();
					Login_GUI frame = new Login_GUI();
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
		  Elimina = new Elimina_Post_Commento_GUI(NG, NU); 
		  Elimina.setVisible(true);
		
	  }
	  
	  public void Elimina_Commento(String NG, String NU, int Id_Contenuto) {
		  Elimina = new Elimina_Post_Commento_GUI(NG, NU, Id_Contenuto); 
		  Elimina.setVisible(true);
		
	  }

	  
	  public void Notifiche() {
		  Notifica = new Notifiche_GUI();
		  Notifica.setVisible(true);
		  
	  }
	  
	  public void Report_S(String NU) {
		  Report = new Report_Statistico_GUI(NU);
		  Report.setVisible(true);
	  }
	  
}
