package UninaSocialGroup;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JPannelloRichieste extends JPanel {
    private NotificheRichiesteDAO esitato = new NotificheRichiesteDAO();
    private JPanel secondPanel = new JPanel();
    private JPanel thirdPanel = new JPanel();
    public JTextArea notificationText = new JTextArea();
    public JTextArea groupText = new JTextArea();
    private JButton accettaButton = new JButton("Accetta");
    private JButton rifiutaButton = new JButton("Rifiuta");
    private NotificheController notificheController = new NotificheController(JPannelloRichieste.this);
    
    private PaletteColori paletteColori;
    
    public JPannelloRichieste(String nomeUtente, String nomeGruppo, Notifiche notifiche) { //Passa la singola notifica
        super(new BorderLayout());

        List<Notifiche> notificheR = esitato.SelNoitificheRichiesteDiUnCreatore(nomeUtente);

        if (notificheR != null) {
            
                notificationText.append(notifiche.getTesto() + "\n"); //prende testo della notifica
            
        } else {
            notificationText.append("Nessuna notifica trovata.\n");
        }
        notificationText.setEditable(false);
        groupText.setEditable(false);
        thirdPanel.setForeground(new Color(192, 192, 192));

        this.add(thirdPanel, BorderLayout.NORTH);
                accettaButton.setForeground(paletteColori.blueColor);
        
                accettaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	notificheController.ActionAccetta(nomeGruppo, notifiche);
                    }
                });
                accettaButton.setBackground(new Color(255, 255, 255));
                
                        rifiutaButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                            	notificheController.ActionRifiuta(nomeGruppo, notifiche);
                            }
                        });
                        rifiutaButton.setBackground(new Color(255, 255, 255));
                        rifiutaButton.setForeground(new Color(0, 128, 255));
                        
                                secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
                                secondPanel.setBounds(new Rectangle(21, 8, 13, 8));
                                secondPanel.add(accettaButton);
                                secondPanel.add(rifiutaButton);
        GroupLayout gl_thirdPanel = new GroupLayout(thirdPanel);
        gl_thirdPanel.setHorizontalGroup(
        	gl_thirdPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_thirdPanel.createSequentialGroup()
        			.addGroup(gl_thirdPanel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(groupText, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        				.addComponent(notificationText, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(secondPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_thirdPanel.setVerticalGroup(
        	gl_thirdPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_thirdPanel.createSequentialGroup()
        			.addGroup(gl_thirdPanel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(secondPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        				.addGroup(gl_thirdPanel.createSequentialGroup()
        					.addComponent(notificationText, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(groupText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18))
        );
        thirdPanel.setLayout(gl_thirdPanel);
    }
}

