package StandardJava;

import java.sql.*;

public class Gruppi {

	
	private String Nome;
	private Date Data_Creazione;
	private String Descrizone;
	private int OnlineC;
	private String Creatore; //FK_NOME_UTENTE
	
	public Gruppi(String nome, Date data_Creazione, String descrizone, int onlineC, String creatore) {
		Nome = nome;
		Data_Creazione = data_Creazione;
		Descrizone = descrizone;
		OnlineC = onlineC;
		Creatore = creatore;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Date getData_Creazione() {
		return Data_Creazione;
	}

	public void setData_Creazione(Date data_Creazione) {
		Data_Creazione = data_Creazione;
	}

	public String getDescrizone() {
		return Descrizone;
	}

	public void setDescrizone(String descrizone) {
		Descrizone = descrizone;
	}

	public int getOnlineC() {
		return OnlineC;
	}

	public void setOnlineC(int onlineC) {
		OnlineC = onlineC;
	}

	public String getCreatore() {
		return Creatore;
	}

	public void setCreatore(String creatore) {
		Creatore = creatore;
	}
	
}
