DELETE FROM AUTHORITIES;
DELETE FROM USERS;
DELETE FROM USERDATA;

-- Admin: password = adm_id_07
-- Basic: password = bsc_id_07
-- Pro: password = pro_id_07
-- Utenti PROVA: password = prv_id_07
INSERT INTO USERS (username, password, enabled) VALUES
    ('admin#07', '$2a$10$Yw2oRKktnyJPPSdBCJ3slOg/AzoXjNHmkIATa3n21YxVRfXBpuuku', 1),
    ('basic#07', '$2a$10$QPUP9UQu2af39luCl32Pp.ki/LFwDsBhG2BTbn4sMpBL0e0iO7tWO', 1),
    ('pro#07', '$2a$10$cDGeQY57lzax0.rRufKT7.E0MHOOlMwuLlrk4qwvS73JtoV2Pc0em', 1),
    ('prova#1#07', '$2a$10$VkC.v783OrhGMZP3HbKFm.FdoRG38F4Fm9bIynJSlqAbtvq0ie2uK', 1),
    ('prova#2#07', '$2a$10$VkC.v783OrhGMZP3HbKFm.FdoRG38F4Fm9bIynJSlqAbtvq0ie2uK', 1),
    ('prova#3#07', '$2a$10$VkC.v783OrhGMZP3HbKFm.FdoRG38F4Fm9bIynJSlqAbtvq0ie2uK', 0),
    ('prova#4#07', '$2a$10$VkC.v783OrhGMZP3HbKFm.FdoRG38F4Fm9bIynJSlqAbtvq0ie2uK', 0),
    ('prova#5#07', '$2a$10$VkC.v783OrhGMZP3HbKFm.FdoRG38F4Fm9bIynJSlqAbtvq0ie2uK', 1);

INSERT INTO AUTHORITIES (username, authority) VALUES
    ('admin#07', 'ROLE_ADMIN'),
    ('basic#07', 'ROLE_USER_BASIC'),
    ('pro#07', 'ROLE_USER_PRO'),
    ('prova#1#07', 'ROLE_USER_PROVA'),
    ('prova#2#07', 'ROLE_USER_PROVA'),
    ('prova#3#07', 'ROLE_USER_PROVA'),
    ('prova#4#07', 'ROLE_USER_PROVA'),
    ('prova#5#07', 'ROLE_USER_PROVA');

INSERT INTO USERDATA (username, firstName, lastName, email, birthDate, signupDate) VALUES
    ('admin#07',   'Admin',     'System',   'admin@fitapp.it',   '1990-01-01', '2025-01-01'),
    ('basic#07',   'Stefano',   'Videsott', 'basic@fitapp.it',   '1990-01-01', '2025-01-01'),
    ('pro#07',     'Alessandro','Como',     'pro@fitapp.it',     '1990-01-01', '2025-01-01'),
    ('prova#1#07', 'Giovanna',  'Varni',    'prova1@fitapp.it',  '1990-01-01', '2025-06-01'),
    ('prova#2#07', 'Prova',     'Bianchi',  'prova2@fitapp.it',  '1990-01-01', '2025-06-01'),
    ('prova#3#07', 'Prova',      'Esposito', 'prova3@fitapp.it',  '1990-01-01', '2025-06-01'),
    ('prova#4#07', 'Prova',      'Ferrari',  'prova4@fitapp.it',  '1990-01-01', '2025-06-01'),
    ('prova#5#07', 'Prova',      'Romano',   'prova5@fitapp.it',  '1990-01-01', '2025-06-01');

-- Per avere l'hash delle passwords
-- BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
-- System.out.println("adm: " + encoder.encode("adm_id_07"));
-- System.out.println("bsc: " + encoder.encode("bsc_id_07"));
-- System.out.println("pro: " + encoder.encode("pro_id_07"));
-- System.out.println("prv: " + encoder.encode("prv_id_07"));
