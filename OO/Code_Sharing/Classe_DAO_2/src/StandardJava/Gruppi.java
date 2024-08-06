package StandardJava;

import java.sql.*;
import java.util.ArrayList;

public class Gruppi {

	private int Id_Gruppo;
	private String Nome;
	private Date Data_Creazione;
	private String Descrizone;
	private int OnlineC;
	private String Creatore; //FK_NOME_UTENTE
	
	public Gruppi(int id_Gruppo, String nome, Date data_Creazione, String descrizone, int onlineC, String creatore) {
		Id_Gruppo = id_Gruppo;
		Nome = nome;
		Data_Creazione = data_Creazione;
		Descrizone = descrizone;
		OnlineC = onlineC;
		Creatore = creatore;
	}

	

	public int getId_Gruppo() {
		return Id_Gruppo;
	}

	public void setId_Gruppo(int id_Gruppo) {
		Id_Gruppo = id_Gruppo;
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
