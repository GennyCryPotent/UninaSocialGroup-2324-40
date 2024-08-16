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
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

public class Report_Statistico_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<Gruppi> Res_Gruppi = new ArrayList<Gruppi>();

	/**
	 * Create the frame.
	 */
	public Report_Statistico_GUI(String NU) {

		String[] mesi = { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
		Gruppi_DAO G = new Gruppi_DAO("system", "Database@03");

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
		Label.setBounds(210, 10, 202, 38);
		contentPane.add(Label);

		JButton Indietro = new JButton("<");
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report_Statistico_GUI.this.setVisible(false);
				Gestione_Finestre N = new Gestione_Finestre();
				N.AccessoHome(null);
			}
		});
		Indietro.setForeground(new Color(0, 128, 255));
		Indietro.setFont(new Font("Arial Black", Font.BOLD, 17));
		Indietro.setBackground(Color.WHITE);
		Indietro.setBounds(27, 22, 60, 53);
		contentPane.add(Indietro);
		contentPane.setLayout(null);
		contentPane.add(Label);
		contentPane.add(Indietro);

		JComboBox Sel_Gruppi = new JComboBox();
		Sel_Gruppi.setBackground(Color.LIGHT_GRAY);
		Sel_Gruppi.setBounds(27, 86, 155, 22);

		//inserimento di tutti i gruppi che ha creato l'utente
		Res_Gruppi = G.SelAllGruppoUtente(NU);
		for (int i = 0; i < Res_Gruppi.size(); i++) {
			Sel_Gruppi.addItem(Res_Gruppi.get(i).getNome());
		}
		contentPane.add(Sel_Gruppi);
		
		//inserimento di tutti i mesi dell'anno
		JComboBox Sel_Mese = new JComboBox();
		Sel_Mese.setBackground(Color.LIGHT_GRAY);
		Sel_Mese.setBounds(192, 86, 155, 22);
		
		for (int i = 0; i < mesi.length; i++) {
			Sel_Mese.addItem(mesi[i]);
		}
		contentPane.add(Sel_Mese);

	}
}
