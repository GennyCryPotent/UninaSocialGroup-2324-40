package UninaSocialGroup;

import java.sql.*;

public class Profili {

	private String nomeUtente; //PK
	private String password;
	private String nome;
	private String cognome;
	private String genere;
	private Date dataNascita;
	
	public Profili(String nomeUtente, String password, String nome, String cognome, String genere, Date dataNascita) {
		super();
		this.nomeUtente = nomeUtente;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.genere = genere;
		this.dataNascita = dataNascita;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	
	
}
