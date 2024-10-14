package UninaSocialGroup;

import java.sql.*;

public class Gruppi {

    private String nome;
    private Date dataCreazione;
    private String descrizione;
    private int onlineC;
    private String creatore; //FKNOMEUTENTE

    public Gruppi(String nome, Date dataCreazione, String descrizione, int onlineC, String creatore) {
        this.nome = nome;
        this.dataCreazione = dataCreazione;
        this.descrizione = descrizione;
        this.onlineC = onlineC;
        this.creatore = creatore;
    }

    public String GetNome() {
        return nome;
    }

    public void SetNome(String nome) {
        this.nome = nome;
    }

    public Date GetDataCreazione() {
        return dataCreazione;
    }

    public void SetDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String GetDescrizione() {
        return descrizione;
    }

    public void SetDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int GetOnlineC() {
        return onlineC;
    }

    public void SetOnlineC(int onlineC) {
        this.onlineC = onlineC;
    }

    public String GetCreatore() {
        return creatore;
    }

    public void SetCreatore(String creatore) {
        this.creatore = creatore;
    }
}
