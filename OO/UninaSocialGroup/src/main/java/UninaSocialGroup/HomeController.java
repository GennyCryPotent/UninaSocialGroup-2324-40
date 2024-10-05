package UninaSocialGroup;

public class HomeController {

	private Gestione_Finestre GF = new Gestione_Finestre();
    private Home_GUI homeView;
    
    public HomeController(Home_GUI homeView) {
        this.homeView = homeView;
        new Partecipano_DAO();
        new Contenuti_DAO();
        new Likes_DAO();
        new Commenti_DAO();

    }
    
    public void ActionReport(String NU) {
    	homeView.setVisible(false);
		GF.Report_S(NU);
    	
    }
   
    public void ActionNotifiche(String NU) {
    	homeView.setVisible(false);
		GF.Notifiche(NU);
    	
    }
    
    public void ActionGruppi(String NU, String NG) {
    	homeView.setVisible(false);
		GF.GruppiGUI(NU, NG);
    	
    }
    
    public void ActionRicerca(String NU) {
    	homeView.setVisible(true);
    	GF.RicercaGUI(NU, homeView);
    }
  

   }
