package UninaSocialGroup;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class Notifiche_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
	
    private JPanel contentPane;
    private JPanel contentPaneForContent;
    private JScrollPane scrollPane;
    Notifiche_Gruppi_DAO notificheDAOG = new Notifiche_Gruppi_DAO();
    Notifiche_Richieste_DAO notificheDAOR = new Notifiche_Richieste_DAO();
  
    
    private int contentHeight = 0;
    
    public Notifiche_GUI(String NU) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //737 484
        setBounds(100, 100, 720, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel NomeGruppo = new JLabel("Notifiche");
        NomeGruppo.setForeground(new Color(0, 128, 255));
        NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
        

        JButton Indietro = new JButton("◀️");
        Indietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Notifiche_GUI.this.setVisible(false);
                Gestione_Finestre N = new Gestione_Finestre();
                N.AccessoHome(NU);
            }
        });
        Indietro.setForeground(new Color(0, 128, 255));
        Indietro.setFont(new Font("Dialog", Font.PLAIN, 16));
        Indietro.setBackground(Color.WHITE);
        
        
        JTabbedPane Sezioni_Notifiche = new JTabbedPane(JTabbedPane.TOP);
        Sezioni_Notifiche.setForeground(new Color(0, 128, 255));
        Sezioni_Notifiche.setBackground(new Color(255, 255, 255));

        // Pannello Gruppi
        JPanel panelGruppi = new JPanel(new BorderLayout());
        panelGruppi.setBackground(new Color(255, 255, 255));
        Sezioni_Notifiche.addTab("Gruppi", null, panelGruppi, null);

        // Pannello Richieste
        JPanel panelRichieste = new JPanel();
        panelRichieste.setBackground(new Color(255, 255, 255));
        Sezioni_Notifiche.addTab("Richieste", null, panelRichieste, null);
        
        
        // Pannello Archiviati
        JPanel panelArchiviati = new JPanel();
        panelArchiviati.setBackground(new Color(255, 255, 255));
        Sezioni_Notifiche.addTab("Archiviati", null, panelArchiviati, null);
        
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(22)
        			.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addGap(123)
        			.addComponent(NomeGruppo, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
        			.addGap(344))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(35)
        			.addComponent(Sezioni_Notifiche, GroupLayout.DEFAULT_SIZE, 661, GroupLayout.DEFAULT_SIZE))
        );
        
        
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(17)
        					.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(5)
        					.addComponent(NomeGruppo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
        			.addGap(39)
        			.addComponent(Sezioni_Notifiche, GroupLayout.DEFAULT_SIZE, 308, GroupLayout.DEFAULT_SIZE))
        );
        contentPane.setLayout(gl_contentPane);

      //griglia per Notifiche gruppi e contenuti: 
        
        JPanel contentPaneForContent = new JPanel();
        contentPaneForContent.setBackground(new Color(255, 255, 255));
        contentPaneForContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForContent.setLayout(new BoxLayout(contentPaneForContent, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(NomeGruppo);
        contentPane.add(Indietro);
     
        
        ArrayList<JTextArea> notificheLabel = new ArrayList<>();
        List<Notifiche> notifiche = notificheDAOG.SelNotifiche(NU);   //list per la gestione delle notifiche gruppi
        List<Notifiche> notifiche2 = notificheDAOG.SelNotifiche(NU);
        
        int dim = notifiche.size(); //dimensione utilizzata per generare le notifiche
        
        // Imposta una dimensione preferita sufficiente per contenere tutte le JTextArea
        contentPaneForContent.setPreferredSize(new Dimension(700, dim * 65)); // 50 è un'altezza approssimativa per ogni JTextArea
        
        JScrollPane scrollPane = new JScrollPane(contentPaneForContent);
        scrollPane.setEnabled(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        try {
        	
        	JTextArea textArea=new JTextArea();  //textArea per la visualizzazione delle notifiche
        	
            if (notifiche != null) {             //controllo per la list notifiche che non sia vuota 
            	int i=0;
            	
            	
                for (Notifiche notifica : notifiche2) {
                	
                	 textArea.append( "N: " + i + "\n" +        //aggiungi notifica alla textArea  
                                                       notifica.getTesto() + "\n" +
                                                       notifica.getData_Notifica() + "\n" +
                                                       "Gruppo: " + notifica.getNome_Gruppo() + "\n");
                                                        i++;
                     System.out.println("Gruppi: " + dim);                  //numero di stampe
                   
                
            }
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    contentPaneForContent.add(textArea);
                    contentPaneForContent.add(Box.createRigidArea(new Dimension(0, 10))); // Aggiungi spazio tra le notifiche
            
            } else {
            	 textArea = new JTextArea("Nessuna notifica trovata per l'utente specificato.");
                contentPaneForContent.add(textArea);                   //Se non trova notifiche nel db con il NomeUtente inserito
            }
            
            
            
        } catch (Exception e1) {
            System.out.println("errore");
        }

        JPanel contentPaneForArchiviati = new JPanel();
        contentPaneForArchiviati.setBackground(new Color(255, 255, 255));
        contentPaneForArchiviati.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForArchiviati.setLayout(new BoxLayout(contentPaneForArchiviati, BoxLayout.Y_AXIS));

        JScrollPane scrollPaneArchiviati = new JScrollPane(contentPaneForArchiviati);
        scrollPaneArchiviati.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneArchiviati.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneArchiviati.setBorder(new EmptyBorder(5, 5, 5, 5));
        GroupLayout gl_panelArchiviati = new GroupLayout(panelArchiviati);
        gl_panelArchiviati.setHorizontalGroup(
        	gl_panelArchiviati.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPaneArchiviati, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        gl_panelArchiviati.setVerticalGroup(
        	gl_panelArchiviati.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPaneArchiviati, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );
        panelArchiviati.setLayout(gl_panelArchiviati);

		revalidate();
		repaint();

        ArrayList<JTextArea> archiviatiLabel = new ArrayList<>();
        
        List<Notifiche> notificheA = notificheDAOR.SelNoitificheArchiviate(NU); //list per le notifiche archiviate
        
        int dimArchiviati= notificheA.size();
        
        JPanel contentPaneForRichieste = new JPanel();
        contentPaneForRichieste.setBackground(new Color(255, 255, 255));
        contentPaneForRichieste.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForRichieste.setLayout(new BoxLayout(contentPaneForRichieste, BoxLayout.Y_AXIS));
 
        
        JScrollPane scrollPaneRichieste = new JScrollPane(contentPaneForRichieste);
        scrollPaneRichieste.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRichieste.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRichieste.setBorder(new EmptyBorder(5, 5, 5, 5));
        GroupLayout groupLayout = new GroupLayout(panelRichieste);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPaneRichieste, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPaneRichieste, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );
        panelRichieste.setLayout(groupLayout);
     
        
		revalidate();
		repaint();

        
        
         //Visualizzazione Notifiche archiviate
         try {
        	
        	JTextArea textAreaArchiviati=new JTextArea();  //textArea per la visualizzazione delle notifiche
        	
            if (notifiche != null) {             //controllo per la list notifiche che non sia vuota 
            	int i=0;
            	
            	
                for (Notifiche notifica : notificheA) {
                	
                	textAreaArchiviati.append( "N: " + i + "\n" +        //aggiungi notifica alla textArea  
                                                       notifica.getTesto() + "\n" +
                                                       notifica.getData_Notifica() + "\n" +
                                                       "Gruppo: " + notifica.getNome_Gruppo() + "\n"
                                                        );
                                                        i++;
                     System.out.println("Archiviati: " + dimArchiviati);                  //numero di stampe
                   
                
            }
                textAreaArchiviati.setLineWrap(true);
                textAreaArchiviati.setWrapStyleWord(true);
                textAreaArchiviati.setEditable(false);
                contentPaneForArchiviati.add(textAreaArchiviati);
                contentPaneForArchiviati.add(Box.createRigidArea(new Dimension(0, 10))); // Aggiungi spazio tra le notifiche
            
            } else {
            	textAreaArchiviati = new JTextArea("Nessuna notifica trovata per l'utente specificato.");
            	contentPaneForArchiviati.add(textAreaArchiviati);                   //Se non trova notifiche nel db con il NomeUtente inserito
            }
            
        } catch (Exception e1) {
            System.out.println("errore");
        }
        
        
      // Visualizzazione Notifiche Richieste 
         List<Notifiche> notificheRichieste = notificheDAOR.SelNoitificheRichiesteDiUnCreatore(NU);

         int DimRichieste = notificheRichieste.size();

         ArrayList<JPannelloRichieste> TestiLabel = new ArrayList<>();
         Gestione_Finestre RecuperaGruppo = new Gestione_Finestre();

         try {
        	 System.out.println("Richieste: " + DimRichieste);
             for (int i = 0; i < DimRichieste; i++) {
            	 
            	 System.out.println("Richieste: " + DimRichieste);
            	 
                 Notifiche notifica = notificheRichieste.get(i);
                 JPannelloRichieste NotPanel = new JPannelloRichieste(NU, "Fantacalcio");

                 NotPanel.NotificationText.append(notifica.getTesto());

                 NotPanel.NotificationText.setWrapStyleWord(false);
                 NotPanel.NotificationText.setEditable(false);
                 TestiLabel.add(NotPanel);

                 NotPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                 NotPanel.add(TestiLabel.get(i));

                 contentPaneForRichieste.add(NotPanel);
                
             }
         } catch (Exception e1) {
             System.out.println("Errore: " + e1.getMessage());
         }
        
       // Aggiungi un listener per impostare le dimensioni del JScrollPane dopo che il frame Ã¨ visibile
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            	
            	//Posiziona correttamente le notifiche
            	for (int i = 0; i < dim; i++) {
                    if(i==0) {
                    	notificheLabel.get(i).setBounds(129, (int) (notificheLabel.get(i-1).getBounds().getY() + notificheLabel.get(i-1).getPreferredSize().getHeight() + 10 ), (int) notificheLabel.get(i).getPreferredSize().width,  notificheLabel.get(i).getPreferredSize().height); 
                    }else {
                    	// Imposta le dimensioni desiderate
                    	notificheLabel.get(i).setBounds(129, 100 , (int) notificheLabel.get(i).getPreferredSize().width, notificheLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
                    }        
                }
                
                
                //crea la giusta dimensione per ContentPaneForContent che permette di fare lo scrollbar delle giuste dimensioni 
        		contentHeight = 0;
            	for(int i = 0; i<dim; i++) {
                    contentHeight += (notificheLabel.get(i).getBounds().getHeight() + 10 );
            	}
                // Aggiorna la dimensione preferita del contenitore in base all'effettiva altezza di tutti gli elementi
                contentPaneForContent.setPreferredSize(new Dimension(scrollPane.getWidth()-27, contentHeight));
                
                contentPaneForContent.revalidate();
                contentPaneForContent.repaint();
                
                
            contentPane.revalidate();
            contentPane.repaint();
            
            }
            
         /*   addComponentListener(new ComponentAdapter() {
    			@Override
    			public void componentResized(ComponentEvent e) {

    				int contentHeight = 0;
    				// Posiziona correttamente le notifiche
    				for (int i = 0; i < numbOfPosts; i++) {
    					if (i == 0) {

    						TestiLabel.get(i).setBounds(10, 10, (int) TestiLabel.get(i).getPreferredSize().width,
    								TestiLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
    					} else {
    						System.out.println(TestiLabel.get(i).getBounds());
    						TestiLabel.get(i).setBounds(10,
    								(int) (TestiLabel.get(i - 1).getBounds().getY()
    										+ TestiLabel.get(i - 1).getPreferredSize().getHeight() + 10),
    								(int) TestiLabel.get(i).getPreferredSize().width,
    								TestiLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate

    					}
    					// crea la giusta dimensione per ContentPaneForContent che permette di fare lo
    					// scrollbar delle giuste dimensioni
    					contentHeight += (TestiLabel.get(i).getHeight() + 10);

    					// Colori
    					TestiLabel.get(i).setColors(AcctualtColorInternalArea, AcctualColorFont);
    				}

    				// Aggiorna la dimensione preferita del contenitore in base all'effettiva
    				// altezza di tutti gli elementi
    				postsArea.setPreferredSize(new Dimension(PostsScrollPane.getWidth() - 27, contentHeight));

    				postsArea.revalidate();
    				postsArea.repaint();

    				PostsScrollPane.revalidate();
    				PostsScrollPane.repaint();

    				contentPane.revalidate();
    				contentPane.repaint();
    			}

    	
    		*/
        
        }); 
        
        
        panelGruppi.add(scrollPane, BorderLayout.CENTER);
       
        revalidate();
        repaint();
        

    }
}