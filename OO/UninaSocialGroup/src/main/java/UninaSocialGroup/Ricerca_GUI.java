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
	
	private Gestione_Finestre GF = new Gestione_Finestre();
	private Home_GUI homeView;
	
    private JPanel contentPane;
    private JPopupMenu popUpMenu;
    private JPanel panelOfSearch;
    private JPanel panelOfRicercaResoult;
    private JPanel ParametresArea;
    	
    private JLabel labelSearchText;
    private JTextArea SearchText;
    private JLabel labelTagsText;
    private JPanel ResoultArea;
    private JPanel ResoultRicercaArea;
    private JComboBox TagsBox;
    private JScrollPane ScrollResoult;
    private JScrollPane ScrollRicercaResoult;
    
    

    private Color lightColorBG = new Color(255, 255, 255);
    private Color lightColorButton = new Color(255, 255, 255);
    private Color lightColorFont = new Color(0, 0, 0);
    private Color lightColorInternalArea = new Color(244, 244, 244);
    
    private Color blueColor = new Color(0, 128, 255);
    private Color darkColorFont = new Color(255, 255, 255);

    public Ricerca_GUI(String NU, Home_GUI HomeView) {
    	
    	homeView = HomeView;
    	
        setTitle("Ricerca");
        setForeground(new Color(0, 128, 255));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setForeground(new Color(31, 31, 31));
        contentPane.setBackground(lightColorBG);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        
        ParametresArea = new JPanel();
        ParametresArea.setLayout(new BoxLayout(ParametresArea, BoxLayout.Y_AXIS));
        
        labelSearchText = new JLabel("Search Bar:");
        labelSearchText.setForeground(new Color(0, 128, 255));
		labelSearchText.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelSearchText.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		labelTagsText = new JLabel("Tags:");
        labelTagsText.setForeground(new Color(0, 128, 255));
		labelTagsText.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelTagsText.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		
		
        setContentPane(contentPane);

        popUpMenu = new JPopupMenu();
        popUpMenu.setFocusable(false);
        panelOfSearch = new JPanel();
        panelOfSearch.setBackground(lightColorInternalArea);
        //panelOfSearch.setPreferredSize(new Dimension(300, 100)); // Imposta la dimensione preferita
        popUpMenu.add(panelOfSearch);

        
        panelOfRicercaResoult = new JPanel();
        panelOfRicercaResoult.setBackground(lightColorInternalArea);
        
        List<String> tags = new ArrayList<String>();
        
		Tags_DAO tags_DAO = new Tags_DAO();
        
		
		tags.add("Nessuno");
		
		tags.addAll(tags_DAO.SelAllTags_String());
		
		
        
        SearchText = new JTextArea();
        

		
        TagsBox = new JComboBox(tags.toArray(new String[0]));
        

        
        SearchText.setPreferredSize(new Dimension(0,20));
        
        
        
        
        
        
        
        //-------- Tasto Ricerca 
        
        
        ResoultRicercaArea = new JPanel();
        ResoultRicercaArea.setLayout(new BoxLayout(ResoultRicercaArea, BoxLayout.Y_AXIS));
		
        ScrollRicercaResoult = new JScrollPane(ResoultRicercaArea);
        ScrollRicercaResoult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ScrollRicercaResoult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panelOfRicercaResoult.add(ScrollRicercaResoult,  BorderLayout.CENTER);
        
        List<JPanel> listaDellaRicerca = new ArrayList<>();
      
        
        JButton ricercaButton = new JButton("🔍");
		ricercaButton.setForeground(blueColor);
		ricercaButton.setBackground(lightColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchText.setText("");
				popUpMenu.setVisible(false);
				CreazioneResultDaTag(listaDellaRicerca, NU);
			}
		});


        
        
        

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// ------- Barra della ricerca
		
        
        
        
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
            	
            	if(GruppiIsNotEmpty(gruppi)) {
            	
            			for(int i = 0; i< gruppi.size(); i++) {
            				
            				JTextArea textArea = new JTextArea();
            				
            				//System.out.println(gruppi.get(i).getNome());
            				textArea.setText(gruppi.get(i).getNome());
           
            				textArea.setEditable(false);
            				
            				// Aggiungi il MouseListener per mostrare il testo quando si passa sopra
            				textArea.addMouseListener(new MouseAdapter() {
            	                @Override
            	                public void mouseEntered(MouseEvent e) {
            	                	textAreaSetColor(textArea,blueColor, darkColorFont);
            	                    // Puoi usare un JToolTip personalizzato
            	                	textArea.setToolTipText(textArea.getText());
            	                }

            	                @Override
            	                public void mouseExited(MouseEvent e) {
            	                	textAreaSetColor(textArea,lightColorInternalArea, lightColorFont);
            	                	// Nascondi il tooltip quando il mouse esce
            	                	textArea.setToolTipText(null);
            	                }
            	                
            	                public void mousePressed(MouseEvent e) {
            	                    if (SwingUtilities.isLeftMouseButton(e)) {
            	                		//System.out.println(e.getPoint());
            	                		SearchText.setText(textArea.getText());
            	                		CreazioneResultDaBarraRicerca(textArea.getText(), NU);
            	                		popUpMenu.setVisible(false);
            	                    }

            	                }
            	                
            	            });
            			

        				ResoultText.add(textArea);
        				ResoultArea.add(ResoultText.get(i));
        			
        				ResoultArea.setPreferredSize(new Dimension(200, gruppi.size() * 20));
            			
        				
        				RefreshSearchPopUp();
            	}
            	
            	popUpMenu.pack();  // Questo forzerà il JPopupMenu a ridimensionarsi correttamente.
            	// Point location = SearchText.getLocationOnScreen();
                popUpMenu.show(SearchText, 0, SearchText.getHeight());
                
            }else {
    			popUpMenu.setVisible(false);
            }
           }
        });
        
        ParametresArea.add(labelSearchText);
        ParametresArea.add(new JScrollPane(SearchText));
        ParametresArea.add(labelTagsText);
        JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JScrollPane(TagsBox));
        p.add(ricercaButton);
        ParametresArea.add(p); 
		ParametresArea.setAlignmentX(Component.LEFT_ALIGNMENT);

		contentPane.add(ParametresArea, BorderLayout.NORTH);
        contentPane.add(panelOfRicercaResoult, BorderLayout.CENTER);
        //ParametresArea.setLayout(new BorderLayout(100,100));
        
        //SearchText.setBorder(new EmptyBorder(5, 5, 5, 5));
        
    }
    
	private void RefreshSearchPopUp() {
		ResoultArea.revalidate();
		ResoultArea.repaint();
		
		popUpMenu.revalidate();
		popUpMenu.repaint();

		panelOfSearch.revalidate();
		panelOfSearch.repaint();
	}
	
	
	private void RefreshSearch() {
		ResoultRicercaArea.revalidate();
		ResoultRicercaArea.repaint();
		
		panelOfRicercaResoult.revalidate();
		panelOfRicercaResoult.repaint();
	}
	
	private boolean GruppiIsNotEmpty(List<Gruppi> g) {
		return g != null && !g.isEmpty();
	}
	
	private void textAreaSetColor(JTextArea textArea, Color ColorInternalArea, Color ColorFont ) {
    	textArea.setBackground(ColorInternalArea);
    	textArea.setForeground(ColorFont);
	}
	
	private JButton CreazioneTastoInterazioneGruppoCercato(String Nome_Utente, String Nome_Gruppo) {
		
		Partecipano_DAO partecipano_DAO = new Partecipano_DAO(); 
		Notifiche_Richieste_DAO notifiche_Richieste_DAO = new Notifiche_Richieste_DAO(); 
		
		JButton button = new JButton();
		
		if(partecipano_DAO.SelPartecipanoGruppo(Nome_Gruppo, Nome_Utente)) {
			button.setText("🚪");
			//System.out.println("Bottone - " + Nome_Utente + " - " + Nome_Gruppo);
			button.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					setVisible(false);
					GF.GruppiGUI(Nome_Utente, Nome_Gruppo);
					homeView.setVisible(false);
		        }  
			});
		}else if(notifiche_Richieste_DAO.SelNoitificheRichiesteDiUtenteDiGruppo(Nome_Gruppo, Nome_Utente)) {
			button.setText("⏳");

		}else{
			button.setText("ENTRA");
			button.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					notifiche_Richieste_DAO.InsNotifica_R(Nome_Gruppo, Nome_Utente);
					button.setText("⏳");
		        }  
			});
		}
		return button;
	}
	
	
	private void CreazioneResultDaTag(List<JPanel> listaDellaRicerca, String NU) {

		ResoultRicercaArea.removeAll();
		listaDellaRicerca.clear();
		panelOfRicercaResoult.removeAll();
    	
		List<Possiedono> possiedono = new ArrayList<>();
    	
		Possiedono_DAO possiedono_DAO = new Possiedono_DAO();
    	
    	
    	possiedono = possiedono_DAO.SelGruppiConTag((String)TagsBox.getSelectedItem());
    	
    	//System.out.println(TagsBox.getSelectedItem()); 
    	
		
		
		for(int i=0; i<possiedono.size(); i++) {
    		
			
			//System.out.println(possiedono.get(i).Nome_Gruppo);
    		
    		
    		listaDellaRicerca.add(creazionePanelPerResultRicerca( possiedono.get(i).Nome_Gruppo, NU) );
    		//listaDellaRicerca.get(i).setEditable(false);
    		
    		ResoultRicercaArea.add(listaDellaRicerca.get(i));
    		panelOfRicercaResoult.add(ResoultRicercaArea);				
			
    		//RefreshSearch();
		}
		
		
		RefreshSearch();
		
	}
	
	
	private void CreazioneResultDaBarraRicerca(String Nome_Gruppo, String NU) {

		ResoultRicercaArea.removeAll();
		panelOfRicercaResoult.removeAll();
	
		ResoultRicercaArea.add(creazionePanelPerResultRicerca(Nome_Gruppo, NU));
		panelOfRicercaResoult.add(ResoultRicercaArea);				
					
		RefreshSearch();
		
	}
	
	
	private JPanel creazionePanelPerResultRicerca(String Nome_Gruppo, String NU) {
		JPanel panel = new JPanel();
		panel.setBackground(lightColorInternalArea);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		panel.add(new JTextArea(Nome_Gruppo));
		panel.add(CreazioneTastoInterazioneGruppoCercato(NU, Nome_Gruppo));
		
		return panel;
	}
}
