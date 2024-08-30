package UninaSocialGroup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class InfoPost_GUI extends JFrame {

	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private Commenti_DAO CO = new Commenti_DAO();
	private Contenuti_DAO C = new Contenuti_DAO();
	private Likes_DAO L = new Likes_DAO();
	private Contenuti Res_Contenuto;
	private int NLike;
	private int NCommenti;
	
	public InfoPost_GUI(int Id_Contenuto, String NU, String NG, int check) {

		// PANNELLI
		setBounds(100, 100, 738, 600);

		Res_Contenuto = C.SelSigContenuto(Id_Contenuto);
		

		// COMPONENTI
		JTextArea textCommenti = new JTextArea();
		textCommenti.setLineWrap(true);
		textCommenti.setEditable(false);
		createTextAreaCommenti(Id_Contenuto, textCommenti);
		getContentPane().add(textCommenti);

		JButton btnNewButton_1 = new JButton("Indietro");
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
		getContentPane().add(btnNewButton_1);

		JLabel LabelCommenti = new JLabel();
		LabelCommenti.setText("Commenti");
		LabelCommenti.setForeground(new Color(0, 128, 255));
		LabelCommenti.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(LabelCommenti);

		JTextArea textAreaPost = new JTextArea(Res_Contenuto.getTesto());
		textAreaPost.setLineWrap(true);
		textAreaPost.setEditable(false);
		getContentPane().add(textAreaPost);

		JLabel LabelPost = new JLabel();
		LabelPost.setText(Res_Contenuto.getPubblicatore());
		LabelPost.setForeground(new Color(0, 128, 255));
		LabelPost.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(LabelPost);

		JTextArea textAddCommento = new JTextArea();
		textAddCommento.setLineWrap(true);
		getContentPane().add(textAddCommento);

		JLabel lblAggiungiCommento = new JLabel();
		lblAggiungiCommento.setText("Aggiungi un commento");
		lblAggiungiCommento.setForeground(new Color(0, 128, 255));
		lblAggiungiCommento.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(lblAggiungiCommento);

		JButton btnNewButtonAddCommento = new JButton("Commenta");
		btnNewButtonAddCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textAddCommento.getText().isEmpty()) {
					CO.InsCommento(textAddCommento.getText(), Id_Contenuto, NU);
					textCommenti.append(NU + " :" + textAddCommento.getText() + "\n");
					textAddCommento.setText("");
				}
			}
		});
		getContentPane().add(btnNewButtonAddCommento);

		JButton btnNewButtonRimCommento = new JButton("Rimuovi/Modifica commento");
		btnNewButtonRimCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.Elimina_Commento(NU, NG, Id_Contenuto);

			}
		});
		getContentPane().add(btnNewButtonRimCommento);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("Post inserito nel gruppo : " + Res_Contenuto.getNome_Gruppo() + "\nData: " + Res_Contenuto.getData_Creazione() + 
						 "\nNumero di like: " + L.SelNumLike(Id_Contenuto) + "\nNumero di commenti: " + CO.SelNumCommenti(Id_Contenuto));

		// GroupLayout
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			    groupLayout.createParallelGroup(Alignment.LEADING)
			        .addGroup(groupLayout.createSequentialGroup()
			            .addContainerGap()
			            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
			                .addComponent(LabelPost, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
			                .addComponent(lblAggiungiCommento)
			                .addComponent(textAddCommento, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
			                .addComponent(btnNewButtonAddCommento)
			                .addComponent(btnNewButtonRimCommento))
			            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
			                .addComponent(btnNewButton_1)
			                .addGroup(groupLayout.createSequentialGroup()
			                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
			                        .addComponent(LabelCommenti, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
			                        .addComponent(textCommenti, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			                        .addComponent(textPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			                    .addContainerGap())))
			        .addGroup(groupLayout.createSequentialGroup()
			            .addContainerGap()
			            .addComponent(textAreaPost, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
			            .addContainerGap())
			);

		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(LabelPost, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textAreaPost, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(LabelCommenti, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAggiungiCommento, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCommenti, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
						.addComponent(textAddCommento, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButtonAddCommento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButtonRimCommento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		getContentPane().setLayout(groupLayout);
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {

				//????
			}

		});
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
	
	

