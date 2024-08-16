package UninaSocialGroup;

import java.sql.Date;

public class Notifiche_Contenuti {

	private int Id_Notifica_C; //PK
	private String Testo;
	private Date Data_Notifica; 
	private String Visualizzato;
	private int FK_Id_Contenuto; 
	private String Ricevente; //FK_NOME_UTENTE
	
	public Notifiche_Contenuti(int id_Notifica_C, String testo, Date data_Notifica, String visualizzato,
			int fK_Id_Contenuto, String ricevente) {
		super();
		Id_Notifica_C = id_Notifica_C;
		Testo = testo;
		Data_Notifica = data_Notifica;
		Visualizzato = visualizzato;
		FK_Id_Contenuto = fK_Id_Contenuto;
		Ricevente = ricevente;
	}

	public int getId_Notifica_C() {
		return Id_Notifica_C;
	}

	public void setId_Notifica_C(int id_Notifica_C) {
		Id_Notifica_C = id_Notifica_C;
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

	public String getVisualizzato() {
		return Visualizzato;
	}

	public void setVisualizzato(String visualizzato) {
		Visualizzato = visualizzato;
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
	
	
	
}
