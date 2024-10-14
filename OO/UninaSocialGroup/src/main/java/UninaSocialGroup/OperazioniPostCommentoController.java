package UninaSocialGroup;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class OperazioniPostCommentoController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private OperazioniPostCommentoGUI OperazioniPostCommentoView;
	private ContenutiDAO contenutiDAO = new ContenutiDAO();
	private CommentiDAO commentiDAO = new CommentiDAO();

	public OperazioniPostCommentoController(OperazioniPostCommentoGUI operazioniPostCommentoView) {
		OperazioniPostCommentoView = operazioniPostCommentoView;
	}

	public void ActionEliminaPost(JButton buttonElimina, String nomeUtente, String nomeGruppo, int idPost) {

			contenutiDAO.DelContenuto(idPost);
			OperazioniPostCommentoView.setVisible(false);
			
			gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);
		

	}

	public void ActionModificaPost(JButton buttonModifica, String nomeUtente, String nomeGruppo, int idPost) {
		
			String NewPost = JOptionPane.showInputDialog(buttonModifica, "Cosa vuoi scrivere?", "Modifica un commento",
					JOptionPane.QUESTION_MESSAGE);

			contenutiDAO.UpContenuto(nomeUtente, NewPost, idPost);
			OperazioniPostCommentoView.setVisible(false);
			
			gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);

	}
	
	public void ActionEliminaCommento(JButton buttonElimina, String nomeUtente, String nomeGruppo, int IdContenuto, int idCommento) {

			commentiDAO.DelCommento(idCommento);
			gestioneFinestre.InfoPost(IdContenuto, nomeUtente, nomeGruppo, 1);
			OperazioniPostCommentoView.setVisible(false);
		
	}
	
	public void ActionModificaCommento(JButton buttonModifica, String nomeUtente, String nomeGruppo, int IdContenuto, int idCommento) {

			String newCommento = JOptionPane.showInputDialog(buttonModifica, "Cosa vuoi scrivere?",
					"Modifica un commento", JOptionPane.QUESTION_MESSAGE);

			commentiDAO.UpCommento(nomeUtente, idCommento, newCommento);
			gestioneFinestre.InfoPost(IdContenuto, nomeUtente, nomeGruppo, 1);
			OperazioniPostCommentoView.setVisible(false);
		
	}

	
	
	public void ActionAnnulla(String nomeUtente, String nomeGruppo, int checkSchermata, int IdContenuto, int checkInfoPost ) {
		OperazioniPostCommentoView.setVisible(false);
		
		if(checkSchermata==0) {	//0 GruppiGUI; 1 InfoPostGUI
			gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);
		}else {
			gestioneFinestre.InfoPost(IdContenuto, nomeUtente, nomeGruppo, checkInfoPost);
		}
	}

}
