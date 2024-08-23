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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Gruppi_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String NewPost;
	private String NewCommento;
	private List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
	private Contenuti Res_Contenuto;
	private Contenuti_DAO C = new Contenuti_DAO();
	private Commenti_DAO CO = new Commenti_DAO();
	private JTextArea textCommenti;

	public Gruppi_GUI(String NU, String NG) {

		Res_Contenuto = C.SelSigContenuto(2);

		// PANNELLI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// FRAME INTERNO
		JInternalFrame internalFrame = new JInternalFrame("Post");
		internalFrame.setResizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(37, 0, 626, 434);
		contentPane.add(internalFrame);

		JTextArea textAreaCommenti = createTextAreaCommenti(2);

		JButton btnNewButton_1 = new JButton("Indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
			}
		});

		JLabel LabelCommenti = new JLabel();
		LabelCommenti.setText("Commenti");
		LabelCommenti.setForeground(new Color(0, 128, 255));
		LabelCommenti.setFont(new Font("Tahoma", Font.BOLD, 18));

		JTextArea textAreaPost = new JTextArea(Res_Contenuto.getTesto());
		textAreaPost.setEditable(false);

		JLabel LabelPost = new JLabel();
		LabelPost.setText(Res_Contenuto.getPubblicatore());
		LabelPost.setForeground(new Color(0, 128, 255));
		LabelPost.setFont(new Font("Tahoma", Font.BOLD, 18));

		JTextArea textAddCommento = new JTextArea();

		JLabel lblAggiungiCommento = new JLabel();
		lblAggiungiCommento.setText("Aggiungi commento");
		lblAggiungiCommento.setForeground(new Color(0, 128, 255));
		lblAggiungiCommento.setFont(new Font("Tahoma", Font.BOLD, 18));

		JButton btnNewButtonAddCommento = new JButton("Commenta");
		btnNewButtonAddCommento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textAddCommento.getText() != "") {
					CO.InsCommento(textAddCommento.getText(), 2, NU);
					textAreaCommenti.append(NU + " :" + textAddCommento.getText());
					textAddCommento.setText("");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(internalFrame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(textAreaPost,
								GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
								.createSequentialGroup()
								.addComponent(LabelPost, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1))
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup().addContainerGap()
														.addComponent(textAddCommento, GroupLayout.PREFERRED_SIZE, 271,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup().addGap(14).addComponent(
														lblAggiungiCommento, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(btnNewButtonAddCommento)))
										.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(LabelCommenti, GroupLayout.PREFERRED_SIZE, 174,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textCommenti, GroupLayout.PREFERRED_SIZE, 268,
														GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(LabelPost, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textAreaPost, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(LabelCommenti, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAggiungiCommento, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCommenti, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(textAddCommento, GroupLayout.PREFERRED_SIZE, 122,
										GroupLayout.PREFERRED_SIZE)
								.addGap(8).addComponent(btnNewButtonAddCommento)))
				.addGap(433)));
		internalFrame.getContentPane().setLayout(groupLayout);

		JLabel NomeGruppo = new JLabel();
		NomeGruppo.setForeground(new Color(0, 128, 255));
		NomeGruppo.setText(NG);
		NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		NomeGruppo.setBounds(316, 29, 202, 38);
		contentPane.add(NomeGruppo);

		JTextArea textArea = createTextArea(NG); // crea la textArea con tutti i contenuti del gruppo
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(27, 86, 684, 348);
		contentPane.add(scrollPane);

		// BOTTONI
		JButton Notifiche = new JButton("🔔");
		Notifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Gruppi_GUI.this.setVisible(false);
				Gestione_Finestre G = new Gestione_Finestre();
				G.Notifiche();

			}
		});
		Notifiche.setForeground(new Color(0, 128, 255));
		Notifiche.setFont(new Font("Dialog", Font.PLAIN, 18));
		Notifiche.setBackground(Color.WHITE);
		Notifiche.setBounds(27, 22, 60, 53);
		contentPane.add(Notifiche);

		JButton AggiungiPost = new JButton("➕");
		AggiungiPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				NewPost = JOptionPane.showInputDialog(AggiungiPost, "Cosa c'è di nuovo?", "Aggiungi un post",
						JOptionPane.QUESTION_MESSAGE);
				C.InsContenuto(null, NewPost, NG, NU);

				if (NewPost != null) {
					textArea.append(NU + ": " + NewPost + "\n");
				}

			}

		});
		AggiungiPost.setForeground(new Color(0, 128, 255));
		AggiungiPost.setFont(new Font("Dialog", Font.PLAIN, 18));
		AggiungiPost.setBackground(Color.WHITE);
		AggiungiPost.setBounds(109, 22, 60, 53);
		contentPane.add(AggiungiPost);

		JButton Rimuovi_Post = new JButton("➖");
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

		JButton btnNewButton = new JButton("Vedi post");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(622, 29, 89, 23);
		contentPane.add(btnNewButton);

	}

	// costruisce e restutuisce una textArea che contine tutti i contenuti del
	// gruppo
	private JTextArea createTextArea(String NG) {

		JTextArea textContenuti = new JTextArea();
		textContenuti.setEditable(false);
		textContenuti.setBackground(new Color(244, 244, 244));
		textContenuti.setLineWrap(true);
		textContenuti.setBounds(27, 97, 686, 337);

		Res_Contenuti_Gruppi = C.SelAllContenutiGruppo(NG);
		for (int i = 0; i < Res_Contenuti_Gruppi.size(); i++) {
			textContenuti.append(Res_Contenuti_Gruppi.get(i).getPubblicatore() + ": "
					+ Res_Contenuti_Gruppi.get(i).getTesto() + "\n");
		}

		return textContenuti;
	}

	private JTextArea createTextAreaCommenti(int Id_Contenuto) {

		textCommenti = new JTextArea();
		textCommenti.setEditable(false);

		Res_Commenti = CO.SelCommentiPost(2);
		for (int i = 0; i < Res_Commenti.size(); i++) {
			textCommenti.append(Res_Commenti.get(i).getPubblicatore() + ": " + Res_Commenti.get(i).getTesto() + "\n");
		}

		return textCommenti;
	}
}
