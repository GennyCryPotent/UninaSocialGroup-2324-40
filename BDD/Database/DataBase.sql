-- CREAZIONI DELLE TABELLE

--CREAZIONE TABELLA PROFILO
 CREATE TABLE Profili(
 NomeUtente VARCHAR2(30),
 Password VARCHAR2(30) NOT NULL CHECK(length(Password)>=8),
 Nome VARCHAR2(30) NOT NULL,
 Cognome VARCHAR2(30) NOT NULL,
 Genere CHAR(1) NOT NULL CHECK(Genere='M' OR Genere='F' OR Genere='N'),
 DataNascita Date NOT NULL,
 Primary key (NomeUtente)
 );

--CREAZIONE TABELLA GRUPPI
 CREATE TABLE Gruppi(
 IdGruppi NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), 
 Nome VARCHAR2(30) NOT NULL,
 DataCreazione Date DEFAULT SYSDATE,
 Descrizione VARCHAR2(100) NOT NULL,
 OnlineC NUMBER(1) DEFAULT 0, --0 offline, 1 online
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente) ON DELETE CASCADE
);

--CREAZIONE TABELLA TAGS
 CREATE TABLE Tags(
 Parola VARCHAR2(20),
 Primary key (Parola)
 );

--CREAZIONE TABELLA CONTENUTI
 CREATE TABLE Contenuti(
 IdContenuti NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 DataCreazione Date DEFAULT SYSDATE,
 Foto VARCHAR2(2000),
 Testo VARCHAR2(1000),
 Likes NUMBER DEFAULT 0,
 CONSTRAINT Check_Contenuto Check(Foto<>NULL OR Testo<>NULL),
 FK_IdGruppi NUMBER NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdContenuti),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente) ON DELETE CASCADE,
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) ON DELETE CASCADE
);

--CREAZIONE TABELLA COMMENTI
 CREATE TABLE Commenti (
 IdCommenti NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 DataCreazione Date DEFAULT SYSDATE,
 Testo VARCHAR2(1000) NOT NULL,
 FK_IdContenuti NUMBER NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdCommenti),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente) ON DELETE CASCADE,
 FOREIGN KEY (FK_IdContenuti) REFERENCES Contenuti(IdContenuti) ON DELETE CASCADE
 );

--CREAZIONE TABELLA NOTIFICHE
 CREATE TABLE Notifiche(
 IdNotifiche NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Testo VARCHAR2(1000) NOT NULL,
 DataNotifica Date DEFAULT SYSDATE,
 FK_IdGruppi NUMBER NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdNotifiche),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente) ON DELETE CASCADE,
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) ON DELETE CASCADE
 );

 --CREAZIONE TABELLA PARTECIPANO
create table Partecipano (
 FK_NomeUtente VARCHAR2(30),
 FK_IdGruppi NUMBER,
 Primary key(FK_NomeUtente, FK_IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente) ON DELETE CASCADE,
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) ON DELETE CASCADE
);

--CREAZIONE TABELLA REGOLANO (Tabella per gli amministartori)
create table Regolano (
 FK_NomeUtente VARCHAR2(30),
 FK_IdGruppi NUMBER,
 Primary key(FK_NomeUtente, FK_IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente) ON DELETE CASCADE,
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) ON DELETE CASCADE
);

--CREAZIONE TABELLA POSSIEDE 
create table Possiede(
FK_IdGruppi NUMBER,
FK_Parola VARCHAR2(20),
Primary key (FK_IdGruppi, FK_Parola),
FOREIGN KEY (FK_Parola) REFERENCES TAGS(Parola) ON DELETE CASCADE,
FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) ON DELETE CASCADE
);



--POPOLAZIONE DB

--Popolamento Profili
Insert into Profili Values ('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'dd-MM-yyyy'));

--Popolamento Gruppi
Insert into Gruppi (Nome, Descrizione, fk_nomeutente) Values ('Fantacalcio', 'Ciao', 'Genny03cry');
Insert into Gruppi (Nome, Descrizione, fk_nomeutente) Values ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');



-- TRIGGER 

--Trigger per verificare la Data di nascita degli utenti
create or replace TRIGGER Check_DataNascita
BEFORE INSERT ON Profili
FOR EACH ROW
WHEN (NEW.DataNascita>SYSDATE)

BEGIN
    RAISE_APPLICATION_ERROR(-20001, 'La data di nascita deve essere minore o uguale alla data attuale'); -- Eccezione che ci permette di avere un messaggio personalizzato
END;


--Trigger che avvisa gli utenti di un gruppo quando il creatore è online
create or replace TRIGGER Invia_Notifica_OnlineC
AFTER UPDATE ON Gruppi
FOR EACH ROW
WHEN (NEW.OnlineC = 1 AND OLD.OnlineC = 0)

DECLARE

CURSOR Rec_Utente IS (SELECT FK_NomeUtente FROM partecipano Where FK_idgruppi= :NEW.IdGruppi);
TMP_Utente Partecipano.FK_NomeUtente%TYPE;

BEGIN

OPEN Rec_Utente;

LOOP

    FETCH Rec_Utente INTO TMP_Utente;
    EXIT WHEN Rec_Utente%NOTFOUND;


    INSERT INTO Notifiche (Testo, FK_IdGruppi, FK_NomeUtente) VALUES ('Il Creatore del gruppo '|| :NEW.Nome || ' è Online!', :NEW.IdGruppi, TMP_Utente); 

END LOOP;

CLOSE Rec_Utente;

END;


--Trigger che avvisa gli utenti iscritti ad un gruppo che un utente ha messo un contenuto
create or replace TRIGGER Invia_Notifica_G
AFTER INSERT ON Contenuti
FOR EACH ROW

DECLARE 

CURSOR Rec_User IS (SELECT FK_NomeUtente FROM partecipano Where fk_idgruppi= :NEW.fk_idgruppi);
TMP_User Partecipano.FK_NomeUtente%TYPE;

BEGIN

OPEN Rec_User;

LOOP 

    FETCH Rec_User INTO TMP_User;
    EXIT WHEN Rec_User%NOTFOUND;

    INSERT INTO Notifiche (Testo, fk_idgruppi, fk_nomeutente) VALUES ('inserito un nuovo contenuto', :NEW.FK_IdGruppi, TMP_User);
    
END LOOP;

CLOSE Rec_User;

END;


