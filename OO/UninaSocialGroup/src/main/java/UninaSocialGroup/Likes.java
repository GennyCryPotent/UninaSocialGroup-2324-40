package UninaSocialGroup;

public class Likes {

	private String fKNomeUtente;
	private int fKIdContenuto;
	
	public Likes(String fKNomeUtente, int fKIdContenuto) {
		super();
		this.fKNomeUtente = fKNomeUtente;
		this.fKIdContenuto = fKIdContenuto;
	}

	public String getFKNomeUtente() {
		return fKNomeUtente;
	}

	public void setFKNomeUtente(String fKNomeUtente) {
		this.fKNomeUtente = fKNomeUtente;
	}

	public int getFKIdContenuto() {
		return fKIdContenuto;
	}

	public void setFKIdContenuto(int fKIdContenuto) {
		this.fKIdContenuto = fKIdContenuto;
	}
}
