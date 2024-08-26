package UninaSocialGroup;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPannelloContenuti extends JPanel {
    
    private JPanel secondPanel = new JPanel();
    
    public JTextArea textArea = new JTextArea();
    public JLabel creatorLabel = new JLabel();
    
    private JButton likeButton = new JButton("❤️");
    private JButton commentButton = new JButton("💬");
    
    public JPannelloContenuti() {
        super(new BorderLayout());

        
        
        this.add(creatorLabel, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        
        
        likeButton.setContentAreaFilled(false);
        likeButton.setBorderPainted(false);
        likeButton.setForeground(new Color(255, 0, 0));
        
        commentButton.setContentAreaFilled(false);
        commentButton.setBorderPainted(false);
        
        
        
        // Imposta il layout del secondPanel prima di aggiungere i pulsanti
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
        secondPanel.add(likeButton);
        secondPanel.add(commentButton);
        
        this.add(secondPanel, BorderLayout.SOUTH);
    }
    
    public JPannelloContenuti(String creator, String nomeGruppo, String text, String likeNum, String commentNum, int Id_Post, Gruppi_GUI G) {
    	this();
        textArea.setText(text);
        setLikeNum(likeNum);
        setCommentNum(commentNum);
        setCreator(creator, nomeGruppo);
        
        JInternalFrame_Gruppi newFrame = new JInternalFrame_Gruppi(Id_Post, creator, nomeGruppo);
        
        
        textArea.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.out.println("cliccato");
        	
        		G.getContentPane().add(newFrame);
        		newFrame.toFront();
        		newFrame.setVisible(true);
        	}
        });
        
    }
    
    public JPannelloContenuti(String text) {
        this();
        textArea.setText(text);
    }
    
    public void setLikeNum(String likeNum) {
    	likeButton.setText(likeButton.getText() + " " + likeNum);;
    }
    
    public void setCreator(String creator, String nomeGruppo) {
    	creatorLabel.setText(creator + " - " + nomeGruppo);
    }
    
    
    public void setCommentNum(String commentNum) {
    	commentButton.setText(commentButton.getText() + " " + commentNum);;
    }
    
    
    public String getText() {
        return textArea.getText();
    }
}
