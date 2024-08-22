package UninaSocialGroup;

import java.sql.Date;

public class Notifiche {
	
	private int Id_Notifica; //PK
	private String Testo;
	private Date Data_Notifica; 
	private String Visualizzato_Esitato; //variabile che contiene il visualizzato o l'esitato in base alla notifica
	private int FK_Id_Contenuto; 
	private String Ricevente; //FK_NOME_UTENTE
	private String Nome_Gruppo; //FK_NOME_GRUPPO
	
	
	//Costruttore per Notifiche_Contenuti
	public Notifiche(int id_Notifica, String testo, Date data_Notifica, String visualizzato_esitato, int fK_Id_Contenuto,
			String ricevente) {
		super();
		Id_Notifica = id_Notifica;
		Testo = testo;
		Data_Notifica = data_Notifica;
		Visualizzato_Esitato = visualizzato_esitato;
		FK_Id_Contenuto = fK_Id_Contenuto;
		Ricevente = ricevente;
	}
	
	//Costruttore per Notifiche_Gruppi e Notifiche_Richieste
	public Notifiche(int id_Notifica, String testo, Date data_Notifica, String visualizzato_esitato, String nome_Gruppo,
			String ricevente) {
		super();
		Id_Notifica = id_Notifica;
		Testo = testo;
		Data_Notifica = data_Notifica;
		Visualizzato_Esitato = visualizzato_esitato;
		Nome_Gruppo = nome_Gruppo;
		Ricevente = ricevente;
	}
	
	
	//Costrutture per mostrare le Notifiche_Gruppi e Notifiche_Contenuti 
	public Notifiche(int id_Notifica, String testo, Date data_Notifica, String visualizzato_Esitato) {
		super();
		Id_Notifica = id_Notifica;
		Testo = testo;
		Data_Notifica = data_Notifica;
		Visualizzato_Esitato = visualizzato_Esitato;
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

	public String getVisualizzato_Esitato() {
		return Visualizzato_Esitato;
	}

	public void setVisualizzato_Esitato(String visualizzato_Esitato) {
		Visualizzato_Esitato = visualizzato_Esitato;
	}

	public int getFK_Id_Contenuto() {
		return FK_Id_Contenuto;
	}

	public void setFK_Id_Contenuto(int fK_Id_Contenuto) {
		FK_Id_Contenuto = fK_Id_Contenuto;
	}

	public String getRicevente() {
		return Ricevente;
	}

	public void setRicevente(String ricevente) {
		Ricevente = ricevente;
	}

	public String getNome_Gruppo() {
		return Nome_Gruppo;
	}

	public void setNome_Gruppo(String nome_Gruppo) {
		Nome_Gruppo = nome_Gruppo;
	}
	
}
