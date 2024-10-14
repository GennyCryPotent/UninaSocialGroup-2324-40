package UninaSocialGroup;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class OperazioniPostCommentoController {

	private GestioneFinestre GF = new GestioneFinestre();
	private OperazioniPostCommentoGUI OperazioniPostCommentoView;
	private ContenutiDAO C = new ContenutiDAO();
	private CommentiDAO CO = new CommentiDAO();

	public OperazioniPostCommentoController(OperazioniPostCommentoGUI operazioniPostCommentoView) {
		OperazioniPostCommentoView = operazioniPostCommentoView;
	}

	public void ActionEliminaPost(JButton Button_Elimina, String NU, String NG, int IdPost) {

			C.DelContenuto(IdPost);
			OperazioniPostCommentoView.setVisible(false);
			
			GF.MostraGruppi(NU, NG);
		

	}

	public void ActionModificaPost(JButton Button_Modifica, String NU, String NG, int IdPost) {
		
			String NewPost = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?", "Modifica un commento",
					JOptionPane.QUESTION_MESSAGE);

			C.UpContenuto(NU, NewPost, IdPost);
			OperazioniPostCommentoView.setVisible(false);
			
			GF.MostraGruppi(NU, NG);

	}
	
	public void ActionEliminaCommento(JButton Button_Elimina, String NU, String NG, int IdContenuto, int IdCommento) {

			CO.DelCommento(IdCommento);
			GF.InfoPost(IdContenuto, NU, NG, 1);
			OperazioniPostCommentoView.setVisible(false);
		
	}
	
	public void ActionModificaCommento(JButton Button_Modifica, String NU, String NG, int IdContenuto, int IdCommento) {

			String NewCommento = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?",
					"Modifica un commento", JOptionPane.QUESTION_MESSAGE);

			CO.UpCommento(NU, IdCommento, NewCommento);
			GF.InfoPost(IdContenuto, NU, NG, 1);
			OperazioniPostCommentoView.setVisible(false);
		
	}

	
	
	public void ActionAnnulla(String NU, String NG, int checkSchermata, int IdContenuto, int checkInfoPost ) {
		OperazioniPostCommentoView.setVisible(false);
		
		if(checkSchermata==0) {	//0 GruppiGUI; 1 InfoPostGUI
			GF.MostraGruppi(NU, NG);
		}else {
			GF.InfoPost(IdContenuto, NU, NG, checkInfoPost);
		}
	}

}
