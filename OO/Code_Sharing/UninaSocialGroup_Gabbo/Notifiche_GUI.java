package UninaSocialGroup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;




public class Notifiche_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private String NU;
    
	List<Notifiche_Gruppi> Res_Notifiche_Gruppi = new ArrayList<Notifiche_Gruppi>();
	
	Notifiche_Gruppi_DAO Collegamento = new Notifiche_Gruppi_DAO("system", "Database@03");
	
	public Notifiche_GUI(String NU) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel NomeGruppo = new JLabel();
        NomeGruppo.setForeground(new Color(0, 128, 255));
        NomeGruppo.setText("Notifiche");
        NomeGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
        NomeGruppo.setBounds(210, 10, 202, 38);
        contentPane.add(NomeGruppo);
        
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
        Indietro.setBounds(27, 22, 60, 53);
        contentPane.add(Indietro);
        
        JTabbedPane Sezioni_Notifiche = new JTabbedPane(JTabbedPane.TOP);
        Sezioni_Notifiche.setForeground(new Color(0, 128, 255));
        Sezioni_Notifiche.setBackground(new Color(255, 255, 255));
        
        // Pannello Gruppi
        JPanel panelGruppi = new JPanel();
        panelGruppi.setBackground(new Color(255, 255, 255));
        Sezioni_Notifiche.addTab("Gruppi", null, panelGruppi, null);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 18));
        GroupLayout gl_panelGruppi = new GroupLayout(panelGruppi);
        gl_panelGruppi.setHorizontalGroup(
        	gl_panelGruppi.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelGruppi.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelGruppi.setVerticalGroup(
        	gl_panelGruppi.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelGruppi.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        			.addContainerGap())
        );
        panelGruppi.setLayout(gl_panelGruppi);
        
        
        
		for (int i = 0; i < Res_Notifiche_Gruppi.size(); i++) {
			textArea.append(Res_Notifiche_Gruppi.get(i)+"\n");
		}
		contentPane.add(textArea);
        
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
