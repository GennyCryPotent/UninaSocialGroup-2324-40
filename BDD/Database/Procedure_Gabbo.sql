--Procedure per la creazione dei commenti
CREATE OR REPLACE PROCEDURE Crea_Commenti(Testo_C Commento.Testo%TYPE, Id_Cont_C Commento.FK_Id_Contenuto%TYPE, N_Utente_C Commento.FK_Nome_Utente%TYPE)
AS 
  
BEGIN 
   
   INSERT INTO Commenti (Testo, Id_Contenuto, FK_NOME_UTENTE) VALUES (Testo_C, Id_Cont_C, N_Utente_C); 

END Crea_Commenti;
/

--Procedure per la creazione delle Notifiche dei gruppi
CREATE OR REPLACE PROCEDURE Crea_Notifiche_Gruppi(Testo_NG Notifiche_Gruppi.Testo%TYPE, 
 Id_Gruppo_NG Notifiche_Gruppi.FK_Id_Gruppo%TYPE, N_Utente_NG Notifiche_Gruppi.FK_Nome_Utente%TYPE)
AS

BEGIN

   INSERT INTO Notifiche_Gruppi(Testo,FK_Id_Gruppo, FK_Nome_Utente) VALUES (Testo_NG, Id_Gruppo_NG, N_Utente_NG);

END Crea_Notifiche_Gruppi;
/

--Procedure per la creazione delle Notifiche dei contenuti 
CREATE OR REPLACE PROCEDURE Crea_Notifiche_Contenuti(Testo_NC Notifiche_Contenuti.Testo%TYPE,  ID_Cont_NC Notifiche_Contenuti.FK_Id_Contenuto%TYPE , N_Utente_NC Notifiche_Contenuti.FK_Nome_Utente%TYPE)
AS

BEGIN

    INSERT INTO Notifiche_Contenuti(Testo, FK_Id_Contenuto, FK_Nome_Utente) VALUES (Testo_NC, ID_Cont_NC, N_Utente_NC);

END Crea_Notifiche_Contenuti;
/

--Procedure per 
CREATE OR REPLACE PROCEDURE Crea_Notifiche_Richieste_Esiti(Testo_NR Notifiche_Richieste_Esiti.Testo%TYPE,  ID_Cont_NR Notifiche_Richieste_Esiti.FK_Id_Contenuto%TYPE , N_Utente_NR Notifiche_Richieste_Esiti.FK_Nome_Utente%TYPE)
AS 

BEGIN 

   INSERT INTO Notifiche_Richieste_Esiti(Testo, FK_Id_Contenuto, FK_Nome_Utente) VALUES (Testo_NR, ID_Cont_NR, N_Utente_NR);

END Crea_Notifiche_Richieste_Esiti;
/





