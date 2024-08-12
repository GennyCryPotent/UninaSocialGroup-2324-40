package StandardJava;
import java.sql.Date;


public class Notifiche_Richieste {

	private int Id_Notifica; //PK
	private String Testo;
	private Date Data_Notifica; 
	private String esitato;
	private String Nome_Gruppo; //FK_NOME_GRUPPO
	private String Ricevente; //FK_NOME_UTENTE
	
	public Notifiche_Richieste(int id_Notifica, String testo, Date data_Notifica, String esitato, String nome_Gruppo,
			String ricevente) {
		super();
		Id_Notifica = id_Notifica;
		Testo = testo;
		Data_Notifica = data_Notifica;
		this.esitato = esitato;
		Nome_Gruppo = nome_Gruppo;
		Ricevente = ricevente;
	}

	public int getId_Notifica() {
		return Id_Notifica;
	}

	public void setId_Notifica(int id_Notifica) {
		Id_Notifica = id_Notifica;
	}

	public String getTesto() {
		return Testo;
	}

	public void setTesto(String testo) {
		Testo = testo;
	}

	public Date getData_Notifica() {
		return Data_Notifica;
	}

	public void setData_Notifica(Date data_Notifica) {
		Data_Notifica = data_Notifica;
	}

	public String getEsitato() {
		return esitato;
	}

	public void setEsitato(String esitato) {
		this.esitato = esitato;
	}

	public String getNome_Gruppo() {
		return Nome_Gruppo;
	}

	public void setNome_Gruppo(String nome_Gruppo) {
		Nome_Gruppo = nome_Gruppo;
	}

	public String getRicevente() {
		return Ricevente;
	}

	public void setRicevente(String ricevente) {
		Ricevente = ricevente;
	}
	
	
	
	
	
}
