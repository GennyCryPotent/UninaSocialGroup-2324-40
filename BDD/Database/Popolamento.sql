--POPOLAZIONE DB

--Popolamento Profili
Insert into Profili (Nome_Utente, Password, Nome, Cognome, Genere, Data_Nascita)Values ('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'DD-MM-YYYY'));
Insert into Profili (Nome_Utente, Password, Nome, Cognome, Genere, Data_Nascita) Values ('Gabbo', 'SonoIo02', 'Gabriele', 'Cifuni', 'F', TO_DATE('21-4-2002', 'DD-MM-YYYY'));

--Popolamento Gruppi
Insert into Gruppi (Nome, Descrizione, fk_Nome_Utente) Values ('Fantacalcio', 'Ciao', 'Genny03cry');
Insert into Gruppi (Nome, Descrizione, fk_Nome_Utente) Values ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');

--Popolamneto NOTIFICHE_RICHIESTE_ESITI
INSERT INTO Notifiche_Richieste_Esiti (Testo, FK_Id_Gruppo, FK_Nome_Utente) VALUES ('Ha inviato richiesta', 1, 'Gabbo');