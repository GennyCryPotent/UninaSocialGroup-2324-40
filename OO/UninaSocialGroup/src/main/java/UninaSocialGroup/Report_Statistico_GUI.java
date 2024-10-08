package UninaSocialGroup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Report_Statistico_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ReportStatisticoController RSC = new ReportStatisticoController(Report_Statistico_GUI.this);
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
		Indietro.setToolTipText("Torna alla home");
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RSC.ActionIndietro(NU);
			}
		});
		Indietro.setForeground(new Color(0, 128, 255));
		Indietro.setFont(new Font("Dialog", Font.PLAIN, 18));
		Indietro.setBackground(Color.WHITE);
		Indietro.setContentAreaFilled(false);
		Indietro.setBorderPainted(false);
		Indietro.setBackground(Color.WHITE);

		// JCOMBOBOX
		// inserimento di tutti i mesi dell'anno
		JComboBox Sel_Mese = new JComboBox(mesi); // in questo caso posso mettere solo mesi
		Sel_Mese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RSC.ActionSelMese(tableStatContenuti, NU, Sel_Mese);
			}
		});
		Sel_Mese.setBackground(Color.LIGHT_GRAY);

		// JTABLE
		tableStatContenuti = new JTable();
		tableStatContenuti.setModel(new DefaultTableModel(RSC.Rec_Stat_Contenuti(NU, Sel_Mese.getSelectedIndex()),
				new String[] { "Gruppo", "Contenuto con più Like", "Contenuto con meno Like",
						"Contenuto con più Commenti", "Contenuto con meno Commenti" }));
		tableStatContenuti.setBounds(27, 119, 684, 315);
		JScrollPane scrollPane = new JScrollPane(tableStatContenuti);

		// Ridimensionamento pagina
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(22)
						.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(250)
						.addComponent(Label, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(22)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(Sel_Mese, GroupLayout.PREFERRED_SIZE, 155,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(5)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(Label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGap(16).addComponent(Sel_Mese, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

	}

	
}
