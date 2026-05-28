DELETE FROM Composizioni;
DELETE FROM Programmi;
DELETE FROM Esercizi;

-- Inserimento dei nomi dei programmi predefiniti
INSERT INTO Programmi (nome) VALUES ('Full Body');
INSERT INTO Programmi (nome) VALUES ('Push/Pull/Legs');
INSERT INTO Programmi (nome) VALUES ('Cardio');
INSERT INTO Programmi (nome) VALUES ('Strength');

-- Inserimento esercizi con le kcal associate
INSERT INTO Esercizi (nome,unit) VALUES
    ('Git Pull-up','0.3'),
    ('Squat','0.5'),
    ('Leg press','0.3'),
    ('Git Push-up','0.2'),
    ('Bicipe curl','0.3'),
    ('Dead(Line) lift','0.5'),
    ('Full stack squat','0.5'),
    ('Lat machine','0.3'),
    ('Corsa saltellante','150'),
    ('Tapis roulant','200'),
    ('Salto della corda','10'),
    ('Triceps extension','0.3'),
    ('Chest press','0.5'),
    ('Pectoral machine','0.3'),
    ('Cable one arm side','0.2');

-- Inseriemento delle composizioni dei programmi predefiniti
INSERT INTO Composizioni (nome_esercizio,numero_serie,numero_ripetizioni,programmaType,esercizioType) VALUES
   ('Git Pull-up','3','10','Push/Pull/Legs','Git Pull-up'),
   ('Squat','4','12','Push/Pull/Legs','Squat'),
   ('Leg press','3','8','Push/Pull/Legs','Leg press'),
   ('Git Push-up','3','10','Push/Pull/Legs','Git Push-up'),

   ('Bicipe curl','3','10','Strength','Bicipe curl'),
   ('Dead(Line) lift','2','6','Strength','Dead(Line) lift'),
   ('Full stack squat','2','12','Strength','Full stack squat'),
   ('Lat machine','4','12','Strength','Lat machine'),

   ('Corsa saltellante','3','10','Cardio','Corsa saltellante'),
   ('Tapis roulant','1','1','Cardio','Tapis roulant'),
   ('Salto della corda','10','10','Cardio','Salto della corda'),

   ('Git Pull-up','3','10','Full Body','Git Pull-up'),
   ('Triceps extension','3','12','Full Body','Triceps extension'),
   ('Chest press','3','8','Full Body','Chest press'),
   ('Pectoral machine','3','10','Full Body','Pectoral machine'),
   ('Cable one arm side','4','10','Full Body','Cable one arm side');





