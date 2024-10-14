package UninaSocialGroup;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

public class RegistrazioneController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private Registrazione_GUI registrazioneView;
	private ProfiliDAO profiliDAO = new ProfiliDAO();

	public RegistrazioneController(Registrazione_GUI registrazioneView) {

		this.registrazioneView = registrazioneView;

	}

	public void ActionRegistrati(JTextField textFieldNomeUtente, JTextField textFieldPassword, JTextField textFieldNome,
			JTextField textFieldCognome, JDatePickerImpl datePicker, Date DataNascita, JComboBox comboBoxGenere,
			JButton btnRegistrati) {

		boolean check = textFieldNomeUtente.getText().isEmpty() || textFieldPassword.getText().isEmpty()
				|| textFieldNome.getText().isEmpty() || textFieldCognome.getText().isEmpty()
				|| datePicker.getJFormattedTextField().getText().isEmpty();
		try {
			if (!check) { // controllo se tutti i campi sono stati inseriti
				if (!(textFieldPassword.getText().length() < 8)) {

					profiliDAO.InsProfilo(textFieldNomeUtente.getText(), textFieldPassword.getText(), textFieldNome.getText(),
							textFieldCognome.getText(), String.valueOf(comboBoxGenere.getSelectedItem()), DataNascita);

					JOptionPane.showMessageDialog(btnRegistrati, "Registrazione completata. Benvenuto!");
					registrazioneView.setVisible(false);
					gestioneFinestre.MostraLogin();

				} else {
					JOptionPane.showMessageDialog(btnRegistrati, "La password deve essere lunga minimo 8 caratteri");
				}
			} else {
				JOptionPane.showMessageDialog(btnRegistrati, "Inserisci tutti i campi");
			}
		} catch (SQLIntegrityConstraintViolationException SQLError) {
			JOptionPane.showMessageDialog(btnRegistrati, "Questo nome utente già esiste");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(btnRegistrati, "La data deve essere minore di quella odierna");
		} 
	}
	
	public void ActionIndietro() {
		registrazioneView.setVisible(false);
		gestioneFinestre.MostraLogin();
	}

}
