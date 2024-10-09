package UninaSocialGroup;

import java.awt.Font;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Registrazione_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeUtente;
	private JTextField textFieldPassword;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private RegistrazioneController RC = new RegistrazioneController(Registrazione_GUI.this);
	private Date DataNascita;

	public Registrazione_GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Creazione del pannello in alto per il titolo e il pulsante Indietro
		JPanel topPanel = new JPanel(new BorderLayout());
		contentPane.add(topPanel, BorderLayout.NORTH);

		// Label titolo
		JLabel lblNewLabel = new JLabel("Inserisci i dati");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		topPanel.add(lblNewLabel, BorderLayout.CENTER);

		// Pulsante Indietro
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RC.ActionIndietro();
			}
		});
		topPanel.add(btnIndietro, BorderLayout.EAST);  // Posizionamento in alto a destra

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 3, 0, 10));

		JLabel LabelNomeUtente = new JLabel("Nome Utente: ");
		panel.add(LabelNomeUtente);

		textFieldNomeUtente = new JTextField();
		panel.add(textFieldNomeUtente);
		textFieldNomeUtente.setColumns(10);

		JLabel LabelPassword = new JLabel("Password (minimo 8 caratteri): ");
		panel.add(LabelPassword);

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		panel.add(textFieldPassword);

		JLabel LabelNome = new JLabel("Nome: ");
		panel.add(LabelNome);

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		panel.add(textFieldNome);

		JLabel LabelCognome = new JLabel("Cognome: ");
		panel.add(LabelCognome);

		textFieldCognome = new JTextField();
		textFieldCognome.setColumns(10);
		panel.add(textFieldCognome);

		JLabel LabelGenere = new JLabel("Genere: ");
		panel.add(LabelGenere);
		String[] genere = new String[] { "M", "F", "N" };
		JComboBox comboBoxGenere = new JComboBox(genere);
		panel.add(comboBoxGenere);

		JLabel LabelData = new JLabel("Data di nascita:");
		panel.add(LabelData);

		// Pannello per selezionare la data
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.TRAILING);
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datePicker.getJFormattedTextField().setText(String.valueOf(datePicker.getModel().getValue()));
				DataNascita = (Date) datePicker.getModel().getValue();
			}
		});
		panel.add(datePicker);

		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RC.ActionRegistrati(textFieldNomeUtente, textFieldPassword, textFieldNome, textFieldCognome, datePicker, DataNascita, comboBoxGenere, btnRegistrati);
			}
		});
		contentPane.add(btnRegistrati, BorderLayout.SOUTH);
	}
}
