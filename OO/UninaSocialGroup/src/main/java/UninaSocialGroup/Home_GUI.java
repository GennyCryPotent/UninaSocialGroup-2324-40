package UninaSocialGroup;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Panel;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

public class Home_GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel contentPaneNorth;
	private JPanel contentPaneNorthNorth;
	private JPanel contentPaneNorthWest;
	private JPanel contentPaneNorthCenter;

	private String NU; // Nome Utente
	private String NG; // Nome Gruppo
	private String trova; // cio che si trova nella textfield della ricerca
	private boolean darkMode = false;
	private JScrollPane PostsScrollPane;

	private Color darkColorBG = new Color(27, 27, 27);
	private Color darkColorButton = new Color(15, 15, 15);
	private Color darkColorFont = new Color(255, 255, 255);
	private Color darkColorInternalArea = new Color(63, 63, 63);

	private Color lightColorBG = new Color(255, 255, 255);
	private Color lightColorButton = new Color(255, 255, 255);
	private Color lightColorFont = new Color(0, 0, 0);
	private Color lightColorInternalArea = new Color(244, 244, 244);

	private Color AcctualColorBG = lightColorBG;
	private Color AcctualColorButton = lightColorButton;
	private Color AcctualColorFont = lightColorFont;
	private Color AcctualtColorInternalArea = lightColorInternalArea;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */

	public Home_GUI(String NU) {
		setTitle("Home");
		setForeground(new Color(0, 128, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setForeground(new Color(31, 31, 31));
		contentPane.setBackground(AcctualColorBG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPaneNorthNorth = new JPanel();
		contentPaneNorthWest = new JPanel();
		contentPaneNorthCenter = new JPanel();
		contentPaneNorth = new JPanel(new BorderLayout());
		contentPane.add(contentPaneNorth, BorderLayout.NORTH);

		contentPaneNorthNorth.setLayout(new BoxLayout(contentPaneNorthNorth, BoxLayout.X_AXIS));
		contentPaneNorthWest.setLayout(new BoxLayout(contentPaneNorthWest, BoxLayout.X_AXIS));
		contentPaneNorthCenter.setLayout(new BoxLayout(contentPaneNorthCenter, BoxLayout.X_AXIS));

		contentPaneNorth.add(contentPaneNorthNorth, BorderLayout.NORTH);
		contentPaneNorth.add(contentPaneNorthWest, BorderLayout.WEST);
		contentPaneNorth.add(contentPaneNorthCenter, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Bentornato");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPaneNorthNorth.add(lblNewLabel);

		JLabel NomeUtente = new JLabel(NU);
		NomeUtente.setForeground(new Color(0, 128, 255));
		NomeUtente.setFont(new Font("Tahoma", Font.BOLD, 18));
		// NomeUtente.setBounds(319, 10, 260, 38);
		contentPaneNorthNorth.add(NomeUtente);

		JButton ricercaButton = new JButton("🔍");
		ricercaButton.setForeground(new Color(0, 128, 255));
		ricercaButton.setBackground(AcctualColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(trova);

			}
		});
		ricercaButton.setFont(new Font(null, Font.PLAIN, 12));
		// ricerca.setBounds(440, 54, 46, 21);

		JScrollPane GruppiGUIV = new JScrollPane();
		GruppiGUIV.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// GruppiGUIV.setBounds(10, 97, 110, 291);
		contentPane.add(GruppiGUIV, BorderLayout.WEST);

		JPanel GroupPane = new JPanel();
		GroupPane.setLayout(new BoxLayout(GroupPane, BoxLayout.Y_AXIS));
		GroupPane.setBackground(new Color(255, 255, 255));
		GruppiGUIV.setViewportView(GroupPane);

		List<Partecipano> partecipano = new ArrayList<>();

		List<JButton> GroupButtons = new ArrayList<>();

		Partecipano_DAO partecipano_DAO = new Partecipano_DAO();

		partecipano = partecipano_DAO.SelSigPartecipanoGruppo(NU);

		int numOfGroups = partecipano.size();

		for (int i = 0; i < numOfGroups; i++) {
			JButton btnNewButton = new JButton(partecipano.get(i).getNome_Gruppo());
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Home_GUI.this.setVisible(false);
					Gestione_Finestre G = new Gestione_Finestre();
					NG = btnNewButton.getText();
					G.GruppiGUI(NU, NG);
				}
			});
			btnNewButton.setForeground(new Color(0, 128, 255));
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			// btnNewButton.setBorder(new EmptyBorder(20,0,20,0));

			// Crea il bordo con la linea colorata
			Border lineBorder = BorderFactory.createLineBorder(new Color(0, 128, 255));

			// Crea il bordo vuoto (padding)
			Border emptyBorder = new EmptyBorder(3, 2, 3, 2);

			// Combina i due bordi in un CompoundBorder
			Border compoundBorder = new CompoundBorder(emptyBorder, lineBorder);

			// Applica il CompoundBorder al pulsante
			btnNewButton.setBorder(compoundBorder);
			// btnNewButton.setBorder(BorderFactory.createLineBorder(new Color(0, 128,
			// 255)));
			// btnNewButton.setBorder(new EmptyBorder(20,0,20,0));
			GroupButtons.add(btnNewButton);
			GroupPane.add(GroupButtons.get(i));
		}

		/*
		 * JButton btnNewButton = new JButton("Fantacalcio");
		 * btnNewButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Home_GUI.this.setVisible(false);
		 * Gestione_Finestre G = new Gestione_Finestre(); NG = btnNewButton.getText();
		 * G.GruppiGUI(NU, NG); } });
		 * 
		 * //btnNewButton.setBounds(10, 10, 85, 21); //GroupPane.add(btnNewButton);
		 */

		JButton Notifiche = new JButton("🔔");
		Notifiche.setContentAreaFilled(false);
		Notifiche.setBorderPainted(false);

		Notifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.Notifiche(NU);
			}
		});
		Notifiche.setBackground(new Color(255, 255, 255));
		Notifiche.setForeground(new Color(0, 128, 255));
		Notifiche.setFont(new Font(null, Font.PLAIN, 18));
		// Notifiche.setBounds(27, 22, 60, 53);
		contentPaneNorthWest.add(Notifiche, BorderLayout.WEST);

		JButton Report = new JButton("😍");
		Report.setContentAreaFilled(false);
		Report.setBorderPainted(false);

		Report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.Report_S(NU);
			}
		});
		Report.setForeground(new Color(0, 128, 255));
		Report.setFont(new Font("Dialog", Font.PLAIN, 18));
		Report.setBackground(Color.WHITE);
		// Report.setBounds(109, 22, 60, 53);
		contentPaneNorthWest.add(Report, BorderLayout.WEST);

		JPanel postsArea = new JPanel();
		postsArea.setBackground(AcctualtColorInternalArea);
		// postsArea.setBounds(140, 97, 573, 291);
		postsArea.setLayout(null);

		JButton Scuro = new JButton("🌙");
		Scuro.setContentAreaFilled(false);
		Scuro.setBorderPainted(false);

		Scuro.setForeground(new Color(0, 128, 255));
		Scuro.setFont(new Font("Dialog", Font.PLAIN, 18));
		Scuro.setBackground(Color.WHITE);
		// Scuro.setBounds(604, 22, 120, 53);
		contentPaneNorth.add(Scuro, BorderLayout.EAST);

		String[] GruppiRicerca = { "Fantacalcio", "SSC_Napoli_Ultras", "Dungeons N Dragons", "Amanti del Cinema",
				"Fotografia per Passione", "Viaggiatori del Mondo", "Cucinare con Amore", "Appassionati di Tecnologia",
				"Libri e Letteratura", "Amici degli Animali", "Fitness e Benessere", "Musica per tutti" };

		JComboBox Ricerca = new JComboBox(GruppiRicerca);
		Ricerca.setEditable(true);
		// Ricerca.setBounds(210, 54, 230, 21);
		Ricerca.setBorder(new EmptyBorder(10, 10, 10, 0));
		contentPaneNorthCenter.add(Ricerca);
		contentPaneNorthCenter.add(ricercaButton);

		trova = (String) Ricerca.getSelectedItem();

		// ----------

		JScrollPane PostsScrollPane = new JScrollPane(postsArea);
		PostsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		PostsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// PostsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		// PostsScrollPane.setBounds(140, 97, 573, 291);
		PostsScrollPane.setVisible(true);

		ArrayList<JPannelloContenuti> TestiLabel = new ArrayList<>();

		List<Contenuti> contenuti = new ArrayList<>();

		Contenuti_DAO contenuto_DAO = new Contenuti_DAO();

		contenuti = contenuto_DAO.SelContenutiGruppiUtente(NU);

		Likes_DAO like_DAO = new Likes_DAO();
		Commenti_DAO commento_DAO = new Commenti_DAO();

		int numbOfPosts = contenuti.size();

		for (int i = 0; i < numbOfPosts; i++) {

			JPannelloContenuti postPanel = new JPannelloContenuti(contenuti.get(i).getPubblicatore(), 
					NU,
					contenuti.get(i).getNome_Gruppo(), 
					contenuti.get(i).getTesto(),
					like_DAO.SelNumLike(contenuti.get(i).getId_Contenuto()),
					commento_DAO.SelNumCommenti(contenuti.get(i).getId_Contenuto()),
					contenuti.get(i).getId_Contenuto());

			postPanel.textArea.setWrapStyleWord(false);
			postPanel.textArea.setEditable(false);
			TestiLabel.add(postPanel);

			postsArea.add(Box.createRigidArea(new Dimension(0, 10)));
			postsArea.add(TestiLabel.get(i));
		}

		PostsScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

		contentPane.add(PostsScrollPane, BorderLayout.CENTER);

		// ---------------

		// Aggiungi un listener per impostare le dimensioni del JScrollPane dopo che il
		// frame Ã¨ visibile
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				int contentHeight = 0;
				// Posiziona correttamente le notifiche
				for (int i = 0; i < numbOfPosts; i++) {
					if (i == 0) {

						TestiLabel.get(i).setBounds(10, 10, (int) TestiLabel.get(i).getPreferredSize().width,
								TestiLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
					} else {
						System.out.println(TestiLabel.get(i).getBounds());
						TestiLabel.get(i).setBounds(10,
								(int) (TestiLabel.get(i - 1).getBounds().getY()
										+ TestiLabel.get(i - 1).getPreferredSize().getHeight() + 10),
								(int) TestiLabel.get(i).getPreferredSize().width,
								TestiLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate

					}
					// crea la giusta dimensione per ContentPaneForContent che permette di fare lo
					// scrollbar delle giuste dimensioni
					contentHeight += (TestiLabel.get(i).getHeight() + 10);

					// Colori
					TestiLabel.get(i).setColors(AcctualtColorInternalArea, AcctualColorFont);
				}

				// Aggiorna la dimensione preferita del contenitore in base all'effettiva
				// altezza di tutti gli elementi
				postsArea.setPreferredSize(new Dimension(PostsScrollPane.getWidth() - 27, contentHeight));

				postsArea.revalidate();
				postsArea.repaint();

				PostsScrollPane.revalidate();
				PostsScrollPane.repaint();

				contentPane.revalidate();
				contentPane.repaint();
			}

		});

		// -----

		Scuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!darkMode) {
					AcctualColorBG = darkColorBG;
					AcctualColorButton = darkColorButton;
					AcctualColorFont = darkColorFont;
					AcctualtColorInternalArea = darkColorInternalArea;

					PostsScrollPane.setBorder(BorderFactory.createLineBorder(Color.white));

					Scuro.setText("🔆");
					darkMode = true;

				} else {
					AcctualColorBG = lightColorBG;
					AcctualColorButton = lightColorButton;
					AcctualColorFont = lightColorFont;
					AcctualtColorInternalArea = lightColorInternalArea;

					PostsScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

					Scuro.setText("🌙");
					darkMode = false;
				}

				// contentPane.setBackground(new Color(27,27,27));
				contentPane.setBackground(AcctualColorBG);// pannello

				contentPaneNorthNorth.setBackground(AcctualColorBG);
				contentPaneNorthWest.setBackground(AcctualColorBG);
				contentPaneNorthCenter.setBackground(AcctualColorBG);
				contentPaneNorth.setBackground(AcctualColorBG);

				Scuro.setBackground(AcctualColorButton); // pulsante scuro
				ricercaButton.setBackground(AcctualColorButton); // ricerca
				Report.setBackground(AcctualColorButton);
				Notifiche.setBackground(AcctualColorButton);

				GruppiGUIV.setBackground(AcctualColorBG);
				GruppiGUIV.setForeground(AcctualColorBG);

				// GroupPane.setForeground(AcctualColorFont);
				GroupPane.setBackground(AcctualtColorInternalArea);

				// btnNewButton.setBackground(AcctualColorButton);

				PostsScrollPane.setBackground(AcctualColorButton);

				postsArea.setBackground(AcctualtColorInternalArea);
				postsArea.setForeground(AcctualColorFont);

				Ricerca.setForeground(AcctualColorFont);
				Ricerca.setBackground(AcctualColorBG);

				for (int i = 0; i < numbOfPosts; i++) {
					TestiLabel.get(i).setColors(AcctualtColorInternalArea, AcctualColorFont);
				}
				
				for (int i = 0; i < numOfGroups; i++) {
					GroupButtons.get(i).setBackground(AcctualtColorInternalArea);
				}
			}
		});

	}

}
