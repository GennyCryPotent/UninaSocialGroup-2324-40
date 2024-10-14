package UninaSocialGroup;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.image.Image;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.MediaTracker;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField NomeUtente;
	public JPasswordField passwordField;
	private JLabel Password;
	public String NU; // variabile per nome utente
	private JButton btnRegistrati;
	
	private LoginController LC = new LoginController(LoginGUI.this);

	public LoginGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JButton accedi = new JButton("Accedi");
		sl_contentPane.putConstraint(SpringLayout.WEST, accedi, 195, SpringLayout.WEST, contentPane);
		accedi.setForeground(new Color(0, 128, 255));
		accedi.setBackground(new Color(255, 255, 255));
		accedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LC.ActionAccedi();
			}
		});
		contentPane.add(accedi);

		NomeUtente = new JTextField();
		contentPane.add(NomeUtente);
		NomeUtente.setColumns(10);

		passwordField = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, passwordField, -118, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, accedi, 36, SpringLayout.SOUTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, NomeUtente, 0, SpringLayout.WEST, passwordField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, NomeUtente, -23, SpringLayout.NORTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField, 225, SpringLayout.WEST, contentPane);
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

		ImageIcon highResIcon = new ImageIcon(getClass().getResource("image.png"));
		java.awt.Image image = highResIcon.getImage(); // Recupera l'immagine originale
		java.awt.Image scaledImage = image.getScaledInstance(190, 190 ,java.awt.Image.SCALE_SMOOTH); // Ridimensiona l'immagine
		ImageIcon scaledIcon = new ImageIcon(scaledImage); // Crea un nuovo ImageIcon con l'immagine ridimensionata

		JLabel icona = new JLabel(scaledIcon);
		sl_contentPane.putConstraint(SpringLayout.NORTH, icona, -5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, icona, 0, SpringLayout.WEST, accedi);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, icona, -11, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, icona, 360, SpringLayout.WEST, contentPane);
		contentPane.add(icona);

		btnRegistrati = new JButton("Registrati");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRegistrati, 264, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, accedi, -1, SpringLayout.WEST, btnRegistrati);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnRegistrati, 36, SpringLayout.SOUTH, passwordField);
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LC.ActionRegistrati();
			}
		});
		btnRegistrati.setForeground(new Color(0, 128, 255));
		btnRegistrati.setBackground(Color.WHITE);
		contentPane.add(btnRegistrati);

	}
}
