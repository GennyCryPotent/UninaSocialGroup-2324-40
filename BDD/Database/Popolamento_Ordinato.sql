--POPOLAZIONE DB

--Popolamento Profili
CALL CREA_PROFILO('Genny03cry', 'Database03', 'Gennaro', 'De Luca', 'M', TO_DATE('04-11-2003', 'DD-MM-YYYY'));
CALL CREA_PROFILO('Gabbo', 'SonoIo02', 'Gabriele', 'Cifuni', 'F', TO_DATE('21-4-2002', 'DD-MM-YYYY'));
CALL CREA_PROFILO('DarkNine', 'Pagl1accio', 'Simone', 'Francesco', 'F', TO_DATE('21-11-2003', 'DD-MM-YYYY'));
CALL CREA_PROFILO('errore31', 'Sbagliato03', 'Antonio', 'Caruso', 'M', TO_DATE('27-05-2003', 'DD-MM-YYYY'));
CALL CREA_PROFILO('Lauriel', 'R0rioneDiRiccione', 'Laura', 'Miele', 'F', TO_DATE('24-02-2003', 'DD-MM-YYYY'));
CALL CREA_PROFILO('OgniRiccioUnCapriccio', 'Ricci0ne', 'Mario Flavio', 'Di Blasio', 'M', TO_DATE('22-02-2003', 'DD-MM-YYYY'));
CALL CREA_PROFILO('Mondindo', 'MONDONEEEE', 'Francesco', 'Barra', 'M', TO_DATE('20-02-2003', 'DD-MM-YYYY'));

--Popolamneto TAG
CALL CREA_TAG('Fotografia');
CALL CREA_TAG('Gaming');
CALL CREA_TAG('Moda');
CALL CREA_TAG('Educazione');
CALL CREA_TAG('Ambiente');
CALL CREA_TAG('Politica');
CALL CREA_TAG('Economia');
CALL CREA_TAG('Storia');
CALL CREA_TAG('Astronomia');
CALL CREA_TAG('Psicologia');
CALL CREA_TAG('Fitness');
CALL CREA_TAG('Gastronomia');
CALL CREA_TAG('Architettura');
CALL CREA_TAG('Filosofia');
CALL CREA_TAG('Matematica');
CALL CREA_TAG('Fisica');
CALL CREA_TAG('Chimica');
CALL CREA_TAG('Biologia');
CALL CREA_TAG('Geografia');
CALL CREA_TAG('Linguistica');
CALL CREA_TAG('Sport');
CALL CREA_TAG('Cinema');
CALL CREA_TAG('Animali');
CALL CREA_TAG('Musica');




--Popolamento Gruppi
CALL CREA_GRUPPO ('Fantacalcio', 'Ciao', 'Genny03cry');
CALL CREA_GRUPPO ('SSC_Napoli_Ultras', 'Solo fan del napoli', 'Genny03cry');
CALL CREA_GRUPPO ('Dungeons N Dragons', 'Gruppo pubblico di DND dove giocare e divertirsi', 'errore31');
CALL CREA_GRUPPO('Amanti del Cinema', 'Discussioni sui film e serie TV preferite', 'errore31');
CALL CREA_GRUPPO('Fotografia per Passione', 'Condividi le tue foto e apprendi nuove tecniche', 'Lauriel');
CALL CREA_GRUPPO('Viaggiatori del Mondo', 'Esperienze di viaggio e consigli di destinazioni', 'Gabbo');
CALL CREA_GRUPPO('Cucinare con Amore', 'Ricette e trucchi per cucinare come uno chef', 'Genny03cry');
CALL CREA_GRUPPO('Appassionati di Tecnologia', 'Novità e discussioni su gadget e innovazioni', 'DarkNine');
CALL CREA_GRUPPO('Libri e Letteratura', 'Consigli di lettura e discussioni sui libri', 'DarkNine');
CALL CREA_GRUPPO('Amici degli Animali', 'Tutto sui nostri amici a quattro zampe', 'OgniRiccioUnCapriccio');
CALL CREA_GRUPPO('Fitness e Benessere', 'Consigli su esercizi, diete e stile di vita sano', 'Gabbo');
CALL CREA_GRUPPO('Musica per Tutti', 'Condivisione di playlist e nuovi artisti', 'Lauriel');

--Popolamneto POSSIEDONO
CALL CREA_POSSIEDONO(1, 'Sport');
CALL CREA_POSSIEDONO(2, 'Sport');
CALL CREA_POSSIEDONO(3, 'Gaming');
CALL CREA_POSSIEDONO(4, 'Educazioe');
CALL CREA_POSSIEDONO(4, 'Cinema');
CALL CREA_POSSIEDONO(5, 'Fotografia');
CALL CREA_POSSIEDONO(5, 'Geografia');
CALL CREA_POSSIEDONO(5, 'Linguistica');
CALL CREA_POSSIEDONO(5, 'Ambiente');
CALL CREA_POSSIEDONO(6, 'Gastronomia');
CALL CREA_POSSIEDONO(7, 'Matematica');
CALL CREA_POSSIEDONO(8, 'Linguistica');
CALL CREA_POSSIEDONO(8, 'Filosofia');
CALL CREA_POSSIEDONO(8, 'Politica');
CALL CREA_POSSIEDONO(8, 'Economia');
CALL CREA_POSSIEDONO(9, 'Animali');
CALL CREA_POSSIEDONO(10, 'Fitness');
CALL CREA_POSSIEDONO(11, 'Musica');



--Popolamento NOTIFICHE_RICHIESTE_ESITI
CALL CREA_RICHIESTA (1, 'Gabbo');
CALL CREA_RICHIESTA (1, 'DarkNine');
CALL CREA_RICHIESTA (2, 'errore31');
CALL CREA_RICHIESTA (4, 'errore31');
CALL CREA_RICHIESTA (5, 'errore31');
CALL CREA_RICHIESTA (6, 'errore31');
CALL CREA_RICHIESTA (6, 'DarkNine');
CALL CREA_RICHIESTA (5, 'Gabbo');
CALL CREA_RICHIESTA (3, 'Lauriel');
CALL CREA_RICHIESTA (1, 'Lauriel');
CALL CREA_RICHIESTA (6, 'OgniRiccioUnCapriccio');
CALL CREA_RICHIESTA (1, 'OgniRiccioUnCapriccio');
CALL CREA_RICHIESTA (6, 'Mondindo');
CALL CREA_RICHIESTA (6, 'Genny03cry');



--Popolamneto NOTIFICHE_RICHIESTE_ESITI (ACCETTAZIONI)   
CALL ACCETTA_PROFILO ('Gabbo', 1);
CALL ACCETTA_PROFILO ('DarkNine', 1);
CALL RIFIUTA_PROFILO ('errore31', 2);
CALL ACCETTA_PROFILO ('errore31', 4);
CALL ACCETTA_PROFILO ('errore31', 5);
CALL ACCETTA_PROFILO ('errore31', 6);
CALL RIFIUTA_PROFILO ('DarkNine', 6);
CALL ACCETTA_PROFILO ('Gabbo', 5);
CALL ACCETTA_PROFILO ('Lauriel', 3);
CALL ACCETTA_PROFILO ('Lauriel', 1);
CALL ACCETTA_PROFILO ('OgniRiccioUnCapriccio', 6);
CALL ACCETTA_PROFILO ('OgniRiccioUnCapriccio', 1);
CALL ACCETTA_PROFILO ('Mondindo', 6);
CALL ACCETTA_PROFILO ('Genny03cry', 6);


--Popolamneto REGOLANO   
CALL CREA_REGOLANO ('Gabbo', 1);
CALL CREA_REGOLANO ('DarkNine', 1);
CALL CREA_REGOLANO ('errore31', 4);
CALL CREA_REGOLANO ('errore31', 5);
CALL CREA_REGOLANO ('Gabbo', 5);
CALL CREA_REGOLANO ('Lauriel', 3);



--Popolamento Contenuti
CALL CREA_CONTENUTO(NULL,'Pako ha vinto il Fanta!!1!1!',1,'Gabbo');
CALL CREA_CONTENUTO(NULL, 'Thuram è troppo forte',1,'DarkNine');
CALL CREA_CONTENUTO(NULL, 'Buongiorno è troppo forte',1,'DarkNine');
CALL CREA_CONTENUTO(NULL, 'A breve inizierà "Signore Oscuro in DND" ', 3, 'errore31');
CALL CREA_CONTENUTO(NULL, 'Questo fantacalcio è molto equilibrato', 1, 'Lauriel');


--Popolamento Like
CALL CREA_LIKE ('DarkNine', 2);
CALL CREA_LIKE ('Gabbo', 2);
CALL CREA_LIKE ('DarkNine', 1);
CALL CREA_LIKE ('Gabbo', 1); 
CALL CREA_LIKE ('Lauriel', 4); 



--Popolamento Commenti
CALL CREA_COMMENTO('Non è vero che è troppo forte!!',2,'Gabbo'); 
CALL CREA_COMMENTO('Invece si, ha rovinato il gioco!!',2,'errore31'); 
CALL CREA_COMMENTO('🔥🔥🔥',2,'DarkNine'); 
CALL CREA_COMMENTO('EVVIA!',4,'Lauriel'); 






