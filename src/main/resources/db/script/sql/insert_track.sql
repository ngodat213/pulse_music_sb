INSERT INTO track (id, music_id, user_id, time_count, title, description)
VALUES
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 0), (SELECT id FROM user LIMIT 1 OFFSET 0), 100, 'Track 1', 'Description 1'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 1), (SELECT id FROM user LIMIT 1 OFFSET 1), 200, 'Track 2', 'Description 2'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 2), (SELECT id FROM user LIMIT 1 OFFSET 2), 300, 'Track 3', 'Description 3'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 3), (SELECT id FROM user LIMIT 1 OFFSET 3), 400, 'Track 4', 'Description 4'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 4), (SELECT id FROM user LIMIT 1 OFFSET 4), 500, 'Track 5', 'Description 5'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 5), (SELECT id FROM user LIMIT 1 OFFSET 5), 600, 'Track 6', 'Description 6'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 6), (SELECT id FROM user LIMIT 1 OFFSET 6), 700, 'Track 7', 'Description 7'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 7), (SELECT id FROM user LIMIT 1 OFFSET 7), 800, 'Track 8', 'Description 8'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 8), (SELECT id FROM user LIMIT 1 OFFSET 8), 900, 'Track 9', 'Description 9'),
    (gen_random_uuid(), (SELECT id FROM music LIMIT 1 OFFSET 9), (SELECT id FROM user LIMIT 1 OFFSET 9), 1000, 'Track 10', 'Description 10');
