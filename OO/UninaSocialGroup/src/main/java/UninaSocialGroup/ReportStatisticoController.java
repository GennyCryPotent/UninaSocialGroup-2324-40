package UninaSocialGroup;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReportStatisticoController {

	private GestioneFinestre gestioneFinsetre = new GestioneFinestre();
	private ReportStatisticoGUI reportStatisticoView;
	private List<Gruppi> resGruppi = new ArrayList<Gruppi>();
	private List<Contenuti> resContenuti = new ArrayList<Contenuti>();

	public ReportStatisticoController(ReportStatisticoGUI reportStatisticoView) {
		this.reportStatisticoView = reportStatisticoView;
	}

	public void ActionIndietro(String NU) {
		reportStatisticoView.setVisible(false);
		gestioneFinsetre.AccessoHome(NU);
	}

	public void ActionSelMese(JTable tableStatContenuti, String NU, JComboBox Sel_Mese) {
		tableStatContenuti.setModel(new DefaultTableModel(Rec_Stat_Contenuti(NU, Sel_Mese.getSelectedIndex() + 1),
				new String[] { "Gruppo", "Contenuto con più Like", "Contenuto con meno Like",
						"Contenuto con pi� Commenti", "Contenuto con meno Commenti" }));
	}

	// Recupera i post con più/meno commenti e like
	public Object[][] Rec_Stat_Contenuti(String NU, int Mese) {

		int tmp;
		int indice_L_M = 0; // contenuto con più like
		int indice_L_m = 0; // contenuto con meno like
		int indice_C_M = 0; // contenuto con più commenti
		int indice_C_m = 0; // contenuto con meno commenti

		GruppiDAO gruppiDAO = new GruppiDAO();
		LikesDAO likesDAO = new LikesDAO();
		ContenutiDAO contenutiDAO = new ContenutiDAO();
		CommentiDAO commentiDAO = new CommentiDAO();

		resGruppi = gruppiDAO.SelAllGruppoUtente(NU);

		Object[][] res = new Object[resGruppi.size()][]; // Array 2D che contiene tutte le righe della tabella (i dati
															// sui post)

		for (int i = 0; i < resGruppi.size(); i++) {

			boolean check = false;

			while (!check) { // Ciclo per vedere se le prossime istruzioni creano un'eccezzione
				try {
					int max_L = 0;
					int max_C = 0;
					int min_L = 10000;
					int min_C = 10000;

					resContenuti = contenutiDAO.SelAllContenutiMeseGruppo(resGruppi.get(i).GetNome(), Mese);

					for (int j = 0; j < resContenuti.size(); j++) {

						tmp = likesDAO.SelNumLike(resContenuti.get(j).getIdContenuto());

						if (tmp > max_L) { // Controllo Like
							max_L = tmp;
							indice_L_M = j;
						} else if (tmp < min_L) {
							min_L = tmp;
							indice_L_m = j;
						}

						tmp = commentiDAO.SelNumCommenti(resContenuti.get(j).getIdContenuto());

						if (tmp > max_C) { // Controllo Commenti
							max_C = tmp;
							indice_C_M = j;
						} else if (tmp < min_C) {
							min_C = tmp;
							indice_C_m = j;
						}
					}

					res[i] = new Object[] { resGruppi.get(i).GetNome(), resContenuti.get(indice_L_M).getTesto(),
							resContenuti.get(indice_L_m).getTesto(), resContenuti.get(indice_C_M).getTesto(),
							resContenuti.get(indice_C_m).getTesto() };

					check = true; // non ci sono eccezioni, vai avanti con il for

				} catch (Exception e) {

					// Mostro il nome che non ha dati a sufficienza per il report_statistico
					res[i] = new Object[] { resGruppi.get(i).GetNome(), "Dati non disponibili", "Dati non disponibili",
							"Dati non disponibili", "Dati non disponibili" };

					i++; // incremento la i per la prossima interazione

					if (i >= resGruppi.size()) { // se la lista è stata letta tutta, esci
						break;
					}

				}
			}

		}

		return res;
	}

}
