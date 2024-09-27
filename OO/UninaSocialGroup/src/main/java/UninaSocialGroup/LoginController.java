package UninaSocialGroup;

import javax.swing.JOptionPane;

public class LoginController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Login_GUI LoginView;
	private String NU;
	private String PSW;
	private Profili_DAO Pass = new Profili_DAO();

	public LoginController(Login_GUI loginView) {
		this.LoginView = loginView;
	}

	public void ActionAccedi() {

		NU = LoginView.NomeUtente.getText();
		PSW = new String(LoginView.passwordField.getPassword());

		try {
			String passaggio = Pass.SelSigProfilo(NU).getPassword();
			String NomeU = Pass.SelSigProfilo(NU).getNome_Utente();

			if (PSW.compareTo(passaggio) == 0 && NU.compareTo(NomeU) == 0) {

				LoginView.setVisible(false);
				GF.AccessoHome(NU);

			} else {

				JOptionPane.showMessageDialog(null, "Controlla Nome Utente e Password", "Credenziali errate",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Controlla Nome Utente e Password", "Credenziali errate",
					JOptionPane.WARNING_MESSAGE);

		}

	}

	public void ActionRegistrati() {
		LoginView.setVisible(false);
		GF.RegistrazioneGUI();
	}

}
