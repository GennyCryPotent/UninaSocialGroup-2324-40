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

public class GruppiGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String NU; // Nome Utente
	public String NG; // Nome del gruppo
	private String NewPost;
	List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();

	public GruppiGUI(String NU, String NG) {

		Contenuti_DAO C = new Contenuti_DAO("system", "Database@03");

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

		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(244, 244, 244));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(140, 97, 573, 291);

		Res_Contenuti_Gruppi = C.SelAllContenutiGruppo(NG);
		for (int i = 0; i < Res_Contenuti_Gruppi.size(); i++) {
			textArea.append(Res_Contenuti_Gruppi.get(i).getPubblicatore() + ": "
					+ Res_Contenuti_Gruppi.get(i).getTesto() + "\n");
		}
		contentPane.add(textArea);

		JScrollPane GruppiGUIV = new JScrollPane();
		GruppiGUIV.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GruppiGUIV.setBounds(10, 97, 110, 291);
		contentPane.add(GruppiGUIV);

		JButton Notifiche = new JButton("🔔");
		Notifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GruppiGUI.this.setVisible(false);
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

				GruppiGUI.this.setVisible(false);
				Gestione_Finestre V = new Gestione_Finestre();
				V.Elimina_Contenuto(NU, NG);

			}
		});
		Rimuovi_Post.setForeground(new Color(0, 128, 255));
		Rimuovi_Post.setFont(new Font("Dialog", Font.PLAIN, 18));
		Rimuovi_Post.setBackground(Color.WHITE);
		Rimuovi_Post.setBounds(190, 22, 60, 53);
		contentPane.add(Rimuovi_Post);
	}
}
