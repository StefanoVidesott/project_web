DELETE FROM ESERCIZI;
DELETE FROM PROGRAMMICUSTOM;
DELETE FROM CUSTOM_TRAININGS_COUNTER;
DELETE FROM REVIEWS;
DELETE FROM USERDATA;
DELETE FROM AUTHORITIES;
DELETE FROM USERS;


INSERT INTO USERS (username, password, enabled) VALUES
    ('admin#07', '$2a$10$YiAOkG/.iH38LhnGSWRWMuxZog8IBsOsT4gOhfuNxkIGGJqX743pG', 1),
    ('basic#07', '$2a$10$IWmpI5gtvC959a1jZ8WRe.xT3Iqi3nHLUptGa.HAEerJaZ3Zq2LGO', 1),
    ('pro#07', '$2a$10$wJcF3GHtE2a3mo1B7hulA.wnnNQgjTJfiVBO94DzFHWk1ml0l8Exu', 1),
    ('prova#1#07', '$2a$10$fqL.CWpKFNz9ACh0x4it4ezy7TKZa4Tzx3PMLcWIwFbvDpntGlnUW', 1),
    ('prova#2#07', '$2a$10$fqL.CWpKFNz9ACh0x4it4ezy7TKZa4Tzx3PMLcWIwFbvDpntGlnUW', 1),
    ('prova#3#07', '$2a$10$fqL.CWpKFNz9ACh0x4it4ezy7TKZa4Tzx3PMLcWIwFbvDpntGlnUW', 0),
    ('prova#4#07', '$2a$10$fqL.CWpKFNz9ACh0x4it4ezy7TKZa4Tzx3PMLcWIwFbvDpntGlnUW', 0),
    ('prova#5#07', '$2a$10$fqL.CWpKFNz9ACh0x4it4ezy7TKZa4Tzx3PMLcWIwFbvDpntGlnUW', 1);

INSERT INTO AUTHORITIES (username, authority) VALUES
    ('admin#07', 'ROLE_ADMIN'),
    ('basic#07', 'ROLE_USER_BASIC'),
    ('pro#07', 'ROLE_USER_PRO'),
    ('prova#1#07', 'ROLE_USER_PROVA'),
    ('prova#2#07', 'ROLE_USER_PROVA'),
    ('prova#3#07', 'ROLE_USER_PROVA'),
    ('prova#4#07', 'ROLE_USER_PROVA'),
    ('prova#5#07', 'ROLE_USER_PROVA');

INSERT INTO USERDATA (username, firstName, lastName, email, birthDate, signupDate, count_training0, count_training1, count_training2, count_training3) VALUES
    ('admin#07', 'Admin', 'System', 'admin@fitapp.it', '1990-01-01', '2025-01-01', 0, 0, 0, 0),
    ('basic#07', 'Stefano', 'Videsott', 'basic@fitapp.it', '1990-01-01', '2025-01-02', 0, 0, 0, 0),
    ('pro#07', 'Alessandro', 'Como', 'pro@fitapp.it', '1990-01-01', '2025-01-03', 6, 10, 3, 3),
    ('prova#1#07', 'Giovanna', 'Varni', 'prova1@fitapp.it', '1990-01-01', '2025-06-07', 2, 2, 2, 2),
    ('prova#2#07', 'Prova', 'Bianchi', 'prova2@fitapp.it', '1990-01-01', '2025-06-09', 0, 1, 3, 3),
    ('prova#3#07', 'Prova',  'Esposito', 'prova3@fitapp.it', '1990-01-01', '2025-06-21', 6, 3, 6, 1),
    ('prova#4#07', 'Prova', 'Ferrari', 'prova4@fitapp.it', '1990-01-01', '2025-06-23', 0, 0, 0, 0),
    ('prova#5#07', 'Prova', 'Romano', 'prova5@fitapp.it', '1990-01-01', '2025-06-08', 0, 0, 0, 0);

INSERT INTO PROGRAMMICUSTOM (username, nome, kcal) VALUES
    ('pro#07', 'programma_custom_1', 0),
    ('pro#07', 'programma_custom_2', 0);

INSERT INTO CUSTOM_TRAININGS_COUNTER (username, trainingId, count) VALUES
    ('pro#07', 1, 7),
    ('pro#07', 2, 2);

INSERT INTO REVIEWS (username, content) VALUES
    ('basic#07', 'Fantastica, la consiglio a tutti i nerd pompati li fuori'),
    ('basic#07', 'Mai provato nulla del genere, adoro fare Branch press'),
    ('pro#07', 'Perfetta per iniziare a sviluppare muscoli');

-- Per avere l'hash delle passwords
-- BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
-- System.out.println("adm: " + encoder.encode("ad_id_07"));
-- System.out.println("bsc: " + encoder.encode("bs_id_07"));
-- System.out.println("pro: " + encoder.encode("pr_id_07"));
-- System.out.println("prv: " + encoder.encode("pv_id_07"));
