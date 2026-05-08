-- Cancella e reinserisce ogni avvio (idempotente)
DELETE FROM AUTHORITIES;
DELETE FROM USERS;
DELETE FROM USERDATA;

-- Admin: password = adm_id_07
INSERT INTO USERS (username, password, enabled) VALUES
    ('admin#07', '$2a$10$Yw2oRKktnyJPPSdBCJ3slOg/AzoXjNHmkIATa3n21YxVRfXBpuuku', 1);
INSERT INTO AUTHORITIES (username, authority) VALUES
    ('admin#07', 'ROLE_ADMIN');

-- Basic: password = bsc_id_07
INSERT INTO USERS (username, password, enabled) VALUES
    ('basic#07', '$2a$10$QPUP9UQu2af39luCl32Pp.ki/LFwDsBhG2BTbn4sMpBL0e0iO7tWO', 1);
INSERT INTO AUTHORITIES (username, authority) VALUES
    ('basic#07', 'ROLE_USER_BASIC');

-- Pro: password = pro_id_07
INSERT INTO USERS (username, password, enabled) VALUES
    ('pro#07', '$2a$10$cDGeQY57lzax0.rRufKT7.E0MHOOlMwuLlrk4qwvS73JtoV2Pc0em', 1);
INSERT INTO AUTHORITIES (username, authority) VALUES
    ('pro#07', 'ROLE_USER_PRO');

-- Prova: password = prv_id_07
INSERT INTO USERS (username, password, enabled) VALUES
    ('prova#1#07', '$2a$10$VkC.v783OrhGMZP3HbKFm.FdoRG38F4Fm9bIynJSlqAbtvq0ie2uK', 1);
INSERT INTO AUTHORITIES (username, authority) VALUES
    ('prova#1#07', 'ROLE_USER_PROVA');

-- Per avere il valore hashed delle passwords usare
-- BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
-- System.out.println("adm: " + encoder.encode("adm_id_07"));
-- System.out.println("bsc: " + encoder.encode("bsc_id_07"));
-- System.out.println("pro: " + encoder.encode("pro_id_07"));
-- System.out.println("prv: " + encoder.encode("prv_id_07"));
