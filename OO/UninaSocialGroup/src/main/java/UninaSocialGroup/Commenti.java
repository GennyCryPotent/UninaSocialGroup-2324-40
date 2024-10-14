package UninaSocialGroup;

import java.sql.*;

public class Commenti {

	private int idCommento;
	private Date dataCreazione;
	private String testo;
	private int fKIdContenuto;
	private String pubblicatore; //FK_NOME_UTENTE
	
	public Commenti(int id_Commento, Date data_Creazione, String testo, int fK_Id_Contenuto, String pubblicatore) {
		super();
		this.idCommento = id_Commento;
		this.dataCreazione = data_Creazione;
		this.testo = testo;
		this.fKIdContenuto = fK_Id_Contenuto;
		this.pubblicatore = pubblicatore;
	}

	public int getId_Commento() {
		return idCommento;
	}

	public void setId_Commento(int id_Commento) {
		this.idCommento = id_Commento;
	}

	public Date getData_Creazione() {
		return dataCreazione;
	}

	public void setData_Creazione(Date data_Creazione) {
		this.dataCreazione = data_Creazione;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public int getFK_Id_Contenuto() {
		return fKIdContenuto;
	}

	public void setFK_Id_Contenuto(int fK_Id_Contenuto) {
		this.fKIdContenuto = fK_Id_Contenuto;
	}

	public String getPubblicatore() {
		return pubblicatore;
	}

	public void setPubblicatore(String pubblicatore) {
		this.pubblicatore = pubblicatore;
	}
	
	
	
	
}
