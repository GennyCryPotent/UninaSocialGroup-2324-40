package UninaSocialGroup;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPostController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private InfoPostGUI infoView;
	private CommentiDAO commentiDAO = new CommentiDAO();

	public InfoPostController(InfoPostGUI infoView) {
		this.infoView = infoView;
	}

	public void ActionIndietro(int check, String nomeUtente, String nomeGruppo) {
		infoView.setVisible(false);

		if (check == 0) { // ritorna nella schermata che ha invocato InfoPost (0 = Home ; 1 = Gruppi)
			gestioneFinestre.AccessoHome(nomeUtente);
		} else {
			gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);
		}
	}

	public void ActionAddCommento(JTextArea textAddCommento, JPanel panelCommenti, int id_Contenuto, String nomeUtente, String nomeGruppo, int check) {
		
		if (!textAddCommento.getText().isEmpty() && !textAddCommento.getText().equals("Aggiungi un commento...")) {
			JTextArea text = new JTextArea();
			commentiDAO.InsCommento(textAddCommento.getText(), id_Contenuto, nomeUtente);
			text.append(nomeUtente + " :" + textAddCommento.getText() + "\n");
			panelCommenti.add(text);
			textAddCommento.setText("");
			infoView.setVisible(false);
			gestioneFinestre.InfoPost(id_Contenuto, nomeUtente, nomeGruppo, check);
		}

	}

	public void ActionModCommento(int id_Contenuto, String nomeUtente, String nomeGruppo, int check, JFrame schermataVecchia) {
		gestioneFinestre.EliminaCommento(nomeUtente, nomeGruppo, id_Contenuto, check, schermataVecchia);
	}

}
