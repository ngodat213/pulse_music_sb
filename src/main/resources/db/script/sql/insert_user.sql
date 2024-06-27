INSERT INTO user (id, password, email, full_name, avatar_id, bio, role, created_at, enabled, count_fail, lock_expired, token_reset_password, token_reset_password_expired)
VALUES
    (gen_random_uuid(), 'password1', 'user1@example.com', 'User One', NULL, 'Bio One', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password2', 'user2@example.com', 'User Two', NULL, 'Bio Two', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password3', 'user3@example.com', 'User Three', NULL, 'Bio Three', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password4', 'user4@example.com', 'User Four', NULL, 'Bio Four', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password5', 'user5@example.com', 'User Five', NULL, 'Bio Five', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password6', 'user6@example.com', 'User Six', NULL, 'Bio Six', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password7', 'user7@example.com', 'User Seven', NULL, 'Bio Seven', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password8', 'user8@example.com', 'User Eight', NULL, 'Bio Eight', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password9', 'user9@example.com', 'User Nine', NULL, 'Bio Nine', 'USER', NOW(), true, 0, NULL, NULL, NULL),
    (gen_random_uuid(), 'password10', 'user10@example.com', 'User Ten', NULL, 'Bio Ten', 'USER', NOW(), true, 0, NULL, NULL, NULL);
