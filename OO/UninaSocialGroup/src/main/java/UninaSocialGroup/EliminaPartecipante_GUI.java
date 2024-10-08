package UninaSocialGroup;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

public class EliminaPartecipante_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GruppiController GC = new GruppiController (EliminaPartecipante_GUI.this);
	String Ruolo;
	
	public EliminaPartecipante_GUI(String NU, String NG, String Ruolo) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane ScrollPartecipanti = new JScrollPane();
		ScrollPartecipanti.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPartecipanti.setBounds(20, 50, 390, 190);
		contentPane.add(ScrollPartecipanti, BorderLayout.WEST);
		
		JPanel ParteciPanel = new JPanel();
		ScrollPartecipanti.setViewportView(ParteciPanel);
		ScrollPartecipanti.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Popolazione dei bottoni dei gruppi
				creaBottoniPartecipanti(NU, ParteciPanel, NG, Ruolo);
		
		JLabel Label = new JLabel("Quale partecipante vuoi eliminare?");
		Label.setForeground(new Color(0, 128, 255));
		Label.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label.setBorder(new EmptyBorder(10, 10, 10, 10));
		Label.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(Label, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(Label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(211, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	// crea dinamicamente tutti i bottoni per accedere ai gruppi
		private void creaBottoniPartecipanti(String NU, JPanel ParteciPanel, String NG, String Ruolo) {
			Partecipano_DAO partecipano_DAO = new Partecipano_DAO();
			List<Partecipano> partecipano = partecipano_DAO. SelAllPartecipanoGruppo(NG);
			Gruppi_DAO Creatore = new Gruppi_DAO();
			
			String GCreator = Creatore.SelSigGruppo(NG).getCreatore();
			
			for (Partecipano p : partecipano) {
				if(NU.compareTo(p.getNome_Partecipante())!=0) { 
					if(GCreator.compareTo(p.getNome_Partecipante())==0) {
						//NO BUTTON CREATORE
						System.out.print("Creatore: " + GCreator);
					}else {
				JButton btnNewButton = new JButton(p.getNome_Partecipante());
				btnNewButton.addActionListener(e -> {
					
			         GC.ActionRimuoviUtente(p, NG);
				
				});
				
				btnNewButton.setForeground(new Color(0, 128, 255));
				btnNewButton.setBackground(new Color(255, 255, 255));
				btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                
				Border lineBorder = BorderFactory.createLineBorder(new Color(0, 128, 255));
				Border emptyBorder = new EmptyBorder(3, 2, 3, 2);
				Border compoundBorder = new CompoundBorder(emptyBorder, lineBorder);

				btnNewButton.setBorder(compoundBorder);
				ParteciPanel.add(btnNewButton);
				}
				}
			}
		}
}
