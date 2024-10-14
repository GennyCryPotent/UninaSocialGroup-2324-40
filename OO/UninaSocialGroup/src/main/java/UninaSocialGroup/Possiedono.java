package UninaSocialGroup;

public class Possiedono {

	String tag; // FK_PAROLA
	String nomeGruppo; // FK_NOME_GRUPPO
	
	public Possiedono(String tag, String nomeGruppo) {
		super();
		this.tag = tag;
		this.nomeGruppo = nomeGruppo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNome_Gruppo() {
		return nomeGruppo;
	}

	public void setNome_Gruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}
	
	
	
	
}

