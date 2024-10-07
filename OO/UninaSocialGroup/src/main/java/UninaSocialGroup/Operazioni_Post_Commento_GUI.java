package UninaSocialGroup;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Operazioni_Post_Commento_GUI extends JFrame {

	List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gruppi_GUI gView;
	private OperazioniPostCommentoController OPC = new OperazioniPostCommentoController(
			Operazioni_Post_Commento_GUI.this);

	// Schermata Post
	/**
	 * @wbp.parser.constructor
	 */
	public Operazioni_Post_Commento_GUI(String NU, String NG, Gruppi_GUI gruppiView) {

		gView = gruppiView;
		// PANELLI
		setBounds(100, 100, 556, 334);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelContenuti = new JPanel();
		panelContenuti.setLayout(new BoxLayout(panelContenuti, BoxLayout.Y_AXIS)); // Disposizione verticale

		AggiungiPost(NG, NU, panelContenuti); // aggiunta post nel panel

		// Metti il pannello principale in uno JScrollPane
		JScrollPane scrollPane = new JScrollPane(panelContenuti, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 522, 190);
		contentPane.add(scrollPane);

		JButton Button_Annulla = new JButton("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionAnnulla(NU, NG);
				gView.setVisible(false);
			}
		});
		Button_Annulla.setActionCommand("Modifica");
		Button_Annulla.setBounds(438, 11, 94, 40);
		contentPane.add(Button_Annulla);

		// LABEL
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setText("Scegli un contenuto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 186, 22);
		contentPane.add(lblNewLabel);

	}

	// -----------------------------------------
	// Schermata commento
	public Operazioni_Post_Commento_GUI(String NU, String NG, int Id_Contenuto) {
		setTitle("Modifica/Elimina commento");

		// PANELLI

		setBounds(100, 100, 556, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCommenti = new JPanel();
		panelCommenti.setLayout(new BoxLayout(panelCommenti, BoxLayout.Y_AXIS)); // Disposizione verticale

		AggiungiCommenti(NG, NU, panelCommenti, Id_Contenuto); // aggiunta commenti nel panel

		// Metti il pannello principale in uno JScrollPane
		JScrollPane scrollPane = new JScrollPane(panelCommenti, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 522, 190);
		contentPane.add(scrollPane);

		JButton Button_Annulla = new JButton("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionAnnulla(NU, NG);
			}
		});
		Button_Annulla.setActionCommand("Modifica");
		Button_Annulla.setBounds(438, 11, 94, 40);
		contentPane.add(Button_Annulla);

		// LABEL
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setText("Scegli un commento");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 186, 22);
		contentPane.add(lblNewLabel);

	}

	// Aggiunge i post nel JPanel insieme ai bottoni
	private void AggiungiPost(String NG, String NU, JPanel panelContenuti) {

		Contenuti_DAO C = new Contenuti_DAO();
		Res_Contenuti_Gruppi = C.SelContenutiUtenteGruppo(NG, NU);

		for (int i = 0; i < Res_Contenuti_Gruppi.size(); i++) {

			final int IdexPost = i;
			JPanel postPanel = new JPanel(new BorderLayout()); // Layout principale per allineare i bottoni a destra

			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(i + "): " + Res_Contenuti_Gruppi.get(i).getTesto()); // Testo del post
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);

			textArea.setBorder(BorderFactory.createEmptyBorder());

			JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Allinea i bottoni a destra

			JButton Modifica = new JButton("Modifica");
			Modifica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OPC.ActionModificaPost(Modifica, NU, NG, Res_Contenuti_Gruppi.get(IdexPost).getId_Contenuto());
					gView.setVisible(false);
				}
			});
			JButton Elimina = new JButton("Elimina");

			Elimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OPC.ActionEliminaPost(Elimina, NU, NG, Res_Contenuti_Gruppi.get(IdexPost).getId_Contenuto());
					gView.setVisible(false);
				}
			});
			buttonsPanel.add(Modifica);
			buttonsPanel.add(Elimina);

			postPanel.add(textArea, BorderLayout.CENTER); // Aggiungi il testo al centro
			postPanel.add(buttonsPanel, BorderLayout.EAST); // Aggiungi i bottoni a destra

			panelContenuti.add(postPanel);

			panelContenuti.add(Box.createRigidArea(new Dimension(0, 3)));
		}

	}

	// Aggiunge i post nel JPanel insieme ai bottoni
	private void AggiungiCommenti(String NG, String NU, JPanel panelCommenti, int IdContenuto) {

		Commenti_DAO C = new Commenti_DAO();
		Res_Commenti = C.SelCommentiUtentePost(NU, IdContenuto);

		for (int i = 0; i < Res_Commenti.size(); i++) {

			final int IndexCommenti = i;
			JPanel postPanel = new JPanel(new BorderLayout()); // Layout principale per allineare i bottoni a destra

			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(i + "): " + Res_Commenti.get(i).getTesto()); // Testo del commento
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);

			textArea.setBorder(BorderFactory.createEmptyBorder());

			JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Allinea i bottoni a destra

			JButton Modifica = new JButton("Modifica");
			Modifica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OPC.ActionModificaCommento(Modifica, NU, NG, IdContenuto,
							Res_Commenti.get(IndexCommenti).getId_Commento());
				}
			});
			JButton Elimina = new JButton("Elimina");

			Elimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OPC.ActionEliminaCommento(Elimina, NU, NG, IdContenuto,
							Res_Commenti.get(IndexCommenti).getId_Commento());
				}
			});
			buttonsPanel.add(Modifica);
			buttonsPanel.add(Elimina);

			postPanel.add(textArea, BorderLayout.CENTER);
			postPanel.add(buttonsPanel, BorderLayout.EAST);

			panelCommenti.add(postPanel);

			panelCommenti.add(Box.createRigidArea(new Dimension(0, 3)));
		}

	}
}
