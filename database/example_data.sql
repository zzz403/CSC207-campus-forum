-- Insert users
INSERT INTO users (username, password, email, role, registration_date, avatar_url, last_modified) VALUES
('admin','admin123','admin@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/admin_avatar.png', CURRENT_TIMESTAMP),
('john_doe', 'hashed_password1', 'john@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example2.png', CURRENT_TIMESTAMP),
('jane_smith', 'hashed_password2', 'jane@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/avater_example3.png', CURRENT_TIMESTAMP),
('alice_jones', 'hashed_password3', 'alice@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example4.png', CURRENT_TIMESTAMP),
('bob_brown', 'hashed_password4', 'bob@example.com', 'moderator', CURRENT_TIMESTAMP, 'resources/avatar/avater_example5.png', CURRENT_TIMESTAMP),
('charlie_clark', 'hashed_password5', 'charlie@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example6.png', CURRENT_TIMESTAMP);

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
INSERT INTO chat_groups (group_name, creation_date, is_group,last_modified) VALUES
('Developers Group', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Gaming Group', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Fitness Enthusiasts', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Travel Lovers', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Book Club', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Testing', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Music Lovers', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Photography Enthusiasts', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Cooking Club', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Outdoor Activities', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Movie Buffs', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP),
('Pet Lovers', CURRENT_TIMESTAMP, TRUE, CURRENT_TIMESTAMP);

-- Insert chat messages
INSERT INTO chat_messages (sender_id, group_id, content_type, content, timestamp) VALUES
(1, 1, 'text', 'Hey, how are you?', '2024-06-01 10:00:00'),
(1, 1, 'text', 'I''m good, thanks!', '2024-06-01 10:01:00'),
(2, 2, 'text', 'Anyone up for a game?', '2024-06-01 10:02:45'),
(3, 3, 'text', 'Let''s discuss some workout routines.', '2024-06-01 10:07:13'),
(4, 4, 'text', 'Has anyone been to Paris?', '2024-06-01 10:10:52'),
(5, 5, 'text', 'What book are you reading right now?', '2024-06-01 10:15:33'),
(2, 2, 'text', 'I just got a new game!', '2024-06-01 10:23:11'),
(3, 3, 'image', 'resources/image/3/test1.png', '2024-06-01 10:27:45'),
(1, 1, 'audio','resources/audio/for_test/admin_20240626_180700.wav', '2024-06-01 10:31:29'),
(1, 1, 'text', 'What are you working on now?', '2024-06-01 10:33:29'),
(2, 2, 'text', 'What game did you get?', '2024-06-01 10:36:40'),
(3, 2, 'text', 'I got the latest Call of Duty.', '2024-06-01 10:40:15'),
(4, 1, 'text', 'I am working on a new app.', '2024-06-01 10:48:55'),
(5, 5, 'text', 'Have you read the new book by Stephen King?', '2024-06-01 10:52:30'),
(3, 5, 'text', 'Yes, it is amazing!', '2024-06-01 10:57:12'),
(4, 4, 'text', 'What is your favorite place to travel?', '2024-06-01 11:01:45'),
(2, 4, 'text', 'I love going to Italy.', '2024-06-01 11:06:33'),
(5, 5, 'text', 'What do you think about the new book club guidelines?', '2024-06-01 11:10:18'),
(1, 5, 'text', 'I think they are very helpful.', '2024-06-01 11:14:50'),
(1, 1, 'text', 'Do you want to join the developers group?', '2024-06-01 11:19:25'),
(1, 1, 'text', 'I am good, thanks!', '2024-06-01 11:19:26'),
(1, 1, 'text', 'Hey, how are you?', '2024-06-01 11:19:27'),
(1, 1, 'text', 'Yes, it looks great.', '2024-06-01 11:19:28'),
(1, 1, 'text', 'What do you think about the new app features?', '2024-06-01 11:19:29'),
(3, 1, 'text', 'Sure, I would love to!', '2024-06-01 11:23:57'),
(2, 2, 'text', 'I got a new game!', '2024-06-01 11:28:42'),
(4, 4, 'text', 'Has anyone been to Paris?', '2024-06-01 11:32:15'),
(2, 2, 'text', 'Anyone up for a game?', '2024-06-01 11:41:00'),
(3, 3, 'text', 'Let''s discuss some workout routines.', '2024-06-01 11:45:35'),
(5, 5, 'text', 'What book are you reading right now?', '2024-06-01 11:50:20'),
(2, 2, 'text', 'Did you check the latest forum update?', '2024-06-01 11:59:05'),
(3, 3, 'text', 'I have a new workout routine to share.', '2024-06-01 12:08:43'),
(5, 5, 'text', 'Thanks for the recommendation!', '2024-06-01 12:13:30'),
(4, 1, 'text', 'I think they are really useful.', '2024-06-01 12:23:45'),
(2, 2, 'text', 'What is the latest game you played?', '2024-06-01 12:28:12'),
(3, 2, 'text', 'I played Cyberpunk 2077 recently.', '2024-06-01 12:32:59'),
(5, 5, 'text', 'Do you have any book recommendations?', '2024-06-01 12:37:44'),
(3, 5, 'text', 'I recommend "The Catcher in the Rye".', '2024-06-01 12:42:37'),
(4, 4, 'text', 'What are your travel plans for this year?', '2024-06-01 12:47:25'),
(2, 4, 'text', 'I am planning to visit Japan.', '2024-06-01 12:52:00'),
(1, 1, 'text', 'Do you have any updates on the project?', '2024-06-01 12:56:18'),
(1, 1, 'text', 'Let''s meet up tomorrow to discuss the details.', '2024-06-01 12:57:18'),
(1, 6, 'text', 'Have you listened to the new album by Taylor Swift?', '2024-06-01 13:05:50'),
(1, 6, 'text', 'Yes, it''s amazing!', '2024-06-01 13:10:32'),
(1, 7, 'text', 'What camera do you use for photography?', '2024-06-01 13:15:17'),
(1, 7, 'text', 'I use a Canon EOS 5D Mark IV.', '2024-06-01 13:19:45'),
(1, 8, 'text', 'Do you have any good recipes to share?', '2024-06-01 13:23:58'),
(1, 8, 'text', 'I love making homemade pizza.', '2024-06-01 13:28:41'),
(1, 2, 'text', 'What are your thoughts on the new tech trends?', '2024-06-01 13:33:22'),
(1, 2, 'text', 'I think AI and machine learning are the future.', '2024-06-01 13:38:10'),
(1, 3, 'text', 'What is your workout routine?', '2024-06-01 13:42:57'),
(1, 3, 'text', 'I usually do a mix of cardio and strength training.', '2024-06-01 13:47:20'),
(1, 4, 'text', 'What is your dream travel destination?', '2024-06-01 13:51:55'),
(1, 4, 'text', 'I have always wanted to visit New Zealand.', '2024-06-01 13:56:42'),
(1, 5, 'text', 'What book are you currently reading?', '2024-06-01 14:01:33'),
(1, 5, 'text', 'I am reading "To Kill a Mockingbird" by Harper Lee.', '2024-06-01 14:06:25'),
(1, 6, 'text', 'Who is your favorite musician?', '2024-06-01 14:11:17'),
(1, 6, 'text', 'I really like Ed Sheeran.', '2024-06-01 14:16:08'),
(1, 7, 'text', 'What is your favorite photo you have taken?', '2024-06-01 14:20:50'),
(1, 7, 'text', 'I took a great shot of the sunset last summer.', '2024-06-01 14:25:32'),
(1, 8, 'text', 'Do you prefer baking or cooking?', '2024-06-01 14:30:17'),
(1, 8, 'text', 'I enjoy both, but I think I prefer baking.', '2024-06-01 14:35:09'),
(1, 9, 'text', 'Has anyone tried the new hiking trail?', '2024-06-01 14:39:55'),
(2, 9, 'text', 'Yes, it''s beautiful and quite challenging.', '2024-06-01 14:44:30'),
(3, 9, 'text', 'I went last weekend, it was amazing!', '2024-06-01 14:49:15'),
(1, 10, 'text', 'What are your thoughts on the latest Marvel movie?', '2024-06-01 14:54:05'),
(4, 10, 'text', 'I loved the action scenes, but the plot was a bit weak.', '2024-06-01 14:58:43'),
(5, 10, 'text', 'The visuals were stunning though!', '2024-06-01 15:03:20'),
(1, 11, 'text', 'Do you have any tips for taking care of a new puppy?', '2024-06-01 15:07:58'),
(2, 11, 'text', 'Make sure to establish a routine and be patient.', '2024-06-01 15:12:40'),
(5, 11, 'text', 'Puppy training classes can be very helpful.', '2024-06-01 15:17:22'),
(1, 9, 'text', 'Any plans for the weekend hike?', '2024-06-01 15:21:55'),
(3, 9, 'text', 'I am planning to go early in the morning to avoid the heat.', '2024-06-01 15:26:37'),
(2, 9, 'text', 'I might join you. What time are you thinking?', '2024-06-01 15:31:12'),
(1, 10, 'text', 'What movie should we watch for our next movie night?', '2024-06-01 15:35:50'),
(4, 10, 'text', 'How about the new sci-fi thriller?', '2024-06-01 15:40:25'),
(5, 10, 'text', 'Sounds good to me!', '2024-06-01 15:45:03'),
(1, 11, 'text', 'Does anyone have a good vet recommendation?', '2024-06-01 15:49:45'),
(2, 11, 'text', 'Yes, Dr. Smith at the downtown clinic is great.', '2024-06-01 15:54:18'),
(5, 11, 'text', 'I second that, Dr. Smith is awesome.', '2024-06-01 15:59:00'),
(1, 9, 'text', 'What gear do you recommend for a beginner hiker?', '2024-06-01 16:03:35'),
(2, 9, 'text', 'Comfortable shoes and a good backpack are essential.', '2024-06-01 16:08:13'),
(3, 9, 'text', 'Don''t forget a water bottle and some snacks.', '2024-06-01 16:13:00');

-- (1, 1, 'audio', 'resources/audio/1/admin_20240701_174611.wav', '2024-07-01 17:46:11'),
-- (3, 1, 'audio', 'resources/audio/1/john_doe_20240701_180149.wav', '2024-07-01 18:01:49'),
-- (3, 1, 'audio', 'resources/audio/1/john_doe_20240701_183942.wav', '2024-07-01 18:39:42'),
-- (2, 1, 'audio', 'resources/audio/1/2_20240704_154636.wav', '2024-07-04 15:46:36')


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
(5, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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


-- Insert audio_waveforms
INSERT INTO audio_waveforms (message_id, min_values, max_values, duration) VALUES
(9,
 '[-502, -607, -540, -471, -501, -669, -547, -2040, -1358, -1383, -1241, -1306, -1366, -1021, -1238, -1482, -820, -753, -1021, -688, -583, -740, -507, -546, -469, -512, -578, -645, -498, -569]',
 '[481, 506, 504, 584, 570, 660, 661, 1915, 1736, 1518, 1222, 1396, 1665, 1218, 1268, 1379, 997, 830, 1004, 634, 495, 647, 463, 734, 551, 751, 520, 671, 586, 557]',
 5.374875283446712);