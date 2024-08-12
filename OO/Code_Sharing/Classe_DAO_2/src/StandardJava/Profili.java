package StandardJava;

import java.sql.*;

public class Profili {

	private String Nome_Utente; //PK
	private String Password;
	private String Nome;
	private String Cognome;
	private String Genere;
	private Date Data_Nascita;
	
	public Profili(String nome_Utente, String password, String nome, String cognome, String genere, Date data_Nascita) {
		super();
		Nome_Utente = nome_Utente;
		Password = password;
		Nome = nome;
		Cognome = cognome;
		Genere = genere;
		Data_Nascita = data_Nascita;
	}

	public String getNome_Utente() {
		return Nome_Utente;
	}

	public void setNome_Utente(String nome_Utente) {
		Nome_Utente = nome_Utente;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getGenere() {
		return Genere;
	}

	public void setGenere(String genere) {
		Genere = genere;
	}

	public Date getData_Nascita() {
		return Data_Nascita;
	}

	public void setData_Nascita(Date data_Nascita) {
		Data_Nascita = data_Nascita;
	}
	
	
	
}
