-- Invia_Notifica_Online TRIGGER 

--Trigger per verificare la Data di nascita degli utenti
create or replace TRIGGER Verifica_DataNascita 
BEFORE INSERT ON Profili
FOR EACH ROW
WHEN (NEW.Data_Nascita>SYSDATE)

BEGIN
    RAISE_APPLICATION_ERROR(-20001, 'La data di nascita deve essere minore o uguale alla data attuale'); -- Eccezione che ci permette di avere un messaggio personalizzato
END;
/


--Trigger che avvisa gli utenti di un gruppo quando il creatore è online
create or replace TRIGGER Invia_Notifica_OnlineC
AFTER UPDATE ON Gruppi
FOR EACH ROW
WHEN (NEW.OnlineC = 1 AND OLD.OnlineC = 0)

DECLARE

CURSOR Rec_Utente IS (SELECT FK_Nome_Utente FROM partecipano Where FK_Nome_Gruppo= :NEW.Nome);
TMP_Utente Partecipano.FK_Nome_Utente%TYPE;
TMP_GENERE PROFILI.Genere%TYPE;
BEGIN

OPEN Rec_Utente;

LOOP

    FETCH Rec_Utente INTO TMP_Utente;
    EXIT WHEN Rec_Utente%NOTFOUND;
    
    SELECT GENERE INTO TMP_GENERE FROM PROFILI WHERE :NEW.FK_NOME_UTENTE = NOME_UTENTE;
    
    IF( TMP_GENERE = 'M' OR TMP_GENERE = 'N') THEN
        INSERT INTO Notifiche_Gruppi (Testo, FK_Nome_Gruppo, FK_Nome_Utente) VALUES ('Il creatore del gruppo '|| :NEW.Nome || ' è Online!', :NEW.Nome, TMP_Utente); 
    ELSE
        INSERT INTO Notifiche_Gruppi (Testo, FK_Nome_Gruppo, FK_Nome_Utente) VALUES ('La creatrice del gruppo '|| :NEW.Nome || ' è Online!', :NEW.Nome, TMP_Utente); 
    END IF;
END LOOP;

CLOSE Rec_Utente;

END;
/


--Trigger che aggiunge il creatore quando crea un gruppo
create or replace TRIGGER Partecipazione_Creatore
AFTER INSERT ON GRUPPI
FOR EACH ROW

BEGIN

    CREA_PARTECIPANO(:NEW.Fk_Nome_Utente, :NEW.Nome);

END;
/


--Trigger che avvisa gli utenti iscritti ad un gruppo che un utente ha messo un contenuto
create or replace TRIGGER Invia_Notifica_G
AFTER INSERT ON Contenuti
FOR EACH ROW

DECLARE 

CURSOR Rec_Utente IS (SELECT FK_Nome_Utente FROM partecipano Where FK_Nome_Gruppo= :NEW.FK_Nome_Gruppo AND FK_Nome_Utente<>:NEW.FK_Nome_Utente);
TMP_Utente Partecipano.FK_Nome_Utente%TYPE;

BEGIN

OPEN Rec_Utente;

LOOP 

    FETCH Rec_Utente INTO TMP_Utente;
    EXIT WHEN Rec_Utente%NOTFOUND;

    INSERT INTO Notifiche_Gruppi (Testo, FK_Nome_Gruppo, fk_Nome_Utente) VALUES ('Utente '|| :NEW.FK_Nome_Utente || ' ha inserito un nuovo contenuto!', :NEW.FK_Nome_Gruppo, TMP_Utente);
END LOOP;

CLOSE Rec_Utente;

END;
/


--Trigger che avvisa l'utente che un altro utente ha interagito con un like al suo post
create or replace TRIGGER Notifica_Like
AFTER INSERT ON Likes
FOR EACH ROW

DECLARE
TMP_Utente Profili.Nome_Utente%TYPE;
TMP_Testo Contenuti.Testo%TYPE;
Verifica_Notifica NUMBER;
Verifica_Creatore_Partecipa Number;


BEGIN
    SELECT FK_Nome_Utente INTO TMP_Utente FROM Contenuti WHERE Id_Contenuto=:NEW.FK_Id_Contenuto;

    -- recupera l'id del gruppo e il nome creatore, del contenuto, del commento e con questi verifica se partecipa (contando se esiste almeno 1 riga)
    SELECT COUNT(*) INTO Verifica_Notifica FROM Partecipano WHERE FK_Nome_Gruppo IN (SELECT FK_Nome_Gruppo FROM CONTENUTI WHERE Id_Contenuto = :NEW.FK_Id_Contenuto) AND FK_NOME_UTENTE IN (SELECT FK_Nome_Utente FROM CONTENUTI WHERE Id_Contenuto = :NEW.FK_Id_Contenuto);
    
    
    --se è presente almeno una riga allora...
    IF(Verifica_Notifica <> 0) THEN
        SELECT Testo INTO TMP_Testo FROM Contenuti WHERE Id_Contenuto=:NEW.FK_Id_Contenuto;
        IF(TMP_Utente<>:NEW.FK_Nome_Utente) THEN
            IF(TMP_Testo=NULL) THEN
                INSERT INTO notifiche_contenuti (Testo, fk_Id_Contenuto, fk_Nome_Utente) VALUES (:NEW.FK_Nome_Utente || ' ha messo mi piace alla tua foto', :NEW.FK_Id_Contenuto, TMP_Utente);
            ElSE
                INSERT INTO notifiche_contenuti (Testo, fk_Id_Contenuto, fk_Nome_Utente) VALUES (:NEW.FK_Nome_Utente || ' ha messo mi piace al tuo contenuto: '|| TMP_Testo, :NEW.FK_Id_Contenuto, TMP_Utente);
            END IF;
        END IF;
    END IF;

END;
/

--Trigger che avvisa l'utente che un altro utente ha interagito con un commento al suo post
create or replace TRIGGER Notifica_Commento
AFTER INSERT ON Commenti
FOR EACH ROW

DECLARE
TMP_Utente Profili.Nome_Utente%TYPE;
Verifica_Notifica NUMBER;
BEGIN
    SELECT FK_Nome_Utente INTO TMP_Utente FROM Contenuti WHERE Id_Contenuto=:NEW.FK_Id_Contenuto;

    -- recupera l'id del gruppo e il nome creatore del contenuto del commento e con questi verifica se partecipa (contando se esiste almeno 1 riga)
    SELECT COUNT(*) INTO Verifica_Notifica FROM Partecipano WHERE FK_Nome_Gruppo IN (SELECT FK_Nome_Gruppo FROM CONTENUTI WHERE Id_Contenuto = :NEW.FK_Id_Contenuto) AND FK_NOME_UTENTE IN ((SELECT FK_Nome_Utente FROM CONTENUTI WHERE Id_Contenuto = :NEW.FK_Id_Contenuto));

    --se è presente almeno una riga allora...
    IF(Verifica_Notifica <> 0) THEN

        IF(TMP_Utente<>:NEW.FK_Nome_Utente) THEN
            INSERT INTO notifiche_contenuti (Testo, fk_Id_Contenuto, fk_Nome_Utente) VALUES (:NEW.FK_Nome_Utente || ' ha commentato il tuo contenuto: '|| :NEW.Testo, :NEW.FK_Id_Contenuto, TMP_Utente);
        END IF;
    END IF;
END;
/

--Trigger che avvisa l'utente che è stato eliminato da un gruppo
create or replace TRIGGER Notifica_Eliminazione
BEFORE DELETE ON Partecipano
FOR EACH ROW

DECLARE

BEGIN

    INSERT INTO Notifiche_Gruppi (Testo, FK_Nome_Gruppo, FK_Nome_Utente) VALUES (:OLD.FK_Nome_Utente||' non fa più parte del gruppo ' || :OLD.FK_Nome_Gruppo, :OLD.FK_Nome_Gruppo, :OLD.FK_Nome_Utente);


EXCEPTION WHEN OTHERS THEN
NULL;

END;

/


--Trigger che verifica se un utente ha già messo like ad un contenuto
CREATE OR REPLACE TRIGGER Verifica_Like
BEFORE INSERT ON Likes
FOR EACH ROW

DECLARE
Check_Like NUMBER;

BEGIN

    SELECT Count(*) INTO Check_Like
    FROM Likes
    WHERE fk_id_contenuto = :NEW.fk_id_contenuto AND fk_nome_utente = :NEW.fk_nome_utente;
    
    IF(Check_Like = 1) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Hai già messo like a questo contenuto'); -- Eccezione che ci permette di avere un messaggio personalizzato
    END IF;

END;
/

--INSERISCE GLI UTENTI IN PARTECIPANO DOPO AVER ACCETTATO LA RICHIESTA
create or replace TRIGGER Accettazione_Richiesta
AFTER UPDATE ON Notifiche_richieste
FOR EACH ROW
WHEN (NEW.Esitato = '1' AND OLD.Esitato <> '1')

BEGIN

    CREA_PARTECIPANO(:OLD.Fk_Nome_Utente, :OLD.FK_Nome_Gruppo);

END;
/

--TRIGGER CHE MANDA UNA NOTIFICA A TUTTI GLI UTENTI DEL GRUPPO QUANDO UN UTENTE ELIMINA UN CONTENUTO
create or replace TRIGGER Notifca_Contenuto_Eliminato
BEFORE DELETE ON Contenuti
FOR EACH ROW

BEGIN

    crea_notifica_gruppo(:OLD.FK_Nome_Utente || ' Ha eliminato un contenuto', :OLD.FK_Nome_Gruppo, :OLD.FK_Nome_Utente);

EXCEPTION WHEN OTHERS THEN
NULL;

END;
/


-- INVIA NOTIFICA ESITO
create or replace TRIGGER Invia_Notifica_Esito
AFTER UPDATE ON Notifiche_richieste
FOR EACH ROW
WHEN (OLD.Esitato<>'1' AND NEW.Esitato='1')

DECLARE

CURSOR Rec_Nome_Utente IS (SELECT FK_Nome_Utente FROM Partecipano WHERE FK_Nome_Gruppo = :NEW.FK_Nome_Gruppo);

TMP_Nome_Utente Gruppi.FK_Nome_Utente%TYPE;
TMP_GENERE PROFILI.Genere%TYPE;

testo_Msg VARCHAR2(1000);

BEGIN

    OPEN Rec_Nome_Utente;

    LOOP

        FETCH Rec_Nome_Utente INTO TMP_Nome_Utente;
        EXIT WHEN Rec_Nome_Utente%NOTFOUND;
       
        SELECT GENERE INTO TMP_GENERE FROM PROFILI WHERE :NEW.FK_Nome_Utente = NOME_UTENTE;

        IF (TMP_GENERE LIKE 'M' OR TMP_GENERE LIKE 'N') THEN
            INSERT INTO Notifiche_Gruppi(Testo, Visualizzato, FK_Nome_Gruppo, FK_Nome_Utente) VALUES (:NEW.FK_Nome_Utente ||' è stato aggiunto nel gruppo ' || :NEW.FK_Nome_Gruppo, 0 , :NEW.FK_Nome_Gruppo, TMP_Nome_Utente); 
        ELSE 
            INSERT INTO Notifiche_Gruppi(Testo, Visualizzato, FK_Nome_Gruppo, FK_Nome_Utente) VALUES (:NEW.FK_Nome_Utente ||' è stata aggiunta nel gruppo ' || :NEW.FK_Nome_Gruppo, 0 , :NEW.FK_Nome_Gruppo, TMP_Nome_Utente);     
        END IF;
    END LOOP;
    ClOSE Rec_Nome_Utente;
    
    SELECT GENERE INTO TMP_GENERE FROM PROFILI WHERE :NEW.FK_Nome_Utente = NOME_UTENTE;
    
    IF (TMP_GENERE LIKE 'M' OR TMP_GENERE LIKE 'N') THEN
        INSERT INTO Notifiche_Gruppi(Testo, Visualizzato, FK_Nome_Gruppo, FK_Nome_Utente) VALUES ('Sei stato aggiunto nel gruppo ' || :NEW.FK_Nome_Gruppo, 0 , :NEW.FK_Nome_Gruppo, :NEW.FK_Nome_Utente); 
    ELSE
        INSERT INTO Notifiche_Gruppi(Testo, Visualizzato, FK_Nome_Gruppo, FK_Nome_Utente) VALUES ('Sei stata aggiunta nel gruppo ' || :NEW.FK_Nome_Gruppo, 0 , :NEW.FK_Nome_Gruppo, :NEW.FK_Nome_Utente); 
    END IF;
END;

/


--RIMOZIONE_REGOLANO 

create or replace TRIGGER RIMOZIONE_REGOLANO
BEFORE DELETE ON Partecipano
FOR EACH ROW

DECLARE

TMP_VERIFICA NUMBER;

BEGIN
        SELECT COUNT(*) INTO TMP_VERIFICA FROM REGOLANO WHERE :OLD.FK_NOME_UTENTE = FK_NOME_UTENTE AND :OLD.FK_NOME_GRUPPO = FK_NOME_GRUPPO;
        
        
        IF(TMP_VERIFICA = 1) THEN
            DELETE FROM REGOLANO WHERE :OLD.FK_NOME_UTENTE = FK_NOME_UTENTE AND :OLD.FK_NOME_GRUPPO = FK_NOME_GRUPPO;
        END IF;
        
EXCEPTION WHEN OTHERS THEN
NULL;

END;
/


--PROCEDURE PER LA RIMOZIONE COMMENTO
create or replace PROCEDURE Rimozione_Commento(P_Id_Commento IN COMMENTI.Id_Commento%TYPE)
AS

BEGIN

    DELETE FROM COMMENTI WHERE ID_COMMENTO = P_Id_Commento;
    
END Rimozione_Commento;
/

--PROCEDURE PER LA RIMOZIONE LIKE
create or replace  PROCEDURE Rimozione_Like(P_Nome_Utente IN LIKES.FK_NOME_UTENTE%TYPE, P_ID_CONTENUTO IN LIKES.FK_ID_CONTENUTO%TYPE)
AS

BEGIN

    DELETE FROM LIKES WHERE FK_ID_CONTENUTO = P_ID_CONTENUTO AND FK_NOME_UTENTE = P_Nome_Utente;

END Rimozione_Like;
/


-- RICERCA GRUPPO DA UNA STRINGA
create or replace PROCEDURE Ricerca_Gruppo(P_NOME IN VARCHAR2)
AS 

CURSOR Rec_Gruppi IS SELECT NOME FROM GRUPPI WHERE NOME LIKE '%' || P_NOME || '%';


TMP_Gruppo Gruppi.Nome%TYPE;

BEGIN
    OPEN Rec_Gruppi;

    LOOP
        FETCH Rec_Gruppi INTO TMP_Gruppo;
        EXIT WHEN Rec_Gruppi%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(TMP_Gruppo);

    END LOOP;
    CLOSE Rec_Gruppi;

END Ricerca_Gruppo;
/

--RIMOZIONE CONTENUTO
create or replace PROCEDURE Rimozione_Contenuto(P_Id_Contenuto IN CONTENUTI.Id_Contenuto%TYPE)
AS

BEGIN

    DELETE FROM CONTENUTI WHERE ID_CONTENUTO = P_Id_Contenuto;

END Rimozione_Contenuto;
/


--MODIFICA UN SINGOLO ATTRIBUTO DELLA TABELLA PROFILI
create or replace  PROCEDURE Modifica_Profilo(Campo IN VARCHAR2, Val_NEW IN VARCHAR2, P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

Comando VARCHAR(1000);


BEGIN
    Comando:='UPDATE Profili SET '||Campo||' =  '''||Val_New||''' WHERE Nome_Utente = '''||P_Nome_Utente||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
    EXECUTE IMMEDIATE Comando;
END Modifica_Profilo;
/


--MOSTRA TUTTE LE NOTIFICHE DELLE RICHIESTE DI PARTECIPAZIONE AL CREATORE DEL GRUPPO
create or replace PROCEDURE Mostra_Richiesta (P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

CURSOR Rec_Gruppo_C IS (SELECT Nome From Gruppi Where FK_Nome_Utente = P_Nome_Utente);

TMP_Nome_Gruppo Gruppi.Nome%TYPE;
TMP_Testo Notifiche_richieste.Testo%TYPE;

CURSOR Rec_Testo IS (SELECT Testo FROM Notifiche_richieste WHERE TMP_Nome_Gruppo = Notifiche_richieste.FK_Nome_Gruppo AND Notifiche_richieste.Esitato = '0');

BEGIN

    OPEN Rec_Gruppo_C;
    
    LOOP

        FETCH Rec_Gruppo_C INTO TMP_Nome_Gruppo;
        EXIT WHEN Rec_Gruppo_C%NOTFOUND;
       
        
        OPEN Rec_Testo;
        
        LOOP
            FETCH Rec_Testo INTO TMP_Testo;
            EXIT WHEN Rec_Testo%NOTFOUND;
            
            DBMS_OUTPUT.PUT_LINE(TMP_Testo);

        END LOOP;
        CLOSE Rec_Testo;



    END LOOP;

    CLOSE Rec_Gruppo_C;

EXCEPTION
WHEN OTHERS THEN
NULL;


END Mostra_Richiesta;
/

--MOSTRA TUTTI I LIKE E I COMMENTI DI UN ID_COMMNETO 
create or replace PROCEDURE Mostra_Like_Commento (P_Id_Contenuto IN CONTENUTI_CON_LIKES.ID_CONTENUTO%TYPE)
AS


CURSOR Rec_Commento IS SELECT Testo, FK_Nome_Utente FROM COMMENTI WHERE FK_ID_CONTENUTO = P_Id_Contenuto;



TMP_N_LIKE CONTENUTI_CON_LIKES.N_LIKE%TYPE;
TMP_Testo CONTENUTI_CON_LIKES.Testo%TYPE;
TMP_Nome_Utente CONTENUTI_CON_LIKES.FK_Nome_Utente%TYPE;

BEGIN
    SELECT N_LIKE INTO TMP_N_LIKE FROM CONTENUTI_CON_LIKES WHERE ID_CONTENUTO = P_Id_Contenuto;

    DBMS_OUTPUT.PUT_LINE('LIKES : ' || TMP_N_LIKE);
    
    OPEN Rec_Commento;
    LOOP
        FETCH Rec_Commento INTO TMP_Testo, TMP_Nome_Utente;
        EXIT WHEN Rec_Commento%NOTFOUND;
    
        DBMS_OUTPUT.PUT_LINE( TMP_Nome_Utente || ' : '|| TMP_Testo);
    
    END LOOP;
    CLOSE Rec_Commento;
    
END Mostra_Like_Commento;
/



--MOSTRA TUTTE LE RICHIESTE SUI GRUPPI DOVE L'UTENTE E' STATO ACCETTATO O HA ACCETATTO UN ALTRO UTENTE 
create or replace NONEDITIONABLE PROCEDURE Mostra_Archiviata (P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

CURSOR Rec_Gruppo_C IS (SELECT Nome From Gruppi Where FK_Nome_Utente = P_Nome_Utente);

TMP_Nome_Gruppo Gruppi.Nome%TYPE;
TMP_Testo NOTIFICHE_RICHIESTE.Testo%TYPE;

        -- Recupero testo di Notifiche di cui HO RIFIUTATO
CURSOR Rec_Testo IS (  SELECT Testo
            INTO TMP_Testo FROM NOTIFICHE_RICHIESTE 
            WHERE FK_Nome_Gruppo = TMP_Nome_Gruppo
            AND Esitato <> '0'
            AND FK_Nome_Utente <> P_Nome_Utente);

            -- Recupero testo di Notifiche di rifiuto e accettato
CURSOR Rec_Testo2 IS (  SELECT Testo
            INTO TMP_Testo FROM NOTIFICHE_RICHIESTE 
            WHERE 
            Esitato <> '0'
            AND FK_Nome_Utente = P_Nome_Utente);
       

  
BEGIN

    OPEN Rec_Gruppo_C;

    LOOP

        FETCH Rec_Gruppo_C INTO TMP_Nome_Gruppo;
        EXIT WHEN Rec_Gruppo_C%NOTFOUND;

            OPEN Rec_Testo;
            LOOP
                FETCH Rec_Testo INTO TMP_Testo;
                EXIT WHEN Rec_Testo%NOTFOUND;
            
                DBMS_OUTPUT.PUT_LINE(TMP_Testo);
            
            END LOOP;
            CLOSE Rec_Testo;
            

            
    END LOOP;
    CLOSE Rec_Gruppo_C;
    
    
     OPEN Rec_Testo2;
        LOOP
            FETCH Rec_Testo2 INTO TMP_Testo;
            EXIT WHEN Rec_Testo2%NOTFOUND;
        
            DBMS_OUTPUT.PUT_LINE(TMP_Testo);
        
        END LOOP;
        CLOSE Rec_Testo2;

EXCEPTION
WHEN OTHERS THEN
NULL;


END Mostra_Archiviata;
/



-- PROCEDURE PER RIFIUTARE UN PROFILO
create or replace NONEDITIONABLE PROCEDURE Rifiuta_Profilo(P_FK_Nome_Utente IN NOTIFICHE_RICHIESTE.FK_Nome_Utente%TYPE, P_FK_NOME_GRUPPO IN NOTIFICHE_RICHIESTE.FK_Nome_Gruppo%TYPE)
AS

Comando VARCHAR(1000);
Tmp_Notifica NOTIFICHE_RICHIESTE.id_notifica_re%TYPE;

TMP_GENERE PROFILI.Genere%TYPE;
BEGIN


    -- recupero l'id della notifica dell'utente che ha fatto richiesta al gruppo e che deve ancora avere una risposta (esitato = 0)
    SELECT id_notifica_re INTO TMP_Notifica
    FROM NOTIFICHE_RICHIESTE
    WHERE FK_Nome_Gruppo=P_FK_NOME_GRUPPO AND fk_nome_utente=P_FK_Nome_Utente AND Esitato='0';
    
    SELECT GENERE INTO TMP_GENERE FROM PROFILI WHERE P_FK_Nome_Utente = NOME_UTENTE;
    
    IF (TMP_GENERE = 'M' OR TMP_GENERE = 'N') THEN
        Comando := 'UPDATE NOTIFICHE_RICHIESTE SET Esitato = ''2'', TESTO = ''Rifiutato ' || P_FK_Nome_Utente || ' nel gruppo :' || P_FK_NOME_GRUPPO || '''  WHERE id_notifica_re = '''||TMP_Notifica||'''';
    ELSE
        Comando := 'UPDATE NOTIFICHE_RICHIESTE SET Esitato = ''2'', TESTO = ''Rifiutata ' || P_FK_Nome_Utente || ' nel gruppo :' || P_FK_NOME_GRUPPO || '''  WHERE id_notifica_re = '''||TMP_Notifica||'''';    
    END IF;
    EXECUTE IMMEDIATE Comando;

END Rifiuta_Profilo;
/




-- CREA PARTECIPANO
create or replace PROCEDURE Crea_Partecipano (P_Nome_Utente IN PARTECIPANO.FK_NOME_UTENTE%TYPE, P_Nome_Gruppo IN PARTECIPANO.FK_Nome_Gruppo%TYPE)
AS

BEGIN
    INSERT INTO Partecipano VALUES (P_Nome_Utente, P_Nome_Gruppo);
END Crea_Partecipano;
/

-- CREA REGOLANO
create or replace PROCEDURE Crea_Regolano (P_Nome_Utente IN Regolano.FK_NOME_UTENTE%TYPE, P_Nome_Gruppo IN Regolano.FK_Nome_Gruppo%TYPE)
AS

BEGIN
    INSERT INTO Regolano VALUES (P_Nome_Utente, P_Nome_Gruppo);
END Crea_Regolano;
/

-- CREA LIKES
create or replace PROCEDURE Crea_Like (P_FK_Nome_Utente IN Likes.FK_NOME_UTENTE%TYPE, P_FK_Id_Contenuto IN Likes.FK_Id_Contenuto%TYPE)
AS

Verifica_Partecipano NUMBER;
Trova_Gruppo Gruppi.Nome%TYPE;

BEGIN
    
    SELECT FK_Nome_Gruppo INTO Trova_Gruppo
    FROM Contenuti 
    WHERE Id_Contenuto = P_FK_Id_Contenuto;

    --Verifico se l'utente che mette LIKE partecipa effettivamente al gruppo (se si conta 1 riga)
    SELECT COUNT(*) INTO Verifica_Partecipano 
    FROM Partecipano 
    WHERE FK_Nome_Gruppo = Trova_Gruppo AND FK_Nome_Utente = P_FK_Nome_Utente;

   IF (Verifica_Partecipano = 1) THEN 
     INSERT INTO Likes VALUES (P_FK_Nome_Utente, P_FK_Id_Contenuto);
   ELSE
     DBMS_OUTPUT.PUT_LINE('Non partecipi al gruppo');
   END IF;
    
END Crea_Like;
/

-- CREA POSSIEDONO

create or replace PROCEDURE Crea_Possiedono (P_Nome_Gruppo IN Possiedono.FK_Nome_Gruppo%TYPE, P_FK_Parola IN Possiedono.FK_Parola%TYPE )
AS

BEGIN
    INSERT INTO Possiedono VALUES (P_Nome_Gruppo, P_FK_Parola);
END Crea_Possiedono;
/


-- RIMOZIONE DI TUTTI I COMMENTI DI UN PROFILO IN UN GRUPPO
create or replace NONEDITIONABLE PROCEDURE Rimozione_Commento_Profilo(P_Nome_Utente COMMENTI.FK_Nome_Utente%TYPE, P_Nome_Gruppo GRUPPI.Nome%TYPE)
AS


CURSOR Rec_Id_Con IS SELECT ID_CONTENUTO FROM CONTENUTI WHERE FK_Nome_Gruppo = P_Nome_Gruppo;

TMP_Id_Con COMMENTI.FK_ID_CONTENUTO%TYPE;

BEGIN

    OPEN Rec_Id_Con;

    LOOP
        FETCH Rec_Id_Con INTO TMP_Id_Con;
        EXIT WHEN Rec_Id_Con%NOTFOUND;

        DELETE FROM COMMENTI WHERE FK_ID_CONTENUTO = TMP_Id_Con AND FK_NOME_UTENTE = P_Nome_Utente;

    END LOOP;
    CLOSE Rec_Id_Con;

END Rimozione_Commento_Profilo;
/


-- RIMOZIONE DI TUTTI I LIKE DI UN PROFILO IN UN GRUPPO
create or replace PROCEDURE Rimozione_Like_Profilo(P_Nome_Utente COMMENTI.FK_Nome_Utente%TYPE, P_Nome_Gruppo GRUPPI.Nome%TYPE)
AS


CURSOR Rec_Id_Con IS SELECT ID_CONTENUTO FROM CONTENUTI WHERE FK_Nome_Gruppo = P_Nome_Gruppo;

TMP_Id_Con LIKES.FK_ID_CONTENUTO%TYPE;

BEGIN

    OPEN Rec_Id_Con;

    LOOP
        FETCH Rec_Id_Con INTO TMP_Id_Con;
        EXIT WHEN Rec_Id_Con%NOTFOUND;

        DELETE FROM LIKES WHERE FK_ID_CONTENUTO = TMP_Id_Con AND FK_NOME_UTENTE = P_Nome_Utente;

    END LOOP;
    CLOSE Rec_Id_Con;

END Rimozione_Like_Profilo;
/

-- RIMOZIONE DI TUTTI I CONTENUTI DI UN PROFILO IN UN GRUPPO
create or replace PROCEDURE Rimozione_Contenuto_Profilo(P_Nome_Utente CONTENUTI.FK_Nome_Utente%TYPE, P_Nome_Gruppo GRUPPI.Nome%TYPE)
AS

BEGIN

    DELETE FROM CONTENUTI WHERE FK_NOME_UTENTE = P_Nome_Utente AND FK_Nome_Gruppo = P_Nome_Gruppo;

END Rimozione_Contenuto_Profilo;
/



-- PROCEDURE PER L'AGGIUNTA DI UN UTENTE NELLA TABALLA PROFILI
create or replace PROCEDURE Crea_Profilo (P_Nome_Utente IN Profili.Nome_Utente%TYPE, P_Password IN Profili.Password%TYPE, P_Nome IN Profili.Nome%TYPE, P_Cognome IN Profili.Cognome%TYPE, P_Genere IN Profili.Genere%TYPE, P_Data_Nascita IN Profili.Data_Nascita%TYPE)
AS

BEGIN
    INSERT INTO Profili VALUES (P_Nome_Utente, P_Password, P_Nome, P_Cognome, P_Genere, P_Data_Nascita);
END Crea_Profilo;
/

-- PROCEDURE PER L'AGGIUNTA DI UN GRUPPO NELLA TABALLA GRUPPI
create or replace PROCEDURE Crea_Gruppo (P_Nome IN Gruppi.Nome%TYPE, P_Descrizione IN Gruppi.Descrizione%TYPE, P_FK_Nome_Utente IN Gruppi.FK_Nome_Utente%TYPE)
AS

BEGIN
    INSERT INTO Gruppi (Nome, Descrizione, FK_Nome_Utente) VALUES (P_Nome, P_Descrizione, P_FK_Nome_Utente);
END Crea_Gruppo;
/

-- PROCEDURE PER L'AGGIUNTA DI UN TAG NELLA TABALLA TAGS
create or replace PROCEDURE Crea_Tag (P_Parola IN Tags.Parola%TYPE)
AS

BEGIN
    INSERT INTO Tags (Parola) VALUES (P_Parola);
END Crea_Tag;
/

-- PROCEDURE PER L'AGGIUNTA DI UN CONTENUTO NELLA TABALLA CONTENUTI
create or replace PROCEDURE Crea_Contenuto (P_Foto IN Contenuti.Foto%TYPE, P_Testo IN Contenuti.Testo%TYPE, P_FK_NOME_GRUPPO IN Contenuti.FK_Nome_Gruppo%TYPE, P_FK_Nome_Utente IN Contenuti.FK_Nome_Utente%TYPE)
AS

Verifica_Partecipano NUMBER;

BEGIN


    --Verifico se l'utente partecipa effettivamente al gruppo (se si conta 1 riga)
    SELECT COUNT(*) INTO Verifica_Partecipano 
    FROM Partecipano 
    WHERE FK_Nome_Gruppo = P_FK_NOME_GRUPPO AND FK_Nome_Utente = P_FK_Nome_Utente;

    IF (Verifica_Partecipano = 1) THEN
        INSERT INTO Contenuti (Foto, Testo, FK_Nome_Gruppo, FK_Nome_Utente) VALUES (P_Foto, P_Testo, P_FK_NOME_GRUPPO, P_FK_Nome_Utente);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non partecipi al gruppo');
    END IF;

END Crea_Contenuto;

/


--Procedure rimozione regolano
create or replace PROCEDURE Rimozione_Regolano(P_FK_Nome_Gruppo REGOLANO.FK_Nome_Gruppo%TYPE, P_FK_Nome_Utente REGOLANO.FK_Nome_Utente%TYPE)
AS

BEGIN

    DELETE FROM REGOLANO WHERE FK_NOME_UTENTE = P_FK_Nome_Utente  AND FK_NOME_GRUPPO =  P_FK_Nome_Gruppo;

END Rimozione_Regolano;
/


--Procedure rimozione possiedono
create or replace PROCEDURE Rimozione_Possiedono(P_Nome_Gruppo GRUPPI.Nome%TYPE, P_Parola tags.parola%TYPE)
AS

BEGIN

    DELETE FROM Possiedono WHERE FK_Parola = P_Parola AND FK_Nome_Gruppo = P_Nome_Gruppo;

END Rimozione_Possiedono;
/



--Procedure per la creazione dei commenti
create or replace PROCEDURE Crea_Commento(P_Testo IN Commenti.Testo%TYPE, P_FK_Id_Contenuto IN Commenti.FK_Id_Contenuto%TYPE, P_FK_Nome_Utente IN Commenti.FK_Nome_Utente%TYPE)
AS 
  
Verifica_Partecipano NUMBER;
Trova_Gruppo Gruppi.Nome%TYPE;

BEGIN
    
    SELECT FK_Nome_Gruppo INTO Trova_Gruppo
    FROM Contenuti 
    WHERE Id_Contenuto = P_FK_Id_Contenuto;

    --Verifico se l'utente che mette il commento partecipa effettivamente al gruppo (se si conta 1 riga)
    SELECT COUNT(*) INTO Verifica_Partecipano 
    FROM Partecipano 
    WHERE FK_Nome_Gruppo = Trova_Gruppo AND FK_Nome_Utente = P_FK_Nome_Utente;

   IF (Verifica_Partecipano = 1) THEN 
    INSERT INTO Commenti (Testo, FK_Id_Contenuto, FK_NOME_UTENTE) VALUES (P_Testo, P_FK_Id_Contenuto, P_FK_Nome_Utente); 
   ELSE
    DBMS_OUTPUT.PUT_LINE('Non partecipi al gruppo');
   END IF;
   
END Crea_Commento;

/

--Procedure per la creazione delle notifiche gruppo
create or replace PROCEDURE Crea_Notifica_Gruppo(P_Testo IN Notifiche_Gruppi.Testo%TYPE, P_FK_NOME_GRUPPO IN Notifiche_Gruppi.FK_Nome_Gruppo%TYPE, P_FK_Nome_Utente IN Notifiche_Gruppi.FK_Nome_Utente%TYPE)
AS

BEGIN

   INSERT INTO Notifiche_Gruppi(Testo,FK_Nome_Gruppo, FK_Nome_Utente) VALUES (P_Testo,P_FK_NOME_GRUPPO, P_FK_Nome_Utente);

END Crea_Notifica_Gruppo;
/

--Procedure per la creazione delle notifiche contenuto
create or replace PROCEDURE Crea_Notifica_Contenuto(P_Testo IN Notifiche_Contenuti.Testo%TYPE, P_FK_Id_Contenuto IN Notifiche_Contenuti.FK_Id_Contenuto%TYPE, P_FK_Nome_Utente IN Notifiche_Contenuti.FK_Nome_Utente%TYPE)
AS 
  
BEGIN 

   INSERT INTO Notifiche_Contenuti (Testo, FK_Id_Contenuto, FK_NOME_UTENTE) VALUES (P_Testo, P_FK_Id_Contenuto, P_FK_Nome_Utente); 

END Crea_Notifica_Contenuto;
/

--Procedure per creare la notifica della richiesta
create or replace PROCEDURE Crea_Richiesta(P_FK_NOME_GRUPPO IN Notifiche_richieste.FK_Nome_Gruppo%TYPE , P_FK_Nome_Utente IN Notifiche_richieste.FK_Nome_Utente%TYPE)
AS 

TMP_Nome Gruppi.Nome%TYPE;
Verifica_Richiesta NUMBER;
Verifica_Richiesta2 NUMBER;
BEGIN 
    SELECT Nome INTO TMP_Nome FROM Gruppi WHERE P_FK_NOME_GRUPPO = Nome;


    --Verifico Se ho già mandato 1 richiesta che deve ancora avere risposta al gruppo (se si conta 1 riga)
    SELECT Count(*) INTO Verifica_Richiesta
    FROM Notifiche_richieste
    WHERE 
    (Notifiche_richieste.fk_nome_utente = p_fk_nome_utente
    AND Notifiche_richieste.FK_Nome_Gruppo = P_FK_NOME_GRUPPO
    AND Notifiche_richieste.Esitato = '0');

    --Verifico Se sto mandando una richiesta ad un gruppo di cui già faccio parte (se si conta 1 riga)
    SELECT Count(*) INTO Verifica_Richiesta2
    FROM Partecipano
    WHERE 
    (Partecipano.fk_nome_utente = p_fk_nome_utente
    AND Partecipano.FK_Nome_Gruppo = P_FK_NOME_GRUPPO);
    
    
    IF (Verifica_Richiesta2 <> 0) THEN
        DBMS_OUTPUT.PUT_LINE('partecipi già al gruppo');
    ELSIF (Verifica_Richiesta <> 0) THEN
        DBMS_OUTPUT.PUT_LINE('Hai già inviato una richiesta al gruppo');
    ELSE
        INSERT INTO Notifiche_richieste(Testo, FK_Nome_Gruppo, FK_Nome_Utente) VALUES (P_FK_Nome_Utente || ' Ha inviato una richiesta al gruppo: ' || TMP_Nome, P_FK_NOME_GRUPPO, P_FK_Nome_Utente);
    END IF;
    

END Crea_Richiesta;
/

-- Procedure per Mostrare le notifiche dei gruppi e dei contenuti di un utente
create or replace NONEDITIONABLE PROCEDURE Mostra_Notifica(P_Nome_Utente IN Notifiche_Contenuti.FK_Nome_Utente%TYPE, P_Rec_Notifica OUT SYS_REFCURSOR)
AS 

BEGIN 

    OPEN P_Rec_Notifica FOR 
        SELECT  NULL AS Id_Notifica_G, Id_Notifica_C ,Visualizzato, Testo, Data_Notifica, NULL AS FK_Nome_Gruppo FROM Notifiche_Contenuti WHERE fk_nome_utente LIKE P_Nome_Utente
        UNION ALL
        SELECT Id_Notifica_G, NULL AS Id_Notifica_C,Visualizzato, Testo, Data_Notifica, FK_Nome_Gruppo FROM Notifiche_Gruppi WHERE FK_NOME_UTENTE LIKE P_Nome_Utente
        ORDER BY Data_Notifica DESC;

END Mostra_Notifica;

/

--Procedure per Visualizzare le notifiche dei contenuti e dei gruppi di un utente
create or replace NONEDITIONABLE PROCEDURE Visualizzato_Notifica(P_Rec_Notifiche IN SYS_REFCURSOR)

AS 

TMP_Testo Notifiche_Contenuti.Testo%TYPE;
TMP_Data Notifiche_Contenuti.Data_Notifica%TYPE;
TMP_Visualizzato Notifiche_Contenuti.Visualizzato%TYPE;
TMP_Id_Notifica_C Notifiche_Contenuti.Id_Notifica_C%TYPE;
TMP_Id_Notifica_G Notifiche_Gruppi.Id_Notifica_G%TYPE;


BEGIN 

        LOOP
            FETCH P_Rec_Notifiche INTO TMP_Id_Notifica_G,TMP_Id_Notifica_C,TMP_Visualizzato,TMP_Testo, TMP_Data;
            EXIT WHEN P_Rec_Notifiche%NOTFOUND;

            IF TMP_Id_Notifica_C IS NOT NULL AND TMP_Id_Notifica_C <> 0 THEN
                UPDATE Notifiche_Contenuti SET Visualizzato = '1' WHERE Id_Notifica_C = TMP_Id_Notifica_C;
            ELSIF TMP_Id_Notifica_G IS NOT NULL AND TMP_Id_Notifica_G <> 0 THEN
                 UPDATE Notifiche_Gruppi SET Visualizzato = '1' WHERE Id_Notifica_G = TMP_Id_Notifica_G;
            END IF;

        END LOOP;
        
        CLOSE P_Rec_Notifiche;

  COMMIT;

END Visualizzato_Notifica;
/


--MODIFICA IL GRUPPO SOLO SE SEI IL CREATORE
create or replace PROCEDURE Modifica_Gruppo(Campo IN VARCHAR2, Val_NEW IN VARCHAR2, P_FK_Nome_Utente IN Gruppi.FK_Nome_Utente%TYPE, P_Nome_Gruppo IN Gruppi.Nome%TYPE)
AS

Comando VARCHAR(1000);
TMP_Creatore Gruppi.FK_Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Creatore
    FROM Gruppi
    WHERE Nome = P_Nome_Gruppo;


    IF (TMP_Creatore LIKE P_FK_Nome_Utente) THEN
        Comando:='UPDATE Gruppi SET '||Campo||' =  '''||Val_New||''' WHERE Nome = '''||P_Nome_Gruppo||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
        EXECUTE IMMEDIATE Comando;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non sei il creatore del gruppo gruppo');
    END IF;

END Modifica_Gruppo;
/


--MODIFICA IL CONTENUTO SOLO SE SEI IL CREATORE
create or replace NONEDITIONABLE PROCEDURE Modifica_Contenuto(Val_NEW IN VARCHAR2, P_FK_Nome_Utente IN Contenuti.FK_Nome_Utente%TYPE, P_Id_Contenuto IN Contenuti.Id_Contenuto%TYPE)
AS

Comando VARCHAR(1000);
TMP_Creatore Contenuti.FK_Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Creatore
    FROM Contenuti
    WHERE Id_Contenuto = P_Id_Contenuto;


    IF (TMP_Creatore LIKE P_FK_Nome_Utente) THEN
        Comando:='UPDATE Contenuti SET Testo =  '''||Val_New||''' WHERE Id_Contenuto = '''||P_Id_Contenuto||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
        EXECUTE IMMEDIATE Comando;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non sei il creatore del contenuto');
    END IF;

END Modifica_Contenuto;
/

--MODIFICA IL COMMENTO SOLO SE SEI IL CREATORE
create or replace PROCEDURE Modifica_Commento(Val_NEW IN VARCHAR2, P_FK_Nome_Utente IN Commenti.FK_Nome_Utente%TYPE, P_Id_Commento IN Commenti.Id_Commento%TYPE)
AS

Comando VARCHAR(1000);
TMP_Creatore Contenuti.FK_Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Creatore
    FROM Commenti
    WHERE Id_Commento = P_Id_Commento;


    IF (TMP_Creatore LIKE P_FK_Nome_Utente) THEN
        Comando:='UPDATE Commenti SET Testo =  '''||Val_New||''' WHERE Id_Commento = '''||P_Id_Commento||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
        EXECUTE IMMEDIATE Comando;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non sei il creatore del commento');
    END IF;

END Modifica_Commento;
/

--ACCETTA LA RICHIESTA DI PARTECIPAZIONE AD UN GRUPPO DA PARTE DELL'UTENTE 
create or replace NONEDITIONABLE PROCEDURE Accetta_Profilo(P_FK_Nome_Utente IN NOTIFICHE_RICHIESTE.FK_Nome_Utente%TYPE, P_FK_NOME_GRUPPO IN NOTIFICHE_RICHIESTE.FK_Nome_Gruppo%TYPE)
AS

Comando VARCHAR(1000);
Tmp_Notifica NOTIFICHE_RICHIESTE.id_notifica_re%TYPE;
TMP_GENERE PROFILI.Genere%TYPE;

BEGIN



    --Recupero l'id della notifica dell utente X che non ha ancora avuto risposta (esitato = 0)
    SELECT id_notifica_re INTO TMP_Notifica
    FROM NOTIFICHE_RICHIESTE
    WHERE FK_Nome_Gruppo=P_FK_NOME_GRUPPO AND fk_nome_utente=P_FK_Nome_Utente AND Esitato='0';
    
    
    SELECT GENERE INTO TMP_GENERE FROM PROFILI WHERE P_FK_Nome_Utente = NOME_UTENTE;
    
     IF (TMP_GENERE = 'M' OR TMP_GENERE = 'N') THEN
        Comando := 'UPDATE NOTIFICHE_RICHIESTE SET Esitato = ''1'', TESTO = ''Accettato ' || P_FK_Nome_Utente || ' nel gruppo :' || P_FK_NOME_GRUPPO || '''  WHERE id_notifica_re = '''||TMP_Notifica||'''';
    ELSE
        Comando := 'UPDATE NOTIFICHE_RICHIESTE SET Esitato = ''1'', TESTO = ''Accettata ' || P_FK_Nome_Utente || ' nel gruppo :' || P_FK_NOME_GRUPPO || '''  WHERE id_notifica_re = '''||TMP_Notifica||'''';
    END IF;
    EXECUTE IMMEDIATE Comando;

END Accetta_Profilo;

/

--L'UTENTE ABBANDONA O VIENE RIMOSSO DAL GRUPPO
create or replace PROCEDURE Abbandona_Gruppo(P_FK_Nome_Utente IN Partecipano.FK_Nome_Utente%TYPE, P_FK_NOME_GRUPPO IN Partecipano.FK_Nome_Gruppo%TYPE)
AS

Comando VARCHAR(1000);
TMP_Creatore Partecipano.FK_Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Creatore
    FROM Gruppi
    WHERE Nome = P_FK_NOME_GRUPPO;

        IF(TMP_Creatore LIKE P_FK_Nome_Utente) THEN
            Comando:='DELETE FROM Gruppi WHERE Nome = '''||P_FK_NOME_GRUPPO||'''AND fk_nome_utente ='''|| P_FK_Nome_Utente||''''; -- Si usano le virgole ('') prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
            EXECUTE IMMEDIATE Comando;
        ELSE
            Comando:='DELETE FROM Partecipano WHERE FK_Nome_Gruppo = '''||P_FK_NOME_GRUPPO||'''AND fk_nome_utente ='''|| P_FK_Nome_Utente||''''; -- Si usano le virgole ('') prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
            EXECUTE IMMEDIATE Comando;
        END IF;
        
END Abbandona_Gruppo;
/

--IL CREATORE ELIMINA IL GRUPPO
create or replace NONEDITIONABLE PROCEDURE Rimozione_Gruppo(P_Nome_Gruppo IN Gruppi.Nome%TYPE)
AS



BEGIN

    DELETE FROM Gruppi WHERE Nome = P_Nome_Gruppo;
    
END Rimozione_Gruppo;
/

--L'UTENTE ELIMINA IL PROFOILO
create or replace PROCEDURE Rimozione_Profilo(P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

BEGIN

    DELETE FROM Profili WHERE nome_utente = p_nome_utente;

END Rimozione_Profilo;
/

--RITORNA TUTTE LE RICHIESTE SUI GRUPPI DOVE L'UTENTE E' STATO ACCETTATO O HA ACCETATTO UN ALTRO UTENTE 
create or replace NONEDITIONABLE FUNCTION Mostra_Archiviata_F(P_Nome_Utente IN Profili.Nome_Utente%TYPE, TMP_Nome_Gruppo IN Gruppi.Nome%TYPE)
RETURN SYS_REFCURSOR AS
    rc SYS_REFCURSOR;
BEGIN
    OPEN rc FOR
    SELECT *
    FROM (
        (SELECT *
         FROM NOTIFICHE_RICHIESTE 
         WHERE FK_Nome_Gruppo = TMP_Nome_Gruppo AND Esitato <> '0'
         AND FK_Nome_Utente <> P_Nome_Utente) 

        UNION ALL

        (SELECT *
         FROM NOTIFICHE_RICHIESTE 
         WHERE Esitato <> '0'
         AND FK_Nome_Utente = P_Nome_Utente)
    )
    ORDER BY DATA_NOTIFICA;

    RETURN rc;
END;
/



--Rimozione di un tag dal gruppo
create or replace PROCEDURE Rimozione_Tag(ParolaTag IN TAGS.Parola%TYPE)
AS

BEGIN

    DELETE FROM TAGS WHERE Parola = ParolaTag;

END Rimozione_Tag;
/