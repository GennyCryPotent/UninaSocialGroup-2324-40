package UninaSocialGroup;

public class Possiedono {

	String Tag; // FK_PAROLA
	String Nome_Gruppo; // FK_NOME_GRUPPO
	
	public Possiedono(String tag, String nome_Gruppo) {
		super();
		Tag = tag;
		Nome_Gruppo = nome_Gruppo;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public String getNome_Gruppo() {
		return Nome_Gruppo;
	}

	public void setNome_Gruppo(String nome_Gruppo) {
		Nome_Gruppo = nome_Gruppo;
	}
	
	
	
	
}

