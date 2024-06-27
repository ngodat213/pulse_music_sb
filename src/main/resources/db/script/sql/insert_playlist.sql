INSERT INTO playlist (id, user_id, music_id)
VALUES
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 0), (SELECT id FROM music LIMIT 1 OFFSET 0)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 1), (SELECT id FROM music LIMIT 1 OFFSET 1)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 2), (SELECT id FROM music LIMIT 1 OFFSET 2)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 3), (SELECT id FROM music LIMIT 1 OFFSET 3)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 4), (SELECT id FROM music LIMIT 1 OFFSET 4)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 5), (SELECT id FROM music LIMIT 1 OFFSET 5)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 6), (SELECT id FROM music LIMIT 1 OFFSET 6)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 7), (SELECT id FROM music LIMIT 1 OFFSET 7)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 8), (SELECT id FROM music LIMIT 1 OFFSET 8)),
    (gen_random_uuid(), (SELECT id FROM user LIMIT 1 OFFSET 9), (SELECT id FROM music LIMIT 1 OFFSET 9));
