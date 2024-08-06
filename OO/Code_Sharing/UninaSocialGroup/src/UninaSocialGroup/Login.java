package UninaSocialGroup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NomeUtente;
	private JPasswordField passwordField;
	private JLabel Password;
	private JLabel lblNewLabel_1;
	private Gestione_Finestre Accesso = new Gestione_Finestre();  //passaggio nel gestore delle finestre
    public String NU;   //variabile per nome utente
    private String PSW; //variabile per password
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton accedi = new JButton("Accedi");
		sl_contentPane.putConstraint(SpringLayout.WEST, accedi, 236, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, accedi, -62, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, accedi, -264, SpringLayout.EAST, contentPane);
		accedi.setForeground(new Color(0, 128, 255));
		accedi.setBackground(new Color(255, 255, 255));
		accedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NU=NomeUtente.getText();
				PSW= new String (passwordField.getPassword()); 
				
				String passaggio ="gabbo";
				
		         if(PSW.compareTo(passaggio)==0) {
		        	 Accesso.AccessoHome(NU);
		         }else {
		        	 
		        	 JOptionPane.showMessageDialog(null, "Controlla Nome Utente e Password", "Credenziali errate", JOptionPane.WARNING_MESSAGE );
		         }
				
			}
		});
		contentPane.add(accedi);
		
		NomeUtente = new JTextField();
		contentPane.add(NomeUtente);
		NomeUtente.setColumns(10);
		
		passwordField = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.WEST, NomeUtente, 0, SpringLayout.WEST, passwordField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, NomeUtente, -23, SpringLayout.NORTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField, 225, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, passwordField, -33, SpringLayout.NORTH, accedi);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField, 331, SpringLayout.WEST, contentPane);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Nome Utente");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, NomeUtente);
		contentPane.add(lblNewLabel);
		
		Password = new JLabel("Password");
		sl_contentPane.putConstraint(SpringLayout.WEST, Password, 146, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Password, -11, SpringLayout.WEST, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, Password);
		sl_contentPane.putConstraint(SpringLayout.NORTH, Password, 3, SpringLayout.NORTH, passwordField);
		contentPane.add(Password);
		
		ImageIcon highResIcon = new ImageIcon("C:\\Users\\pc\\Downloads\\th (11).png");
		
		JLabel lblNewLabel_2 = new JLabel(highResIcon);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 187, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -11, SpringLayout.NORTH, NomeUtente);
		contentPane.add(lblNewLabel_2);

		
        

		
		

	       
	        
	      
	}
}
