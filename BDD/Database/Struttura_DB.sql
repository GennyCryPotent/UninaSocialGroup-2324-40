-- CREAZIONI DELLE TABELLE

--CREAZIONE TABELLA PROFILI
 CREATE TABLE Profili(
 Nome_Utente VARCHAR2(30),
 Password VARCHAR2(30) NOT NULL CHECK(length(Password)>=8),
 Nome VARCHAR2(30) NOT NULL,
 Cognome VARCHAR2(30) NOT NULL,
 Genere CHAR(1) NOT NULL CHECK(Genere='M' OR Genere='F' OR Genere='N'),
 Data_Nascita Date NOT NULL,
 Primary key (Nome_Utente)
 );

--CREAZIONE TABELLA GRUPPI
 CREATE TABLE Gruppi(
 Nome VARCHAR2(30),
 Data_Creazione Date DEFAULT SYSDATE,
 Descrizione VARCHAR2(100) NOT NULL,
 OnlineC NUMBER(1) DEFAULT 0, --0 offline, 1 online
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Nome),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE
);

--CREAZIONE TABELLA TAGS
 CREATE TABLE Tags(
 Parola VARCHAR2(20),
 Primary key (Parola)
 );

--CREAZIONE TABELLA CONTENUTI
 CREATE TABLE Contenuti(
 Id_Contenuto NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Data_Creazione Date DEFAULT SYSDATE,
 Foto VARCHAR2(2000),
 Testo VARCHAR2(1000),
 FK_Nome_Gruppo VARCHAR2(30) NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Contenuto),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (Fk_Nome_Gruppo) REFERENCES Gruppi(Nome) ON DELETE CASCADE,
 CONSTRAINT Check_Contenuto Check(Foto<>NULL OR Testo<>NULL)
);

--CREAZIONE TABELLA COMMENTI
 CREATE TABLE Commenti(
 Id_Commento NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Data_Creazione TIMESTAMP DEFAULT SYSTIMESTAMP,
 Testo VARCHAR2(1000) NOT NULL,
 FK_Id_Contenuto NUMBER NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Commento),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Contenuto) REFERENCES Contenuti(Id_Contenuto) ON DELETE CASCADE
 );


--CREAZIONE TABELLA NOTIFICHE GRUPPI
 CREATE TABLE Notifiche_Gruppi(
 Id_Notifica_G NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Testo VARCHAR2(1000) NOT NULL,
 Data_Notifica TIMESTAMP DEFAULT SYSTIMESTAMP,
 Visualizzato CHAR(1) DEFAULT '0' CHECK(Visualizzato='0' OR Visualizzato='1'), --0 non visualizzato, 1 visualizzato
 FK_Nome_Gruppo VARCHAR2(30) NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Notifica_G),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (Fk_Nome_Gruppo) REFERENCES Gruppi(Nome) ON DELETE CASCADE
 );

 --CREAZIONE TABELLA NOTIFICHE CONTENUTI
 CREATE TABLE Notifiche_Contenuti(
 Id_Notifica_C NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Testo VARCHAR2(1000) NOT NULL,
 Data_Notifica TIMESTAMP DEFAULT SYSTIMESTAMP,
 Visualizzato CHAR(1) DEFAULT '0' CHECK(Visualizzato='0' OR Visualizzato='1'), --0 non visualizzato, 1 visualizzato
 FK_Id_Contenuto NUMBER NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Notifica_C),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Contenuto) REFERENCES Contenuti(Id_Contenuto) ON DELETE CASCADE
 );

--CREAZIONE TABELLA NOTIFICHE_RICHIESTE
 CREATE TABLE Notifiche_Richieste(
 Id_Notifica_RE NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Testo VARCHAR2(1000) NOT NULL,
 Data_Notifica TIMESTAMP DEFAULT SYSTIMESTAMP,
 Esitato CHAR(1) DEFAULT '0' CHECK(Esitato='0' OR Esitato='1' OR Esitato='2'), --0 non risposto, 1 accettato, 2 rifutato
 FK_Nome_Gruppo VARCHAR2(30) NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Notifica_RE),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (Fk_Nome_Gruppo) REFERENCES Gruppi(Nome) ON DELETE CASCADE
);

 --CREAZIONE TABELLA PARTECIPANO
create table Partecipano (
 FK_Nome_Utente VARCHAR2(30),
 FK_Nome_Gruppo VARCHAR2(30),
 Primary key(FK_Nome_Utente, Fk_Nome_Gruppo),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (Fk_Nome_Gruppo) REFERENCES Gruppi(Nome) ON DELETE CASCADE
);

--CREAZIONE TABELLA REGOLANO (Tabella per gli amministartori)
create table Regolano (
 FK_Nome_Utente VARCHAR2(30),
 FK_Nome_Gruppo VARCHAR2(30),
 Primary key(FK_Nome_Utente, Fk_Nome_Gruppo),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (Fk_Nome_Gruppo) REFERENCES Gruppi(Nome) ON DELETE CASCADE
);

--CREAZIONE TABELLA POSSIEDONO 
create table Possiedono(
FK_Nome_Gruppo VARCHAR2(30),
FK_Parola VARCHAR2(20),
Primary key (Fk_Nome_Gruppo, FK_Parola),
FOREIGN KEY (FK_Parola) REFERENCES TAGS(Parola) ON DELETE CASCADE,
FOREIGN KEY (Fk_Nome_Gruppo) REFERENCES Gruppi(Nome) ON DELETE CASCADE
);

--CREAZIONE TABELLA LIKES
CREATE TABLE LIKES(
FK_Nome_Utente VARCHAR2(30),
FK_Id_Contenuto NUMBER,
Primary key (FK_Nome_Utente, FK_Id_Contenuto),
FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
FOREIGN KEY (FK_Id_Contenuto) REFERENCES Contenuti(Id_Contenuto) ON DELETE CASCADE
);

--Vista utile per il conteggio dei like di un contenuto
CREATE View Contenuti_con_Likes AS (
    SELECT Contenuti.*, (SELECT COUNT(*) FROM Likes WHERE likes.fk_Id_Contenuto=Contenuti.Id_Contenuto) AS N_LIKE FROM Contenuti
);
