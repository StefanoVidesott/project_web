DELETE FROM COMPOSITIONS;
DELETE FROM TRAININGS;
DELETE FROM EXERCISES;

INSERT INTO TRAININGS (id, name) VALUES (0, 'Full Body');
INSERT INTO TRAININGS (id, name) VALUES (1, 'Push/Pull/Legs');
INSERT INTO TRAININGS (id, name) VALUES (2, 'Cardio');
INSERT INTO TRAININGS (id, name) VALUES (3, 'Strength');

INSERT INTO EXERCISES (name, unit) VALUES
    ('Git Pull-up', 0.3),
    ('Squat', 0.5),
    ('Leg press', 0.3),
    ('Git Push-up', 0.2),
    ('Bicipe curl', 0.3),
    ('Dead(Line) lift', 0.5),
    ('Full stack squat', 0.5),
    ('Lat machine', 0.3),
    ('Corsa saltellante', 0),
    ('Tapis roulant', 0),
    ('Salto della corda', 0),
    ('Triceps extension', 0.3),
    ('Chest press', 0.5),
    ('Pectoral machine', 0.3),
    ('Cable one arm side', 0.2);

INSERT INTO COMPOSITIONS (sets, reps, training_name, exercise_name) VALUES
    (3, 10, 'Push/Pull/Legs', 'Git Pull-up'),
    (4, 12, 'Push/Pull/Legs', 'Squat'),
    (3, 8, 'Push/Pull/Legs', 'Leg press'),
    (3, 10, 'Push/Pull/Legs', 'Git Push-up'),

    (3, 10, 'Strength', 'Bicipe curl'),
    (2, 6, 'Strength', 'Dead(Line) lift'),
    (2, 12, 'Strength', 'Full stack squat'),
    (4, 12, 'Strength', 'Lat machine'),

    (3, 10, 'Cardio', 'Corsa saltellante'),
    (1, 1, 'Cardio', 'Tapis roulant'),
    (10, 10, 'Cardio', 'Salto della corda'),

    (3, 10, 'Full Body', 'Git Pull-up'),
    (3, 12, 'Full Body', 'Triceps extension'),
    (3, 8, 'Full Body', 'Chest press'),
    (3, 10, 'Full Body', 'Pectoral machine'),
    (4, 10, 'Full Body', 'Cable one arm side');
