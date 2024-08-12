package StandardJava;

import java.sql.Date;

public class Contenuti {

	private int Id_Contenuto; //PK
	private Date Data_Creazione;
	private String Testo;
	private String Nome_Gruppo; //FK_NOME_GRUPPO
	private String Pubblicatore; //FK_NOME_UTENTE
	
	public Contenuti(int id_Contenuto, Date data_Creazione, String testo, String nome_Gruppo, String pubblicatore) {
		super();
		Id_Contenuto = id_Contenuto;
		Data_Creazione = data_Creazione;
		Testo = testo;
		Nome_Gruppo = nome_Gruppo;
		Pubblicatore = pubblicatore;
	}

	public int getId_Contenuto() {
		return Id_Contenuto;
	}

	public void setId_Contenuto(int id_Contenuto) {
		Id_Contenuto = id_Contenuto;
	}

	public Date getData_Creazione() {
		return Data_Creazione;
	}

	public void setData_Creazione(Date data_Creazione) {
		Data_Creazione = data_Creazione;
	}

	public String getTesto() {
		return Testo;
	}

	public void setTesto(String testo) {
		Testo = testo;
	}

	public String getNome_Gruppo() {
		return Nome_Gruppo;
	}

	public void setNome_Gruppo(String nome_Gruppo) {
		Nome_Gruppo = nome_Gruppo;
	}

	public String getPubblicatore() {
		return Pubblicatore;
	}

	public void setPubblicatore(String pubblicatore) {
		Pubblicatore = pubblicatore;
	}
	
	
	
}
