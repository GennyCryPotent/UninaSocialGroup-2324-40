
--CREAZIONE TABELLA PROFILO
 CREATE TABLE Profili(
 NomeUtente VARCHAR(30),
 Password VARCHAR(30) NOT NULL,
 Nome VARCHAR(30) NOT NULL,
 Cognome VARCHAR(30) NOT NULL,
 Genere CHAR NOT NULL,
 DataNascita Date NOT NULL,
 Primary key (NomeUtente)
 );

--CREAZIONE TABELLA GRUPPI
 CREATE TABLE Gruppi(
 IdGruppi Int ,
 Nome VARCHAR(30) NOT NULL,
 DataCreazione Date NOT NULL,
 Descrizione VARCHAR(100) NOT NULL,
 FK_NomeUtente Varchar(30) NOT NULL,
 OnlineC NUMBER(1) NOT NULL Default 0 --0 offline, 1 online
 Primary key (IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente)
);

CREATE SEQUENCE IdGruppi_seq START WITH 1; --Utile per fare l'autoIncrement

--CREAZIONE TABELLA TAGS
 CREATE TABLE Tags(
 Parola VARCHAR(20),
 Primary key (Parola)
 );

--CREAZIONE TABELLA CONTENUTI
 CREATE TABLE Contenuti(
 IdContenuti INT,
 Foto VARCHAR(30),
 Testo VARCHAR(30),
 Likes INT NOT NULL Default 0, 
 FK_IdGruppi int NOT NULL,
 FK_NomeUtente Varchar(30) NOT NULL,
 Primary key (IdContenuti),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

--CREAZIONE TABELLA COMMENTI
 CREATE TABLE Commenti (
 IdCommenti Int,
 Testo VARCHAR(1000) NOT NULL,
 FK_IdContenuti Int NOT NULL,
 FK_NomeUtente Varchar(30) NOT NULL,
 Primary key (IdCommenti),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdContenuti) REFERENCES Contenuti(IdContenuti)
 );

--CREAZIONE TABELLA NOTIFICHE
 CREATE TABLE Notifiche(
 IdNotifiche Int,
 Testo VARCHAR(1000) NOT NULL,
 DataNotifica Date NOT NULL,
 FK_IdGruppi int NOT NULL,
 FK_NomeUtente Varchar(30) NOT NULL,
 Primary key (IdNotifiche),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
 );

 --CREAZIONE TABELLA PARTECIPANO
create table Partecipano (
 FK_NomeUtente Varchar(30),
 FK_IdGruppi Int,
 Primary key(FK_NomeUtente, FK_IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

 --CREAZIONE TABELLA REGOLANO (Tabella per gli amministartori)
create table Regolano (
 FK_NomeUtente Varchar(30),
 FK_IdGruppi Int,
 Primary key(FK_NomeUtente, FK_IdGruppi),
 FOREIGN KEY (FK_NomeUtente) REFERENCES Profili(NomeUtente),
 FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

--CREAZIONE TABELLA POSSIEDE 
create table Possiede(
FK_IdGruppi Int,
FK_Parola Varchar(20),
Primary key (FK_IdGruppi, FK_Parola),
FOREIGN KEY (FK_Parola) REFERENCES TAGS(Parola),
FOREIGN KEY (FK_IdGruppi) REFERENCES Gruppi(IdGruppi) 
);

-- Trigger

CREATE OR REPLACE TRIGGER Inc_IdGruppi 
BEFORE INSERT ON Gruppi 
FOR EACH ROW

BEGIN
  SELECT IdGruppi_seq.NEXTVAL
  INTO   :new.IdGruppi
  FROM   dual;
END;
/