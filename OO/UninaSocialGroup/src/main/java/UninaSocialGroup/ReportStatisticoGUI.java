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
import java.awt.Toolkit;

public class ReportStatisticoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ReportStatisticoController reportStatisticoController = new ReportStatisticoController(ReportStatisticoGUI.this);
	private JTable tableStatContenuti;
	/**
	 * Create the frame.
	 */
	public ReportStatisticoGUI(String nomeUtente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReportStatisticoGUI.class.getResource("/UninaSocialGroup/image.png")));
		setTitle("Report Statistico ");

		String[] mesi = { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre",
				"Ottobre", "Novembre", "Dicembre" };

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		contentPane = new JPanel();
		contentPane.setBackground(PaletteColori.lightModeColorBG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLabel lblreport = new JLabel();
		lblreport.setForeground(PaletteColori.blueColor);
		lblreport.setText("Report Statistico");
		lblreport.setFont(new Font("Tahoma", Font.BOLD, 18));

		// BOTTONI
		JButton indietroButton = new JButton("◀️");
		indietroButton.setToolTipText("Torna alla home");
		indietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportStatisticoController.ActionIndietro(nomeUtente);
			}
		});
		indietroButton.setBackground(PaletteColori.lightModeColorBG);
		indietroButton.setForeground(PaletteColori.blueColor);
		indietroButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		indietroButton.setBorderPainted(false);

		// JCOMBOBOX
		// inserimento di tutti i mesi dell'anno
		JComboBox selMese = new JComboBox(mesi); // in questo caso posso mettere solo mesi
		selMese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportStatisticoController.ActionSelMese(tableStatContenuti, nomeUtente, selMese);
			}
		});
		selMese.setBackground(Color.LIGHT_GRAY);

		// JTABLE
		tableStatContenuti = new JTable();
		tableStatContenuti.setEnabled(false);
		tableStatContenuti.setModel(new DefaultTableModel(reportStatisticoController.Rec_Stat_Contenuti(nomeUtente, selMese.getSelectedIndex()),
				new String[] { "Gruppo", "Contenuto con più Like", "Contenuto con meno Like",
						"Contenuto con più Commenti", "Contenuto con meno Commenti" }));
		tableStatContenuti.setBounds(27, 119, 684, 315);
		JScrollPane scrollPane = new JScrollPane(tableStatContenuti);

		// Ridimensionamento pagina
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(indietroButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(188)
							.addComponent(lblreport, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(selMese, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(indietroButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblreport, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(selMese, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}

}
