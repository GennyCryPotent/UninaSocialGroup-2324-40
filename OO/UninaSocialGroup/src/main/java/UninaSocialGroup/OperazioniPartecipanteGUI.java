package UninaSocialGroup;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.BoxLayout;
import java.awt.Toolkit;

public class OperazioniPartecipanteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GruppiController gruppiController = new GruppiController(OperazioniPartecipanteGUI.this);
	String ruolo;
	
	public OperazioniPartecipanteGUI(String nomeUtente, String nomeGruppo, String Ruolo, int checkOperazione) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OperazioniPartecipanteGUI.class.getResource("/UninaSocialGroup/image.png")));
		setTitle("Operazioni partecipante");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JScrollPane scrollPartecipanti = new JScrollPane();
		scrollPartecipanti.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPartecipanti.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPartecipanti.setBounds(20, 50, 390, 190);
		contentPane.add(scrollPartecipanti, BorderLayout.WEST);

		JPanel parteciPanel = new JPanel();
		scrollPartecipanti.setViewportView(parteciPanel);
		scrollPartecipanti.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Popolazione dei bottoni dei gruppi
		creaBottoniPartecipanti(nomeUtente, parteciPanel, nomeGruppo, Ruolo, checkOperazione);
		parteciPanel.setLayout(new BoxLayout(parteciPanel, BoxLayout.Y_AXIS));

		JLabel lblElminazionePartecipante = new JLabel("Quale partecipante vuoi eliminare?");
		lblElminazionePartecipante.setForeground(PaletteColori.blueColor);
		lblElminazionePartecipante.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblElminazionePartecipante.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblElminazionePartecipante.setBackground(Color.WHITE);

		if (checkOperazione == 0) { // 0 elimina utente; 1 aggiungi amministratore
			lblElminazionePartecipante.setText("Quale partecipante vuoi eliminare?");
		} else {
			lblElminazionePartecipante.setText("Quale partecipante vuoi rendere amministratore?");
		}

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(lblElminazionePartecipante, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblElminazionePartecipante, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(211, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	// crea dinamicamente tutti i bottoni per eliminare i partecipanti
	private void creaBottoniPartecipanti(String nomeUtente, JPanel parteciPanel, String nomeGruppo, String ruolo, int checkOperazione) {
		PartecipanoDAO partecipano_DAO = new PartecipanoDAO();
		List<Partecipano> partecipano = partecipano_DAO.SelAllPartecipanoGruppo(nomeGruppo);
		GruppiDAO creatore = new GruppiDAO();

		String grouppCreator = creatore.SelSigGruppo(nomeGruppo).GetCreatore();

		for (Partecipano p : partecipano) {
			if (nomeUtente.compareTo(p.getNome_Partecipante()) != 0) {
				if (grouppCreator.compareTo(p.getNome_Partecipante()) == 0) {
					// NO BUTTON CREATORE
					System.out.print("creatore: " + grouppCreator);
				} else {
					JButton btnNewButton = new JButton(p.getNome_Partecipante());
					btnNewButton.addActionListener(e -> {

						if (checkOperazione == 0) { // 0 elimina utente; 1 aggiungi amministratore
							gruppiController.ActionRimuoviUtente(p, nomeGruppo);
						} else {
							gruppiController.ActionAggiungiAmministratore(p, nomeGruppo);
						}

					});

					btnNewButton.setForeground(PaletteColori.blueColor);
					btnNewButton.setBackground(PaletteColori.lightModeColorBG);
					btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);

					Border lineBorder = BorderFactory.createLineBorder(PaletteColori.blueColor);
					Border emptyBorder = new EmptyBorder(3, 2, 3, 2);
					Border compoundBorder = new CompoundBorder(emptyBorder, lineBorder);

					btnNewButton.setBorder(compoundBorder);
					parteciPanel.add(btnNewButton);

				}
			}
		}
	}
}