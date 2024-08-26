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
        

        JButton Indietro = new JButton("<");
        Indietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Notifiche_GUI.this.setVisible(false);
                Gestione_Finestre N = new Gestione_Finestre();
                N.AccessoHome(null);
            }
        });
        Indietro.setForeground(new Color(0, 128, 255));
        Indietro.setFont(new Font("Arial Black", Font.BOLD, 17));
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


        int numbOfN = 300;

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
        
        // Imposta una dimensione preferita sufficiente per contenere tutte le JTextArea
        contentPaneForContent.setPreferredSize(new Dimension(700, numbOfN * 50)); // 50 Ã¨ un'altezza approssimativa per ogni JTextArea

        JScrollPane scrollPane = new JScrollPane(contentPaneForContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        ArrayList<JTextArea> notificheLabel = new ArrayList<>();
        List<Notifiche> notifiche = notificheDAOG.SelNotificheUtente(NU);
        int dim = notifiche.size();
        for (int i = 0; i < dim ; i++) {
            
        	
        	
        	
        	 if (notifiche != null) {
                 for (Notifiche notifica : notifiche) {
                     JTextArea textArea = new JTextArea("N: " + i + "\n" +
                                                        notifica.getTesto() + "\n" +
                                                        notifica.getData_Notifica() + "\n" +
                                                        "Gruppo: " + notifica.getNome_Gruppo() + "\n" );
                                                        
                     textArea.setLineWrap(true);
                     textArea.setWrapStyleWord(true);
                     textArea.setEditable(false);
                     contentPaneForContent.add(textArea);
                     contentPaneForContent.add(Box.createRigidArea(new Dimension(0, 10))); // Aggiungi spazio tra le notifiche
                 }
             } else {
                 JTextArea textArea = new JTextArea("Nessuna notifica trovata per l'utente specificato.");
             }
                
            
        	
        	
        	JTextArea textArea = new JTextArea("Notifica " + i + ":");
            
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(false);
            textArea.setEditable(false);
            notificheLabel.add(textArea);
            contentPaneForContent.add(textArea);
            contentPaneForContent.add(Box.createRigidArea(new Dimension(0, 10))); // Aggiungi spazio tra le notifiche
        }


       // Aggiungi un listener per impostare le dimensioni del JScrollPane dopo che il frame Ã¨ visibile
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            	
            	//Posiziona correttamente le notifiche
            	for (int i = 0; i < numbOfN; i++) {
                    if(i==0) {
                    	notificheLabel.get(i).setBounds(129, 100 , (int) notificheLabel.get(i).getPreferredSize().width, notificheLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
                    }else {
                    	notificheLabel.get(i).setBounds(129, (int) (notificheLabel.get(i-1).getBounds().getY() + notificheLabel.get(i-1).getPreferredSize().getHeight() + 10 ), (int) notificheLabel.get(i).getPreferredSize().width,  notificheLabel.get(i).getPreferredSize().height); // Imposta le dimensioni desiderate
                    
                    }        
                }
                
                
                //crea la giusta dimensione per ContentPaneForContent che permette di fare lo scrollbar delle giuste dimensioni 
        		contentHeight = 0;
            	for(int i = 0; i<numbOfN; i++) {
                    contentHeight += (notificheLabel.get(i).getBounds().getHeight() + 10 );
            	}
                // Aggiorna la dimensione preferita del contenitore in base all'effettiva altezza di tutti gli elementi
                contentPaneForContent.setPreferredSize(new Dimension(scrollPane.getWidth()-27, contentHeight));
                
                contentPaneForContent.revalidate();
                contentPaneForContent.repaint();

            contentPane.revalidate();
            contentPane.repaint();
            }
        });
        panelGruppi.add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();


    }
}