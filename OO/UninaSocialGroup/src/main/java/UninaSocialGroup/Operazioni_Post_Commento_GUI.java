package UninaSocialGroup;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Operazioni_Post_Commento_GUI extends JFrame {

	List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gruppi_GUI gView;
	private OperazioniPostCommentoController OPC = new OperazioniPostCommentoController(
			Operazioni_Post_Commento_GUI.this);

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
		JButton Button_Elimina = new JButton("Elimina");
		Button_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionEliminaPost(Button_Elimina, NU, NG);
				gView.setVisible(false);
			}
		});
		Button_Elimina.setBounds(462, 10, 70, 40);
		contentPane.add(Button_Elimina);

		JButton Button_Modifica = new JButton("Modifica");
		Button_Modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionModificaPost(Button_Modifica, NU, NG);
				gView.setVisible(false);
			}
		});
		Button_Modifica.setActionCommand("Modifica");
		Button_Modifica.setBounds(386, 10, 70, 40);
		contentPane.add(Button_Modifica);
        
		
		JButton Button_Annulla = new JButton("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionAnnulla(NU, NG);
				gView.setVisible(false);
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
		JButton Button_Elimina = new JButton("Elimina");
		Button_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionEliminaCommento(Button_Elimina, NU, NG, Id_Contenuto);
			}
		});
		Button_Elimina.setBounds(462, 10, 70, 40);
		contentPane.add(Button_Elimina);

		JButton Button_Modifica = new JButton("Modifica");
		Button_Modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionModificaCommento(Button_Modifica, NU, NG, Id_Contenuto);
			}
		});
		Button_Modifica.setActionCommand("Modifica");
		Button_Modifica.setBounds(386, 10, 70, 40);
		contentPane.add(Button_Modifica);

		JButton Button_Annulla = new JButton("Annulla");
		Button_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPC.ActionAnnulla(NU, NG);
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
