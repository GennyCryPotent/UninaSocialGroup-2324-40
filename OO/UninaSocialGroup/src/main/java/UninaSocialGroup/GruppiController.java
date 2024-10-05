package UninaSocialGroup;

public class GruppiController {

	private Gestione_Finestre GF = new Gestione_Finestre();
	private Gruppi_GUI gruppiView;
	private Contenuti_DAO C = new Contenuti_DAO();

	public GruppiController(Gruppi_GUI gruppiView) {
		this.gruppiView = gruppiView;
	}

	public void ActionHome(String NU) {
		gruppiView.setVisible(false);
		GF.AccessoHome(NU);
	}

	public void ActionPost(String NU, String NG, String NewPost) {

		if (!NewPost.isEmpty()) {
			C.InsContenuto(null, NewPost, NG, NU);

			gruppiView.setVisible(false);
			GF.GruppiGUI(NU, NG);
		}

	}

	public void ActionModifica(String NU, String NG) {

		gruppiView.setVisible(false);
		GF.Elimina_Contenuto(NU, NG);

	}
	
	public void ActionElimina(String NU, String NG) {
		GF.EliminaPartecipante(NU, NG);
	}

}
