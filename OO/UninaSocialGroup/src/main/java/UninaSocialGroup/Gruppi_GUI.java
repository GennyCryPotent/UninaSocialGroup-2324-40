package UninaSocialGroup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Gruppi_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private String NewPost;
	
	private List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private Contenuti_DAO C = new Contenuti_DAO();
	private ArrayList<JPannelloContenuti> ContenutiPanel = new ArrayList<>(); 

	
	
	
	private Color lightColorBG = new Color(255, 255, 255);
	private Color lightColorButton = new Color(255, 255, 255);
	private Color lightColorFont = new Color(0, 0, 0);
	private Color lightColorInternalArea = new Color(244, 244, 244);

	private Color AcctualColorBG = lightColorBG;
	private Color AcctualColorButton = lightColorButton;
	private Color AcctualColorFont = lightColorFont;
	private Color AcctualtColorInternalArea = lightColorInternalArea;

	
	
	public Gruppi_GUI(String NU, String NG) {

		// PANNELLI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel NomeGruppo = new JLabel();
		NomeGruppo.setForeground(new Color(0, 128, 255));
		NomeGruppo.setText(NG);
		NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		NomeGruppo.setBounds(316, 29, 202, 38);
		contentPane.add(NomeGruppo);
		
		JPanel postsArea = new JPanel();
		postsArea.setBackground(new Color(244, 244, 244));
		postsArea.setBounds(140, 97, 573, 291);
		postsArea.setLayout(null);

		// BOTTONI
		JButton Home = new JButton("ðŸ� ");
		Home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Gruppi_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.AccessoHome(NU);

			}
		});
		Home.setForeground(new Color(0, 128, 255));
		Home.setFont(new Font("Dialog", Font.PLAIN, 18));
		Home.setBackground(Color.WHITE);
		Home.setBounds(27, 22, 60, 53);
		contentPane.add(Home);

		JButton AggiungiPost = new JButton("âž•");
		AggiungiPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				NewPost = JOptionPane.showInputDialog(AggiungiPost, "Cosa c'Ã¨ di nuovo?", "Aggiungi un post",
						JOptionPane.QUESTION_MESSAGE);
				C.InsContenuto(null, NewPost, NG, NU);
				
				Gruppi_GUI.this.setVisible(false);
				Gestione_Finestre GF = new Gestione_Finestre();
				GF.GruppiGUI(NU, NG);

			}

		});
		AggiungiPost.setForeground(new Color(0, 128, 255));
		AggiungiPost.setFont(new Font("Dialog", Font.PLAIN, 18));
		AggiungiPost.setBackground(Color.WHITE);
		AggiungiPost.setBounds(109, 22, 60, 53);
		contentPane.add(AggiungiPost);

		JButton Rimuovi_Post = new JButton("âž–");
		Rimuovi_Post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Gruppi_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.Elimina_Contenuto(NU, NG);

			}
		});
		Rimuovi_Post.setForeground(new Color(0, 128, 255));
		Rimuovi_Post.setFont(new Font("Dialog", Font.PLAIN, 18));
		Rimuovi_Post.setBackground(Color.WHITE);
		Rimuovi_Post.setBounds(190, 22, 60, 53);
		contentPane.add(Rimuovi_Post);

		//SCROLLPANE PER OSPITARE TUTTI I CONTENUTI
		JScrollPane scrollPane = new JScrollPane(postsArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setBounds(27, 97, 686, 291);
		scrollPane.setVisible(true);

		

		Res_Contenuti_Gruppi = C.SelAllContenutiGruppo(NG);

		Likes_DAO like_DAO = new Likes_DAO();
		Commenti_DAO commento_DAO = new Commenti_DAO();

		int numbOfTxt = Res_Contenuti_Gruppi.size();

		for (int i = 0; i < numbOfTxt; i++) {

			JPannelloContenuti NewPostPanel = new JPannelloContenuti(
					Res_Contenuti_Gruppi.get(i).getPubblicatore(), 
					NU,
					Res_Contenuti_Gruppi.get(i).getNome_Gruppo(), 
					Res_Contenuti_Gruppi.get(i).getTesto(),
					like_DAO.SelNumLike(Res_Contenuti_Gruppi.get(i).getId_Contenuto()),
					commento_DAO.SelNumCommenti(Res_Contenuti_Gruppi.get(i).getId_Contenuto()), 
					Res_Contenuti_Gruppi.get(i).getId_Contenuto());
			
			
			NewPostPanel.textArea.setWrapStyleWord(false);
			NewPostPanel.textArea.setEditable(false);
			
			ContenutiPanel.add(NewPostPanel);
			
			ContenutiPanel.get(i).setColors(AcctualtColorInternalArea, AcctualColorFont);
			
			postsArea.add(Box.createRigidArea(new Dimension(0, 10)));
			postsArea.add(ContenutiPanel.get(i));
		}

		contentPane.add(scrollPane);

		// ---------------

		// Aggiungi un listener per impostare le dimensioni del JScrollPane dopo che il
		// frame ÃƒÂ¨ visibile
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				int contentHeight = 0;
				// Posiziona correttamente le notifiche
				for (int i = 0; i < numbOfTxt; i++) {
					if (i == 0) {

						ContenutiPanel.get(i).setBounds(10, 10, (int) ContenutiPanel.get(i).getPreferredSize().width,
								ContenutiPanel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
					} else {
						System.out.println(ContenutiPanel.get(i).getBounds());
						ContenutiPanel.get(i).setBounds(10,
								(int) (ContenutiPanel.get(i - 1).getBounds().getY()
										+ ContenutiPanel.get(i - 1).getPreferredSize().getHeight() + 10),
								(int) ContenutiPanel.get(i).getPreferredSize().width,
								ContenutiPanel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
					}
					// crea la giusta dimensione per ContentPaneForContent che permette di fare lo
					// scrollbar delle giuste dimensioni
					contentHeight += (ContenutiPanel.get(i).getHeight() + 10);
				}

				// Aggiorna la dimensione preferita del contenitore in base all'effettiva
				// altezza di tutti gli elementi
				postsArea.setPreferredSize(new Dimension(scrollPane.getWidth() - 27, contentHeight));

				postsArea.revalidate();
				postsArea.repaint();

				scrollPane.revalidate();
				scrollPane.repaint();

				contentPane.revalidate();
				contentPane.repaint();
			}

		});

	}
}
