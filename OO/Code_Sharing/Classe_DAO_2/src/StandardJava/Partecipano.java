package StandardJava;

public class Partecipano {

	String Nome_Partecipante; // FK_NOME_UTENTE
	String Nome_Gruppo; // FK_NOME_GRUPPO

	public Partecipano(String nome_Partecipante, String nome_Gruppo) {
		super();
		Nome_Partecipante = nome_Partecipante;
		Nome_Gruppo = nome_Gruppo;
	}

	public String getNome_Partecipante() {
		return Nome_Partecipante;
	}

	public void setNome_Partecipante(String nome_Partecipante) {
		Nome_Partecipante = nome_Partecipante;
	}

	public String getNome_Gruppo() {
		return Nome_Gruppo;
	}

	public void setNome_Gruppo(String nome_Gruppo) {
		Nome_Gruppo = nome_Gruppo;
	}

}
