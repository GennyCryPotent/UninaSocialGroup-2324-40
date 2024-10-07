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
	

public class Notifiche_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    

    private JPanel contentPane;
    private JPanel contentPaneForContent;
    private JPanel contentPaneForRichieste;
    private JPanel contentPaneForArchiviati;
    private Notifiche_Gruppi_DAO notificheDAOG = new Notifiche_Gruppi_DAO();
    private Notifiche_Richieste_DAO notificheDAOR = new Notifiche_Richieste_DAO();
    private NotificheController NC = new NotificheController(Notifiche_GUI.this);

    public Notifiche_GUI(String NU) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 420);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel NomeGruppo = new JLabel("Notifiche");
        NomeGruppo.setForeground(new Color(0, 128, 255));
        NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton Indietro = new JButton("◀️");
		Indietro.setBorderPainted(false);
        Indietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	NC.ActionInditero(NU);
            }
        });
        Indietro.setBackground(new Color(255, 255, 255));
        Indietro.setForeground(new Color(0, 128, 255));
        Indietro.setFont(new Font(null, Font.PLAIN, 18));

        JTabbedPane Sezioni_Notifiche = new JTabbedPane(JTabbedPane.TOP);
        Sezioni_Notifiche.setForeground(new Color(0, 128, 255));
        Sezioni_Notifiche.setBackground(Color.WHITE);

        // Pannello Gruppi
        JPanel panelGruppi = new JPanel(new BorderLayout());
        panelGruppi.setBackground(Color.WHITE);
        Sezioni_Notifiche.addTab("Gruppi", null, panelGruppi, null);

        // Pannello Richieste
        JPanel panelRichieste = new JPanel(new BorderLayout());
        panelRichieste.setBackground(Color.WHITE);
        Sezioni_Notifiche.addTab("Richieste", null, panelRichieste, null);

        // Pannello Archiviati
        JPanel panelArchiviati = new JPanel(new BorderLayout());
        panelArchiviati.setBackground(Color.WHITE);
        Sezioni_Notifiche.addTab("Archiviati", null, panelArchiviati, null);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(22)
        			.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(NomeGruppo, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
        			.addGap(449))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(35)
        			.addComponent(Sezioni_Notifiche, GroupLayout.DEFAULT_SIZE, 661, GroupLayout.DEFAULT_SIZE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(17)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
        				.addComponent(NomeGruppo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(Sezioni_Notifiche, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        //inizializzo le diverse richieste
        initializeGruppiTab(panelGruppi, NU);
        initializeRichiesteTab(panelRichieste, NU);
        initializeArchiviatiTab(panelArchiviati, NU);
    }

    //notifiche generali
    private void initializeGruppiTab(JPanel panelGruppi, String NU) {
        contentPaneForContent = new JPanel();
        contentPaneForContent.setBackground(Color.WHITE);
        contentPaneForContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForContent.setLayout(new BoxLayout(contentPaneForContent, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(contentPaneForContent);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        panelGruppi.add(scrollPane, BorderLayout.CENTER);
        
        List<Notifiche> notificheGruppi = notificheDAOG.SelNotifiche(NU);
        
        if (notificheGruppi != null && !notificheGruppi.isEmpty()) {
            // Ordina le notifiche per data con i comparetor e collectionsSort
            Collections.sort(notificheGruppi, new Comparator<Notifiche>() {
                public int compare(Notifiche n1, Notifiche n2) {
                    return n2.getData_Notifica().compareTo(n1.getData_Notifica());
                }
            });
        
        if (notificheGruppi != null && !notificheGruppi.isEmpty()) {
            for (Notifiche notifica : notificheGruppi) {
                JTextArea textArea = new JTextArea("N: " + notificheGruppi.indexOf(notifica) + "\n" +
                        notifica.getTesto() + "\n" +
                        notifica.getData_Notifica() + "\n" +
                        "Gruppo: " + notifica.getNome_Gruppo() + "\n");
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
    private void initializeRichiesteTab(JPanel panelRichieste, String NU) {
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

        List<Notifiche> notificheRichieste = notificheDAOR.SelNoitificheRichiesteDiUnCreatore(NU);
        
        System.out.println("Notifiche R trovate: " + notificheRichieste.size());
        
        if (notificheRichieste != null && !notificheRichieste.isEmpty()) {
        	System.out.println("Notifiche trovate: " + notificheRichieste.size());
            for (Notifiche notifica : notificheRichieste) {
            
            	System.out.println("Notifiche trovate: " + notificheRichieste.size());
                JPannelloRichieste notPanel = new JPannelloRichieste(NU, notifica.getNome_Gruppo(), notifica); //Passa la singola notifica e il nome del gruppo
                notPanel.NotificationText.setWrapStyleWord(true);
                notPanel.NotificationText.setEditable(false);
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
    private void initializeArchiviatiTab(JPanel panelArchiviati, String NU) {
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
        List<Notifiche> notificheArchiviati = notificheDAOR.SelNoitificheArchiviate(NU);

        if (notificheArchiviati != null && !notificheArchiviati.isEmpty()) {
        	// Ordina le notifiche per data con i comparetor e collectionsSort
            Collections.sort(notificheArchiviati, new Comparator<Notifiche>() {
                public int compare(Notifiche n1, Notifiche n2) {
                    return n2.getData_Notifica().compareTo(n1.getData_Notifica());
                }
            });

            for (Notifiche notifica : notificheArchiviati) {
                JTextArea textArea = new JTextArea("N: " + notificheArchiviati.indexOf(notifica) + "\n" +
                        notifica.getTesto() + "\n" +
                        notifica.getData_Notifica() + "\n" +
                        "Gruppo: " + notifica.getNome_Gruppo() + "\n");
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
