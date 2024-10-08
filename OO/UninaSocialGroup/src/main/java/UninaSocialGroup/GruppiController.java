package UninaSocialGroup;

import javax.swing.JOptionPane;

public class GruppiController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Gruppi_GUI gruppiView;
	private EliminaPartecipante_GUI eliminaPartecipanteView;
	private Contenuti_DAO C = new Contenuti_DAO();
	private Partecipano_DAO partecipano_DAO = new Partecipano_DAO();

	public GruppiController(Gruppi_GUI gruppiView) {
		this.gruppiView = gruppiView;
	}
	
	public GruppiController(EliminaPartecipante_GUI eliminaPartecipanteView) {
		this.eliminaPartecipanteView = eliminaPartecipanteView;
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

	public void ActionElimina(String NU, String NG, String Ruolo) {

		GF.EliminaPartecipante(NU, NG, Ruolo);
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
	
	public void ActionRimuoviUtente(Partecipano p, String NG) {
		
		int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare questo utente?", "Elimina", JOptionPane.YES_NO_OPTION);

        if (scelta == JOptionPane.YES_OPTION) {
            partecipano_DAO.DelPartecipante(p.getNome_Partecipante(), NG);
            JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " eliminato", "Eliminato", JOptionPane.INFORMATION_MESSAGE);
            eliminaPartecipanteView.setVisible(false);
        } else if (scelta == JOptionPane.NO_OPTION) {
        	JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " non eliminato", "Eliminato", JOptionPane.INFORMATION_MESSAGE);
        	eliminaPartecipanteView.setVisible(false);
        }
		
	}

}
