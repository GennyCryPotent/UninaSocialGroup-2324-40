package UninaSocialGroup;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPostController {

	private GestioneFinestre GF = new GestioneFinestre();
	private InfoPost_GUI infoView;
	private CommentiDAO CO = new CommentiDAO();

	public InfoPostController(InfoPost_GUI infoView) {
		this.infoView = infoView;
	}

	public void ActionIndietro(int check, String NU, String NG) {
		infoView.setVisible(false);

		if (check == 0) { // ritorna nella schermata che ha invocato InfoPost (0 = Home ; 1 = Gruppi)
			GF.AccessoHome(NU);
		} else {
			GF.MostraGruppi(NU, NG);
		}
	}

	public void ActionAddCommento(JTextArea textAddCommento, JPanel panelCommenti, int Id_Contenuto, String NU, String NG, int check) {
		
		if (!textAddCommento.getText().isEmpty() && !textAddCommento.getText().equals("Aggiungi un commento...")) {
			JTextArea text = new JTextArea();
			CO.InsCommento(textAddCommento.getText(), Id_Contenuto, NU);
			text.append(NU + " :" + textAddCommento.getText() + "\n");
			panelCommenti.add(text);
			textAddCommento.setText("");
			infoView.setVisible(false);
			GF.InfoPost(Id_Contenuto, NU, NG, check);
		}

	}

	public void ActionModCommento(int Id_Contenuto, String NU, String NG, int check) {
		infoView.setVisible(false);
		GF.EliminaCommento(NU, NG, Id_Contenuto, check);
	}

}
