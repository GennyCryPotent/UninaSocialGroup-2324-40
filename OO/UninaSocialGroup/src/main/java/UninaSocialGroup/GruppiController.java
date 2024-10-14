package UninaSocialGroup;

import javax.swing.JOptionPane;

public class GruppiController {

	private GestioneFinestre GF = new GestioneFinestre();
	private GruppiGUI gruppiView;
	private OperazioniPartecipanteGUI eliminaPartecipanteView;
	private ContenutiDAO C = new ContenutiDAO();
	private PartecipanoDAO partecipano_DAO = new PartecipanoDAO();
	private Regolano_DAO regolano_DAO = new Regolano_DAO();

	public GruppiController(GruppiGUI gruppiView) {
		this.gruppiView = gruppiView;
	}

	public GruppiController(OperazioniPartecipanteGUI eliminaPartecipanteView) {
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
			GF.MostraGruppi(NU, NG);
		}

	}

	public void ActionModifica(String NU, String NG) {

		GF.EliminaContenuto(NU, NG, gruppiView);

	}

	public void ActionElimina(String NU, String NG, String Ruolo, int checkOp) {

		GF.EliminaPartecipante(NU, NG, Ruolo, checkOp);
	}

	public void ActionAbbandona(String NU, String NG, boolean CkeckCreatore) {
		int scelta;
		if (CkeckCreatore) { // messaggio personalizzato per il creatore del gruppo
			scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler cancellare questo gruppo? "
					+ "PS. se si cancella il gruppo, si elimineranno tutti i contenuti e tutti gli utenti del gruppo usciranno.",
					"Abbandona", JOptionPane.YES_NO_OPTION);

		} else {
			scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler abbandonare questo gruppo?", "Abbandona",
					JOptionPane.YES_NO_OPTION);
		}

		if (scelta == JOptionPane.YES_OPTION) {
			partecipano_DAO.DelPartecipante(NU, NG);
			JOptionPane.showMessageDialog(null, "Hai abbandonato il gruppo " + NG, "Abbandonato",
					JOptionPane.INFORMATION_MESSAGE);
			GF.AccessoHome(NU);
			gruppiView.setVisible(false);
		}

	}

	public void ActionRimuoviUtente(Partecipano p, String NG) {

		int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare questo utente?", "Elimina",
				JOptionPane.YES_NO_OPTION);

		if (scelta == JOptionPane.YES_OPTION) {
			partecipano_DAO.DelPartecipante(p.getNome_Partecipante(), NG);
			JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " eliminato", "Eliminato",
					JOptionPane.INFORMATION_MESSAGE);
			eliminaPartecipanteView.setVisible(false);
		} else if (scelta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " non eliminato",
					"Eliminato", JOptionPane.INFORMATION_MESSAGE);
			eliminaPartecipanteView.setVisible(false);
		}

	}

	public void ActionAggiungiAmministratore(Partecipano p, String NG) {

		int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler rendere amministratore questo utente?",
				"Aggiungi amministratore", JOptionPane.YES_NO_OPTION);

		if (scelta == JOptionPane.YES_OPTION) {
			regolano_DAO.InsAmministratore(p.getNome_Partecipante(), NG);
			JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " reso amministratore",
					"Aggiungi amministratore", JOptionPane.INFORMATION_MESSAGE);
			eliminaPartecipanteView.setVisible(false);
		} else if (scelta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " non reso amministratore",
					"Aggiungi amministratore", JOptionPane.INFORMATION_MESSAGE);
			eliminaPartecipanteView.setVisible(false);
		}

	}

}
