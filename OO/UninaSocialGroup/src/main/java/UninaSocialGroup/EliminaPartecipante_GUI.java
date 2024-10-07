package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;

public class EliminaPartecipante_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gestione_Finestre GF = new Gestione_Finestre();
	
	public EliminaPartecipante_GUI(String NU, String NG) {
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
		
		// Popolazione dei bottoni dei gruppi
				creaBottoniPartecipanti(NU, ParteciPanel, NG);
		
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
		private void creaBottoniPartecipanti(String NU, JPanel ParteciPanel, String NG) {
			Partecipano_DAO partecipano_DAO = new Partecipano_DAO();
			List<Partecipano> partecipano = partecipano_DAO. SelAllPartecipanoGruppo(NG);

			for (Partecipano p : partecipano) {
				JButton btnNewButton = new JButton(p.getNome_Partecipante());
				btnNewButton.addActionListener(e -> {
					
			        int scelta = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare questo utente?", "Elimina", JOptionPane.YES_NO_OPTION);

			        if (scelta == JOptionPane.YES_OPTION) {
			            partecipano_DAO.DelPartecipante(p.getNome_Partecipante(), NG);
			            JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " eliminato", "Eliminato", JOptionPane.INFORMATION_MESSAGE);
			            EliminaPartecipante_GUI.this.setVisible(false);
			        } else if (scelta == JOptionPane.NO_OPTION) {
			        	JOptionPane.showMessageDialog(null, "Partecipante " + p.getNome_Partecipante() + " non eliminato", "Eliminato", JOptionPane.INFORMATION_MESSAGE);
			        	EliminaPartecipante_GUI.this.setVisible(false);
			        }

			        System.out.println("scelta : " + scelta);

			        
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
