package UninaSocialGroup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Notifiche_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel contentPaneForContent;
    private JScrollPane scrollPane;
    
    private int contentHeight = 0;
    
    public Notifiche_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //737 484
        setBounds(100, 100, 720, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        int numbOfN = 1000;

        contentPaneForContent = new JPanel();
        contentPaneForContent.setBackground(new Color(255, 255, 255));
        contentPaneForContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneForContent.setLayout(new BoxLayout(contentPaneForContent, BoxLayout.Y_AXIS));

        // Imposta una dimensione preferita sufficiente per contenere tutte le JTextArea
        contentPaneForContent.setPreferredSize(new Dimension(700, numbOfN * 50)); // 50 è un'altezza approssimativa per ogni JTextArea

        scrollPane = new JScrollPane(contentPaneForContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(scrollPane, gbc); // Aggiungi lo JScrollPane con GridBagConstraints

        JLabel NomeGruppo = new JLabel("Notifiche");
        NomeGruppo.setForeground(new Color(0, 128, 255));
        NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        contentPane.add(NomeGruppo, gbc);

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
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPane.add(Indietro, gbc);

        ArrayList<JTextArea> notificheLabel = new ArrayList<>();


        
        //= numbOfN * (notificheLabel.get(0).getPreferredSize().height + 10) ; // Altezza totale
        
        for (int i = 0; i < numbOfN; i++) {
            JTextArea textArea = new JTextArea("Notifica " + i + " : abcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvzabcdefghilmnopqrstuvz");
            
            
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(false);
            textArea.setEditable(false);
            //textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, textArea.getPreferredSize().height));
            notificheLabel.add(textArea);
            contentPaneForContent.add(textArea);
            contentPaneForContent.add(Box.createRigidArea(new Dimension(0, 10))); // Aggiungi spazio tra le notifiche
            //contentHeight = contentHeight + (getWrappedLineCount(notificheLabel.get(i)) + 10);
            //contentHeight += (notificheLabel.get(i).getPreferredSize().height + 10);

            
        }
       

        revalidate();
        repaint();
    

        // Aggiungi un listener per impostare le dimensioni del JScrollPane dopo che il frame è visibile
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
            		System.out.println(contentHeight);
            	}
                // Aggiorna la dimensione preferita del contenitore in base all'effettiva altezza di tutti gli elementi
                contentPaneForContent.setPreferredSize(new Dimension(scrollPane.getWidth()-27, contentHeight));
                
                contentPaneForContent.revalidate();
                contentPaneForContent.repaint();

            contentPane.revalidate();
            contentPane.repaint();
            }
        });

        revalidate();
        repaint();
        

    }
}