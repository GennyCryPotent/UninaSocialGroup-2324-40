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

	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();

	private CommentiDAO CO = new CommentiDAO();
	private ContenutiDAO C = new ContenutiDAO();
	private Contenuti Res_Contenuto;
	private InfoPostController IC = new InfoPostController(InfoPost_GUI.this);

	public InfoPost_GUI(int Id_Contenuto, String NU, String NG, int check) {

		Res_Contenuto = C.SelSigContenuto(Id_Contenuto);

		setTitle("Info Post");
		setForeground(new Color(0, 128, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);

		BorderLayout bl_contentPane = new BorderLayout();
		bl_contentPane.setHgap(10);
		bl_contentPane.setVgap(20);
		contentPane = new JPanel(bl_contentPane);
		contentPane.setForeground(new Color(31, 31, 31));
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

		JLabel LabelPost = new JLabel();
		LabelPost.setText(Res_Contenuto.getPubblicatore());
		LabelPost.setForeground(new Color(0, 128, 255));
		LabelPost.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneNorthNorth.add(LabelPost);

		JButton btnIndietro = new JButton("Indietro");
		contentPaneNorthNorth.add(Box.createHorizontalGlue()); // sposta il pulsante a destra
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IC.ActionIndietro(check, NU, NG);
			}
		});
		contentPaneNorthNorth.add(btnIndietro);

		JTextArea textAreaPost = new JTextArea(Res_Contenuto.getTesto());
		textAreaPost.setEditable(false);
		textAreaPost.setPreferredSize(new Dimension(0, 40));
		textAreaPost.setBackground(UIManager.getColor("TextArea.background"));
		textAreaPost.setForeground(UIManager.getColor("TextArea.foreground"));
		textAreaPost.setLineWrap(true);
		contentPaneNorthCenter.add(textAreaPost);

		// CENTRO
		JLabel LabelCommenti = new JLabel();
		LabelCommenti.setHorizontalAlignment(SwingConstants.LEFT);
		LabelCommenti.setText("Commenti");
		LabelCommenti.setForeground(new Color(0, 128, 255));
		LabelCommenti.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneCenter.add(LabelCommenti, BorderLayout.NORTH);

		JPanel panelCommenti = new JPanel();
		panelCommenti.setBackground(Color.WHITE);
		createTextAreaCommenti(Id_Contenuto, panelCommenti);

		JScrollPane PostsScrollPane = new JScrollPane(panelCommenti);
		panelCommenti.setLayout(new BoxLayout(panelCommenti, BoxLayout.Y_AXIS));
		PostsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		PostsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		PostsScrollPane.setVisible(true);
		contentPaneCenter.add(PostsScrollPane, BorderLayout.CENTER);

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
		textAddCommento.setBackground(new Color(255, 255, 255));
		textAddCommento.setLineWrap(true);
		panelCommento.add(textAddCommento);

		JButton btnRimCommento = new JButton("Rimuovi/Modifica commento");
		btnRimCommento.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnRimCommento.setHorizontalAlignment(SwingConstants.LEFT);
		btnRimCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IC.ActionModCommento(Id_Contenuto, NU, NG, check);
			}
		});
		
				JButton btnAddCommento = new JButton("Commenta");
				btnAddCommento.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				btnAddCommento.setHorizontalAlignment(SwingConstants.LEFT);
				btnAddCommento.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IC.ActionAddCommento(textAddCommento, panelCommenti, Id_Contenuto, NU, NG, check);
					}
				});
				panelCommento.add(btnAddCommento);

		panelCommento.add(btnRimCommento);

	}

	// restutuisce una textArea che contine tutti i commenti del post
	private void createTextAreaCommenti(int Id_Contenuto, JPanel panelCommenti) {

		Res_Commenti = CO.SelCommentiPost(Id_Contenuto);

		// For speciale
		for (Commenti commento : Res_Commenti) {
			JTextArea text = new JTextArea();
			text.setEditable(false);
			text.append(commento.getPubblicatore() + ": " + commento.getTesto() + "\n");
			text.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelCommenti.add(text);
			panelCommenti.add(Box.createRigidArea(new Dimension(0, 10)));
		}

	}

}
