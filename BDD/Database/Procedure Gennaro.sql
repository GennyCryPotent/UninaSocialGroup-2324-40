-- PROCEDURE PER L'AGGIUNTA DI UN UTENTE NELLA TABALLA PROFILI
create or replace PROCEDURE Crea_Utente (P_Nome_Utente IN Profili.Nome_Utente%TYPE, P_Password IN Profili.Password%TYPE, P_Nome IN Profili.Nome%TYPE, P_Cognome IN Profili.Cognome%TYPE, P_Genere IN Profili.P_Genere%TYPE, P_Data_Nascita IN Profili.Data_Nascita%TYPE)
AS

BEGIN
    INSERT INTO Profili VALUES (P_Nome_Utente, P_Password, P_Nome, P_Cognome, P_Genere, P_Data_Nascita);
END Crea_Utente;

-- PROCEDURE PER L'AGGIUNTA DI UN GRUPPO NELLA TABALLA GRUPPI
create or replace PROCEDURE Crea_Gruppo (P_Nome IN Gruppi.Nome%TYPE, P_Descrizione IN Gruppi.Descrizione%TYPE, P_FK_Nome_Utente IN Gruppi.FK_Nome_Utente%TYPE)
AS

BEGIN
    INSERT INTO Gruppi (Nome, Descrizione, FK_Nome_Utente) VALUES (P_Nome, P_Descrizione, P_FK_Nome_Utente);
END Crea_Gruppi;

-- PROCEDURE PER L'AGGIUNTA DI UN TAG NELLA TABALLA TAGS
create or replace PROCEDURE Crea_Tag (P_Parola IN Tags.Parola%TYPE)
AS

BEGIN
    INSERT INTO Tags (Parola) VALUES (P_Parola);
END Crea_Tag;

-- PROCEDURE PER L'AGGIUNTA DI UN CONTENUTO NELLA TABALLA CONTENUTI
create or replace PROCEDURE Crea_Contenuto (P_Foto IN Contenuti.Foto%TYPE, P_Testo IN Contenuti.Testo%TYPE, P_FK_Id_Gruppo IN Contenuti.Id_Gruppo%TYPE, P_FK_Nome_Utente IN Contenuti.FK_Nome_Utente%TYPE)
AS

BEGIN
    INSERT INTO Contenuti (Foto, Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (P_Foto, P_Testo, P_FK_Id_Gruppo, P_FK_Nome_Utente);
END Crea_Contenuto;

