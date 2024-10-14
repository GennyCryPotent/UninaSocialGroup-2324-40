package UninaSocialGroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InfoPost_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPaneNorth;
	private JPanel contentPaneNorthNorth;
	private JPanel contentPaneNorthCenter;
	private JPanel contentPaneCenter;

	private List<Commenti> resCommenti = new ArrayList<Commenti>();

	private CommentiDAO commentiDAO = new CommentiDAO();
	private ContenutiDAO contenutoDAO = new ContenutiDAO();
	private Contenuti resContenuto;
	private InfoPostController infoPostController = new InfoPostController(InfoPost_GUI.this);
	
	
	public InfoPost_GUI(int id_Contenuto, String nomeUtente, String nomeGruppo, int check) {

		resContenuto = contenutoDAO.SelSigContenuto(id_Contenuto);
		
		setTitle("Info Post");
		setForeground(PaletteColori.blueColor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);

		BorderLayout bl_contentPane = new BorderLayout();
		bl_contentPane.setHgap(10);
		bl_contentPane.setVgap(20);
		contentPane = new JPanel(bl_contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPaneNorthNorth = new JPanel();
		contentPaneCenter = new JPanel(new BorderLayout());
		contentPaneNorthCenter = new JPanel();
		contentPaneNorth = new JPanel(new BorderLayout());
		contentPane.add(contentPaneNorth, BorderLayout.NORTH);
		contentPane.add(contentPaneCenter, BorderLayout.CENTER);

		// parte nord
		contentPaneNorthNorth.setLayout(new BoxLayout(contentPaneNorthNorth, BoxLayout.X_AXIS));
		contentPaneNorthCenter.setLayout(new BoxLayout(contentPaneNorthCenter, BoxLayout.X_AXIS));
		contentPaneNorth.add(contentPaneNorthNorth, BorderLayout.NORTH);
		contentPaneNorth.add(contentPaneNorthCenter, BorderLayout.CENTER);

		JLabel labelPost = new JLabel();
		labelPost.setText(resContenuto.getPubblicatore());
		labelPost.setForeground(PaletteColori.blueColor);
		labelPost.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneNorthNorth.add(labelPost);

		JButton btnIndietro = new JButton("Indietro");
		contentPaneNorthNorth.add(Box.createHorizontalGlue()); // sposta il pulsante a destra
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoPostController.ActionIndietro(check, nomeUtente, nomeGruppo);
			}
		});
		contentPaneNorthNorth.add(btnIndietro);

		JTextArea textAreaPost = new JTextArea(resContenuto.getTesto());
		textAreaPost.setEditable(false);
		textAreaPost.setPreferredSize(new Dimension(0, 40));
		textAreaPost.setBackground(UIManager.getColor("TextArea.background"));
		textAreaPost.setForeground(UIManager.getColor("TextArea.foreground"));
		textAreaPost.setLineWrap(true);
		contentPaneNorthCenter.add(textAreaPost);

		// CENTRO
		JLabel labelCommenti = new JLabel();
		labelCommenti.setHorizontalAlignment(SwingConstants.LEFT);
		labelCommenti.setText("Commenti");
		labelCommenti.setForeground(PaletteColori.blueColor);
		labelCommenti.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneCenter.add(labelCommenti, BorderLayout.NORTH);

		JPanel panelCommenti = new JPanel();
		panelCommenti.setBackground(Color.WHITE);
		createTextAreaCommenti(id_Contenuto, panelCommenti);

		JScrollPane postsScrollPane = new JScrollPane(panelCommenti);
		panelCommenti.setLayout(new BoxLayout(panelCommenti, BoxLayout.Y_AXIS));
		postsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		postsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		postsScrollPane.setVisible(true);
		contentPaneCenter.add(postsScrollPane, BorderLayout.CENTER);

		JPanel panelCommento = new JPanel();
		panelCommento.setBorder(new EmptyBorder(5, 0, 0, 0)); // aggiunge 10 pixel di spazio verticale sopra il pannello
																// dei commenti
		contentPaneCenter.add(panelCommento, BorderLayout.SOUTH);
		panelCommento.setLayout(new BoxLayout(panelCommento, BoxLayout.X_AXIS));

		JTextArea textAddCommento = new JTextArea("Aggiungi un commento...");
		textAddCommento.setForeground(Color.GRAY);
		textAddCommento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textAddCommento.getText().equals("Aggiungi un commento...")) {
					textAddCommento.setText(""); // Rimuovi il testo placeholder
					textAddCommento.setForeground(Color.BLACK); // Cambia il colore del testo a nero
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textAddCommento.getText().trim().isEmpty()) {
					textAddCommento.setText("Aggiungi un commento...");
					textAddCommento.setForeground(Color.GRAY); // Ripristina il colore del placeholder
				}
			}
		});
		textAddCommento.setBackground(PaletteColori.lightModeColorBG);
		textAddCommento.setLineWrap(true);
		panelCommento.add(textAddCommento);

		JButton btnRimCommento = new JButton("Rimuovi/Modifica commento");
		btnRimCommento.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnRimCommento.setHorizontalAlignment(SwingConstants.LEFT);
		btnRimCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoPostController.ActionModCommento(id_Contenuto, nomeUtente, nomeGruppo, check);
			}
		});
		
				JButton btnAddCommento = new JButton("Commenta");
				btnAddCommento.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				btnAddCommento.setHorizontalAlignment(SwingConstants.LEFT);
				btnAddCommento.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						infoPostController.ActionAddCommento(textAddCommento, panelCommenti, id_Contenuto, nomeUtente, nomeGruppo, check);
					}
				});
				panelCommento.add(btnAddCommento);

		panelCommento.add(btnRimCommento);

	}

	// restutuisce una textArea che contine tutti i commenti del post
	private void createTextAreaCommenti(int id_Contenuto, JPanel panelCommenti) {

		resCommenti = commentiDAO.SelCommentiPost(id_Contenuto);

		// For speciale
		for (Commenti commento : resCommenti) {
			JTextArea text = new JTextArea();
			text.setEditable(false);
			text.append(commento.getPubblicatore() + ": " + commento.getTesto() + "\n");
			text.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelCommenti.add(text);
			panelCommenti.add(Box.createRigidArea(new Dimension(0, 10)));
		}

	}

}
