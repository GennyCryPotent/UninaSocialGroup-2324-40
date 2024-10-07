package UninaSocialGroup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPannelloContenuti extends JPanel {

	private JPanel secondPanel = new JPanel();
	private JPanel thirdPanel = new JPanel();

	public JTextArea textArea = new JTextArea();
	public JTextArea creatorText = new JTextArea();
	public JTextArea GroupText = new JTextArea();

	private JButton likeButton = new JButton("❤️");
	private JButton commentButton = new JButton("💬");

	private Likes_DAO L = new Likes_DAO();
	public int likeNumDinamico;
	private JPannelloContenutiController JPC = new JPannelloContenutiController(JPannelloContenuti.this);

	public JPannelloContenuti() {
		super(new BorderLayout());

		this.add(textArea, BorderLayout.CENTER);

		likeButton.setContentAreaFilled(false);
		likeButton.setBorderPainted(false);

		commentButton.setContentAreaFilled(false);
		commentButton.setBorderPainted(false);
		commentButton.setForeground(new Color(0, 0, 0));

		// Imposta il layout del secondPanel prima di aggiungere i pulsanti
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
		secondPanel.add(likeButton);
		secondPanel.add(commentButton);

		// Imposta il layout del ThirdPanel prima di aggiungere i titoli del post
		thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
		thirdPanel.add(creatorText);
		thirdPanel.add(GroupText);

		this.add(thirdPanel, BorderLayout.NORTH);
		this.add(secondPanel, BorderLayout.SOUTH);

		// this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public JPannelloContenuti(String creator, String nomeUtente, String nomeGruppo, String text, int likeNum,
			int commentNum, int Id_Post) {
		this();
		textArea.setText(text);
		setLikeNum(likeNum);
		setCommentNum(commentNum);
		setCreatorAndGroup(creator, nomeGruppo);
		likeNumDinamico = likeNum; // aggiorna il numero dei like durante l'esecuzione del programma

		// cambia il colore del cuore del mi piace
		if (L.SelLikeProfilo(nomeUtente, Id_Post)) {
			likeButton.setForeground(new Color(255, 0, 0));

		} else {
			likeButton.setForeground(new Color(0, 0, 0));

		}

		// LISTENER DEI VARI COMPONENTI
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPC.ActionViewPost(Id_Post, nomeUtente, nomeGruppo);
			}
		});

		likeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPC.ActionInsLike(nomeUtente, Id_Post, likeButton);
			}
		});

		commentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPC.ActionViewPost(Id_Post, nomeUtente, nomeGruppo);
			}
		});
	}

	public JPannelloContenuti(String text) {
		this();
		textArea.setText(text);
	}

	public void setLikeNum(int likeNum) {
		likeButton.setText("❤️");
		likeButton.setText(likeButton.getText() + " " + likeNum);
		;
	}

	public void setCreatorAndGroup(String creator, String nomeGruppo) {
		creatorText.setText(creator);
		creatorText.setEditable(false);

		GroupText.setText(nomeGruppo);
		GroupText.setEditable(false);

		creatorText.setFont(new Font("Dialog", Font.BOLD, 11));
		GroupText.setFont(new Font("Dialog", Font.ITALIC, 8));
	}

	public void setCommentNum(int commentNum) {
		commentButton.setText(commentButton.getText() + " " + commentNum);
		;
	}

	public void setColors(Color bgC, Color fontC) {
		secondPanel.setBackground(bgC);
		textArea.setBackground(bgC);
		textArea.setForeground(fontC);
		creatorText.setBackground(bgC);
		creatorText.setForeground(fontC);
		GroupText.setBackground(bgC);
		GroupText.setForeground(fontC);
		likeButton.setBackground(bgC);
		commentButton.setBackground(bgC);

	}

	public String getText() {
		return textArea.getText();
	}
}
