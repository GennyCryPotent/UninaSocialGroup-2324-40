--Procedure per la creazione dei commenti
CREATE OR REPLACE PROCEDURE Crea_Commenti(P_Testo IN Commento.Testo%TYPE, P_Id_Contenuto IN Commento.FK_Id_Contenuto%TYPE, P_FK_Nome_Utente IN Commento.FK_Nome_Utente%TYPE)
AS 
  
BEGIN 
   
   INSERT INTO Commenti (Testo, Id_Contenuto, FK_NOME_UTENTE) VALUES (P_Testo, P_Id_Contenuto, P_FK_Nome_Utente); 

END Crea_Commenti;
/

--Procedure per la creazione delle Notifiche dei gruppi
CREATE OR REPLACE PROCEDURE Crea_Notifiche_Gruppi(P_Testo IN Notifiche_Gruppi.Testo%TYPE, P_FK_Id_Gruppo IN Notifiche_Gruppi.FK_Id_Gruppo%TYPE, P_FK_Nome_Utente IN Notifiche_Gruppi.FK_Nome_Utente%TYPE)
AS

BEGIN

   INSERT INTO Notifiche_Gruppi(Testo,FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_Testo,P_FK_Id_Gruppo, P_FK_Nome_Utente);

END Crea_Notifiche_Gruppi;
/

--Procedure per la creazione delle Notifiche dei contenuti 
CREATE OR REPLACE PROCEDURE Crea_Notifiche_Contenuti(P_Testo IN Notifiche_Contenuti.Testo%TYPE, P_Id_Contenuto IN Notifiche_Contenuti.FK_Id_Contenuto%TYPE, P_FK_Nome_Utente IN Notifiche_Contenuti.FK_Nome_Utente%TYPE)
AS 
  
BEGIN 
   
   INSERT INTO Notifiche_Contenuti (Testo, Id_Contenuto, FK_NOME_UTENTE) VALUES (P_Testo, P_Id_Contenuto, P_FK_Nome_Utente); 

END Crea_Notifiche_Contenuti;
/

--Procedure per 
CREATE OR REPLACE PROCEDURE Crea_Notifiche_Richieste_Esiti(P_Testo IN Notifiche_Richieste_Esiti.Testo%TYPE,  P_FK_Id_Gruppo IN Notifiche_Richieste_Esiti.FK_Id_Gruppo%TYPE , P_FK_Nome_Utente IN Notifiche_Richieste_Esiti.FK_Nome_Utente%TYPE)
AS 

BEGIN 

   INSERT INTO Notifiche_Richieste_Esiti(Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_Testo, P_FK_Id_Gruppo, P_FK_Nome_Utente);

END Crea_Notifiche_Richieste_Esiti;
/





