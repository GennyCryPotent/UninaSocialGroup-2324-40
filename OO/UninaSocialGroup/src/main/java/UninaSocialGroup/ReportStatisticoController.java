package UninaSocialGroup;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReportStatisticoController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Report_Statistico_GUI ReportStatisticoView;
	private List<Gruppi> Res_Gruppi = new ArrayList<Gruppi>();
	private List<Contenuti> Res_Contenuti = new ArrayList<Contenuti>();

	public ReportStatisticoController(Report_Statistico_GUI reportStatisticoView) {
		ReportStatisticoView = reportStatisticoView;
	}

	public void ActionIndietro(String NU) {
		ReportStatisticoView.setVisible(false);
		GF.AccessoHome(NU);
	}

	public void ActionSelMese(JTable tableStatContenuti, String NU, JComboBox Sel_Mese) {
		tableStatContenuti.setModel(new DefaultTableModel(Rec_Stat_Contenuti(NU, Sel_Mese.getSelectedIndex() + 1),
				new String[] { "Gruppo", "Contenuto con più Like", "Contenuto con meno Like",
						"Contenuto con più Commenti", "Contenuto con meno Commenti" }));
	}

	// Recupera i post con più/meno commenti e like
	public Object[][] Rec_Stat_Contenuti(String NU, int Mese) {

		int TMP;
		int indice_L_M = 0; // contenuto con più like
		int indice_L_m = 0; // contenuto con meno like
		int indice_C_M = 0; // contenuto con più commenti
		int indice_C_m = 0; // contenuto con meno commenti

		Gruppi_DAO G = new Gruppi_DAO();
		Likes_DAO L = new Likes_DAO();
		Contenuti_DAO C = new Contenuti_DAO();
		Commenti_DAO COM = new Commenti_DAO();

		Res_Gruppi = G.SelAllGruppoUtente(NU);

		Object[][] Res = new Object[Res_Gruppi.size()][]; // Array 2D che contiene tutte le righe della tabella (i dati
															// sui post)

		for (int i = 0; i < Res_Gruppi.size(); i++) {

			boolean Check = false;

			while (!Check) { // Ciclo per vedere se le prossime istruzioni creano un'eccezzione
				try {
					int max_L = 0;
					int max_C = 0;
					int min_L = 10000;
					int min_C = 10000;

					Res_Contenuti = C.SelAllContenutiMeseGruppo(Res_Gruppi.get(i).getNome(), Mese);

					for (int j = 0; j < Res_Contenuti.size(); j++) {

						TMP = L.SelNumLike(Res_Contenuti.get(j).getId_Contenuto());

						if (TMP > max_L) { // Controllo Like
							max_L = TMP;
							indice_L_M = j;
						} else if (TMP < min_L) {
							min_L = TMP;
							indice_L_m = j;
						}

						TMP = COM.SelNumCommenti(Res_Contenuti.get(j).getId_Contenuto());

						if (TMP > max_C) { // Controllo Commenti
							max_C = TMP;
							indice_C_M = j;
						} else if (TMP < min_C) {
							min_C = TMP;
							indice_C_m = j;
						}
					}

					Res[i] = new Object[] { Res_Gruppi.get(i).getNome(), Res_Contenuti.get(indice_L_M).getTesto(),
							Res_Contenuti.get(indice_L_m).getTesto(), Res_Contenuti.get(indice_C_M).getTesto(),
							Res_Contenuti.get(indice_C_m).getTesto() };

					Check = true; // non ci sono eccezioni, vai avanti con il for

				} catch (Exception e) {

					// Mostro il nome che non ha dati a sufficienza per il report_statistico
					Res[i] = new Object[] { Res_Gruppi.get(i).getNome(), "Dati non disponibili", "Dati non disponibili",
							"Dati non disponibili", "Dati non disponibili" };

					i++; // incremento la i per la prossima interazione

					if (i >= Res_Gruppi.size()) { // se la lista è stata letta tutta, esci
						break;
					}

				}
			}

		}

		return Res;
	}

}
