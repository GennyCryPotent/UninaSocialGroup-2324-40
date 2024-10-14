package UninaSocialGroup;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HomeController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
    private HomeGUI homeView;
    private CreaGruppoGUI creaGruppoView;
    
    public HomeController(CreaGruppoGUI creaGruppoView) {
		this.creaGruppoView = creaGruppoView;
	}

	public HomeController(HomeGUI homeView) {
        this.homeView = homeView;
    }
    
    public void ActionReport(String nomeUtente) {
    	homeView.setVisible(false);
		gestioneFinestre.ReportS(nomeUtente);
    	
    }
   
    public void ActionNotifiche(String nomeUtente) {
    	homeView.setVisible(false);
		gestioneFinestre.MostraNotifiche(nomeUtente);
    	
    }
    
    public void ActionGruppi(String nomeUtente, String nomeGruppo) {
    	homeView.setVisible(false);
		gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);
    	
    }
    
    public void ActionRicerca(String nomeUtente) {
    	homeView.setVisible(true);
    	gestioneFinestre.MostraRicerca(nomeUtente, homeView);
    }
  
    public void ActionCrea(String nomeUtente) {
		
		gestioneFinestre.CreaGruppo(nomeUtente, homeView);
		
	}
    
    public void ActionLogout() {
    	homeView.setVisible(false);
    	gestioneFinestre.MostraLogin();
    }
    
    public void ActionCreaGruppo(JTextField NGruppoTxt, JTextArea descrizioneTxt, String nomeUtente, HomeGUI home, List<String> listOfTags) {
    	String nomeGruppo;
	    String descrGruppo;
	    GruppiDAO CreaGruppo = new GruppiDAO();
		PossiedonoDAO possiedonoDAO = new PossiedonoDAO();

	   nomeGruppo=NGruppoTxt.getText();
	   descrGruppo=descrizioneTxt.getText();
	   

	   
	   
	   
	   if(nomeGruppo.isEmpty() || descrGruppo.isEmpty()) {
		   JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati", "Credenziali errate",
					JOptionPane.WARNING_MESSAGE);
	    }else {
	   
	    CreaGruppo.InsGruppo(nomeGruppo ,descrGruppo , nomeUtente);
			
	    for(String tag : listOfTags) {
	    	possiedonoDAO.InsTagGruppo(nomeGruppo, tag);
	    }
		creaGruppoView.setVisible(false);
		home.setVisible(false);
		gestioneFinestre.AccessoHome(nomeUtente);
		
	   }
    }
 }
