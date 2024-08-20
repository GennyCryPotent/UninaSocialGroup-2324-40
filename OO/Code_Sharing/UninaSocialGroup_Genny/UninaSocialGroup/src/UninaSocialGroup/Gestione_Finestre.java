package UninaSocialGroup;

import java.awt.EventQueue;

public class Gestione_Finestre {
	
	private Home_GUI Accesso;
	private Gruppi_GUI Gruppo;
	private Notifiche_GUI Notifica = new Notifiche_GUI();  //oggetto Notifiche  
	private Elimina_Contenuto_GUI Elimina; 
	private Report_Statistico_GUI Report;
	
	public Gestione_Finestre() {
		
	}
	
	public static void main(String[] args) {  //main login
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI frame = new Login_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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
		  Notifica.setVisible(true);
		  
	  }
	  
	  public void Report_S(String NU) {
		  Report = new Report_Statistico_GUI(NU);
		  Report.setVisible(true);
	  }
	  
}
