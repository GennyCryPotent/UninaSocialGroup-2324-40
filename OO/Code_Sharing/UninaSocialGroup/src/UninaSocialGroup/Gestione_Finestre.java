package UninaSocialGroup;

import java.awt.EventQueue;

public class Gestione_Finestre {
	private String NU;  //variabile per il passaggio del nomeUtente
    private String Trova; //variabile per la ricerca
    private String NG; // nome Gruppo
	private Home Accesso = new Home(NU); //oggetto Home
	private Gruppi Gruppo = new Gruppi(NU); //oggetto Gruppi
	private Notifiche Notifica = new Notifiche();  //oggetto Notifiche  
	private Report_Statistico Report= new Report_Statistico();
	public Gestione_Finestre() {
		
	}
	
	public static void main(String[] args) {  //main login
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	  public void Gruppi(String NU) {
		  Gruppo = new Gruppi(NU);
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