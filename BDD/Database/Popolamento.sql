--POPOLAZIONE DB

--Popolamento Profili
CALL CREA_UTENTE('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'DD-MM-YYYY'));
CALL CREA_UTENTE('Gabbo', 'SonoIo02', 'Gabriele', 'Cifuni', 'F', TO_DATE('21-4-2002', 'DD-MM-YYYY'));
CALL CREA_UTENTE('DarkNine', 'Pagliaccio', 'Simone', 'Francesco', 'F', TO_DATE('21-11-2003', 'DD-MM-YYYY'));
CALL CREA_UTENTE('errore31', 'Sbagliato03', 'Antonio', 'Caruso', 'M', TO_DATE('27-05-2003', 'DD-MM-YYYY'));



--Popolamento Gruppi
CALL CREA_GRUPPO ('Fantacalcio', 'Ciao', 'Genny03cry');
CALL CREA_GRUPPO ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');

--Popolamneto NOTIFICHE_RICHIESTE_ESITI
-- INSERT INTO Notifiche_Richieste_Esiti (Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES (FK_Nome_Utente ||' Ha inviato richiesta al gruppo: ' || (SELECT Nome FROM Gruppi WHERE FK_Id_Gruppo = Id_Gruppo), 1, 'Gabbo');
CALL CREA_NOTIFICA_RICHIESTA_ESITO (1, 'Gabbo');
CALL CREA_NOTIFICA_RICHIESTA_ESITO (1, 'DarkNine');
CALL CREA_NOTIFICA_RICHIESTA_ESITO (1, 'errore31');


-- --Popolamneto NOTIFICHE_RICHIESTE_ESITI (ACCETTAZIONI)
-- INSERT INTO Notifiche_Richieste_Esiti(Testo, Esitato,FK_Id_Gruppo, FK_Nome_Utente) VALUES ('Gabbo è stato aggiunto al gruppo: Fantacalcio', '3', 1, 'Gabbo');      
-- INSERT INTO Notifiche_Richieste_Esiti(Testo, Esitato,FK_Id_Gruppo, FK_Nome_Utente) VALUES ('errore31 è stato aggiunto al gruppo: Fantacalcio', '3', 1, 'errore31');      


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
CALL Crea_Like ('DarkNine', 2);
CALL Crea_Like ('Gabbo', 2);
CALL Crea_Like ('DarkNine', 1);
CALL Crea_Like ('Gabbo', 1); 

--Popolamento Commenti
CALL Crea_Commento('Non è vero che è troppo forte!!',2,'Gabbo'); 
CALL Crea_Commento('Invece si, ha rovinato il gioco!!',2,'errore31'); 
CALL Crea_Commento('🔥🔥🔥',2,'DarkNine'); 




-- COSE NON POPOLAMENTO TMP

CALL MOSTRA_LIKE_COMMENTI(5);



--Mostra Richieste
CALL MOSTRA_RICHIESTA('Genny03cry');


CALL MOSTRA_ARCHIVIATA('Genny03cry');

--Update Accettazione
UPDATE notifiche_richieste_esiti SET Esitato = '1' WHERE id_notifica_re = 1;
