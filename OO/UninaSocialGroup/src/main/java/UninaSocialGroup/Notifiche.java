package UninaSocialGroup;

import java.sql.Date;

public class Notifiche {
	
	private int idNotifica; //PK
	private String testo;
	private Date dataNotifica; 
	private String visualizzatoEsitato; //variabile che contiene il visualizzato o l'esitato in base alla notifica
	private int fKIdContenuto; 
	private String ricevente; //FKNOMEUTENTE
	private String nomeGruppo; //FKNOMEGRUPPO
	
	
	//Costruttore per NotificheContenuti
	public Notifiche(int idNotifica, String testo, Date dataNotifica, String visualizzatoEsitato, int fKIdContenuto,
			String ricevente) {
		this.idNotifica = idNotifica;
		this.testo = testo;
		this.dataNotifica = dataNotifica;
		this.visualizzatoEsitato = visualizzatoEsitato;
		this.fKIdContenuto = fKIdContenuto;
		this.ricevente = ricevente;
	}
	
	//Costruttore per NotificheGruppi e NotificheRichieste
	public Notifiche(int idNotifica, String testo, Date dataNotifica, String visualizzatoEsitato, String nomeGruppo,
			String ricevente) {
		
		this.idNotifica = idNotifica;
		this.testo = testo;
		this.dataNotifica = dataNotifica;
		this.visualizzatoEsitato = visualizzatoEsitato;
		this.nomeGruppo = nomeGruppo;
		this.ricevente = ricevente;
	}
	
	
	//Costrutture per mostrare le NotificheGruppi e NotificheContenuti 
	public Notifiche(int idNotifica, String testo, Date dataNotifica, String visualizzatoEsitato, String nomeGruppo) {
		this.idNotifica = idNotifica;
		this.testo = testo;
		this.dataNotifica = dataNotifica;
		this.visualizzatoEsitato = visualizzatoEsitato;
		this.nomeGruppo = nomeGruppo;
	}

	public int getIdNotifica() {
		return idNotifica;
	}

	public void setIdNotifica(int idNotifica) {
		this.idNotifica = idNotifica;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Date getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}

	public String getvisualizzatoEsitato() {
		return visualizzatoEsitato;
	}

	public void setvisualizzatoEsitato(String visualizzatoEsitato) {
		this.visualizzatoEsitato = visualizzatoEsitato;
	}

	public int getFKIdContenuto() {
		return fKIdContenuto;
	}

	public void setFKIdContenuto(int fKIdContenuto) {
		this.fKIdContenuto = fKIdContenuto;
	}

	public String getRicevente() {
		return ricevente;
	}

	public void setRicevente(String ricevente) {
		this.ricevente = ricevente;
	}

	public String getNomeGruppo() {
		return nomeGruppo;
	}

	public void setNomeGruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}
	
}
