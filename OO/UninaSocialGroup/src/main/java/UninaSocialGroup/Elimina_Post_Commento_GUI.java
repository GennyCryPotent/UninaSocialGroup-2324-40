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

public class Elimina_Post_Commento_GUI extends JFrame {

	List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	//Schermata 
	public Elimina_Post_Commento_GUI(String NU, String NG) {

		Contenuti_DAO C = new Contenuti_DAO();

		// PANELLI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(114, 10, 396, 194);

		Res_Contenuti_Gruppi = C.SelContenutiUtenteGruppo(NG, NU);

		for (int i = 0; i < Res_Contenuti_Gruppi.size(); i++) {
			textArea.append(Res_Contenuti_Gruppi.get(i).getId_Contenuto() + " - "
					+ Res_Contenuti_Gruppi.get(i).getTesto() + "\n");
		}
		
		JScrollPane scrollPane = new JScrollPane(textArea, // mette un scroll panel con i contenuti della text Area
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 414, 194);
		contentPane.add(scrollPane);

		// BOTTONI
		Button Button_Elimina = new Button("Elimina");
		Button_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int DelPost = Integer.parseInt(JOptionPane.showInputDialog(Button_Elimina, "Quale post vuoi eliminare?",
						"Elimina un post", JOptionPane.QUESTION_MESSAGE));
				C.DelContenuto(DelPost);
				Elimina_Post_Commento_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.GruppiGUI(NU, NG);

			}
		});
		Button_Elimina.setBounds(354, 10, 70, 40);
		contentPane.add(Button_Elimina);
		
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setText("Quale contenuto vuoi eliminare?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(35, 10, 313, 22);
		contentPane.add(lblNewLabel);

	}
	
	//Schermata commento
	/**
	 * @wbp.parser.constructor
	 */
	public Elimina_Post_Commento_GUI(String NU, String NG, int Id_Contenuto) {

		Commenti_DAO C = new Commenti_DAO();
		
		// PANELLI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		//COMPONENTI
		JTextArea textAreaCommenti = new JTextArea();
		textAreaCommenti.setBounds(114, 10, 396, 194);

		Res_Commenti = C.SelCommentiUtentePost(NU, Id_Contenuto);

		for (int i = 0; i < Res_Commenti.size(); i++) {
			textAreaCommenti.append(Res_Commenti.get(i).getId_Commento() + " - "
					+ Res_Commenti.get(i).getTesto() + "\n");
		}
		
		JScrollPane scrollPane = new JScrollPane(textAreaCommenti, // mette un scroll panel con i contenuti della text Area
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 56, 414, 194);
		contentPane.add(scrollPane);

		// BOTTONI
		Button Button_Elimina = new Button("Elimina");
		Button_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int DelCommento = Integer.parseInt(JOptionPane.showInputDialog(Button_Elimina, "Quale commento vuoi eliminare?",
						"Elimina un commento", JOptionPane.QUESTION_MESSAGE));
				C.DelCommento(DelCommento);
				Elimina_Post_Commento_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.GruppiGUI(NU, NG);

				
			}
		});
		Button_Elimina.setBounds(354, 10, 70, 40);
		contentPane.add(Button_Elimina);
		
		Button Button_Modifica = new Button("Modifica");
		Button_Modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ModCommento = Integer.parseInt(JOptionPane.showInputDialog(Button_Modifica, "Quale commento vuoi modificare?",
						"Modifica un commento", JOptionPane.QUESTION_MESSAGE));
				String NewCommento = JOptionPane.showInputDialog(Button_Modifica, "Cosa vuoi scrivere?",
						"Modifica un commento", JOptionPane.QUESTION_MESSAGE);
				
				C.UpCommento(NU, ModCommento, NewCommento);
				Elimina_Post_Commento_GUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.GruppiGUI(NU, NG);
				
			}
		});
		Button_Modifica.setActionCommand("Modifica");
		Button_Modifica.setBounds(265, 10, 70, 40);
		contentPane.add(Button_Modifica);
		
		JLabel lblNewLabel = new JLabel("New Label");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setText("Scegli un commento");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 217, 22);
		contentPane.add(lblNewLabel);
		
		

	}
}
