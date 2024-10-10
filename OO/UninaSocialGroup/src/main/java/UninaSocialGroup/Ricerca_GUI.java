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
    
    private Color lightModeColorBG = new Color(255, 255, 255);
    private Color lightModeColorButton = new Color(255, 255, 255);
    private Color lightModeColorFont = new Color(0, 0, 0);
    private Color lightModeColorInternalArea = new Color(244, 244, 244);
    
    private Color blueColor = new Color(0, 128, 255);
    private Color darkModeColorFont = new Color(255, 255, 255);

    public Ricerca_GUI(String NU, Home_GUI HomeView) {
    	
    	// Salva la vista home
    	homeView = HomeView;
    	
        setTitle("Ricerca");
        setForeground(blueColor);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setForeground(new Color(31, 31, 31));
        contentPane.setBackground(lightModeColorBG);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // Area per i parametri di ricerca
        ParametresArea = new JPanel();
        ParametresArea.setBackground(lightModeColorBG);
        ParametresArea.setLayout(new BoxLayout(ParametresArea, BoxLayout.Y_AXIS));
        
        // Etichetta per la barra di ricerca
        labelSearchText = new JLabel("Search Bar:");
        labelSearchText.setForeground(blueColor);
		labelSearchText.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelSearchText.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		// Etichetta per i tag
		labelTagsText = new JLabel("Tags:");
        labelTagsText.setForeground(blueColor);
		labelTagsText.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelTagsText.setAlignmentX(Component.LEFT_ALIGNMENT);
		
        setContentPane(contentPane);

        // Menu popup per i risultati di ricerca
        popUpMenu = new JPopupMenu();
        popUpMenu.setFocusable(false);
        panelOfSearch = new JPanel();
        panelOfSearch.setBackground(lightModeColorInternalArea);
        popUpMenu.add(panelOfSearch);

        // Pannello per i risultati della ricerca
        panelOfRicercaResoult = new JPanel();
        panelOfRicercaResoult.setBackground(lightModeColorBG);
        
        // Lista di tag disponibile
        List<String> tags = new ArrayList<String>();
        Tags_DAO tags_DAO = new Tags_DAO();
        tags.add("Nessuno");
        tags.addAll(tags_DAO.SelAllTags_String());
        
        // Campo di testo per la barra di ricerca
        SearchText = new JTextArea();
        SearchText.setPreferredSize(new Dimension(0, 20));
        
        // ComboBox per selezionare i tag
        TagsBox = new JComboBox(tags.toArray(new String[0]));
        
        // Pannello per i risultati della ricerca
        ResoultRicercaArea = new JPanel();
        ResoultRicercaArea.setBackground(lightModeColorBG);
        ResoultRicercaArea.setLayout(new BoxLayout(ResoultRicercaArea, BoxLayout.Y_AXIS));
        
        // ScrollPane per i risultati della ricerca
        ScrollRicercaResoult = new JScrollPane(ResoultRicercaArea);
        ScrollRicercaResoult.setBackground(lightModeColorBG);
        ScrollRicercaResoult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ScrollRicercaResoult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Aggiunta dello ScrollPane al pannello dei risultati
        panelOfRicercaResoult.add(ScrollRicercaResoult, BorderLayout.CENTER);
        
        // Lista per memorizzare i risultati della ricerca
        List<JPanel> listaDellaRicerca = new ArrayList<>();
      
        // Bottone di ricerca
        JButton ricercaButton = new JButton("🔍");
        ricercaButton.setToolTipText("Cerca");
		ricercaButton.setForeground(blueColor);
		ricercaButton.setBackground(lightModeColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Quando si clicca il bottone, pulisce la barra di ricerca e avvia la ricerca
				SearchText.setText("");
				popUpMenu.setVisible(false);
				CreazioneResultDaTag(listaDellaRicerca, NU);
			}
		});

        // Area per i risultati della barra di ricerca
		ResoultArea = new JPanel();
		ResoultArea.setBackground(lightModeColorBG);
		ResoultArea.setLayout(new BoxLayout(ResoultArea, BoxLayout.Y_AXIS));
		
		ScrollResoult = new JScrollPane(ResoultArea);
		ScrollResoult.setBackground(lightModeColorBG);
		ScrollResoult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ScrollResoult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panelOfSearch.add(ScrollResoult, BorderLayout.CENTER);
        
        // Listener per aggiornare il menu popup quando il testo cambia
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

            // Mostra il menu popup con i suggerimenti di ricerca
            private void showPopup() {
            	// Lista di gruppi corrispondenti alla ricerca
        		List<Gruppi> gruppi = new ArrayList<>();
            	Gruppi_DAO gruppi_DAO = new Gruppi_DAO();
            	gruppi = gruppi_DAO.SelAllGruppoFromNome(SearchText.getText());
            	
            	// Pulisce l'area dei risultati
            	ResoultArea.removeAll();
            	
            	// Verifica se ci sono gruppi corrispondenti
            	if(GruppiIsNotEmpty(gruppi)) {
            		for(int i = 0; i < gruppi.size(); i++) {
            			// Aggiunge ogni gruppo come risultato di ricerca
            			JTextArea textArea = new JTextArea();
            			textArea.setText(gruppi.get(i).getNome());
            			textArea.setEditable(false);
            			
            			// Aggiungi un MouseListener per mostrare il testo quando si passa sopra
            			textArea.addMouseListener(new MouseAdapter() {
            	                @Override
            	                public void mouseEntered(MouseEvent e) {
            	                	// Cambia il colore dell'area di testo quando il mouse entra
            	                	textAreaSetColor(textArea, blueColor, darkModeColorFont);
            	                	textArea.setToolTipText(textArea.getText());
            	                }

            	                @Override
            	                public void mouseExited(MouseEvent e) {
            	                	// Ripristina il colore originale quando il mouse esce
            	                	textAreaSetColor(textArea, lightModeColorInternalArea, lightModeColorFont);
            	                	textArea.setToolTipText(null);
            	                }
            	                
            	                @Override
            	                public void mousePressed(MouseEvent e) {
            	                    if (SwingUtilities.isLeftMouseButton(e)) {
            	                    	// Imposta il testo nella barra di ricerca e mostra i risultati dettagliati
            	                    	SearchText.setText(textArea.getText());
            	                    	CreazioneResultDaBarraRicerca(textArea.getText(), NU);
            	                    	popUpMenu.setVisible(false);
            	                    }
            	                }
            	            });
            			ResoultArea.add(textArea);
            		}
            		ResoultArea.setPreferredSize(new Dimension(200, gruppi.size() * 20));
        			RefreshSearchPopUp();
                	popUpMenu.pack();  // Ridimensiona il JPopupMenu correttamente
                    popUpMenu.show(SearchText, 0, SearchText.getHeight());
                } else {
                	// Nasconde il menu popup se non ci sono risultati
                	popUpMenu.setVisible(false);
                }
           }
        });
        
        // Aggiunta dei componenti all'area dei parametri di ricerca
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
    }
    
    // Aggiorna il popup di ricerca
	private void RefreshSearchPopUp() {
		if(ResoultArea.getComponentCount() > 15) {
			// Imposta la dimensione massima e usa la barra di scorrimento
			ScrollResoult.setPreferredSize(new Dimension(250, 150));
		} else {
			ScrollResoult.setSize(ResoultArea.getPreferredSize());
		}
		ScrollResoult.revalidate();
		ScrollResoult.repaint();
		ResoultArea.revalidate();
		ResoultArea.repaint();
		popUpMenu.revalidate();
		popUpMenu.repaint();
		panelOfSearch.revalidate();
		panelOfSearch.repaint();
	}
	
    // Aggiorna l'area di ricerca
	private void RefreshSearch() {
		ResoultRicercaArea.revalidate();
		ResoultRicercaArea.repaint();
		panelOfRicercaResoult.revalidate();
		panelOfRicercaResoult.repaint();
	}
	
    // Verifica se la lista dei gruppi non è vuota
	private boolean GruppiIsNotEmpty(List<Gruppi> g) {
		return g != null && !g.isEmpty();
	}
	
    // Imposta i colori dell'area di testo
	private void textAreaSetColor(JTextArea textArea, Color ColorInternalArea, Color ColorFont) {
    	textArea.setBackground(ColorInternalArea);
    	textArea.setForeground(ColorFont);
	}
	
    // Crea il bottone di interazione per il gruppo cercato
	private JButton CreazioneTastoInterazioneGruppoCercato(String Nome_Utente, String Nome_Gruppo) {
		Partecipano_DAO partecipano_DAO = new Partecipano_DAO(); 
		Notifiche_Richieste_DAO notifiche_Richieste_DAO = new Notifiche_Richieste_DAO(); 
		
		JButton button = new JButton();
		
		// Verifica lo stato di partecipazione dell'utente al gruppo e aggiorna il testo del bottone
		if(partecipano_DAO.SelPartecipanoGruppo(Nome_Gruppo, Nome_Utente)) {
			button.setText("🚪");
			button.addActionListener(new ActionListener() {  
				public void actionPerformed(ActionEvent e) {  
					setVisible(false);
					GF.GruppiGUI(Nome_Utente, Nome_Gruppo);
					homeView.setVisible(false);
		        }  
			});
		} else if(notifiche_Richieste_DAO.SelNoitificheRichiesteDiUtenteDiGruppo(Nome_Gruppo, Nome_Utente)) {
			button.setText("⏳");
		} else {
			button.setText("ENTRA");
			button.addActionListener(new ActionListener() {  
				public void actionPerformed(ActionEvent e) {  
					notifiche_Richieste_DAO.InsNotifica_R(Nome_Gruppo, Nome_Utente);
					button.setText("⏳");
		        }  
			});
		}
		return button;
	}
	
    // Crea i risultati di ricerca filtrati per tag
	private void CreazioneResultDaTag(List<JPanel> listaDellaRicerca, String NU) {
		ResoultRicercaArea.removeAll();
		listaDellaRicerca.clear();
		panelOfRicercaResoult.removeAll();
    	
		// Ottiene i gruppi associati al tag selezionato
		List<Possiedono> possiedono = new ArrayList<>();
    	Possiedono_DAO possiedono_DAO = new Possiedono_DAO();
    	possiedono = possiedono_DAO.SelGruppiConTag((String) TagsBox.getSelectedItem());
		
		// Aggiunge ogni gruppo come risultato di ricerca
		for(int i = 0; i < possiedono.size(); i++) {
    		listaDellaRicerca.add(creazionePanelPerResultRicerca(possiedono.get(i).Nome_Gruppo, NU));
    		ResoultRicercaArea.add(listaDellaRicerca.get(i));
    		panelOfRicercaResoult.add(ResoultRicercaArea);				
		}
		RefreshSearch();
	}
	
    // Crea i risultati di ricerca dalla barra di ricerca
	private void CreazioneResultDaBarraRicerca(String Nome_Gruppo, String NU) {
		ResoultRicercaArea.removeAll();
		panelOfRicercaResoult.removeAll();
		ResoultRicercaArea.add(creazionePanelPerResultRicerca(Nome_Gruppo, NU));
		panelOfRicercaResoult.add(ResoultRicercaArea);					
		RefreshSearch();
	}
	
    // Crea un pannello per ogni risultato di ricerca
	private JPanel creazionePanelPerResultRicerca(String Nome_Gruppo, String NU) {
		JPanel panel = new JPanel();
		panel.setBackground(lightModeColorInternalArea);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		// Aggiunge il nome del gruppo e il bottone di interazione
		panel.add(new JLabel(Nome_Gruppo + "  "));
		panel.add(CreazioneTastoInterazioneGruppoCercato(NU, Nome_Gruppo));
		
		return panel;
	}
}