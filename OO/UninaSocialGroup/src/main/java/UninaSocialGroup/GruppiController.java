package UninaSocialGroup;

import javax.swing.JOptionPane;

public class GruppiController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private GruppiGUI gruppiView;
	private OperazioniPartecipanteGUI eliminaPartecipanteView;
	private ContenutiDAO contenutiDAO = new ContenutiDAO();
	private PartecipanoDAO partecipanoDAO = new PartecipanoDAO();
	private RegolanoDAO regolanoDAO = new RegolanoDAO();

	public GruppiController(GruppiGUI gruppiView) {
		this.gruppiView = gruppiView;
	}

	public GruppiController(OperazioniPartecipanteGUI eliminaPartecipanteView) {
		this.eliminaPartecipanteView = eliminaPartecipanteView;
	}

	public void ActionHome(String nomeUtente) {
		gruppiView.setVisible(false);
		gestioneFinestre.AccessoHome(nomeUtente);
	}

	public void ActionPost(String nomeUtente, String nomeGruppo, String NewPost) {

		try {
			if (!NewPost.isEmpty()) {
				contenutiDAO.InsContenuto(null, NewPost, nomeGruppo, nomeUtente);

				gruppiView.setVisible(false);
				gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);
			}
		}catch(NullPointerException e) {
			System.out.println("Stringa vuota");
		}

	}

	public void ActionModifica(String nomeUtente, String nomeGruppo) {

		gestioneFinestre.EliminaContenuto(nomeUtente, nomeGruppo, gruppiView);

	}

	public void ActionElimina(String nomeUtente, String nomeGruppo, String Ruolo, int checkOp) {

		gestioneFinestre.EliminaPartecipante(nomeUtente, nomeGruppo, Ruolo, checkOp);
	}

	public void ActionAbbandona(String nomeUtente, String nomeGruppo, boolean ckeckCreatore) {
		int scelta;
		if (ckeckCreatore) { // messaggio personalizzato per il creatore del gruppo
			scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler cancellare questo gruppo? "
					+ "PS. se si cancella il gruppo, si elimineranno tutti i contenuti e tutti gli utenti del gruppo usciranno.",
					"Abbandona", JOptionPane.YES_NO_OPTION);

		} else {
			scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler abbandonare questo gruppo?", "Abbandona",
					JOptionPane.YES_NO_OPTION);
		}

		if (scelta == JOptionPane.YES_OPTION) {
			partecipanoDAO.DelPartecipante(nomeUtente, nomeGruppo);
			JOptionPane.showMessageDialog(null, "Hai abbandonato il gruppo " + nomeGruppo, "Abbandonato",
					JOptionPane.INFORMATION_MESSAGE);
			gestioneFinestre.AccessoHome(nomeUtente);
			gruppiView.setVisible(false);
		}

	}

	public void ActionRimuoviUtente(Partecipano p, String nomeGruppo) {

		int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare questo utente?", "Elimina",
				JOptionPane.YES_NO_OPTION);

		if (scelta == JOptionPane.YES_OPTION) {
			partecipanoDAO.DelPartecipante(p.getNome_Partecipante(), nomeGruppo);
			JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " eliminato", "Eliminato",
					JOptionPane.INFORMATION_MESSAGE);
			eliminaPartecipanteView.setVisible(false);
		} else if (scelta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " non eliminato",
					"Eliminato", JOptionPane.INFORMATION_MESSAGE);
			eliminaPartecipanteView.setVisible(false);
		}

	}

	public void ActionAggiungiAmministratore(Partecipano p, String nomeGruppo) {

		int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler rendere amministratore questo utente?",
				"Aggiungi amministratore", JOptionPane.YES_NO_OPTION);

		if (scelta == JOptionPane.YES_OPTION) {
			regolanoDAO.InsAmministratore(p.getNome_Partecipante(), nomeGruppo);
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
