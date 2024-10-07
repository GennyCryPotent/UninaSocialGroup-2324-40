package UninaSocialGroup;

import javax.swing.JOptionPane;

public class GruppiController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Gruppi_GUI gruppiView;
	private Contenuti_DAO C = new Contenuti_DAO();
	private Partecipano_DAO partecipano_DAO = new Partecipano_DAO();

	public GruppiController(Gruppi_GUI gruppiView) {
		this.gruppiView = gruppiView;
	}

	public void ActionHome(String NU) {
		gruppiView.setVisible(false);
		GF.AccessoHome(NU);
	}

	public void ActionPost(String NU, String NG, String NewPost) {

		if (!NewPost.isEmpty()) {
			C.InsContenuto(null, NewPost, NG, NU);

			gruppiView.setVisible(false);
			GF.GruppiGUI(NU, NG);
		}

	}

	public void ActionModifica(String NU, String NG) {

		GF.Elimina_Contenuto(NU, NG, gruppiView);

	}

	public void ActionElimina(String NU, String NG) {

		GF.EliminaPartecipante(NU, NG);
	}

	public void ActionAbbandona(String NU, String NG) {

		int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler abbandonare questo gruppo?", "Abbandona",
				JOptionPane.YES_NO_OPTION);

		if (scelta == JOptionPane.YES_OPTION) {
			partecipano_DAO.DelPartecipante(NU, NG);
			JOptionPane.showMessageDialog(null, "Hai abbandonato il gruppo " + NG, "Abbandonato",
					JOptionPane.INFORMATION_MESSAGE);
			GF.AccessoHome(NU);
			gruppiView.setVisible(false);
		}

	}

}
