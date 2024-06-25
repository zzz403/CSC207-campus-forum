-- Insert users
INSERT INTO users (username, password, email, role, registration_date, avatar_url, last_modified) VALUES
('admin','admin123','admin@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/admin_avatar.png', CURRENT_TIMESTAMP),
('john_doe', 'hashed_password1', 'john@example.com', 'user', CURRENT_TIMESTAMP, 'https://i.pravatar.cc/150?img=2', CURRENT_TIMESTAMP),
('jane_smith', 'hashed_password2', 'jane@example.com', 'admin', CURRENT_TIMESTAMP, 'https://i.pravatar.cc/150?img=3', CURRENT_TIMESTAMP),
('alice_jones', 'hashed_password3', 'alice@example.com', 'user', CURRENT_TIMESTAMP, 'https://i.pravatar.cc/150?img=4', CURRENT_TIMESTAMP),
('bob_brown', 'hashed_password4', 'bob@example.com', 'moderator', CURRENT_TIMESTAMP, 'https://i.pravatar.cc/150?img=5', CURRENT_TIMESTAMP),
('charlie_clark', 'hashed_password5', 'charlie@example.com', 'user', CURRENT_TIMESTAMP, 'http://i.pravatar.cc/150?img=6', CURRENT_TIMESTAMP);

-- Insert boards
INSERT INTO boards (name, description, creator_id, created_at) VALUES
('General Discussion', 'A place for general topics', 1, CURRENT_TIMESTAMP),
('Tech Talk', 'Discuss the latest in technology', 2, CURRENT_TIMESTAMP),
('Health & Fitness', 'Share your health and fitness tips', 3, CURRENT_TIMESTAMP),
('Travel', 'Discuss travel destinations and tips', 4, CURRENT_TIMESTAMP),
('Books & Literature', 'Talk about your favorite books', 5, CURRENT_TIMESTAMP);

-- Insert posts
INSERT INTO posts (title, content, author_id, board_id, creation_date, last_modified_date) VALUES
('Welcome to the forum', 'This is the first post in the general discussion board.', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tech News', 'Let''s talk about the latest tech news.', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fitness Tips', 'Share your best fitness tips here.', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Favorite Destinations', 'What are your favorite travel destinations?', 4, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Book Recommendations', 'Recommend some good books to read.', 5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert comments
INSERT INTO comments (content, author_id, post_id, parent_comment_id, creation_date, last_modified) VALUES
('Great post!', 2, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I agree, very interesting.', 1, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Thanks for the tips!', 4, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I love visiting Japan.', 3, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I recommend "1984" by George Orwell.', 2, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Thanks for the recommendation!', 1, 5, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert chat groups
INSERT INTO chat_groups (group_name, creation_date, last_modified) VALUES
('Developers Group', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gaming Group', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fitness Enthusiasts', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Travel Lovers', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Book Club', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert chat messages
INSERT INTO chat_messages (sender_id, recipient_id, group_id, content, timestamp) VALUES
(1, 2, 1, 'Hey, how are you?', CURRENT_TIMESTAMP),
(2, 1, 2, 'Anyone up for a game?', CURRENT_TIMESTAMP),
(3, 4, 3, 'Let''s discuss some workout routines.', CURRENT_TIMESTAMP),  
(4, 3, 4, 'Has anyone been to Paris?', CURRENT_TIMESTAMP),
(5, 1, 5, 'What book are you reading right now?', CURRENT_TIMESTAMP),   
(1, 5, 1, 'I''m good, thanks!', CURRENT_TIMESTAMP),
(2, 3, 2, 'I just got a new game!', CURRENT_TIMESTAMP);


-- Insert group members
INSERT INTO group_members (group_id, user_id, role, joined_date, last_modified) VALUES
(1, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 3, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 5, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 1, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 2, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 4, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert post likes
INSERT INTO post_likes (user_id, post_id, liked_at) VALUES
(1, 1, CURRENT_TIMESTAMP),
(2, 2, CURRENT_TIMESTAMP),
(3, 3, CURRENT_TIMESTAMP),
(4, 4, CURRENT_TIMESTAMP),
(5, 5, CURRENT_TIMESTAMP);

-- Insert comment likes
INSERT INTO comment_likes (user_id, comment_id, liked_at) VALUES
(1, 1, CURRENT_TIMESTAMP),
(2, 2, CURRENT_TIMESTAMP),
(3, 3, CURRENT_TIMESTAMP),
(4, 4, CURRENT_TIMESTAMP),
(5, 5, CURRENT_TIMESTAMP),
(1, 6, CURRENT_TIMESTAMP);

-- Insert user follows
INSERT INTO user_follows (follower_id, followee_id, followed_at) VALUES
(1, 2, CURRENT_TIMESTAMP),
(2, 3, CURRENT_TIMESTAMP),
(3, 4, CURRENT_TIMESTAMP),
(4, 5, CURRENT_TIMESTAMP),
(5, 1, CURRENT_TIMESTAMP);

-- Insert files
INSERT INTO files (uploader_id, post_id, comment_id, file_url, uploaded_at, last_modified) VALUES
(1, 1, NULL, 'http://example.com/file1.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, NULL, 'http://example.com/file2.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, NULL, 'http://example.com/file3.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 4, NULL, 'http://example.com/file4.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 5, NULL, 'http://example.com/file5.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert notifications
INSERT INTO notifications (user_id, type, reference_id, created_at, read_at, last_modified) VALUES
(1, 'post_like', 1, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP),
(2, 'comment_like', 2, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP),
(3, 'new_follower', 3, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP),
(4, 'mention', 4, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP),
(5, 'message', 5, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP);
