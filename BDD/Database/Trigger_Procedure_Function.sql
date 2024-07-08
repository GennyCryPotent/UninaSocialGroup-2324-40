-- TRIGGER 

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
/

--Trigger che avvisa gli utenti iscritti ad un gruppo che un utente ha messo un contenuto
create or replace TRIGGER Invia_Notifica_G
AFTER INSERT ON Contenuti
FOR EACH ROW

DECLARE 

CURSOR Rec_Utente IS (SELECT FK_Nome_Utente FROM partecipano Where fk_Id_Gruppo= :NEW.fk_Id_Gruppo AND FK_Nome_Utente<>:NEW.FK_Nome_Utente);
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
/


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
/

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
/

--Trigger che avvisa l'utente che è stato eliminato da un gruppo
create or replace NONEDITIONABLE TRIGGER Notifica_Eliminazione
AFTER DELETE ON Partecipano
FOR EACH ROW

DECLARE
TMP_Nome_Gruppo Gruppi.Nome%TYPE;

BEGIN

    SELECT Nome INTO TMP_Nome_Gruppo
    FROM Gruppi
    WHERE Id_Gruppo=:OLD.FK_Id_Gruppo;

    INSERT INTO Notifiche_Gruppi (Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (:OLD.FK_Nome_Utente||' non fa più parte del gruppo ' || TMP_Nome_Gruppo, :OLD.FK_Id_Gruppo, :OLD.FK_Nome_Utente);

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
AFTER UPDATE ON notifiche_richieste_esiti
FOR EACH ROW
WHEN (NEW.Esitato = '1' AND OLD.Esitato != '1')

BEGIN

    CREA_PARTECIPANO(:OLD.Fk_Nome_Utente, :OLD.Fk_Id_Gruppo);

END;
/

--TRIGGER CHE MANDA UNA NOTIFICA A TUTTI GLI UTENTI DEL GRUPPO QUANDO UN UTENTE ELIMINA UN CONTENUTO
create or replace NONEDITIONABLE TRIGGER Notifca_Contenuto_Eliminato
AFTER DELETE ON Contenuti
FOR EACH ROW

BEGIN

    crea_notifica_gruppo(:OLD.FK_Nome_Utente || ' Ha eliminato un contenuto', :OLD.FK_Id_Gruppo, :OLD.FK_Nome_Utente);

END;
/
--PROCEDURE IMPORTANTI

--MODIFICA UN SINGOLO ATTRIBUTO DELLA TABELLA PROFILI
create or replace NONEDITIONABLE PROCEDURE Modifica_Utente(Campo IN VARCHAR2, Val_NEW IN VARCHAR2, P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

Comando VARCHAR(1000);


BEGIN
    Comando:='UPDATE Profili SET '||Campo||' =  '''||Val_New||''' WHERE Nome_Utente = '''||P_Nome_Utente||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
    EXECUTE IMMEDIATE Comando;
END Modifica_Utente;
/


--MOSTRA TUTTE LE NOTIFICHE DELLE RICHIESTE DI PARTECIPAZIONE AL CREATORE DEL GRUPPO
create or replace NONEDITIONABLE PROCEDURE Mostra_Richiesta (P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

CURSOR Rec_Gruppo_C IS (SELECT Id_Gruppo From Gruppi Where FK_Nome_Utente = P_Nome_Utente);

TMP_Id_Gruppo Gruppi.Id_Gruppo%TYPE;
TMP_Testo notifiche_richieste_esiti.Testo%TYPE;

CURSOR Rec_Testo IS (SELECT Testo FROM notifiche_richieste_esiti WHERE TMP_Id_Gruppo = notifiche_richieste_esiti.fk_id_gruppo AND notifiche_richieste_esiti.Esitato = '0');

BEGIN

    OPEN Rec_Gruppo_C;
    
    LOOP

        FETCH Rec_Gruppo_C INTO TMP_Id_Gruppo;
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

--MOSTRA TUTTE LE RICHIESTE SUI GRUPPI DOVE L'UTENTE E' STATO ACCETTATO O HA ACCETATTO UN ALTRO UTENTE 
create or replace PROCEDURE Mostra_Archiviata (P_Nome_Utente IN Profili.Nome_Utente%TYPE)
AS

CURSOR Rec_Gruppo_C IS (SELECT Id_Gruppo From Gruppi Where FK_Nome_Utente = P_Nome_Utente);

TMP_Id_Gruppo Gruppi.Id_Gruppo%TYPE;
Rec_Testo SYS_REFCURSOR;
TMP_Testo notifiche_richieste_esiti.Testo%TYPE;

Comando VARCHAR(1000);

BEGIN

    EXECUTE IMMEDIATE 'CREATE TABLE TMP_NOTIF_GRUP_U (ID_Notifica_RE NUMBER,
                                                      Testo VARCHAR2(1000))';

    OPEN Rec_Gruppo_C;

    LOOP

        FETCH Rec_Gruppo_C INTO TMP_Id_Gruppo;
        EXIT WHEN Rec_Gruppo_C%NOTFOUND;

       EXECUTE IMMEDIATE '
            INSERT INTO TMP_NOTIF_GRUP_U (id_notifica_re, Testo) 
            SELECT Id_Notifica_RE, Testo
            FROM notifiche_richieste_esiti 
            WHERE fk_id_gruppo = :1 -- parametro di bind (1 viene sostituito con TMP_Id_Gruppo) 
            AND Esitato = ''3''
            AND FK_Nome_Utente != P_Nome_Utente
        
        ' USING TMP_Id_Gruppo;

    END LOOP;
    CLOSE Rec_Gruppo_C;
    
    EXECUTE IMMEDIATE '
            INSERT INTO TMP_NOTIF_GRUP_U (id_notifica_re, Testo) 
            SELECT Id_Notifica_RE, Testo
            FROM notifiche_richieste_esiti 
            WHERE FK_Nome_Utente = :1 -- parametro di bind (1 viene sostituito con P_Nome_Utente)
            AND Esitato = ''3''
        ' USING P_Nome_Utente;

     Comando:='SELECT Testo FROM TMP_NOTIF_GRUP_U';

     OPEN Rec_Testo FOR Comando;

     LOOP
         FETCH Rec_Testo INTO TMP_Testo;
         EXIT WHEN Rec_Testo%NOTFOUND;

         DBMS_OUTPUT.PUT_LINE(TMP_Testo);
     END LOOP;

     Close Rec_Testo;


    EXECUTE IMMEDIATE 'DROP TABLE "SYSTEM"."TMP_NOTIF_GRUP_U"';
    
    
EXCEPTION
WHEN OTHERS THEN
IF (Rec_Testo%ISOPEN) THEN
CLOSE Rec_Testo;
END IF;

EXECUTE IMMEDIATE  'DROP TABLE "SYSTEM"."TMP_NOTIF_GRUP_U"';
DBMS_OUTPUT.PUT_LINE('So morto');


END Mostra_Archiviata;
/


--PROCEDURE INSERMINETO

-- CREA PARTECIPANO
create or replace NONEDITIONABLE PROCEDURE Crea_Partecipano (P_Nome_Utente IN PARTECIPANO.FK_NOME_UTENTE%TYPE, P_Id_Gruppo IN PARTECIPANO.FK_Id_Gruppo%TYPE)
AS

BEGIN
    INSERT INTO Partecipano VALUES (P_Nome_Utente, P_Id_Gruppo);
END Crea_Partecipano;
/

-- CREA REGOLANO

create or replace NONEDITIONABLE PROCEDURE Crea_Regolano (P_Nome_Utente IN Regolano.FK_NOME_UTENTE%TYPE, P_Id_Gruppo IN Regolano.FK_Id_Gruppo%TYPE)
AS

BEGIN
    INSERT INTO Regolano VALUES (P_Nome_Utente, P_Id_Gruppo);
END Crea_Regolano;
/

-- CREA LIKES
create or replace NONEDITIONABLE PROCEDURE Crea_Like (P_Nome_Utente IN Likes.FK_NOME_UTENTE%TYPE, P_Id_Contenuto IN Likes.FK_Id_Contenuto%TYPE)
AS

BEGIN
    INSERT INTO Likes VALUES (P_Nome_Utente, P_Id_Contenuto);
END Crea_Like;
/

-- CREA POSSIEDONO

create or replace PROCEDURE Crea_Possiedono (P_Id_Gruppo IN Possiedono.FK_Id_Gruppo%TYPE, P_FK_Parola IN Possiedono.FK_Parola%TYPE )
AS

BEGIN
    INSERT INTO Possiedono VALUES (P_Id_Gruppo, P_FK_Parola);
END Crea_Possiedono;
/

-- PROCEDURE PER L'AGGIUNTA DI UN UTENTE NELLA TABALLA PROFILI
create or replace PROCEDURE Crea_Utente (P_Nome_Utente IN Profili.Nome_Utente%TYPE, P_Password IN Profili.Password%TYPE, P_Nome IN Profili.Nome%TYPE, P_Cognome IN Profili.Cognome%TYPE, P_Genere IN Profili.Genere%TYPE, P_Data_Nascita IN Profili.Data_Nascita%TYPE)
AS

BEGIN
    INSERT INTO Profili VALUES (P_Nome_Utente, P_Password, P_Nome, P_Cognome, P_Genere, P_Data_Nascita);
END Crea_Utente;
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
create or replace NONEDITIONABLE PROCEDURE Crea_Contenuto (P_Foto IN Contenuti.Foto%TYPE, P_Testo IN Contenuti.Testo%TYPE, P_FK_Id_Gruppo IN Contenuti.FK_Id_Gruppo%TYPE, P_FK_Nome_Utente IN Contenuti.FK_Nome_Utente%TYPE)
AS

Check_Partecipano NUMBER;

BEGIN

    SELECT COUNT(*) INTO Check_Partecipano 
    FROM Partecipano 
    WHERE FK_Id_Gruppo = P_FK_Id_Gruppo AND FK_Nome_Utente = P_FK_Nome_Utente;
    
    IF (check_partecipano = 1) THEN
        INSERT INTO Contenuti (Foto, Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_Foto, P_Testo, P_FK_Id_Gruppo, P_FK_Nome_Utente);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non partecipi al gruppo');
    END IF;
    
END Crea_Contenuto;
/

--Procedure per la creazione dei commenti
CREATE OR REPLACE PROCEDURE Crea_Commento(P_Testo IN Commenti.Testo%TYPE, P_FK_Id_Contenuto IN Commenti.FK_Id_Contenuto%TYPE, P_FK_Nome_Utente IN Commenti.FK_Nome_Utente%TYPE)
AS 
  
BEGIN 
   
   INSERT INTO Commenti (Testo, FK_Id_Contenuto, FK_NOME_UTENTE) VALUES (P_Testo, P_FK_Id_Contenuto, P_FK_Nome_Utente); 

END Crea_Commento;
/

--Procedure per la creazione delle notifiche gruppo
create or replace NONEDITIONABLE PROCEDURE Crea_Notifica_Gruppo(P_Testo IN Notifiche_Gruppi.Testo%TYPE, P_FK_Id_Gruppo IN Notifiche_Gruppi.FK_Id_Gruppo%TYPE, P_FK_Nome_Utente IN Notifiche_Gruppi.FK_Nome_Utente%TYPE)
AS

BEGIN

   INSERT INTO Notifiche_Gruppi(Testo,FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_Testo,P_FK_Id_Gruppo, P_FK_Nome_Utente);

END Crea_Notifica_Gruppo;
/

--Procedure per la creazione delle notifiche contenuto
create or replace NONEDITIONABLE PROCEDURE Crea_Notifica_Contenuto(P_Testo IN Notifiche_Contenuti.Testo%TYPE, P_FK_Id_Contenuto IN Notifiche_Contenuti.FK_Id_Contenuto%TYPE, P_FK_Nome_Utente IN Notifiche_Contenuti.FK_Nome_Utente%TYPE)
AS 
  
BEGIN 

   INSERT INTO Notifiche_Contenuti (Testo, FK_Id_Contenuto, FK_NOME_UTENTE) VALUES (P_Testo, P_FK_Id_Contenuto, P_FK_Nome_Utente); 

END Crea_Notifica_Contenuto;
/

--Procedure per creare la notifica della richiesta
create or replace NONEDITIONABLE PROCEDURE Crea_Notifica_Richiesta_Esito(P_FK_Id_Gruppo IN Notifiche_Richieste_Esiti.FK_Id_Gruppo%TYPE , P_FK_Nome_Utente IN Notifiche_Richieste_Esiti.FK_Nome_Utente%TYPE)
AS 

TMP_Nome Gruppi.Nome%TYPE;
Verifica_Richiesta NUMBER;

BEGIN 
    SELECT Nome INTO TMP_Nome FROM Gruppi WHERE P_FK_Id_Gruppo = Id_Gruppo;
    
    SELECT Count(*) INTO Verifica_Richiesta
    FROM notifiche_richieste_esiti
    WHERE fk_nome_utente = p_fk_nome_utente AND fk_id_gruppo = p_fk_id_gruppo;
    
    IF(Verifica_Richiesta = 0) THEN
        INSERT INTO Notifiche_Richieste_Esiti(Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_FK_Nome_Utente || ' Ha inviato una richiesta al gruppo: ' || TMP_Nome, P_FK_Id_Gruppo, P_FK_Nome_Utente);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Hai già inviato una richiesta al gruppo'); 
    END IF;
    
END Crea_Notifica_Richiesta_Esito;
/

-- Procedure per Visualizzare le notifiche dei contenuti di un utente
create or replace NONEDITIONABLE PROCEDURE Visualizzato_Notifica_Contenuto(P_Id_Notifica IN Notifiche_Contenuti.Id_Notifica_C%TYPE)
AS 

BEGIN
    UPDATE Notifiche_Contenuti SET Visualizzato = '1' WHERE Id_Notifica_C = P_Id_Notifica;
END Visualizzato_Notifica_Contenuto;
/

-- Procedure per Visualizzare le notifiche dei gruppi di un utente
create or replace NONEDITIONABLE PROCEDURE Visualizzato_Notifica_Gruppo(P_Id_Notifica IN Notifiche_Gruppi.Id_Notifica_G%TYPE)
AS 

BEGIN
    UPDATE Notifiche_Gruppi SET Visualizzato = '1' WHERE Id_Notifica_G = P_Id_Notifica;
END Visualizzato_Notifica_Gruppo;
/



-- Procedure per Mostrare le notifiche dei gruppi e dei contenuti di un utente
create or replace NONEDITIONABLE PROCEDURE Mostra_Notifica(P_Nome_Utente IN Notifiche_Contenuti.FK_Nome_Utente%TYPE)
AS 

CURSOR Rec_Notifica IS SELECT  NULL AS Id_Notifica_G, Id_Notifica_C ,Visualizzato, Testo, Data_Notifica FROM Notifiche_Contenuti WHERE fk_nome_utente LIKE P_Nome_Utente
                         UNION ALL
                         SELECT Id_Notifica_G, NULL AS Id_Notifica_C,Visualizzato, Testo, Data_Notifica FROM Notifiche_Gruppi WHERE FK_NOME_UTENTE LIKE P_Nome_Utente
                         ORDER BY Data_Notifica DESC;


TMP_Testo Notifiche_Contenuti.Testo%TYPE;
TMP_Data Notifiche_Contenuti.Data_Notifica%TYPE;
TMP_Visualizzato Notifiche_Contenuti.Visualizzato%TYPE;
TMP_Id_Notifica_C Notifiche_Contenuti.Id_Notifica_C%TYPE;
TMP_Id_Notifica_G Notifiche_Gruppi.Id_Notifica_G%TYPE;


BEGIN 
    OPEN Rec_Notifica;

    LOOP
        FETCH Rec_Notifica INTO TMP_Id_Notifica_G,TMP_Id_Notifica_C,TMP_Visualizzato,TMP_Testo, TMP_Data;
        EXIT WHEN Rec_Notifica%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(TMP_Visualizzato || ' - ' || TMP_Testo || ' - ' ||TMP_Data);
        
        IF TMP_Id_Notifica_C IS NOT NULL AND TMP_Id_Notifica_C <> 0 THEN
            Visualizzato_Notifica_Contenuto(TMP_Id_Notifica_C);
        ELSIF TMP_Id_Notifica_G IS NOT NULL AND TMP_Id_Notifica_G <> 0 THEN
            Visualizzato_Notifica_Gruppo(TMP_Id_Notifica_G);
        END IF;

        
    END LOOP;
    CLOSE Rec_Notifica;
    
  COMMIT;

END Mostra_Notifica;
/

--MODIFICA IL GRUPPO SOLO SE SEI IL CREATORE
create or replace NONEDITIONABLE PROCEDURE Modifica_Gruppo(Campo IN VARCHAR2, Val_NEW IN VARCHAR2, P_FK_Nome_Utente IN Gruppi.FK_Nome_Utente%TYPE, P_Id_Gruppo IN Gruppi.Id_Gruppo%TYPE)
AS

Comando VARCHAR(1000);
TMP_Creatore Gruppi.FK_Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Creatore
    FROM Gruppi
    WHERE Id_Gruppo = P_Id_Gruppo;


    IF (TMP_Creatore LIKE P_FK_Nome_Utente) THEN
        Comando:='UPDATE Gruppi SET '||Campo||' =  '''||Val_New||''' WHERE Id_Gruppo = '''||P_Id_Gruppo||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
        EXECUTE IMMEDIATE Comando;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non sei il creatore del gruppo gruppo');
    END IF;

END Modifica_Gruppo;
/


--MODIFICA IL CONTENUTO SOLO SE SEI IL CREATORE
create or replace NONEDITIONABLE PROCEDURE Modifica_Contenuto(Campo IN VARCHAR2, Val_NEW IN VARCHAR2, P_FK_Nome_Utente IN Contenuti.FK_Nome_Utente%TYPE, P_Id_Contenuto IN Contenuti.Id_Contenuto%TYPE)
AS

Comando VARCHAR(1000);
TMP_Creatore Contenuti.FK_Nome_Utente%TYPE;

BEGIN

    SELECT FK_Nome_Utente INTO TMP_Creatore
    FROM Contenuti
    WHERE Id_Contenuto = P_Id_Contenuto;


    IF (TMP_Creatore LIKE P_FK_Nome_Utente) THEN
        Comando:='UPDATE Contenuti SET '||Campo||' =  '''||Val_New||''' WHERE Id_Contenuto = '''||P_Id_Contenuto||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
        EXECUTE IMMEDIATE Comando;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Non sei il creatore del contenuto');
    END IF;

END Modifica_Contenuto;
/

--MODIFICA IL COMMENTO SOLO SE SEI IL CREATORE
create or replace NONEDITIONABLE PROCEDURE Modifica_Commento(Val_NEW IN VARCHAR2, P_FK_Nome_Utente IN Commenti.FK_Nome_Utente%TYPE, P_Id_Commento IN Commenti.Id_Commento%TYPE)
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
create or replace NONEDITIONABLE PROCEDURE Accetta_Profilo(P_FK_Nome_Utente IN Notifiche_Richieste_Esiti.FK_Nome_Utente%TYPE, P_FK_Id_Gruppo IN Notifiche_Richieste_Esiti.FK_Id_Gruppo%TYPE)
AS

Comando VARCHAR(1000);
Tmp_Notifica Notifiche_Richieste_Esiti.id_notifica_re%TYPE;


BEGIN

    SELECT id_notifica_re INTO TMP_Notifica
    FROM notifiche_richieste_esiti
    WHERE FK_Id_Gruppo=P_FK_Id_Gruppo AND fk_nome_utente=P_FK_Nome_Utente;
    
    Comando:='UPDATE Notifiche_Richieste_Esiti SET Esitato = ''1'' WHERE id_notifica_re = '''||TMP_Notifica||''''; -- Si usano le virgole (") prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
    EXECUTE IMMEDIATE Comando;

END Accetta_Profilo;
/

--L'ABBANDONA O VIENE RIMOSSO DAL GRUPPO
create or replace NONEDITIONABLE PROCEDURE Abbandona_Gruppo(P_FK_Nome_Utente IN Gruppi.FK_Nome_Utente%TYPE, P_Id_Gruppo IN Gruppi.Id_Gruppo%TYPE)
AS

Comando VARCHAR(1000);

BEGIN
        Comando:='DELETE FROM Partecipano WHERE FK_Id_Gruppo = '''||P_Id_Gruppo||'''AND fk_nome_utente ='''|| P_FK_Nome_Utente||''''; -- Si usano le virgole ('') prima e dopo gli || per ogni variabile che ha bisogno degli apici ('') nel comando
        EXECUTE IMMEDIATE Comando;

END Abbandona_Gruppo;
/
