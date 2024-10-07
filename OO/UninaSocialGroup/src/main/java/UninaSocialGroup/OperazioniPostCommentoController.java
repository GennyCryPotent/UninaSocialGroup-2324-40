package UninaSocialGroup;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class OperazioniPostCommentoController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Operazioni_Post_Commento_GUI OperazioniPostCommentoView;
	private Contenuti_DAO C = new Contenuti_DAO();
	private Commenti_DAO CO = new Commenti_DAO();

	public OperazioniPostCommentoController(Operazioni_Post_Commento_GUI operazioniPostCommentoView) {
		OperazioniPostCommentoView = operazioniPostCommentoView;
	}

	public void ActionEliminaPost(JButton Button_Elimina, String NU, String NG, int IdPost) {

			C.DelContenuto(IdPost);
			OperazioniPostCommentoView.setVisible(false);
			
			GF.GruppiGUI(NU, NG);
		

	}

	public void ActionModificaPost(JButton Button_Modifica, String NU, String NG, int IdPost) {
		
			String NewPost = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?", "Modifica un commento",
					JOptionPane.QUESTION_MESSAGE);

			C.UpContenuto(NU, NewPost, IdPost);
			OperazioniPostCommentoView.setVisible(false);
			
			GF.GruppiGUI(NU, NG);

	}
	
	public void ActionEliminaCommento(JButton Button_Elimina, String NU, String NG, int IdContenuto, int IdCommento) {

			CO.DelCommento(IdCommento);
			GF.Info_Post(IdContenuto, NU, NG, 1);
			OperazioniPostCommentoView.setVisible(false);
		
	}
	
	public void ActionModificaCommento(JButton Button_Modifica, String NU, String NG, int IdContenuto, int IdCommento) {

			String NewCommento = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?",
					"Modifica un commento", JOptionPane.QUESTION_MESSAGE);

			CO.UpCommento(NU, IdCommento, NewCommento);
			GF.Info_Post(IdContenuto, NU, NG, 1);
			OperazioniPostCommentoView.setVisible(false);
		
	}

	
	
	public void ActionAnnulla(String NU, String NG) {
		OperazioniPostCommentoView.setVisible(false);
		GF.GruppiGUI(NU, NG);
	}

}
