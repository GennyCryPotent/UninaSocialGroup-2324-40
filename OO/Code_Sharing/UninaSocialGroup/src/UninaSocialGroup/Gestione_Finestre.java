package UninaSocialGroup;


public class Gestione_Finestre {
	private String NU;  //variabile per il passaggio del nomeUtente
	private Home Accesso = new Home(NU);
      
	  
	public Gestione_Finestre() {
		
	}
	
	  public void AccessoHome(String NU) {
		  Accesso = new Home(NU); // Passa NU al costruttore di Home
		  Accesso.setVisible(true);
		
	  }
}
