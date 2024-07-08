--POPOLAZIONE DB

--Popolamento Profili
Insert into Profili (Nome_Utente, Password, Nome, Cognome, Genere, Data_Nascita)Values ('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'DD-MM-YYYY'));
Insert into Profili (Nome_Utente, Password, Nome, Cognome, Genere, Data_Nascita) Values ('Gabbo', 'SonoIo02', 'Gabriele', 'Cifuni', 'F', TO_DATE('21-4-2002', 'DD-MM-YYYY'));
Insert into Profili (Nome_Utente, Password, Nome, Cognome, Genere, Data_Nascita) Values ('DarkNine', 'Pagliaccio', 'Simone', 'Francesco', 'F', TO_DATE('21-11-2003', 'DD-MM-YYYY'));
Insert into Profili (Nome_Utente, Password, Nome, Cognome, Genere, Data_Nascita) Values ('errore31', 'Sbagliato03', 'Antonio', 'Caruso', 'M', TO_DATE('27-05-2003', 'DD-MM-YYYY'));

--Popolamento Gruppi
Insert into Gruppi (Nome, Descrizione, fk_Nome_Utente) Values ('Fantacalcio', 'Ciao', 'Genny03cry');
Insert into Gruppi (Nome, Descrizione, fk_Nome_Utente) Values ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');

--Popolamneto NOTIFICHE_RICHIESTE_ESITI
-- INSERT INTO Notifiche_Richieste_Esiti (Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (FK_Nome_Utente ||' Ha inviato richiesta al gruppo: ' || (SELECT Nome FROM Gruppi WHERE FK_Id_Gruppo = Id_Gruppo), 1, 'Gabbo');
CALL CREA_NOTIFICA_RICHIESTA_ESITO (1, 'Gabbo');
CALL CREA_NOTIFICA_RICHIESTA_ESITO (1, 'DarkNine');
CALL CREA_NOTIFICA_RICHIESTA_ESITO (1, 'errore31');

--Popolamento Contenuti
-- INSERT INTO Contenuti (Testo, fk_id_gruppo, fk_nome_utente) VALUES ('Pako ha vinto il fanta', 1, 'Gabbo');
-- INSERT INTO Contenuti (Testo, fk_id_gruppo, fk_nome_utente) VALUES ('Thuram è troppo forte', 1, 'DarkNine'); 
CALL CREA_CONTENUTO(NULL,'Pako ha vinto il Fanta!!1!1!',1,'Gabbo');
CALL CREA_CONTENUTO(NULL, 'Thuram è troppo forte',1,'DarkNine');
CALL CREA_CONTENUTO(NULL, 'Buongiorno è troppo forte',1,'DarkNine');



--Popolamento Partecipano
-- INSERT INTO Partecipano VALUES ('Gabbo', 1);
-- INSERT INTO Partecipano VALUES ('DarkNine', 1);

--Popolamento Like
CALL Create_Like ('DarkNine', 2);
CALL Create_Like ('Gabbo', 2);
CALL Create_Like ('DarkNine', 1);
CALL Create_Like ('Gabbo', 1); 






-- COSE NON POPOLAMENTO TMP





--Mostra Richieste
CALL MOSTRA_RICHIESTE('Genny03cry');

--Update Accettazione
UPDATE notifiche_richieste_esiti SET Esitato = '1' WHERE id_notifica_re = 1;
