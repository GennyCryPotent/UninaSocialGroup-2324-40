package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreaGruppo_GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NGruppo;
	private JTextField Descrizione;

	

	/**
	 * Create the frame.
	 */
	public CreaGruppo_GUI(String NU) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 431, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblCreaGruppo = new JLabel("Crea gruppo");
		lblCreaGruppo.setForeground(new Color(0, 128, 255));
		lblCreaGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreaGruppo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblCreaGruppo.setBackground(Color.WHITE);
		
		NGruppo = new JTextField();
		NGruppo.setColumns(10);
		
		Descrizione = new JTextField();
		Descrizione.setColumns(10);
		
		JLabel NomeGLabel = new JLabel("Nome:");
		
		JLabel lblDescrizione = new JLabel("Descrizione:");

		
	    Gruppi_DAO CreaG = new Gruppi_DAO();
		
		JButton btnCrea = new JButton("Crea");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    String NG;
			    String DescrG;
			    
			   NG=NGruppo.getText();
			   DescrG=Descrizione.getText();
			   
			   
			   
			   if(NG.isEmpty() || DescrG.isEmpty()) {
				   JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati", "Credenziali errate",
							JOptionPane.WARNING_MESSAGE);
			    }else {
			   
				CreaG.InsGruppo(NG ,DescrG , NU);
				
			   }
			}
		});
		btnCrea.setForeground(new Color(0, 128, 255));
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaGruppo_GUI.this.setVisible(false);
			}
		});
		btnAnnulla.setForeground(new Color(0, 128, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblDescrizione, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(Descrizione, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
								.addGap(26))
							.addComponent(lblCreaGruppo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(104)
							.addComponent(btnCrea, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(btnAnnulla, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(NomeGLabel, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(26)
					.addComponent(NGruppo, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCreaGruppo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(NomeGLabel)
						.addComponent(NGruppo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescrizione)
						.addComponent(Descrizione, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrea)
						.addComponent(btnAnnulla))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
