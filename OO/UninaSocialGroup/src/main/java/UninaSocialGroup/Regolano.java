package UninaSocialGroup;

public class Regolano {
	
	String nomeAmministratore; // FK_NOME_UTENTE
	String nomeGruppo; // FK_NOME_GRUPPO
	
	public Regolano(String nomeAmministratore, String nomeGruppo) {
		super();
		this.nomeAmministratore = nomeAmministratore;
		this.nomeGruppo = nomeGruppo;
	}

	public String getNome_Amministratore() {
		return nomeAmministratore;
	}

	public void setNome_Amministratore(String nomeAmministratore) {
		this.nomeAmministratore = nomeAmministratore;
	}

	public String getNome_Gruppo() {
		return nomeGruppo;
	}

	public void setNome_Gruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}

	

	
}
