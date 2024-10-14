package UninaSocialGroup;

import javax.swing.JOptionPane;

public class LoginController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private LoginGUI loginView;
	private String nomeUtente;
	private String password;
	private ProfiliDAO profiliDAO = new ProfiliDAO();

	public LoginController(LoginGUI loginView) {
		this.loginView = loginView;
	}

	public void ActionAccedi() {

		nomeUtente = loginView.lblNomeUtente.getText();
		password = new String(loginView.passwordField.getPassword());

		try {
			String Password = profiliDAO.SelSigProfilo(nomeUtente).getPassword();
			String NomeUtente = profiliDAO.SelSigProfilo(nomeUtente).getNomeUtente();

			if (password.compareTo(Password) == 0 && nomeUtente.compareTo(NomeUtente) == 0) {

				loginView.setVisible(false);
				gestioneFinestre.AccessoHome(nomeUtente);

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
		loginView.setVisible(false);
		gestioneFinestre.MostraRegistrazione();
	}

}
