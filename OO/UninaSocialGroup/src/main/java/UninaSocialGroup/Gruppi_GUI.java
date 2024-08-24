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
	private List<Contenuti> Res_Contenuti_Gruppi = new ArrayList<Contenuti>();
	private Contenuti_DAO C = new Contenuti_DAO();
	
	

	public Gruppi_GUI(String NU, String NG) {

	

		// PANNELLI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JInternalFrame_Gruppi newFrame = new JInternalFrame_Gruppi(2, NU, NG);
		contentPane.add(newFrame);
		
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
				G.Notifiche(NU);

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
				newFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(622, 29, 89, 23);
		contentPane.add(btnNewButton);

	}

	// costruisce e restutuisce una textArea che contine tutti i contenuti del gruppo
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

}
