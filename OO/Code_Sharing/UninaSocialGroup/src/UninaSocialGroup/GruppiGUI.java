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
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GruppiGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String NU;
	String NewPost;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GruppiGUI(String NU) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel NomeGruppo = new JLabel();
        NomeGruppo.setForeground(new Color(0, 128, 255));
        NomeGruppo.setText("<Dynamic>");
        NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
        NomeGruppo.setBounds(210, 10, 202, 38);
        contentPane.add(NomeGruppo);
        
        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(244, 244, 244));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBounds(140, 97, 573, 291);
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
            
        	NewPost=JOptionPane.showInputDialog(AggiungiPost, "Cosa c'è di nuovo?", "Aggiungi un post", JOptionPane.QUESTION_MESSAGE);
       		System.out.println("L'utente: "+ NU + " ha inserito un nuovo post: "+NewPost);
        	
       		if(NewPost!=null) {
       			textArea.append(NU+" ha aggiunto un nuovo post: "+NewPost + "\n");
       		}
        	
        	}
        	
        });
        AggiungiPost.setForeground(new Color(0, 128, 255));
        AggiungiPost.setFont(new Font("Dialog", Font.PLAIN, 18));
        AggiungiPost.setBackground(Color.WHITE);
        AggiungiPost.setBounds(109, 22, 60, 53);
        contentPane.add(AggiungiPost);
	}
}
