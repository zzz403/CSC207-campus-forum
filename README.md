# CSC207 Campus Forum - Academia Imperial

## Overview
Academia Imperial's Campus Forum is a user-friendly and inclusive platform designed for students and faculty to discuss, share resources, post announcements, and exchange information about events. The forum is built with extensibility in mind, allowing for the easy addition of new features.

## Features
- **Post Management**: Create, edit, and delete posts.
- **Commenting**: Comment on posts and reply to comments.
- **Search**: Find posts and comments using a search bar.
- **Notifications**: Receive notifications for new comments or replies.
- **Real-Time Chat**: Engage in real-time conversations.
- **Admin Controls**: Manage users and content, ban users, and remove inappropriate content.
- **Board Navigation**: Browse different discussion boards.

## Technology Stack
- **UI**: Swing
- **Database**: H2
- **Backend**: Pure Java (excluding SQL for data storage)
- **JDK Version**: JDK 17

## User Stories
1. **Creating and Managing Posts**
2. **Commenting on Posts**
3. **Searching for Content**
4. **Receiving Notifications**
5. **Participating in Real-Time Chat**
6. **Admin Managing Users and Content**
7. **Browsing Different Boards**

## Domain Entities

### User
```java
public class User {
    private int userID;
    private String username;
    private String role; // user/admin
    private String email;
    private Date registrationDate;
    private List<Integer> postIds;
    private List<Integer> commentIds;
    // Getters and Setters
}
```

### Post
```java
public class Post {
    private int postID;
    private String title;
    private String content;
    private int authorId;
    private Date creationDate;
    private Date lastModifiedDate;
    private int boardId;
    private List<Integer> commentIds;
    // Getters and Setters
}
```

### Comment
```java
public class Comment {
    private int commentId;
    private String content;
    private int authorId;
    private int postId;
    private Date creationDate;
    private int parentCommentId; // or None
    private List<Integer> replyIds;
    // Getters and Setters
}
```

### Board
```java
public class Board {
    private int boardId;
    private String name;
    private String description;
    private List<Integer> postIds;
    // Getters and Setters
}
```

### ChatMessage
```java
public class ChatMessage {
    private int messageId;
    private String content;
    private int senderId;
    private int recipientId;
    private Timestamp timestamp;
    // Getters and Setters
}
```

### Notification
```java
public class Notification {
    private int notificationId;
    private String type; // reply/message
    private int recipientId;
    private int senderId;
    private String content;
    private Date creationDate;
    private Boolean isRead;
    // Getters and Setters
}
```

### ChatGroup
```java
public class ChatGroup {
    private int groupId;
    private String groupName;
    private List<Integer> memberIds;
    private List<Integer> messageIds;
    // Getters and Setters
}
```

### GroupMember
```java
public class GroupMember {
    private int memberId;
    private int userId;
    private int groupId;
    // Getters and Setters
}
```

### PostLike
```java
public class PostLike {
    private int likeId;
    private int userId;
    private int postId;
    // Getters and Setters
}
```

### CommentLike
```java
public class CommentLike {
    private int likeId;
    private int userId;
    private int commentId;
    // Getters and Setters
}
```

### FileData
```java
public class FileData {
    private int fileId;
    private String fileName;
    private byte[] fileContent;
    private int uploaderId;
    private Date uploadDate;
    // Getters and Setters
}
```

### MapData
```java
public class MapData {
    private int mapId;
    private String locationName;
    private String mapImage;
    private int uploaderId;
    private Date uploadDate;
    // Getters and Setters
}
```

### WaveformData
```java
public class WaveformData {
    private int waveformId;
    private byte[] waveformContent;
    private int uploaderId;
    private Date uploadDate;
    // Getters and Setters
}
```

## Features and APIs

### Chat
Our chat feature was created with the vision of providing a seamless and secure communication platform for users around the world. Whether you want to connect with friends, family, or colleagues, our app offers a range of features to make your conversations more engaging and convenient.

- **Sending Files and Pictures**: The system interacts with the operating system to handle file selection, reading, and sending.
- **Sending Maps**: Utilizes the `Mapbox` API to fetch and render maps. It provides detailed map data and various map functionalities.
- **Sending Audio Recordings**: Uses Java to interface with the operating system's audio recording capabilities.

### Create Post
Our "Create Post" feature was designed with the vision of empowering users to share their thoughts and opinions effortlessly. We believe that everyone should have a platform to express themselves and engage with a wider community. By leveraging advanced technology, we have created a user-friendly and efficient tool for you to post your ideas and viewpoints.

- **Create Post**: Easily share your ideas with our intuitive interface and rich text editing capabilities.
- **AI Writing Enhance**: Enhance your posts with `gpt-3.5-turbo-0125` model for grammar and style improvements for clearer and more compelling content.
- **Check Post**: Review and verify your post after publishing.

## Installation and Setup

### Prerequisites
- JDK 17
- Maven

### Clone the Repository
```bash
git clone https://github.com/zzz403/CSC207-campus-forum.git
cd CSC207-campus-forum
```

### Config API Token
```bash
vi src\main\java\com\imperial\academia\config\ApiKeyConfig.java
```
In here replease your **Mapbox** and **gpt-3.5-turbo-0125** model token under `MAP_BOX_API_KEY` and `GPT_3_5_turbo_0125_TOKEN` variable.

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn exec:java -Dexec.mainClass="com.imperial.academia.app.Main"
```

## Usage
Upon running the application, the Swing-based UI will launch, providing access to the forum's features. Users can sign up, log in, create posts, comment, and engage in real-time chat.

### Database
The application uses H2 as its database. Configuration details can be found in the `DatabaseConfig.java` file.

## Contributors
- **Zhongze (August) Zheng** - *Login & Signup Feature; Chat Feature*  
  [Zhongze's GitHub page](https://github.com/zzz403)

- **Zhengyu (Joey) Wang** - *Create Post & View Post Feature*  
  [Zhengyu's GitHub page](https://github.com/wzy403)

- **Ray Liu** - *View Profile Feature*  
  [Ray's GitHub page](https://github.com/zliu0312)


## Contribution Guidelines
1. Fork the repository.
2. Create a new branch (`git checkout -b <feature-branch>`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin <feature-branch>`).
5. Create a new Pull Request.

## License
This project is licensed under the GPL License. See the [LICENSE](LICENSE) file for details.

## Contact
For any questions or suggestions, please reach out to the project maintainers via GitHub issues.