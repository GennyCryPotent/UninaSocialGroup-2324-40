package UninaSocialGroup;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class GruppiGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private String newPost;
    private GruppiController gruppiController = new GruppiController(GruppiGUI.this);

    private List<Contenuti> resContenutiGruppi = new ArrayList<Contenuti>();
    private ContenutiDAO contenutiDao = new ContenutiDAO();
    private ArrayList<JPannelloContenuti> contenutiPanel = new ArrayList<>();
    private RegolanoDAO regolanoDAO = new RegolanoDAO();
    private GruppiDAO gruppiDAO = new GruppiDAO();
    private Gruppi gruppo;
    private boolean checkCreatore;
    private boolean checkAmministratore;
    
    private String ruolo;


    // Costruttore della classe GruppiGUI
    public GruppiGUI(String nomeUtente, String ng) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(GruppiGUI.class.getResource("/UninaSocialGroup/image.png")));
    	setTitle("Gruppo");
    	

        // Recupera i dettagli del gruppo dal database
        gruppo = gruppiDAO.SelSigGruppo(ng);
        
        // Controlla se l'utente è un amministratore o il creatore del gruppo
        checkAmministratore = regolanoDAO.CheckRegola(ng, nomeUtente);
        checkCreatore = gruppo.GetCreatore().equals(nomeUtente);

        // IMPOSTAZIONI DEL PANNELLO
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Etichetta per il nome del gruppo
        JLabel nomeGruppo = new JLabel();
        nomeGruppo.setForeground(new Color(0, 128, 255));
        nomeGruppo.setText(ng);
        nomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Pannello per visualizzare i post
        JPanel postsArea = new JPanel();
        postsArea.setBackground(new Color(244, 244, 244));
        postsArea.setBounds(140, 97, 573, 291);
        postsArea.setLayout(null);

        // BOTTONI
        // Bottone Home per tornare alla schermata principale
        JButton home = new JButton("\uD83C\uDFE0");
        home.setToolTipText("Home");
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gruppiController.ActionHome(nomeUtente);
            }
        });
        home.setBorderPainted(false);
        home.setBackground(new Color(255, 255, 255));
        home.setForeground(new Color(0, 128, 255));
        home.setFont(new Font(null, Font.PLAIN, 18));

        // Bottone per aggiungere un nuovo post
        JButton aggiungiPost = new JButton("\u2795");
        aggiungiPost.setToolTipText("Aggiungi post");
        aggiungiPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostra una finestra di dialogo per aggiungere un nuovo post
                newPost = JOptionPane.showInputDialog(aggiungiPost, "Cosa c'\u00e8 di nuovo?", "Aggiungi un post",
                        JOptionPane.QUESTION_MESSAGE);
                
                // Chiama il controller per gestire l'azione di aggiunta del nuovo post
                gruppiController.ActionPost(nomeUtente, ng, newPost);
            }
        });
        aggiungiPost.setBorderPainted(false);
        aggiungiPost.setBackground(new Color(255, 255, 255));
        aggiungiPost.setForeground(new Color(0, 128, 255));
        aggiungiPost.setFont(new Font(null, Font.PLAIN, 18));

        // Bottone per modificare i post
        JButton rimuoviPost = new JButton("\uD83D\uDCDD");
        rimuoviPost.setToolTipText("Modifica post");
        rimuoviPost.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
                // Chiama il controller per gestire la modifica del post
                gruppiController.ActionModifica(nomeUtente, ng);
            }
        });
        rimuoviPost.setBorderPainted(false);
        rimuoviPost.setBackground(new Color(255, 255, 255));
        rimuoviPost.setForeground(new Color(0, 128, 255));
        rimuoviPost.setFont(new Font(null, Font.PLAIN, 20));

        // SCROLLPANE PER CONTENERE TUTTI I POST
        JScrollPane scrollPane = new JScrollPane(postsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setBounds(27, 97, 686, 291);
        scrollPane.setVisible(true);
        contentPane.add(scrollPane);

        // Recupera tutti i post del gruppo
        recuperaPostGruppo(ng, nomeUtente, postsArea, scrollPane);

        JLabel labelRuolo = new JLabel();
        labelRuolo.setVisible(false);
        
        labelRuolo.getText();
        
        // Bottone per rimuovere un partecipante dal gruppo
        JButton eliminaPartecipante = new JButton("\uD83D\uDEB7");
        eliminaPartecipante.setToolTipText("Rimuovi partecipante");
        eliminaPartecipante.setBorderPainted(false);
        eliminaPartecipante.setBackground(new Color(255, 255, 255));
        eliminaPartecipante.setForeground(new Color(0, 128, 255));
        eliminaPartecipante.setFont(new Font(null, Font.PLAIN, 18));
        eliminaPartecipante.setVisible(false);
        eliminaPartecipante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chiama il controller per gestire la rimozione del partecipante
                gruppiController.ActionElimina(nomeUtente, ng, ruolo, 0);
            }
        });
        
        // Bottone per abbandonare il gruppo
        JButton abbandona = new JButton("\u274C");
        abbandona.setToolTipText("Abbandona gruppo");
        abbandona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chiama il controller per gestire l'azione di abbandono del gruppo
                gruppiController.ActionAbbandona(nomeUtente, ng, checkCreatore);
            }
        });
        abbandona.setBorderPainted(false);
        abbandona.setBackground(new Color(255, 255, 255));
        abbandona.setForeground(new Color(0, 128, 255));
        abbandona.setFont(new Font(null, Font.PLAIN, 18));
        
        // Bottone per aggiungere un amministratore
        JButton aggiungiAmm = new JButton("\uD83C\uDD99");
        aggiungiAmm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chiama il controller per gestire l'azione di aggiunta di un amministratore
                gruppiController.ActionElimina(nomeUtente, ng, ruolo, 1);
            }
        });
        aggiungiAmm.setToolTipText("Aggiungi amministratore");
        aggiungiAmm.setForeground(new Color(0, 128, 255));
        aggiungiAmm.setFont(new Font("Dialog", Font.PLAIN, 18));
        aggiungiAmm.setBorderPainted(false);
        aggiungiAmm.setBackground(Color.WHITE);
        aggiungiAmm.setVisible(false);
        
        // Impostazioni di layout per il content pane
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(22)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 749, GroupLayout.DEFAULT_SIZE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(4)
        					.addComponent(home, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(aggiungiPost, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(rimuoviPost, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addGap(23)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(nomeGruppo, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(eliminaPartecipante, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(aggiungiAmm, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(abbandona, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(labelRuolo, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
        					.addContainerGap())))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(17)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nomeGruppo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        				.addComponent(rimuoviPost, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        				.addComponent(aggiungiPost, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        				.addComponent(home, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        				.addComponent(aggiungiAmm, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
        				.addComponent(abbandona, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        				.addComponent(eliminaPartecipante, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
        			.addGap(1)
        			.addComponent(labelRuolo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        // Imposta la visibilità e le proprietà dei componenti in base al ruolo dell'utente
        if (checkCreatore) {
            labelRuolo.setText("Creatore");
            labelRuolo.setVisible(true);
            eliminaPartecipante.setVisible(true);
            eliminaPartecipante.setEnabled(true);
            aggiungiAmm.setVisible(true);
            aggiungiAmm.setEnabled(true);
            ruolo = "Creatore";
        } else if (checkAmministratore) {
            labelRuolo.setText("Amministratore");
            labelRuolo.setVisible(true);
            eliminaPartecipante.setVisible(true);
            eliminaPartecipante.setEnabled(true);
            ruolo = "Amministratore";
        }
        
        // Aggiungi listener per gestire il ridimensionamento dei componenti
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int contentHeight = 0;
                // Posiziona correttamente le notifiche
                for (int i = 0; i < contenutiPanel.size(); i++) {
                    if (i == 0) {
                        // Imposta i confini della prima notifica
                        contenutiPanel.get(i).setBounds(10, 10, (int) contenutiPanel.get(i).getPreferredSize().width,
                                contenutiPanel.get(i).getPreferredSize().height);
                    } else {
                        // Imposta i confini delle notifiche successive in base a quella precedente
                        contenutiPanel.get(i).setBounds(10,
                                (int) (contenutiPanel.get(i - 1).getBounds().getY()
                                        + contenutiPanel.get(i - 1).getPreferredSize().getHeight() + 10),
                                (int) contenutiPanel.get(i).getPreferredSize().width,
                                contenutiPanel.get(i).getPreferredSize().height);
                    }
                    // Calcola l'altezza totale per l'area di contenuto scrollabile
                    contentHeight += (contenutiPanel.get(i).getHeight() + 10);
                }

                // Aggiorna la dimensione preferita del contenitore in base all'altezza di tutti gli elementi
                postsArea.setPreferredSize(new Dimension(scrollPane.getWidth() - 27, contentHeight));

                // Riconvalida e ridisegna per riflettere i cambiamenti
                postsArea.revalidate();
                postsArea.repaint();

                scrollPane.revalidate();
                scrollPane.repaint();

                contentPane.revalidate();
                contentPane.repaint();
            }

        });

    }

    // Funzione per recuperare i post del gruppo
    private void recuperaPostGruppo(String ng, String nomeUtente, JPanel postsArea, JScrollPane scrollPane) {
        // Ottieni tutti i post relativi al gruppo dal database
        resContenutiGruppi = contenutiDao.SelAllContenutiGruppo(ng);

        LikesDAO likesDAO = new LikesDAO();
        CommentiDAO commentiDAO = new CommentiDAO();

        int numbOfTxt = resContenutiGruppi.size();

        for (int i = 0; i < numbOfTxt; i++) {
            // Crea un nuovo pannello per ogni post
            JPannelloContenuti newPostPanel = new JPannelloContenuti(resContenutiGruppi.get(i).getPubblicatore(), nomeUtente,
                    resContenutiGruppi.get(i).getNomeGruppo(), resContenutiGruppi.get(i).getTesto(),
                    likesDAO.SelNumLike(resContenutiGruppi.get(i).getIdContenuto()),
                    commentiDAO.SelNumCommenti(resContenutiGruppi.get(i).getIdContenuto()),
                    resContenutiGruppi.get(i).getIdContenuto());

            // Imposta le proprietà per l'area di testo nel pannello del post
            newPostPanel.textArea.setWrapStyleWord(false);
            newPostPanel.textArea.setEditable(false);

            // Aggiungi il pannello del post alla lista dei pannelli
            contenutiPanel.add(newPostPanel);

            // Imposta i colori per il pannello del post
            contenutiPanel.get(i).setColors(PaletteColori.lightModeColorInternalArea, PaletteColori.lightModeColorFont);

            // Aggiungi spaziatura e il pannello del post all'area dei post
            postsArea.add(Box.createRigidArea(new Dimension(0, 10)));
            postsArea.add(contenutiPanel.get(i));
        }
    }
}