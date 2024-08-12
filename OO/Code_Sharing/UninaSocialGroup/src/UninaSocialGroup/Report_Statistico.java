package UninaSocialGroup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Report_Statistico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Report_Statistico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 713, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 437, Short.MAX_VALUE)
        );
        contentPane.setLayout(gl_contentPane);
        
        JLabel Label = new JLabel();
        Label.setForeground(new Color(0, 128, 255));
        Label.setText("Report Statistico");
        Label.setFont(new Font("Tahoma", Font.BOLD, 18));
        Label.setBounds(210, 10, 202, 38);
        contentPane.add(Label);
        
        JButton Indietro = new JButton("<");
        Indietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Report_Statistico.this.setVisible(false);
        		Gestione_Finestre N = new Gestione_Finestre();
        		N.AccessoHome(null);
        	}
        });
        Indietro.setForeground(new Color(0, 128, 255));
        Indietro.setFont(new Font("Arial Black", Font.BOLD, 17));
        Indietro.setBackground(Color.WHITE);
        Indietro.setBounds(27, 22, 60, 53);
        contentPane.add(Indietro);
        
        
	}
}
