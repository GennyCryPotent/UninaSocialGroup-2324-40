package UninaSocialGroup;

import java.sql.Date;

public class Contenuti {

	private int idContenuto; //PK
	private Date dataCreazione;
	private String testo;
	private String nomeGruppo; //FKNOMEGRUPPO
	private String pubblicatore; //FKNOMEUTENTE
	
	public Contenuti(int idContenuto, Date dataCreazione, String testo, String nomeGruppo, String pubblicatore) {
		super();
		this.idContenuto = idContenuto;
		this.dataCreazione = dataCreazione;
		this.testo = testo;
		this.nomeGruppo = nomeGruppo;
		this.pubblicatore = pubblicatore;
	}

	public int getIdContenuto() {
		return idContenuto;
	}

	public void setIdContenuto(int idContenuto) {
		this.idContenuto = idContenuto;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getNomeGruppo() {
		return nomeGruppo;
	}

	public void setNomeGruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}

	public String getPubblicatore() {
		return pubblicatore;
	}

	public void setPubblicatore(String pubblicatore) {
		this.pubblicatore = pubblicatore;
	}
	
	
	
}

