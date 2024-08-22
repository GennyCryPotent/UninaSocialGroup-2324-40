package UninaSocialGroup;

public class Likes {

	private String FK_Nome_Utente;
	private int FK_Id_Contenuto;
	
	public Likes(String fK_Nome_Utente, int fK_Id_Contenuto) {
		super();
		FK_Nome_Utente = fK_Nome_Utente;
		FK_Id_Contenuto = fK_Id_Contenuto;
	}

	public String getFK_Nome_Utente() {
		return FK_Nome_Utente;
	}

	public void setFK_Nome_Utente(String fK_Nome_Utente) {
		FK_Nome_Utente = fK_Nome_Utente;
	}

	public int getFK_Id_Contenuto() {
		return FK_Id_Contenuto;
	}

	public void setFK_Id_Contenuto(int fK_Id_Contenuto) {
		FK_Id_Contenuto = fK_Id_Contenuto;
	}
}
