package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CreaGruppo_GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NGruppo;
	private JTextField Descrizione;
	private HomeController HC = new HomeController(CreaGruppo_GUI.this);

	
	private JPanel centerPane;
	private JPanel southPane;
	
	private JPanel ResoultArea;
	private JScrollPane ScrollResoult;
    private JPanel ResoultRicercaArea;
	
	private String NU;

    private Color lightModeColorBG = new Color(255, 255, 255);
    private Color lightModeColorButton = new Color(255, 255, 255);
    private Color lightModeColorFont = new Color(0, 0, 0);
    private Color lightModeColorInternalArea = new Color(244, 244, 244);
    private Color blueColor = new Color(0, 128, 255);
    private Color darkModeColorFont = new Color(255, 255, 255);
    
    private JPopupMenu popUpMenu;

    private JPanel panelOfSearch;
    private JTextArea SearchText;
    
	/**
	 * Create the frame.
	 */
	public CreaGruppo_GUI(String NU) {
		
		this.NU = NU;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 431, 300);
		contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
		
		setContentPane(contentPane);
		
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane ,BoxLayout.Y_AXIS));
		
		southPane = new JPanel();
		southPane.setLayout(new BoxLayout(southPane ,BoxLayout.X_AXIS));
		
		
		contentPane.add(centerPane, BorderLayout.CENTER);
		contentPane.add(southPane, BorderLayout.SOUTH);
		
		
		JLabel lblCreaGruppo = new JLabel("Crea gruppo");
		lblCreaGruppo.setForeground(new Color(0, 128, 255));
		lblCreaGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreaGruppo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblCreaGruppo.setBackground(Color.WHITE);
		
		
        popUpMenu = new JPopupMenu();
        popUpMenu.setFocusable(false);
        panelOfSearch = new JPanel();
        panelOfSearch.setBackground(lightModeColorInternalArea);

        popUpMenu.add(panelOfSearch);


		NGruppo = new JTextField();
		NGruppo.setColumns(10);
		
		Descrizione = new JTextField();
		Descrizione.setColumns(10);
		
		JLabel NomeGLabel = new JLabel("Nome:");
		
		JLabel lblDescrizione = new JLabel("Descrizione:");

		
	    
		
		JButton btnCrea = new JButton("Crea");
		
		
		
		
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
			   HC.ActionCreaGruppo(NGruppo, Descrizione, NU, home);
			   home.setVisible(false);
			}
		});
		btnCrea.setForeground(new Color(0, 128, 255));
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaGruppo_GUI.this.setVisible(false);
			}
		});

		
		contentPane.add(lblCreaGruppo, BorderLayout.NORTH);
		
		centerPane.add(NomeGLabel);
		
		centerPane.add(NGruppo);
		
		centerPane.add(lblDescrizione);

		centerPane.add(Descrizione);

		southPane.add(btnCrea);		
		southPane.add(btnAnnulla);


		
		
		
		
		
		
		
		
		
		/*
		
		
		
		//Barra Tag

		ResoultArea = new JPanel();
		ResoultArea.setBackground(lightModeColorBG);
		ResoultArea.setLayout(new BoxLayout(ResoultArea, BoxLayout.Y_AXIS));
		
	    ScrollResoult = new JScrollPane(ResoultArea);
	    ScrollResoult.setBackground(lightModeColorBG);
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
	        	
	            List<String> tags = new ArrayList<String>();
	            
	    		Tags_DAO tags_DAO = new Tags_DAO();
	        	
	    		tags.addAll(tags_DAO.SelAllTags_String());
	        	
				
	        	ResoultArea.removeAll();
	        	
	        	
				for(int i = 0; i< tags.size(); i++) {
					
					JTextArea textArea = new JTextArea();
					
					//System.out.println(gruppi.get(i).getNome());
	    				textArea.setText(tags.get(i));
	   
	    				textArea.setEditable(false);
	    				
	    				// Aggiungi il MouseListener per mostrare il testo quando si passa sopra
					textArea.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseEntered(MouseEvent e) {
		                	textAreaSetColor(textArea,blueColor, darkModeColorFont);
		                    // Puoi usare un JToolTip personalizzato
		                	textArea.setToolTipText(textArea.getText());
		                }

		                @Override
		                public void mouseExited(MouseEvent e) {
		                	textAreaSetColor(textArea,lightModeColorInternalArea, lightModeColorFont);
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
				

				ResoultArea.add(textArea);
				
				}
		

		ResoultArea.setPreferredSize(new Dimension(200, gruppi.size() * 20));
				
		RefreshSearchPopUp();
		popUpMenu.pack();  // Questo forzerà il JPopupMenu a ridimensionarsi correttamente.

	    popUpMenu.show(SearchText, 0, SearchText.getHeight());
	    
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
	    
	*/
	}
	
	
	
	


	
	
	
/*
	private void CreazioneResultDaBarraRicerca(String Nome_Gruppo, String NU) {
	
		ResoultRicercaArea.removeAll();
		panelOfRicercaResoult.removeAll();
	
		ResoultRicercaArea.add(creazionePanelPerResultRicerca(Nome_Gruppo, NU));
		panelOfRicercaResoult.add(ResoultRicercaArea);				
					
		RefreshSearch();
		
	}
		
	
	private JPanel creazionePanelPerResultRicerca(String Nome_Gruppo, String NU) {
		JPanel panel = new JPanel();
		panel.setBackground(lightModeColorInternalArea);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		panel.add(new JLabel(Nome_Gruppo + "  "));
		panel.add(CreazioneTastoInterazioneGruppoCercato(NU, Nome_Gruppo));
		
		return panel;
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
	
	
    
	private void RefreshSearchPopUp() {
		
		//ResoultArea.setPreferredSize(new Dimension(0,0));
		//panelOfSearch.setPreferredSize(new Dimension(100,200));
		//max size in that case use the scrollbar
		
		ScrollResoult.setPreferredSize(new Dimension(250,150));
		
		ScrollResoult.revalidate();
		ScrollResoult.repaint();
		
		
		ResoultArea.revalidate();
		ResoultArea.repaint();
		
		popUpMenu.revalidate();
		popUpMenu.repaint();
		
		panelOfSearch.revalidate();
		panelOfSearch.repaint();
	}
	
	
	
	private void textAreaSetColor(JTextArea textArea, Color ColorInternalArea, Color ColorFont ) {
    	textArea.setBackground(ColorInternalArea);
    	textArea.setForeground(ColorFont);
	}
	
*/}
