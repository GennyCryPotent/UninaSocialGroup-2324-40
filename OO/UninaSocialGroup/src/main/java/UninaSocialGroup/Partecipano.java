package UninaSocialGroup;

public class Partecipano {

	String nomePartecipante; // FK_NOME_UTENTE
	String nomeGruppo; // FK_NOME_GRUPPO

	public Partecipano(String nomePartecipante, String nomeGruppo) {
		super();
		this.nomePartecipante = nomePartecipante;
		this.nomeGruppo = nomeGruppo;
	}

	public String getNome_Partecipante() {
		return nomePartecipante;
	}

	public void setNome_Partecipante(String nomePartecipante) {
		this.nomePartecipante = nomePartecipante;
	}

	public String getNome_Gruppo() {
		return nomeGruppo;
	}

	public void setNome_Gruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}

}

