package UninaSocialGroup;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Panel;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

public class Home_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private String NU; //Nome Utente
    private String NG; //Nome Gruppo
    private String trova; //cio che si trova nella textfield della ricerca
    private boolean darkMode=false;
    private JScrollPane scrollPane;
    
    
    
    private Color darkColorBG = new Color(27,27,27);
    private Color darkColorButton = new Color(15,15,15);
    private Color darkColorFont= new Color(255,255,255);
    private Color darkColorInternalArea = new Color(63, 63, 63);

    
    private Color lightColorBG =  new Color(255,255,255);
    private Color lightColorButton = new Color(255,255,255);
    private Color lightColorFont = new Color(0,0,0);
    private Color lightColorInternalArea = new Color(238, 238, 238);
    
    
    
    
    
    
    private Color AcctualColorBG = lightColorBG;
    private Color AcctualColorButton = lightColorButton;
    private Color AcctualColorFont = lightColorFont;
    private Color AcctualtColorInternalArea = lightColorInternalArea;
    
    
    
    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     */

    public Home_GUI(String NU) {
    	setForeground(new Color(0, 128, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(31,31,31));
        contentPane.setBackground(AcctualColorBG);
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
        
        JButton ricerca = new JButton("🔍");
        ricerca.setForeground(new Color(0, 128, 255));
        ricerca.setBackground(AcctualColorBG);
        ricerca.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	        
        		
        		System.out.println(trova);
                
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
        
        JButton btnNewButton = new JButton("Fantacalcio");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home_GUI.this.setVisible(false);
        		Gestione_Finestre G = new Gestione_Finestre();
        		NG = btnNewButton.getText();
        		G.GruppiGUI(NU, NG);
        	}
        });
        btnNewButton.setForeground(new Color(0, 128, 255));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setBounds(10, 10, 85, 21);
        layeredPane.add(btnNewButton);
        
        
        JButton Notifiche = new JButton("🔔");
        Notifiche.setContentAreaFilled(false);
        Notifiche.setBorderPainted(false);
        
        Notifiche.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home_GUI.this.setVisible(false);
        		Gestione_Finestre V = new Gestione_Finestre();
        		V.Notifiche(NU);
        	}
        });
        Notifiche.setBackground(new Color(255, 255, 255));
        Notifiche.setForeground(new Color(0, 128, 255));
        Notifiche.setFont(new Font(null, Font.PLAIN, 18));
        Notifiche.setBounds(27, 22, 60, 53);
        contentPane.add(Notifiche);
        
        JButton Report = new JButton("😍");
        Report.setContentAreaFilled(false);
        Report.setBorderPainted(false);
        
        Report.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home_GUI.this.setVisible(false);
        		Gestione_Finestre V = new Gestione_Finestre();
        		V.Report_S(NU);
        	}
        });
        Report.setForeground(new Color(0, 128, 255));
        Report.setFont(new Font("Dialog", Font.PLAIN, 18));
        Report.setBackground(Color.WHITE);
        Report.setBounds(109, 22, 60, 53);
        contentPane.add(Report);
        
        JPanel postsArea = new JPanel();
        postsArea.setBackground(new Color(244, 244, 244));
        postsArea.setBounds(140, 97, 573, 291);
        postsArea.setLayout(null);
        //contentPane.add(postsArea);
        
        JButton Scuro = new JButton("🌙");
        Scuro.setContentAreaFilled(false);
        Scuro.setBorderPainted(false);
        
        
        
        
        Scuro.setForeground(new Color(0, 128, 255));
        Scuro.setFont(new Font("Dialog", Font.PLAIN, 18));
        Scuro.setBackground(Color.WHITE);
        Scuro.setBounds(604, 22, 120, 53);
        contentPane.add(Scuro);
        

        
        String[] GruppiRicerca = {"Fantacalcio", "SSC_Napoli_Ultras", "Dungeons N Dragons", "Amanti del Cinema", "Fotografia per Passione", "Viaggiatori del Mondo", "Cucinare con Amore", "Appassionati di Tecnologia", "Libri e Letteratura" , "Amici degli Animali" , "Fitness e Benessere" , "Musica per tutti"};
        
        JComboBox Ricerca = new JComboBox(GruppiRicerca);
        Ricerca.setEditable(true);
        Ricerca.setBounds(210, 54, 230, 21);
        contentPane.add(Ricerca);
        
        trova=(String)Ricerca.getSelectedItem();
        
        
        //----------
       
        JScrollPane scrollPane = new JScrollPane(postsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setBounds(140, 97, 573, 291);
        scrollPane.setVisible(true);
        
        ArrayList<JPannelloContenuti> TestiLabel = new ArrayList<>();
        
        List<Contenuti> contenuti = new ArrayList<>();
        
        Contenuti_DAO contenuto_DAO = new Contenuti_DAO();
        
        contenuti = contenuto_DAO.SelContenutiGruppiUtente(NU);
        
        Likes_DAO like_DAO = new Likes_DAO();
        Commenti_DAO commento_DAO = new Commenti_DAO();
        
        
        
        int numbOfTxt = contenuti.size();
        
        for(int i = 0 ; i < numbOfTxt; i++) {
        	
        	JPannelloContenuti postPanel = new JPannelloContenuti( contenuti.get(i).getPubblicatore() , contenuti.get(i).getNome_Gruppo() ,contenuti.get(i).getTesto(), Integer.toString(like_DAO.SelNumLike(contenuti.get(i).getId_Contenuto())),  Integer.toString(commento_DAO.SelNumCommenti(contenuti.get(i).getId_Contenuto())));
        	
        	
        	//postPanel.setLikeNum( Integer.toString(like_DAO.SelNumLike(contenuti.get(i).getId_Contenuto())) );      
        	//postPanel.setCommentNum( Integer.toString(commento_DAO.SelNumCommenti(contenuti.get(i).getId_Contenuto())) );     
        	
        	postPanel.textArea.setWrapStyleWord(false);
        	postPanel.textArea.setEditable(false);
        	TestiLabel.add(postPanel);
        	
        	postsArea.add(Box.createRigidArea(new Dimension(0, 10)));
        	postsArea.add(TestiLabel.get(i));
        }
        
        
        contentPane.add(scrollPane);
 
        
        
        

        //---------------
        
        // Aggiungi un listener per impostare le dimensioni del JScrollPane dopo che il frame Ã¨ visibile
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
        
            	int contentHeight = 0;
            	//Posiziona correttamente le notifiche
            	for (int i = 0; i < numbOfTxt; i++) {
                    if(i==0) {
                    	
                    	TestiLabel.get(i).setBounds(0, 10 , (int) TestiLabel.get(i).getPreferredSize().width, TestiLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
                    }else {
                    	System.out.println(TestiLabel.get(i).getBounds());
                    	TestiLabel.get(i).setBounds(0, (int) (TestiLabel.get(i-1).getBounds().getY() + TestiLabel.get(i-1).getPreferredSize().getHeight() + 10 ), (int) TestiLabel.get(i).getPreferredSize().width,  TestiLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
                    }
                  //crea la giusta dimensione per ContentPaneForContent che permette di fare lo scrollbar delle giuste dimensioni 
                    contentHeight += (TestiLabel.get(i).getHeight() + 10 );
                }
                
                
                
        		
            	
                // Aggiorna la dimensione preferita del contenitore in base all'effettiva altezza di tutti gli elementi
            	postsArea.setPreferredSize(new Dimension(scrollPane.getWidth()-27, contentHeight));
                
            	postsArea.revalidate();
            	postsArea.repaint();
            	
            	scrollPane.revalidate();
            	scrollPane.repaint();
            	
            	
	            contentPane.revalidate();
	            contentPane.repaint();
            }
        
        });
        

        //-----
        
        Scuro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(!darkMode) {
        			AcctualColorBG = darkColorBG;
        		    AcctualColorButton = darkColorButton;
        		    AcctualColorFont = darkColorFont;
        		    AcctualtColorInternalArea = darkColorInternalArea;
        		    Scuro.setText("🃜🃚🃖🃁🂭🂺");
        		    darkMode = true;
        		    
        		}else {
        			AcctualColorBG = lightColorBG;
        		    AcctualColorButton = lightColorButton;
        		    AcctualColorFont = lightColorFont;
        		    AcctualtColorInternalArea = lightColorInternalArea;
        		    Scuro.setText("🌙");
        		    darkMode = false;
        		}
        		
	        	//contentPane.setBackground(new Color(27,27,27)); 
        		contentPane.setBackground(AcctualColorBG);//pannello 
	        	Scuro.setBackground(AcctualColorButton);     //pulsante scuro
	            ricerca.setBackground(AcctualColorButton);   //ricerca
	            Report.setBackground(AcctualColorButton);
	            Notifiche.setBackground(AcctualColorButton);
	           
	            GruppiGUIV.setBackground(AcctualColorBG);
	            
	            layeredPane.setForeground(AcctualColorFont);
	            layeredPane.setBackground(AcctualColorBG);
	            
	            btnNewButton.setBackground(AcctualColorButton);
        		
	            
	            
	            postsArea.setBackground(AcctualtColorInternalArea);
	            postsArea.setForeground(AcctualColorFont);
	            
	            
	            Ricerca.setForeground(AcctualColorFont);
	            Ricerca.setBackground(AcctualColorBG);
	            
        	}
        });

  
   	
        
    }
    
    

    
}
