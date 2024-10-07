package UninaSocialGroup;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Operazioni_Post_Commento_GUI extends JFrame {

	List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gruppi_GUI gView;

	// Schermata Post
	/**
	 * @wbp.parser.constructor
	 */
	public Operazioni_Post_Commento_GUI(String NU, String NG, Gruppi_GUI gruppiView) {

		Contenuti_DAO C = new Contenuti_DAO();
		gView = gruppiView;
		// PANELLI
		setBounds(100, 100, 556, 334);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(114, 10, 396, 194);

		Res_Contenuti_Gruppi = C.SelContenutiUtenteGruppo(NG, NU);

		for (int i = 0; i < Res_Contenuti_Gruppi.size(); i++) {
			textArea.append(Res_Contenuti_Gruppi.get(i).getId_Contenuto() + " - "
					+ Res_Contenuti_Gruppi.get(i).getTesto() + "\n");
		}

		JScrollPane scrollPane = new JScrollPane(textArea, // mette un scroll panel con i contenuti della text Area
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 522, 190);
		contentPane.add(scrollPane);

		// BOTTONI
		Button Button_Elimina = new Button("Elimina");
		Button_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             
				try {
					
					int DelPost = Integer.parseInt(JOptionPane.showInputDialog(Button_Elimina,
							"Quale post vuoi eliminare?", "Elimina un post", JOptionPane.QUESTION_MESSAGE));
					
					C.DelContenuto(DelPost);
					Operazioni_Post_Commento_GUI.this.setVisible(false);
					
					gView.setVisible(false);
					
					gView = new Gruppi_GUI(NU , NG);

					gView.setVisible(true);
					
					
				} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
													// di input
				
					JOptionPane.showMessageDialog(Button_Elimina, "Inserisci un numero!");
				    
				}

			}
		});
		Button_Elimina.setBounds(462, 10, 70, 40);
		contentPane.add(Button_Elimina);

		Button Button_Modifica = new Button("Modifica");
		Button_Modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int ModPost = Integer.parseInt(JOptionPane.showInputDialog(Button_Modifica,
							"Quale commento vuoi modificare?", "Modifica un commento", JOptionPane.QUESTION_MESSAGE));
					String NewPost = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?",
							"Modifica un commento", JOptionPane.QUESTION_MESSAGE);

					C.UpContenuto(NU, NewPost, ModPost);
					Operazioni_Post_Commento_GUI.this.setVisible(false);
					
					gView.setVisible(false);
					
					gView = new Gruppi_GUI(NU , NG);

					gView.setVisible(true);
					
				} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
													// di input
					JOptionPane.showMessageDialog(Button_Modifica, "Inserisci un numero!");
				}

			}
		});
		Button_Modifica.setActionCommand("Modifica");
		Button_Modifica.setBounds(386, 10, 70, 40);
		contentPane.add(Button_Modifica);
        
		
		Button Button_Annulla = new Button("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//Gestione_Finestre V = new Gestione_Finestre();
				//V.GruppiGUI(NU, NG);
				gView = new Gruppi_GUI(NU , NG);
			}
		});
		Button_Annulla.setActionCommand("Modifica");
		Button_Annulla.setBounds(310, 10, 70, 40);
		contentPane.add(Button_Annulla);

		// LABEL
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setText("Scegli un contenuto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 186, 22);
		contentPane.add(lblNewLabel);

	}
	
	
	
	//-----------------------------------------
	// Schermata commento
	public Operazioni_Post_Commento_GUI(String NU, String NG, int Id_Contenuto) {
		setTitle("Modifica/Elimina commento");

		Commenti_DAO C = new Commenti_DAO();

		// PANELLI
		
		setBounds(100, 100, 556, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// COMPONENTI
		JTextArea textAreaCommenti = new JTextArea();
		textAreaCommenti.setBounds(114, 10, 396, 194);

		Res_Commenti = C.SelCommentiUtentePost(NU, Id_Contenuto);

		for (int i = 0; i < Res_Commenti.size(); i++) {
			textAreaCommenti
					.append(Res_Commenti.get(i).getId_Commento() + " - " + Res_Commenti.get(i).getTesto() + "\n");
		}

		JScrollPane scrollPane = new JScrollPane(textAreaCommenti, // mette un scroll panel con i commenti della text
																	// Area
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 522, 190);
		contentPane.add(scrollPane);

		// BOTTONI
		Button Button_Elimina = new Button("Elimina");
		Button_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int DelCommento = Integer.parseInt(JOptionPane.showInputDialog(Button_Elimina,
							"Quale commento vuoi eliminare?", "Elimina un commento", JOptionPane.QUESTION_MESSAGE));
					C.DelCommento(DelCommento);
					Gestione_Finestre V = new Gestione_Finestre();
					V.Info_Post(Id_Contenuto, NU, NG, 1);
					setVisible(false);
				} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
													// di input
					JOptionPane.showMessageDialog(Button_Elimina, "Inserisci un numero!");
				}

			}
		});
		Button_Elimina.setBounds(462, 10, 70, 40);
		contentPane.add(Button_Elimina);

		Button Button_Modifica = new Button("Modifica");
		Button_Modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int ModCommento = Integer.parseInt(JOptionPane.showInputDialog(Button_Modifica,
							"Quale commento vuoi modificare?", "Modifica un commento", JOptionPane.QUESTION_MESSAGE));
					String NewCommento = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?",
							"Modifica un commento", JOptionPane.QUESTION_MESSAGE);

					C.UpCommento(NU, ModCommento, NewCommento);
					Gestione_Finestre V = new Gestione_Finestre();
					V.Info_Post(Id_Contenuto, NU, NG, 1);
					setVisible(false);
				} catch (NumberFormatException E) { // eccezione quando non si inserisce un intero nella prima finestra
													// di input
					JOptionPane.showMessageDialog(Button_Modifica, "Inserisci un numero!");
				}

			}
		});
		Button_Modifica.setActionCommand("Modifica");
		Button_Modifica.setBounds(386, 10, 70, 40);
		contentPane.add(Button_Modifica);

		Button Button_Annulla = new Button("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Operazioni_Post_Commento_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.GruppiGUI(NU, NG);
			}
		});
		Button_Annulla.setActionCommand("Modifica");
		Button_Annulla.setBounds(310, 10, 70, 40);
		contentPane.add(Button_Annulla);

		// LABEL
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setText("Scegli un commento");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 186, 22);
		contentPane.add(lblNewLabel);
		

	}
	
}
