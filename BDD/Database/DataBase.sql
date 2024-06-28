-- CREAZIONI DELLE TABELLE

--CREAZIONE TABELLA PROFILO
 CREATE TABLE Profili(
 NomeUtente VARCHAR2(30),
 Password VARCHAR2(30) NOT NULL,
 Nome VARCHAR2(30) NOT NULL,
 Cognome VARCHAR2(30) NOT NULL,
 Genere CHAR(1) NOT NULL,
 DataNascita Date NOT NULL,
 Primary key (NomeUtente)
 );

--CREAZIONE TABELLA GRUPPI
 CREATE TABLE Gruppi(
 IdGruppi NUMBER,
 Nome VARCHAR2(30) NOT NULL,
 DataCreazione Date DEFAULT SYSDATE,
 Descrizione VARCHAR2(100) NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 OnlineC NUMBER(1) DEFAULT 0, --0 offline, 1 online
 Primary key (IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente)
);

--CREAZIONE TABELLA TAGS
 CREATE TABLE Tags(
 Parola VARCHAR2(20),
 Primary key (Parola)
 );

--CREAZIONE TABELLA CONTENUTI
 CREATE TABLE Contenuti(
 IdContenuti NUMBER,
 Foto VARCHAR2(30),
 Testo VARCHAR2(30),
 Likes NUMBER DEFAULT 0, 
 FK_IdGruppi NUMBER NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdContenuti),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

--CREAZIONE TABELLA COMMENTI
 CREATE TABLE Commenti (
 IdCommenti NUMBER,
 Testo VARCHAR2(1000) NOT NULL,
 FK_IdContenuti NUMBER NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdCommenti),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdContenuti) REFERENCES Contenuti(IdContenuti)
 );

--CREAZIONE TABELLA NOTIFICHE
 CREATE TABLE Notifiche(
 IdNotifiche NUMBER,
 Testo VARCHAR2(1000) NOT NULL,
 DataNotifica Date NOT NULL,
 FK_IdGruppi NUMBER NOT NULL,
 FK_NomeUtente VARCHAR2(30) NOT NULL,
 Primary key (IdNotifiche),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
 );

 --CREAZIONE TABELLA PARTECIPANO
create table Partecipano (
 FK_NomeUtente VARCHAR2(30),
 FK_IdGruppi NUMBER,
 Primary key(FK_NomeUtente, FK_IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

--CREAZIONE TABELLA REGOLANO (Tabella per gli amministartori)
create table Regolano (
 FK_NomeUtente VARCHAR2(30),
 FK_IdGruppi NUMBER,
 Primary key(FK_NomeUtente, FK_IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

--CREAZIONE TABELLA POSSIEDE 
create table Possiede(
FK_IdGruppi NUMBER,
FK_Parola VARCHAR2(20),
Primary key (FK_IdGruppi, FK_Parola),
FOREIGN KEY (FK_Parola) REFERENCES TAGS(Parola),
FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);



--POPOLAZIONE DB

--Meccanismi AUTOINCREMENT
CREATE SEQUENCE IdGruppi_seq START WITH 1; -- Utile per fare l'autoIncrement 

--Popolamento Profili
Insert into Profili Values ('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'dd-MM-yyyy'));

--Popolamento Gruppi
Insert into Gruppi (Nome, Descrizione, fk_nomeutente) Values ('AnimeCaruso', 'Ciao', 'Genny03cry');
Insert into Gruppi (Nome, Descrizione, fk_nomeutente) Values ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');



-- TRIGGER 

-- AUTOINCREMENT PK GRUPPI
CREATE OR REPLACE TRIGGER Inc_IdGruppi 
BEFORE INSERT ON Gruppi 
FOR EACH ROW

BEGIN
  SELECT IdGruppi_seq.NEXTVAL
  INTO   :new.IdGruppi
  FROM   dual;
END;
/