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
 Id_Gruppo NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), 
 Nome VARCHAR2(30) NOT NULL UNIQUE,
 Data_Creazione Date DEFAULT SYSDATE,
 Descrizione VARCHAR2(100) NOT NULL,
 OnlineC NUMBER(1) DEFAULT 0, --0 offline, 1 online
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Gruppo),
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
 FK_Id_Gruppo NUMBER NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Contenuto),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Gruppo) REFERENCES Gruppi(Id_Gruppo) ON DELETE CASCADE,
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
 FK_Id_Gruppo NUMBER NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Notifica_G),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Gruppo) REFERENCES Gruppi(Id_Gruppo) ON DELETE CASCADE
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

--CREAZIONE TABELLA NOTIFICHE_RICHIESTE_ESITI
 CREATE TABLE Notifiche_Richieste_Esiti(
 Id_Notifica_RE NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
 Testo VARCHAR2(1000) NOT NULL,
 Data_Notifica TIMESTAMP DEFAULT SYSTIMESTAMP,
 Esitato CHAR(1) DEFAULT '0' CHECK(Esitato='0' OR Esitato='1' OR Esitato='2' OR Esitato='3'), --0 non risposto, 1 accettato, 2 rifutato , 3 notifica di esito
 FK_Id_Gruppo NUMBER NOT NULL,
 FK_Nome_Utente VARCHAR2(30) NOT NULL,
 Primary key (Id_Notifica_RE),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Gruppo) REFERENCES Gruppi(Id_Gruppo) ON DELETE CASCADE
);

 --CREAZIONE TABELLA PARTECIPANO
create table Partecipano (
 FK_Nome_Utente VARCHAR2(30),
 FK_Id_Gruppo NUMBER,
 Primary key(FK_Nome_Utente, FK_Id_Gruppo),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Gruppo) REFERENCES Gruppi(Id_Gruppo) ON DELETE CASCADE
);

--CREAZIONE TABELLA REGOLANO (Tabella per gli amministartori)
create table Regolano (
 FK_Nome_Utente VARCHAR2(30),
 FK_Id_Gruppo NUMBER,
 Primary key(FK_Nome_Utente, FK_Id_Gruppo),
 FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
 FOREIGN KEY (FK_Id_Gruppo) REFERENCES Gruppi(Id_Gruppo) ON DELETE CASCADE
);

--CREAZIONE TABELLA POSSIEDE 
create table Possiede(
FK_Id_Gruppo NUMBER,
FK_Parola VARCHAR2(20),
Primary key (FK_Id_Gruppo, FK_Parola),
FOREIGN KEY (FK_Parola) REFERENCES TAGS(Parola) ON DELETE CASCADE,
FOREIGN KEY (FK_Id_Gruppo) REFERENCES Gruppi(Id_Gruppo) ON DELETE CASCADE
);

--CREAZIONE TABELLA LIKES
CREATE TABLE LIKES(
FK_Nome_Utente VARCHAR2(30),
FK_Id_Contenuto NUMBER,
Primary key (FK_Nome_Utente, FK_Id_Contenuto),
FOREIGN KEY (FK_Nome_Utente) REFERENCES Profili(Nome_Utente) ON DELETE CASCADE,
FOREIGN KEY (FK_Id_Contenuto) REFERENCES Contenuti(Id_Contenuto) ON DELETE CASCADE
);


--POPOLAZIONE DB

--Popolamento Profili
Insert into Profili Values ('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'DD-MM-YYYY'));
Insert into Profili Values ('Gabbo', 'SonoIo', 'Gabriele', 'Cifuni', 'F', TO_DATE('21-4-2002', 'DD-MM-YYYY'))

--Popolamento Gruppi
Insert into Gruppi (Nome, Descrizione, fk_Nome_Utente) Values ('Fantacalcio', 'Ciao', 'Genny03cry');
Insert into Gruppi (Nome, Descrizione, fk_Nome_Utente) Values ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');

--Popolamneto NOTIFICHE_RICHIESTE_ESITI
INSERT INTO Notifiche_Richieste_Esiti (Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES ('Ha inviato richiesta', 1, 'Gabbo');


--Viste 
CREATE View Contenuti_con_Likes AS (
    SELECT Contenuti.*, (SELECT COUNT(*) FROM Likes WHERE likes.fk_Id_Contenuto=Contenuti.Id_Contenuto) AS N_LIKES FROM Contenuti
);


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

CURSOR Rec_Utente IS (SELECT FK_Nome_Utente FROM partecipano Where FK_Id_Gruppo= :NEW.Id_Gruppo);
TMP_Utente Partecipano.FK_Nome_Utente%TYPE;

BEGIN

OPEN Rec_Utente;

LOOP

    FETCH Rec_Utente INTO TMP_Utente;
    EXIT WHEN Rec_Utente%NOTFOUND;

    INSERT INTO Notifiche_Gruppi (Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES ('Il creatore del gruppo '|| :NEW.Nome || ' è Online!', :NEW.Id_Gruppo, TMP_Utente); 

END LOOP;

CLOSE Rec_Utente;

END;


--Trigger che avvisa gli utenti iscritti ad un gruppo che un utente ha messo un contenuto
create or replace TRIGGER Invia_Notifica_G
AFTER INSERT ON Contenuti
FOR EACH ROW

DECLARE 

CURSOR Rec_Utente IS (SELECT FK_Nome_Utente FROM partecipano Where fk_Id_Gruppo= :NEW.fk_Id_Gruppo);
TMP_Utente Partecipano.FK_Nome_Utente%TYPE;

BEGIN

OPEN Rec_Utente;

LOOP 

    FETCH Rec_Utente INTO TMP_Utente;
    EXIT WHEN Rec_Utente%NOTFOUND;

    INSERT INTO Notifiche_Gruppi (Testo, fk_Id_Gruppo, fk_Nome_Utente) VALUES ('Utente '|| :NEW.FK_Nome_Utente || ' ha inserito un nuovo contenuto!', :NEW.FK_Id_Gruppo, TMP_Utente);
END LOOP;

CLOSE Rec_Utente;

END;


--Trigger che avvisa l'utente che un altro utente ha interagito con un like al suo post
create or replace TRIGGER Notifica_Likes
AFTER INSERT ON Likes
FOR EACH ROW

DECLARE
TMP_Utente Profili.Nome_Utente%TYPE;
TMP_Testo Contenuti.Testo%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Utente FROM Contenuti WHERE Id_Contenuto=:NEW.FK_Id_Contenuto;
    SELECT Testo INTO TMP_Testo FROM Contenuti WHERE Id_Contenuto=:NEW.FK_Id_Contenuto;

    IF(TMP_Utente<>:NEW.FK_Nome_Utente) THEN
        IF(TMP_Testo=NULL) THEN
            INSERT INTO notifiche_contenuti (Testo, fk_Id_Contenuto, fk_Nome_Utente) VALUES (:NEW.FK_Nome_Utente || ' ha messo mi piace alla tua foto', :NEW.FK_Id_Contenuto, TMP_Utente);
        ElSE
            INSERT INTO notifiche_contenuti (Testo, fk_Id_Contenuto, fk_Nome_Utente) VALUES (:NEW.FK_Nome_Utente || ' ha messo mi piace al tuo contenuto: '|| TMP_Testo, :NEW.FK_Id_Contenuto, TMP_Utente);
        END IF;
    END IF;
END;

--Trigger che avvisa l'utente che un altro utente ha interagito con un commento al suo post
create or replace TRIGGER Notifica_Commenti
AFTER INSERT ON Commenti
FOR EACH ROW

DECLARE
TMP_Utente Profili.Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Utente FROM Contenuti WHERE Id_Contenuto=:NEW.FK_Id_Contenuto;

    IF(TMP_Utente<>:NEW.FK_Nome_Utente) THEN
        INSERT INTO notifiche_contenuti (Testo, fk_Id_Contenuto, fk_Nome_Utente) VALUES (:NEW.FK_Nome_Utente || ' ha commentato il tuo contenuto: '|| :NEW.Testo, :NEW.FK_Id_Contenuto, TMP_Utente);
    END IF;
END;

--Trigger per gestire l'esito

create or replace TRIGGER Cambio_di_Esitato
AFTER UPDATE ON Notifiche_Richieste_Esiti
FOR EACH ROW
WHEN (NEW.Esitato <> OLD.Esitato)  
BEGIN
   
    IF(:NEW.Esitato = '1') THEN
        INSERT INTO Notifiche_Richieste_Esiti (Testo, Esitato, FK_Id_Gruppo, FK_Nome_Utente) VALUES (:NEW.FK_Id_Gruppo || ' ha accettato la tua richiesta!', '3', :NEW.FK_Id_Gruppo, :NEW.FK_Nome_Utente);
        INSERT INTO Partecipano(FK_Nome_Utente, FK_Id_Gruppo) VALUES (:NEW.FK_Nome_Utente, :NEW.KF_Id_Gruppo);
    ELSE IF (:NEW.Esitato = '2') THEN
        INSERT INTO Notifiche_Richieste_Esiti (Testo, Esitato, FK_Id_Gruppo, FK_Nome_Utente) VALUES (:NEW.FK_Id_Gruppo || ' ha rifiutato la tua richiesta!', '3', :NEW.FK_Id_Gruppo, :NEW.FK_Nome_Utente);
    END IF;    
END;


-- FUNZIONI/PROCEDURE

-- CREAZIONE PROCEDURE PER L'AGGIUNTA DI UN UTENTE NELLA TABALLA PROFILI
create or replace PROCEDURE Crea_Utente (Nome_Ut IN VARCHAR2, PSW IN VARCHAR2, Nome_P IN VARCHAR2, Cognome_P IN VARCHAR2, Gen IN Char, Data_Di_Nascita IN Date)
AS

BEGIN
    INSERT INTO Profili VALUES (Nome_Ut, PSW, Nome_P, Cognome_P, Gen, Data_Di_Nascita);
END Crea_Utente;

--
CREATE OR REPLACE PROCEDURE Inserimento_Notifiche_RE (P_Testo IN Notifiche_Richieste_Esiti.Testo%TYPE, P_Esitato Notifiche_Richieste_Esiti.Esitato%TYPE,
                                                      P_FK_ID_Gruppo Notifiche_Richieste_Esiti.FK_ID_Gruppo%TYPE, P_FK_Nome_Utente Notifiche_Richieste_Esiti.FK_Nome_Utente%TYPE)

AS

BEGIN

    INSERT INTO Notifiche_Richieste_Esiti (Testo, Esitato, FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_FK_ID_Gruppo || ' ha accettato la tua richiesta!', P_Esitato, P_FK_ID_Gruppo, P_FK_Nome_Utente);

END Inserimento_Notifiche_RE;








