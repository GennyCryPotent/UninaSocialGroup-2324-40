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

public class JPannelloRichieste extends JPanel {
	
	private Notifiche_Richieste_DAO Esitato= new Notifiche_Richieste_DAO();

	private JPanel secondPanel = new JPanel();
	private JPanel thirdPanel = new JPanel();
	public JTextArea NotificationText = new JTextArea();
	public JTextArea GroupText = new JTextArea();

	JButton Accetta = new JButton("Accetta");
	JButton Rifiuta = new JButton("Rifiuta");
	
	

		public JPannelloRichieste(String NU, String NG) {
		    super(new BorderLayout());

		    List<Notifiche> notificheR = Esitato.SelNoitificheRichiesteDiUnCreatore(NU);
		    List<Notifiche> notificheA = Esitato.SelNoitificheArchiviate(NU);

		    // Mostra notifica nella textArea
		    StringBuilder notificheText = new StringBuilder();
		    for (Notifiche notifica : notificheR) {
		        notificheText.append(notifica.getTesto()).append("\n");
		    }

		    Accetta.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            Esitato.Accetta_Richiesta(NU, NG);
		        }
		    });
		    Accetta.setBackground(new Color(255, 255, 255));
		    Accetta.setContentAreaFilled(false);
		    Accetta.setBorderPainted(false);

		    Rifiuta.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            Esitato.Rifiuta_Richiesta(NU, NG);
		        }
		    });
		    Rifiuta.setBackground(new Color(255, 255, 255));
		    Rifiuta.setContentAreaFilled(false);
		    Rifiuta.setBorderPainted(false);
		    Rifiuta.setForeground(new Color(0, 0, 0));

		    // Imposta il layout del secondPanel prima di aggiungere i pulsanti
		    secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
		    secondPanel.add(Accetta);
		    secondPanel.add(Rifiuta);

		    // Imposta il layout del thirdPanel prima di aggiungere i titoli del post
		    thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
		    thirdPanel.add(NotificationText);
		    thirdPanel.add(GroupText);

		    this.add(thirdPanel, BorderLayout.NORTH);
		    this.add(secondPanel, BorderLayout.SOUTH);
		}

	
	public void setText(String NU, String NG){ 
		
		
		
    }
	
}
