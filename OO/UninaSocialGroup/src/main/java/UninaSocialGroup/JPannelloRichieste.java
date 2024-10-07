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
    private Notifiche_Richieste_DAO Esitato = new Notifiche_Richieste_DAO();
    private JPanel secondPanel = new JPanel();
    private JPanel thirdPanel = new JPanel();
    public JTextArea NotificationText = new JTextArea();
    public JTextArea GroupText = new JTextArea();
    private JButton Accetta = new JButton("Accetta");
    private JButton Rifiuta = new JButton("Rifiuta");
    private NotificheController NC = new NotificheController(JPannelloRichieste.this);

    public JPannelloRichieste(String NU, String NG, Notifiche N) { //Passa la singola notifica
        super(new BorderLayout());

        List<Notifiche> notificheR = Esitato.SelNoitificheRichiesteDiUnCreatore(NU);

        if (notificheR != null) {
            
                NotificationText.append(N.getTesto() + "\n"); //prende testo della notifica
            
        } else {
            NotificationText.append("Nessuna notifica trovata.\n");
        }
        NotificationText.setEditable(false);
        GroupText.setEditable(false);
        thirdPanel.setForeground(new Color(192, 192, 192));

        this.add(thirdPanel, BorderLayout.NORTH);
                Accetta.setForeground(new Color(0, 128, 255));
        
                Accetta.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	NC.ActionAccetta(NG, N);
                    }
                });
                Accetta.setBackground(new Color(255, 255, 255));
                
                        Rifiuta.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                            	NC.ActionRifiuta(NG, N);
                            }
                        });
                        Rifiuta.setBackground(new Color(255, 255, 255));
                        Rifiuta.setForeground(new Color(0, 128, 255));
                        
                                secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
                                secondPanel.setBounds(new Rectangle(21, 8, 13, 8));
                                secondPanel.add(Accetta);
                                secondPanel.add(Rifiuta);
        GroupLayout gl_thirdPanel = new GroupLayout(thirdPanel);
        gl_thirdPanel.setHorizontalGroup(
        	gl_thirdPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_thirdPanel.createSequentialGroup()
        			.addGroup(gl_thirdPanel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(GroupText, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        				.addComponent(NotificationText, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
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
        					.addComponent(NotificationText, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(GroupText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18))
        );
        thirdPanel.setLayout(gl_thirdPanel);
    }
}

