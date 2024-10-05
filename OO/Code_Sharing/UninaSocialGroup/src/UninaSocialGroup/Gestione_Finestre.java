package UninaSocialGroup;

import java.awt.EventQueue;

public class Gestione_Finestre {
	private String NU;  //variabile per il passaggio del nomeUtente
    private String Trova; //variabile per la ricerca
    private String NG; // nome Gruppo
	private Home Accesso = new Home(NU); //oggetto Home
	private GruppiGUI Gruppo = new GruppiGUI(NU); //oggetto GruppiGUI
	private Notifiche Notifica = new Notifiche();  //oggetto Notifiche  
	private Report_Statistico Report= new Report_Statistico();
	public Gestione_Finestre() {
		
	}
	
	public static void main(String[] args) {  //main login
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notifiche frame = new Notifiche();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	  public void GruppiGUI(String NU) {
		  Gruppo = new GruppiGUI(NU);
		  Gruppo.setVisible(true);
	  }
	
	  public void AccessoHome(String NU) {
		  Accesso = new Home(NU); // Passa NU al costruttore di Home
		  Accesso.setVisible(true);
		
	  }
	  
	  public void Notifiche() {
		  Notifica.setVisible(true);
		  
	  }
	  
	  public void Report_S() {
		  Report.setVisible(true);
	  }
	  
}
