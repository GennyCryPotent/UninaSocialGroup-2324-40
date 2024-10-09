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

public class Home_GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private HomeController HC = new HomeController(Home_GUI.this);

	private JPanel contentPane;
	private JPanel contentPaneNorth;
	private JPanel contentPaneNorthNorth;
	private JPanel contentPaneNorthWest;
	private JPanel contentPaneNorthCenter;

	private String NG; // Nome Gruppo

	private Color lightModeColorBG = new Color(255, 255, 255);
	private Color lightModeColorFont = new Color(0, 0, 0);
	private Color lightModeColorInternalArea = new Color(244, 244, 244);
	private Color blueColorFont = new Color(0, 128, 255);

	public Home_GUI(String NU) {
		setTitle("Home");
		setForeground(blueColorFont);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setForeground(new Color(31, 31, 31));
		contentPane.setBackground(lightModeColorBG);
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
		lblNewLabel.setForeground(blueColorFont);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPaneNorthNorth.add(lblNewLabel);

		JLabel NomeUtente = new JLabel(NU);
		NomeUtente.setForeground(blueColorFont);
		NomeUtente.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneNorthNorth.add(NomeUtente);

		JScrollPane GruppiGUIV = new JScrollPane();
		GruppiGUIV.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(GruppiGUIV, BorderLayout.WEST);

		JPanel GroupPane = new JPanel();
		GroupPane.setLayout(new BoxLayout(GroupPane, BoxLayout.Y_AXIS));
		GroupPane.setBackground(lightModeColorInternalArea);
		GruppiGUIV.setViewportView(GroupPane);

		// Popolazione dei bottoni dei gruppi
		creaBottoniGruppi(NU, GroupPane);

		JButton Notifiche = new JButton("🔔");
		Notifiche.setToolTipText("Notifiche");
		Notifiche.setContentAreaFilled(false);
		Notifiche.setBorderPainted(false);
		Notifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HC.ActionNotifiche(NU);
			}
		});
		Notifiche.setBackground(new Color(255, 255, 255));
		Notifiche.setForeground(blueColorFont);
		Notifiche.setFont(new Font(null, Font.PLAIN, 18));
		// Notifiche.setBounds(27, 22, 60, 53);
		contentPaneNorthWest.add(Notifiche, BorderLayout.WEST);

		JButton btnLogout = new JButton("➡️🚪");
		btnLogout.setToolTipText("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HC.ActionLogout();
			}
		});
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.setForeground(blueColorFont);
		btnLogout.setFont(new Font(null, Font.PLAIN, 18));
		contentPaneNorth.add(btnLogout, BorderLayout.EAST);

		JButton Report = new JButton("📈");
		Report.setToolTipText("Report Statistico");
		Report.setContentAreaFilled(false);
		Report.setBorderPainted(false);
		Report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HC.ActionReport(NU);
			}
		});
		Report.setForeground(blueColorFont);
		Report.setFont(new Font("Dialog", Font.PLAIN, 18));
		Report.setBackground(Color.WHITE);
		// Report.setBounds(109, 22, 60, 53);
		contentPaneNorthWest.add(Report, BorderLayout.WEST);

		JPanel postsArea = new JPanel();
		postsArea.setBackground(lightModeColorInternalArea);
		postsArea.setLayout(null);

		JButton ricercaButton = new JButton("🔍");
		ricercaButton.setToolTipText("Ricerca gruppi");
		ricercaButton.setForeground(blueColorFont);
		ricercaButton.setBackground(lightModeColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HC.ActionRicerca(NU);
			}
		});
		ricercaButton.setFont(new Font(null, Font.PLAIN, 12));
		contentPaneNorthCenter.add(ricercaButton);

		JScrollPane PostsScrollPane = new JScrollPane(postsArea);
		PostsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		PostsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		PostsScrollPane.setVisible(true);

		contentPane.add(PostsScrollPane, BorderLayout.CENTER);

		// Creazione dei post dinamici
		creaPost(NU, postsArea, PostsScrollPane);

		// Listener per il ridimensionamento
		adjustPostLayout(postsArea, PostsScrollPane);
	}

	// crea dinamicamente tutti i bottoni per accedere ai gruppi
	private void creaBottoniGruppi(String NU, JPanel GroupPane) {
		Partecipano_DAO partecipano_DAO = new Partecipano_DAO();
		List<Partecipano> partecipano = partecipano_DAO.SelSigPartecipanoGruppo(NU);

		JButton btnCreaG = new JButton("     ➕     ");
		btnCreaG.setToolTipText("Crea gruppo");
		btnCreaG.addActionListener(e -> {

			HC.ActionCrea(NU);

		});

		modificaButtonGruppi(btnCreaG);

		GroupPane.add(btnCreaG);

		for (Partecipano p : partecipano) {

			JButton btnNewButton = new JButton(p.getNome_Gruppo());
			btnNewButton.addActionListener(e -> {
				NG = btnNewButton.getText();
				HC.ActionGruppi(NU, NG);
			});

			modificaButtonGruppi(btnNewButton);

			GroupPane.add(btnNewButton);

		}
	}

	// crea dinamicamnete tutti i post da visualizzare
	private void creaPost(String NU, JPanel postsArea, JScrollPane PostsScrollPane) {
		Contenuti_DAO contenuto_DAO = new Contenuti_DAO();
		Likes_DAO like_DAO = new Likes_DAO();
		Commenti_DAO commento_DAO = new Commenti_DAO();

		List<Contenuti> contenuti = contenuto_DAO.SelContenutiGruppiUtente(NU);
		ArrayList<JPannelloContenuti> TestiLabel = new ArrayList<>();

		for (Contenuti contenuto : contenuti) {
			JPannelloContenuti postPanel = new JPannelloContenuti(contenuto.getPubblicatore(), NU,
					contenuto.getNome_Gruppo(), contenuto.getTesto(), like_DAO.SelNumLike(contenuto.getId_Contenuto()),
					commento_DAO.SelNumCommenti(contenuto.getId_Contenuto()), contenuto.getId_Contenuto());

			postPanel.textArea.setWrapStyleWord(false);
			postPanel.textArea.setEditable(false);
			TestiLabel.add(postPanel);

			postsArea.add(Box.createRigidArea(new Dimension(0, 10)));
			postsArea.add(postPanel);
		}

		adjustPostLayout(postsArea, TestiLabel, PostsScrollPane);
	}

	private void adjustPostLayout(JPanel postsArea, JScrollPane PostsScrollPane) {

		contentPane.add(PostsScrollPane, BorderLayout.CENTER);

		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				int contentHeight = 0;

				for (Component component : postsArea.getComponents()) {
					if (component instanceof JPannelloContenuti) {
						JPannelloContenuti panel = (JPannelloContenuti) component;
						panel.setBounds(5, contentHeight, PostsScrollPane.getWidth() - 25,
								panel.getPreferredSize().height);
						contentHeight += panel.getHeight() + 10;
					}
				}

				postsArea.setPreferredSize(new Dimension(PostsScrollPane.getWidth() - 25, contentHeight));
				postsArea.revalidate();
				postsArea.repaint();
			}
		});
	}

	private void adjustPostLayout(JPanel postsArea, ArrayList<JPannelloContenuti> TestiLabel,
			JScrollPane PostsScrollPane) {
		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				int contentHeight = 0;

				for (int i = 0; i < TestiLabel.size(); i++) {
					JPannelloContenuti postPanel = TestiLabel.get(i);
					if (i == 0) {
						postPanel.setBounds(10, 10, (int) postPanel.getPreferredSize().width,
								postPanel.getPreferredSize().height);
					} else {
						postPanel.setBounds(10,
								(int) (TestiLabel.get(i - 1).getBounds().getY()
										+ TestiLabel.get(i - 1).getPreferredSize().getHeight() + 10),
								(int) postPanel.getPreferredSize().width, postPanel.getPreferredSize().height);
					}
					contentHeight += (postPanel.getHeight() + 10);
					postPanel.setColors(lightModeColorInternalArea, lightModeColorFont);
				}

				postsArea.setPreferredSize(new Dimension(PostsScrollPane.getWidth() - 27, contentHeight));
				postsArea.revalidate();
				postsArea.repaint();
				PostsScrollPane.revalidate();
				PostsScrollPane.repaint();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
	}

	void modificaButtonGruppi(JButton button) {
		button.setForeground(blueColorFont);
		button.setBackground(new Color(255, 255, 255));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);

		Border lineBorder = BorderFactory.createLineBorder(blueColorFont);
		Border emptyBorder = new EmptyBorder(3, 2, 3, 2);
		Border compoundBorder = new CompoundBorder(emptyBorder, lineBorder);

		button.setBorder(compoundBorder);

		// return button;

	}
}
