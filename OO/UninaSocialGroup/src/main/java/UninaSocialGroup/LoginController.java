package UninaSocialGroup;

import javax.swing.JOptionPane;

public class LoginController {

	private GestioneFinestre GF = new GestioneFinestre();
	private LoginGUI LoginView;
	private String NU;
	private String PSW;
	private ProfiliDAO ProfiliDao = new ProfiliDAO();

	public LoginController(LoginGUI loginView) {
		this.LoginView = loginView;
	}

	public void ActionAccedi() {

		NU = LoginView.NomeUtente.getText();
		PSW = new String(LoginView.passwordField.getPassword());

		try {
			String Password = ProfiliDao.SelSigProfilo(NU).getPassword();
			String NomeUtente = ProfiliDao.SelSigProfilo(NU).getNome_Utente();

			if (PSW.compareTo(Password) == 0 && NU.compareTo(NomeUtente) == 0) {

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
		GF.MostraRegistrazione();
	}

}
