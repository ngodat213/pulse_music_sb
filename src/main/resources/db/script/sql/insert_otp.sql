INSERT INTO user_otp (id, code, expired_at, user_id)
VALUES
    (1, '123456', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 0)),
    (2, '654321', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 1)),
    (3, '112233', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 2)),
    (4, '332211', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 3)),
    (5, '445566', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 4)),
    (6, '665544', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 5)),
    (7, '778899', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 6)),
    (8, '998877', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 7)),
    (9, '000111', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 8)),
    (10, '111000', NOW() + INTERVAL '10 minutes', (SELECT id FROM user LIMIT 1 OFFSET 9));
