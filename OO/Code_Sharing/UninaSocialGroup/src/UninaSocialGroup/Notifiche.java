package UninaSocialGroup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class Notifiche extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	

	/**
	 * Create the frame.
	 */
	public Notifiche() {
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
        		Notifiche.this.setVisible(false);
        		Gestione_Finestre N = new Gestione_Finestre();
        		N.AccessoHome(null);
        	}
        });
        Indietro.setForeground(new Color(0, 128, 255));
        Indietro.setFont(new Font("Arial Black", Font.BOLD, 17));
        Indietro.setBackground(Color.WHITE);
        Indietro.setBounds(27, 22, 60, 53);
        contentPane.add(Indietro);
        
        JLabel lblNewLabel = new JLabel("Notifica Esempio");
        lblNewLabel.setBounds(129, 107, 381, 23);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Notifica Esempio");
        lblNewLabel_1.setBounds(129, 152, 381, 23);
        contentPane.add(lblNewLabel_1);
        
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBackground(new Color(0, 128, 255));
        scrollBar.setBounds(696, 82, 17, 355);
        contentPane.add(scrollBar);
	}
}
