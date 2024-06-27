INSERT INTO music (id, title, description, duration, play_count, heart_count, image_id, mp3_id, user_id)
VALUES
    (gen_random_uuid(), 'Song 1', 'Description 1', 180, 100, 10, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 0)),
    (gen_random_uuid(), 'Song 2', 'Description 2', 200, 150, 20, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 1)),
    (gen_random_uuid(), 'Song 3', 'Description 3', 220, 200, 30, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 2)),
    (gen_random_uuid(), 'Song 4', 'Description 4', 240, 250, 40, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 3)),
    (gen_random_uuid(), 'Song 5', 'Description 5', 260, 300, 50, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 4)),
    (gen_random_uuid(), 'Song 6', 'Description 6', 280, 350, 60, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 5)),
    (gen_random_uuid(), 'Song 7', 'Description 7', 300, 400, 70, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 6)),
    (gen_random_uuid(), 'Song 8', 'Description 8', 320, 450, 80, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 7)),
    (gen_random_uuid(), 'Song 9', 'Description 9', 340, 500, 90, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 8)),
    (gen_random_uuid(), 'Song 10', 'Description 10', 360, 550, 100, NULL, NULL, (SELECT id FROM user LIMIT 1 OFFSET 9));
