package UninaSocialGroup;

import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

public class RegistrazioneController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Registrazione_GUI registrazioneView;
	private Profili_DAO P = new Profili_DAO();

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

					P.InsProfilo(textFieldNomeUtente.getText(), textFieldPassword.getText(), textFieldNome.getText(),
							textFieldCognome.getText(), String.valueOf(comboBoxGenere.getSelectedItem()), DataNascita);

					JOptionPane.showMessageDialog(btnRegistrati, "Registrazione completata. Benvenuto!");
					registrazioneView.setVisible(false);
					GF.LoginGUI();

				} else {
					JOptionPane.showMessageDialog(btnRegistrati, "La password deve essere lunga minimo 8 caratteri");
				}
			} else {
				JOptionPane.showMessageDialog(btnRegistrati, "Inserisci tutti i campi");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(btnRegistrati, "Questo nome utente già esiste");
		}
	}

}
