package UninaSocialGroup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class HomeGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private HomeController homeController = new HomeController(HomeGUI.this);

	private JPanel contentPane;
	private JPanel contentPaneNorth;
	private JPanel contentPaneNorthNorth;
	private JPanel contentPaneNorthWest;
	private JPanel contentPaneNorthCenter;

	private String nomeGruppo; // Nome Gruppo

	
	public HomeGUI(String nomeUtente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeGUI.class.getResource("/UninaSocialGroup/image.png")));
		
		setTitle("Home");
		setForeground(PaletteColori.blueColor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		
		// Configura la finestra per essere massimizzata all'avvio
	    setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(PaletteColori.lightModeColorBG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Creazione dei vari pannelli del layout
		contentPaneNorthNorth = new JPanel();
		contentPaneNorthWest = new JPanel();
		contentPaneNorthCenter = new JPanel();
		contentPaneNorth = new JPanel(new BorderLayout());
		contentPane.add(contentPaneNorth, BorderLayout.NORTH);

		// Impostazione dei layout per i pannelli del nord
		contentPaneNorthNorth.setLayout(new BoxLayout(contentPaneNorthNorth, BoxLayout.X_AXIS));
		contentPaneNorthWest.setLayout(new BoxLayout(contentPaneNorthWest, BoxLayout.X_AXIS));
		contentPaneNorthCenter.setLayout(new BoxLayout(contentPaneNorthCenter, BoxLayout.X_AXIS));

		contentPaneNorth.add(contentPaneNorthNorth, BorderLayout.NORTH);
		contentPaneNorth.add(contentPaneNorthWest, BorderLayout.WEST);
		contentPaneNorth.add(contentPaneNorthCenter, BorderLayout.CENTER);

		// Messaggio di benvenuto
		JLabel lblNewLabel = new JLabel("Bentornato");
		lblNewLabel.setForeground(PaletteColori.blueColor);
		lblNewLabel.setBackground(PaletteColori.lightModeColorBG);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPaneNorthNorth.add(lblNewLabel);

		JLabel lblnomeUtente = new JLabel(nomeUtente);
		lblnomeUtente.setForeground(PaletteColori.blueColor);
		lblnomeUtente.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneNorthNorth.add(lblnomeUtente);

		// ScrollPane per la visualizzazione dei gruppi
		JScrollPane gruppiGUIV = new JScrollPane();
		gruppiGUIV.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(gruppiGUIV, BorderLayout.WEST);

		JPanel groupPane = new JPanel();
		groupPane.setLayout(new BoxLayout(groupPane, BoxLayout.Y_AXIS));
		groupPane.setBackground(PaletteColori.lightModeColorInternalArea);
		gruppiGUIV.setViewportView(groupPane);

		// Popolazione dei bottoni dei gruppi
		creaBottoniGruppi(nomeUtente, groupPane);

		// Bottone notifiche
		JButton notifiche = new JButton("🔔");
		notifiche.setToolTipText("notifiche");
		notifiche.setContentAreaFilled(false);
		notifiche.setBorderPainted(false);
		notifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeController.ActionNotifiche(nomeUtente);
			}
		});
		notifiche.setBackground(PaletteColori.lightModeColorBG);
		notifiche.setForeground(PaletteColori.blueColor);
		notifiche.setFont(new Font(null, Font.PLAIN, 18));
		contentPaneNorthWest.add(notifiche, BorderLayout.WEST);

		// Bottone logout
		JButton btnLogout = new JButton("➡️🚪");
		btnLogout.setToolTipText("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeController.ActionLogout();
			}
		});
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBackground(PaletteColori.lightModeColorBG);
		btnLogout.setForeground(PaletteColori.blueColor);
		btnLogout.setFont(new Font(null, Font.PLAIN, 18));
		contentPaneNorth.add(btnLogout, BorderLayout.EAST);

		// Bottone report statistico
		JButton report = new JButton("📈");
		report.setToolTipText("report Statistico");
		report.setContentAreaFilled(false);
		report.setBorderPainted(false);
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeController.ActionReport(nomeUtente);
			}
		});
		report.setForeground(PaletteColori.blueColor);
		report.setFont(new Font("Dialog", Font.PLAIN, 18));
		report.setBackground(Color.WHITE);
		contentPaneNorthWest.add(report, BorderLayout.WEST);

		// Area dei post
		JPanel postsArea = new JPanel();
		postsArea.setBackground(PaletteColori.lightModeColorInternalArea);
		postsArea.setLayout(null);

		// Bottone ricerca gruppi
		JButton ricercaButton = new JButton("🔍");
		ricercaButton.setToolTipText("Ricerca gruppi");
		ricercaButton.setForeground(PaletteColori.blueColor);
		ricercaButton.setBackground(PaletteColori.lightModeColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeController.ActionRicerca(nomeUtente);
			}
		});
		ricercaButton.setFont(new Font(null, Font.PLAIN, 12));
		contentPaneNorthCenter.add(ricercaButton);

		// ScrollPane per l'area dei post
		JScrollPane postsScrollPane = new JScrollPane(postsArea);
		postsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		postsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		postsScrollPane.setVisible(true);

		contentPane.add(postsScrollPane, BorderLayout.CENTER);

		// Creazione dei post dinamici
		creaPost(nomeUtente, postsArea, postsScrollPane);

		// metodo per il layout e il ridimensionamento
		adjustPostLayout(postsArea, postsScrollPane);
	}

	// crea dinamicamente tutti i bottoni per accedere ai gruppi
	private void creaBottoniGruppi(String nomeUtente, JPanel groupPane) {
		PartecipanoDAO partecipano_DAO = new PartecipanoDAO();
		List<Partecipano> partecipano = partecipano_DAO.SelSigPartecipanoGruppo(nomeUtente);

		// Bottone per creare un nuovo gruppo
		JButton btnCreaG = new JButton("     ➕     ");
		btnCreaG.setToolTipText("Crea gruppo");
		btnCreaG.addActionListener(e -> {
			homeController.ActionCrea(nomeUtente);
		});

		modificaButtonGruppi(btnCreaG);

		groupPane.add(btnCreaG);

		// Creazione di un bottone per ogni gruppo a cui l'utente partecipa
		for (Partecipano p : partecipano) {

			JButton btnNewButton = new JButton(p.getNome_Gruppo());
			btnNewButton.addActionListener(e -> {
				nomeGruppo = btnNewButton.getText();
				homeController.ActionGruppi(nomeUtente, nomeGruppo);
			});

			modificaButtonGruppi(btnNewButton);

			groupPane.add(btnNewButton);

		}
	}

	// crea dinamicamente tutti i post da visualizzare
	private void creaPost(String nomeUtente, JPanel postsArea, JScrollPane postsScrollPane) {
		ContenutiDAO contenuto_DAO = new ContenutiDAO();
		LikesDAO likeDAO = new LikesDAO();
		CommentiDAO commento_DAO = new CommentiDAO();

		List<Contenuti> contenuti = contenuto_DAO.SelContenutiGruppiUtente(nomeUtente);
		ArrayList<JPannelloContenuti> testiLabel = new ArrayList<>();

		// Creazione dei pannelli di ogni post
		for (Contenuti contenuto : contenuti) {
			JPannelloContenuti postPanel = new JPannelloContenuti(contenuto.getPubblicatore(), nomeUtente,
					contenuto.getNomeGruppo(), contenuto.getTesto(), likeDAO.SelNumLike(contenuto.getIdContenuto()),
					commento_DAO.SelNumCommenti(contenuto.getIdContenuto()), contenuto.getIdContenuto());

			postPanel.textArea.setWrapStyleWord(false);
			postPanel.textArea.setEditable(false);
			testiLabel.add(postPanel);

			postsArea.add(Box.createRigidArea(new Dimension(0, 10)));
			postsArea.add(postPanel);
		}

		//metodo per il layout e il ridimensionamento dei post
		adjustPostLayout(postsArea, testiLabel, postsScrollPane);
	}

	// Metodo per gestire il layout dei post durante il ridimensionamento della finestra
	private void adjustPostLayout(JPanel postsArea, JScrollPane postsScrollPane) {

		contentPane.add(postsScrollPane, BorderLayout.CENTER);

		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				int contentHeight = 0;

				for (Component component : postsArea.getComponents()) {
					if (component instanceof JPannelloContenuti) {
						JPannelloContenuti panel = (JPannelloContenuti) component;
						panel.setBounds(5, contentHeight, postsScrollPane.getWidth() - 25,
								panel.getPreferredSize().height);
						contentHeight += panel.getHeight() + 10;
					}
				}

				postsArea.setPreferredSize(new Dimension(postsScrollPane.getWidth() - 25, contentHeight));
				postsArea.revalidate();
				postsArea.repaint();
			}
		});
	}

	// Metodo per gestire il layout dei post durante il ridimensionamento della finestra
	private void adjustPostLayout(JPanel postsArea, ArrayList<JPannelloContenuti> testiLabel,
			JScrollPane postsScrollPane) {
		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				int contentHeight = 0;

				for (int i = 0; i < testiLabel.size(); i++) {
					JPannelloContenuti postPanel = testiLabel.get(i);
					if (i == 0) {
						postPanel.setBounds(10, 10, (int) postPanel.getPreferredSize().width,
								postPanel.getPreferredSize().height);
					} else {
						postPanel.setBounds(10,
								(int) (testiLabel.get(i - 1).getBounds().getY()
										+ testiLabel.get(i - 1).getPreferredSize().getHeight() + 10),
								(int) postPanel.getPreferredSize().width, postPanel.getPreferredSize().height);
					}
					contentHeight += (postPanel.getHeight() + 10);
					postPanel.setColors(PaletteColori.lightModeColorInternalArea, PaletteColori.lightModeColorFont);
				}

				postsArea.setPreferredSize(new Dimension(postsScrollPane.getWidth() - 27, contentHeight));
				postsArea.revalidate();
				postsArea.repaint();
				postsScrollPane.revalidate();
				postsScrollPane.repaint();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
	}

	// Metodo per modificare l'aspetto dei bottoni dei gruppi
	void modificaButtonGruppi(JButton button) {
		button.setForeground(PaletteColori.blueColor);
		button.setBackground(PaletteColori.lightModeColorBG);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);

		Border lineBorder = BorderFactory.createLineBorder(PaletteColori.blueColor);
		Border emptyBorder = new EmptyBorder(3, 2, 3, 2);
		Border compoundBorder = new CompoundBorder(emptyBorder, lineBorder);

		button.setBorder(compoundBorder);

		// return button;

	}
}