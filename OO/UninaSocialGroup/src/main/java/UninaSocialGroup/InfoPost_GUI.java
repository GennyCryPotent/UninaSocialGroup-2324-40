package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InfoPost_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPaneNorth;
	private JPanel contentPaneNorthNorth;
	private JPanel contentPaneNorthCenter;
	private JPanel contentPaneCenter;
	private JPanel contentPaneWest;

	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private Commenti_DAO CO = new Commenti_DAO();
	private Contenuti_DAO C = new Contenuti_DAO();
	private Likes_DAO L = new Likes_DAO();
	private Contenuti Res_Contenuto;

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
		contentPaneWest = new JPanel();
		contentPaneCenter = new JPanel(new BorderLayout());
		contentPaneNorthCenter = new JPanel();
		contentPaneNorth = new JPanel(new BorderLayout());
		contentPane.add(contentPaneNorth, BorderLayout.NORTH);
		contentPane.add(contentPaneWest, BorderLayout.WEST);
		contentPane.add(contentPaneCenter, BorderLayout.CENTER);

		// parte nord
		contentPaneNorthNorth.setLayout(new BoxLayout(contentPaneNorthNorth, BoxLayout.X_AXIS));
		contentPaneNorthCenter.setLayout(new BoxLayout(contentPaneNorthCenter, BoxLayout.X_AXIS));
		contentPaneNorth.add(contentPaneNorthNorth, BorderLayout.NORTH);
		contentPaneNorth.add(contentPaneNorthCenter, BorderLayout.CENTER);

		// parte Ovest
		contentPaneWest.setLayout(new BoxLayout(contentPaneWest, BoxLayout.PAGE_AXIS));

		

		JLabel LabelPost = new JLabel();
		LabelPost.setText(Res_Contenuto.getPubblicatore());
		LabelPost.setForeground(new Color(0, 128, 255));
		LabelPost.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneNorthNorth.add(LabelPost);

		JButton btnNewButton_1 = new JButton("Indietro");
		contentPaneNorthNorth.add(Box.createHorizontalGlue()); // sposta il pulsante a destra
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Gestione_Finestre GF = new Gestione_Finestre();

				if (check == 0) { // ritorna nella schermata che ha invocato InfoPost (0 = Home ; 1 = Gruppi)
					GF.AccessoHome(NU);
				} else {
					GF.GruppiGUI(NU, NG);
				}
			}
		});
		contentPaneNorthNorth.add(btnNewButton_1);

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

		JTextArea textCommenti = new JTextArea();
		textCommenti.setEditable(false);
		textCommenti.setBackground(UIManager.getColor("TextArea.background"));
		textCommenti.setForeground(UIManager.getColor("TextArea.foreground"));
		textCommenti.setLineWrap(true);
		createTextAreaCommenti(Id_Contenuto, textCommenti);

		JScrollPane PostsScrollPane = new JScrollPane(textCommenti);
		PostsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		PostsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		PostsScrollPane.setVisible(true);
		contentPaneCenter.add(PostsScrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("Post inserito nel gruppo : " + Res_Contenuto.getNome_Gruppo() + "\nData: " + Res_Contenuto.getData_Creazione() + 
						 "\nNumero di like: " + L.SelNumLike(Id_Contenuto) + "\nNumero di commenti: " + CO.SelNumCommenti(Id_Contenuto));
		
		contentPaneCenter.add(textPane, BorderLayout.SOUTH);

		
		//OVEST
		JLabel lblAggiungiCommento = new JLabel();
		lblAggiungiCommento.setHorizontalAlignment(SwingConstants.LEFT);
		lblAggiungiCommento.setText("Aggiungi un commento");
		lblAggiungiCommento.setForeground(new Color(0, 128, 255));
		lblAggiungiCommento.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPaneWest.add(lblAggiungiCommento);

		JTextArea textAddCommento = new JTextArea();
		textAddCommento.setLineWrap(true);
		contentPaneWest.add(textAddCommento);

		JButton btnNewButtonAddCommento = new JButton("Commenta");
		btnNewButtonAddCommento.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButtonAddCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textAddCommento.getText().isEmpty()) {
					CO.InsCommento(textAddCommento.getText(), Id_Contenuto, NU);
					 textCommenti.append(NU + " :" + textAddCommento.getText() + "\n");
					textAddCommento.setText("");
				}
			}
		});
		contentPaneWest.add(btnNewButtonAddCommento);

		JButton btnNewButtonRimCommento = new JButton("Rimuovi/Modifica commento");
		btnNewButtonRimCommento.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButtonRimCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.Elimina_Commento(NU, NG, Id_Contenuto);

			}
		});
		contentPaneWest.add(btnNewButtonRimCommento);

	}
	
	// costruisce e restutuisce una textArea che contine tutti i commenti del post
		private void createTextAreaCommenti(int Id_Contenuto, JTextArea textArea) {

			Res_Commenti = CO.SelCommentiPost(Id_Contenuto);

			// For speciale
			for (Commenti commento : Res_Commenti) {
				textArea.append(commento.getPubblicatore() + ": " + commento.getTesto() + "\n");
			}

		}

}
