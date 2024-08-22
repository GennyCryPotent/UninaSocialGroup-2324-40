package UninaSocialGroup;

import java.sql.*;

public class Commenti {

	private int Id_Commento;
	private Date Data_Creazione;
	private String Testo;
	private int FK_Id_Contenuto;
	private String Pubblicatore; //FK_NOME_UTENTE
	
	public Commenti(int id_Commento, Date data_Creazione, String testo, int fK_Id_Contenuto, String pubblicatore) {
		super();
		Id_Commento = id_Commento;
		Data_Creazione = data_Creazione;
		Testo = testo;
		FK_Id_Contenuto = fK_Id_Contenuto;
		Pubblicatore = pubblicatore;
	}

	public int getId_Commento() {
		return Id_Commento;
	}

	public void setId_Commento(int id_Commento) {
		Id_Commento = id_Commento;
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

	public int getFK_Id_Contenuto() {
		return FK_Id_Contenuto;
	}

	public void setFK_Id_Contenuto(int fK_Id_Contenuto) {
		FK_Id_Contenuto = fK_Id_Contenuto;
	}

	public String getPubblicatore() {
		return Pubblicatore;
	}

	public void setPubblicatore(String pubblicatore) {
		Pubblicatore = pubblicatore;
	}
	
	
	
	
}
