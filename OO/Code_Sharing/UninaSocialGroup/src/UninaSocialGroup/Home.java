package UninaSocialGroup;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private String NU; //Nome Utente
    private String NG; //Nome Gruppo
    private String Trova; //cio che si trova nella textfield della ricerca
    private int i=1;
    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     */

    public Home(String NU) {
    	setForeground(new Color(0, 128, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(0, 0, 0));
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bentornato");
        lblNewLabel.setForeground(new Color(0, 128, 255));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(210, 10, 106, 38);
        contentPane.add(lblNewLabel);

        JLabel NomeUtente = new JLabel(NU);
        NomeUtente.setForeground(new Color(0, 128, 255));
        NomeUtente.setFont(new Font("Tahoma", Font.BOLD, 18));
        NomeUtente.setBounds(319, 10, 260, 38);
        contentPane.add(NomeUtente);

        TextField textField = new TextField();
        textField.setText("Ricerca");
        textField.setBounds(210, 54, 224, 21);
        contentPane.add(textField);

      
       
        
        JButton ricerca = new JButton("🔍");
        ricerca.setForeground(new Color(0, 128, 255));
        ricerca.setBackground(new Color(255, 255, 255));
        ricerca.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		 
                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBounds(10, 10, 11, 11);
                layeredPane.setVisible(true);
                contentPane.add(layeredPane);
                
                
        	}
        });
        ricerca.setFont(new Font(null, Font.PLAIN, 12));
        ricerca.setBounds(440, 54, 46, 21);
        contentPane.add(ricerca);
        
        JScrollPane GruppiGUIV = new JScrollPane();
        GruppiGUIV.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GruppiGUIV.setBounds(10, 97, 110, 291);
        contentPane.add(GruppiGUIV);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(new Color(255, 255, 255));
        GruppiGUIV.setViewportView(layeredPane);
        
        JButton btnNewButton = new JButton("Gruppo1");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home.this.setVisible(false);
        		Gestione_Finestre G = new Gestione_Finestre();
        		G.GruppiGUI(NU);
        	}
        });
        btnNewButton.setForeground(new Color(0, 128, 255));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setBounds(10, 10, 85, 21);
        layeredPane.add(btnNewButton);
        
        
        JButton Notifiche = new JButton("🔔");
        Notifiche.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home.this.setVisible(false);
        		Gestione_Finestre V = new Gestione_Finestre();
        		V.Notifiche();
        	}
        });
        Notifiche.setBackground(new Color(255, 255, 255));
        Notifiche.setForeground(new Color(0, 128, 255));
        Notifiche.setFont(new Font(null, Font.PLAIN, 18));
        Notifiche.setBounds(27, 22, 60, 53);
        contentPane.add(Notifiche);
        
        JButton Report = new JButton("📊");
        Report.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home.this.setVisible(false);
        		Gestione_Finestre V = new Gestione_Finestre();
        		V.Report_S();
        	}
        });
        Report.setForeground(new Color(0, 128, 255));
        Report.setFont(new Font("Dialog", Font.PLAIN, 18));
        Report.setBackground(Color.WHITE);
        Report.setBounds(109, 22, 60, 53);
        contentPane.add(Report);
        
        JButton Scuro = new JButton("🌙");
        Scuro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(i==1) {
        		        	contentPane.setBackground(new Color(27,27,27)); //pannello 
        		        	Scuro.setBackground(new Color(31,31,31));     //pulsante scuro
        		            ricerca.setBackground(new Color(31,31,31));   //ricerca
        		            Report.setBackground(new Color(31,31,31));
        		            Notifiche.setBackground(new Color(31,31,31));
        		           
        		            GruppiGUIV.setBackground(new Color(27,27,27));
        		            layeredPane.setBackground(new Color(27,27,27));
        		            btnNewButton.setBackground(new Color(31,31,31));
        		            textField.setBackground(new Color(31,31,31));
        		            textField.setForeground(new Color(255,255,255));
        		            i=2;
        		}else if(i==3){
        			contentPane.setBackground(new Color(255,255,255)); //pannello 
		        	Scuro.setBackground(new Color(255,255,255));     //pulsante scuro
		            ricerca.setBackground(new Color(255,255,255));   //ricerca
		            Report.setBackground(new Color(255,255,255));
		            Notifiche.setBackground(new Color(255,255,255));
		           
		            
		            layeredPane.setBackground(new Color(255,255,255));
		            btnNewButton.setBackground(new Color(255,255,255));
		            textField.setBackground(new Color(255,255,255));
		            textField.setForeground(new Color(0,0,0));
        		}   
        	}
        });
        Scuro.setForeground(new Color(0, 128, 255));
        Scuro.setFont(new Font("Dialog", Font.PLAIN, 18));
        Scuro.setBackground(Color.WHITE);
        Scuro.setBounds(604, 22, 60, 53);
        contentPane.add(Scuro);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(244, 244, 244));
        textArea.setBounds(140, 97, 573, 291);
        contentPane.add(textArea);
  
   			
   		
       
        
    }
}
