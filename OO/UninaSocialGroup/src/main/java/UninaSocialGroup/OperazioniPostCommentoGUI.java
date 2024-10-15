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

public class OperazioniPostCommentoGUI extends JFrame {

	List<Contenuti> resContenutiGruppi = new ArrayList<Contenuti>();
	private List<Commenti> resCommenti = new ArrayList<Commenti>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GruppiGUI gruppiView;
	private OperazioniPostCommentoController operazioniPostCommentoController = new OperazioniPostCommentoController(
			OperazioniPostCommentoGUI.this); 

	// Schermata Post
	/**
	 * @wbp.parser.constructor
	 */
	public OperazioniPostCommentoGUI(String nomeUtente, String nomeGruppo, GruppiGUI gruppiGUI) {
		setTitle("Modifica/Elimina  post");
		setResizable(false);

		this.gruppiView = gruppiGUI;
		// PANELLI
		setBounds(100, 100, 556, 334);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelContenuti = new JPanel();
		panelContenuti.setLayout(new BoxLayout(panelContenuti, BoxLayout.Y_AXIS)); // Disposizione verticale

		AggiungiPost(nomeGruppo, nomeUtente, panelContenuti); // aggiunta post nel panel

		// Metti il pannello principale in uno JScrollPane
		JScrollPane scrollPane = new JScrollPane(panelContenuti, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 522, 190);
		contentPane.add(scrollPane);

		JButton buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.setForeground(new Color(0, 128, 255));
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operazioniPostCommentoController.ActionAnnulla(nomeUtente, nomeGruppo, 0, 0, 0, null);
				gruppiView.setVisible(false);
			}
		});
		buttonAnnulla.setActionCommand("Modifica");
		buttonAnnulla.setBounds(438, 11, 94, 40);
		contentPane.add(buttonAnnulla);

		// LABEL
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(PaletteColori.blueColor);
		lblNewLabel.setText("Scegli un contenuto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 186, 22);
		contentPane.add(lblNewLabel);

	}

	// -----------------------------------------
	// Schermata commento
	public OperazioniPostCommentoGUI(String nomeUtente, String nomeGruppo, int idContenuto, int checkSchermata, JFrame vecchiaSchermata) {
		setTitle("Modifica/Elimina commento");

		// PANELLI

		setBounds(100, 100, 556, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 

		JPanel panelCommenti = new JPanel();
		panelCommenti.setLayout(new BoxLayout(panelCommenti, BoxLayout.Y_AXIS)); // Disposizione verticale

		AggiungiCommenti(nomeGruppo, nomeUtente, panelCommenti, idContenuto, vecchiaSchermata); // aggiunta commenti nel panel

		// Metti il pannello principale in uno JScrollPane
		JScrollPane scrollPane = new JScrollPane(panelCommenti, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 522, 190);
		contentPane.add(scrollPane);

		JButton Button_Annulla = new JButton("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operazioniPostCommentoController.ActionAnnulla(nomeUtente, nomeGruppo, 1, idContenuto, checkSchermata, vecchiaSchermata); //con 1 passa alla schermata delle infoPost
			}
		});
		Button_Annulla.setActionCommand("Modifica");
		Button_Annulla.setBounds(438, 11, 94, 40);
		contentPane.add(Button_Annulla);

		// LABEL
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(PaletteColori.blueColor);
		lblNewLabel.setText("Scegli un commento");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 186, 22);
		contentPane.add(lblNewLabel);

	}

	// Aggiunge i post nel JPanel insieme ai bottoni
	private void AggiungiPost(String nomeGruppo, String nomeUtente, JPanel panelContenuti) {

		ContenutiDAO contenutiDAO = new ContenutiDAO();
		resContenutiGruppi = contenutiDAO.SelContenutiUtenteGruppo(nomeGruppo, nomeUtente);

		for (int i = 0; i < resContenutiGruppi.size(); i++) {

			final int IdexPost = i;
			JPanel postPanel = new JPanel(new BorderLayout()); // Layout principale per allineare i bottoni a destra

			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(i + "): " + resContenutiGruppi.get(i).getTesto()); // Testo del post
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);

			textArea.setBorder(BorderFactory.createEmptyBorder());

			JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Allinea i bottoni a destra

			JButton Modifica = new JButton("Modifica");
			Modifica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operazioniPostCommentoController.ActionModificaPost(Modifica, nomeUtente, nomeGruppo, resContenutiGruppi.get(IdexPost).getIdContenuto(), gruppiView);
					gruppiView.setVisible(false);
				}
			});
			JButton Elimina = new JButton("Elimina");

			Elimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operazioniPostCommentoController.ActionEliminaPost(Elimina, nomeUtente, nomeGruppo, resContenutiGruppi.get(IdexPost).getIdContenuto());
					gruppiView.setVisible(false);
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
	private void AggiungiCommenti(String nomeGruppo, String nomeUtente, JPanel panelCommenti, int idContenuto, JFrame vecchiaSchermata) {

		CommentiDAO commentiDAO = new CommentiDAO();
		resCommenti = commentiDAO.SelCommentiUtentePost(nomeUtente, idContenuto);

		for (int i = 0; i < resCommenti.size(); i++) {

			final int indexCommenti = i;
			JPanel postPanel = new JPanel(new BorderLayout()); // Layout principale per allineare i bottoni a destra

			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(i + "): " + resCommenti.get(i).getTesto()); // Testo del commento
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);

			textArea.setBorder(BorderFactory.createEmptyBorder());

			JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Allinea i bottoni a destra

			JButton modificaButton = new JButton("Modifica");
			
			modificaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operazioniPostCommentoController.ActionModificaCommento(modificaButton, nomeUtente, nomeGruppo, idContenuto,
							resCommenti.get(indexCommenti).getId_Commento(), vecchiaSchermata);
				}
			});
			JButton eliminaButton = new JButton("Elimina");
			

			eliminaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operazioniPostCommentoController.ActionEliminaCommento(eliminaButton, nomeUtente, nomeGruppo, idContenuto,
							resCommenti.get(indexCommenti).getId_Commento(), vecchiaSchermata);
				}
			});
			buttonsPanel.add(modificaButton);
			buttonsPanel.add(eliminaButton);

			postPanel.add(textArea, BorderLayout.CENTER);
			postPanel.add(buttonsPanel, BorderLayout.EAST);

			panelCommenti.add(postPanel);

			panelCommenti.add(Box.createRigidArea(new Dimension(0, 3)));
		}

	}
}
