package UninaSocialGroup;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HomeController {

	private Gestione_Finestre GF = new Gestione_Finestre();
    private Home_GUI homeView;
    private CreaGruppo_GUI creaGruppoView;
    
    public HomeController(CreaGruppo_GUI creaGruppoView) {
		this.creaGruppoView = creaGruppoView;
	}

	public HomeController(Home_GUI homeView) {
        this.homeView = homeView;
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
  
    public void ActionCrea(String NU) {
		
		GF.CreaGruppo(NU, homeView);
		
	}
    
    public void ActionLogout() {
    	homeView.setVisible(false);
    	GF.LoginGUI();
    }
    
    public void ActionCreaGruppo(JTextField NGruppo, JTextArea Descrizione, String NU, Home_GUI home, List<String> listOfTags) {
    	String NG;
	    String DescrG;
	    Gruppi_DAO CreaG = new Gruppi_DAO();
		Possiedono_DAO possiedonoDAO = new Possiedono_DAO();

	   NG=NGruppo.getText();
	   DescrG=Descrizione.getText();
	   

	   
	   
	   
	   if(NG.isEmpty() || DescrG.isEmpty()) {
		   JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati", "Credenziali errate",
					JOptionPane.WARNING_MESSAGE);
	    }else {
	   
	    CreaG.InsGruppo(NG ,DescrG , NU);
			
	    for(String tag : listOfTags) {
	    	possiedonoDAO.InsTagGruppo(NG, tag);
	    }
		creaGruppoView.setVisible(false);
		home.setVisible(false);
		GF.AccessoHome(NU);
		
	   }
    }
 }
