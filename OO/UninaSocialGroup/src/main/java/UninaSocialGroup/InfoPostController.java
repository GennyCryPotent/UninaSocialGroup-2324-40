package UninaSocialGroup;

import javax.swing.JTextArea;

public class InfoPostController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private InfoPost_GUI infoView;
	private Commenti_DAO CO = new Commenti_DAO();

	public InfoPostController(InfoPost_GUI infoView) {
		this.infoView = infoView;
	}

	public void ActionIndietro(int check, String NU, String NG) {
		infoView.setVisible(false);

		if (check == 0) { // ritorna nella schermata che ha invocato InfoPost (0 = Home ; 1 = Gruppi)
			GF.AccessoHome(NU);
		} else {
			GF.GruppiGUI(NU, NG);
		}
	}

	public void ActionAddCommento(JTextArea textAddCommento, JTextArea textCommenti, int Id_Contenuto, String NU) {
		if (!textAddCommento.getText().isEmpty()) {
			CO.InsCommento(textAddCommento.getText(), Id_Contenuto, NU);
			textCommenti.append(NU + " :" + textAddCommento.getText() + "\n");
			textAddCommento.setText("");
		}

	}

	public void ActionModCommento(int Id_Contenuto, String NU, String NG) {
		infoView.setVisible(false);
		GF.Elimina_Commento(NU, NG, Id_Contenuto);
	}

}
