package UninaSocialGroup;

import java.awt.EventQueue;

public class Gestione_Finestre {
	
	private Home_GUI Accesso;
	private Gruppi_GUI Gruppo;
	private Notifiche_GUI Notifica;  
	private Elimina_Contenuto_GUI Elimina; 
	private Report_Statistico_GUI Report;
	
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //portatile genny
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //Fisso Genny
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Caruso"); //Caruso
	//public static DB_Connection DB = new DB_Connection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Unina@03"); //Gabbo
	
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
		  Elimina = new Elimina_Contenuto_GUI(NG, NU); 
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
