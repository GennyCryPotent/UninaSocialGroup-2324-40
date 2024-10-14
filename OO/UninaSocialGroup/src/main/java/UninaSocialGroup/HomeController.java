package UninaSocialGroup;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HomeController {

	private GestioneFinestre GF = new GestioneFinestre();
    private HomeGUI homeView;
    private CreaGruppoGUI creaGruppoView;
    
    public HomeController(CreaGruppoGUI creaGruppoView) {
		this.creaGruppoView = creaGruppoView;
	}

	public HomeController(HomeGUI homeView) {
        this.homeView = homeView;
    }
    
    public void ActionReport(String NU) {
    	homeView.setVisible(false);
		GF.ReportS(NU);
    	
    }
   
    public void ActionNotifiche(String NU) {
    	homeView.setVisible(false);
		GF.MostraNotifiche(NU);
    	
    }
    
    public void ActionGruppi(String NU, String NG) {
    	homeView.setVisible(false);
		GF.MostraGruppi(NU, NG);
    	
    }
    
    public void ActionRicerca(String NU) {
    	homeView.setVisible(true);
    	GF.MostraRicerca(NU, homeView);
    }
  
    public void ActionCrea(String NU) {
		
		GF.CreaGruppo(NU, homeView);
		
	}
    
    public void ActionLogout() {
    	homeView.setVisible(false);
    	GF.MostraLogin();
    }
    
    public void ActionCreaGruppo(JTextField NGruppo, JTextArea Descrizione, String NU, HomeGUI home, List<String> listOfTags) {
    	String NG;
	    String DescrG;
	    GruppiDAO CreaG = new GruppiDAO();
		PossiedonoDAO possiedonoDAO = new PossiedonoDAO();

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
