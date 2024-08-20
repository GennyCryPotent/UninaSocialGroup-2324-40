package UninaSocialGroup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Notifiche_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Notifiche_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        
        JLabel NomeGruppo = new JLabel();
        NomeGruppo.setForeground(new Color(0, 128, 255));
        NomeGruppo.setText("Notifiche");
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
        
        // Pannello Gruppi
        JPanel panelGruppi = new JPanel();
        Sezioni_Notifiche.addTab("Gruppi", null, panelGruppi, null);
        
        JLabel Label_Prova = new JLabel("Notifiche Gruppi");
        GroupLayout gl_panelGruppi = new GroupLayout(panelGruppi);
        gl_panelGruppi.setHorizontalGroup(
        	gl_panelGruppi.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelGruppi.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(Label_Prova, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(538, Short.MAX_VALUE))
        );
        gl_panelGruppi.setVerticalGroup(
        	gl_panelGruppi.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelGruppi.createSequentialGroup()
        			.addGap(28)
        			.addComponent(Label_Prova, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(227, Short.MAX_VALUE))
        );
        panelGruppi.setLayout(gl_panelGruppi);

        // Pannello Richieste
        JPanel panelRichieste = new JPanel();
        Sezioni_Notifiche.addTab("Richieste", null, panelRichieste, null);

        // Pannello Archiviati
        JPanel panelArchiviati = new JPanel();
        Sezioni_Notifiche.addTab("Archiviati", null, panelArchiviati, null);
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(22)
        			.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addGap(123)
        			.addComponent(NomeGruppo, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(35)
        			.addComponent(Sezioni_Notifiche, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(5)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(12)
        					.addComponent(Indietro, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        				.addComponent(NomeGruppo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
        			.addGap(39)
        			.addComponent(Sezioni_Notifiche, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
        );
        contentPane.setLayout(gl_contentPane);
	}
}

