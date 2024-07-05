--Buon Divertimento


-- CREA PARTECIPANO
create or replace NONEDITIONABLE PROCEDURE Crea_Partecipano (P_Nome_Utente IN PARTECIPANO.FK_NOME_UTENTE%TYPE, P_Id_Gruppo IN PARTECIPANO.FK_Id_Gruppo%TYPE)
AS

BEGIN
    INSERT INTO Partecipano VALUES (P_Nome_Utente, P_Id_Gruppo);
END Crea_Partecipano;

-- CREA REGOLANO

create or replace NONEDITIONABLE PROCEDURE Crea_Regolano (P_Nome_Utente IN Regolano.FK_NOME_UTENTE%TYPE, P_Id_Gruppo IN Regolano.FK_Id_Gruppo%TYPE)
AS

BEGIN
    INSERT INTO Regolano VALUES (P_Nome_Utente, P_Id_Gruppo);
END Crea_Regolano;


-- CREA LIKES
create or replace NONEDITIONABLE PROCEDURE Crea_Likes (P_Nome_Utente IN Likes.FK_NOME_UTENTE%TYPE, P_Id_Contenuto IN Likes.FK_Id_Contenuto%TYPE)
AS

BEGIN
    INSERT INTO Likes VALUES (P_Nome_Utente, P_Id_Contenuto);
END Crea_Likes;