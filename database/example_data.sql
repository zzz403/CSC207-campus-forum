-- Insert users
INSERT INTO users (username, password, email, role, registration_date, avatar_url, last_modified) VALUES
('admin','admin123','admin@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/admin_avatar.png', CURRENT_TIMESTAMP),
('long', 'long123', 'john@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example2.png', CURRENT_TIMESTAMP),
('jane_smith', 'hashed_password2', 'jane@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/avater_example3.png', CURRENT_TIMESTAMP),
('alice_jones', 'hashed_password3', 'alice@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example4.png', CURRENT_TIMESTAMP),
('bob_brown', 'hashed_password4', 'bob@example.com', 'moderator', CURRENT_TIMESTAMP, 'resources/avatar/avater_example5.png', CURRENT_TIMESTAMP),
('charlie_clark', 'hashed_password5', 'charlie@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example6.png', CURRENT_TIMESTAMP),
('david_lee', 'hashed_password6', 'david@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example7.png', CURRENT_TIMESTAMP),
('emily_wilson', 'hashed_password7', 'emily@example.com', 'moderator', CURRENT_TIMESTAMP, 'resources/avatar/avater_example8.png', CURRENT_TIMESTAMP),
('frank_harris', 'hashed_password8', 'frank@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example9.png', CURRENT_TIMESTAMP),
('grace_martin', 'hashed_password9', 'grace@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/avater_example10.png', CURRENT_TIMESTAMP),
('henry_white', 'hashed_password10', 'henry@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example11.png', CURRENT_TIMESTAMP),
('isabella_kim', 'hashed_password11', 'isabella@example.com', 'moderator', CURRENT_TIMESTAMP, 'resources/avatar/avater_example12.png', CURRENT_TIMESTAMP),
('james_young', 'hashed_password12', 'james@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example13.png', CURRENT_TIMESTAMP),
('karen_sanders', 'hashed_password13', 'karen@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example14.png', CURRENT_TIMESTAMP),
('lucas_roberts', 'hashed_password14', 'lucas@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/avater_example15.png', CURRENT_TIMESTAMP),
('mia_moore', 'hashed_password15', 'mia@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example16.png', CURRENT_TIMESTAMP),
('noah_clarkson', 'hashed_password16', 'noah@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example17.png', CURRENT_TIMESTAMP),
('olivia_green', 'hashed_password17', 'olivia@example.com', 'moderator', CURRENT_TIMESTAMP, 'resources/avatar/avater_example18.png', CURRENT_TIMESTAMP),
('paul_baker', 'hashed_password18', 'paul@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example19.png', CURRENT_TIMESTAMP),
('quinn_adams', 'hashed_password19', 'quinn@example.com', 'admin', CURRENT_TIMESTAMP, 'resources/avatar/avater_example20.png', CURRENT_TIMESTAMP),
('rachel_turner', 'hashed_password20', 'rachel@example.com', 'user', CURRENT_TIMESTAMP, 'resources/avatar/avater_example21.png', CURRENT_TIMESTAMP);

-- Insert boards
INSERT INTO boards (name, description, creator_id, created_at) VALUES
('General Discussion', 'A place for general topics', 1, CURRENT_TIMESTAMP),
('Tech Talk', 'Discuss the latest in technology', 2, CURRENT_TIMESTAMP),
('Health & Fitness', 'Share your health and fitness tips', 3, CURRENT_TIMESTAMP),
('Travel', 'Discuss travel destinations and tips', 4, CURRENT_TIMESTAMP),
('Books & Literature', 'Talk about your favorite books', 5, CURRENT_TIMESTAMP);

-- Insert posts
INSERT INTO posts (title, content, author_id, board_id, creation_date, last_modified_date) VALUES
('Welcome to the forum',  'This is the first post in the general discussion board. We are excited to have you here! Feel free to introduce yourself and share what you hope to gain from this community. Let’s build a friendly and supportive environment for everyone.', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tech News', 'Technology has become an integral part of our daily lives, shaping how we communicate, work, and entertain ourselves. The rapid advancements in technology have brought about significant changes across various sectors, from healthcare and education to transportation and entertainment. This article explores the profound impact of technology on society, highlighting both the positive and negative aspects of this digital revolution.

**1. The Evolution of Communication**

One of the most noticeable impacts of technology is the transformation of communication. Gone are the days when sending a letter or making a long-distance phone call was the primary means of staying in touch. Today, technology has made communication instant and accessible through various platforms such as social media, messaging apps, and video conferencing tools.

- **Social Media**: Platforms like Facebook, Twitter, and Instagram have changed how we connect with others. They allow us to share our thoughts, photos, and videos with a global audience in real-time, fostering a sense of community and connectivity.

- **Messaging Apps**: Applications like WhatsApp, Telegram, and Signal have revolutionized the way we send messages. With features like group chats, voice notes, and multimedia sharing, these apps offer a versatile and convenient communication experience.

- **Video Conferencing**: Tools like Zoom, Microsoft Teams, and Google Meet have become essential for both personal and professional communication, especially during the COVID-19 pandemic. They enable face-to-face interactions across vast distances, making remote work and virtual events possible.

**2. Transforming the Workplace**

Technology has significantly impacted the workplace, altering how businesses operate and how employees perform their tasks.

- **Remote Work**: The advent of technology has made remote work feasible, allowing employees to work from anywhere with an internet connection. This flexibility has led to improved work-life balance and increased productivity for many.

- **Automation and AI**: Automation and artificial intelligence have streamlined processes across various industries, reducing the need for manual labor and increasing efficiency. While this has led to job displacement in some sectors, it has also created new opportunities in tech-driven fields.

- **Collaboration Tools**: Software like Slack, Asana, and Trello have enhanced collaboration among team members, enabling real-time communication and project management. These tools facilitate seamless teamwork, even in distributed teams.

**3. Advancements in Healthcare**

Technology has transformed the healthcare industry, improving patient care and outcomes through innovations such as telemedicine, electronic health records, and medical devices.

- **Telemedicine**: Telemedicine platforms have made healthcare more accessible by allowing patients to consult with doctors remotely. This is particularly beneficial for those living in rural or underserved areas.

- **Electronic Health Records (EHRs)**: EHRs have digitized patient information, making it easier for healthcare providers to access and share data. This has led to more accurate diagnoses and coordinated care.

- **Medical Devices**: Advanced medical devices, such as wearable health trackers and robotic surgical systems, have improved the accuracy and efficiency of medical procedures, enhancing patient outcomes.

**4. Education and Learning**

Technology has revolutionized education by providing new ways to learn and access information.

- **Online Learning**: Online platforms like Coursera, Khan Academy, and Udemy offer courses on a wide range of subjects, making education more accessible to people worldwide. This democratization of knowledge has empowered individuals to learn at their own pace and convenience.

- **Interactive Learning Tools**: Technology has introduced interactive learning tools, such as virtual labs and educational apps, that enhance the learning experience. These tools engage students and make complex concepts easier to understand.

- **Virtual Classrooms**: The COVID-19 pandemic accelerated the adoption of virtual classrooms, allowing students to continue their education remotely. Platforms like Google Classroom and Blackboard facilitate online learning and collaboration between teachers and students.

**5. The Entertainment Industry**

The entertainment industry has undergone a digital transformation, with technology playing a crucial role in content creation, distribution, and consumption.

- **Streaming Services**: Platforms like Netflix, Spotify, and YouTube have changed how we consume media, offering on-demand access to movies, music, and videos. This shift has disrupted traditional media channels, such as cable TV and radio.

- **Gaming**: The gaming industry has seen significant advancements with the introduction of virtual reality (VR) and augmented reality (AR). These technologies offer immersive experiences, blurring the line between the digital and physical worlds.

- **Content Creation**: Technology has democratized content creation, allowing individuals to produce and share their work with a global audience. Social media platforms and streaming services provide a stage for creators to showcase their talents and reach millions.

**6. Transportation and Smart Cities**

Technology has also impacted transportation, leading to the development of smart cities and innovative mobility solutions.

- **Electric Vehicles (EVs)**: The rise of electric vehicles, such as Tesla and Nissan Leaf, has revolutionized the automotive industry, promoting sustainability and reducing carbon emissions.

- **Autonomous Vehicles**: Self-driving cars are on the horizon, promising to transform the way we travel and reduce traffic accidents caused by human error.

- **Smart City Initiatives**: Technology is at the heart of smart city initiatives, where data-driven solutions optimize urban living. From smart traffic lights to waste management systems, these technologies aim to enhance the quality of life for residents.

**7. The Dark Side of Technology**

While technology has brought numerous benefits, it also poses challenges and risks that must be addressed.

- **Privacy Concerns**: The collection and use of personal data by tech companies have raised privacy concerns among users. Ensuring data protection and transparency is crucial to maintaining trust in technology.

- **Cybersecurity Threats**: As technology becomes more integrated into our lives, cybersecurity threats, such as hacking and identity theft, have increased. Protecting sensitive information and developing robust security measures are essential.

- **Digital Divide**: Despite the widespread adoption of technology, a digital divide still exists between those who have access to technology and those who do not. Bridging this gap is vital to ensuring equal opportunities for all.

- **Addiction and Mental Health**: The overuse of technology, particularly social media, can lead to addiction and negative impacts on mental health. Promoting healthy technology use and digital well-being is important for a balanced lifestyle.

**Conclusion**

Technology has undoubtedly transformed society, offering unprecedented opportunities and challenges. As we continue to embrace digital advancements, it is crucial to navigate the complexities of this tech-driven world thoughtfully and ethically. By leveraging technology responsibly, we can create a future that enhances our lives and fosters a more connected, sustainable, and equitable society.', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fitness Tips', 'Share your best fitness tips here.', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Favorite Destinations', 'What are your favorite travel destinations?', 4, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Book Recommendations', 'Recommend some good books to read.', 5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gaming Discussion', 'What games are you currently playing?', 6, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Photography Tips', 'Share your best photography tips and tricks.', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cooking Recipes', 'Post your favorite recipes here.', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Music Recommendations', 'What music are you listening to these days?', 9, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Programming Challenges', 'Share some interesting coding challenges.', 10, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Travel Stories', 'Share your most memorable travel experiences.', 11, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pet Care Advice', 'How do you take care of your pets?', 12, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Art and Creativity', 'Showcase your artwork and creative projects.', 13, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sports Talk', 'Discuss the latest sports news and events.', 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Movie Night', 'What movies have you watched recently?', 15, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Healthy Eating', 'Share your favorite healthy recipes and snacks.', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('DIY Projects', 'Post your latest do-it-yourself projects here.', 17, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Career Advice', 'Discuss career tips and job hunting strategies.', 18, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tech Gadgets', 'What new gadgets are you excited about?', 19, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Education and Learning', 'Share your learning experiences and resources.', 20, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert comments
INSERT INTO comments (content, author_id, post_id, parent_comment_id, creation_date, last_modified) VALUES
('Great post!', 2, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I agree, very interesting.', 1, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Thanks for the tips!', 4, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I love visiting Japan.', 3, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I recommend "1984" by George Orwell.', 2, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Thanks for the recommendation!', 1, 5, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Comments for Post 1
('This article really highlights the importance of technology in modern society.', 3, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I learned a lot about how technology impacts different sectors. Thanks for sharing!', 4, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I think technology also plays a huge role in environmental conservation efforts.', 5, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Great insights! How do you think technology will shape the future of education?', 6, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('The part about AI and automation was particularly fascinating. More jobs are getting automated!', 7, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I believe there should be more emphasis on ethical tech development.', 8, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Nice write-up! I think privacy concerns are a major issue with tech advancement.', 9, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Agreed! We need to be cautious about data security.', 10, 1, 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I think technology also helps to connect people in ways we never imagined.', 11, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Do you think tech companies are doing enough to address cybersecurity threats?', 12, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Comments for Post 2
('Tech news is always exciting to keep up with. What do you guys think about the new iPhone release?', 3, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I think the latest AI developments are game-changing for industries.', 4, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I’m excited about the upcoming tech conferences. Anyone planning to attend CES this year?', 5, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Has anyone tried the new VR headsets? Heard they have some impressive features!', 6, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('What do you think is the most important tech trend this year?', 7, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I’m curious about how 5G will change our connectivity and data speeds.', 8, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('I heard there’s a new breakthrough in quantum computing. Any thoughts?', 9, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert chat groups
INSERT INTO chat_groups (group_name, creation_date, is_group,last_modified) VALUES
('Developers Group', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Gaming Group', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Fitness Enthusiasts', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Travel Lovers', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Book Club', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Testing', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Music Lovers', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Photography Enthusiasts', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Cooking Club', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Outdoor Activities', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Movie Buffs', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Pet Lovers', '2024-06-01 10:00:00', TRUE, '2024-06-01 10:00:00'),
('Private Chat', '2024-06-01 10:00:00', FALSE, '2024-06-01 10:00:00');

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
(3, 9, 'text', 'Don''t forget a water bottle and some snacks.', '2024-06-01 16:13:00'),
(1, 13, 'text', 'Hello', '2024-07-01 16:13:00'),
(1,1, 'map', 'resources/map/1/1_-80.554000_43.474000_1720424928.png','2024-07-05 16:13:00');

-- (1, 1, 'audio', 'resources/audio/1/admin_20240701_174611.wav', '2024-07-01 17:46:11'),
-- (3, 1, 'audio', 'resources/audio/1/john_doe_20240701_180149.wav', '2024-07-01 18:01:49'),
-- (3, 1, 'audio', 'resources/audio/1/john_doe_20240701_183942.wav', '2024-07-01 18:39:42'),
-- (2, 1, 'audio', 'resources/audio/1/2_20240704_154636.wav', '2024-07-04 15:46:36')


-- Insert group members
INSERT INTO group_members (group_id, user_id, role, joined_date, last_modified) VALUES
(1, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 3, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 5, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 1, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 2, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 4, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 1, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 3, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 4, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 2, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 5, 'member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 1, 'admin', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
(13, 2, 'admin', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

-- Insert post likes
INSERT INTO post_likes (user_id, post_id, liked_at) VALUES
(1, 1, CURRENT_TIMESTAMP),
(2, 2, CURRENT_TIMESTAMP),
(3, 3, CURRENT_TIMESTAMP),
(4, 4, CURRENT_TIMESTAMP),
(5, 5, CURRENT_TIMESTAMP),
(6, 1, CURRENT_TIMESTAMP),
(7, 2, CURRENT_TIMESTAMP),
(8, 3, CURRENT_TIMESTAMP),
(9, 4, CURRENT_TIMESTAMP),
(10, 5, CURRENT_TIMESTAMP),
(11, 6, CURRENT_TIMESTAMP),
(12, 7, CURRENT_TIMESTAMP),
(13, 8, CURRENT_TIMESTAMP),
(14, 9, CURRENT_TIMESTAMP),
(15, 10, CURRENT_TIMESTAMP),
(16, 11, CURRENT_TIMESTAMP),
(17, 12, CURRENT_TIMESTAMP),
(18, 13, CURRENT_TIMESTAMP),
(19, 14, CURRENT_TIMESTAMP),
(20, 15, CURRENT_TIMESTAMP),
(1, 6, CURRENT_TIMESTAMP),
(2, 7, CURRENT_TIMESTAMP),
(3, 8, CURRENT_TIMESTAMP),
(4, 9, CURRENT_TIMESTAMP),
(5, 10, CURRENT_TIMESTAMP),
(6, 11, CURRENT_TIMESTAMP),
(7, 12, CURRENT_TIMESTAMP),
(8, 13, CURRENT_TIMESTAMP),
(9, 14, CURRENT_TIMESTAMP),
(10, 15, CURRENT_TIMESTAMP);

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

INSERT INTO map_data (message_id, latitude, longitude, location_info) VALUES
(85, 43.474, -80.554, 'Columbia Lake Firepit, Waterloo, Ontario N2L 6J7, Canada');