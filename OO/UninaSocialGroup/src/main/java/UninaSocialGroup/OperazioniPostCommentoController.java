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

	public void ActionEliminaPost(JButton Button_Elimina, String NU, String NG) {

		try {
			int DelPost = Integer.parseInt(JOptionPane.showInputDialog(Button_Elimina, "Quale post vuoi eliminare?",
					"Elimina un post", JOptionPane.QUESTION_MESSAGE));
			C.DelContenuto(DelPost);
			OperazioniPostCommentoView.setVisible(false);
			
			GF.GruppiGUI(NU, NG);
		} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
											// di input
			JOptionPane.showMessageDialog(Button_Elimina, "Inserisci un numero!");
		}

	}

	public void ActionModificaPost(JButton Button_Modifica, String NU, String NG) {

		try {
			int ModPost = Integer.parseInt(JOptionPane.showInputDialog(Button_Modifica,
					"Quale commento vuoi modificare?", "Modifica un commento", JOptionPane.QUESTION_MESSAGE));
			String NewPost = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?", "Modifica un commento",
					JOptionPane.QUESTION_MESSAGE);

			C.UpContenuto(NU, NewPost, ModPost);
			OperazioniPostCommentoView.setVisible(false);
			
			GF.GruppiGUI(NU, NG);
		} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
											// di input
			JOptionPane.showMessageDialog(Button_Modifica, "Inserisci un numero!");
		}

	}
	
	public void ActionEliminaCommento(JButton Button_Elimina, String NU, String NG, int Id_Contenuto) {

		try {
			int DelCommento = Integer.parseInt(JOptionPane.showInputDialog(Button_Elimina,
					"Quale commento vuoi eliminare?", "Elimina un commento", JOptionPane.QUESTION_MESSAGE));
			CO.DelCommento(DelCommento);
			GF.Info_Post(Id_Contenuto, NU, NG, 1);
			OperazioniPostCommentoView.setVisible(false);
		} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
											// di input
			JOptionPane.showMessageDialog(Button_Elimina, "Inserisci un numero!");
		}
	}
	
	public void ActionModificaCommento(JButton Button_Modifica, String NU, String NG, int Id_Contenuto) {

		try {
			int ModCommento = Integer.parseInt(JOptionPane.showInputDialog(Button_Modifica,
					"Quale commento vuoi modificare?", "Modifica un commento", JOptionPane.QUESTION_MESSAGE));
			String NewCommento = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?",
					"Modifica un commento", JOptionPane.QUESTION_MESSAGE);

			CO.UpCommento(NU, ModCommento, NewCommento);
			GF.Info_Post(Id_Contenuto, NU, NG, 1);
			OperazioniPostCommentoView.setVisible(false);
		} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
											// di input
			JOptionPane.showMessageDialog(Button_Modifica, "Inserisci un numero!");
		}
	}

	
	
	public void ActionAnnulla(String NU, String NG) {
		OperazioniPostCommentoView.setVisible(false);
		GF.GruppiGUI(NU, NG);
	}

}
