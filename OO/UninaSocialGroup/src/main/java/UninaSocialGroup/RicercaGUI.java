package UninaSocialGroup;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RicercaGUI extends JFrame {
	
	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private HomeGUI homeView;
	
    private JPanel contentPane;
    private JPopupMenu popUpMenu;
    private JPanel panelOfSearch;
    private JPanel panelOfRicercaResoult;
    private JPanel ParametresArea;
    	
    private JLabel labelSearchText;
    private JTextArea searchText;
    private JLabel labelTagsText;
    private JPanel resoultArea;
    private JPanel resoultRicercaArea;
    private JComboBox tagsBox;
    private JScrollPane scrollResoult;
    private JScrollPane scrollRicercaResoult;
    

    public RicercaGUI(String nomeUtente, HomeGUI homeView) {
    	
    	// Salva la vista home
    	this.homeView = homeView;
    	
        setTitle("Ricerca");
        setForeground(PaletteColori.blueColor);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(PaletteColori.lightModeColorBG);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // Area per i parametri di ricerca
        ParametresArea = new JPanel();
        ParametresArea.setBackground(PaletteColori.lightModeColorBG);
        ParametresArea.setLayout(new BoxLayout(ParametresArea, BoxLayout.Y_AXIS));
        
        // Etichetta per la barra di ricerca
        labelSearchText = new JLabel("Search Bar:");
        labelSearchText.setForeground(PaletteColori.blueColor);
		labelSearchText.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelSearchText.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		// Etichetta per i tag
		labelTagsText = new JLabel("Tags:");
        labelTagsText.setForeground(PaletteColori.blueColor);
		labelTagsText.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelTagsText.setAlignmentX(Component.LEFT_ALIGNMENT);
		
        setContentPane(contentPane);

        // Menu popup per i risultati di ricerca
        popUpMenu = new JPopupMenu();
        popUpMenu.setFocusable(false);
        panelOfSearch = new JPanel();
        panelOfSearch.setBackground(PaletteColori.lightModeColorInternalArea);
        popUpMenu.add(panelOfSearch);

        // Pannello per i risultati della ricerca
        panelOfRicercaResoult = new JPanel();
        panelOfRicercaResoult.setBackground(PaletteColori.lightModeColorBG);
        
        // Lista di tag disponibile
        List<String> tags = new ArrayList<String>();
        TagsDAO tags_DAO = new TagsDAO();
        tags.add("Nessuno");
        tags.addAll(tags_DAO.SelAllTags_String());
        
        // Campo di testo per la barra di ricerca
        searchText = new JTextArea();
        searchText.setPreferredSize(new Dimension(0, 20));
        
        // ComboBox per selezionare i tag
        tagsBox = new JComboBox(tags.toArray(new String[0]));
        
        // Pannello per i risultati della ricerca
        resoultRicercaArea = new JPanel();
        resoultRicercaArea.setBackground(PaletteColori.lightModeColorBG);
        resoultRicercaArea.setLayout(new BoxLayout(resoultRicercaArea, BoxLayout.Y_AXIS));
        
        // ScrollPane per i risultati della ricerca
        scrollRicercaResoult = new JScrollPane(resoultRicercaArea);
        scrollRicercaResoult.setBackground(PaletteColori.lightModeColorBG);
        scrollRicercaResoult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollRicercaResoult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Aggiunta dello ScrollPane al pannello dei risultati
        panelOfRicercaResoult.add(scrollRicercaResoult, BorderLayout.CENTER);
        
        // Lista per memorizzare i risultati della ricerca
        List<JPanel> listaDellaRicerca = new ArrayList<>();
      
        // Bottone di ricerca
        JButton ricercaButton = new JButton("🔍");
        ricercaButton.setToolTipText("Cerca");
		ricercaButton.setForeground(PaletteColori.blueColor);
		ricercaButton.setBackground(PaletteColori.lightModeColorBG);
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Quando si clicca il bottone, pulisce la barra di ricerca e avvia la ricerca
				searchText.setText("");
				popUpMenu.setVisible(false);
				CreazioneResultDaTag(listaDellaRicerca, nomeUtente);
			}
		});

        // Area per i risultati della barra di ricerca
		resoultArea = new JPanel();
		resoultArea.setBackground(PaletteColori.lightModeColorBG);
		resoultArea.setLayout(new BoxLayout(resoultArea, BoxLayout.Y_AXIS));
		
		scrollResoult = new JScrollPane(resoultArea);
		scrollResoult.setBackground(PaletteColori.lightModeColorBG);
		scrollResoult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollResoult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panelOfSearch.add(scrollResoult, BorderLayout.CENTER);
        
        // Listener per aggiornare il menu popup quando il testo cambia
        searchText.getDocument().addDocumentListener(new DocumentListener() {
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
            	GruppiDAO gruppi_DAO = new GruppiDAO();
            	gruppi = gruppi_DAO.SelAllGruppoFromNome(searchText.getText());
            	
            	// Pulisce l'area dei risultati
            	resoultArea.removeAll();
            	
            	// Verifica se ci sono gruppi corrispondenti
            	if(GruppiIsNotEmpty(gruppi)) {
            		for(int i = 0; i < gruppi.size(); i++) {
            			// Aggiunge ogni gruppo come risultato di ricerca
            			JTextArea textArea = new JTextArea();
            			textArea.setText(gruppi.get(i).GetNome());
            			textArea.setEditable(false);
            			
            			// Aggiungi un MouseListener per mostrare il testo quando si passa sopra
            			textArea.addMouseListener(new MouseAdapter() {
            	                @Override
            	                public void mouseEntered(MouseEvent e) {
            	                	// Cambia il colore dell'area di testo quando il mouse entra
            	                	textAreaSetColor(textArea, PaletteColori.blueColor, PaletteColori.lightModeColorFont);
            	                	textArea.setToolTipText(textArea.getText());
            	                }

            	                @Override
            	                public void mouseExited(MouseEvent e) {
            	                	// Ripristina il colore originale quando il mouse esce
            	                	textAreaSetColor(textArea, PaletteColori.lightModeColorInternalArea, PaletteColori.lightModeColorFont);
            	                	textArea.setToolTipText(null);
            	                }
            	                
            	                @Override
            	                public void mousePressed(MouseEvent e) {
            	                    if (SwingUtilities.isLeftMouseButton(e)) {
            	                    	// Imposta il testo nella barra di ricerca e mostra i risultati dettagliati
            	                    	searchText.setText(textArea.getText());
            	                    	CreazioneResultDaBarraRicerca(textArea.getText(), nomeUtente);
            	                    	popUpMenu.setVisible(false);
            	                    }
            	                }
            	            });
            			resoultArea.add(textArea);
            		}
            		resoultArea.setPreferredSize(new Dimension(200, gruppi.size() * 20));
        			RefreshSearchPopUp();
                	popUpMenu.pack();  // Ridimensiona il JPopupMenu correttamente
                    popUpMenu.show(searchText, 0, searchText.getHeight());
                } else {
                	// Nasconde il menu popup se non ci sono risultati
                	popUpMenu.setVisible(false);
                }
           }
        });
        
        // Aggiunta dei componenti all'area dei parametri di ricerca
        ParametresArea.add(labelSearchText);
        ParametresArea.add(new JScrollPane(searchText));
        ParametresArea.add(labelTagsText);
        JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JScrollPane(tagsBox));
        p.add(ricercaButton);
        ParametresArea.add(p); 
		ParametresArea.setAlignmentX(Component.LEFT_ALIGNMENT);

		contentPane.add(ParametresArea, BorderLayout.NORTH);
        contentPane.add(panelOfRicercaResoult, BorderLayout.CENTER);
    }
    
    // Aggiorna il popup di ricerca
	private void RefreshSearchPopUp() {
		if(resoultArea.getComponentCount() > 15) {
			// Imposta la dimensione massima e usa la barra di scorrimento
			scrollResoult.setPreferredSize(new Dimension(250, 150));
		} else {
			scrollResoult.setSize(resoultArea.getPreferredSize());
		}
		scrollResoult.revalidate();
		scrollResoult.repaint();
		resoultArea.revalidate();
		resoultArea.repaint();
		popUpMenu.revalidate();
		popUpMenu.repaint();
		panelOfSearch.revalidate();
		panelOfSearch.repaint();
	}
	
    // Aggiorna l'area di ricerca
	private void RefreshSearch() {
		resoultRicercaArea.revalidate();
		resoultRicercaArea.repaint();
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
	private JButton CreazioneTastoInterazioneGruppoCercato(String nomeUtente, String nomeGruppo) {
		PartecipanoDAO partecipano_DAO = new PartecipanoDAO(); 
		NotificheRichiesteDAO notifiche_Richieste_DAO = new NotificheRichiesteDAO(); 
		
		JButton button = new JButton();
		
		// Verifica lo stato di partecipazione dell'utente al gruppo e aggiorna il testo del bottone
		if(partecipano_DAO.SelPartecipanoGruppo(nomeGruppo, nomeUtente)) {
			button.setText("🚪");
			button.addActionListener(new ActionListener() {  
				public void actionPerformed(ActionEvent e) {  
					setVisible(false);
					gestioneFinestre.MostraGruppi(nomeUtente, nomeGruppo);
					homeView.setVisible(false);
		        }  
			});
		} else if(notifiche_Richieste_DAO.SelNoitificheRichiesteDiUtenteDiGruppo(nomeGruppo, nomeUtente)) {
			button.setText("⏳");
		} else {
			button.setText("ENTRA");
			button.addActionListener(new ActionListener() {  
				public void actionPerformed(ActionEvent e) {  
					notifiche_Richieste_DAO.InsNotifica_R(nomeGruppo, nomeUtente);
					button.setText("⏳");
		        }  
			});
		}
		return button;
	}
	
    // Crea i risultati di ricerca filtrati per tag
	private void CreazioneResultDaTag(List<JPanel> listaDellaRicerca, String nomeUtente) {
		resoultRicercaArea.removeAll();
		listaDellaRicerca.clear();
		panelOfRicercaResoult.removeAll();
    	
		// Ottiene i gruppi associati al tag selezionato
		List<Possiedono> possiedono = new ArrayList<>();
    	PossiedonoDAO possiedono_DAO = new PossiedonoDAO();
    	possiedono = possiedono_DAO.SelGruppiConTag((String) tagsBox.getSelectedItem());
		
		// Aggiunge ogni gruppo come risultato di ricerca
		for(int i = 0; i < possiedono.size(); i++) {
    		listaDellaRicerca.add(creazionePanelPerResultRicerca(possiedono.get(i).nomeGruppo, nomeUtente));
    		resoultRicercaArea.add(listaDellaRicerca.get(i));
    		panelOfRicercaResoult.add(resoultRicercaArea);				
		}
		RefreshSearch();
	}
	
    // Crea i risultati di ricerca dalla barra di ricerca
	private void CreazioneResultDaBarraRicerca(String nomeGruppo, String nomeUtente) {
		resoultRicercaArea.removeAll();
		panelOfRicercaResoult.removeAll();
		resoultRicercaArea.add(creazionePanelPerResultRicerca(nomeGruppo, nomeUtente));
		panelOfRicercaResoult.add(resoultRicercaArea);					
		RefreshSearch();
	}
	
    // Crea un pannello per ogni risultato di ricerca
	private JPanel creazionePanelPerResultRicerca(String nomeGruppo, String nomeUtente) {
		JPanel panel = new JPanel();
		panel.setBackground(PaletteColori.lightModeColorInternalArea);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		// Aggiunge il nome del gruppo e il bottone di interazione
		panel.add(new JLabel(nomeGruppo + "  "));
		panel.add(CreazioneTastoInterazioneGruppoCercato(nomeUtente, nomeGruppo));
		
		return panel;
	}
}