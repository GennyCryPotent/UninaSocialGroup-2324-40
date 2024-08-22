package UninaSocialGroup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Report_Statistico_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<Gruppi> Res_Gruppi = new ArrayList<Gruppi>();
	List<Contenuti> Res_Contenuti = new ArrayList<Contenuti>();
	private JTable table;
	private JTable tableStatContenuti;

	/**
	 * Create the frame.
	 */
	public Report_Statistico_GUI(String NU) {

		String[] mesi = { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre",
				"Ottobre", "Novembre", "Dicembre" };

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel Label = new JLabel();
		Label.setForeground(new Color(0, 128, 255));
		Label.setText("Report Statistico");
		Label.setFont(new Font("Tahoma", Font.BOLD, 18));

		// BOTTONI
		JButton Indietro = new JButton("<");
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report_Statistico_GUI.this.setVisible(false);
				Gestione_Finestre N = new Gestione_Finestre();
				N.AccessoHome(NU);
			}
		});
		Indietro.setForeground(new Color(0, 128, 255));
		Indietro.setFont(new Font("Arial Black", Font.BOLD, 17));
		Indietro.setBackground(Color.WHITE);

		// JCOMBOBOX
		// inserimento di tutti i mesi dell'anno
		JComboBox Sel_Mese = new JComboBox(mesi); // in questo caso posso mettere solo mesi
		Sel_Mese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableStatContenuti.setModel(
						new DefaultTableModel(Rec_Stat_Contenuti(NU, Sel_Mese.getSelectedIndex()+1), new String[] { "Gruppo", "Contenuto con più Like",
								"Contenuto con meno Like", "Contenuto con più Commenti", "Contenuto con meno Commenti" }));
			}
		});
		Sel_Mese.setBackground(Color.LIGHT_GRAY);

		//JTABLE
		tableStatContenuti = new JTable();
		tableStatContenuti.setModel(
				new DefaultTableModel(Rec_Stat_Contenuti(NU, Sel_Mese.getSelectedIndex()), new String[] { "Gruppo", "Contenuto con più Like",
						"Contenuto con meno Like", "Contenuto con più Commenti", "Contenuto con meno Commenti" }));
		tableStatContenuti.setBounds(27, 119, 684, 315);
		JScrollPane scrollPane = new JScrollPane(tableStatContenuti);

		// Ridimensionamento pagina
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(250)
					.addComponent(Label, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Sel_Mese, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(Label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(Sel_Mese, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}

	// Recupera i post con più/meno commenti e like
	private Object[][] Rec_Stat_Contenuti(String NU, int Mese) {

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

		try {

			for (int i = 0; i < Res_Gruppi.size(); i++) {

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

				System.out.println("Contenuto con più like: " + Res_Contenuti.get(indice_L_M).getId_Contenuto());
				System.out.println("Contenuto con meno like: " + Res_Contenuti.get(indice_L_m).getId_Contenuto());
				System.out.println("Contenuto con più commenti: " + Res_Contenuti.get(indice_C_M).getId_Contenuto());
				System.out.println("Contenuto con meno commenti: " + Res_Contenuti.get(indice_C_m).getId_Contenuto());

				Res[i] = new Object[] { Res_Gruppi.get(i).getNome(), Res_Contenuti.get(indice_L_M).getTesto(),
						Res_Contenuti.get(indice_L_m).getTesto(), Res_Contenuti.get(indice_C_M).getTesto(),
						Res_Contenuti.get(indice_C_m).getTesto() };
				// System.out.println(Res_Gruppi.get(i).getNome());
			}

			return Res;

		} catch (Exception e) {
			System.out.println("Non ci sono like e commenti nel gruppo " + e.getMessage());
			return Res;
		}
	}
}
