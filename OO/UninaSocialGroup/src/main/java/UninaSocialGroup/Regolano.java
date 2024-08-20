package UninaSocialGroup;

public class Regolano {
	
	String Nome_Amministratore; // FK_NOME_UTENTE
	String Nome_Gruppo; // FK_NOME_GRUPPO
	
	public Regolano(String nome_Amministratore, String nome_Gruppo) {
		super();
		Nome_Amministratore = nome_Amministratore;
		Nome_Gruppo = nome_Gruppo;
	}

	public String getNome_Amministratore() {
		return Nome_Amministratore;
	}

	public void setNome_Amministratore(String nome_Amministratore) {
		Nome_Amministratore = nome_Amministratore;
	}

	public String getNome_Gruppo() {
		return Nome_Gruppo;
	}

	public void setNome_Gruppo(String nome_Gruppo) {
		Nome_Gruppo = nome_Gruppo;
	}

	

	
}
