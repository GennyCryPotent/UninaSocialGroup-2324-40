package UninaSocialGroup;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Ricerca_GUI extends JFrame {

    private JPanel contentPane;
    private JPopupMenu popUpMenu;
    private JPanel panelOfSearch;
    private JPanel ParametresArea;
    	
    private JTextArea SearchText;
    private JPanel ResoultArea;
    private JComboBox TagsBox;
    private JScrollPane ScrollResoult;
    
    private Color darkColorBG = new Color(27, 27, 27);
    private Color darkColorButton = new Color(15, 15, 15);
    private Color darkColorFont = new Color(255, 255, 255);
    private Color darkColorInternalArea = new Color(63, 63, 63);

    private Color lightColorBG = new Color(255, 255, 255);
    private Color lightColorButton = new Color(255, 255, 255);
    private Color lightColorFont = new Color(0, 0, 0);
    private Color lightColorInternalArea = new Color(244, 244, 244);

    private Color AcctualColorBG = lightColorBG;
    private Color AcctualColorButton = lightColorButton;
    private Color AcctualColorFont = lightColorFont;
    private Color AcctualtColorInternalArea = lightColorInternalArea;

    public Ricerca_GUI() {
        setTitle("Ricerca");
        setForeground(new Color(0, 128, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setForeground(new Color(31, 31, 31));
        contentPane.setBackground(AcctualColorBG);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        
        ParametresArea = new JPanel();
        ParametresArea.setLayout(new BoxLayout(ParametresArea, BoxLayout.Y_AXIS));
		
	
        
        setContentPane(contentPane);

        popUpMenu = new JPopupMenu();
        popUpMenu.setFocusable(false);
        panelOfSearch = new JPanel();
        panelOfSearch.setBackground(AcctualtColorInternalArea);
        //panelOfSearch.setPreferredSize(new Dimension(300, 100)); // Imposta la dimensione preferita
        popUpMenu.add(panelOfSearch);

        
        List<String> tags = new ArrayList<String>();
        
		Tags_DAO tags_DAO = new Tags_DAO();
        
		
		tags.add("Nessuno");
		
		tags.addAll(tags_DAO.SelAllTags_String());
		
		
        
        SearchText = new JTextArea();
        TagsBox = new JComboBox(tags.toArray(new String[0]));
        

        
        SearchText.setPreferredSize(new Dimension(0,20));
        
        
        
        
        
        JButton ricercaButton = new JButton("🔍");
		ricercaButton.setForeground(new Color(0, 128, 255));
		ricercaButton.setBackground(AcctualColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//this.setVisible(true);
				//Gestione_Finestre G = new Gestione_Finestre();
				//NG = btnNewButton.getText();
				//G.RicercaGUI();
				System.out.println("CIAO");

			}
		});
		//ricercaButton.setFont(new Font(null, Font.PLAIN, 12));

        
        
        
        
        
        
        
        
		ResoultArea = new JPanel();
		ResoultArea.setLayout(new BoxLayout(ResoultArea, BoxLayout.Y_AXIS));
		
	    ScrollResoult = new JScrollPane(ResoultArea);
	    ScrollResoult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ScrollResoult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
        panelOfSearch.add(ScrollResoult,  BorderLayout.CENTER);

        //SearchText.setBounds(1,1,1,1);
        
        SearchText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                showPopup();
            }

            public void removeUpdate(DocumentEvent e) {
                showPopup();
            }

            public void changedUpdate(DocumentEvent e) {
                showPopup();
            }

            private void showPopup() {
            	
        		List<Gruppi> gruppi = new ArrayList<>();
            	
            	Gruppi_DAO gruppi_DAO = new Gruppi_DAO();
            	
            	gruppi = gruppi_DAO.SelAllGruppoFromNome(SearchText.getText());
            	
            	
            	
            	List<JTextArea> ResoultText = new ArrayList<>();
    			
            	
    			
            	ResoultArea.removeAll();
            	
            	if(gruppi != null && !gruppi.isEmpty()) {
            	
            			for(int i = 0; i< gruppi.size(); i++) {
            				
            				JTextArea textArea = new JTextArea();
            				
            				
            				System.out.println(gruppi.get(i).getNome());
            				textArea.setText(gruppi.get(i).getNome());
            				textArea.setEditable(false);
            				
            				// Aggiungi il MouseListener per mostrare il testo quando si passa sopra
            				textArea.addMouseListener(new MouseAdapter() {
            	                @Override
            	                public void mouseEntered(MouseEvent e) {
            	                	textArea.setBackground(darkColorInternalArea);
            	                	textArea.setForeground(darkColorFont);
            	                    // Puoi usare un JToolTip personalizzato
            	                	textArea.setToolTipText(textArea.getText());
            	                }

            	                @Override
            	                public void mouseExited(MouseEvent e) {
            	                	textArea.setBackground(lightColorInternalArea);
            	                	textArea.setForeground(lightColorFont);
            	                	// Nascondi il tooltip quando il mouse esce
            	                	textArea.setToolTipText(null);
            	                }
            	                
            	                public void mousePressed(MouseEvent e) {
            	                    if (SwingUtilities.isLeftMouseButton(e)) {
            	                		System.out.println(e.getPoint());
            	                		SearchText.setText(textArea.getText());
            	                    }

            	                }
            	                
            	            });
            			

        				ResoultText.add(textArea);
        				ResoultArea.add(ResoultText.get(i));
        			
        				ResoultArea.setPreferredSize(new Dimension(200, gruppi.size() * 18));
            			
            			//popUpMenu.setPreferredSize(new Dimension(100, (int) ResoultArea.getPreferredSize().getHeight()));
            			
            			//popUpMenu.setPreferredSize(new Dimension(100, gruppi.size() * 18));
            			
            			
            			ResoultArea.revalidate();
            			ResoultArea.repaint();
            			
            			popUpMenu.revalidate();
            			popUpMenu.repaint();

            			panelOfSearch.revalidate();
            			panelOfSearch.repaint();
            	}
            	
            	popUpMenu.pack();  // Questo forzerà il JPopupMenu a ridimensionarsi correttamente.
            	// Point location = SearchText.getLocationOnScreen();
                popUpMenu.show(SearchText, 0, SearchText.getHeight());
                
            }
           }
        });
        
        
        ParametresArea.add(new JScrollPane(SearchText));
        ParametresArea.add(new JScrollPane(TagsBox));
		ParametresArea.add(ricercaButton);
        
        contentPane.add(ParametresArea, BorderLayout.NORTH);
        //ParametresArea.setLayout(new BorderLayout(100,100));
        
        //SearchText.setBorder(new EmptyBorder(5, 5, 5, 5));
        
    }
}
