package UninaSocialGroup;

import java.awt.*;
import java.util.List;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.util.Comparator;
import javax.swing.LayoutStyle.ComponentPlacement;
	

public class NotificheGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    

    private JPanel contentPane;
    private JPanel contentPaneForContent;
    private JPanel contentPaneForRichieste;
    private JPanel contentPaneForArchiviati;
    private NotificheGruppiDAO notificheDAOG = new NotificheGruppiDAO();
    private NotificheRichiesteDAO notificheDAOR = new NotificheRichiesteDAO();
    private NotificheController notificheController = new NotificheController(NotificheGUI.this);
    
    public NotificheGUI(String nomeUtente) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 420);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNomeGruppo = new JLabel("Notifiche");
        lblNomeGruppo.setForeground(PaletteColori.blueColor);
        lblNomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton indietroButton = new JButton("◀️");
        indietroButton.setToolTipText("Torna alla Home");
		indietroButton.setBorderPainted(false);
        indietroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	notificheController.ActionInditero(nomeUtente);
            }
        });
        indietroButton.setBackground(PaletteColori.lightModeColorBG);
        indietroButton.setForeground(PaletteColori.blueColor);
        indietroButton.setFont(new Font(null, Font.PLAIN, 18));

        JTabbedPane sezioniNotifiche = new JTabbedPane(JTabbedPane.TOP);
        sezioniNotifiche.setForeground(PaletteColori.blueColor);
        sezioniNotifiche.setBackground(Color.WHITE);

        // Pannello Gruppi
        JPanel panelGruppi = new JPanel(new BorderLayout());
        panelGruppi.setBackground(Color.WHITE);
        sezioniNotifiche.addTab("Gruppi", null, panelGruppi, null);

        // Pannello Richieste
        JPanel panelRichieste = new JPanel(new BorderLayout());
        panelRichieste.setBackground(Color.WHITE);
        sezioniNotifiche.addTab("Richieste", null, panelRichieste, null);

        // Pannello Archiviati
        JPanel panelArchiviati = new JPanel(new BorderLayout());
        panelArchiviati.setBackground(Color.WHITE);
        sezioniNotifiche.addTab("Archiviati", null, panelArchiviati, null);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(22)
        			.addComponent(indietroButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(lblNomeGruppo, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
        			.addGap(449))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(35)
        			.addComponent(sezioniNotifiche, GroupLayout.DEFAULT_SIZE, 661, GroupLayout.DEFAULT_SIZE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(17)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(indietroButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNomeGruppo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(sezioniNotifiche, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        //inizializzo le diverse richieste
        initializeGruppiTab(panelGruppi, nomeUtente);
        initializeRichiesteTab(panelRichieste, nomeUtente);
        initializeArchiviatiTab(panelArchiviati, nomeUtente);
    }

    //notifiche generali
    private void initializeGruppiTab(JPanel panelGruppi, String nomeUtente) {
        contentPaneForContent = new JPanel();
        contentPaneForContent.setBackground(Color.WHITE);
        contentPaneForContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForContent.setLayout(new BoxLayout(contentPaneForContent, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(contentPaneForContent);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        panelGruppi.add(scrollPane, BorderLayout.CENTER);
        
        List<Notifiche> notificheGruppi = notificheDAOG.SelNotifiche(nomeUtente);
        
        if (notificheGruppi != null && !notificheGruppi.isEmpty()) {
            // Ordina le notifiche per data con i comparetor e collectionsSort
            Collections.sort(notificheGruppi, new Comparator<Notifiche>() {
                public int compare(Notifiche n1, Notifiche n2) {
                    return n2.getDataNotifica().compareTo(n1.getDataNotifica());
                }
            });
        
        if (notificheGruppi != null && !notificheGruppi.isEmpty()) {
            for (Notifiche notifica : notificheGruppi) {
                JTextArea textArea = new JTextArea("N: " + notificheGruppi.indexOf(notifica) + "\n" +
                        notifica.getTesto() + "\n" +
                        notifica.getDataNotifica() + "\n" +
                        "Gruppo: " + notifica.getNomeGruppo() + "\n");
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                contentPaneForContent.add(textArea);
                contentPaneForContent.add(Box.createRigidArea(new Dimension(0, 10)));
               
            }
        } else {
            JTextArea textArea = new JTextArea("Nessuna notifica trovata per l'utente specificato.");
            textArea.setEditable(false);
            contentPaneForContent.add(textArea);
        }
        }
        contentPaneForContent.revalidate();
        contentPaneForContent.repaint();
        
        SwingUtilities.invokeLater(new Runnable() {  // imposta la scrollbar in cima
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }

    //notifiche richieste7
    private void initializeRichiesteTab(JPanel panelRichieste, String nomeUtente) {
        contentPaneForRichieste = new JPanel();
        contentPaneForRichieste.setBackground(Color.WHITE);
        contentPaneForRichieste.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForRichieste.setLayout(new BoxLayout(contentPaneForRichieste, BoxLayout.Y_AXIS));

        JScrollPane scrollPaneRichieste = new JScrollPane(contentPaneForRichieste);
        //scrollPaneRichieste.getVerticalScrollBar().setValue(0);
        scrollPaneRichieste.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRichieste.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRichieste.setBorder(new EmptyBorder(5, 5, 5, 5));

        panelRichieste.add(scrollPaneRichieste, BorderLayout.CENTER);

        List<Notifiche> notificheRichieste = notificheDAOR.SelNoitificheRichiesteDiUnCreatore(nomeUtente);
        
        System.out.println("Notifiche R trovate: " + notificheRichieste.size());
        
        if (notificheRichieste != null && !notificheRichieste.isEmpty()) {
        	System.out.println("Notifiche trovate: " + notificheRichieste.size());
            for (Notifiche notifica : notificheRichieste) {
            
            	System.out.println("Notifiche trovate: " + notificheRichieste.size());
                JPannelloRichieste notPanel = new JPannelloRichieste(nomeUtente, notifica.getNomeGruppo(), notifica); //Passa la singola notifica e il nome del gruppo
                notPanel.notificationText.setWrapStyleWord(true);
                notPanel.notificationText.setEditable(false);
                contentPaneForRichieste.add(notPanel);
                contentPaneForRichieste.add(Box.createRigidArea(new Dimension(0, 10)));}
            	
        } else {
            JTextArea textArea = new JTextArea("Nessuna richiesta trovata.");
            textArea.setEditable(false);
            contentPaneForRichieste.add(textArea);
        }
        
        contentPaneForRichieste.revalidate();
        contentPaneForRichieste.repaint();
    }
    
   


    //notifiche archiviate
    private void initializeArchiviatiTab(JPanel panelArchiviati, String nomeUtente) {
        contentPaneForArchiviati = new JPanel();
        contentPaneForArchiviati.setBackground(Color.WHITE);
        contentPaneForArchiviati.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForArchiviati.setLayout(new BoxLayout(contentPaneForArchiviati, BoxLayout.Y_AXIS));

        JScrollPane scrollPaneArchiviati = new JScrollPane(contentPaneForArchiviati);
        scrollPaneArchiviati.getVerticalScrollBar().setValue(0);
        scrollPaneArchiviati.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneArchiviati.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneArchiviati.setBorder(new EmptyBorder(5, 5, 5, 5));

        panelArchiviati.add(scrollPaneArchiviati, BorderLayout.CENTER);

        // Fetch notifications (archived notifications)
        List<Notifiche> notificheArchiviati = notificheDAOR.SelNoitificheArchiviate(nomeUtente);

        if (notificheArchiviati != null && !notificheArchiviati.isEmpty()) {
        	// Ordina le notifiche per data con i comparetor e collectionsSort
            Collections.sort(notificheArchiviati, new Comparator<Notifiche>() {
                public int compare(Notifiche n1, Notifiche n2) {
                    return n2.getDataNotifica().compareTo(n1.getDataNotifica());
                }
            });

            for (Notifiche notifica : notificheArchiviati) {
                JTextArea textArea = new JTextArea("N: " + notificheArchiviati.indexOf(notifica) + "\n" +
                        notifica.getTesto() + "\n" +
                        notifica.getDataNotifica() + "\n" +
                        "Gruppo: " + notifica.getNomeGruppo() + "\n");
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                contentPaneForArchiviati.add(textArea);
                contentPaneForArchiviati.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        } else {
            JTextArea textArea = new JTextArea("Nessuna notifica archiviata trovata.");
            textArea.setEditable(false);
            contentPaneForArchiviati.add(textArea);
        }

        contentPaneForArchiviati.revalidate();
        contentPaneForArchiviati.repaint();
        
        SwingUtilities.invokeLater(new Runnable() {  // imposta la scrollbar in cima
            public void run() {
                scrollPaneArchiviati.getVerticalScrollBar().setValue(0);
            }
        });
    }
}
