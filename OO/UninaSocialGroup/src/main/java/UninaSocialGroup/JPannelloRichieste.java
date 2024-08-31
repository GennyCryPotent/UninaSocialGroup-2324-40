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

public class JPannelloRichieste extends JPanel {
    private Notifiche_Richieste_DAO Esitato = new Notifiche_Richieste_DAO();
    private JPanel secondPanel = new JPanel();
    private JPanel thirdPanel = new JPanel();
    public JTextArea NotificationText = new JTextArea();
    public JTextArea GroupText = new JTextArea();
    JButton Accetta = new JButton("Accetta");
    JButton Rifiuta = new JButton("Rifiuta");

    public JPannelloRichieste(String NU, String NG) {
        super(new BorderLayout());

        List<Notifiche> notificheR = Esitato.SelNoitificheRichiesteDiUnCreatore(NU);

        if (notificheR != null) {
            for (Notifiche notifica : notificheR) {
                NotificationText.append(notifica.getTesto() + "\n");
                GroupText.append(notifica.getNome_Gruppo() + "\n");
            }
        } else {
            NotificationText.append("Nessuna notifica trovata.\n");
        }

        Accetta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Esitato.Accetta_Richiesta(NG, NU);
            }
        });
        Accetta.setBackground(new Color(255, 255, 255));
        Accetta.setContentAreaFilled(false);
        Accetta.setBorderPainted(false);

        Rifiuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Esitato.Rifiuta_Richiesta(NG, NU);
            }
        });
        Rifiuta.setBackground(new Color(255, 255, 255));
        Rifiuta.setContentAreaFilled(false);
        Rifiuta.setBorderPainted(false);
        Rifiuta.setForeground(new Color(0, 0, 0));

        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
        secondPanel.add(Accetta);
        secondPanel.add(Rifiuta);

        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
        NotificationText.setEditable(false);
        thirdPanel.add(NotificationText);
        GroupText.setEditable(false);
        thirdPanel.add(GroupText);

        this.add(thirdPanel, BorderLayout.NORTH);
        this.add(secondPanel, BorderLayout.SOUTH);
    }
}

