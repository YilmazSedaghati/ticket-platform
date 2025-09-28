INSERT INTO categorie (nome, descrizione) VALUES 
('Problemi Tecnici', 'Problemi relativi a bug, errori o malfunzionamenti'),
('Installazione e Aggiornamenti', 'Problemi relativi a installazione nuove Release e aggiornamenti'),
('Richieste Funzionalità', 'Richiesta di nuove funzionalità e aggiornamenti software'),
('Problema Rete', 'Problemi di connettività e rete'),
('Assistenza Remota', 'Richiesta di assistenza tecnica remota'),
('Assistenza Utente', 'Problemi di utilizzo o configurazione'),
('Compatibilità', 'Problemi di incompatibilità software/hardware'),
('Fatturazione', 'Problemi relativi a pagamenti e fatture'),
('Abbonamenti', 'Problemi relativi ad abbonamenti e servizi');


INSERT INTO operatori (nome, email, password, disponibile, ruolo) VALUES 
('Admin', 'admin@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', true, 'ADMIN'),
('Yilmaz', 'yilmaz.sedag@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', true, 'ADMIN'),
('User', 'user@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', true, 'OPERATORE'),
('Mario', 'mario.rossi@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', true, 'OPERATORE'),
('Laura', 'laura.bianchi@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', true, 'OPERATORE'),
('Giuseppe', 'giuseppe.verdi@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', false, 'OPERATORE'),
('Francesca', 'francesca.neri@sedasoftware.com', '$2a$10$lEDqi4/6g9tQ/L8Vxz7CfeueLNiSTkyb8Q.FcIm8WzqE5rcQP0dii', true, 'OPERATORE');


INSERT INTO tickets (titolo, descrizione, stato, categoria_id, operatore_id, data_creazione, data_aggiornamento) VALUES 
('Errore di connessione database', 'Il sistema non riesce a connettersi al database MySQL. Errore: "Connection refused"', 'DA_FARE', 1, 4, '2024-01-15 08:30:00', '2024-01-15 08:30:00'),
('Aggiornamento versione 2.5', 'Necessità di assistenza per l''installazione della nuova release 2.5 sul server di produzione', 'IN_CORSO', 2, 5, '2024-01-14 14:20:00', '2024-01-15 09:15:00'),
('Richiesta report avanzato', 'Sarebbe utile avere un report che mostri i tempi medi di risoluzione per categoria', 'DA_FARE', 3, 3, '2024-01-13 11:45:00', '2024-01-13 11:45:00'),
('Problema VPN aziendale', 'I dipendenti in smart working non riescono a connettersi alla VPN da ieri pomeriggio', 'IN_CORSO', 4, 6, '2024-01-15 09:00:00', '2024-01-15 16:30:00'),
('Assistenza configurazione nuovo PC', 'Necessario supporto remoto per configurare il nuovo PC del direttore marketing', 'DA_FARE', 5, 4, '2024-01-15 10:15:00', '2024-01-15 10:15:00'),
('Problema login portale clienti', 'I clienti non riescono ad accedere al portale con le credenziali corrette', 'COMPLETATO', 6, 5, '2024-01-10 08:45:00', '2024-01-12 16:30:00'),
('Incompatibilità con Windows 11', 'L''applicazione non si avvia su dispositivi con Windows 11 aggiornato', 'IN_CORSO', 7, 3, '2024-01-14 16:10:00', '2024-01-15 14:20:00'),
('Fattura duplicata', 'Nel mese di dicembre è stata emessa due volte la stessa fattura numero F-2023-1245', 'DA_FARE', 8, 4, '2024-01-15 11:30:00', '2024-01-15 11:30:00'),
('Rinnovo abbonamento premium', 'L''abbonamento premium è scaduto ma il sistema non permette il rinnovo', 'COMPLETATO', 9, 5, '2024-01-12 09:20:00', '2024-01-13 15:45:00'),
('Lentezza applicazione web', 'L''applicazione web risulta molto lenta durante le ore di punta (10:00-12:00)', 'IN_CORSO', 1, 6, '2024-01-15 13:00:00', '2024-01-15 17:00:00');


INSERT IGNORE INTO note (testo, data_creazione, autore_id, ticket_id) VALUES 
('Ho verificato le impostazioni del database. La connessione sembra bloccata dal firewall.', '2024-01-15 09:45:00', 4, 1),
('Ho contattato il team di sviluppo per il manuale di installazione della versione 2.5.', '2024-01-14 16:30:00', 5, 2),
('Interessante proposta. Valuteremo con il team di sviluppo la fattibilità.', '2024-01-13 14:20:00', 3, 3),
('Il fornitore della VPN sta investigando sul problema. Hanno segnalato un guasto hardware.', '2024-01-15 11:00:00', 6, 4),
('Pianificato intervento remoto per domani mattina alle 9:30.', '2024-01-15 11:30:00', 4, 5),
('Risolto: era un problema di cache del browser. Svuotata la cache e ora funziona.', '2024-01-12 10:15:00', 5, 6),
('Sto testando l''applicazione su diverse build di Windows 11 per identificare il problema.', '2024-01-15 10:00:00', 3, 7),
('Ho contattato l''ufficio amministrazione per la stornatura della fattura duplicata.', '2024-01-15 12:00:00', 4, 8),
('Risolto: aggiornato il sistema di pagamento. L''abbonamento è stato rinnovato correttamente.', '2024-01-13 11:30:00', 5, 9),
('Monitorando le performance, ho notato picchi di utilizzo della CPU durante le ore di punta.', '2024-01-15 15:30:00', 6, 10);