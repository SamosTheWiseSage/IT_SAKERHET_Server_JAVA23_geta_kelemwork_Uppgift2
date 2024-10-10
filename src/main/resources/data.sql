-- Insert initial users
INSERT INTO user (email, password) VALUES ('user1@example.com', '$2a$10$e0MnZ8s8F2c8Z5Li0N.4QOQiP0X0UpXCljKhBkB98nA6qRlU3V7t2'); -- password: password123
INSERT INTO user (email, password) VALUES ('user2@example.com', '$2a$10$e0MnZ8s8F2c8Z5Li0N.4QOQiP0X0UpXCljKhBkB98nA6qRlU3V7t2'); -- password: password123

-- Assuming user with ID 1 has the email 'user1@example.com'
INSERT INTO time_capsule (encrypted_message, user_id) VALUES ('U2FsdGVkX1...', 1); -- Replace with actual encrypted message
INSERT INTO time_capsule (encrypted_message, user_id) VALUES ('U2FsdGVkX1...', 1); -- Replace with actual encrypted message
